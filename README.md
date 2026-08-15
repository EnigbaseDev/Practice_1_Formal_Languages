# Practice_1_Formal_Languages
 Practice 1  Formal Languages
# Made by:
- Carlos Mario Monsalve Tangarife
- Daniel Felipe Serna Medina

## Environment
- Operating system: Windows 11
- Programming language: Java JDK 8 (version 1.8.0_461)
- Tools: Visual Studio Code, Windows PowerShell, Java compiler (`javac`)

## How to run

1. Compile:

```bash
javac Practice.java
```

2. Run, providing the input via stdin redirection from a file:

```bash
java Practice < input.txt
```

Alternatively, input can be typed manually into the terminal after running `java Practice`, or in case you're using Visual Studio Code, you may press `Run Java`.

3. Output is printed to stdout: for each test case, one line containing the equivalent state pairs in lexicographical order, formatted as `(i, j) (k, l) ...`.

## Algorithm

The program implements the table-filling algorithm from Kozen (1997), Lecture 14, to compute all pairs of equivalent states of a DFA without inaccessible states.

For each test case:

1. Table setup: an `n x n` boolean matrix `marcados` is used, where only the upper triangle (`i < j`) is meaningful — `marcados[i][j] == true` means the pair `{i, j}` has been proven not equivalent.
2. Initial marking: for every pair `{i, j}` with `i < j`, if exactly one of the two states is final (`finales[i] != finales[j]`), the pair is marked immediately, since reading the empty string already distinguishes them.
3. Propagation: the algorithm repeatedly scans every unmarked pair `{i, j}`. For each symbol `k` in the alphabet, it computes the destination states `transiciones[i][k]` and `transiciones[j][k]`, normalizes them to `(menor, mayor) = (min, max)` so they can be looked up in the upper triangle, and if that resulting pair is already marked, `{i, j}` is marked too because a distinguishing string exists via that transition. This repeats in full passes over the table, tracked with a `cambio` flag, until a complete pass produces no new marks.
4. Output: once no more marks are added, every unmarked pair `{i, j}` (`i < j`) represents two equivalent states (`p ≈ q`). These pairs are printed in lexicographical order (ascending `i`, then ascending `j`), which is guaranteed naturally by iterating in increasing order.

Complexity: in the worst case, this runs in O(n^4 * m) time, since each of the up to O(n^2) full passes may re-check all O(n^2) pairs, and each check requires up to `m` lookups for the alphabet size. This matches the straightforward version of the algorithm presented in the lecture notes.
