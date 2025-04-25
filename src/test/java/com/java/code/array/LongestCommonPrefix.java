package com.java.code.array;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>Write a function to find the longest common prefix string amongst an array of strings.</p>

 <p>If there is no common prefix, return an empty string <code>""</code>.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> strs = ["flower","flow","flight"]
 <strong>Output:</strong> "fl"
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> strs = ["dog","racecar","car"]
 <strong>Output:</strong> ""
 <strong>Explanation:</strong> There is no common prefix among the input strings.
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= strs .length &lt;= 200</code></li> 
 <li><code>0 &lt;= strs[i] .length &lt;= 200</code></li> 
 <li><code>strs[i]</code> consists of only lowercase English letters.</li> 
 </ul>

 <div><div>Related Topics</div><div><li>String</li><li>Trie</li></div></div><br><div><li>👍 16769</li><li>👎 4405</li></div>
 */
public class LongestCommonPrefix extends BaseTest {

    @DataProvider(name = "LongestCommonPrefix")
    public Object[][] data() {
        return new Object[][] {
                {new String[] {"flower","flow","flight"}, "fl"},
                {new String[] {"dog","racecar","car"}, ""},
        };
    }

    @Test(dataProvider = "LongestCommonPrefix")
    public void test(String[] strs, String expected) {
        softAssert.as(Arrays.toString(strs))
                  .assertThat(longestCommonPrefix(strs))
                  .isEqualTo(expected);
    }

    public String longestCommonPrefix(String[] strs) {
        String first = strs[0];
        int end = first.length() - 1;
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int length = Math.min(end, str.length() - 1);
            end = -1;
            int k = 0;
            for (int j = 0; j <= length; j++) {
                if (str.charAt(j) != first.charAt(k)) {
                    break;
                } else {
                    end = j;
                }
                k++;
            }
        }
        
        return end < 0 ? "" : first.substring(0, end + 1);
    }
}
