package com.java.code.slidingwindow;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Given two strings<span>&nbsp;</span><code>s</code><span>&nbsp;</span>and<span>&nbsp;</span><code>t</code><span>&nbsp;</span>of lengths<span>&nbsp;</span><code>m</code><span>&nbsp;</span>and<span>&nbsp;</span><code>n</code><span>&nbsp;</span>respectively, return<span>&nbsp;</span><em>the<span>&nbsp;</span><strong>minimum window</strong></em><span>&nbsp;</span><span data-keyword="substring-nonempty" class=" cursor-pointer relative text-dark-blue-s text-sm"></span></p>
 * <div class="popover-wrapper inline-block" data-headlessui-state="">
 * <div>
 * <div aria-expanded="false" data-headlessui-state="" id="headlessui-popover-button-:rv:">
 * <div><strong><em>substring</em></strong></div>
 * </div>
 * <div></div>
 * </div>
 * </div>
 * <p><span data-keyword="substring-nonempty" class=" cursor-pointer relative text-dark-blue-s text-sm"></span><em><span>&nbsp;</span>of<span>&nbsp;</span></em><code>s</code><em><span>&nbsp;</span>such that every character in<span>&nbsp;</span></em><code>t</code><em><span>&nbsp;</span>(<strong>including duplicates</strong>) is included in the window</em>. If there is no such substring, return<span>&nbsp;</span><em>the empty string<span>&nbsp;</span></em><code>""</code>.</p>
 * <p>The testcases will be generated such that the answer is<span>&nbsp;</span><strong>unique</strong>.</p>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input:</strong> s = "ADOBECODEBANC", t = "ABC"
 * <strong>Output:</strong> "BANC"
 * <strong>Explanation:</strong> The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre><strong>Input:</strong> s = "a", t = "a"
 * <strong>Output:</strong> "a"
 * <strong>Explanation:</strong> The entire string s is the minimum window.
 * </pre>
 * <p><strong class="example">Example 3:</strong></p>
 * <pre><strong>Input:</strong> s = "a", t = "aa"
 * <strong>Output:</strong> ""
 * <strong>Explanation:</strong> Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>m == s .length</code></li>
 * <li><code>n == t .length</code></li>
 * <li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
 * <li><code>s</code><span>&nbsp;</span>and<span>&nbsp;</span><code>t</code><span>&nbsp;</span>consist of uppercase and lowercase English letters.</li>
 * </ul>
 * <p>&nbsp;</p>
 * <p><strong>Follow up:</strong><span>&nbsp;</span>Could you find an algorithm that runs in<span>&nbsp;</span><code>O(m + n)</code><span>&nbsp;</span>time?</p>
 */
public class MinimumWindowSubstring extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"ADOBECODEBANC", "ABC", "BANC"},
                {"a", "a", "a"},
                {"a", "aa", ""},
        };
    }

    @Test(dataProvider = "data")
    public void test(String s, String t, String expected) {
        softAssert.as(String.format("s = %s, t = %s", s, t))
                  .assertThat(minWindow(s, t))
                  .isEqualTo(expected);
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
        }

        int windowStart = 0;
        int minLength = Integer.MAX_VALUE;
        int start = 0;
        int end = -1;
        Map<Character, Integer> sMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            sMap.put(ch, sMap.getOrDefault(ch, 0) + 1);

            boolean satisfied = true;
            for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
                if (!sMap.containsKey(entry.getKey()) || sMap.get(entry.getKey()) < entry.getValue()) {
                    satisfied = false;
                    break;
                }
            }

            while (satisfied && windowStart <= s.length() - 1) {
                if (i - windowStart + 1 < minLength) {
                    minLength = i - windowStart + 1;
                    start = windowStart;
                    end = i;
                }
                char startChar = s.charAt(windowStart);
                sMap.put(startChar, sMap.get(startChar) - 1);
                if (sMap.get(startChar) == 0) {
                    sMap.remove(startChar);
                }
                windowStart++;

                for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
                    if (!sMap.containsKey(entry.getKey()) || sMap.get(entry.getKey()) < entry.getValue()) {
                        satisfied = false;
                        break;
                    }
                }
            }

        }

        return s.substring(start, end + 1);
    }
}
