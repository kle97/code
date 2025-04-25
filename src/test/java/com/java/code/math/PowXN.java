package com.java.code.math;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 <p>Implement <a href="http://www.cplusplus.com/reference/valarray/pow/" target="_blank">pow(x, n)</a>, which calculates <code>x</code> raised to the power <code>n</code> (i.e., <code>x<sup>n</sup></code>).</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> x = 2.00000, n = 10
 <strong>Output:</strong> 1024.00000
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> x = 2.10000, n = 3
 <strong>Output:</strong> 9.26100
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> x = 2.00000, n = -2
 <strong>Output:</strong> 0.25000
 <strong>Explanation:</strong> 2<sup>-2</sup> = 1/2<sup>2</sup> = 1/4 = 0.25
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>-100.0 &lt; x &lt; 100 .0</code></li>
 <li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup>-1</code></li>
 <li><code>n</code> is an integer.</li>
 <li>Either <code>x</code> is not zero or <code>n &gt; 0</code>.</li>
 <li><code>-10<sup>4</sup> &lt;= x<sup>n</sup> &lt;= 10<sup>4</sup></code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Math</li><li>Recursion</li></div></div><br><div><li>👍 10614</li><li>👎 10108</li></div>
 */
public class PowXN extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {2.00000, 10, 1024.00000},
                {2.10000, 3, 9.26100},
                {2.00000, -2, 0.25000},
                {-2.00000, 1, -2.00000},
                {-2.00000, -3, -0.12500},
                {0.00001, 2147483647, Math.pow(0.00001, 2147483647)},
                {1.00000, 2147483647, Math.pow(1.00000, 2147483647)},
                {2.00000, -2147483648, Math.pow(2.00000, -2147483648)},
                {-1.00000, 2147483647, Math.pow(-1.00000, 2147483647)},
                {-1.00000, -2147483648, Math.pow(-1.00000, -2147483648)},
        };
    }

    @Test(dataProvider = "data")
    public void test(double x, long n, double expected) {
        softAssert.as(String.format("x = %f, n = %d", x, n))
                  .assertThat(((double) (int) (myPow(x, n) * 100000.00)) / 100000.0)
                  .isEqualTo(expected);
    }

    public double myPow(double x, long n) {
        if (n == 0) {
            return 1;
        } else if (n < 0) {
            return 1 / myPow(x, -n);
        }

        double result = myPow(x, n / 2);
        if (n % 2 != 0) {
            result = x * result * result;
        } else {
            result = result * result;
        }
        return result;
    }

//    public double myPow(double x, long n) {
//        double result = 1;
//        double lastResult = 0;
//        long m = Math.abs(n);
//        for (long i = 1; i <= m; i++) {
//            if (n >= 0) {
//                result *= x;
//            } else {
//                result *= 1/x;
//            }
//            if (Math.abs(result) == Math.abs(lastResult)) {
//                if (x < 0) {
//                    return n % 2 == 0 ? Math.abs(result) : -Math.abs(result);
//                } else {
//                    return result;
//                }
//            }
//            lastResult = result;
//        }
//        return result;
//    }
}
