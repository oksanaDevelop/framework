import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by luckyPC on 10/5/2017.
 */
public class Main {

    //static String s = getClass().getEnclosingClass().getName();

    public static void main(String[] args) {

        List<String> list = new ArrayList<>(Arrays.asList("123", "345345", "8"));

        System.out.println(list);


        List<String> list2 = list.stream().filter(l -> l.length()!=1).collect(Collectors.toList());
        System.out.println(list2);
    }
}
