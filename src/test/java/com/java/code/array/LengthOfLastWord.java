package com.java.code.array;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 <p>Given a string <code>s</code> consisting of words and spaces, return <em>the length of the <strong>last</strong> word in the string.</em></p>

 <p>A <strong>word</strong> is a maximal <span data-keyword="substring-nonempty">substring</span> consisting of non-space characters only.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> s = "Hello World"
 <strong>Output:</strong> 5
 <strong>Explanation:</strong> The last word is "World" with length 5.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> s = "   fly me   to   the moon  "
 <strong>Output:</strong> 4
 <strong>Explanation:</strong> The last word is "moon" with length 4.
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> s = "luffy is still joyboy"
 <strong>Output:</strong> 6
 <strong>Explanation:</strong> The last word is "joyboy" with length 6.
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= s .length &lt;= 10<sup>4</sup></code></li> 
 <li><code>s</code> consists of only English letters and spaces <code>' '</code>.</li> 
 <li>There will be at least one word in <code>s</code>.</li> 
 </ul>

 <div><div>Related Topics</div><div><li>String</li></div></div><br><div><li>👍 4486</li><li>👎 239</li></div>
 */
public class LengthOfLastWord extends BaseTest {

    @DataProvider(name = "LengthOfLastWord")
    public Object[][] data() {
        return new Object[][] {
                {"Hello World", 5},
                {"   fly me   to   the moon  ", 4},
                {"luffy is still joyboy", 6},
        };
    }

    @Test(dataProvider = "LengthOfLastWord")
    public void test(String s, int expected) {
        softAssert.as(String.format("s = \"%s\"", s))
                  .assertThat(lengthOfLastWord(s))
                  .isEqualTo(expected);
    }

    public int lengthOfLastWord(String s) {
        int length = 0;
        boolean wordStarted = false;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (wordStarted) {
                    return length;
                }
            } else {
                wordStarted = true;
                length++;
            }
        }
        
        return length;
    }
}
