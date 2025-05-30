package com.java.code.graph;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * <p>You are given a m×n 2D grid initialized with these three possible values:</p>
 *
 * <p>1. <code>-1</code> - A wall or an obstacle.</p>
 * <p>2. <code>0</code> - A gate.</p>
 * <p>3. <code>INF</code> - Infinity means an empty room. We use the integer 2^31 - 1 = 2147483647 to represent INF as you may assum that the distance to a gate is less than 2147483647.</p>
 * <p>Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.</p>
 * </br>
 * <p>Assume the grid can only be traversed up, down, left, or right.</p>
 *
 * <p>Modify the grid in-place.</p>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre>
 * <strong>Input:</strong> [
 *   [2147483647,-1,0,2147483647],
 *   [2147483647,2147483647,2147483647,-1],
 *   [2147483647,-1,2147483647,-1],
 *   [0,-1,2147483647,2147483647]
 * ]
 *
 * <strong>Output:</strong> [
 *   [3,-1,0,1],
 *   [2,2,1,-1],
 *   [1,-1,2,-1],
 *   [0,-1,3,4]
 * ]
 * </pre>
 *
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre>
 * <strong>Input:</strong> [
 *   [0,-1],
 *   [2147483647,2147483647]
 * ]
 *
 * <strong>Output:</strong> [
 *   [0,-1],
 *   [1,2]
 * ]
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li>m == grid.length</li>
 * <li>n == grid[i].length</li>
 * <li>1 <= m, n <= 100</li>
 * <li>grid[i][j] is one of {-1, 0, 2147483647}</li>
 * </ul>
 */
public class WallsAndGates extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[][] {
                        new int[] {2147483647,-1,0,2147483647},
                        new int[] {2147483647,2147483647,2147483647,-1},
                        new int[] {2147483647,-1,2147483647,-1},
                        new int[] {0,-1,2147483647,2147483647},
                },
                 new int[][] {
                         new int[] {3,-1,0,1},
                         new int[] {2,2,1,-1},
                         new int[] {1,-1,2,-1},
                         new int[] {0,-1,3,4},
                         }
                },

                {new int[][] {
                        new int[] {0,-1},
                        new int[] {2147483647,2147483647},
                        },
                 new int[][] {
                         new int[] {0,-1},
                         new int[] {1,2},
                 }
                },
        };
    }

    @Test(dataProvider = "data")
    public void test(int[][] grid, int[][] expected) {
        String input = Arrays.deepToString(grid);
        islandsAndTreasure(grid);
        softAssert.as(String.format("grid = %s", input))
                  .assertThat(Arrays.deepToString(grid))
                  .isEqualTo(Arrays.deepToString(expected));
    }

    public void islandsAndTreasure(int[][] grid) {
        Queue<AbstractMap.SimpleEntry<Integer, Integer>> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    queue.offer(new AbstractMap.SimpleEntry<>(i, j));
                    while (!queue.isEmpty()) {
                        var pair = queue.poll();
                        int row = pair.getKey();
                        int column = pair.getValue();
                        int currentDistance = grid[row][column];
                        if (row > 0 && grid[row - 1][column] != -1 && grid[row - 1][column] != 0) {
                            if (currentDistance + 1 < grid[row - 1][column]) {
                                grid[row - 1][column] = currentDistance + 1;
                                queue.offer(new AbstractMap.SimpleEntry<>(row - 1, column));
                            }
                        }
                        if (row < grid.length - 1 && grid[row + 1][column] != -1 && grid[row + 1][column] != 0) {
                            if (currentDistance + 1 < grid[row + 1][column]) {
                                grid[row + 1][column] = currentDistance + 1;
                                queue.offer(new AbstractMap.SimpleEntry<>(row + 1, column));
                            }
                        }
                        if (column > 0 && grid[row][column - 1] != -1 && grid[row][column - 1] != 0) {
                            if (currentDistance + 1 < grid[row][column - 1]) {
                                grid[row][column - 1] = currentDistance + 1;
                                queue.offer(new AbstractMap.SimpleEntry<>(row, column - 1));
                            }
                        }
                        if (column < grid[0].length - 1 && grid[row][column + 1] != -1 && grid[row][column + 1] != 0) {
                            if (currentDistance + 1 < grid[row][column + 1]) {
                                grid[row][column + 1] = currentDistance + 1;
                                queue.offer(new AbstractMap.SimpleEntry<>(row, column + 1));
                            }
                        }
                    }
                }
            }
        }
    }
}
