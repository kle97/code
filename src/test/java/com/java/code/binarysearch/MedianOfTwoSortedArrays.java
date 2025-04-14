package com.java.code.binarysearch;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * <p>Given two sorted arrays<span>&nbsp;</span><code>nums1</code><span>&nbsp;</span>and<span>&nbsp;</span><code>nums2</code><span>&nbsp;</span>of size<span>&nbsp;</span><code>m</code><span>&nbsp;</span>and<span>&nbsp;</span><code>n</code><span>&nbsp;</span>respectively, return<span>&nbsp;</span><strong>the median</strong><span>&nbsp;</span>of the two sorted arrays.</p>
 * <p>The overall run time complexity should be<span>&nbsp;</span><code>O(log (m+n))</code>.</p>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input:</strong> nums1 = [1,3], nums2 = [2]
 * <strong>Output:</strong> 2.00000
 * <strong>Explanation:</strong> merged array = [1,2,3] and median is 2.
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre><strong>Input:</strong> nums1 = [1,2], nums2 = [3,4]
 * <strong>Output:</strong> 2.50000
 * <strong>Explanation:</strong> merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>nums1.length == m</code></li>
 * <li><code>nums2.length == n</code></li>
 * <li><code>0 &lt;= m &lt;= 1000</code></li>
 * <li><code>0 &lt;= n &lt;= 1000</code></li>
 * <li><code>1 &lt;= m + n &lt;= 2000</code></li>
 * <li><code>-10<sup>6</sup> &lt;= nums1[i], nums2[i] &lt;= 10<sup>6</sup></code></li>
 * </ul>
 */
public class MedianOfTwoSortedArrays extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {1,3}, new int[] {2}, 2.00000},
                {new int[] {1,2}, new int[] {3,4}, 2.50000},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums1, int[] nums2, double expected) {
        softAssert.as(String.format("nums1 = %s, nums2 = %s", Arrays.toString(nums1), Arrays.toString(nums2)))
                  .assertThat(findMedianSortedArrays(nums1, nums2))
                  .isEqualTo(expected);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            nums[i] = nums1[i];
        }

        int j = 0;
        for (int i = nums1.length; i < nums.length; i++) {
            nums[i] = nums2[j];
            j++;
        }

        Arrays.sort(nums);
        int mid = (nums.length - 1) / 2;
        return nums.length % 2 == 0 ? (double) (nums[mid] + nums[mid + 1]) / 2 : nums[mid];
    }
}
