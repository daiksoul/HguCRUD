package we.crud.word;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
public class WordCRUD implements ICRUD{
    private ArrayList<Word> list;
    Scanner s;
    private int wordCounter = 0;
    private List<Word> filtered;

    public WordCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
    }

    public Object add() {
        System.out.print("=> 새 난이도 (1,2,3) 입력 : ");
        int lvl = rightInput(
                "=>오류] 1에서 3까지의 값을 입력해주세요 : ",
                (val) -> val>=1&&val<=3,
                Integer::parseInt
        );
        System.out.print("=> 새 단어 입력 : ");
        String word = s.nextLine();
        System.out.print("=> 뜻 입력 : ");
        String meaning = s.nextLine();

        return new Word(0,lvl,word,meaning);
    }

    @Override
    public void addWord(){
        Word word = (Word) add();
        word.setId(wordCounter++);
        list.add(word);
        System.out.println("새 단어가 단어장에 추가되었습니다.");
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }

    @Override
    public int list(Predicate<Word> filter, Comparator<Word> comparator) {
        int c = 1;
        filtered = list.stream().filter(filter).sorted(comparator).toList();
        if(filtered.size()<1){
            System.out.println("[결과가 없습니다.]");
            return 0;
        }
        System.out.println("---------------------------");
        for (Word word : filtered)
            System.out.println((c++) + " " + word.toString());
        System.out.println("---------------------------");
        return filtered.size();
    }

    public void listAll(){
        list((word)-> true, Comparator.comparing(Word::getWord));
    }

    public void listFilterByLvl(){
        System.out.print("=> 난이도(1,2,3) : ");
        int lvl = rightInput(
                "=>오류] 1에서 3까지의 값을 입력해주세요 : ",
                (val) -> val>=1&&val<=3,
                Integer::parseInt
        );
        list((word)->word.getLvl()==lvl,Comparator.comparing(Word::getWord));
    }

    public void searchWord(){
        System.out.print("=> 검색어를 입력하세요 : ");
        String key = s.next();
        s.nextLine();
        int c = list((word)->word.getWord().contains(key),Comparator.comparing(Word::getWord));
        System.out.println(
                "총 "+c+"개의 결과가 검색되었습니다.\n" +
                "---------------------------");
    }

    public void deleteWord(){
        System.out.print("=> 삭제할 단어를 검색하세요 : ");
        String key = s.next(); s.nextLine();
        int c = list((word)->word.getWord().contains(key),Comparator.comparing(Word::getWord));
        if(c<1) return;
        System.out.print("삭제할 단어의 번호를 입력하세요 : ");
        int idx = rightInput(
                "=>오류] 1에서 "+filtered.size()+"까지의 값을 입력해주세요 : ",
                (val)-> val >=1&& val<=filtered.size(),
                Integer::parseInt
        );
        System.out.print("정말로 단어 "+filtered.get(idx-1).getWord()+"를 삭제하시겠습니까? (Y/N) : ");
        String answer = rightInput(
                "=>오류] Y또는 N을 입력해주세요 : ",
                (val)-> val.equalsIgnoreCase("Y") || val.equalsIgnoreCase("N"),
                (x)->x
        );
        switch (answer.toUpperCase()) {
            case "Y" -> {
                list.remove(filtered.get(idx - 1));
                System.out.println("삭제되었습니다.");
            }
            case "N" -> System.out.println("취소되었습니다.");
        }
    }

    public void updateWord(){
        System.out.print("=> 수정할 단어를 검색하세요 : ");
        String key = s.next(); s.nextLine();
        int c = list((word)->word.getWord().contains(key),Comparator.comparing(Word::getWord));
        if(c<1) return;
        System.out.print("수정할 단어의 번호를 입력하세요 : ");
        int tidx = rightInput(
                "=>오류] 1에서 "+filtered.size()+"까지의 값을 입력해주세요 : ",
                (val)-> val >=1&& val<=filtered.size(),
                Integer::parseInt
        );
        int idx = list.indexOf(filtered.get(tidx-1));
        Word prevWord = list.get(idx);
        Word word = (Word) add();
        word.setId(prevWord.getId());
        System.out.println(
                "-".repeat(31)+"\n" +
                String.format("%15s | %15s\n",prevWord.getWord(),word.getWord())+
                String.format("%15s | %15s\n","*".repeat(prevWord.getLvl()),"*".repeat(word.getLvl()))+
                String.format("%15s | %15s\n",prevWord.getMeaning(),word.getMeaning())+
                "-".repeat(31)
        );
        System.out.print("정말로 단어를 수정하시겠습니까? (Y/N) : ");
        String answer = rightInput(
                "=>오류] Y또는 N을 입력해주세요 : ",
                (val)-> val.equalsIgnoreCase("Y") || val.equalsIgnoreCase("N"),
                (x)->x
        );

        switch (answer.toUpperCase()) {
            case "Y" -> {
                list.set(idx,word);
                System.out.println("수정되었습니다.");
            }
            case "N" -> System.out.println("취소되었습니다.");
        }
    }

    public <T> T rightInput(String errMsg, Predicate<T> validator, Function<String,T> parser){
        String str;
        T val;
        while(true){
            str = s.nextLine().trim();
            try {
                val = parser.apply(str);
            }catch (Exception e){
                System.out.print(errMsg);
                continue;
            }
            if(validator.test(val))
                break;
            System.out.print(errMsg);
        }
        return val;
    }

    public void saveWord(){
        try {
            Writer w = new FileWriter("word.txt");
            for(Word word : list.stream().sorted(Comparator.comparing(Word::getId)).toList()){
                w.write(word.toFileString()+"\n");
            }
            System.out.println("파일이 저장되었습니다!");
            w.close();
        } catch (IOException e) {
            System.out.println("파일 저장에 실패하였습니다!");
        }
    }

    public int loadWord(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("word.txt"));
            String line;
            while ((line = br.readLine())!=null){
                StringTokenizer tokenizer = new StringTokenizer(line,"\\|");
                int lvl = Integer.parseInt(tokenizer.nextToken());
                String word = tokenizer.nextToken();
                String meaning = tokenizer.nextToken();
                list.add(new Word(wordCounter++,lvl,word,meaning));
            }
        } catch (IOException e) {
            System.out.println("파일 로딩에 실패하였습니다!");
        }
        return list.size();
    }
}
