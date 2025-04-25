package com.java.code.slidingwindow;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * <p>Given an array of positive integers<span>&nbsp;</span><code>nums</code><span>&nbsp;</span>and a positive integer<span>&nbsp;</span><code>target</code>, return<span>&nbsp;</span><em>the<span>&nbsp;</span><strong>minimal length</strong><span>&nbsp;</span>of a<span>&nbsp;</span></em><span data-keyword="subarray-nonempty" class=" cursor-pointer relative text-dark-blue-s text-sm"></span></p>
 * <div class="popover-wrapper inline-block" data-headlessui-state="">
 * <div>
 * <div aria-expanded="false" data-headlessui-state="" id="headlessui-popover-button-:rt:">
 * <div><em>subarray</em></div>
 * </div>
 * <div></div>
 * </div>
 * </div>
 * <p><span data-keyword="subarray-nonempty" class=" cursor-pointer relative text-dark-blue-s text-sm"></span><em><span>&nbsp;</span>whose sum is greater than or equal to</em><span>&nbsp;</span><code>target</code>. If there is no such subarray, return<span>&nbsp;</span><code>0</code><span>&nbsp;</span>instead.</p>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input:</strong> target = 7, nums = [2,3,1,2,4,3]
 * <strong>Output:</strong> 2
 * <strong>Explanation:</strong> The subarray [4,3] has the minimal length under the problem constraint.
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre><strong>Input:</strong> target = 4, nums = [1,4,4]
 * <strong>Output:</strong> 1
 * </pre>
 * <p><strong class="example">Example 3:</strong></p>
 * <pre><strong>Input:</strong> target = 11, nums = [1,1,1,1,1,1,1,1]
 * <strong>Output:</strong> 0
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;= target &lt;= 10<sup>9</sup></code></li>
 * <li><code>1 &lt;= nums .length &lt;= 10<sup>5</sup></code></li>
 * <li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
 * </ul>
 * <p>&nbsp;</p>
 * <p><strong>Follow up:</strong><span>&nbsp;If you have figured out the&nbsp;</span><code>O(n)</code><span>&nbsp;solution, try coding another solution of which the time complexity is&nbsp;</span><code>O(n log(n))</code><span>.</span></p>
 */
public class MinimumSizeSubarraySum extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {7 , new int[] {2,3,1,2,4,3}, 2},
                {4 , new int[] {1,4,4}, 1},
                {11 , new int[] {1,1,1,1,1,1,1,1}, 0},
                {7 , new int[] {2,3,1,2,4,3}, 2},
        };
    }

    @Test(dataProvider = "data")
    public void test(int target, int[] nums, int expected) {
        softAssert.as(String.format("target = %d, nums = %s", target, Arrays.toString(nums)))
                  .assertThat(minSubArrayLen(target, nums))
                  .isEqualTo(expected);
    }

    public int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        int windowStart = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum - nums[windowStart] >= target) {
                sum -= nums[windowStart];
                windowStart++;
            }

            if (sum >= target) {
                min = Math.min(min, i - windowStart + 1);
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
