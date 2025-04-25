package com.java.code.stack;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * <p>Given an array of integers<span>&nbsp;</span><code>heights</code><span>&nbsp;</span>representing the histogram's bar height where the width of each bar is<span>&nbsp;</span><code>1</code>, return<span>&nbsp;</span><em>the area of the largest rectangle in the histogram</em>.</p>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <p><img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/histogram.jpg" /></p>
 * <pre><strong>Input:</strong> heights = [2,1,5,6,2,3]
 * <strong>Output:</strong> 10
 * <strong>Explanation:</strong> The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <p><img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/histogram-1.jpg" /></p>
 * <pre><strong>Input:</strong> heights = [2,4]
 * <strong>Output:</strong> 4
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;= heights .length &lt;= 10<sup>5</sup></code></li>
 * <li><code>0 &lt;= heights[i] &lt;= 10<sup>4</sup></code></li>
 * </ul>
 */
public class LargestRectangleInHistogram extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {2,1,5,6,2,3}, 10},
                {new int[] {2,4}, 4},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] heights, int expected) {
        softAssert.as(String.format("heights = %s", Arrays.toString(heights)))
                  .assertThat(largestRectangleArea(heights))
                  .isEqualTo(expected);
    }

    public int largestRectangleArea(int[] heights) {
        return 0;
    }
}
