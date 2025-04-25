package com.java.code.stack;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * <p>You are given an array of strings<span>&nbsp;</span><code>tokens</code><span>&nbsp;</span>that represents an arithmetic expression in a<span>&nbsp;</span><a href="http://en.wikipedia.org/wiki/Reverse_Polish_notation" target="_blank" rel="noopener">Reverse Polish Notation</a>.</p>
 * <p>Evaluate the expression. Return<span>&nbsp;</span><em>an integer that represents the value of the expression</em>.</p>
 * <p><strong>Note</strong><span>&nbsp;</span>that:</p>
 * <ul>
 * <li>The valid operators are<span>&nbsp;</span><code>'+'</code>,<span>&nbsp;</span><code>'-'</code>,<span>&nbsp;</span><code>'*'</code>, and<span>&nbsp;</span><code>'/'</code>.</li>
 * <li>Each operand may be an integer or another expression.</li>
 * <li>The division between two integers always<span>&nbsp;</span><strong>truncates toward zero</strong>.</li>
 * <li>There will not be any division by zero.</li>
 * <li>The input represents a valid arithmetic expression in a reverse polish notation.</li>
 * <li>The answer and all the intermediate calculations can be represented in a<span>&nbsp;</span><strong>32-bit</strong><span>&nbsp;</span>integer.</li>
 * </ul>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input:</strong> tokens = ["2","1","+","3","*"]
 * <strong>Output:</strong> 9
 * <strong>Explanation:</strong> ((2 + 1) * 3) = 9
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre><strong>Input:</strong> tokens = ["4","13","5","/","+"]
 * <strong>Output:</strong> 6
 * <strong>Explanation:</strong> (4 + (13 / 5)) = 6
 * </pre>
 * <p><strong class="example">Example 3:</strong></p>
 * <pre><strong>Input:</strong> tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * <strong>Output:</strong> 22
 * <strong>Explanation:</strong> ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;= tokens .length &lt;= 10<sup>4</sup></code></li>
 * <li><code>tokens[i]</code><span>&nbsp;</span>is either an operator:<span>&nbsp;</span><code>"+"</code>,<span>&nbsp;</span><code>"-"</code>,<span>&nbsp;</span><code>"*"</code>, or<span>&nbsp;</span><code>"/"</code>, or an integer in the range<span>&nbsp;</span><code>[-200, 200]</code>.</li>
 * </ul>
 */
public class EvaluateReversePolishNotation extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new String[] {"2","1","+","3","*"}, 9},
                {new String[] {"4","13","5","/","+"}, 6},
                {new String[] {"10","6","9","3","+","-11","*","/","*","17","+","5","+"}, 22},
        };
    }

    @Test(dataProvider = "data")
    public void test(String[] tokens, int expected) {
        softAssert.as(String.format("tokens = %s", Arrays.toString(tokens)))
                  .assertThat(evalRPN(tokens))
                  .isEqualTo(expected);
    }

    public int evalRPN(String[] tokens) {
        Deque<String> deque = new ArrayDeque<>();
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                if (token.equals("+")) {
                    int first = Integer.parseInt(deque.pollLast());
                    int second = Integer.parseInt(deque.pollLast());
                    deque.offerLast(String.valueOf(first + second));
                } else if (token.equals("-")) {
                    int first = Integer.parseInt(deque.pollLast());
                    int second = Integer.parseInt(deque.pollLast());
                    deque.offerLast(String.valueOf(second - first));
                } if (token.equals("*")) {
                    int first = Integer.parseInt(deque.pollLast());
                    int second = Integer.parseInt(deque.pollLast());
                    deque.offerLast(String.valueOf(first * second));
                } else if (token.equals("/")) {
                    int first = Integer.parseInt(deque.pollLast());
                    int second = Integer.parseInt(deque.pollLast());
                    deque.offerLast(String.valueOf(second / first));
                }
            } else {
                deque.offerLast(tokens[i]);
            }
        }

        return Integer.parseInt(deque.pollLast());
    }
}
