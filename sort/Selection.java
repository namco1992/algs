import edu.princeton.cs.algs4.In;

/**
 * Created by namco on 31/1/17.
 */
public class Selection extends SortTemplate {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i+1; j < N; j++) {
                if(less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
    }
    public static void main(String[] args) {
//        File file = new File("words.txt");
//        System.out.println(file.exists());
        String[] a = In.readStrings("words.txt");
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
