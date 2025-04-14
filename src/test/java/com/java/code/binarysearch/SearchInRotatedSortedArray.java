package com.java.code.binarysearch;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * <p>There is an integer array<span>&nbsp;</span><code>nums</code><span>&nbsp;</span>sorted in ascending order (with<span>&nbsp;</span><strong>distinct</strong><span>&nbsp;</span>values).</p>
 * <p>Prior to being passed to your function,<span>&nbsp;</span><code>nums</code><span>&nbsp;</span>is<span>&nbsp;</span><strong>possibly rotated</strong><span>&nbsp;</span>at an unknown pivot index<span>&nbsp;</span><code>k</code><span>&nbsp;</span>(<code>1 &lt;= k &lt; nums.length</code>) such that the resulting array is<span>&nbsp;</span><code>[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]</code><span>&nbsp;</span>(<strong>0-indexed</strong>). For example,<span>&nbsp;</span><code>[0,1,2,4,5,6,7]</code><span>&nbsp;</span>might be rotated at pivot index<span>&nbsp;</span><code>3</code><span>&nbsp;</span>and become<span>&nbsp;</span><code>[4,5,6,7,0,1,2]</code>.</p>
 * <p>Given the array<span>&nbsp;</span><code>nums</code><span>&nbsp;</span><strong>after</strong><span>&nbsp;</span>the possible rotation and an integer<span>&nbsp;</span><code>target</code>, return<span>&nbsp;</span><em>the index of<span>&nbsp;</span></em><code>target</code><em><span>&nbsp;</span>if it is in<span>&nbsp;</span></em><code>nums</code><em>, or<span>&nbsp;</span></em><code>-1</code><em><span>&nbsp;</span>if it is not in<span>&nbsp;</span></em><code>nums</code>.</p>
 * <p>You must write an algorithm with<span>&nbsp;</span><code>O(log n)</code><span>&nbsp;</span>runtime complexity.</p>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input:</strong> nums = [4,5,6,7,0,1,2], target = 0
 * <strong>Output:</strong> 4
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre><strong>Input:</strong> nums = [4,5,6,7,0,1,2], target = 3
 * <strong>Output:</strong> -1
 * </pre>
 * <p><strong class="example">Example 3:</strong></p>
 * <pre><strong>Input:</strong> nums = [1], target = 0
 * <strong>Output:</strong> -1
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 5000</code></li>
 * <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
 * <li>All values of<span>&nbsp;</span><code>nums</code><span>&nbsp;</span>are<span>&nbsp;</span><strong>unique</strong>.</li>
 * <li><code>nums</code><span>&nbsp;</span>is an ascending array that is possibly rotated.</li>
 * <li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
 * </ul>
 */
public class SearchInRotatedSortedArray extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {4,5,6,7,0,1,2}, 0, 4},
                {new int[] {4,5,6,7,0,1,2}, 3, -1},
                {new int[] {1}, 0, -1},
                {new int[] {5,1,3}, 5, 0},
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
            if (nums[mid] == target) {
                return mid;
            } else if (nums[i] <= nums[mid]) {
                if (nums[i] <= target && target < nums[mid]) {
                    j = mid - 1;
                } else {
                    i = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[j]) {
                    i = mid + 1;
                } else {
                    j = mid - 1;
                }
            }
        }

        return -1;
    }
}
