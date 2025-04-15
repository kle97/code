package com.java.code.twopointers;


import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 <p>A phrase is a <strong>palindrome</strong> if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.</p>

 <p>Given a string <code>s</code>, return <code>true</code><em> if it is a <strong>palindrome</strong>, or </em><code>false</code><em> otherwise</em>.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> s = "A man, a plan, a canal: Panama"
 <strong>Output:</strong> true
 <strong>Explanation:</strong> "amanaplanacanalpanama" is a palindrome.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> s = "race a car"
 <strong>Output:</strong> false
 <strong>Explanation:</strong> "raceacar" is not a palindrome.
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> s = " "
 <strong>Output:</strong> true
 <strong>Explanation:</strong> s is an empty string "" after removing non-alphanumeric characters.
 Since an empty string reads the same forward and backward, it is a palindrome.
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li> 
 <li><code>s</code> consists only of printable ASCII characters.</li> 
 </ul>

 <div><div>Related Topics</div><div><li>Two Pointers</li><li>String</li></div></div><br><div><li>👍 8829</li><li>👎 8223</li></div>
 */
public class ValidPalindrome extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"A man, a plan, a canal: Panama", true},
                {"race a car", false},
                {" ", true},
                {"a", true},
                {"aa", true},
                {".,", true},
                {"0P", false},
        };
    }

    @Test(dataProvider = "data")
    public void test(String s, boolean expected) {
        softAssert.as(String.format("s = %s", s))
                  .assertThat(isPalindrome(s))
                  .isEqualTo(expected);
    }
    
    public boolean isPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        if (s.isEmpty()) {
            return true;
        }

        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
