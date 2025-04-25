package com.java.code.array;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 <p>Given an integer array <code>nums</code>, return <code>true</code> if any value appears <strong>at least twice</strong> in the array, and return <code>false</code> if every element is distinct.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p> 
 <pre><strong>Input:</strong> nums = [1,2,3,1]
 <strong>Output:</strong> true
 </pre>
 <p><strong class="example">Example 2:</strong></p> 
 <pre><strong>Input:</strong> nums = [1,2,3,4]
 <strong>Output:</strong> false
 </pre>
 <p><strong class="example">Example 3:</strong></p> 
 <pre><strong>Input:</strong> nums = [1,1,1,3,3,4,3,2,4,2]
 <strong>Output:</strong> true
 </pre> 
 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= nums  .length &lt;= 10<sup>5</sup></code></li> 
 <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Hash Table</li><li>Sorting</li></div></div><br><div><li>👍 11448</li><li>👎 1270</li></div>
 */
public class ContainsDuplicate extends BaseTest {

    @DataProvider(name = "ContainsDuplicate")
    public Object[][] data() {
        return new Object[][] {
                {new int[]{1,2,3,1}, true},
                {new int[]{1,2,3,4}, false},
                {new int[]{1,1,1,3,3,4,3,2,4,2}, true},
        };
    }
    
    @Test(dataProvider = "ContainsDuplicate")
    public void test(int[] nums, boolean expected) {
        softAssert.as(Arrays.toString(nums))
                  .assertThat(containsDuplicate(nums))
                  .isEqualTo(expected);
    }
    
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }
        return false;
    }
}
