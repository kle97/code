package com.java.code.graph;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 <p>There is an <code>m x n</code> rectangular island that borders both the <strong>Pacific Ocean</strong> and <strong>Atlantic Ocean</strong>. The <strong>Pacific Ocean</strong> touches the island's left and top edges, and the <strong>Atlantic Ocean</strong> touches the island's right and bottom edges.</p>

 <p>The island is partitioned into a grid of square cells. You are given an <code>m x n</code> integer matrix <code>heights</code> where <code>heights[r][c]</code> represents the <strong>height above sea level</strong> of the cell at coordinate <code>(r, c)</code>.</p>

 <p>The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is <strong>less than or equal to</strong> the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.</p>

 <p>Return <em>a <strong>2D list</strong> of grid coordinates </em><code>result</code><em> where </em><code>result[i] = [r<sub>i</sub>, c<sub>i</sub>]</code><em> denotes that rain water can flow from cell </em><code>(r<sub>i</sub>, c<sub>i</sub>)</code><em> to <strong>both</strong> the Pacific and Atlantic oceans</em>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2021/06/08/waterflow-grid.jpg" style="width: 400px; height: 400px;" />
 <pre>
 <strong>Input:</strong> heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 <strong>Output:</strong> [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 <strong>Explanation:</strong> The following cells can flow to the Pacific and Atlantic oceans, as shown below:
 [0,4]: [0,4] -&gt; Pacific Ocean
 &nbsp;      [0,4] -&gt; Atlantic Ocean
 [1,3]: [1,3] -&gt; [0,3] -&gt; Pacific Ocean
 &nbsp;      [1,3] -&gt; [1,4] -&gt; Atlantic Ocean
 [1,4]: [1,4] -&gt; [1,3] -&gt; [0,3] -&gt; Pacific Ocean
 &nbsp;      [1,4] -&gt; Atlantic Ocean
 [2,2]: [2,2] -&gt; [1,2] -&gt; [0,2] -&gt; Pacific Ocean
 &nbsp;      [2,2] -&gt; [2,3] -&gt; [2,4] -&gt; Atlantic Ocean
 [3,0]: [3,0] -&gt; Pacific Ocean
 &nbsp;      [3,0] -&gt; [4,0] -&gt; Atlantic Ocean
 [3,1]: [3,1] -&gt; [3,0] -&gt; Pacific Ocean
 &nbsp;      [3,1] -&gt; [4,1] -&gt; Atlantic Ocean
 [4,0]: [4,0] -&gt; Pacific Ocean
 [4,0] -&gt; Atlantic Ocean
 Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> heights = [[1]]
 <strong>Output:</strong> [[0,0]]
 <strong>Explanation:</strong> The water can flow from the only cell to the Pacific and Atlantic oceans.
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>m == heights.length</code></li>
 <li><code>n == heights[r].length</code></li>
 <li><code>1 &lt;= m, n &lt;= 200</code></li>
 <li><code>0 &lt;= heights[r][c] &lt;= 10<sup>5</sup></code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Depth-First Search</li><li>Breadth-First Search</li><li>Matrix</li></div></div><br><div><li>👍 7842</li><li>👎 1599</li></div>
 */
public class PacificAtlanticWaterFlow extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[][] {
                        new int[] {1,2,2,3,5},
                        new int[] {3,2,3,4,4},
                        new int[] {2,4,5,3,1},
                        new int[] {6,7,1,4,5},
                        new int[] {5,1,1,2,4},
                },
                 List.of (
                         List.of(0,4),
                         List.of(1,3),
                         List.of(1,4),
                         List.of(2,2),
                         List.of(3,0),
                         List.of(3,1),
                         List.of(4,0)
                  )
                },

                {new int[][] {
                        new int[] {1},
                        },
                 List.of (
                         List.of(0,0)
                 )
                },
        };
    }

    @Test(dataProvider = "data")
    public void test(int[][] heights, List<List<Integer>> expected) {
        softAssert.as(String.format("heights = %s", Arrays.deepToString(heights)))
                  .assertThat(pacificAtlantic(heights))
                  .isEqualTo(expected);
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (reachPacificOrAtlantic(heights, i, j, new boolean[heights.length][heights[0].length], true)
                        && reachPacificOrAtlantic(heights, i, j, new boolean[heights.length][heights[0].length], false)) {
                    answer.add(List.of(i, j));
                }
            }
        }
        return answer;
    }

    public boolean reachPacificOrAtlantic(int[][] heights, int i, int j, boolean[][] visited, boolean isPacific) {
        if (i == 0 || j == 0 || i == heights.length - 1 || j == heights[0].length - 1) {
            if (isPacific) {
                if (i == 0 || j == 0) {
                    return true;
                }
            } else {
                if (i == heights.length - 1 || j == heights[0].length - 1) {
                    return true;
                }
            }
        }
        if (visited[i][j]) {
            return false;
        }

        visited[i][j] = true;
        if (i > 0 && heights[i - 1][j] <= heights[i][j]) {
            boolean result = reachPacificOrAtlantic(heights, i - 1, j, visited, isPacific);
            if (result) {
                return true;
            }
        }
        if (i < heights.length - 1 && heights[i + 1][j] <= heights[i][j]) {
            boolean result = reachPacificOrAtlantic(heights, i + 1, j, visited, isPacific);
            if (result) {
                return true;
            }
        }
        if (j > 0 && heights[i][j - 1] <= heights[i][j]) {
            boolean result = reachPacificOrAtlantic(heights, i, j - 1, visited, isPacific);
            if (result) {
                return true;
            }
        }
        if (j < heights[0].length - 1 && heights[i][j + 1] <= heights[i][j]) {
            boolean result = reachPacificOrAtlantic(heights, i, j + 1, visited, isPacific);
            if (result) {
                return true;
            }
        }

        visited[i][j] = false;
        return false;
    }
}
