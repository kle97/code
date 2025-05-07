package com.java.code.greedy;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>You are given an integer array <code>nums</code>. You are initially positioned at the array's <strong>first index</strong>, and each element in the array represents your maximum jump length at that position.</p>

 <p>Return <code>true</code><em> if you can reach the last index, or </em><code>false</code><em> otherwise</em>.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [2,3,1,1,4]
 <strong>Output:</strong> true
 <strong>Explanation:</strong> Jump 1 step from index 0 to 1, then 3 steps to the last index.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [3,2,1,0,4]
 <strong>Output:</strong> false
 <strong>Explanation:</strong> You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li> 
 <li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li> 
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Dynamic Programming</li><li>Greedy</li></div></div><br><div><li>👍 20481</li><li>👎 1384</li></div>
 */
public class JumpGame extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[]{2,3,1,1,4}, true},
                {new int[]{3,2,1,0,4}, false},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, boolean expected) {
        softAssert.as("nums = %s", Arrays.toString(nums))
                  .assertThat(canJump(nums))
                  .isEqualTo(expected);
    }

    public boolean canJump(int[] nums) {
        return canJump(nums, 0, new int[nums.length]);
    }

    public boolean canJump(int[] nums, int index, int[] memoized) {
        if (index == nums.length - 1) {
            return true;
        } else if (memoized[index] == -1) {
            return false;
        }
        for (int i = 1; i <= nums[index]; i++) {
            if (canJump(nums, index + i, memoized)) {
                return true;
            }
        }
        memoized[index] = -1;
        return false;
    }

//    public boolean canJump(int[] nums) {
//        int maxJump = nums[0];
//        for (int index = 1; index < nums.length; index++) {
//            int currentMaxJump = nums[index];
//            if (maxJump < index) {
//                return false;
//            }
//            if (currentMaxJump + index > maxJump) {
//                maxJump = currentMaxJump + index;
//            }
//        }
//        return true;
//    }
}
