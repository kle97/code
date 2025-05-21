package com.java.code.bitmanipulation;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 <p>Reverse bits of a given 32 bits unsigned integer.</p>

 <p><strong>Note:</strong></p>

 <ul>
 <li>Note that in some languages, such as Java, there is no unsigned integer type. In this case, both input and output will be given as a signed integer type. They should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.</li>
 <li>In Java, the compiler represents the signed integers using <a href="https://en.wikipedia .org/wiki/Two%27s_complement" target="_blank">2's complement notation</a>. Therefore, in <strong class="example">Example 2</strong> above, the input represents the signed integer <code>-3</code> and the output represents the signed integer <code>-1073741825</code>.</li>
 </ul>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> n = 00000010100101000001111010011100
 <strong>Output:</strong>    964176192 (00111001011110000010100101000000)
 <strong>Explanation: </strong>The input binary string <strong>00000010100101000001111010011100</strong> represents the unsigned integer 43261596, so return 964176192 which its binary representation is <strong>00111001011110000010100101000000</strong>.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> n = 11111111111111111111111111111101
 <strong>Output:</strong>   3221225471 (10111111111111111111111111111111)
 <strong>Explanation: </strong>The input binary string <strong>11111111111111111111111111111101</strong> represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is <strong>10111111111111111111111111111111</strong>.
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li>The input must be a <strong>binary string</strong> of length <code>32</code></li>
 </ul>

 <p>&nbsp;</p>
 <p><strong>Follow up:</strong> If this function is called many times, how would you optimize it?</p>

 <div><div>Related Topics</div><div><li>Divide and Conquer</li><li>Bit Manipulation</li></div></div><br><div><li>👍 5330</li><li>👎 1569</li></div>
 */
public class ReverseBits extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"00000010100101000001111010011100", 964176192L},
                {"11111111111111111111111111111101", 3221225471L},
        };
    }

    @Test(dataProvider = "data")
    public void test(String n, long expected) {
        softAssert.as(String.format("n = %s", n))
                  .assertThat(reverseBits(convert(n)))
                  .isEqualTo(expected);
    }

    public long convert(String n) {
        long result = Integer.parseUnsignedInt(n, 2);
        if (result < 0) {
            return result & 0x00000000ffffffffL;
        }
        return result;
    }

    public long reverseBits(long n) {
        long result = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                result++;
            }
            n = n >>> 1;
            if (i < 31) {
                result = result << 1;
            }
        }

        System.out.println(result);
        return result;
    }
}
