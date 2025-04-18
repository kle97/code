package com.java.code.math;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 <p>You are given a <strong>large integer</strong> represented as an integer array <code>digits</code>, where each <code>digits[i]</code> is the <code>i<sup>th</sup></code> digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading <code>0</code>'s.</p>

 <p>Increment the large integer by one and return <em>the resulting array of digits</em>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> digits = [1,2,3]
 <strong>Output:</strong> [1,2,4]
 <strong>Explanation:</strong> The array represents the integer 123.
 Incrementing by one gives 123 + 1 = 124.
 Thus, the result should be [1,2,4].
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> digits = [4,3,2,1]
 <strong>Output:</strong> [4,3,2,2]
 <strong>Explanation:</strong> The array represents the integer 4321.
 Incrementing by one gives 4321 + 1 = 4322.
 Thus, the result should be [4,3,2,2].
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> digits = [9]
 <strong>Output:</strong> [1,0]
 <strong>Explanation:</strong> The array represents the integer 9.
 Incrementing by one gives 9 + 1 = 10.
 Thus, the result should be [1,0].
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= digits.length &lt;= 100</code></li>
 <li><code>0 &lt;= digits[i] &lt;= 9</code></li>
 <li><code>digits</code> does not contain any leading <code>0</code>'s.</li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Math</li></div></div><br><div><li>👍 10177</li><li>👎 5485</li></div>
 */
public class PlusOne extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {1,2,3}, new int[] {1,2,4}},
                {new int[] {4,3,2,1}, new int[] {4,3,2,2}},
                {new int[] {9}, new int[] {1,0}},
                {new int[] {9,8,7,6,5,4,3,2,1,0}, new int[] {9,8,7,6,5,4,3,2,1,1}},
                {new int[] {7,2,8,5,0,9,1,2,9,5,3,6,6,7,3,2,8,4,3,7,9,5,7,7,4,7,4,9,4,7,0,1,1,1,7,4,0,0,6}, new int[] {7,2,8,5,0,9,1,2,9,5,3,6,6,7,3,2,8,4,3,7,9,5,7,7,4,7,4,9,4,7,0,1,1,1,7,4,0,0,7}},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] digits, int[] expected) {
        softAssert.as(String.format("digits = %s", Arrays.toString(digits)))
                  .assertThat(plusOne(digits))
                  .isEqualTo(expected);
    }

    public int[] plusOne(int[] digits) {
        List<Integer> list = new ArrayList<>();
        int borrow = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i];
            if (i == digits.length - 1) {
                sum += 1;
            } else {
                sum += borrow;
            }
            borrow = 0;
            if (sum == 10) {
                list.add(0);
                borrow = 1;
            } else {
                list.add(sum);
            }
        }
        if (borrow == 1) {
            list.add(borrow);
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(list.size() - 1 - i);
        }
        return result;
    }
}
