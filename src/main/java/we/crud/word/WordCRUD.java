package we.crud.word;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Predicate;
public class WordCRUD implements ICRUD{
    private ArrayList<Word> list;
    Scanner s;
    static int wordCounter = 0;

    public WordCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
    }

    public Object add() {
        System.out.print("=> 난이도 (1,2,3) : ");
        int lvl = s.nextInt();
        s.nextLine();
        System.out.print("=> 새 단어 입력 : ");
        String word = s.nextLine();
        System.out.print("=> 뜻 입력 : ");
        String meaning = s.nextLine();

        return new Word(wordCounter++,lvl,word,meaning);
    }

    @Override
    public void addWord(){
        Word word = (Word) add();
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
        System.out.println("---------------------------");
        for (Word word : list.stream().filter(filter).sorted(comparator).toList())
            System.out.println((c++) + " " + word.toString());
        System.out.println("---------------------------");
        return c-1;
    }

    public void listAll(){
        list((word)-> true, Comparator.comparing(Word::getWord));
    }

    public void listFilterByLvl(){
        System.out.print("=> 난이도(1,2,3) : ");
        int lvl = s.nextInt();
        s.nextLine();
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
}
