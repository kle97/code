package com.java.code.slidingwindow;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>You are given an array <code>prices</code> where <code>prices[i]</code> is the price of a given stock on the <code>i<sup>th</sup></code> day.</p>

 <p>You want to maximize your profit by choosing a <strong>single day</strong> to buy one stock and choosing a <strong>different day in the future</strong> to sell that stock.</p>

 <p>Return <em>the maximum profit you can achieve from this transaction</em>. If you cannot achieve any profit, return <code>0</code>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> prices = [7,1,5,3,6,4]
 <strong>Output:</strong> 5
 <strong>Explanation:</strong> Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> prices = [7,6,4,3,1]
 <strong>Output:</strong> 0
 <strong>Explanation:</strong> In this case, no transactions are done and the max profit = 0.
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= prices .length &lt;= 10<sup>5</sup></code></li>
 <li><code>0 &lt;= prices[i] &lt;= 10<sup>4</sup></code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Dynamic Programming</li></div></div><br><div><li>👍 29998</li><li>👎 1077</li></div>
 */
public class BestTimeToBuyAndSellStock extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {7,1,5,3,6,4}, 5},
                {new int[] {7,6,4,3,1}, 0},
                {new int[] {2,1,4}, 3},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] prices, int expected) {
        softAssert.as(String.format("prices = %s", Arrays.toString(prices)))
                  .assertThat(maxProfit(prices))
                  .isEqualTo(expected);
    }

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int lowest = prices[0];
        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - lowest);
            lowest = Math.min(lowest, prices[i]);
        }
        return maxProfit;
    }
}
