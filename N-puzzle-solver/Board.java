/****************************************************************************
 *  Compilation:  javac Board.java
 *  Execution:  java Board
 *  Dependencies: StdIn.java StdOut.java 
 *
 *  Board.
 *
 ****************************************************************************/
/**
 * Class Name: Board.java Description: The <tt>Board</tt> .java program
 * constructs a board from an N-by-N array of blocks. It supports dimension(),
 * hamming(), manhattan(), isGoal(), twin() and neighbors() operations. It
 * determines the number of blocks out of place through hamming() and sum of
 * distances between block and goal through manhattan().
 * <p>
 * The methods run in time proportional to N^2 in the worst case. the
 * constructor receives an N-by-N array containing the N2 integers between 0 and
 * N^2 - 1, where 0 represents the blank square.
 * 
 * @author Amulya Manchikanti
 * @date : 07/17/2015
 */

public class Board {

    private int length; // local copy of the size
    private int[][] blocks; // local copy of the board

    /**
     * constructs a board from an N-by-N array of blocks
     * 
     * @param blocks
     *            indicates the input read from file
     */
    public Board(int[][] blocks) // construct a board from an N-by-N array of
                                    // blocks
                                    // (where blocks[i][j] = block in row i,
                                    // column j)
    {
        length = blocks.length;
        this.blocks = blocks;
    }

    /**
     * formula to represent the goal board
     * 
     * @param x
     *            indicates the row index
     * @param y
     *            indicates the column index
     * @return the integer in the row, column index corresponding to goal board.
     */
    private int assignId(int x, int y) {
        return (x * length + y + 1);
    }

    /**
     * computes the dimension
     * 
     * @return the length of the board
     */
    public int dimension() // board dimension N
    {
        return length;
    }

    /**
     * computes the number of blocks out of place
     * 
     * @return the number of blocks out of place
     */
    public int hamming() // number of blocks out of place
    {
        int hamcount = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {

                if (blocks[i][j] == 0)
                    continue;
                if (blocks[i][j] != assignId(i, j))
                    hamcount++;
            }
        }
        return hamcount;
    }

    /**
     * computes the sum of distances between each element to its goal
     * 
     * @return the sum of Manhattan distances between blocks and goal
     */
    public int manhattan() // sum of Manhattan distances between blocks and goal
    {
        int mancount = 0;
        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++) {
                if (blocks[i][j] == 0)
                    continue;
                else {
                    int row = (blocks[i][j] - 1) / length;
                    int col = (blocks[i][j] - 1) % length;
                    mancount += Math.abs(i - row) + Math.abs(j - col);
                }
            }
        return mancount;
    }

    /**
     * finds out if the board matches the goal board
     * 
     * @return true if it matches else false
     */
    public boolean isGoal() // is this board the goal board?
    {
        if (hamming() == 0)
            return true;
        return false;
    }

    /**
     * exchanges 2 adjacent non-zero elements in the row
     * 
     * @return the copy of the modified board
     */
    public Board twin() // a board that is obtained by exchanging two adjacent
                        // blocks in the same row
    {
        int[][] twin = copyBlocks();

        t: for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1; j++) {
                if (twin[i][j] != 0 && twin[i][j + 1] != 0) {
                    int tmp = twin[i][j + 1];
                    twin[i][j + 1] = twin[i][j];
                    twin[i][j] = tmp;
                    break t;
                }
            }
        }
        return new Board(twin);
    }

    /**
     * returns true if the invoking board is equal to the argument board else
     * false
     */
    public boolean equals(Object y) // does this board equal y?
    {
        if (y == this)
            return true;
        if (y == null)
            return false;
        if (y.getClass() != this.getClass())
            return false;

        Board that = (Board) y;

        if (that.dimension() != this.dimension())
            return false;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (this.blocks[i][j] != that.blocks[i][j])
                    return false;
            }
        }
        return true;
    }

    /**
     * creates the copy of the board
     * 
     * @return the copied board
     */
    private int[][] copyBlocks() {
        int[][] copy = new int[length][length];
        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++)
                copy[i][j] = blocks[i][j];

        return copy;
    }

    /**
     * finds out all the neighbors of the search node it is invoked with
     * 
     * @return the set of neighboring boards
     */
    public Iterable<Board> neighbors() // all neighboring boards
    {
        Queue<Board> boards = new Queue<Board>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (blocks[i][j] == 0) {
                    if (i > 0) // top neighbor existence
                    {
                        int[][] neigh1 = copyBlocks();
                        neigh1[i][j] = neigh1[i - 1][j];
                        neigh1[i - 1][j] = 0;
                        boards.enqueue(new Board(neigh1));
                    }
                    if (j > 0) // left neighbor existence
                    {
                        int[][] neigh2 = copyBlocks();
                        neigh2[i][j] = neigh2[i][j - 1];
                        neigh2[i][j - 1] = 0;
                        boards.enqueue(new Board(neigh2));
                    }
                    if (i < length - 1) // down neighbor existence
                    {
                        int[][] neigh3 = copyBlocks();
                        neigh3[i][j] = neigh3[i + 1][j];
                        neigh3[i + 1][j] = 0;
                        boards.enqueue(new Board(neigh3));
                    }
                    if (j < length - 1) // right neighbor existence
                    {
                        int[][] neigh4 = copyBlocks();
                        neigh4[i][j] = neigh4[i][j + 1];
                        neigh4[i][j + 1] = 0;
                        boards.enqueue(new Board(neigh4));
                    }

                    return boards;
                }
            }

        }
        return null;
    }

    /**
     * represents the output in the specified format
     */
    public String toString() // string representation of this board (in the
                                // output format specified below)
    {
        StringBuilder s = new StringBuilder();
        s.append(length + "\n");
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) // unit tests (not graded)
    {

    }
}
