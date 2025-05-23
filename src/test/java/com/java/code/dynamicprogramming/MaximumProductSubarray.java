package com.java.code.dynamicprogramming;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 <p>Given an integer array <code>nums</code>, find a <span data-keyword="subarray-nonempty">subarray</span> that has the largest product, and return <em>the product</em>.</p>

 <p>The test cases are generated so that the answer will fit in a <strong>32-bit</strong> integer.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [2,3,-2,4]
 <strong>Output:</strong> 6
 <strong>Explanation:</strong> [2,3] has the largest product 6.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [-2,0,-1]
 <strong>Output:</strong> 0
 <strong>Explanation:</strong> The result cannot be 2, because [-2,-1] is not a subarray.
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
 <li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
 <li>The product of any subarray of <code>nums</code> is <strong>guaranteed</strong> to fit in a <strong>32-bit</strong> integer.</li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Dynamic Programming</li></div></div><br><div><li>👍 19363</li><li>👎 783</li></div>
 */
public class MaximumProductSubarray extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {2,3,-2,4}, 6},
                {new int[] {-2,0,-1}, 0},
                {new int[] {-2}, -2},
                {new int[] {-3,0,1,-2}, 1},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, int expected) {
        softAssert.as(String.format("nums = %s", Arrays.toString(nums)))
                  .assertThat(maxProduct(nums))
                  .isEqualTo(expected);
    }

    public int maxProduct(int[] nums) {
        return maxProduct(nums, nums[0], 1, new HashMap<>());
    }

    public int maxProduct(int[] nums, int currentProduct, int currentIndex, Map<String, Integer> memoized) {
        if (currentIndex == nums.length) {
            return currentProduct;
        }

        String key = String.valueOf(currentIndex) + currentProduct;
        if (!memoized.containsKey(key)) {
            currentProduct = Math.max(currentProduct, maxProduct(nums, currentProduct * nums[currentIndex], currentIndex + 1, memoized));
            currentProduct = Math.max(currentProduct, maxProduct(nums, nums[currentIndex], currentIndex + 1, memoized));
            memoized.put(key, currentProduct);
        }
        return memoized.get(key);
    }
}
