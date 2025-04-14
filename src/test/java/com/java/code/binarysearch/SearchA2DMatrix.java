package com.java.code.binarysearch;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * <p>You are given an<span>&nbsp;</span><code>m x n</code><span>&nbsp;</span>integer matrix<span>&nbsp;</span><code>matrix</code><span>&nbsp;</span>with the following two properties:</p>
 * <ul>
 * <li>Each row is sorted in non-decreasing order.</li>
 * <li>The first integer of each row is greater than the last integer of the previous row.</li>
 * </ul>
 * <p>Given an integer<span>&nbsp;</span><code>target</code>, return<span>&nbsp;</span><code>true</code><span>&nbsp;</span><em>if</em><span>&nbsp;</span><code>target</code><span>&nbsp;</span><em>is in</em><span>&nbsp;</span><code>matrix</code><span>&nbsp;</span><em>or</em><span>&nbsp;</span><code>false</code><span>&nbsp;</span><em>otherwise</em>.</p>
 * <p>You must write a solution in<span>&nbsp;</span><code>O(log(m * n))</code><span>&nbsp;</span>time complexity.</p>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <p><img alt="" src="https://assets.leetcode.com/uploads/2020/10/05/mat.jpg" /></p>
 * <pre><strong>Input:</strong> matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * <strong>Output:</strong> true
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <p><img alt="" src="https://assets.leetcode.com/uploads/2020/10/05/mat2.jpg" /></p>
 * <pre><strong>Input:</strong> matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * <strong>Output:</strong> false
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>m == matrix.length</code></li>
 * <li><code>n == matrix[i].length</code></li>
 * <li><code>1 &lt;= m, n &lt;= 100</code></li>
 * <li><code>-10<sup>4</sup> &lt;= matrix[i][j], target &lt;= 10<sup>4</sup></code></li>
 * </ul>
 */
public class SearchA2DMatrix extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[][] {new int[] {1,3,5,7},
                              new int[] {10,11,16,20},
                              new int[] {23,30,34,60}},
                 3, true},

                {new int[][] {new int[] {1,3,5,7},
                              new int[] {10,11,16,20},
                              new int[] {23,30,34,60}},
                 13, false},

                {new int[][] {new int[] {1,3,5,7},
                              new int[] {10,11,16,20},
                              new int[] {23,30,34,60}},
                 60, true},

                {new int[][] {new int[] {1,3,5,7},
                              new int[] {10,11,16,20},
                              new int[] {23,30,34,60}},
                 7, true},

                {new int[][] {new int[] {1,3,5,7},
                              new int[] {10,11,16,20},
                              new int[] {23,30,34,60}},
                 120, false},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[][] matrix, int target, boolean expected) {
        softAssert.as(String.format("nums = %s, target = %d", Arrays.deepToString(matrix), target))
                  .assertThat(searchMatrix(matrix, target))
                  .isEqualTo(expected);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[0].length * matrix.length - 1;

        while (i <= j) {
            int mid = (j + i) / 2;
            int midRow = mid / matrix[0].length;
            int midCol = mid % matrix[0].length;
            if (matrix[midRow][midCol] == target) {
                return true;
            } else if (matrix[midRow][midCol] > target) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }

        return false;
    }
}
