package com.java.code.slidingwindow;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 <p>Given a string <code>s</code>, find the length of the <strong>longest</strong> <span data-keyword="substring-nonempty"><strong>substring</strong></span> without repeating characters.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> s = "abcabcbb"
 <strong>Output:</strong> 3
 <strong>Explanation:</strong> The answer is "abc", with the length of 3.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> s = "bbbbb"
 <strong>Output:</strong> 1
 <strong>Explanation:</strong> The answer is "b", with the length of 1.
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> s = "pwwkew"
 <strong>Output:</strong> 3
 <strong>Explanation:</strong> The answer is "wke", with the length of 3.
 Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>0 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
 <li><code>s</code> consists of English letters, digits, symbols and spaces.</li>
 </ul>

 <div><div>Related Topics</div><div><li>Hash Table</li><li>String</li><li>Sliding Window</li></div></div><br><div><li>👍 38777</li><li>👎 1808</li></div>
 */
public class LongestSubstringWithoutRepeatingCharacters extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"abcabcbb", 3},
                {"bbbbb", 1},
                {"pwwkew", 3},
        };
    }

    @Test(dataProvider = "data")
    public void test(String s, int expected) {
        softAssert.as(String.format("s = %s", s))
                  .assertThat(lengthOfLongestSubstring(s))
                  .isEqualTo(expected);
    }

    public int lengthOfLongestSubstring(String s) {
        int longest = 0;
        Map<Character, Integer> map = new HashMap<>();
        int windowStart = 0;
        int windowEnd = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch) && map.get(ch) >= windowStart) {
                longest = Math.max(longest, windowEnd - windowStart + 1);
                int lastIndex = map.get(ch);
                windowStart = lastIndex + 1;
                windowEnd = i;
                map.put(ch, i);
            } else {
                map.put(ch, i);
                windowEnd = i;
            }
        }

        return s.isEmpty() ? 0 : Math.max(longest, windowEnd - windowStart + 1);
    }
}
