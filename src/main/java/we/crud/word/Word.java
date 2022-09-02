package we.crud.word;

public class Word {
    private int id;
    private int lvl;
    private String word;
    private String meaning;

    public Word() {
    }

    public Word(int id, int lvl, String word, String meaning) {
        this.id = id;
        this.lvl = lvl;
        this.word = word;
        this.meaning = meaning;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString(){
        return String.format("%-3s","*".repeat(lvl))+String.format("%15s",word)+" "+meaning;
    }
}
