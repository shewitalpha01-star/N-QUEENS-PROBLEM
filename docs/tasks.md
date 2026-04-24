# Implementation Plan: N-Queens Solver

## Overview

This plan implements a Java console application that solves the N-Queens problem using backtracking with constraint-based pruning. The solver will run for N = 8, 10, and 12, displaying solutions in a human-readable format along with execution metrics (time and recursive call counts) to support complexity analysis.

The implementation follows a single-class architecture with clearly separated methods for solving, backtracking, conflict detection, and output formatting.

## Tasks

- [x] 1. Set up project structure and core data models
  - Create `NQueensSolver.java` main class file
  - Define `SolveResult` inner class with fields: `n`, `solutionCount`, `solutions` (List<int[]>), `elapsedMs`, `recursiveCalls`
  - Set up basic `main()` method skeleton
  - _Requirements: 1.1, 4.1_

- [x] 2. Implement conflict detection (bounding function)
  - [x] 2.1 Implement `isSafe(int[] board, int row)` method
    - Check all previously placed queens (rows 0 to row-1) for column conflicts
    - Check all previously placed queens for diagonal conflicts using `|board[i] - board[row]| == |i - row|`
    - Return false immediately on first conflict detected
    - Return true if no conflicts found
    - _Requirements: 2.1, 2.2, 2.3, 2.4, 2.5_

  - [ ]* 2.2 Write property test for conflict detection
    - **Property 1: isSafe rejects all conflicting placements**
    - **Validates: Requirements 2.2, 2.3**
    - Generate random partial boards with injected column conflicts
    - Verify `isSafe` returns false for all conflicting placements

  - [ ]* 2.3 Write property test for safe placement acceptance
    - **Property 2: isSafe accepts all conflict-free placements**
    - **Validates: Requirements 2.5**
    - Generate valid partial solutions by sampling intermediate solver states
    - Verify `isSafe` returns true for all conflict-free placements

  - [ ]* 2.4 Write unit tests for isSafe edge cases
    - Test column conflict detection (e.g., board = [0, 0], row 1)
    - Test diagonal conflict detection (e.g., board = [0, 1], row 1)
    - Test first queen placement (row 0, any column) returns true
    - _Requirements: 2.2, 2.3, 2.5_

- [x] 3. Implement backtracking algorithm
  - [x] 3.1 Implement `backtrack(int[] board, int row, SolveResult result)` method
    - Base case: if row == n, copy board to result.solutions and increment solutionCount
    - Recursive case: iterate columns 0 to n-1
    - For each column: call isSafe, place queen if safe, recurse to next row, restore board state
    - Increment recursiveCalls counter on each invocation
    - _Requirements: 3.1, 3.2, 3.3, 3.4, 3.5, 3.6, 3.7, 6.4_

  - [ ]* 3.2 Write property test for solution validity
    - **Property 3: Every recorded solution is non-attacking**
    - **Validates: Requirements 3.6**
    - Run solve for N ∈ {4, 5, 6, 7, 8}
    - For each solution, verify all queen pairs satisfy no column/diagonal conflicts
    - _Requirements: 3.6_

  - [ ]* 3.3 Write unit tests for small board sizes
    - Test solve(1) returns exactly 1 solution: [0]
    - Test solve(2) returns 0 solutions
    - Test solve(3) returns 0 solutions
    - _Requirements: 3.6, 4.2_

- [x] 4. Checkpoint - Ensure core algorithm tests pass
  - Ensure all tests pass, ask the user if questions arise.

- [x] 5. Implement solve orchestration method
  - [x] 5.1 Implement `solve(int n)` method
    - Initialize board array of size n
    - Initialize empty SolveResult with n
    - Record start timestamp using System.currentTimeMillis()
    - Call backtrack(board, 0, result)
    - Record end timestamp and calculate elapsedMs
    - Return SolveResult
    - _Requirements: 4.1, 6.1, 6.2, 6.3_

  - [ ]* 5.2 Write property test for known solution counts
    - **Property 4: Solution count matches known ground truth**
    - **Validates: Requirements 4.2, 5.1**
    - Test solve(8) returns exactly 92 solutions
    - Test solve(10) returns exactly 724 solutions
    - Test solve(12) returns exactly 14,200 solutions
    - _Requirements: 4.2, 5.1_

  - [ ]* 5.3 Write property test for recursive call counter
    - **Property 6: Recursive call count is positive for any valid N**
    - **Validates: Requirements 6.4**
    - Generate N ∈ {1..10}
    - Verify solve(n).recursiveCalls > 0 for all N
    - _Requirements: 6.4_

