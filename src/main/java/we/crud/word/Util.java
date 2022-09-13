package we.crud.word;

import java.util.function.Function;
import java.util.function.Predicate;

public class Util {
    public static <T> T rightInput(String errMsg, Predicate<T> validator, Function<String,T> parser){
        String str;
        T val;
        while(true){
            str = Main.scanner.nextLine().trim();
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
}
