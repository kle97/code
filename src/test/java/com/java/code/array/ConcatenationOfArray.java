package com.java.code.array;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>Given an integer array <code>nums</code> of length <code>n</code>, you want to create an array <code>ans</code> of length <code>2n</code> where <code>ans[i] == nums[i]</code> and <code>ans[i + n] == nums[i]</code> for <code>0 &lt;= i &lt; n</code> (<strong>0-indexed</strong>).</p>

 <p>Specifically, <code>ans</code> is the <strong>concatenation</strong> of two <code>nums</code> arrays.</p>

 <p>Return <em>the array </em><code>ans</code>.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [1,2,1]
 <strong>Output:</strong> [1,2,1,1,2,1]
 <strong>Explanation:</strong> The array ans is formed as follows:
 - ans = [nums[0],nums[1],nums[2],nums[0],nums[1],nums[2]]
 - ans = [1,2,1,1,2,1]</pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [1,3,2,1]
 <strong>Output:</strong> [1,3,2,1,1,3,2,1]
 <strong>Explanation:</strong> The array ans is formed as follows:
 - ans = [nums[0],nums[1],nums[2],nums[3],nums[0],nums[1],nums[2],nums[3]]
 - ans = [1,3,2,1,1,3,2,1]
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>n == nums  .length</code></li> 
 <li><code>1 &lt;= n &lt;= 1000</code></li> 
 <li><code>1 &lt;= nums[i] &lt;= 1000</code></li> 
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Simulation</li></div></div><br><div><li>👍 3088</li><li>👎 351</li></div>
 */
public class ConcatenationOfArray extends BaseTest {

    @DataProvider(name = "ConcatenationOfArray")
    public Object[][] data() {
        return new Object[][]{
                {new int[]{1, 2, 1}, new int[]{1, 2, 1, 1, 2, 1}},
                {new int[]{1, 3, 2, 1}, new int[]{1, 3, 2, 1, 1, 3, 2, 1}},
        };
    }

    @Test(dataProvider = "ConcatenationOfArray")
    public void test(int[] nums, int[] expected) {
        softAssert.as(Arrays.toString(nums))
                  .assertThat(getConcatenation(nums))
                  .isEqualTo(expected);
    }

    public int[] getConcatenation(int[] nums) {
        int[] ans = new int[nums.length * 2];

        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i];
        }

        for (int i = nums.length; i < nums.length * 2; i++) {
            ans[i] = nums[i - nums.length];
        }

        return ans;
    }
}
