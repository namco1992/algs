import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

/**
 * Created by namco on 31/1/17.
 */
public class Insertion extends SortTemplate {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

    public static void main(String[] args) {
//        File file = new File("words.txt");
//        System.out.println(file.exists());
//        String[] a = In.readStrings("words.txt");
        Double[] a = Arrays.stream(In.readDoubles("words.txt")).boxed().toArray(Double[]::new);
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
