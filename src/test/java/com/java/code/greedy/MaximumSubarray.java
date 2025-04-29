package com.java.code.greedy;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>Given an integer array <code>nums</code>, find the <span data-keyword="subarray-nonempty">subarray</span> with the largest sum, and return <em>its sum</em>.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [-2,1,-3,4,-1,2,1,-5,4]
 <strong>Output:</strong> 6
 <strong>Explanation:</strong> The subarray [4,-1,2,1] has the largest sum 6.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [1]
 <strong>Output:</strong> 1
 <strong>Explanation:</strong> The subarray [1] has the largest sum 1.
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [5,4,-1,7,8]
 <strong>Output:</strong> 23
 <strong>Explanation:</strong> The subarray [5,4,-1,7,8] has the largest sum 23.
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
 <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
 </ul>

 <p>&nbsp;</p> 
 <p><strong>Follow up:</strong> If you have figured out the <code>O(n)</code> solution, try coding another solution using the <strong>divide and conquer</strong> approach, which is more subtle.</p>

 <div><div>Related Topics</div><div><li>Array</li><li>Divide and Conquer</li><li>Dynamic Programming</li></div></div><br><div><li>👍 35542</li><li>👎 1501</li></div>
 */
public class MaximumSubarray extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[]{-2,1,-3,4,-1,2,1,-5,4}, 6},
                {new int[]{1}, 1},
                {new int[]{5,4,-1,7,8}, 23},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, int expected) {
        softAssert.as("nums = %s", Arrays.toString(nums))
                  .assertThat(maxSubArray(nums))
                  .isEqualTo(expected);
    }

    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}
