/* *****************************************************************************
 *  Name: Kevin Chen
 *  Date: 21/03/2023
 *  Description: that takes an integer k as a command-line argument; reads a
 *  sequence of strings from standard input using StdIn.readString(); and prints
 *  exactly k of them, uniformly at random.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> myQueue;
        myQueue = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            myQueue.enqueue(s);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(myQueue.dequeue());
        }
    }
}
