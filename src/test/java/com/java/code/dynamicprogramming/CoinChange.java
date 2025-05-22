package com.java.code.dynamicprogramming;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>You are given an integer array <code>coins</code> representing coins of different denominations and an integer <code>amount</code> representing a total amount of money.</p>

 <p>Return <em>the fewest number of coins that you need to make up that amount</em>. If that amount of money cannot be made up by any combination of the coins, return <code>-1</code>.</p>

 <p>You may assume that you have an infinite number of each kind of coin.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> coins = [1,2,5], amount = 11
 <strong>Output:</strong> 3
 <strong>Explanation:</strong> 11 = 5 + 5 + 1
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> coins = [2], amount = 3
 <strong>Output:</strong> -1
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> coins = [1], amount = 0
 <strong>Output:</strong> 0
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= coins.length &lt;= 12</code></li>
 <li><code>1 &lt;= coins[i] &lt;= 2<sup>31</sup> - 1</code></li>
 <li><code>0 &lt;= amount &lt;= 10<sup>4</sup></code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Dynamic Programming</li><li>Breadth-First Search</li></div></div><br><div><li>👍 19858</li><li>👎 498</li></div>
 */
public class CoinChange extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {1,2,5}, 11, 3},
                {new int[] {2}, 3, -1},
                {new int[] {1}, 0, 0},
                {new int[] {1, 2147483647}, 2, 2},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] coins, int amount, int expected) {
        softAssert.as(String.format("coins = %s, amount = %d", Arrays.toString(coins), amount))
                  .assertThat(coinChange(coins, amount))
                  .isEqualTo(expected);
    }

    public int coinChange(int[] coins, int amount) {
        int result = coinChange(coins, amount, 0, new Integer[amount]);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public int coinChange(int[] coins, int amount, int currentAmount, Integer[] memoized) {
        if (currentAmount == amount) {
            return 0;
        }

        if (memoized[currentAmount] == null) {
            int result = Integer.MAX_VALUE;
            for (int i = 0; i < coins.length; i++) {
                if (currentAmount + coins[i] >= 0 && currentAmount + coins[i] <= amount) {
                    int nextResult = coinChange(coins, amount, currentAmount + coins[i], memoized);
                    if (nextResult != Integer.MAX_VALUE) {
                        result = Math.min(result, 1 + nextResult);
                    }
                }
            }
            memoized[currentAmount] = result;
        }

        return memoized[currentAmount];
    }
}
