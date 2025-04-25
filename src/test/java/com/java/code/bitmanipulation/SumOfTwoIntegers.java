package com.java.code.bitmanipulation;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 <p>Given two integers <code>a</code> and <code>b</code>, return <em>the sum of the two integers without using the operators</em> <code>+</code> <em>and</em> <code>-</code>.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p> 
 <pre><strong>Input:</strong> a = 1, b = 2
 <strong>Output:</strong> 3
 </pre>
 <p><strong class="example">Example 2:</strong></p> 
 <pre><strong>Input:</strong> a = 2, b = 3
 <strong>Output:</strong> 5
 </pre> 
 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>-1000 &lt;= a, b &lt;= 1000</code></li> 
 </ul>

 <div><div>Related Topics</div><div><li>Math</li><li>Bit Manipulation</li></div></div><br><div><li>👍 4437</li><li>👎 5688</li></div>
 */
public class SumOfTwoIntegers extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {1, 2, 3},
                {2, 3, 5},
                {99999, 1, 100000},
        };
    }

    @Test(dataProvider = "data")
    public void test(int a, int b, int expected) {
        softAssert.as(String.format("a = %d, b = %d", a, b))
                  .assertThat(getSum(a, b))
                  .isEqualTo(expected);
    }

    public int getSum(int a, int b) {
        while (b != 0) {
            int sumWithoutCarry = a ^ b;
            int carry = (a & b) << 1;
            a = sumWithoutCarry;
            b = carry;
        }
        return a;
    }
}
