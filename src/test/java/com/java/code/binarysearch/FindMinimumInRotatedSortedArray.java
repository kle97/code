package com.java.code.binarysearch;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * <p>Suppose an array of length<span>&nbsp;</span><code>n</code><span>&nbsp;</span>sorted in ascending order is<span>&nbsp;</span><strong>rotated</strong><span>&nbsp;</span>between<span>&nbsp;</span><code>1</code><span>&nbsp;</span>and<span>&nbsp;</span><code>n</code><span>&nbsp;</span>times. For example, the array<span>&nbsp;</span><code>nums = [0,1,2,4,5,6,7]</code><span>&nbsp;</span>might become:</p>
 * <ul>
 * <li><code>[4,5,6,7,0,1,2]</code><span>&nbsp;</span>if it was rotated<span>&nbsp;</span><code>4</code><span>&nbsp;</span>times.</li>
 * <li><code>[0,1,2,4,5,6,7]</code><span>&nbsp;</span>if it was rotated<span>&nbsp;</span><code>7</code><span>&nbsp;</span>times.</li>
 * </ul>
 * <p>Notice that<span>&nbsp;</span><strong>rotating</strong><span>&nbsp;</span>an array<span>&nbsp;</span><code>[a[0], a[1], a[2], ..., a[n-1]]</code><span>&nbsp;</span>1 time results in the array<span>&nbsp;</span><code>[a[n-1], a[0], a[1], a[2], ..., a[n-2]]</code>.</p>
 * <p>Given the sorted rotated array<span>&nbsp;</span><code>nums</code><span>&nbsp;</span>of<span>&nbsp;</span><strong>unique</strong><span>&nbsp;</span>elements, return<span>&nbsp;</span><em>the minimum element of this array</em>.</p>
 * <p>You must write an algorithm that runs in&nbsp;<code>O(log n) time.</code></p>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input:</strong> nums = [3,4,5,1,2]
 * <strong>Output:</strong> 1
 * <strong>Explanation:</strong> The original array was [1,2,3,4,5] rotated 3 times.
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre><strong>Input:</strong> nums = [4,5,6,7,0,1,2]
 * <strong>Output:</strong> 0
 * <strong>Explanation:</strong> The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 * </pre>
 * <p><strong class="example">Example 3:</strong></p>
 * <pre><strong>Input:</strong> nums = [11,13,15,17]
 * <strong>Output:</strong> 11
 * <strong>Explanation:</strong> The original array was [11,13,15,17] and it was rotated 4 times.
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>n == nums.length</code></li>
 * <li><code>1 &lt;= n &lt;= 5000</code></li>
 * <li><code>-5000 &lt;= nums[i] &lt;= 5000</code></li>
 * <li>All the integers of<span>&nbsp;</span><code>nums</code><span>&nbsp;</span>are<span>&nbsp;</span><strong>unique</strong>.</li>
 * <li><code>nums</code><span>&nbsp;</span>is sorted and rotated between<span>&nbsp;</span><code>1</code><span>&nbsp;</span>and<span>&nbsp;</span><code>n</code><span>&nbsp;</span>times.</li>
 * </ul>
 */
public class FindMinimumInRotatedSortedArray extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {3,4,5,1,2}, 1},
                {new int[] {4,5,6,7,0,1,2}, 0},
                {new int[] {11,13,15,17}, 11},
                {new int[] {3, 1, 2}, 1},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, int expected) {
        softAssert.as(String.format("nums = %s", Arrays.toString(nums)))
                  .assertThat(findMin(nums))
                  .isEqualTo(expected);
    }

    public int findMin(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int mid = (i + j) / 2;
            if (nums[j] <= nums[i]) {
                if (nums[j] <= nums[mid]) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            } else {
                if (nums[i] <= nums[mid]) {
                    j = mid - 1;
                } else {
                    i = mid;
                }
            }
        }

        return nums[i];
    }
}
