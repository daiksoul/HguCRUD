package we.crud.word;

import java.util.Comparator;
import java.util.function.Function;

public interface ICRUD {
    public void addWord();
    public int update(Object obj);
    public int delete(Object obj);
    public void selectOne(int id);
    public int list(Function<Word,Boolean> filter, Comparator<Word> comparator);
}
