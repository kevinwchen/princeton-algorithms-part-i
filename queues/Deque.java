/* *****************************************************************************
 *  Name: Kevin Chen
 *  Date: 20/03/2023
 *  Description: Generic deque data type
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int numNodes;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        numNodes = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return numNodes == 0;
    }

    // return the number of items on the deque
    public int size() {
        return numNodes;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) last = first;
        else {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        numNodes++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) first = last;
        else {
            oldLast.next = last;
            last.prev = oldLast;
        }
        numNodes++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item removedItem = first.item;
        first = first.next;
        if (size() == 1) last = first;
        else first.prev = null;
        numNodes--;
        return removedItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item removedItem = last.item;
        last = last.prev;
        if (size() == 1) first = last;
        else last.next = null;
        numNodes--;
        return removedItem;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    // prints the deque from first to last
    private void showDeque() {
        for (Object obj : this) {
            System.out.println(obj);
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        System.out.println("Running unit tests...");
        Deque<String> myQueue = new Deque<String>();

        System.out.println("Test 1");
        myQueue = new Deque<String>();
        myQueue.addFirst("first");
        myQueue.removeFirst();
        myQueue.showDeque();
        System.out.println(myQueue.size());

        System.out.println("Test 2");
        myQueue = new Deque<String>();
        myQueue.addFirst("first");
        myQueue.removeLast();
        myQueue.showDeque();
        System.out.println(myQueue.size());

        System.out.println("Test 3");
        myQueue = new Deque<String>();
        myQueue.addLast("first");
        myQueue.removeFirst();
        myQueue.showDeque();
        System.out.println(myQueue.size());

        System.out.println("Test 4");
        myQueue = new Deque<String>();
        myQueue.addLast("first");
        myQueue.removeLast();
        myQueue.showDeque();
        System.out.println(myQueue.size());

        System.out.println("Test 5");
        myQueue = new Deque<String>();
        myQueue.addFirst("first");
        myQueue.addFirst("second");
        myQueue.addFirst("third");
        myQueue.removeFirst();
        myQueue.showDeque();
        System.out.println(myQueue.size());

        System.out.println("Test 6");
        myQueue = new Deque<String>();
        myQueue.addFirst("first");
        myQueue.addFirst("second");
        myQueue.addFirst("third");
        myQueue.removeLast();
        myQueue.showDeque();
        System.out.println(myQueue.size());

        System.out.println("Test 7");
        myQueue = new Deque<String>();
        myQueue.addLast("first");
        myQueue.addLast("second");
        myQueue.addLast("third");
        myQueue.removeFirst();
        myQueue.showDeque();
        System.out.println(myQueue.size());

        System.out.println("Test 8");
        myQueue = new Deque<String>();
        myQueue.addLast("first");
        myQueue.addLast("second");
        myQueue.addLast("third");
        myQueue.removeLast();
        myQueue.showDeque();
        System.out.println(myQueue.size());

        System.out.println("Test 9");
        myQueue = new Deque<String>();
        myQueue.addLast("first");
        myQueue.addFirst("second");
        myQueue.addLast("third");
        myQueue.removeFirst();
        myQueue.removeLast();
        myQueue.showDeque();
        System.out.println(myQueue.size());

        System.out.println("Test 10");
        myQueue = new Deque<String>();
        myQueue.addFirst("first");
        myQueue.addLast("second");
        myQueue.addFirst("third");
        myQueue.removeFirst();
        myQueue.removeLast();
        myQueue.addFirst("fourth");
        myQueue.removeLast();
        myQueue.showDeque();
        System.out.println(myQueue.size());
    }

}
