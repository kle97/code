package com.java.code.bitmanipulation;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 <p>Given a positive integer <code>n</code>, write a function that returns the number of <span data-keyword="set-bit">set bits</span> in its binary representation (also known as the <a href="http://en.wikipedia.org/wiki/Hamming_weight" target="_blank">Hamming weight</a>).</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <div class="example-block">
 <p><strong>Input:</strong> <span class="example-io">n = 11</span></p>
 </div>

 <p><strong>Output:</strong> <span class="example-io">3</span></p>

 <p><strong>Explanation:</strong></p>

 <p>The input binary string <strong>1011</strong> has a total of three set bits.</p>

 <p><strong class="example">Example 2:</strong></p>

 <div class="example-block">
 <p><strong>Input:</strong> <span class="example-io">n = 128</span></p>
 </div>

 <p><strong>Output:</strong> <span class="example-io">1</span></p>

 <p><strong>Explanation:</strong></p>

 <p>The input binary string <strong>10000000</strong> has a total of one set bit.</p>

 <p><strong class="example">Example 3:</strong></p>

 <div class="example-block">
 <p><strong>Input:</strong> <span class="example-io">n = 2147483645</span></p>
 </div>

 <p><strong>Output:</strong> <span class="example-io">30</span></p>

 <p><strong>Explanation:</strong></p>

 <p>The input binary string <strong>1111111111111111111111111111101</strong> has a total of thirty set bits.</p>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
 </ul>

 <p>&nbsp;</p>
 <strong>Follow up:</strong> If this function is called many times, how would you optimize it?

 <div><div>Related Topics</div><div><li>Divide and Conquer</li><li>Bit Manipulation</li></div></div><br><div><li>👍 6792</li><li>👎 1347</li></div>
 */
public class NumberOfOneBits extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {11, 3},
                {128, 1},
                {2147483645, 30},
        };
    }

    @Test(dataProvider = "data")
    public void test(int n, int expected) {
        softAssert.as(String.format("n = %d", n))
                  .assertThat(hammingWeight(n))
                  .isEqualTo(expected);
    }

    public int hammingWeight(int n) {
        int numberOf1s = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                numberOf1s++;
            }
            n = n >>> 1;
        }
        return numberOf1s;
    }
}
