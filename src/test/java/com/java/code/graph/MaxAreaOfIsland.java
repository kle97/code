package com.java.code.graph;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>You are given an <code>m x n</code> binary matrix <code>grid</code>. An island is a group of <code>1</code>'s (representing land) connected <strong>4-directionally</strong> (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.</p>

 <p>The <strong>area</strong> of an island is the number of cells with a value <code>1</code> in the island.</p>

 <p>Return <em>the maximum <strong>area</strong> of an island in </em><code>grid</code>. If there is no island, return <code>0</code>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2021/05/01/maxarea1-grid.jpg" style="width: 500px; height: 310px;" />
 <pre>
 <strong>Input:</strong> grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 <strong>Output:</strong> 6
 <strong>Explanation:</strong> The answer is not 11, because the island must be connected 4-directionally.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> grid = [[0,0,0,0,0,0,0,0]]
 <strong>Output:</strong> 0
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>m == grid.length</code></li>
 <li><code>n == grid[i].length</code></li>
 <li><code>1 &lt;= m, n &lt;= 50</code></li>
 <li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Depth-First Search</li><li>Breadth-First Search</li><li>Union Find</li><li>Matrix</li></div></div><br><div><li>👍 10292</li><li>👎 215</li></div>
 */
public class MaxAreaOfIsland extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[][] {
                        new int[] {0,0,1,0,0,0,0,1,0,0,0,0,0},
                        new int[] {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        new int[] {0,1,1,0,1,0,0,0,0,0,0,0,0},
                        new int[] {0,1,0,0,1,1,0,0,1,0,1,0,0},
                        new int[] {0,1,0,0,1,1,0,0,1,1,1,0,0},
                        new int[] {0,0,0,0,0,0,0,0,0,0,1,0,0},
                        new int[] {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        new int[] {0,0,0,0,0,0,0,1,1,0,0,0,0}}, 6},
                {new int[][] {new int[] {0,0,0,0,0,0,0,0}}, 0}
                };
    }

    @Test(dataProvider = "data")
    public void test(int[][] grid, int expected) {
        softAssert.as(String.format("grid = %s", Arrays.deepToString(grid)))
                  .assertThat(maxAreaOfIsland(grid))
                  .isEqualTo(expected);
    }

    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] checked = new boolean[grid.length][grid[0].length];
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !checked[i][j]) {
                    maxArea = Math.max(maxArea, dfs(grid, checked, i, j));
                }
            }
        }
        return maxArea;
    }

    public int dfs(int[][] grid, boolean[][] checked, int i, int j) {
        int result = 0;
        if (grid[i][j] == 1) {
            checked[i][j] = true;
            result++;
        }
        if (i > 0 && grid[i - 1][j] == 1 && !checked[i - 1][j]) {
            result += dfs(grid, checked, i - 1, j);
        }
        if (j > 0 && grid[i][j - 1] == 1 && !checked[i][j - 1]) {
            result += dfs(grid, checked, i, j - 1);
        }
        if (i < grid.length - 1 && grid[i + 1][j] == 1 && !checked[i + 1][j]) {
            result += dfs(grid, checked, i + 1, j);
        }
        if (j < grid[0].length - 1 && grid[i][j + 1] == 1 && !checked[i][j + 1]) {
            result += dfs(grid, checked, i, j + 1);
        }
        return result;
    }
}
