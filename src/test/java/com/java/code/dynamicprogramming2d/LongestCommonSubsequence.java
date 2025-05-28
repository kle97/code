package com.java.code.dynamicprogramming2d;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 <p>Given two strings <code>text1</code> and <code>text2</code>, return <em>the length of their longest <strong>common subsequence</strong>. </em>If there is no <strong>common subsequence</strong>, return <code>0</code>.</p>

 <p>A <strong>subsequence</strong> of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.</p>

 <ul>
 <li>For example, <code>"ace"</code> is a subsequence of <code>"abcde"</code>.</li>
 </ul>

 <p>A <strong>common subsequence</strong> of two strings is a subsequence that is common to both strings.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> text1 = "abcde", text2 = "ace"
 <strong>Output:</strong> 3
 <strong>Explanation:</strong> The longest common subsequence is "ace" and its length is 3.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> text1 = "abc", text2 = "abc"
 <strong>Output:</strong> 3
 <strong>Explanation:</strong> The longest common subsequence is "abc" and its length is 3.
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> text1 = "abc", text2 = "def"
 <strong>Output:</strong> 0
 <strong>Explanation:</strong> There is no such common subsequence, so the result is 0.
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= text1.length, text2.length &lt;= 1000</code></li>
 <li><code>text1</code> and <code>text2</code> consist of only lowercase English characters.</li>
 </ul>

 <div><div>Related Topics</div><div><li>String</li><li>Dynamic Programming</li></div></div><br><div><li>👍 14269</li><li>👎 219</li></div>
 */
public class LongestCommonSubsequence extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"abcde", "ace", 3},
                {"abc", "abc", 3},
                {"abc", "def", 0},
                {"abc", "acb", 2},
                {"bl", "yby", 1},
        };
    }

    @Test(dataProvider = "data")
    public void test(String text1, String text2, int expected) {
        softAssert.as(String.format("text1 = %s, text2 = %s", text1, text2))
                  .assertThat(longestCommonSubsequence(text1, text2))
                  .isEqualTo(expected);
    }

    public int longestCommonSubsequence(String text1, String text2) {
        return longestCommonSubsequence(text1, text2, 0, 0, new Integer[text1.length()][text2.length()]);
    }

    public int longestCommonSubsequence(String text1, String text2, int i, int j, Integer[][] memoized) {
        if (i == text1.length() || j == text2.length()) {
            return 0;
        }

        if (memoized[i][j] == null) {
            int maxLength;
            if (text1.charAt(i) == text2.charAt(j)) {
                maxLength = Math.max(longestCommonSubsequence(text1, text2, i + 1, j, memoized),
                                     1 + longestCommonSubsequence(text1, text2, i + 1, j + 1, memoized));
            } else {
                maxLength = Math.max(longestCommonSubsequence(text1, text2, i, j + 1, memoized),
                                     longestCommonSubsequence(text1, text2, i + 1, j, memoized));
            }
            memoized[i][j] = maxLength;
        }
        return memoized[i][j];
    }
}
