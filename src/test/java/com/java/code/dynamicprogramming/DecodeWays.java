package com.java.code.dynamicprogramming;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 <p>You have intercepted a secret message encoded as a string of numbers. The message is <strong>decoded</strong> via the following mapping:</p>

 <p><code>"1" -&gt; 'A'<br /> "2" -&gt; 'B'<br /> ...<br /> "25" -&gt; 'Y'<br /> "26" -&gt; 'Z'</code></p>

 <p>However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes (<code>"2"</code> and <code>"5"</code> vs <code>"25"</code>).</p>

 <p>For example, <code>"11106"</code> can be decoded into:</p>

 <ul>
 <li><code>"AAJF"</code> with the grouping <code>(1, 1, 10, 6)</code></li>
 <li><code>"KJF"</code> with the grouping <code>(11, 10, 6)</code></li>
 <li>The grouping <code>(1, 11, 06)</code> is invalid because <code>"06"</code> is not a valid code (only <code>"6"</code> is valid).</li>
 </ul>

 <p>Note: there may be strings that are impossible to decode.<br /> <br /> Given a string s containing only digits, return the <strong>number of ways</strong> to <strong>decode</strong> it. If the entire string cannot be decoded in any valid way, return <code>0</code>.</p>

 <p>The test cases are generated so that the answer fits in a <strong>32-bit</strong> integer.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <div class="example-block">
 <p><strong>Input:</strong> <span class="example-io">s = "12"</span></p>
 </div>

 <p><strong>Output:</strong> <span class="example-io">2</span></p>

 <p><strong>Explanation:</strong></p>

 <p>"12" could be decoded as "AB" (1 2) or "L" (12).</p>

 <p><strong class="example">Example 2:</strong></p>

 <div class="example-block">
 <p><strong>Input:</strong> <span class="example-io">s = "226"</span></p>
 </div>

 <p><strong>Output:</strong> <span class="example-io">3</span></p>

 <p><strong>Explanation:</strong></p>

 <p>"226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).</p>

 <p><strong class="example">Example 3:</strong></p>

 <div class="example-block">
 <p><strong>Input:</strong> <span class="example-io">s = "06"</span></p>
 </div>

 <p><strong>Output:</strong> <span class="example-io">0</span></p>

 <p><strong>Explanation:</strong></p>

 <p>"06" cannot be mapped to "F" because of the leading zero ("6" is different from "06"). In this case, the string is not a valid encoding, so return 0.</p>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= s.length &lt;= 100</code></li>
 <li><code>s</code> contains only digits and may contain leading zero(s).</li>
 </ul>

 <div><div>Related Topics</div><div><li>String</li><li>Dynamic Programming</li></div></div><br><div><li>👍 12382</li><li>👎 4575</li></div>
 */
public class DecodeWays extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"12", 2},
                {"226", 3},
                {"06", 0},
                {"10", 1},
                {"99", 1},
                {"2101", 1},
                {"301", 0},
        };
    }

    @Test(dataProvider = "data")
    public void test(String s, int expected) {
        softAssert.as(String.format("s = %s", s))
                  .assertThat(numDecodings(s))
                  .isEqualTo(expected);
    }

    public int numDecodings(String s) {
        return numDecodings(s, 0, null, new Integer[s.length()][100]);
    }

    public int numDecodings(String s, int currentIndex, Integer lastCode, Integer[][] memoized) {
        if (currentIndex == s.length()) {
            return lastCode != null && lastCode == 0 ? 0 : 1;
        }

        if (s.charAt(currentIndex) == '0' && lastCode == null) {
            return 0;
        } else if (lastCode != null && lastCode == '0') {
            return 0;
        }

        int currentCodeIndex = lastCode == null ? 0 : lastCode;
        if (memoized[currentIndex][currentCodeIndex] == null) {
            int count = 0;
            int combinedCode = lastCode == null
                    ? Integer.parseInt(String.valueOf(s.charAt(currentIndex)))
                    : Integer.parseInt(String.valueOf(lastCode) + s.charAt(currentIndex));
            if (combinedCode <= 26 && combinedCode >= 10) {
                count += numDecodings(s, currentIndex + 1, null, memoized);
                if (s.charAt(currentIndex) != '0') {
                    count += numDecodings(s, currentIndex + 1, Integer.parseInt(String.valueOf(s.charAt(currentIndex))), memoized);
                }
            } else if (s.charAt(currentIndex) != '0') {
                count += numDecodings(s, currentIndex + 1, Integer.parseInt(String.valueOf(s.charAt(currentIndex))), memoized);
            }
            memoized[currentIndex][currentCodeIndex] = count;
        }
        return memoized[currentIndex][currentCodeIndex];
    }
}
