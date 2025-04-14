package com.java.code.slidingwindow;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>Given a<span>&nbsp;</span><strong>sorted</strong><span>&nbsp;</span>integer array<span>&nbsp;</span><code>arr</code>, two integers<span>&nbsp;</span><code>k</code><span>&nbsp;</span>and<span>&nbsp;</span><code>x</code>, return the<span>&nbsp;</span><code>k</code><span>&nbsp;</span>closest integers to<span>&nbsp;</span><code>x</code><span>&nbsp;</span>in the array. The result should also be sorted in ascending order.</p>
 * <p>An integer<span>&nbsp;</span><code>a</code><span>&nbsp;</span>is closer to<span>&nbsp;</span><code>x</code><span>&nbsp;</span>than an integer<span>&nbsp;</span><code>b</code><span>&nbsp;</span>if:</p>
 * <ul>
 * <li><code>|a - x| &lt; |b - x|</code>, or</li>
 * <li><code>|a - x| == |b - x|</code><span>&nbsp;</span>and<span>&nbsp;</span><code>a &lt; b</code></li>
 * </ul>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input:</strong> arr = [1,2,3,4,5], k = 4, x = 3
 * <strong>Output:</strong> [1,2,3,4]
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre><strong>Input:</strong> arr = [1,2,3,4,5], k = 4, x = -1
 * <strong>Output:</strong> [1,2,3,4]
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;= k &lt;= arr.length</code></li>
 * <li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>arr</code><span>&nbsp;</span>is sorted in<span>&nbsp;</span><strong>ascending</strong><span>&nbsp;</span>order.</li>
 * <li><code>-10<sup>4</sup> &lt;= arr[i], x &lt;= 10<sup>4</sup></code></li>
 * </ul>
 */
public class FindKClosestElements extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {1,2,3,4,5}, 4, 3, List.of(1,2,3,4)},
                {new int[] {1,2,3,4,5}, 4, -1, List.of(1,2,3,4)},
                {new int[] {1,1,1,10,10,10}, 1, 9, List.of(10)},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] arr, int k, int x, List<Integer> expected) {
        softAssert.as(String.format("arr = %s, k = %d, x = %d", Arrays.toString(arr), k , x))
                  .assertThat(findClosestElements(arr, k, x))
                  .isEqualTo(expected);
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        Queue<Integer> list = new ArrayDeque<>();
        int windowStart = 0;
        for (int i = 0; i < arr.length; i++) {
            if (list.size() < k) {
                list.add(arr[i]);
            } else {
                if (Math.abs(arr[i] - x) < Math.abs(arr[windowStart] - x)) {
                    list.poll();
                    list.add(arr[i]);
                    windowStart++;
                }
            }
        }

        return new ArrayList<>(list);
    }
}
