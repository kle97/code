package com.java.code.dynamicprogramming;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>You are given an integer array <code>cost</code> where <code>cost[i]</code> is the cost of <code>i<sup>th</sup></code> step on a staircase. Once you pay the cost, you can either climb one or two steps.</p>

 <p>You can either start from the step with index <code>0</code>, or the step with index <code>1</code>.</p>

 <p>Return <em>the minimum cost to reach the top of the floor</em>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> cost = [10,<u>15</u>,20]
 <strong>Output:</strong> 15
 <strong>Explanation:</strong> You will start at index 1.
 - Pay 15 and climb two steps to reach the top.
 The total cost is 15.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> cost = [<u>1</u>,100,<u>1</u>,1,<u>1</u>,100,<u>1</u>,<u>1</u>,100,<u>1</u>]
 <strong>Output:</strong> 6
 <strong>Explanation:</strong> You will start at index 0.
 - Pay 1 and climb two steps to reach index 2.
 - Pay 1 and climb two steps to reach index 4.
 - Pay 1 and climb two steps to reach index 6.
 - Pay 1 and climb one step to reach index 7.
 - Pay 1 and climb two steps to reach index 9.
 - Pay 1 and climb one step to reach the top.
 The total cost is 6.
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>2 &lt;= cost .length &lt;= 1000</code></li>
 <li><code>0 &lt;= cost[i] &lt;= 999</code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Dynamic Programming</li></div></div><br><div><li>👍 11851</li><li>👎 1821</li></div>
 */
public class MinCostClimbingStairs extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {10,15,20}, 15},
                {new int[] {1,100,1,1,1,100,1,1,100,1}, 6},
                {new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0}, 1},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] cost, int expected) {
        softAssert.as(String.format("cost = %s", Arrays.toString(cost)))
                  .assertThat(minCostClimbingStairs(cost))
                  .isEqualTo(expected);
    }

    public int minCostClimbingStairs(int[] cost) {
        return minCostClimbingStairs(cost.length, cost, new Integer[cost.length + 1]);
    }

    public int minCostClimbingStairs(int n, int[] cost, Integer[] memoized) {
        if (n == 0 || n == 1) {
            return 0;
        } else if(memoized[n] != null) {
            return memoized[n];
        } else {
            int climb1Step = minCostClimbingStairs(n - 1, cost, memoized) + cost[n - 1];
            int climb2Steps = minCostClimbingStairs(n - 2, cost, memoized) + cost[n - 2];
            if (climb1Step < climb2Steps) {
                memoized[n] = climb1Step;
                return climb1Step;
            } else {
                memoized[n] = climb2Steps;
                return climb2Steps;
            }
        }
    }
}
