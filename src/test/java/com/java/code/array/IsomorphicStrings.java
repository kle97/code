package com.java.code.array;

import java.util.HashMap;
import java.util.Map;

/**
 <p>Given two strings <code>s</code> and <code>t</code>, <em>determine if they are isomorphic</em>.</p>

 <p>Two strings <code>s</code> and <code>t</code> are isomorphic if the characters in <code>s</code> can be replaced to get <code>t</code>.</p>

 <p>All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p> 
 <pre><strong>Input:</strong> s = "egg", t = "add"
 <strong>Output:</strong> true
 </pre>
 <p><strong class="example">Example 2:</strong></p> 
 <pre><strong>Input:</strong> s = "foo", t = "bar"
 <strong>Output:</strong> false
 </pre>
 <p><strong class="example">Example 3:</strong></p> 
 <pre><strong>Input:</strong> s = "paper", t = "title"
 <strong>Output:</strong> true
 </pre> 
 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= s  .length &lt;= 5 * 10<sup>4</sup></code></li> 
 <li><code>t .length == s .length</code></li> 
 <li><code>s</code> and <code>t</code> consist of any valid ascii character.</li> 
 </ul>

 <div><div>Related Topics</div><div><li>Hash Table</li><li>String</li></div></div><br><div><li>👍 8075</li><li>👎 1885</li></div>
 */

public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);

            if (!map.containsKey(sChar)) {
                map.put(sChar, tChar);
            } else if (!map.get(sChar).equals(tChar)) {
                return false;
            }

            if (!map2.containsKey(tChar)) {
                map2.put(tChar, sChar);
            } else if (!map2.get(tChar).equals(sChar)) {
                return false;
            }
        }

        return true;
    }
}
