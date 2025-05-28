package com.java.code.dynamicprogramming2d;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>You are given an array <code>prices</code> where <code>prices[i]</code> is the price of a given stock on the <code>i<sup>th</sup></code> day.</p>

 <p>Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:</p>

 <ul>
 <li>After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).</li>
 </ul>

 <p><strong>Note:</strong> You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> prices = [1,2,3,0,2]
 <strong>Output:</strong> 3
 <strong>Explanation:</strong> transactions = [buy, sell, cooldown, buy, sell]
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> prices = [1]
 <strong>Output:</strong> 0
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= prices.length &lt;= 5000</code></li>
 <li><code>0 &lt;= prices[i] &lt;= 1000</code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Dynamic Programming</li></div></div><br><div><li>👍 9717</li><li>👎 341</li></div>
 */
public class BestTimeToBuyAndSellStockWithCooldown extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {1,2,3,0,2}, 3},
                {new int[] {1}, 0},
                {new int[] {1,2}, 1},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] prices, int expected) {
        softAssert.as(String.format("prices = %s", Arrays.toString(prices)))
                  .assertThat(maxProfit(prices))
                  .isEqualTo(expected);
    }

    public int maxProfit(int[] prices) {
        return maxProfit(prices, 0, 1001, new Integer[prices.length][1002]);
    }

    public int maxProfit(int[] prices, int i, int lastPrice, Integer[][] memoized) {
        if (prices.length == 1 || i >= prices.length) {
            return 0;
        }

        if (memoized[i][lastPrice] == null) {
            int maxProfit;
            if (lastPrice == 1001) {
                maxProfit = Math.max(maxProfit(prices, i + 1, prices[i], memoized),
                                     maxProfit(prices, i + 1, lastPrice, memoized));
            } else {
                maxProfit = Math.max(prices[i] - lastPrice + maxProfit(prices, i + 2, 1001, memoized),
                                     maxProfit(prices, i + 1, lastPrice, memoized));
            }
            memoized[i][lastPrice] = maxProfit;
        }
        return Math.max(0, memoized[i][lastPrice]);
    }
}
