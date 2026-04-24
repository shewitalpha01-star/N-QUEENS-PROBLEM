package nqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * N-Queens Solver
 * Course: Design and Analysis of Algorithms
 * University: Aksum University, AIT
 * Faculty: Computing Technology
 * Department: Computer Science
 */
public class NQueensSolver {

    /**
     * Holds all output data for one board size.
     */
    public static class SolveResult {
        public int n;
        public int solutionCount;
        public List<int[]> solutions;
        public long elapsedMs;
        public long recursiveCalls;

        public SolveResult(int n) {
            this.n = n;
            this.solutionCount = 0;
            this.solutions = new ArrayList<>();
            this.elapsedMs = 0;
            this.recursiveCalls = 0;
        }
    }

    /**
     * Bounding function: checks whether placing a queen at board[row] conflicts
     * with any previously placed queen in rows 0 to row-1.
     *
     * @param board the current board state (board[i] = column of queen in row i)
     * @param row   the row index of the queen to validate
     * @return true if the placement is safe (no conflicts), false otherwise
     */
    public static boolean isSafe(int[] board, int row) {
        for (int i = 0; i < row; i++) {
            // Column conflict
            if (board[i] == board[row]) {
                return false;
            }
            // Diagonal conflict
            if (Math.abs(board[i] - board[row]) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Recursive backtracking engine. Places queens row by row, pruning branches
     * that violate constraints via isSafe.
     *
     * @param board  the current board state (board[i] = column of queen in row i)
     * @param row    the current row being filled
     * @param result accumulates solutions, solution count, and recursive call count
     */
    public static void backtrack(int[] board, int row, SolveResult result) {
        result.recursiveCalls++;

        // Base case: all rows filled — record the solution
        if (row == result.n) {
            result.solutions.add(Arrays.copyOf(board, board.length));
            result.solutionCount++;
            return;
        }

        // Recursive case: try each column in the current row
        for (int col = 0; col < result.n; col++) {
            board[row] = col;
            if (!isSafe(board, row)) {
                continue;
            }
            backtrack(board, row + 1, result);
        }
    }

    /**
     * Initialises the board and counters, runs the backtracking search, and
     * returns a fully populated SolveResult.
     *
     * @param n the board dimension (number of queens)
     * @return a SolveResult containing all solutions, counts, and elapsed time
     */
    public static SolveResult solve(int n) {
        if (n <= 0) {
            System.out.println("Error: n must be a positive integer. Got: " + n);
            return new SolveResult(0);
        }

        int[] board = new int[n];
        SolveResult result = new SolveResult(n);

        long startMs = System.currentTimeMillis();
        backtrack(board, 0, result);
        long endMs = System.currentTimeMillis();

        result.elapsedMs = endMs - startMs;
        return result;
    }

    /**
     * Prints the program identification header.
     * Requirements: 7.1
     */
    public static void printHeader() {
        System.out.println("=".repeat(60));
        System.out.println("N-Queens Solver");
        System.out.println("Course: Design and Analysis of Algorithms");
        System.out.println("University: Aksum University, AIT");
        System.out.println("Faculty: Computing Technology");
        System.out.println("Department: Computer Science");
        System.out.println("=".repeat(60));
    }

    /**
     * Prints an N×N board with 'Q' for queen positions and '.' for empty cells.
     * Requirements: 5.3, 5.5
     *
     * @param board the solution board (board[i] = column of queen in row i)
     * @param n     the board dimension
     */
    public static void printBoard(int[] board, int n) {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row] == col) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Prints a formatted summary table showing results for all board sizes.
     * Requirements: 7.4
     *
     * @param results array of SolveResult objects to display
     */
    public static void printSummaryTable(SolveResult[] results) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SUMMARY TABLE");
        System.out.println("=".repeat(60));
        System.out.printf("%-5s | %-12s | %-12s | %-15s%n", 
                          "N", "Solutions", "Time (ms)", "Recursive Calls");
        System.out.println("-".repeat(60));
        
        for (SolveResult result : results) {
            System.out.printf("%-5d | %-12d | %-12d | %-15d%n",
                              result.n,
                              result.solutionCount,
                              result.elapsedMs,
                              result.recursiveCalls);
        }
        System.out.println("=".repeat(60));
    }

    public static void main(String[] args) {
        printHeader();

        int[] boardSizes = {8, 10, 12};
        SolveResult[] results = new SolveResult[boardSizes.length];

        for (int i = 0; i < boardSizes.length; i++) {
            int n = boardSizes[i];

            System.out.println("\n" + "=".repeat(60));
            System.out.println("Solving N = " + n + "...");

            results[i] = solve(n);
            SolveResult result = results[i];

            System.out.println("Total solutions found: " + result.solutionCount);

            int displayCount = Math.min(3, result.solutionCount);
            for (int s = 0; s < displayCount; s++) {
                System.out.println("\nSolution #" + (s + 1) + ":");
                printBoard(result.solutions.get(s), n);
            }

            System.out.println("\nExecution time: " + result.elapsedMs + " ms");
        }

        printSummaryTable(results);
    }
}
