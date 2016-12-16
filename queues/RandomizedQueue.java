import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


/**
 * Created by namco on 16/12/12.
 */

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int N = 0;

    public boolean isEmpty() {return N == 0;}
    public int size() {return N;}

    public RandomizedQueue() {
        a = (Item[]) new Object[1];
        N = 0;
    }
    private  void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    private void swapWithTheEnd(int position) {
        if (position == N-1) return;
        Item temp;
        temp = a[N-1];
        a[N-1] = a[position];
        a[position] = temp;
    }

    private boolean checkItem(Item item) {
        return item != null;
    }

    private Item pop() {
        Item item = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }
    public void enqueue(Item item) {
        if (!checkItem(item)) throw new NullPointerException("Item is null.");
        if (N == a.length) resize(2 * a.length);
        a[N++] = item;
    }
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("The deque is empty.");
        int n = StdRandom.uniform(N);
        swapWithTheEnd(n);
        return pop();
    }
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("The deque is empty.");
        int n = StdRandom.uniform(N);
        swapWithTheEnd(n);
        return a[N-1];
    }


    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i;
        private Item[] copyOfArray;
        public ArrayIterator() {
            i = N;
            copyOfArray = (Item[]) new Object[N];
            for (int j = 0; j < N; j++) {
                copyOfArray[j] = a[j];
            }
            StdRandom.shuffle(copyOfArray);
        }
        public boolean hasNext() {return i > 0;}
        public Item next() {
            if (!hasNext()) {throw new NoSuchElementException();}
            return copyOfArray[--i];
        }
        public void remove() {throw new UnsupportedOperationException("remove is not supported.");}
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.isEmpty();
        rq.enqueue(42);
        rq.enqueue(12);
//        StdOut.println(rq.sample());
        rq.enqueue(34);
        rq.enqueue(44);
        StdOut.println(rq.dequeue());
//        StdOut.println(rq.size());
//        StdOut.println(rq.sample());
//        StdOut.println(rq.sample());
        for (Integer x: rq) StdOut.println(x);

    }
}

/*
* shouldn't use a linked list, we still need the resizing array
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
*/
