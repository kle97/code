package com.java.code.dynamicprogramming;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 <p>Given a string <code>s</code>, return <em>the number of <strong>palindromic substrings</strong> in it</em>.</p>

 <p>A string is a <strong>palindrome</strong> when it reads the same backward as forward.</p>

 <p>A <strong>substring</strong> is a contiguous sequence of characters within the string.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> s = "abc"
 <strong>Output:</strong> 3
 <strong>Explanation:</strong> Three palindromic strings: "a", "b", "c".
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> s = "aaa"
 <strong>Output:</strong> 6
 <strong>Explanation:</strong> Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= s.length &lt;= 1000</code></li>
 <li><code>s</code> consists of lowercase English letters.</li>
 </ul>

 <div><div>Related Topics</div><div><li>Two Pointers</li><li>String</li><li>Dynamic Programming</li></div></div><br><div><li>👍 11102</li><li>👎 245</li></div>
 */
public class PalindromicSubstrings extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"abc", 3},
                {"aaa", 6},
                {"a", 1},
                {"aa", 3},
                {"abbbc", 8}
        };
    }

    @Test(dataProvider = "data")
    public void test(String s, int expected) {
        softAssert.as("s = %s", s)
                  .assertThat(countSubstrings(s))
                  .isEqualTo(expected);
    }

    public int countSubstrings(String s) {
        int count = 0;
        for (int k = 0; k < s.length(); k++) {
            int i = k;
            int j = k;
            while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
                count++;
                i--;
                j++;
            }

            i = k;
            j = k + 1;
            while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
                count++;
                i--;
                j++;
            }
        }
        return count;
    }


}
