package com.java.code.dynamicprogramming;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>Given an integer array <code>nums</code>, return <em>the length of the longest <strong>strictly increasing </strong></em><span data-keyword="subsequence-array"><em><strong>subsequence</strong></em></span>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [10,9,2,5,3,7,101,18]
 <strong>Output:</strong> 4
 <strong>Explanation:</strong> The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [0,1,0,3,2,3]
 <strong>Output:</strong> 4
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [7,7,7,7,7,7,7]
 <strong>Output:</strong> 1
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= nums.length &lt;= 2500</code></li>
 <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
 </ul>

 <p>&nbsp;</p>
 <p><b>Follow up:</b>&nbsp;Can you come up with an algorithm that runs in&nbsp;<code>O(n log(n))</code> time complexity?</p>

 <div><div>Related Topics</div><div><li>Array</li><li>Binary Search</li><li>Dynamic Programming</li></div></div><br><div><li>👍 21774</li><li>👎 481</li></div>
 */
public class LongestIncreasingSubsequence extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {10,9,2,5,3,7,101,18}, 4},
                {new int[] {0,1,0,3,2,3}, 4},
                {new int[] {7,7,7,7,7,7,7}, 1},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, int expected) {
        softAssert.as(String.format("nums = %s", Arrays.toString(nums)))
                  .assertThat(lengthOfLIS(nums))
                  .isEqualTo(expected);
    }

    public int lengthOfLIS(int[] nums) {
        return lengthOfLIS(nums, nums.length, 0, new Integer[nums.length][nums.length + 1]);
    }

    public int lengthOfLIS(int[] nums, int lastNumberIndex, int currentIndex, Integer[][] memoized) {
        if (currentIndex == nums.length) {
            return 0;
        }

        if (memoized[currentIndex][lastNumberIndex] == null) {
            int maxLength;
            if (lastNumberIndex == nums.length || nums[currentIndex] > nums[lastNumberIndex]) {
                maxLength = Math.max(lengthOfLIS(nums, lastNumberIndex, currentIndex + 1, memoized),
                                     1 + lengthOfLIS(nums, currentIndex, currentIndex + 1, memoized));
            } else {
                maxLength = lengthOfLIS(nums, lastNumberIndex, currentIndex + 1, memoized);
            }
            memoized[currentIndex][lastNumberIndex] = maxLength;
        }

        return memoized[currentIndex][lastNumberIndex];
    }
}
