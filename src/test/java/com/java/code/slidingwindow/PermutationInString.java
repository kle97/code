package com.java.code.slidingwindow;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 <p>Given two strings <code>s1</code> and <code>s2</code>, return <code>true</code><em> if </em><code>s2</code><em> contains a permutation of </em><code>s1</code><em>, or </em><code>false</code><em> otherwise</em>.</p>

 <p>In other words, return <code>true</code> if one of <code>s1</code>'s permutations is the substring of <code>s2</code>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> s1 = "ab", s2 = "eidbaooo"
 <strong>Output:</strong> true
 <strong>Explanation:</strong> s2 contains one permutation of s1 ("ba").
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> s1 = "ab", s2 = "eidboaoo"
 <strong>Output:</strong> false
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= s1 .length, s2 .length &lt;= 10<sup>4</sup></code></li>
 <li><code>s1</code> and <code>s2</code> consist of lowercase English letters.</li>
 </ul>

 <div><div>Related Topics</div><div><li>Hash Table</li><li>Two Pointers</li><li>String</li><li>Sliding Window</li></div></div><br><div><li>👍 11067</li><li>👎 384</li></div>
 */
public class PermutationInString extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"ab", "eidbaooo", true},
                {"ab", "eidboaoo", false},
        };
    }

    @Test(dataProvider = "data")
    public void test(String s1, String s2, boolean expected) {
        softAssert.as(String.format("s1 = %s, s2 = %s", s1, s2))
                  .assertThat(checkInclusion(s1, s2))
                  .isEqualTo(expected);
    }

    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> map1 = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char ch = s1.charAt(i);
            map1.put(ch, map1.getOrDefault(ch, 0) + 1);
        }

        Map<Character, Integer> map2 = new HashMap<>();
        int lastWindowIndex = 0;
        for (int i = 0; i < s2.length(); i++) {
            char ch = s2.charAt(i);
            if (i - lastWindowIndex + 1 <= s1.length()) {
                map2.put(ch, map2.getOrDefault(ch, 0) + 1);
            } else {
                map2.put(ch, map2.getOrDefault(ch, 0) + 1);

                char lastChar = s2.charAt(lastWindowIndex);
                lastWindowIndex++;
                if (map2.get(lastChar) == 1) {
                    map2.remove(lastChar);
                } else {
                    map2.put(lastChar, map2.get(lastChar) - 1);
                }
            }

            if (i - lastWindowIndex + 1 == s1.length()) {
                if (map1.equals(map2)) {
                    return true;
                }
            }
        }

        return false;
    }
}
