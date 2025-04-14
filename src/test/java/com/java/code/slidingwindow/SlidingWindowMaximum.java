package com.java.code.slidingwindow;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * <p>You are given an array of integers&nbsp;<code>nums</code>, there is a sliding window of size<span>&nbsp;</span><code>k</code><span>&nbsp;</span>which is moving from the very left of the array to the very right. You can only see the<span>&nbsp;</span><code>k</code><span>&nbsp;</span>numbers in the window. Each time the sliding window moves right by one position.</p>
 * <p>Return<span>&nbsp;</span><em>the max sliding window</em>.</p>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input:</strong> nums = [1,3,-1,-3,5,3,6,7], k = 3
 * <strong>Output:</strong> [3,3,5,5,6,7]
 * <strong>Explanation:</strong>
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       <strong>3</strong>
 *  1 [3  -1  -3] 5  3  6  7       <strong>3</strong>
 *  1  3 [-1  -3  5] 3  6  7      <strong> 5</strong>
 *  1  3  -1 [-3  5  3] 6  7       <strong>5</strong>
 *  1  3  -1  -3 [5  3  6] 7       <strong>6</strong>
 *  1  3  -1  -3  5 [3  6  7]      <strong>7</strong>
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre><strong>Input:</strong> nums = [1], k = 1
 * <strong>Output:</strong> [1]
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
 * <li><code>1 &lt;= k &lt;= nums.length</code></li>
 * </ul>
 */
public class SlidingWindowMaximum extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {1,3,-1,-3,5,3,6,7}, 3, new int[]{3,3,5,5,6,7}},
                {new int[] {1}, 1, new int[]{1}},
                {new int[] {1,3,1,2,0,5}, 3, new int[]{3,3,2,5}},
                {new int[] {1,-1}, 1, new int[]{1, -1}},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, int k, int[] expected) {
        softAssert.as(String.format("nums = %s, k = %d", Arrays.toString(nums), k))
                  .assertThat(maxSlidingWindow(nums, k))
                  .isEqualTo(expected);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] maxWindow = new int[nums.length - k + 1];

        int count = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> nums[b] - nums[a]);
        for (int i = 0; i < k; i++) {
            pq.offer(i);
        }
        maxWindow[count] = nums[pq.peek()];
        count++;

        int windowStart = 0;
        for (int i = k; i < nums.length; i++) {
            int top = pq.peek();
            while (top <= windowStart) {
                pq.poll();
                if (pq.isEmpty()) {
                    break;
                }
                top = pq.peek();
            }


            pq.offer(i);
            maxWindow[count] = nums[pq.peek()];
            count++;
            windowStart++;
        }

        return maxWindow;
    }
}
