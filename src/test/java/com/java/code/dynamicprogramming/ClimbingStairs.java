package com.java.code.dynamicprogramming;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 <p>You are climbing a staircase. It takes <code>n</code> steps to reach the top.</p>

 <p>Each time you can either climb <code>1</code> or <code>2</code> steps. In how many distinct ways can you climb to the top?</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> n = 2
 <strong>Output:</strong> 2
 <strong>Explanation:</strong> There are two ways to climb to the top.
 1. 1 step + 1 step
 2. 2 steps
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> n = 3
 <strong>Output:</strong> 3
 <strong>Explanation:</strong> There are three ways to climb to the top.
 1. 1 step + 1 step + 1 step
 2. 1 step + 2 steps
 3. 2 steps + 1 step
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= n &lt;= 45</code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Math</li><li>Dynamic Programming</li><li>Memoization</li></div></div><br><div><li>👍 23009</li><li>👎 950</li></div>
 */
public class ClimbingStairs extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {2, 2},
                {3, 3},
                {45, 1836311903},
        };
    }

    @Test(dataProvider = "data")
    public void test(int n, int expected) {
        softAssert.as(String.format("n = %d", n))
                  .assertThat(climbStairs(n))
                  .isEqualTo(expected);
    }

//    public int climbStairs(int n) {
//        int[] memoized = new int[n + 1];
//        memoized[0] = 1;
//        memoized[1] = 1;
//        for (int i = 2; i <= n; i++) {
//            memoized[i] = memoized[i - 1] + memoized[i - 2];
//        }
//        return memoized[n];
//    }

    public int climbStairs(int n) {
        return climbStairs(n, new int[n + 1]);
    }

    public int climbStairs(int n, int[] memoized) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (memoized[n] != 0) {
            return memoized[n];
        } else {
            int climb1Step = climbStairs(n - 1, memoized);
            if (climb1Step > 0) {
                memoized[n - 1] = climbStairs(n - 1, memoized);
            }
            int climb2Steps = climbStairs(n - 2, memoized);
            if (climb2Steps > 0) {
                memoized[n - 2] = climbStairs(n - 2, memoized);
            }
            return climb1Step + climb2Steps;
        }
    }
}
