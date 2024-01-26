package com.java.code.array;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 <p>Given two strings <code>s</code> and <code>t</code>, return <code>true</code> <em>if</em> <code>t</code> <em>is an anagram of</em> <code>s</code><em>, and</em> <code>false</code> <em>otherwise</em>.</p>

 <p>An <strong>Anagram</strong> is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p> 
 <pre><strong>Input:</strong> s = "anagram", t = "nagaram"
 <strong>Output:</strong> true
 </pre>
 <p><strong class="example">Example 2:</strong></p> 
 <pre><strong>Input:</strong> s = "rat", t = "car"
 <strong>Output:</strong> false
 </pre> 
 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= s.length, t.length &lt;= 5 * 10<sup>4</sup></code></li> 
 <li><code>s</code> and <code>t</code> consist of lowercase English letters.</li> 
 </ul>

 <p>&nbsp;</p> 
 <p><strong>Follow up:</strong> What if the inputs contain Unicode characters? How would you adapt your solution to such a case?</p>

 <div><div>Related Topics</div><div><li>Hash Table</li><li>String</li><li>Sorting</li></div></div><br><div><li>👍 11611</li><li>👎 369</li></div>
 */
public class ValidAnagram extends BaseTest {

    @DataProvider(name = "ValidAnagram")
    public Object[][] data() {
        return new Object[][] {
                {"anagram", "nagaram", true},
                {"rat", "car", false},
        };
    }

    @Test(dataProvider = "ValidAnagram")
    public void test(String s, String t, boolean expected) {
        softAssert.as(String.format("s = \"%s\", t = \"%s\"", s, t))
                  .assertThat(isAnagram(s, t))
                  .isEqualTo(expected);
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (map.containsKey(ch)) {
                int value = map.get(ch);

                if (value > 1) {
                    map.put(ch, value - 1);
                } else {
                    map.remove(ch);
                }
            } else {
                return false;
            }
        }
        
        return true;
    }
}
