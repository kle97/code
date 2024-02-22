package com.java.code.twopointers;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 <p>You are given two strings <code>word1</code> and <code>word2</code>. Merge the strings by adding letters in alternating order, starting with <code>word1</code>. If a string is longer than the other, append the additional letters onto the end of the merged string.</p>

 <p>Return <em>the merged string.</em></p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> word1 = "abc", word2 = "pqr"
 <strong>Output:</strong> "apbqcr"
 <strong>Explanation:</strong>&nbsp;The merged string will be merged as so:
 word1:  a   b   c
 word2:    p   q   r
 merged: a p b q c r
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> word1 = "ab", word2 = "pqrs"
 <strong>Output:</strong> "apbqrs"
 <strong>Explanation:</strong>&nbsp;Notice that as word2 is longer, "rs" is appended to the end.
 word1:  a   b 
 word2:    p   q   r   s
 merged: a p b q   r   s
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> word1 = "abcd", word2 = "pq"
 <strong>Output:</strong> "apbqcd"
 <strong>Explanation:</strong>&nbsp;Notice that as word1 is longer, "cd" is appended to the end.
 word1:  a   b   c   d
 word2:    p   q 
 merged: a p b q c   d
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= word1.length, word2.length &lt;= 100</code></li> 
 <li><code>word1</code> and <code>word2</code> consist of lowercase English letters.</li> 
 </ul>

 <div><div>Related Topics</div><div><li>Two Pointers</li><li>String</li></div></div><br><div><li>👍 3500</li><li>👎 72</li></div>
 */
public class MergeStringsAlternately extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"abc", "pqr", "apbqcr"},
                {"abcd", "pq", "apbqcd"},
        };
    }

    @Test(dataProvider = "data")
    public void test(String word1, String word2, String expected) {
        softAssert.as(String.format("word1 = %s, word2 = %s", word1, word2))
                  .assertThat(mergeAlternately(word1, word2))
                  .isEqualTo(expected);
    }

    public String mergeAlternately(String word1, String word2) {
        int length = word1.length() + word2.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        int j = 0;
        for (int k = 0; k < length; k++) {
            if (i < word1.length()) {
                sb.append(word1.charAt(i));
                i++;
            }

            if (j < word2.length()) {
                sb.append(word2.charAt(j));
                j++;
            }
        }
        return sb.toString();
    }
}
