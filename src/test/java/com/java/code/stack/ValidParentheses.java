package com.java.code.stack;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <p>Given a string<span>&nbsp;</span><code>s</code><span>&nbsp;</span>containing just the characters<span>&nbsp;</span><code>'('</code>,<span>&nbsp;</span><code>')'</code>,<span>&nbsp;</span><code>'{'</code>,<span>&nbsp;</span><code>'}'</code>,<span>&nbsp;</span><code>'['</code><span>&nbsp;</span>and<span>&nbsp;</span><code>']'</code>, determine if the input string is valid.</p>
 * <p>An input string is valid if:</p>
 * <ol>
 * <li>Open brackets must be closed by the same type of brackets.</li>
 * <li>Open brackets must be closed in the correct order.</li>
 * <li>Every close bracket has a corresponding open bracket of the same type.</li>
 * </ol>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input:</strong> s = "()"
 * <strong>Output:</strong> true
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre><strong>Input:</strong> s = "()[]{}"
 * <strong>Output:</strong> true
 * </pre>
 * <p><strong class="example">Example 3:</strong></p>
 * <pre><strong>Input:</strong> s = "(]"
 * <strong>Output:</strong> false
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;= s .length &lt;= 10<sup>4</sup></code></li>
 * <li><code>s</code><span>&nbsp;</span>consists of parentheses only<span>&nbsp;</span><code>'()[]{}'</code>.</li>
 * </ul>
 */
public class ValidParentheses extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"()", true},
                {"()[]{}", true},
                {"(]", false},
                {"{[]}", true},
                {"[", false},
        };
    }

    @Test(dataProvider = "data")
    public void test(String s, boolean expected) {
        softAssert.as(String.format("s = %s", s))
                  .assertThat(isValid(s))
                  .isEqualTo(expected);
    }

    public boolean isValid(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                deque.offerLast(ch);
            } else if (ch == ')') {
                if (!deque.isEmpty() && deque.peekLast() == '(') {
                    deque.pollLast();
                } else {
                    return false;
                }
            } else if (ch == ']') {
                if (!deque.isEmpty() && deque.peekLast() == '[') {
                    deque.pollLast();
                } else {
                    return false;
                }
            } else if (ch == '}') {
                if (!deque.isEmpty() && deque.peekLast() == '{') {
                    deque.pollLast();
                } else {
                    return false;
                }
            }
        }

        return deque.isEmpty();
    }
}
