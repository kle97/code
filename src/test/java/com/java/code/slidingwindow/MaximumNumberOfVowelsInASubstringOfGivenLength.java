package com.java.code.slidingwindow;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * <p>Given a string<span>&nbsp;</span><code>s</code><span>&nbsp;</span>and an integer<span>&nbsp;</span><code>k</code>, return<span>&nbsp;</span><em>the maximum number of vowel letters in any substring of<span>&nbsp;</span></em><code>s</code><em><span>&nbsp;</span>with length<span>&nbsp;</span></em><code>k</code>.</p>
 * <p><strong>Vowel letters</strong><span>&nbsp;</span>in English are<span>&nbsp;</span><code>'a'</code>,<span>&nbsp;</span><code>'e'</code>,<span>&nbsp;</span><code>'i'</code>,<span>&nbsp;</span><code>'o'</code>, and<span>&nbsp;</span><code>'u'</code>.</p>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input:</strong> s = "abciiidef", k = 3
 * <strong>Output:</strong> 3
 * <strong>Explanation:</strong> The substring "iii" contains 3 vowel letters.
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre><strong>Input:</strong> s = "aeiou", k = 2
 * <strong>Output:</strong> 2
 * <strong>Explanation:</strong> Any substring of length 2 contains 2 vowels.
 * </pre>
 * <p><strong class="example">Example 3:</strong></p>
 * <pre><strong>Input:</strong> s = "leetcode", k = 3
 * <strong>Output:</strong> 2
 * <strong>Explanation:</strong> "lee", "eet" and "ode" contain 2 vowels.
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;= s .length &lt;= 10<sup>5</sup></code></li>
 * <li><code>s</code><span>&nbsp;</span>consists of lowercase English letters.</li>
 * <li><code>1 &lt;= k &lt;= s .length</code></li>
 * </ul>
 */
public class MaximumNumberOfVowelsInASubstringOfGivenLength extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"abciiidef", 3, 3},
                {"aeiou", 2, 2},
                {"leetcode", 3, 2},
        };
    }

    @Test(dataProvider = "data")
    public void test(String s, int k, int expected) {
        softAssert.as(String.format("s = %s, k = %s", s, k))
                  .assertThat(maxVowels(s, k))
                  .isEqualTo(expected);
    }

    public int maxVowels(String s, int k) {
        int longest = 0;
        for (int i = 0; i < k; i++) {
            char ch = s.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                longest++;
            }
        }

        int count = longest;
        for (int i = k; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }

            char lastChar = s.charAt(i - k);
            if (lastChar == 'a' || lastChar == 'e' || lastChar == 'i' || lastChar == 'o' || lastChar == 'u') {
                count--;
            }

            longest = Math.max(longest, count);
        }
        return longest;
    }
}
