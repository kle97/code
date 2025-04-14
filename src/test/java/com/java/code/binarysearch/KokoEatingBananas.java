package com.java.code.binarysearch;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * <p>Koko loves to eat bananas. There are<span>&nbsp;</span><code>n</code><span>&nbsp;</span>piles of bananas, the<span>&nbsp;</span><code>i<sup>th</sup></code><span>&nbsp;</span>pile has<span>&nbsp;</span><code>piles[i]</code><span>&nbsp;</span>bananas. The guards have gone and will come back in<span>&nbsp;</span><code>h</code><span>&nbsp;</span>hours.</p>
 * <p>Koko can decide her bananas-per-hour eating speed of<span>&nbsp;</span><code>k</code>. Each hour, she chooses some pile of bananas and eats<span>&nbsp;</span><code>k</code><span>&nbsp;</span>bananas from that pile. If the pile has less than<span>&nbsp;</span><code>k</code><span>&nbsp;</span>bananas, she eats all of them instead and will not eat any more bananas during this hour.</p>
 * <p>Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.</p>
 * <p>Return<span>&nbsp;</span><em>the minimum integer</em><span>&nbsp;</span><code>k</code><span>&nbsp;</span><em>such that she can eat all the bananas within</em><span>&nbsp;</span><code>h</code><span>&nbsp;</span><em>hours</em>.</p>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input:</strong> piles = [3,6,7,11], h = 8
 * <strong>Output:</strong> 4
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre><strong>Input:</strong> piles = [30,11,23,4,20], h = 5
 * <strong>Output:</strong> 30
 * </pre>
 * <p><strong class="example">Example 3:</strong></p>
 * <pre><strong>Input:</strong> piles = [30,11,23,4,20], h = 6
 * <strong>Output:</strong> 23
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;= piles.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>piles.length &lt;= h &lt;= 10<sup>9</sup></code></li>
 * <li><code>1 &lt;= piles[i] &lt;= 10<sup>9</sup></code></li>
 * </ul>
 */
public class KokoEatingBananas extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {3,6,7,11}, 8, 4},
                {new int[] {30,11,23,4,20}, 5, 30},
                {new int[] {30,11,23,4,20}, 6, 23},
                {new int[] {312884470}, 312884469, 2},
                {new int[] {805306368,805306368,805306368}, 1000000000, 3},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] piles, int h, int expected) {
        softAssert.as(String.format("piles = %s, h = %d", Arrays.toString(piles), h))
                  .assertThat(minEatingSpeed(piles, h))
                  .isEqualTo(expected);
    }

    public int minEatingSpeed(int[] piles, int h) {
        int min = piles[0];
        int max = piles[piles.length - 1];
        for (int i = 0; i < piles.length; i++) {
            min = Math.min(piles[i], min);
            max = Math.max(piles[i], max);
        }

        int i = 1;
        int j = max;
        int result = 0;
        while (i <= j) {
            int mid = (i + j) / 2;

            int hours = 0;
            for (int k = 0; k < piles.length; k++) {
                if (piles[k] >= mid) {
                    hours += (int) Math.ceil((double )piles[k] / mid);
                } else {
                    hours++;
                }
            }

            if (hours <= h) {
                result = mid;
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }

        return result;
    }
}
