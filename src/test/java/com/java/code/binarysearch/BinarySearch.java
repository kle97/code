package com.java.code.binarysearch;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * <p>Given an array of integers<span>&nbsp;</span><code>nums</code><span>&nbsp;</span>which is sorted in ascending order, and an integer<span>&nbsp;</span><code>target</code>, write a function to search<span>&nbsp;</span><code>target</code><span>&nbsp;</span>in<span>&nbsp;</span><code>nums</code>. If<span>&nbsp;</span><code>target</code><span>&nbsp;</span>exists, then return its index. Otherwise, return<span>&nbsp;</span><code>-1</code>.</p>
 * <p>You must write an algorithm with<span>&nbsp;</span><code>O(log n)</code><span>&nbsp;</span>runtime complexity.</p>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input:</strong> nums = [-1,0,3,5,9,12], target = 9
 * <strong>Output:</strong> 4
 * <strong>Explanation:</strong> 9 exists in nums and its index is 4
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre><strong>Input:</strong> nums = [-1,0,3,5,9,12], target = 2
 * <strong>Output:</strong> -1
 * <strong>Explanation:</strong> 2 does not exist in nums so return -1
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;= nums .length &lt;= 10<sup>4</sup></code></li>
 * <li><code>-10<sup>4</sup> &lt; nums[i], target &lt; 10<sup>4</sup></code></li>
 * <li>All the integers in<span>&nbsp;</span><code>nums</code><span>&nbsp;</span>are<span>&nbsp;</span><strong>unique</strong>.</li>
 * <li><code>nums</code><span>&nbsp;</span>is sorted in ascending order.</li>
 * </ul>
 */
public class BinarySearch extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {-1,0,3,5,9,12}, 9, 4},
                {new int[] {-1,0,3,5,9,12}, 2, -1},
                {new int[] {5}, -5, -1},
                {new int[] {5}, 5, 0},
                {new int[] {-1,0,3,5,9,12}, 3, 2},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, int target, int expected) {
        softAssert.as(String.format("nums = %s, target = %d", Arrays.toString(nums), target))
                  .assertThat(search(nums, target))
                  .isEqualTo(expected);
    }

    public int search(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (nums[mid] > target) {
                j = mid - 1;
            } else if (nums[mid] < target) {
                i = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
