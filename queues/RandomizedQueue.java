import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


/**
 * Created by namco on 16/12/12.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;


    private class Node {
        Item item;
        Node next;
        Node previous;
    }
    public RandomizedQueue() {
        first = last =null;
        N = 0;
    }
    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldLast;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        N++;
    }
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("The deque is empty.");
        int n = StdRandom.uniform(N);
        Node current = first;
        if (n == 0) {
            first = first.next;
            N--;
            if (isEmpty()) last = first;
            else first.previous = null;
            return current.item;
        }
        while (n > 0) {
            current = current.next;
            n--;
        }
        current.previous.next = current.next;
        if (current.next != null) current.next.previous = current.previous;
        else last = first;
        N--;
        return current.item;

    }
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("The deque is empty.");
        int n = StdRandom.uniform(N);
        Node current = first;
        if (n == 0) return current.item;
        while (n > 0) {
            current = current.next;
            n--;
        }
        return current.item;
    }
    public boolean isEmpty() {
        return N == 0;
    }
    public int size() {
        return N;
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
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.isEmpty();
        rq.enqueue(42);
        rq.enqueue(12);
        rq.sample();
        rq.enqueue(34);
        rq.enqueue(44);
        rq.size();
        rq.sample();
        rq.sample();
        for (Integer x: rq) StdOut.println(x);




    }
}
