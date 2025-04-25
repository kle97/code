package com.java.code.slidingwindow;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>The <strong>frequency</strong> of an element is the number of times it occurs in an array.</p>

 <p>You are given an integer array <code>nums</code> and an integer <code>k</code>. In one operation, you can choose an index of <code>nums</code> and increment the element at that index by <code>1</code>.</p>

 <p>Return <em>the <strong>maximum possible frequency</strong> of an element after performing <strong>at most</strong> </em><code>k</code><em> operations</em>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [1,2,4], k = 5
 <strong>Output:</strong> 3<strong>
 Explanation:</strong> Increment the first element three times and the second element two times to make nums = [4,4,4].
 4 has a frequency of 3.</pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [1,4,8,13], k = 5
 <strong>Output:</strong> 2
 <strong>Explanation:</strong> There are multiple optimal solutions:
 - Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
 - Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
 - Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [3,9,6], k = 2
 <strong>Output:</strong> 1
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= nums .length &lt;= 10<sup>5</sup></code></li>
 <li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
 <li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Binary Search</li><li>Greedy</li><li>Sliding Window</li><li>Sorting</li><li>Prefix Sum</li></div></div><br><div><li>👍 4346</li><li>👎 171</li></div>
 */
public class FrequencyOfTheMostFrequentElement extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {1,2,4}, 5, 3},
                {new int[] {1,4,8,13}, 5, 2},
                {new int[] {3,9,6}, 1, 1},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, int k, int expected) {
        softAssert.as(String.format("nums = %s, k = %d", Arrays.toString(nums), k))
                  .assertThat(maxFrequency(nums, k))
                  .isEqualTo(expected);
    }

    public int maxFrequency(int[] nums, int k) {
        long longest = 1;

        Arrays.sort(nums);

        int windowFirst = 0;
        int windowLast = 0;
        long remainder = k;
        for (int i = 1; i < nums.length; i++) {
            long windowSize = windowLast - windowFirst + 1;
            long operationsNeeded = (nums[i] - nums[windowLast]) * windowSize;
            while (operationsNeeded > remainder) {
                remainder += nums[windowLast] - nums[windowFirst];
                windowFirst++;
                windowSize = windowLast - windowFirst + 1;
                operationsNeeded = (nums[i] - nums[windowLast]) * windowSize;
            }

            remainder -= operationsNeeded;
            longest = Math.max(longest, windowSize + 1);
            windowLast++;

        }
        return (int) longest;
    }
}
