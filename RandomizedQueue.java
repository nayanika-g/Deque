import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int n = 0;

    public RandomizedQueue() {
        q = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (n == q.length) {
            resize(2 * q.length);
        }
        q[n++] = item;
    }

    public Item dequeue() {
        if (n == 0) {
            throw new java.util.NoSuchElementException();
        }
        int index = StdRandom.uniform(n);   //random index between 0 and n-1 inclusive
        Item item = q[index];
        if (index < n - 1) {
            for (int i = index; i <= n - 2; i++) {
                q[i] = q[i + 1];
            }
        }
        q[n - 1] = null;
        n--;
        if ((n >= 4) && (n == q.length / 4)) {
            resize(q.length / 2);
        }
        return item;
    }

    public Item sample() {
        if (n == 0) {
            throw new java.util.NoSuchElementException();
        }
        return q[StdRandom.uniform(n)];
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = n;

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (i == 0) {
                throw new java.util.NoSuchElementException();
            }
            return q[--i];
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        if (capacity < q.length) {
            for (int i = 0; i < copy.length; i++) {
                copy[i] = q[i];
            }
        } else {
            for (int i = 0; i < q.length; i++) {
                copy[i] = q[i];
            }
        }
        // assign q to copy
        q = copy;
    }

    //COMMENT THIS OUT
    /*public int getArraySize() {
        return q.length;
    }*/

/*    public static void main(String[] args) {

        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        rq.enqueue(3);
        System.out.println("Item removed = " + rq.dequeue());   //49
        assert (rq.size() == 0);
        rq.enqueue(2);

        for (Integer i : rq) {
            System.out.println(i);
        }
        System.out.println("All tests passed!");

    }*/

}
