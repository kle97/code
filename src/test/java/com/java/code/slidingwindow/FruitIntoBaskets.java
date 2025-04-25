package com.java.code.slidingwindow;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array<span>&nbsp;</span><code>fruits</code><span>&nbsp;</span>where<span>&nbsp;</span><code>fruits[i]</code><span>&nbsp;</span>is the<span>&nbsp;</span><strong>type</strong><span>&nbsp;</span>of fruit the<span>&nbsp;</span><code>i<sup>th</sup></code><span>&nbsp;</span>tree produces.</p>
 * <p>You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:</p>
 * <ul>
 * <li>You only have<span>&nbsp;</span><strong>two</strong><span>&nbsp;</span>baskets, and each basket can only hold a<span>&nbsp;</span><strong>single type</strong><span>&nbsp;</span>of fruit. There is no limit on the amount of fruit each basket can hold.</li>
 * <li>Starting from any tree of your choice, you must pick<span>&nbsp;</span><strong>exactly one fruit</strong><span>&nbsp;</span>from<span>&nbsp;</span><strong>every</strong><span>&nbsp;</span>tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.</li>
 * <li>Once you reach a tree with fruit that cannot fit in your baskets, you must stop.</li>
 * </ul>
 * <p>Given the integer array<span>&nbsp;</span><code>fruits</code>, return<span>&nbsp;</span><em>the<span>&nbsp;</span><strong>maximum</strong><span>&nbsp;</span>number of fruits you can pick</em>.</p>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input:</strong> fruits = [<u>1,2,1</u>]
 * <strong>Output:</strong> 3
 * <strong>Explanation:</strong> We can pick from all 3 trees.
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre><strong>Input:</strong> fruits = [0,<u>1,2,2</u>]
 * <strong>Output:</strong> 3
 * <strong>Explanation:</strong> We can pick from trees [1,2,2].
 * If we had started at the first tree, we would only pick from trees [0,1].
 * </pre>
 * <p><strong class="example">Example 3:</strong></p>
 * <pre><strong>Input:</strong> fruits = [1,<u>2,3,2,2</u>]
 * <strong>Output:</strong> 4
 * <strong>Explanation:</strong> We can pick from trees [2,3,2,2].
 * If we had started at the first tree, we would only pick from trees [1,2].
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;= fruits .length &lt;= 10<sup>5</sup></code></li>
 * <li><code>0 &lt;= fruits[i] &lt; fruits .length</code></li>
 * </ul>
 */
public class FruitIntoBaskets extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {1,2,1}, 3},
                {new int[] {0,1,2,2}, 3},
                {new int[] {1,2,3,2,2}, 4},
                {new int[] {3,3,3,1,2,1,1,2,3,3,4}, 5},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] fruits, int expected) {
        softAssert.as(String.format("fruits = %s", Arrays.toString(fruits)))
                  .assertThat(totalFruit(fruits))
                  .isEqualTo(expected);
    }

    public int totalFruit(int[] fruits) {
        int longest = 1;
        int windowStart = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < fruits.length; i++) {
            int current = fruits[i];
             if (map.containsKey(current)) {
                map.put(current, map.get(current) + 1);
            } else if (map.size() < 2) {
                 map.put(current, 1);
             } else {
                while (map.size() == 2) {
                    int start = fruits[windowStart];
                    if (map.get(start) == 1) {
                        map.remove(start);
                    } else {
                        map.put(start, map.get(start) - 1);
                    }
                    windowStart++;
                }

                map.put(current, 1);
            }

            longest = Math.max(longest, i - windowStart + 1);
        }

        return longest;
    }
}
