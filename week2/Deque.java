import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int size = 0;

    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node node = new Node();
        node.item = item;

        if (head == null) {
            head = node;
            tail = node;
        } else {
            head.prev = node;
            node.next = head;
            head = node;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node node = new Node();
        node.item = item;

        if (tail == null) {
            tail = node;
            head = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item item = head.item;

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item item = tail.item;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private void print() {
        System.out.print("[");
        for (Item i : this) {
            System.out.print(i + ", ");
        }
        System.out.println("]");
        System.out.println("size: " + size());
        System.out.println(isEmpty());
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> que = new Deque<>();
        que.addFirst(1);
        que.print();
        que.removeFirst();
        que.print();

        que.addLast(2);
        que.print();
        que.removeLast();
        que.print();

        que.addFirst(3);
        que.print();
        que.removeLast();
        que.print();

        que.addLast(4);
        que.print();
        que.removeFirst();
        que.print();


        System.out.println("size: " + que.size());
        System.out.println(que.isEmpty());
        que.addFirst(2);
        que.addFirst(3);
        que.addFirst(4);
        que.addFirst(5);
        que.addLast(6);
        que.addLast(7);
        que.addLast(8);
        que.addLast(9);
        que.addLast(10);
        que.addFirst(0);
        que.print();
        System.out.println("size: " + que.size());

        System.out.println(que.removeFirst());
        System.out.println(que.removeFirst());
        System.out.println(que.removeFirst());
        que.print();
        System.out.println("size: " + que.size());

        System.out.println(que.removeLast());
        System.out.println(que.removeLast());
        System.out.println(que.removeLast());
        que.print();
        System.out.println("size: " + que.size());
    }

}
