package com.java.code.slidingwindow;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>Given an array of integers <code>arr</code> and two integers <code>k</code> and <code>threshold</code>, return <em>the number of sub-arrays of size </em><code>k</code><em> and average greater than or equal to </em><code>threshold</code>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
 <strong>Output:</strong> 3
 <strong>Explanation:</strong> Sub-arrays [2,5,5],[5,5,5] and [5,5,8] have averages 4, 5 and 6 respectively. All other sub-arrays of size 3 have averages less than 4 (the threshold).
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
 <strong>Output:</strong> 6
 <strong>Explanation:</strong> The first 6 sub-arrays of size 3 have averages greater than 5. Note that averages are not integers.
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= arr .length &lt;= 10<sup>5</sup></code></li>
 <li><code>1 &lt;= arr[i] &lt;= 10<sup>4</sup></code></li>
 <li><code>1 &lt;= k &lt;= arr .length</code></li>
 <li><code>0 &lt;= threshold &lt;= 10<sup>4</sup></code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Sliding Window</li></div></div><br><div><li>👍 1536</li><li>👎 99</li></div>
 */
public class NumberOfSubArraysOfSizeKAndAvgGreaterThanOrEqualToThreshold extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {2,2,2,2,5,5,5,8}, 3, 4, 3},
                {new int[] {11,13,17,23,29,31,7,5,2,3}, 3, 5, 6},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] arr, int k, int threshold, int expected) {
        softAssert.as(String.format("nums = %s, k = %d, threshold = %d", Arrays.toString(arr), k, threshold))
                  .assertThat(numOfSubarrays(arr, k, threshold))
                  .isEqualTo(expected);
    }

    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int count = 0;

        int lastWindowIndex = 0;
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        if (windowSum / k >= threshold) {
            count++;
        }

        for (int i = k; i < arr.length; i++) {
            windowSum -= arr[lastWindowIndex];
            windowSum += arr[i];
            if (windowSum / k >= threshold) {
                count++;
            }
            lastWindowIndex = lastWindowIndex + 1;
        }

        return count;
    }
}
