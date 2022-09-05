package we.crud.word;

import java.util.Comparator;
import java.util.function.Predicate;

public interface ICRUD {
    public void addWord();
    public int update(Object obj);
    public int delete(Object obj);
    public void selectOne(int id);
    public int list(Predicate<Word> filter, Comparator<Word> comparator);
}
