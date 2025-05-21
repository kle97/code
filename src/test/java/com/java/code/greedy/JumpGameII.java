package com.java.code.greedy;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>You are given a <strong>0-indexed</strong> array of integers <code>nums</code> of length <code>n</code>. You are initially positioned at <code>nums[0]</code>.</p>

 <p>Each element <code>nums[i]</code> represents the maximum length of a forward jump from index <code>i</code>. In other words, if you are at <code>nums[i]</code>, you can jump to any <code>nums[i + j]</code> where:</p>

 <ul>
 <li><code>0 &lt;= j &lt;= nums[i]</code> and</li>
 <li><code>i + j &lt; n</code></li>
 </ul>

 <p>Return <em>the minimum number of jumps to reach </em><code>nums[n - 1]</code>. The test cases are generated such that you can reach <code>nums[n - 1]</code>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [2,3,1,1,4]
 <strong>Output:</strong> 2
 <strong>Explanation:</strong> The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [2,3,0,1,4]
 <strong>Output:</strong> 2
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
 <li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
 <li>It's guaranteed that you can reach <code>nums[n - 1]</code>.</li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Dynamic Programming</li><li>Greedy</li></div></div><br><div><li>👍 15461</li><li>👎 652</li></div>
 */
public class JumpGameII extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[]{2,3,1,1,4}, 2},
                {new int[]{2,3,0,1,4}, 2},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, int expected) {
        softAssert.as("nums = %s", Arrays.toString(nums))
                  .assertThat(jump(nums))
                  .isEqualTo(expected);
    }

    public int jump(int[] nums) {
        return jump(nums, 0, new Integer[nums.length]);
    }

    public int jump(int[] nums, int currentIndex, Integer[] memoization) {
        if (currentIndex == nums.length - 1) {
            return 0;
        }

        int minSteps = Integer.MAX_VALUE - 1;
        for (int i = 1; i <= nums[currentIndex]; i++) {
            if (currentIndex + i <= nums.length - 1) {
                if (memoization[currentIndex + i] == null) {
                    memoization[currentIndex + i] = 1 + jump(nums, currentIndex + i, memoization);
                }
                minSteps = Math.min(minSteps, memoization[currentIndex + i]);
            }
        }
        return minSteps;
    }
}
