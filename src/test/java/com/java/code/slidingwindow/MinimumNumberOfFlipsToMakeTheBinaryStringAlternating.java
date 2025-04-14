package com.java.code.slidingwindow;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * <p>You are given a binary string<span>&nbsp;</span><code>s</code>. You are allowed to perform two types of operations on the string in any sequence:</p>
 * <ul>
 * <li><strong>Type-1: Remove</strong><span>&nbsp;</span>the character at the start of the string<span>&nbsp;</span><code>s</code><span>&nbsp;</span>and<span>&nbsp;</span><strong>append</strong><span>&nbsp;</span>it to the end of the string.</li>
 * <li><strong>Type-2: Pick</strong><span>&nbsp;</span>any character in<span>&nbsp;</span><code>s</code><span>&nbsp;</span>and<span>&nbsp;</span><strong>flip</strong><span>&nbsp;</span>its value, i.e., if its value is<span>&nbsp;</span><code>'0'</code><span>&nbsp;</span>it becomes<span>&nbsp;</span><code>'1'</code><span>&nbsp;</span>and vice-versa.</li>
 * </ul>
 * <p>Return<span>&nbsp;</span><em>the<span>&nbsp;</span><strong>minimum</strong><span>&nbsp;</span>number of<span>&nbsp;</span><strong>type-2</strong><span>&nbsp;</span>operations you need to perform</em><span>&nbsp;</span><em>such that<span>&nbsp;</span></em><code>s</code><span>&nbsp;</span><em>becomes<span>&nbsp;</span><strong>alternating</strong>.</em></p>
 * <p>The string is called<span>&nbsp;</span><strong>alternating</strong><span>&nbsp;</span>if no two adjacent characters are equal.</p>
 * <ul>
 * <li>For example, the strings<span>&nbsp;</span><code>"010"</code><span>&nbsp;</span>and<span>&nbsp;</span><code>"1010"</code><span>&nbsp;</span>are alternating, while the string<span>&nbsp;</span><code>"0100"</code><span>&nbsp;</span>is not.</li>
 * </ul>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input:</strong> s = "111000"
 * <strong>Output:</strong> 2
 * <strong>Explanation</strong>: Use the first operation two times to make s = "100011".
 * Then, use the second operation on the third and sixth elements to make s = "10<u>1</u>01<u>0</u>".
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre><strong>Input:</strong> s = "010"
 * <strong>Output:</strong> 0
 * <strong>Explanation</strong>: The string is already alternating.
 * </pre>
 * <p><strong class="example">Example 3:</strong></p>
 * <pre><strong>Input:</strong> s = "1110"
 * <strong>Output:</strong> 1
 * <strong>Explanation</strong>: Use the second operation on the second element to make s = "1<u>0</u>10".
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>s[i]</code><span>&nbsp;</span>is either<span>&nbsp;</span><code>'0'</code><span>&nbsp;</span>or<span>&nbsp;</span><code>'1'</code>.</li>
 * </ul>
 */
public class MinimumNumberOfFlipsToMakeTheBinaryStringAlternating extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"111000", 2},
                {"010", 0},
                {"1110", 1},
                {"01001001101", 2},
                {"10001100101000000", 5},
                {"111111011010110011100101101010011011111000010011010", 19},
        };
    }

    @Test(dataProvider = "data")
    public void test(String s, int expected) {
        softAssert.as(String.format("s = %s", s))
                  .assertThat(minFlips(s))
                  .isEqualTo(expected);
    }

    public int minFlips(String s) {
        int oldLength = s.length();
        s += s;
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                sb1.append("0");
                sb2.append("1");
            } else {
                sb1.append("1");
                sb2.append("0");
            }
        }

        String s1 = sb1.toString();
        String s2 = sb2.toString();
        int operation1 = 0;
        int operation2 = 0;
        for (int i = 0; i < oldLength; i++) {
            char ch = s.charAt(i);
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);

            if (ch != ch1) {
                operation1++;
            }

            if (ch != ch2) {
                operation2++;
            }
        }

        int min = Math.min(operation1, operation2);

        for (int i = oldLength; i < s.length(); i++) {
            if (s.charAt(i - oldLength) != s1.charAt(i - oldLength)) {
                operation1--;
            }
            if(s.charAt(i - oldLength) != s2.charAt(i - oldLength)) {
                operation2--;
            }

            char ch = s.charAt(i);
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);
            if (ch != ch1) {
                operation1++;
            }
            if (ch != ch2) {
                operation2++;
            }

            min = Math.min(Math.min(min, operation1), Math.min(min, operation2));
        }

        return min;
    }
}
