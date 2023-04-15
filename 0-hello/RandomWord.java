/* *****************************************************************************
 *  Name:              Kevin Chen
 *  Coursera User ID:  123456
 *  Last modified:     March 11, 2023
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        int i = 0;
        while (!StdIn.isEmpty()) {
            String candidate = StdIn.readString();
            i++;
            double prob = 1.0 / i;
            boolean isChamp = StdRandom.bernoulli(prob);
            if (isChamp) {
                champion = candidate;
            }
        }
        StdOut.println(champion);
    }
}
