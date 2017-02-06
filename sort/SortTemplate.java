import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by namco on 31/1/17.
 */
public class SortTemplate {
    public static void sort(Comparable[] a) throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }
    public static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    public static void main(String[] args) throws NoSuchMethodException {
//        File file = new File("words.txt");
//        System.out.println(file.exists());
        String[] a = In.readStrings("words.txt");
        sort(a);
        assert isSorted(a);
        show(a);
    }

}
