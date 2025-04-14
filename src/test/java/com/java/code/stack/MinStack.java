package com.java.code.stack;

import com.java.code.common.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <p>Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.</p>
 * <p>Implement the<span>&nbsp;</span><code>MinStack</code><span>&nbsp;</span>class:</p>
 * <ul>
 * <li><code>MinStack()</code><span>&nbsp;</span>initializes the stack object.</li>
 * <li><code>void push(int val)</code><span>&nbsp;</span>pushes the element<span>&nbsp;</span><code>val</code><span>&nbsp;</span>onto the stack.</li>
 * <li><code>void pop()</code><span>&nbsp;</span>removes the element on the top of the stack.</li>
 * <li><code>int top()</code><span>&nbsp;</span>gets the top element of the stack.</li>
 * <li><code>int getMin()</code><span>&nbsp;</span>retrieves the minimum element in the stack.</li>
 * </ul>
 * <p>You must implement a solution with<span>&nbsp;</span><code>O(1)</code><span>&nbsp;</span>time complexity for each function.</p>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input</strong>
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * <strong>Output</strong>
 * [null,null,null,null,-3,null,0,-2]
 *
 * <strong>Explanation</strong>
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>-2<sup>31</sup> &lt;= val &lt;= 2<sup>31</sup> - 1</code></li>
 * <li>Methods<span>&nbsp;</span><code>pop</code>,<span>&nbsp;</span><code>top</code><span>&nbsp;</span>and<span>&nbsp;</span><code>getMin</code><span>&nbsp;</span>operations will always be called on<span>&nbsp;</span><strong>non-empty</strong><span>&nbsp;</span>stacks.</li>
 * <li>At most<span>&nbsp;</span><code>3 * 10<sup>4</sup></code><span>&nbsp;</span>calls will be made to<span>&nbsp;</span><code>push</code>,<span>&nbsp;</span><code>pop</code>,<span>&nbsp;</span><code>top</code>, and<span>&nbsp;</span><code>getMin</code>.</li>
 * </ul>
 */
public class MinStack extends BaseTest {

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(val);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
    @Test
    public void test() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        softAssert.as("getMin()").assertThat(minStack.getMin()).isEqualTo(-3);
        minStack.pop();
        softAssert.as("top()").assertThat(minStack.top()).isEqualTo(0);
        softAssert.as("getMin()").assertThat(minStack.getMin()).isEqualTo(-2);
    }

    private final Deque<Min> deque = new ArrayDeque<>();

    public MinStack() {
    }

    public void push(int val) {
        if (deque.isEmpty()) {
            deque.offerLast(new Min(val, val));
        } else {
            deque.offerLast(new Min(val, Math.min(deque.peekLast().minVal, val)));
        }
    }

    public void pop() {
        deque.pollLast();
    }

    public int top() {
        if (deque.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return deque.peekLast().val;
        }
    }

    public int getMin() {
        if (deque.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return deque.peekLast().minVal;
        }
    }

    static class Min {
        private final int val;
        private final int minVal;

        public Min(int val, int minVal) {
            this.val = val;
            this.minVal = minVal;
        }
    }
}
