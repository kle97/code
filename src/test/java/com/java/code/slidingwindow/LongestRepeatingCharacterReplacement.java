package com.java.code.slidingwindow;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 <p>You are given a string <code>s</code> and an integer <code>k</code>. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most <code>k</code> times.</p>

 <p>Return <em>the length of the longest substring containing the same letter you can get after performing the above operations</em>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> s = "ABAB", k = 2
 <strong>Output:</strong> 4
 <strong>Explanation:</strong> Replace the two 'A's with two 'B's or vice versa.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> s = "AABABBA", k = 1
 <strong>Output:</strong> 4
 <strong>Explanation:</strong> Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 The substring "BBBB" has the longest repeating letters, which is 4.
 There may exists other ways to achieve this answer too.</pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
 <li><code>s</code> consists of only uppercase English letters.</li>
 <li><code>0 &lt;= k &lt;= s.length</code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Hash Table</li><li>String</li><li>Sliding Window</li></div></div><br><div><li>👍 10153</li><li>👎 470</li></div>
 */
public class LongestRepeatingCharacterReplacement extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"ABAB", 2, 4},
                {"AABABBA", 1, 4},
                {"IMNJJTRMJEGMSOLSCCQICIHLQIOGBJAEHQOCRAJQMBIBATGLJDTBNCPIFRDLRIJHRABBJGQAOLIKRLHDRIGERENNMJSDSSMESSTR", 2, 6},
                {"EOEMQLLQTRQDDCOERARHGAAARRBKCCMFTDAQOLOKARBIJBISTGNKBQGKKTALSQNFSABASNOPBMMGDIOETPTDICRBOMBAAHINTFLH", 7, 11},
        };
    }

    @Test(dataProvider = "data")
    public void test(String s, int k, int expected) {
        softAssert.as(String.format("s = %s, k = %d", s, k))
                  .assertThat(characterReplacement(s, k))
                  .isEqualTo(expected);
    }

    public int characterReplacement(String s, int k) {
        int longest = 0;
        Map<Character, Integer> map = new HashMap<>();
        int diff = 0;
        int lastWindowIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            int max = 0;
            int sum = 0;
            for (int value : map.values()) {
                max = Math.max(max, value);
                sum += value;
            }
            diff = sum - max;

            while (diff > k) {
                char lastChar = s.charAt(lastWindowIndex);
                map.put(lastChar, Math.max(0, map.get(lastChar) - 1));
                lastWindowIndex++;
                max = 0;
                sum = 0;
                for (int value : map.values()) {
                    max = Math.max(max, value);
                    sum += value;
                }
                diff = sum - max;
            }

            longest = Math.max(longest, sum);
        }

        return longest;
    }
}