- [x] 6. Implement output and display methods
  - [x] 6.1 Implement `printHeader()` method
    - Print program name, course, university, and department
    - _Requirements: 7.1_

  - [x] 6.2 Implement `printBoard(int[] board, int n)` method
    - Print N×N grid with 'Q' for queen position and '.' for empty cells
    - Each row on a separate line
    - _Requirements: 5.3, 5.5_

  - [x] 6.3 Implement `printSummaryTable(SolveResult[] results)` method
    - Print formatted table with columns: N | Solutions | Time (ms) | Recursive Calls
    - Include all results passed to the method
    - _Requirements: 7.4_

  - [ ]* 6.4 Write property test for board rendering
    - **Property 5: Board rendering contains exactly N queens**
    - **Validates: Requirements 5.3**
    - Generate valid solutions for N ∈ {4..10}
    - Verify printBoard output contains exactly N 'Q' characters and N×(N-1) '.' characters
    - _Requirements: 5.3_

  - [ ]* 6.5 Write unit test for printBoard output format
    - Test printBoard for a known 4-Queens solution matches expected string format
    - _Requirements: 5.3, 5.5_

- [x] 7. Wire main method and complete program flow
  - [x] 7.1 Complete `main()` method implementation
    - Call printHeader()
    - Create array to store SolveResult for N = 8, 10, 12
    - For each N in [8, 10, 12]: call solve(N), store result
    - Print divider line between each N's output block
    - Display board size N before results
    - Display total solution count for each N
    - Display first 3 solutions (or all if fewer than 3) using printBoard
    - Display execution time in milliseconds for each N
    - Call printSummaryTable with all results
    - _Requirements: 4.2, 4.4, 5.1, 5.2, 5.3, 5.4, 5.5, 6.3, 7.1, 7.2, 7.3, 7.4_

  - [ ]* 7.2 Write integration test for main output
    - Capture stdout from main() execution
    - Verify header is printed
    - Verify divider lines appear between N blocks
    - Verify summary table contains known solution counts (92, 724, 14200)
    - Verify output order: N=8 before N=10 before N=12
    - _Requirements: 7.1, 7.2, 7.3, 7.4_

- [x] 8. Add error handling and edge cases
  - [x] 8.1 Add validation for invalid N values
    - Check for N ≤ 0 in solve() method
    - Print error message and return empty result for invalid N
    - Ensure main() skips invalid board sizes without crashing
    - _Requirements: 4.1_

  - [ ]* 8.2 Write unit tests for error handling
    - Test solve(0) returns 0 solutions without crashing
    - Test solve(-1) returns 0 solutions without crashing
    - _Requirements: 4.1_

- [ ] 9. Set up property-based testing framework
  - [ ] 9.1 Add jqwik dependency to project
    - Add jqwik library to build configuration (Maven/Gradle) or download JAR
    - Create `src/test/resources/jqwik.properties` configuration file
    - Set `database.mode=RESTARTABLE` and `tries.default=100`
    - _Requirements: All property tests_

  - [ ] 9.2 Create test class structure
    - Create `NQueensSolverTest.java` in test directory
    - Set up JUnit 5 and jqwik annotations
    - _Requirements: All property tests_

- [x] 10. Final checkpoint - Ensure all tests pass
  - Run all unit tests and property-based tests
  - Verify output for N = 8, 10, 12 matches expected format
  - Ensure all tests pass, ask the user if questions arise.

## Notes

- Tasks marked with `*` are optional and can be skipped for faster MVP
- Each task references specific requirements for traceability
- Property tests validate universal correctness properties from the design document
- Unit tests validate specific examples and edge cases
- The solver uses a 1D array representation where index = row, value = column
- Known solution counts: N=8 → 92, N=10 → 724, N=12 → 14,200
- jqwik is the property-based testing library for Java (integrates with JUnit 5)
