package com.java.code.dynamicprogramming;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>Given an integer array <code>nums</code>, return <code>true</code> <em>if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or </em><code>false</code><em> otherwise</em>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [1,5,11,5]
 <strong>Output:</strong> true
 <strong>Explanation:</strong> The array can be partitioned as [1, 5, 5] and [11].
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [1,2,3,5]
 <strong>Output:</strong> false
 <strong>Explanation:</strong> The array cannot be partitioned into equal sum subsets.
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= nums.length &lt;= 200</code></li>
 <li><code>1 &lt;= nums[i] &lt;= 100</code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Dynamic Programming</li></div></div><br><div><li>👍 13193</li><li>👎 279</li></div>
 */
public class PartitionEqualSubsetSum extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {1,5,11,5}, true},
                {new int[] {1,2,3,5}, false},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, boolean expected) {
        softAssert.as(String.format("nums = %s", Arrays.toString(nums)))
                  .assertThat(canPartition(nums))
                  .isEqualTo(expected);
    }

    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
        }
        return canPartition(nums, totalSum, 0, 0, new Boolean[nums.length][100 * nums.length]);
    }

    public boolean canPartition(int[] nums, int totalSum, int currentSum, int currentIndex, Boolean[][] memoized) {
        if (totalSum - currentSum == currentSum) {
            return true;
        }
        if (currentIndex == nums.length) {
            return false;
        }

        if (memoized[currentIndex][currentSum] == null) {
            boolean result = canPartition(nums, totalSum, currentSum + nums[currentIndex], currentIndex + 1, memoized);
            result = result || canPartition(nums, totalSum, currentSum, currentIndex + 1, memoized);
            memoized[currentIndex][currentSum] = result;
        }

        return memoized[currentIndex][currentSum];
    }
}
