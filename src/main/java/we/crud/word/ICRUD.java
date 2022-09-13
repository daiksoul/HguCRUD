package we.crud.word;

import java.util.Comparator;
import java.util.function.Predicate;

public interface ICRUD {
    public void addWord();
    public void deleteWord();
    public void updateWord();
    public int list(Predicate<Word> filter, Comparator<Word> comparator);
}
