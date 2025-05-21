package com.java.code.greedy;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>There are <code>n</code> gas stations along a circular route, where the amount of gas at the <code>i<sup>th</sup></code> station is <code>gas[i]</code>.</p>

 <p>You have a car with an unlimited gas tank and it costs <code>cost[i]</code> of gas to travel from the <code>i<sup>th</sup></code> station to its next <code>(i + 1)<sup>th</sup></code> station. You begin the journey with an empty tank at one of the gas stations.</p>

 <p>Given two integer arrays <code>gas</code> and <code>cost</code>, return <em>the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return</em> <code>-1</code>. If there exists a solution, it is <strong>guaranteed</strong> to be <strong>unique</strong>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 <strong>Output:</strong> 3
 <strong>Explanation:</strong>
 Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 Travel to station 4. Your tank = 4 - 1 + 5 = 8
 Travel to station 0. Your tank = 8 - 2 + 1 = 7
 Travel to station 1. Your tank = 7 - 3 + 2 = 6
 Travel to station 2. Your tank = 6 - 4 + 3 = 5
 Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 Therefore, return 3 as the starting index.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> gas = [2,3,4], cost = [3,4,3]
 <strong>Output:</strong> -1
 <strong>Explanation:</strong>
 You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 Travel to station 0. Your tank = 4 - 3 + 2 = 3
 Travel to station 1. Your tank = 3 - 3 + 3 = 3
 You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
 Therefore, you can't travel around the circuit once no matter where you start.
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>n == gas.length == cost.length</code></li>
 <li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
 <li><code>0 &lt;= gas[i], cost[i] &lt;= 10<sup>4</sup></code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Greedy</li></div></div><br><div><li>👍 12668</li><li>👎 1289</li></div>
 */
public class GasStation extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}, 3},
                {new int[]{2,3,4}, new int[]{3,4,3}, -1},
                {new int[]{5,1,2,3,4}, new int[]{4,4,1,5,1}, 4},
                {new int[]{5,8,2,8}, new int[]{6,5,6,6}, 3},
                {new int[]{3,1,1}, new int[]{1,2,2}, 0},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] gas, int[] cost, int expected) {
        softAssert.as("gas = %s, cost = %s", Arrays.toString(gas), Arrays.toString(cost))
                  .assertThat(canCompleteCircuit(gas, cost))
                  .isEqualTo(expected);
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int remainedGas = 0;
        int startingIndex = 0;
        for (int i = 0; i < gas.length; i++) {
            int currentGas = gas[i] - cost[i];
            totalGas += currentGas;
            if (remainedGas < 0) {
                remainedGas = 0;
                startingIndex = i;
            }
            remainedGas += currentGas;
        }
        if (totalGas < 0) {
            return -1;
        }
        return startingIndex;
    }

//    public int canCompleteCircuit(int[] gas, int[] cost) {
//        for (int i = 0; i < gas.length; i++) {
//            int result = canCompleteCircuit(gas, cost, -1, i, 0);
//            if (result != -1) {
//                return result;
//            }
//        }
//        return -1;
//    }
//
//    public int canCompleteCircuit(int[] gas, int[] cost, int currentIndex, int startIndex, int currentGas) {
//        if (currentIndex == startIndex) {
//            return startIndex;
//        } else if (currentIndex == -1) {
//            currentIndex = startIndex;
//        }
//
//        int nextIndex = currentIndex + 1 > gas.length - 1 ? 0 : currentIndex + 1;
//        int nextGas = currentGas + gas[currentIndex] - cost[currentIndex];
//        if (nextGas >= 0) {
//            return canCompleteCircuit(gas, cost, nextIndex, startIndex, nextGas);
//        }
//        return -1;
//    }
}
