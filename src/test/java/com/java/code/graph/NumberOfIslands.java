package com.java.code.graph;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>Given an <code>m x n</code> 2D binary grid <code>grid</code> which represents a map of <code>'1'</code>s (land) and <code>'0'</code>s (water), return <em>the number of islands</em>.</p>

 <p>An <strong>island</strong> is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> grid = [
 ["1","1","1","1","0"],
 ["1","1","0","1","0"],
 ["1","1","0","0","0"],
 ["0","0","0","0","0"]
 ]
 <strong>Output:</strong> 1
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> grid = [
 ["1","1","0","0","0"],
 ["1","1","0","0","0"],
 ["0","0","1","0","0"],
 ["0","0","0","1","1"]
 ]
 <strong>Output:</strong> 3
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>m == grid.length</code></li>
 <li><code>n == grid[i].length</code></li>
 <li><code>1 &lt;= m, n &lt;= 300</code></li>
 <li><code>grid[i][j]</code> is <code>'0'</code> or <code>'1'</code>.</li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Depth-First Search</li><li>Breadth-First Search</li><li>Union Find</li><li>Matrix</li></div></div><br><div><li>👍 23808</li><li>👎 564</li></div>
 */
public class NumberOfIslands extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new char[][] {
                        new char[] {'1','1','1','1','0'},
                        new char[] {'1','1','0','1','0'},
                        new char[] {'1','1','0','0','0'},
                        new char[] {'0','0','0','0','0'}, }, 1},
                {new char[][] {
                        new char[] {'1','1','0','0','0'},
                        new char[] {'1','1','0','0','0'},
                        new char[] {'0','0','1','0','0'},
                        new char[] {'0','0','0','1','1'}, }, 3},
                {new char[][] {
                        new char[] {'0','0','0','0','0'},
                        new char[] {'1','1','0','0','0'},
                        new char[] {'0','0','1','0','0'},
                        new char[] {'0','0','0','1','1'}, }, 3},
                {new char[][] {
                        new char[] {'1','1','1'},
                        new char[] {'0','1','0'},
                        new char[] {'1','1','1'}}, 1},
        };
    }

    @Test(dataProvider = "data")
    public void test(char[][] grid, int expected) {
        softAssert.as(String.format("grid = %s", Arrays.deepToString(grid)))
                  .assertThat(numIslands(grid))
                  .isEqualTo(expected);
    }

    public int numIslands(char[][] grid) {
        boolean[][] checked = new boolean[grid.length][grid[0].length];
        int islandCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !checked[i][j]) {
                    checked[i][j] = true;
                    islandCount++;
                    dfs(grid, checked, i, j);
                }
            }
        }
        return islandCount;
    }

    public void dfs(char[][] grid, boolean[][] checked, int i, int j) {
        if (grid[i][j] == '1') {
            checked[i][j] = true;
        }
        if (i > 0 && grid[i - 1][j] == '1' && !checked[i - 1][j]) {
            dfs(grid, checked, i - 1, j);
        }
        if (j > 0 && grid[i][j - 1] == '1' && !checked[i][j - 1]) {
            dfs(grid, checked, i, j - 1);
        }
        if (i < grid.length - 1 && grid[i + 1][j] == '1' && !checked[i + 1][j]) {
            dfs(grid, checked, i + 1, j);
        }
        if (j < grid[0].length - 1 && grid[i][j + 1] == '1' && !checked[i][j + 1]) {
            dfs(grid, checked, i, j + 1);
        }
    }
}
