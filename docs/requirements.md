# Requirements Document

## Introduction

The N-Queens Solver is a Java-based constraint satisfaction program that places N non-attacking queens
on an N×N chessboard. It uses a backtracking algorithm with pruning to efficiently find and display
all distinct solutions for board sizes N = 8, 10, and 12 (and higher where feasible). The solver
models real-world constraint satisfaction problems such as antenna placement without interference and
conflict-free scheduling. This project is developed for the Design and Analysis of Algorithms course
at Aksum University, AIT, Faculty of Computing Technology, Department of Computer Science.

## Glossary

- **Solver**: The main Java program that orchestrates the N-Queens solving process.
- **Board**: An N×N chessboard represented as a 1D integer array where the index is the row and the value is the column position of the queen in that row.
- **Queen**: A chess piece that attacks along its row, column, and both diagonals.
- **Conflict**: A state where two or more queens share the same column or diagonal, making them able to attack each other. Row conflicts are impossible by design of the 1D array representation.
- **Bounding_Function**: A function that checks whether placing a queen at a given row and column causes a conflict with any previously placed queen.
- **Solution**: A complete board configuration of N queens where no two queens attack each other.
- **Backtracking**: An algorithmic technique that incrementally builds candidates and abandons (prunes) a candidate as soon as it is determined to violate a constraint.
- **Pruning**: The act of cutting off a search branch early when the Bounding_Function detects a conflict, avoiding unnecessary recursive calls.
- **N**: The board dimension, representing both the number of rows/columns and the number of queens to place.

---

## Requirements

### Requirement 1: Board Representation

**User Story:** As a developer, I want the board state stored in a 1D array, so that row conflicts are structurally impossible and memory usage is minimized.

#### Acceptance Criteria

1. THE Solver SHALL represent the board as a 1D integer array of size N, where the array index represents the row and the stored value represents the column of the queen placed in that row.
2. THE Solver SHALL guarantee that no two queens share the same row by construction, since each index (row) holds exactly one value (column).

---

### Requirement 2: Conflict Detection (Bounding Function)

**User Story:** As a developer, I want a bounding function that detects all attack conflicts, so that invalid placements are pruned before recursion continues.

#### Acceptance Criteria

1. THE Bounding_Function SHALL accept the current row index and the board array as input parameters.
2. WHEN the Bounding_Function is called for row `r`, THE Bounding_Function SHALL check all previously placed queens in rows 0 through r-1 for column conflicts (board[i] == board[r]).
3. WHEN the Bounding_Function is called for row `r`, THE Bounding_Function SHALL check all previously placed queens in rows 0 through r-1 for diagonal conflicts (|board[i] - board[r]| == |i - r|).
4. IF a column or diagonal conflict is detected, THEN THE Bounding_Function SHALL return false immediately without checking remaining rows.
5. WHEN no conflict is detected after checking all prior rows, THE Bounding_Function SHALL return true.

---

### Requirement 3: Backtracking Algorithm

**User Story:** As a developer, I want a backtracking algorithm that uses the bounding function to prune invalid branches, so that the search space is reduced from the O(N^N) brute-force to near O(N!) with significant pruning.

#### Acceptance Criteria

1. THE Solver SHALL implement a recursive backtracking function that places queens one row at a time, starting from row 0.
2. WHEN placing a queen in a given row, THE Solver SHALL iterate over all N columns (0 to N-1) as candidate positions.
3. WHEN a candidate column is evaluated, THE Solver SHALL invoke the Bounding_Function before placing the queen and recursing deeper.
4. IF the Bounding_Function returns false for a candidate column, THEN THE Solver SHALL skip that column and try the next candidate (pruning).
5. WHEN the Bounding_Function returns true for a candidate column, THE Solver SHALL place the queen (assign the column to the board array) and recurse to the next row.
6. WHEN all N rows have been successfully filled, THE Solver SHALL record the current board configuration as a valid Solution.
7. WHEN backtracking occurs, THE Solver SHALL restore the board state to its value before the failed placement.

---

### Requirement 4: Solving for Required Board Sizes

**User Story:** As a student, I want the solver to run for N = 8, 10, and 12, so that I can demonstrate the algorithm's scalability and compare solution counts across board sizes.

#### Acceptance Criteria

1. THE Solver SHALL accept N as a configurable input parameter rather than a hard-coded constant.
2. THE Solver SHALL solve the N-Queens problem for N = 8, N = 10, and N = 12 in a single program execution.
3. WHEN N is greater than 12, THE Solver SHALL attempt to solve the problem and report results if computation completes within a reasonable time.
4. WHEN solving begins for a given N, THE Solver SHALL display the board size N before outputting results.

---

### Requirement 5: Solution Display

**User Story:** As a student, I want all distinct solutions counted and a subset displayed, so that I can verify correctness and present results clearly in the assignment report.

#### Acceptance Criteria

1. THE Solver SHALL count all distinct Solutions found for a given N.
2. WHEN solving is complete for a given N, THE Solver SHALL display the total number of Solutions found.
3. THE Solver SHALL display the first 3 Solutions in a human-readable board format (grid with 'Q' for queen and '.' for empty cell) for each value of N.
4. WHEN N produces fewer than 3 Solutions, THE Solver SHALL display all available Solutions.
5. THE Solver SHALL display each Solution's board on separate lines with a solution index label (e.g., "Solution #1:").

---

### Requirement 6: Complexity Analysis Output

**User Story:** As a student, I want the program to report execution time per board size, so that I can support the complexity analysis section of the assignment report.

#### Acceptance Criteria

1. THE Solver SHALL record the start time immediately before beginning the backtracking search for each N.
2. THE Solver SHALL record the end time immediately after the backtracking search completes for each N.
3. WHEN solving is complete for a given N, THE Solver SHALL display the elapsed execution time in milliseconds.
4. THE Solver SHALL display the total number of recursive calls made during the search for each N, to illustrate the effect of pruning.

---

### Requirement 7: Program Output Format

**User Story:** As a student, I want a clean, structured console output, so that results are easy to read and include in the assignment report.

#### Acceptance Criteria

1. THE Solver SHALL print a header section identifying the program name, course, university, and department before any results.
2. WHEN results for a given N are printed, THE Solver SHALL separate each N's output block with a visible divider line.
3. THE Solver SHALL print results for N = 8, N = 10, and N = 12 in ascending order of N.
4. WHEN all board sizes have been solved, THE Solver SHALL print a summary table showing N, total solutions found, and execution time in milliseconds for each board size.
