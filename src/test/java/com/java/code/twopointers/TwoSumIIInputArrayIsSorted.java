package com.java.code.twopointers;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

/**
 <p>Given a <strong>1-indexed</strong> array of integers <code>numbers</code> that is already <strong><em>sorted in non-decreasing order</em></strong>, find two numbers such that they add up to a specific <code>target</code> number. Let these two numbers be <code>numbers[index<sub>1</sub>]</code> and <code>numbers[index<sub>2</sub>]</code> where <code>1 &lt;= index<sub>1</sub> &lt; index<sub>2</sub> &lt;= numbers.length</code>.</p>

 <p>Return<em> the indices of the two numbers, </em><code>index<sub>1</sub></code><em> and </em><code>index<sub>2</sub></code><em>, <strong>added by one</strong> as an integer array </em><code>[index<sub>1</sub>, index<sub>2</sub>]</code><em> of length 2.</em></p>

 <p>The tests are generated such that there is <strong>exactly one solution</strong>. You <strong>may not</strong> use the same element twice.</p>

 <p>Your solution must use only constant extra space.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> numbers = [<u>2</u>,<u>7</u>,11,15], target = 9
 <strong>Output:</strong> [1,2]
 <strong>Explanation:</strong> The sum of 2 and 7 is 9. Therefore, index<sub>1</sub> = 1, index<sub>2</sub> = 2. We return [1, 2].
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> numbers = [<u>2</u>,3,<u>4</u>], target = 6
 <strong>Output:</strong> [1,3]
 <strong>Explanation:</strong> The sum of 2 and 4 is 6. Therefore index<sub>1</sub> = 1, index<sub>2</sub> = 3. We return [1, 3].
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> numbers = [<u>-1</u>,<u>0</u>], target = -1
 <strong>Output:</strong> [1,2]
 <strong>Explanation:</strong> The sum of -1 and 0 is -1. Therefore index<sub>1</sub> = 1, index<sub>2</sub> = 2. We return [1, 2].
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>2 &lt;= numbers.length &lt;= 3 * 10<sup>4</sup></code></li>
 <li><code>-1000 &lt;= numbers[i] &lt;= 1000</code></li>
 <li><code>numbers</code> is sorted in <strong>non-decreasing order</strong>.</li>
 <li><code>-1000 &lt;= target &lt;= 1000</code></li>
 <li>The tests are generated such that there is <strong>exactly one solution</strong>.</li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Two Pointers</li><li>Binary Search</li></div></div><br><div><li>👍 11367</li><li>👎 1375</li></div>
 */
public class TwoSumIIInputArrayIsSorted extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {2,7,11,15}, 9, new int[]{1,2}},
                {new int[] {2,3,4}, 6, new int[]{1,3}},
                {new int[] {-1,0}, -1, new int[]{1,2}},
                };
    }

    @Test(dataProvider = "data")
    public void test(int[] numbers, int target, int[] expected) {
        softAssert.as(String.format("numbers = %s, target = %d", Arrays.toString(numbers), target))
                  .assertThat(twoSum(numbers, target))
                  .isEqualTo(expected);
    }

    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }

        for (int i = 0; i < numbers.length; i++) {
            int key = target - numbers[i];
            if (map.containsKey(key)) {
                return new int[] {i + 1, map.get(key) + 1};
            }
        }
        return new int[0];
    }
}
