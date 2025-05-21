package com.java.code.dynamicprogramming;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 <p>Given a string <code>s</code>, return <em>the longest</em> <span data-keyword="palindromic-string"><em>palindromic</em></span> <span data-keyword="substring-nonempty"><em>substring</em></span> in <code>s</code>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> s = "babad"
 <strong>Output:</strong> "bab"
 <strong>Explanation:</strong> "aba" is also a valid answer.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> s = "cbbd"
 <strong>Output:</strong> "bb"
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= s.length &lt;= 1000</code></li>
 <li><code>s</code> consist of only digits and English letters.</li>
 </ul>

 <div><div>Related Topics</div><div><li>Two Pointers</li><li>String</li><li>Dynamic Programming</li></div></div><br><div><li>👍 30699</li><li>👎 1893</li></div>
 */
public class LongestPalindromicSubstring extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"babad", List.of("bab", "aba")},
                {"cbbd", List.of("bb")},
                {"aacabdkacaa", List.of("aca")},
                {"ccd", List.of("cc")},
        };
    }

    @Test(dataProvider = "data")
    public void test(String s, List<String> expected) {
        softAssert.as("s = %s", s)
                  .assertThat(expected)
                  .contains(longestPalindrome(s));
    }

    public String longestPalindrome(String s) {
        return longestPalindrome(s, 0, s.length() - 1, new String[s.length()][s.length()]);
    }

    public String longestPalindrome(String s, int i, int j, String[][] memoized) {
        String substring = s.substring(i, j + 1);
        if (i == j) {
            return substring;
        }

        if (memoized[i][j] == null) {
            if (isPalindrome(substring)) {
                return substring;
            } else {
                String first = longestPalindrome(s, i + 1, j, memoized);
                String second = longestPalindrome(s, i, j - 1, memoized);
                memoized[i][j] = first.length() > second.length() ? first : second;
            }
        }
        return memoized[i][j];
    }

    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
