package we.crud.word;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    private ArrayList<Word> list;
    Scanner s;
    static int wordCounter = 0;

    public WordCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
    }

    @Override
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

    public void listAll(){
        System.out.println("---------------------------");
        for(int i = 0; i<list.size(); i++)
            System.out.println((i+1)+" "+list.get(i).toString());
        System.out.println("---------------------------");
    }
}
