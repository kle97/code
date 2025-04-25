package com.java.code.twopointers;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 <p>Given a string <code>s</code>, return <code>true</code> <em>if the </em><code>s</code><em> can be palindrome after deleting <strong>at most one</strong> character from it</em>.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> s = "aba"
 <strong>Output:</strong> true
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> s = "abca"
 <strong>Output:</strong> true
 <strong>Explanation:</strong> You could delete the character 'c'.
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> s = "abc"
 <strong>Output:</strong> false
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= s .length &lt;= 10<sup>5</sup></code></li> 
 <li><code>s</code> consists of lowercase English letters.</li> 
 </ul>

 <div><div>Related Topics</div><div><li>Two Pointers</li><li>String</li><li>Greedy</li></div></div><br><div><li>👍 7998</li><li>👎 417</li></div>
 */
public class ValidPalindromeII extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"aba", true},
                {"abca", true},
                {"abc", false},
                {"abbca", true},
                {"eeccccbebaeeabebccceea", false},
                {"aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga", true},
        };
    }

    @Test(dataProvider = "data")
    public void test(String s, boolean expected) {
        softAssert.as(String.format("s = %s", s))
                  .assertThat(validPalindrome(s))
                  .isEqualTo(expected);
    }

    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        
        while (i < j) {
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(j);
            if (ch1 != ch2) {
                return validPalindrome(s, i + 1, j, true) || validPalindrome(s, i, j - 1, true);
            }
            i++;
            j--;
        }
        
        return true;
    }

    public boolean validPalindrome(String s, int i, int j, boolean removed) {
        while (i < j) {
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(j);
            if (ch1 != ch2) {
                if (removed) {
                    return false;
                }
                return validPalindrome(s, i + 1, j, removed) || validPalindrome(s, i, j - 1, removed);
            }
            i++;
            j--;
        }

        return true;
    }
}
