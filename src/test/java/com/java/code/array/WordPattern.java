package com.java.code.array;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 <p>Given a <code>pattern</code> and a string <code>s</code>, find if <code>s</code>&nbsp;follows the same pattern.</p>

 <p>Here <b>follow</b> means a full match, such that there is a bijection between a letter in <code>pattern</code> and a <b>non-empty</b> word in <code>s</code>.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> pattern = "abba", s = "dog cat cat dog"
 <strong>Output:</strong> true
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> pattern = "abba", s = "dog cat cat fish"
 <strong>Output:</strong> false
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> pattern = "aaaa", s = "dog cat cat dog"
 <strong>Output:</strong> false
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= pattern.length &lt;= 300</code></li> 
 <li><code>pattern</code> contains only lower-case English letters.</li> 
 <li><code>1 &lt;= s.length &lt;= 3000</code></li> 
 <li><code>s</code> contains only lowercase English letters and spaces <code>' '</code>.</li> 
 <li><code>s</code> <strong>does not contain</strong> any leading or trailing spaces.</li> 
 <li>All the words in <code>s</code> are separated by a <strong>single space</strong>.</li> 
 </ul>

 <div><div>Related Topics</div><div><li>Hash Table</li><li>String</li></div></div><br><div><li>👍 7031</li><li>👎 944</li></div>
 */
public class WordPattern extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"abba", "dog cat cat dog", true},
                {"abba", "dog dog dog dog", false},
                {"abba", "dog cat cat fish", false},
                {"aaaa", "dog cat cat dog", false},
                {"abc", "b c a", true},
                {"aaa", "aa aa aa aa", false},
                {"he", "unit", false},
        };
    }

    @Test(dataProvider = "data")
    public void test(String pattern, String s, boolean expected) {
        softAssert.as(String.format("pattern = %s, s = \"%s\"", pattern, s))
                  .assertThat(wordPattern(pattern, s))
                  .isEqualTo(expected);
    }

    public boolean wordPattern(String pattern, String s) {
        Map<String, String> map = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        String[] words = s.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        
        for (int i = 0; i < pattern.length(); i++) {
            String p = String.valueOf(pattern.charAt(i));
            String word = words[i];
            
            if (!map.containsKey(p)) {
                map.put(p, word);
            } else if (!map.get(p).equals(word)) {
                return false;
            }

            if (!map2.containsKey(word)) {
                map2.put(word, p);
            } else if (!map2.get(word).equals(p)) {
                return false;
            }
        }
        
        return true;
    }
}
