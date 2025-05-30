package com.java.code.graph;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 <p>You are given an <code>m x n</code> <code>grid</code> where each cell can have one of three values:</p>

 <ul>
 <li><code>0</code> representing an empty cell,</li>
 <li><code>1</code> representing a fresh orange, or</li>
 <li><code>2</code> representing a rotten orange.</li>
 </ul>

 <p>Every minute, any fresh orange that is <strong>4-directionally adjacent</strong> to a rotten orange becomes rotten.</p>

 <p>Return <em>the minimum number of minutes that must elapse until no cell has a fresh orange</em>. If <em>this is impossible, return</em> <code>-1</code>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2019/02/16/oranges.png" style="width: 650px; height: 137px;" />
 <pre>
 <strong>Input:</strong> grid = [[2,1,1],[1,1,0],[0,1,1]]
 <strong>Output:</strong> 4
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> grid = [[2,1,1],[0,1,1],[1,0,1]]
 <strong>Output:</strong> -1
 <strong>Explanation:</strong> The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> grid = [[0,2]]
 <strong>Output:</strong> 0
 <strong>Explanation:</strong> Since there are already no fresh oranges at minute 0, the answer is just 0.
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>m == grid.length</code></li>
 <li><code>n == grid[i].length</code></li>
 <li><code>1 &lt;= m, n &lt;= 10</code></li>
 <li><code>grid[i][j]</code> is <code>0</code>, <code>1</code>, or <code>2</code>.</li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Breadth-First Search</li><li>Matrix</li></div></div><br><div><li>👍 13924</li><li>👎 439</li></div>
 */
public class RottingOranges extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[][] {
                        new int[] {2,1,1},
                        new int[] {1,1,0},
                        new int[] {0,1,1},
                 }, 4
                },
                {new int[][] {
                        new int[] {2,1,1},
                        new int[] {0,1,1},
                        new int[] {1,0,1},
                 }, -1
                },
                {new int[][] {
                        new int[] {0,2},
                 }, 0
                },
                {new int[][] {
                        new int[] {2,1,1},
                        new int[] {1,1,1},
                        new int[] {0,1,2},
                 }, 2
                },
        };
    }

    @Test(dataProvider = "data")
    public void test(int[][] grid, int expected) {
        softAssert.as(String.format("grid = %s", Arrays.deepToString(grid)))
                  .assertThat(orangesRotting(grid))
                  .isEqualTo(expected);
    }

    public int orangesRotting(int[][] grid) {
        Queue<Integer[]> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new Integer[]{i, j});
                    while (!queue.isEmpty()) {
                        Integer[] entry = queue.poll();
                        int row = entry[0];
                        int column = entry[1];
                        int currentMinutes = grid[row][column];
                        if (row > 0 && grid[row - 1][column] != 0 && grid[row - 1][column] != 2) {
                            grid[row - 1][column] = grid[row - 1][column] == 1 ? Integer.MAX_VALUE : grid[row - 1][column];
                            if (currentMinutes + 1 < grid[row - 1][column]) {
                                grid[row - 1][column] = currentMinutes + 1;
                                queue.offer(new Integer[]{row - 1, column});
                            }
                        }
                        if (row < grid.length - 1 && grid[row + 1][column] != 0 && grid[row + 1][column] != 2) {
                            grid[row + 1][column] = grid[row + 1][column] == 1 ? Integer.MAX_VALUE : grid[row + 1][column];
                            if (currentMinutes + 1 < grid[row + 1][column]) {
                                grid[row + 1][column] = currentMinutes + 1;
                                queue.offer(new Integer[]{row + 1, column});
                            }
                        }
                        if (column > 0 && grid[row][column - 1] != 0 && grid[row][column - 1] != 2) {
                            grid[row][column - 1] = grid[row][column - 1] == 1 ? Integer.MAX_VALUE : grid[row][column - 1];
                            if (currentMinutes + 1 < grid[row][column - 1]) {
                                grid[row][column - 1] = currentMinutes + 1;
                                queue.offer(new Integer[]{row, column - 1});
                            }
                        }
                        if (column < grid[0].length - 1 && grid[row][column + 1] != 0 && grid[row][column + 1] != 2) {
                            grid[row][column + 1] = grid[row][column + 1] == 1 ? Integer.MAX_VALUE : grid[row][column + 1];
                            if (currentMinutes + 1 < grid[row][column + 1]) {
                                grid[row][column + 1] = currentMinutes + 1;
                                queue.offer(new Integer[]{row, column + 1});
                            }
                        }
                    }
                }
            }
        }
        int minMinutes = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] > 2) {
                    minMinutes = Math.max(minMinutes, grid[i][j] - 2);
                }
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return minMinutes;
    }
}
