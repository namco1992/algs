import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by namco on 16/12/12.
 */

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;


    private class Node {
        Item item;
        Node next;
        Node previous;
    }
    public Deque() {
        first = last = null;
        N = 0;
    }
    public boolean isEmpty() {
        return N == 0;
    }
    public int size() {
        return N;
    }
    private boolean checkItem(Item item) {
        return item != null;
    }
    public void addFirst(Item item) {
        if (!checkItem(item)) throw new NullPointerException("Item is null.");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        if (isEmpty()) last = first;
        else oldFirst.previous = first;
        N++;
    }
    public void addLast(Item item) {
        if (!checkItem(item)) throw new NullPointerException("Item is null.");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldLast;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        N++;
    }
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("The deque is empty.");
        Item oldFirstItem = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;
        else first.previous = null;
        return oldFirstItem;
    }
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("The deque is empty.");
        Item oldLastItem = last.item;
        last = last.previous;
        N--;
        if (isEmpty()) first = null;
        else last.next = null;
        return oldLastItem;
    }
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {throw new UnsupportedOperationException("remove is not supported.");}
        public Item next() {
            if (!hasNext()) {throw new NoSuchElementException();}
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args) {
        Deque<String> q = new Deque<>();
        StdOut.println(q.size());
        StdOut.println(q.isEmpty());
        q.addFirst("A");
        StdOut.println(q.size());
        StdOut.println(q.isEmpty());
        if (q.size() != 1) throw new AssertionError();
        StdOut.println(q.first.item);
        q.addFirst("B");
        StdOut.println(q.first.item);
        StdOut.println(q.first.next.item);
        q.addLast("C");
        for (String x: q) StdOut.println(x);
        q.removeFirst();
        StdOut.println(q.size());
        StdOut.println(q.isEmpty());
        for (String x: q) StdOut.println(x);
        q.removeLast();
        StdOut.println(q.size());
        StdOut.println(q.isEmpty());
        for (String x: q) StdOut.println(x);
    }
}