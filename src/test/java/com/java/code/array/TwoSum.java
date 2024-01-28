package com.java.code.array;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 <p>Given an array of integers <code>nums</code>&nbsp;and an integer <code>target</code>, return <em>indices of the two numbers such that they add up to <code>target</code></em>.</p>

 <p>You may assume that each input would have <strong><em>exactly</em> one solution</strong>, and you may not use the <em>same</em> element twice.</p>

 <p>You can return the answer in any order.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [2,7,11,15], target = 9
 <strong>Output:</strong> [0,1]
 <strong>Explanation:</strong> Because nums[0] + nums[1] == 9, we return [0, 1].
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [3,2,4], target = 6
 <strong>Output:</strong> [1,2]
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [3,3], target = 6
 <strong>Output:</strong> [0,1]
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>2 &lt;= nums.length &lt;= 10<sup>4</sup></code></li> 
 <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
 <li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li> 
 <li><strong>Only one valid answer exists.</strong></li> 
 </ul>

 <p>&nbsp;</p> 
 <strong>Follow-up:&nbsp;</strong>Can you come up with an algorithm that is less than 
 <code>O(n<sup>2</sup>)</code>
 <font face="monospace">&nbsp;</font>time complexity?

 <div><div>Related Topics</div><div><li>Array</li><li>Hash Table</li></div></div><br><div><li>👍 54632</li><li>👎 1850</li></div>
 */
public class TwoSum extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[]{2,7,11,15}, 9, new int[]{0,1}},
                {new int[]{3,2,4}, 6, new int[]{1,2}},
                {new int[]{3,3}, 6, new int[]{0,1}},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, int target, int[] expected) {
        softAssert.as(Arrays.toString(nums))
                  .assertThat(twoSum(nums, target))
                  .isEqualTo(expected);
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int key = target - nums[i];
            if (map.containsKey(key)) {
                int value = map.get(key);
                if (map.get(key) != i) {
                    return new int[] {i, value};
                }
            }
        }
        
        return new int[0];
    }
}
