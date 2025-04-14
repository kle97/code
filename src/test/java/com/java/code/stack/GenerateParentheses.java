package com.java.code.stack;

import com.java.code.common.BaseTest;
import lombok.experimental.Accessors;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Given<span>&nbsp;</span><code>n</code><span>&nbsp;</span>pairs of parentheses, write a function to<span>&nbsp;</span><em>generate all combinations of well-formed parentheses</em>.</p>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input:</strong> n = 3
 * <strong>Output:</strong> ["((()))","(()())","(())()","()(())","()()()"]
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre><strong>Input:</strong> n = 1
 * <strong>Output:</strong> ["()"]
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;= n &lt;= 8</code></li>
 * </ul>
 */
public class GenerateParentheses extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {3, List.of("((()))", "(()())", "(())()", "()(())", "()()()")},
                {1, List.of("()")},
        };
    }

    @Test(dataProvider = "data")
    public void test(int n, List<String> expected) {
        softAssert.as(String.format("n = %d", n))
                  .assertThat(generateParenthesis(n))
                  .isEqualTo(expected);
    }

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        recursion(list, "", 0, 0, n);
        return list;
    }

    public void recursion(List<String> list, String str, int opened, int closed, int n) {
        if (opened < n) {
            for (int i = 0; i <= opened - closed; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < i; j++) {
                    sb.append(")");
                }

                recursion(list, str + sb + "(", opened + 1, closed + i, n);
            }
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= opened - closed; i++) {
                sb.append(")");
            }
            str += sb;
            list.add(str);
        }
    }
}
