package we.crud.word;

import java.util.Scanner;

public class WordManager {
    static Scanner s = new Scanner(System.in);
    private final WordCRUD wordCRUD;

    public WordManager(){
        wordCRUD = new WordCRUD(s);
    }

    public void start(){
        System.out.println(wordCRUD.loadWord()+"개의 단어를 불러왔습니다!");
        loopmenu: while (true){
            int m = selectMenu();
            switch (m){
                case 0:
                    break loopmenu;
                case 1:
                    wordCRUD.listAll();
                    break;
                case 2:
                    wordCRUD.listFilterByLvl();
                    break;
                case 3:
                    wordCRUD.searchWord();
                    break;
                case 4:
                    wordCRUD.addWord();
                    break;
                case 5:
                    wordCRUD.updateWord();
                    break;
                case 6:
                    wordCRUD.deleteWord();
                    break;
                case 7:
                    wordCRUD.saveWord();
            }
        }
        System.out.println("종료합니다.");
    }

    public int selectMenu(){
        System.out.print("""
                       영단어 마스터
                ************************
                    1. 모든 단어보기
                    2. 수준별 단어보기
                    3. 단어 검색
                    4. 단어 추가
                    5. 단어 수정
                    6. 단어 삭제
                    7. 파일 저장
                    0. 나가기
                ************************
                => 원하는 메뉴는?\s""");
        return wordCRUD.rightInput(
                "=>오류] 0에서 7까지의 값을 입력해주세요 : ",
                (val) -> val>=0&&val<=7,
                Integer::parseInt
        );
    }
}
