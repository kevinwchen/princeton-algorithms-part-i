/* *****************************************************************************
 *  Name: Kevin Chen
 *  Date: 21/03/2023
 *  Description: Similar to a queue but removal is uniformly random
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int numNodes;
    private Item[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
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
        // double array size when full
        if (numNodes == queue.length) resize(2 * queue.length);
        queue[numNodes++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        // half array size when quarter full
        if (numNodes > 0 && numNodes < queue.length / 4) resize(queue.length / 2);

        // swap last item in array with the chosen item then remove it
        int randomIndex = StdRandom.uniformInt(0, numNodes);
        Item removedItem = queue[randomIndex];
        Item oldLast = queue[numNodes - 1];
        queue[randomIndex] = oldLast;
        queue[numNodes - 1] = null;
        numNodes--;
        return removedItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int randomIndex = StdRandom.uniformInt(0, numNodes);
        return queue[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    // resize the array to a given capacity
    private void resize(int capacity) {
        Item[] newQ = (Item[]) new Object[capacity];
        for (int i = 0; i < numNodes; i++) {
            newQ[i] = queue[i];
        }
        queue = newQ;
    }

    private class RandomIterator implements Iterator<Item> {
        private int i = numNodes;

        private Item[] itQueue = null;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            if (i == 0) throw new NoSuchElementException();
            if (i == numNodes) {
                itQueue = (Item[]) new Object[numNodes];
                for (int j = 0; j < numNodes; j++) {
                    itQueue[j] = queue[j];
                }
                StdRandom.shuffle(itQueue);
            }
            return itQueue[--i];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // prints the deque from first to last
    private void showRandomQueue() {
        for (Item item : this) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    // unit testing (required)
    public static void main(String[] args) {
        System.out.println("Running unit tests...");

        System.out.println("Test 0");
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }

        RandomizedQueue<String> myQueue;

        System.out.println("Test 1");
        myQueue = new RandomizedQueue<String>();
        myQueue.enqueue("first");
        myQueue.enqueue("second");
        myQueue.enqueue("third");
        myQueue.showRandomQueue();
        myQueue.showRandomQueue();
        System.out.println(myQueue.sample());

        System.out.println("Test 2");
        myQueue = new RandomizedQueue<String>();
        myQueue.enqueue("first");
        myQueue.enqueue("second");
        myQueue.enqueue("third");
        myQueue.dequeue();
        myQueue.showRandomQueue();
        System.out.println(myQueue.sample());


        RandomizedQueue<Integer> myIntQueue;

        System.out.println("Test 3");
        myIntQueue = new RandomizedQueue<Integer>();
        myIntQueue.enqueue(1);
        myIntQueue.enqueue(2);
        myIntQueue.enqueue(3);
        myIntQueue.dequeue();
        myIntQueue.showRandomQueue();
        System.out.println(myIntQueue.sample());

        System.out.println("Test 4");
        myIntQueue = new RandomizedQueue<Integer>();
        myIntQueue.enqueue(1);
        myIntQueue.enqueue(2);
        myIntQueue.enqueue(3);
        myIntQueue.dequeue();
        myIntQueue.enqueue(4);
        myIntQueue.showRandomQueue();
        System.out.println(myIntQueue.sample());

        System.out.println("Test 5");
        myIntQueue = new RandomizedQueue<Integer>();
        myIntQueue.enqueue(1);
        myIntQueue.enqueue(2);
        myIntQueue.enqueue(3);
        myIntQueue.dequeue();
        myIntQueue.enqueue(4);
        myIntQueue.enqueue(5);
        myIntQueue.enqueue(6);
        myIntQueue.dequeue();
        myIntQueue.showRandomQueue();
        System.out.println(myIntQueue.sample());
    }

}
