package com.java.code.array;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

/**
 <p>Given an array of strings <code>strs</code>, group <strong>the anagrams</strong> together. You can return the answer in <strong>any order</strong>.</p>

 <p>An <strong>Anagram</strong> is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p> 
 <pre><strong>Input:</strong> strs = ["eat","tea","tan","ate","nat","bat"]
 <strong>Output:</strong> [["bat"],["nat","tan"],["ate","eat","tea"]]
 </pre>
 <p><strong class="example">Example 2:</strong></p> 
 <pre><strong>Input:</strong> strs = [""]
 <strong>Output:</strong> [[""]]
 </pre>
 <p><strong class="example">Example 3:</strong></p> 
 <pre><strong>Input:</strong> strs = ["a"]
 <strong>Output:</strong> [["a"]]
 </pre> 
 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= strs.length &lt;= 10<sup>4</sup></code></li> 
 <li><code>0 &lt;= strs[i].length &lt;= 100</code></li> 
 <li><code>strs[i]</code> consists of lowercase English letters.</li> 
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Hash Table</li><li>String</li><li>Sorting</li></div></div><br><div><li>👍 18081</li><li>👎 549</li></div>
 */
public class GroupAnagrams extends BaseTest {

    @DataProvider(name = "GroupAnagrams")
    public Object[][] data() {
        return new Object[][] {
                {new String[] {"eat","tea","tan","ate","nat","bat"}, List.of(List.of("bat"), List.of("nat","tan"), List.of("ate","eat","tea"))},
                {new String[] {""}, List.of(List.of(""))},
                {new String[] {"a"}, List.of(List.of("a"))},

        };
    }

    @Test(dataProvider = "GroupAnagrams")
    public void test(String[] strs, List<List<String>> expected) {
        softAssert.as(Arrays.toString(strs))
                  .assertThat(groupAnagrams(strs))
                  .isEqualNoOrder(expected);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> list = new ArrayList<>();
        boolean[] checked = new boolean[strs.length];

        for (int i = 0; i < strs.length; i++) {
            if (checked[i]) {
                continue;
            }
            
            String str = strs[i];
            List<String> anagram = new ArrayList<>();
            anagram.add(str);
            for (int j = i + 1; j < strs.length; j++) {
                if(checked[i]) {
                    continue;
                }
                
                if (isAnagram(str, strs[j])) {
                    anagram.add(strs[j]);
                    checked[j] = true;
                }
            }
            
            list.add(anagram);
        }
        
        return list;
    }
    
    private boolean isAnagram(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < first.length(); i++) {
            char ch= first.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < second.length(); i++) {
            char ch = second.charAt(i);
            if(map.containsKey(ch)) {
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
