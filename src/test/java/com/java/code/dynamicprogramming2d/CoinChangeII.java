package com.java.code.dynamicprogramming2d;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>You are given an integer array <code>coins</code> representing coins of different denominations and an integer <code>amount</code> representing a total amount of money.</p>

 <p>Return <em>the number of combinations that make up that amount</em>. If that amount of money cannot be made up by any combination of the coins, return <code>0</code>.</p>

 <p>You may assume that you have an infinite number of each kind of coin.</p>

 <p>The answer is <strong>guaranteed</strong> to fit into a signed <strong>32-bit</strong> integer.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> amount = 5, coins = [1,2,5]
 <strong>Output:</strong> 4
 <strong>Explanation:</strong> there are four ways to make up the amount:
 5=5
 5=2+2+1
 5=2+1+1+1
 5=1+1+1+1+1
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> amount = 3, coins = [2]
 <strong>Output:</strong> 0
 <strong>Explanation:</strong> the amount of 3 cannot be made up just with coins of 2.
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> amount = 10, coins = [10]
 <strong>Output:</strong> 1
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= coins.length &lt;= 300</code></li>
 <li><code>1 &lt;= coins[i] &lt;= 5000</code></li>
 <li>All the values of <code>coins</code> are <strong>unique</strong>.</li>
 <li><code>0 &lt;= amount &lt;= 5000</code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Dynamic Programming</li></div></div><br><div><li>👍 9762</li><li>👎 208</li></div>
 */
public class CoinChangeII extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {5, new int[] {1,2,5}, 4},
                {3, new int[] {2}, 0},
                {10, new int[] {10}, 1},
                {500, new int[] {1,2,5}, 12701},
        };
    }

    @Test(dataProvider = "data")
    public void test(int amount, int[] coins, int expected) {
        softAssert.as(String.format("amount = %d, coins = %s", amount, Arrays.toString(coins)))
                  .assertThat(change(amount, coins))
                  .isEqualTo(expected);
    }

    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = coins.length - 1; i >= 0; i--) {
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i]) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - coins[i]];
                }
            }
        }
//        for (int i = 0; i < dp.length; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
        return dp[0][amount];
    }

//    public int change(int amount, int[] coins) {
//        return change(amount, coins, 0, coins.length - 1, new Integer[amount][coins.length]);
//    }
//
//    public int change(int amount, int[] coins, int currentAmount, int currentIndex, Integer[][] memoized) {
//        if (currentAmount > amount) {
//            return 0;
//        } else if (currentAmount == amount) {
//            return 1;
//        } else if (currentIndex < 0 || currentIndex >= coins.length) {
//            return 0;
//        }
//
//        if (memoized[currentAmount][currentIndex] == null) {
//            int result = 0;
//            if (currentAmount + coins[currentIndex] > amount) {
//                result += change(amount, coins, currentAmount, currentIndex - 1, memoized);
//            } else {
//                result += change(amount, coins, currentAmount, currentIndex - 1, memoized);
//                result += change(amount, coins, currentAmount + coins[currentIndex], currentIndex, memoized);
//            }
//            memoized[currentAmount][currentIndex] = result;
//        }
//        return memoized[currentAmount][currentIndex];
//    }
}
