package com.java.code.slidingwindow;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

/**
 <p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <code>true</code> <em>if there are two <strong>distinct indices</strong> </em><code>i</code><em> and </em><code>j</code><em> in the array such that </em><code>nums[i] == nums[j]</code><em> and </em><code>abs(i - j) &lt;= k</code>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [1,2,3,1], k = 3
 <strong>Output:</strong> true
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [1,0,1,1], k = 1
 <strong>Output:</strong> true
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [1,2,3,1,2,3], k = 2
 <strong>Output:</strong> false
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
 <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
 <li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Hash Table</li><li>Sliding Window</li></div></div><br><div><li>👍 5921</li><li>👎 3021</li></div>
 */
public class ContainsDuplicateII extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {1,2,3,1}, 3, true},
                {new int[] {1,0,1,1}, 1, true},
                {new int[] {1,2,3,1,2,3}, 2, false},
                {new int[] {0,1,2,3,4,0,0,7,8,9,10,11,12,0}, 1, true},
                {new int[] {99, 99}, 2, true},
                {new int[] {1}, 1, false},
                {new int[] {4,1,2,3,1,5}, 3, true},
                {new int[] {1,2,3,1,2,3}, 2, false},
                {new int[] {1,2,3,4,5,6,7,8,9,9}, 3, true},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, int k, boolean expected) {
        softAssert.as(String.format("nums = %s, k = %d", Arrays.toString(nums), k))
                  .assertThat(containsNearbyDuplicate(nums, k))
                  .isEqualTo(expected);
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int j = k;
        int length = Math.min(j, nums.length - 1);
        for (int i = 0; i <= length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && map.get(nums[i]) > i) {
                return true;
            }

            j++;
            if (j < nums.length) {
                map.put(nums[j], j);
            }
        }

        return false;
    }
}
