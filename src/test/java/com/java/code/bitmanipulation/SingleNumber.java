package com.java.code.bitmanipulation;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>Given a <strong>non-empty</strong>&nbsp;array of integers <code>nums</code>, every element appears <em>twice</em> except for one. Find that single one.</p>

 <p>You must&nbsp;implement a solution with a linear runtime complexity and use&nbsp;only constant&nbsp;extra space.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <div class="example-block">
 <p><strong>Input:</strong> <span class="example-io">nums = [2,2,1]</span></p>
 </div>

 <p><strong>Output:</strong> <span class="example-io">1</span></p>

 <p><strong class="example">Example 2:</strong></p>

 <div class="example-block">
 <p><strong>Input:</strong> <span class="example-io">nums = [4,1,2,1,2]</span></p>
 </div>

 <p><strong>Output:</strong> <span class="example-io">4</span></p>

 <p><strong class="example">Example 3:</strong></p>

 <div class="example-block">
 <p><strong>Input:</strong> <span class="example-io">nums = [1]</span></p>
 </div>

 <p><strong>Output:</strong> <span class="example-io">1</span></p>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= nums .length &lt;= 3 * 10<sup>4</sup></code></li>
 <li><code>-3 * 10<sup>4</sup> &lt;= nums[i] &lt;= 3 * 10<sup>4</sup></code></li>
 <li>Each element in the array appears twice except for one element which appears only once.</li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Bit Manipulation</li></div></div><br><div><li>👍 17446</li><li>👎 800</li></div>
 */
public class SingleNumber extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[]{2, 2, 1}, 1},
                {new int[]{4, 1, 2, 1, 2}, 4},
                {new int[]{1}, 1},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, int expected) {
        softAssert.as(String.format("nums = %s", Arrays.toString(nums)))
                  .assertThat(singleNumber(nums))
                  .isEqualTo(expected);
    }

    public int singleNumber(int[] nums) {
        int xor = nums[0];
        for (int i = 1; i < nums.length; i++) {
            xor = xor ^ nums[i];
        }
        return xor;
    }
}
