package we.crud.word;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Function;

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
    public int list(Function<Word,Boolean> filter, Comparator<Word> comparator) {
        list.sort(comparator);
        int c = 1;
        System.out.println("---------------------------");
        for (Word word : list)
            if (filter.apply(word))
                System.out.println((c++) + " " + word.toString());
        System.out.println("---------------------------");
        return c;
    }

    public void listAll(){
        list((word)-> true, Comparator.comparingInt(Word::getId));
    }

    public void listFilterByLvl(){
        System.out.print("=> 난이도(1,2,3) : ");
        int lvl = s.nextInt();
        s.nextLine();
        list((word)->word.getLvl()==lvl,Comparator.comparingInt(Word::getId));
    }
}
