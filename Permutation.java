import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randQ = new RandomizedQueue<String>();
        randQ.enqueue(StdIn.readString());
        // print k strings uniformly at random
        for (int i = 1; i <= k; i++) {
            System.out.println(randQ.dequeue());
        }
    }
}
