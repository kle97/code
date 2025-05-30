package com.java.code.twopointers;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>, where <code>nums[i]</code> represents the score of the <code>i<sup>th</sup></code> student. You are also given an integer <code>k</code>.</p>

 <p>Pick the scores of any <code>k</code> students from the array so that the <strong>difference</strong> between the <strong>highest</strong> and the <strong>lowest</strong> of the <code>k</code> scores is <strong>minimized</strong>.</p>

 <p>Return <em>the <strong>minimum</strong> possible difference</em>.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [90], k = 1
 <strong>Output:</strong> 0
 <strong>Explanation:</strong> There is one way to pick score(s) of one student:
 - [<strong><u>90</u></strong>]. The difference between the highest and lowest score is 90 - 90 = 0.
 The minimum possible difference is 0.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> nums = [9,4,1,7], k = 2
 <strong>Output:</strong> 2
 <strong>Explanation:</strong> There are six ways to pick score(s) of two students:
 - [<strong><u>9</u></strong>,<strong><u>4</u></strong>,1,7]. The difference between the highest and lowest score is 9 - 4 = 5.
 - [<strong><u>9</u></strong>,4,<strong><u>1</u></strong>,7]. The difference between the highest and lowest score is 9 - 1 = 8.
 - [<strong><u>9</u></strong>,4,1,<strong><u>7</u></strong>]. The difference between the highest and lowest score is 9 - 7 = 2.
 - [9,<strong><u>4</u></strong>,<strong><u>1</u></strong>,7]. The difference between the highest and lowest score is 4 - 1 = 3.
 - [9,<strong><u>4</u></strong>,1,<strong><u>7</u></strong>]. The difference between the highest and lowest score is 7 - 4 = 3.
 - [9,4,<strong><u>1</u></strong>,<strong><u>7</u></strong>]. The difference between the highest and lowest score is 7 - 1 = 6.
 The minimum possible difference is 2.</pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= k &lt;= nums .length &lt;= 1000</code></li> 
 <li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li> 
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Sliding Window</li><li>Sorting</li></div></div><br><div><li>👍 945</li><li>👎 248</li></div>
 */
public class MinimumDifferenceBetweenHighestAndLowestOfKScores extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {90}, 1, 0},
                {new int[] {9,4,1,7}, 2, 2},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, int k, int expected) {
        softAssert.as(String.format("nums = %s, k = %d", Arrays.toString(nums), k))
                  .assertThat(minimumDifference(nums, k))
                  .isEqualTo(expected);
    }
    
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int minimumDifference = nums[k- 1] - nums[0];
        for (int i = k; i < nums.length; i++) {
            int difference = nums[i] - nums[i - k + 1];
            minimumDifference = Math.min(minimumDifference, difference);
        }
        
        return minimumDifference;
    }
}
