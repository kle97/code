package com.java.code.dynamicprogramming2d;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 <p>There is a robot on an <code>m x n</code> grid. The robot is initially located at the <strong>top-left corner</strong> (i.e., <code>grid[0][0]</code>). The robot tries to move to the <strong>bottom-right corner</strong> (i.e., <code>grid[m - 1][n - 1]</code>). The robot can only move either down or right at any point in time.</p>

 <p>Given the two integers <code>m</code> and <code>n</code>, return <em>the number of possible unique paths that the robot can take to reach the bottom-right corner</em>.</p>

 <p>The test cases are generated so that the answer will be less than or equal to <code>2 * 10<sup>9</sup></code>.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p> 
 <img src="https://assets.leetcode.com/uploads/2018/10/22/robot_maze.png" style="width: 400px; height: 183px;" /> 
 <pre>
 <strong>Input:</strong> m = 3, n = 7
 <strong>Output:</strong> 28
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> m = 3, n = 2
 <strong>Output:</strong> 3
 <strong>Explanation:</strong> From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 1. Right -&gt; Down -&gt; Down
 2. Down -&gt; Down -&gt; Right
 3. Down -&gt; Right -&gt; Down
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= m, n &lt;= 100</code></li> 
 </ul>

 <div><div>Related Topics</div><div><li>Math</li><li>Dynamic Programming</li><li>Combinatorics</li></div></div><br><div><li>👍 17447</li><li>👎 466</li></div>
 */
public class UniquePaths extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {3, 7, 28},
                {3, 2, 3},
        };
    }

    @Test(dataProvider = "data")
    public void test(int m, int n, int expected) {
        softAssert.as(String.format("m = %d, n = %d", m, n))
                  .assertThat(uniquePaths(m, n))
                  .isEqualTo(expected);
    }

    public int uniquePaths(int m, int n) {
        return uniquePaths(m, n, 0, 0, new Integer[m][n]);
    }

    public int uniquePaths(int m, int n, int i, int j, Integer[][] memoized) {
        if (i == m - 1 && j == n - 1) {
            return 1;
        }
        if (i >= m || j >= n) {
            return 0;
        }
        
        if (memoized[i][j] == null) {
            int result = uniquePaths(m, n, i + 1, j, memoized);
            result += uniquePaths(m, n, i, j + 1, memoized);
            memoized[i][j] = result;
        }
        
        return memoized[i][j];
    }
}
