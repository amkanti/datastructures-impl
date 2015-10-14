/****************************************************************************
 *  Compilation:  javac Solver.java
 *  Execution:  java Solver T
 *  Dependencies: StdIn.java StdOut.java MinPQ.java
 *
 *  Solver.
 *
 ****************************************************************************/
/**
 * Class Name: Solver.java 
 * Description: The <tt>Solver</tt> .java program finds solution to the
 * board using A* search algorithm. It supports isSolvable(), moves(), 
 * solution() operations. It computes manhattan distance using manhattan()
 * and uses minimum priority queue to remove the node with the least
 * priority. 
 * <p>
 * The algorithm is applied on two puzzle instances simultaneously; one
 * with the initial board and other with its twin to compute the solvability
 * of the board.
 * 
 * @author Amulya Manchikanti
 * @date : 07/17/2015
 */

import java.util.Comparator;

public class Solver {
    private boolean solvable, tsolvable; // to determine if the board is
                                            // solvable or not
    private MinPQ<Node> queue; // to store the elements in the priority queue
                                // for the given input board
    private MinPQ<Node> tqueue; // to store the elements in PQ for the twin
                                // board
    private int moves = 0; // to store number of moves
    // private int tmoves = 0;
    private Node endnode; // to mark the endnode

    private class Node {
        Board board;
        Node prev;

        Node(Board board, Node prev) {
            this.board = board;
            this.prev = prev;
        }

    }

    /**
     * find a solution to the initial board (using the A* algorithm)
     * 
     * @param initial
     *            represents the board constructed
     */
    public Solver(Board initial) // find a solution to the initial board (using
                                    // the A* algorithm)
    {
        if (initial == null)
            throw new NullPointerException("null argument received");

        solvable = false;
        tsolvable = false;

        queue = new MinPQ<Node>(myComp);
        tqueue = new MinPQ<Node>(myComp);

        Board tboard = initial.twin();
        Node tinit = new Node(tboard, null);
        tqueue.insert(tinit);

        Node init = new Node(initial, null);
        queue.insert(init);

        while (!solvable && !tsolvable) {
            solvable = solve(queue);
            if (solvable == false)
                moves++;
            tsolvable = solve(tqueue);
        }

    }

    /**
     * solves the board with PQ input
     * 
     * @param q
     *            represents priority queue
     * @return true if goal has reached for either given input or twin board and
     *         false if it hasn't.
     */
    private boolean solve(MinPQ<Node> q) {
        Node current = q.delMin();

        if (current.board.isGoal() == true) {
            endnode = current;
            return true;
        }

        for (Board b : current.board.neighbors()) {
            // createnode(b, current)
            Node qneigh = new Node(b, current);

           
            if (qneigh != current.prev)
                q.insert(qneigh);
        }
        // moves++;
        // tmoves++;

        return false;
    }

    /**
     * comparator to find the node with minimum manhattan distance
     */
    private static Comparator<Node> myComp = new Comparator<Node>() {
        public int compare(Node n1, Node n2) {
            return (n1.board.manhattan() - n2.board.manhattan());
        }
    };

    /**
     * to figure out if the initial board is solvable or not
     * 
     * @return true if solvable and false if not
     */
    public boolean isSolvable() // is the initial board solvable?
    {
        if (solvable == true)
            return true;
        else
            return false;
    }

    /**
     * min number of moves to solve initial board
     * 
     * @return -1 if unsolvable and number of moves taken to reach the goal if
     *         solvable
     */
    public int moves() // min number of moves to solve initial board; -1 if
                        // unsolvable
    {
        if (tsolvable == true)
            return -1;
        else
            return moves;
    }

    /**
     * sequence of boards in a shortest solution
     * 
     * @return null if unsolvable and set of boards if solvable
     */
    public Iterable<Board> solution() // sequence of boards in a shortest
                                        // solution; null if unsolvable
    {

        if (tsolvable == true)
            return null;
        Node copyEndnode;
        copyEndnode = endnode;
        Stack<Board> sboards = new Stack<Board>();
        while (copyEndnode != null) {
            sboards.push(copyEndnode.board);
            copyEndnode = copyEndnode.prev;
        }

        return sboards;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
