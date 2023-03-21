/* *****************************************************************************
 *  Name: Kevin Chen
 *  Date: 21/03/2023
 *  Description: Similar to a queue but removal is uniformly random
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first, last;
    private int numNodes;
    private Item[] q;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        first = null;
        last = null;
        numNodes = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return numNodes == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return numNodes;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        return null;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return null;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        StdRandom.uniform();
    }

}
