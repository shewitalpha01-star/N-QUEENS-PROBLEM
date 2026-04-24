# N-Queens Problem Solver
## Backtracking with Pruning — Chess Board Arrangement

### Course Information
- **Course:** Design and Analysis of Algorithms
- **University:** Aksum University, AIT
- **Faculty:** Faculty of Computing Technology
- **Department:** Department of Computer Science
- **Language:** Java
- **Algorithm:** Backtracking with Bounding Function

### Group Members
| No | Name | ID |
|----|------|----|
| 1 | Weldearegay Gebrey | Aku-1602148 |
| 2 | Eyerusalem Teklay | Aku-1602682 |
| 3 | Shewit Legese | Aku-1602069 |

---

### Problem Overview
Place N queens on an N×N chessboard such that no two queens attack each other.
Queens attack along rows, columns, and diagonals.

### Features Implemented
- ✅ Backtracking algorithm with pruning
- ✅ 1D array representation (index = row, value = column)
- ✅ Bounding function — checks column and diagonal conflicts
- ✅ Solves for N = 8, 10, 12
- ✅ Counts all distinct solutions
- ✅ Displays first 3 solutions as visual Q/. grid
- ✅ Execution time and recursive call count per board size
- ✅ Summary table with complexity evidence

### How to Compile and Run

```bash
# Compile
javac src/nqueens/NQueensSolver.java -d out

# Run
java -cp out nqueens.NQueensSolver
```

---

### Known Solution Counts

| N | Distinct Solutions |
|---|--------------------|
| 8 | 92 |
| 10 | 724 |
| 12 | 14,200 |

### Complexity Analysis

| Operation | Time Complexity |
|-----------|----------------|
| Worst case (no pruning) | O(N!) |
| With backtracking pruning | Much less than O(N!) |
| isSafe() per call | O(N) |
| Space complexity | O(N) |

### Real-World Applications
- Constraint satisfaction problems
- Antenna placement without interference
- Scheduling without conflicts
- Resource placement optimization
- CPU register allocation

---

### 📊 Screenshots

**Board Visualization (N=8, Solution #1)**

![N=8 Solution](screenshots/n8_solution.svg)

**Solution Count Chart**

![Solution Count](screenshots/solution_counts.svg)

> 📄 See `docs/` for full requirements, design, and implementation plan.
