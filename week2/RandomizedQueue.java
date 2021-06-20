import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] storage;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        storage = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private int length() {
        return storage.length;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        resizeIfNeed();
        storage[size] = item;
        size++;
//        resizeIfNeed();
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }

        int index = StdRandom.uniform(size);
        Item item = storage[index];
        storage[index] = storage[--size];
        storage[size] = null;
        resizeIfNeed();
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return storage[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final int[] indices = new int[size];
        private int currentIndex = 0;

        public RandomizedQueueIterator() {
            for (int i = 0; i < size; i++) {
                indices[i] = i;
            }
            StdRandom.shuffle(indices);
        }

        public boolean hasNext() {
            return currentIndex < indices.length;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return storage[indices[currentIndex++]];
        }
    }

    private void resizeIfNeed() {
        if (size == storage.length) {
            resize(storage.length * 2);
        } else if (size > 0 && size <= storage.length / 4) {
            resize(storage.length / 2);
        }
    }

    private void resize(int newSize) {
        Item[] newStorage = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newStorage[i] = storage[i];
        }
        storage = newStorage;
    }

    private void print() {
        System.out.print("[");
        for (Item i : this) {
            System.out.print(i + ", ");
        }
        System.out.println("]");

    }


    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueue<Integer> que = new RandomizedQueue<>();
        System.out.println("size: " + que.size());
        System.out.println("empty: " + que.isEmpty());
        System.out.print("length: " + que.length());
        que.print();

        que.enqueue(1);
        System.out.println("size: " + que.size());
        System.out.println("empty: " + que.isEmpty());
        que.print();

        que.enqueue(2);
        que.enqueue(3);
        que.enqueue(4);
        que.enqueue(5);
        que.enqueue(6);
        que.print();
        que.print();
        que.print();
        que.print();
        System.out.println(que.dequeue());
        System.out.println(que.dequeue());
        System.out.println(que.dequeue());
        System.out.println(que.dequeue());
        System.out.println("size: " + que.size());
        System.out.println("empty: " + que.isEmpty());
        que.print();
        System.out.println(que.sample());
        System.out.println(que.sample());
        System.out.println(que.sample());
        System.out.println(que.sample());
        System.out.println(que.dequeue());
        System.out.println(que.dequeue());
        System.out.println("size: " + que.size());
        System.out.println("empty: " + que.isEmpty());
    }

}
