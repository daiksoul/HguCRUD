package we.crud.word;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class Word {
    private int id;
    private int lvl;
    private String word;
    private String meaning;

    public Word(int id, int lvl, String word, String meaning) {
        this.id = id;
        this.lvl = lvl;
        this.word = word;
        this.meaning = meaning;
    }

    @Override
    public String toString(){
        return String.format("%-3s","*".repeat(lvl))+String.format("%15s",word)+" "+meaning;
    }
    public String toFileString(){
        return lvl+"|"+word+"|"+meaning;
    }
}
