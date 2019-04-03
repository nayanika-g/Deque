import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    public Item removeFirst() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        size--;
        return item;
    }

    public Item removeLast() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        Item item = last.item;
        Node current = first;
        if (size == 1) {
            last = null;
            first = null;
        } else {
            while (current.next != last) {
                current = current.next;
            }
            current.next = null;
            last = current;
        }
        size--;
        return item;
    }


    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return (current != null);
        }

        public Item next() {
            if (current.item == null) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {

        Deque<String> d = new Deque();
        String a = "hello";
        d.addLast(a);
        String b = "there";
        d.addLast(b);
        String c = "grace";
        d.addLast(c);
        for (String str : d) {
            System.out.println(str);
        }
        assert (d.size() == 3);
        System.out.println();

        System.out.println(d.removeLast());    //grace
        assert (d.size() == 2);
        System.out.println(d.removeLast());    //there
        assert (d.size() == 1);
        System.out.println(d.removeLast());    //hello
        assert (d.size == 0);
        System.out.println("All tests succeeded!");
    }

}
