package com.java.code.bitmanipulation;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>Given an array <code>nums</code> containing <code>n</code> distinct numbers in the range <code>[0, n]</code>, return <em>the only number in the range that is missing from the array.</em></p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p>

 <div class="example-block"> 
 <p><strong>Input:</strong> <span class="example-io">nums = [3,0,1]</span></p> 
 </div>

 <p><strong>Output:</strong> <span class="example-io">2</span></p>

 <p><strong>Explanation:</strong></p>

 <p><code>n = 3</code> since there are 3 numbers, so all numbers are in the range <code>[0,3]</code>. 2 is the missing number in the range since it does not appear in <code>nums</code>.</p>

 <p><strong class="example">Example 2:</strong></p>

 <div class="example-block"> 
 <p><strong>Input:</strong> <span class="example-io">nums = [0,1]</span></p> 
 </div>

 <p><strong>Output:</strong> <span class="example-io">2</span></p>

 <p><strong>Explanation:</strong></p>

 <p><code>n = 2</code> since there are 2 numbers, so all numbers are in the range <code>[0,2]</code>. 2 is the missing number in the range since it does not appear in <code>nums</code>.</p>

 <p><strong class="example">Example 3:</strong></p>

 <div class="example-block"> 
 <p><strong>Input:</strong> <span class="example-io">nums = [9,6,4,2,3,5,7,0,1]</span></p> 
 </div>

 <p><strong>Output:</strong> <span class="example-io">8</span></p>

 <p><strong>Explanation:</strong></p>

 <p><code>n = 9</code> since there are 9 numbers, so all numbers are in the range <code>[0,9]</code>. 8 is the missing number in the range since it does not appear in <code>nums</code>.</p>

 <div class="simple-translate-system-theme" id="simple-translate"> 
 <div> 
 <div class="simple-translate-button isShow" style="background-image: url(&quot;moz-extension://8a9ffb6b-7e69-4e93-aae1-436a1448eff6/icons/512.png&quot;); height: 22px; width: 22px; top: 318px; left: 36px;">
 &nbsp;
 </div> 
 </div>
 </div>

 <div class="simple-translate-panel " style="width: 300px; height: 200px; top: 0px; left: 0px; font-size: 13px;"> 
 <div class="simple-translate-result-wrapper" style="overflow: hidden;"> 
 <div class="simple-translate-move" draggable="true">
 &nbsp;
 </div> 
 </div>
 </div>

 <div class="simple-translate-result-contents"> 
 <p class="simple-translate-result" dir="auto">&nbsp;</p> 
 </div>

 <p class="simple-translate-candidate" dir="auto">&nbsp;</p>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>n == nums .length</code></li> 
 <li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li> 
 <li><code>0 &lt;= nums[i] &lt;= n</code></li> 
 <li>All the numbers of <code>nums</code> are <strong>unique</strong>.</li> 
 </ul>

 <p>&nbsp;</p> 
 <p><strong>Follow up:</strong> Could you implement a solution using only <code>O(1)</code> extra space complexity and <code>O(n)</code> runtime complexity?</p>

 <div><div>Related Topics</div><div><li>Array</li><li>Hash Table</li><li>Math</li><li>Binary Search</li><li>Bit Manipulation</li><li>Sorting</li></div></div><br><div><li>👍 13019</li><li>👎 3405</li></div>
 */
public class MissingNumber extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {3,0,1}, 2},
                {new int[] {0,1}, 2},
                {new int[] {9,6,4,2,3,5,7,0,1}, 8},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, int expected) {
        softAssert.as(String.format("nums = %s", Arrays.toString(nums)))
                  .assertThat(missingNumber(nums))
                  .isEqualTo(expected);
    }

    public int missingNumber(int[] nums) {
        int full = 0;
        for (int i = 0; i <= nums.length; i++) {
            full = full ^ i;
        }
        
        int partial = 0;
        for (int i = 0; i < nums.length; i++) {
            partial = partial ^ nums[i];
        }
        return full ^ partial;
    }
}
