package com.java.code.dynamicprogramming;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are <strong>arranged in a circle.</strong> That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and&nbsp;<b>it will automatically contact the police if two adjacent houses were broken into on the same night</b>.</p>

 <p>Given an integer array <code>nums</code> representing the amount of money of each house, return <em>the maximum amount of money you can rob tonight <strong>without alerting the police</strong></em>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [2,3,2]
 <strong>Output:</strong> 3
 <strong>Explanation:</strong> You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [1,2,3,1]
 <strong>Output:</strong> 4
 <strong>Explanation:</strong> Rob house 1 (money = 1) and then rob house 3 (money = 3).
 Total amount you can rob = 1 + 3 = 4.
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [1,2,3]
 <strong>Output:</strong> 3
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= nums.length &lt;= 100</code></li>
 <li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Dynamic Programming</li></div></div><br><div><li>👍 10424</li><li>👎 170</li></div>
 */
public class HouseRobberII extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[]{2,3,2}, 3},
                {new int[]{1,2,3,1}, 4},
                {new int[]{1,2,3}, 3},
                {new int[]{1,2,1,1}, 3},
                {new int[]{0}, 0},
                {new int[]{1,2}, 2},
                {new int[]{3,2}, 3},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, int expected) {
        softAssert.as("nums = %s", Arrays.toString(nums))
                  .assertThat(rob(nums))
                  .isEqualTo(expected);
    }

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Integer[][] memoized = new Integer[nums.length][nums.length];
        int robCurrent = rob(nums, -1, 0, memoized);
        int robNext = rob(nums, -1, 1, memoized);
        return Math.max(robCurrent, robNext);
    }

    public int rob(int[] nums, int startIndex, int currentIndex, Integer[][] memoized) {
        if (currentIndex == startIndex) {
            return 0;
        }
        startIndex = startIndex == -1 ? currentIndex : startIndex;
        int nextIndex = currentIndex + 1 >= nums.length ? 0 : currentIndex + 1;
        int nextNextIndex = currentIndex + 1 >= nums.length
                ? 1
                : currentIndex + 2 >= nums.length ? 0 : currentIndex + 2;
        if (nextIndex == startIndex) {
            return 0;
        }
        if (nextNextIndex == startIndex) {
            return nums[currentIndex];
        }

        if (memoized[startIndex][currentIndex] == null) {
            int robCurrent = nums[currentIndex] + rob(nums, startIndex, nextNextIndex, memoized);
            int robNext = rob(nums, startIndex, nextIndex, memoized);
            memoized[startIndex][currentIndex] = Math.max(robCurrent, robNext);
        }
        return memoized[startIndex][currentIndex];
    }
}
