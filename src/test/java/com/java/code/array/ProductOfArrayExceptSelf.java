package com.java.code.array;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>Given an integer array <code>nums</code>, return <em>an array</em> <code>answer</code> <em>such that</em> <code>answer[i]</code> <em>is equal to the product of all the elements of</em> <code>nums</code> <em>except</em> <code>nums[i]</code>.</p>

 <p>The product of any prefix or suffix of <code>nums</code> is <strong>guaranteed</strong> to fit in a <strong>32-bit</strong> integer.</p>

 <p>You must write an algorithm that runs in&nbsp;<code>O(n)</code>&nbsp;time and without using the division operation.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p> 
 <pre><strong>Input:</strong> nums = [1,2,3,4]
 <strong>Output:</strong> [24,12,8,6]
 </pre>
 <p><strong class="example">Example 2:</strong></p> 
 <pre><strong>Input:</strong> nums = [-1,1,0,-3,3]
 <strong>Output:</strong> [0,0,9,0,0]
 </pre> 
 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
 <li><code>-30 &lt;= nums[i] &lt;= 30</code></li> 
 <li>The product of any prefix or suffix of <code>nums</code> is <strong>guaranteed</strong> to fit in a <strong>32-bit</strong> integer.</li> 
 </ul>

 <p>&nbsp;</p> 
 <p><strong>Follow up:</strong>&nbsp;Can you solve the problem in <code>O(1)</code>&nbsp;extra&nbsp;space complexity? (The output array <strong>does not</strong> count as extra space for space complexity analysis.)</p>

 <div><div>Related Topics</div><div><li>Array</li><li>Prefix Sum</li></div></div><br><div><li>👍 21255</li><li>👎 1271</li></div>
 */
public class ProductOfArrayExceptSelf extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][] {
                {new int[] {1,2,3,4}, new int[] {24,12,8,6}},
                {new int[] {-1,1,0,-3,3}, new int[] {0,0,9,0,0}},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, int[] expected) {
        softAssert.as(String.format("nums = %s", Arrays.toString(nums)))
                  .assertThat(productExceptSelf(nums))
                  .isEqualTo(expected);
    }

    public int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];
        int[] forwardProducts = new int[nums.length];
        int[] backwardProducts = new int[nums.length];

        int product = 1;
        for (int i = 0; i < nums.length; i++) {
            product *= nums[i];
            forwardProducts[i] = product;
        }
        product = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            product *= nums[i];
            backwardProducts[i] = product;
        }

        answer[0] = backwardProducts[1];
        answer[answer.length - 1] = forwardProducts[answer.length - 2];
        for (int i = 1; i < answer.length - 1; i++) {
            answer[i] = backwardProducts[i + 1] * forwardProducts[i - 1];
        }
        return answer;
    }
}
