package com.java.code.array;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 <p>Given two strings <code>s</code> and <code>t</code>, return <code>true</code><em> if </em><code>s</code><em> is a <strong>subsequence</strong> of </em><code>t</code><em>, or </em><code>false</code><em> otherwise</em>.</p>

 <p>A <strong>subsequence</strong> of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., <code>"ace"</code> is a subsequence of <code>"<u>a</u>b<u>c</u>d<u>e</u>"</code> while <code>"aec"</code> is not).</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p> 
 <pre><strong>Input:</strong> s = "abc", t = "ahbgdc"
 <strong>Output:</strong> true
 </pre>
 <p><strong class="example">Example 2:</strong></p> 
 <pre><strong>Input:</strong> s = "axc", t = "ahbgdc"
 <strong>Output:</strong> false
 </pre> 
 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>0 &lt;= s  .length &lt;= 100</code></li> 
 <li><code>0 &lt;= t  .length &lt;= 10<sup>4</sup></code></li> 
 <li><code>s</code> and <code>t</code> consist only of lowercase English letters.</li> 
 </ul>

 <p>&nbsp;</p> 
 <strong>Follow up:</strong> Suppose there are lots of incoming 
 <code>s</code>, say 
 <code>s<sub>1</sub>, s<sub>2</sub>, ..., s<sub>k</sub></code> where 
 <code>k &gt;= 10<sup>9</sup></code>, and you want to check one by one to see if 
 <code>t</code> has its subsequence. In this scenario, how would you change your code?

 <div><div>Related Topics</div><div><li>Two Pointers</li><li>String</li><li>Dynamic Programming</li></div></div><br><div><li>👍 9250</li><li>👎 499</li></div>
 */
public class IsSubsequence extends BaseTest {

    @DataProvider(name = "IsSubsequence")
    public Object[][] data() {
        return new Object[][] {
                {"abc", "ahbgdc", true},
                {"axc", "ahbgdc", false},
        };
    }

    @Test(dataProvider = "IsSubsequence")
    public void test(String s, String t, boolean expected) {
        softAssert.as(String.format("s = \"%s\", t = \"%s\"", s, t))
                  .assertThat(isSubsequence(s, t))
                  .isEqualTo(expected);
    }

    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty() && t.isEmpty()) {
            return true;
        }
        
        int j = 0;
        for (int i = 0; i < t.length(); i++) {
            if (j >= s.length()) {
                return true;
            }
            
            if (t.charAt(i) == s.charAt(j)) {
                j++;
            }
            
            if (j >= s.length()) {
                return true;
            }
        }
        
        return false;
    }
}
