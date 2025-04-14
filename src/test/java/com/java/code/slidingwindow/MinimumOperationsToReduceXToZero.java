package com.java.code.slidingwindow;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * <p>You are given an integer array<span>&nbsp;</span><code>nums</code><span>&nbsp;</span>and an integer<span>&nbsp;</span><code>x</code>. In one operation, you can either remove the leftmost or the rightmost element from the array<span>&nbsp;</span><code>nums</code><span>&nbsp;</span>and subtract its value from<span>&nbsp;</span><code>x</code>. Note that this<span>&nbsp;</span><strong>modifies</strong><span>&nbsp;</span>the array for future operations.</p>
 * <p>Return<span>&nbsp;</span><em>the<span>&nbsp;</span><strong>minimum number</strong><span>&nbsp;</span>of operations to reduce<span>&nbsp;</span></em><code>x</code><span>&nbsp;</span><em>to<span>&nbsp;</span><strong>exactly</strong></em><span>&nbsp;</span><code>0</code><span>&nbsp;</span><em>if it is possible</em><em>, otherwise, return<span>&nbsp;</span></em><code>-1</code>.</p>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input:</strong> nums = [1,1,4,2,3], x = 5
 * <strong>Output:</strong> 2
 * <strong>Explanation:</strong> The optimal solution is to remove the last two elements to reduce x to zero.
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre><strong>Input:</strong> nums = [5,6,7,8,9], x = 4
 * <strong>Output:</strong> -1
 * </pre>
 * <p><strong class="example">Example 3:</strong></p>
 * <pre><strong>Input:</strong> nums = [3,2,20,1,1,3], x = 10
 * <strong>Output:</strong> 5
 * <strong>Explanation:</strong> The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
 * <li><code>1 &lt;= x &lt;= 10<sup>9</sup></code></li>
 * </ul>
 */
public class MinimumOperationsToReduceXToZero extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {1,1,4,2,3}, 5, 2},
                {new int[] {5,6,7,8,9}, 4, -1},
                {new int[] {3,2,20,1,1,3}, 10, 5},
                {new int[] {1,1}, 3, -1},
                {new int[] {5}, 5, 1},
                {new int[] {6016,5483,541,4325,8149,3515,7865,2209,9623,9763,4052,6540,2123,2074,765,7520,4941,5290,5868,6150,6006,6077,2856,7826,9119}, 31841, 6},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, int x, int expected) {
        softAssert.as(String.format("nums = %s, x = %d", Arrays.toString(nums), x))
                  .assertThat(minOperations(nums, x))
                  .isEqualTo(expected);
    }

    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        int maxLength = Integer.MIN_VALUE;
        int windowSum = 0;
        int windowStart = 0;

        if (windowSum == sum - x) {
            maxLength = 0;
        }
        for (int i = 0; i < nums.length; i++) {
            windowSum += nums[i];
            while (windowSum > (sum - x) && i > 0 && windowStart < nums.length - 1) {
                windowSum -= nums[windowStart];
                windowStart++;
            }

            if (windowSum == sum - x) {
                maxLength = Math.max(maxLength, i - windowStart + 1);
            }
        }

        return maxLength == Integer.MIN_VALUE ? -1 : nums.length - maxLength;
    }
}
