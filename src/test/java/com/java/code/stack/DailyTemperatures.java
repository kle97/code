package com.java.code.stack;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * <p>Given an array of integers<span>&nbsp;</span><code>temperatures</code><span>&nbsp;</span>represents the daily temperatures, return<span>&nbsp;</span><em>an array</em><span>&nbsp;</span><code>answer</code><span>&nbsp;</span><em>such that</em><span>&nbsp;</span><code>answer[i]</code><span>&nbsp;</span><em>is the number of days you have to wait after the</em><span>&nbsp;</span><code>i<sup>th</sup></code><span>&nbsp;</span><em>day to get a warmer temperature</em>. If there is no future day for which this is possible, keep<span>&nbsp;</span><code>answer[i] == 0</code><span>&nbsp;</span>instead.</p>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input:</strong> temperatures = [73,74,75,71,69,72,76,73]
 * <strong>Output:</strong> [1,1,4,2,1,1,0,0]
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre><strong>Input:</strong> temperatures = [30,40,50,60]
 * <strong>Output:</strong> [1,1,1,0]
 * </pre>
 * <p><strong class="example">Example 3:</strong></p>
 * <pre><strong>Input:</strong> temperatures = [30,60,90]
 * <strong>Output:</strong> [1,1,0]
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;=&nbsp;temperatures.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>30 &lt;=&nbsp;temperatures[i] &lt;= 100</code></li>
 * </ul>
 */
public class DailyTemperatures extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {73,74,75,71,69,72,76,73}, new int[]{1,1,4,2,1,1,0,0}},
                {new int[] {30,40,50,60}, new int[]{1,1,1,0}},
                {new int[] {30,60,90}, new int[]{1,1,0}},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] temperatures, int[] expected) {
        softAssert.as(String.format("temperatures = %s", Arrays.toString(temperatures)))
                  .assertThat(dailyTemperatures(temperatures))
                  .isEqualTo(expected);
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];

        Deque<Temperature> deque = new ArrayDeque<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!deque.isEmpty() && deque.peekLast().temperature < temperatures[i]) {
                Temperature current = deque.pollLast();
                result[current.index] = i - current.index;
            }

            deque.offerLast(new Temperature(temperatures[i], i));
        }

        return result;
    }

    static class Temperature {
        private int temperature;
        private int index;

        public Temperature(int temperature, int index) {
            this.temperature = temperature;
            this.index = index;
        }
    }
}
