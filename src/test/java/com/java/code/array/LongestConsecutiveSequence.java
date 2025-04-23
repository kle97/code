package com.java.code.array;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 <p>Given an unsorted array of integers <code>nums</code>, return <em>the length of the longest consecutive elements sequence.</em></p>

 <p>You must write an algorithm that runs in&nbsp;<code>O(n)</code>&nbsp;time.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [100,4,200,1,3,2]
 <strong>Output:</strong> 4
 <strong>Explanation:</strong> The longest consecutive elements sequence is <span><code>[1, 2, 3, 4]</code></span>. Therefore its length is 4.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [0,3,7,2,5,8,4,6,0,1]
 <strong>Output:</strong> 9
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>0 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
 <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Hash Table</li><li>Union Find</li></div></div><br><div><li>👍 19203</li><li>👎 917</li></div>
 */
public class LongestConsecutiveSequence extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {100,4,200,1,3,2}, 4},
                {new int[] {0,3,7,2,5,8,4,6,0,1}, 9},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, int expected) {
        softAssert.as(String.format("nums = %s", Arrays.toString(nums)))
                  .assertThat(longestConsecutive(nums))
                  .isEqualTo(expected);
    }

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int maxStreak = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int streak = 1;
                int nextNum = num + 1;
                while (set.contains(nextNum)) {
                    streak++;
                    nextNum++;
                }
                maxStreak = Math.max(maxStreak, streak);
            }

        }
        return maxStreak;
    }
}
