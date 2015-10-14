/****************************************************************************
 *  Compilation:  javac Subset.java
 *  Execution:  echo A B C D E F G H I | java Subset 3 
 *  Dependencies: StdIn.java, RandomizedQueue.java
 *
 *  Subset.
 *
 ****************************************************************************/
/**
 * Class Name: Subset.java Description: *The <tt>Subset</tt> .java program takes
 * a command-line integer k; reads in a sequence of N strings from standard
 * input using StdIn.readString(); and prints out exactly k of them, uniformly
 * at random. Each item from the sequence is printed out at most once.
 * <p>
 * This implementation uses RandomizedQueue.class. The running time of Subset
 * must be linear in the size of the input.
 * 
 * @author Amulya Manchikanti
 * @date : 07/03/2015
 */
public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        String input = StdIn.readLine();
        for (String str : input.split("[\\s]+")) {
            rq.enqueue(str);
        }

        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            System.out.println(rq.dequeue());
        }
    }

}
