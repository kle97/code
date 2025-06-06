package com.java.code.heap;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 <p>You are given an array of integers <code>stones</code> where <code>stones[i]</code> is the weight of the <code>i<sup>th</sup></code> stone.</p>

 <p>We are playing a game with the stones. On each turn, we choose the <strong>heaviest two stones</strong> and smash them together. Suppose the heaviest two stones have weights <code>x</code> and <code>y</code> with <code>x &lt;= y</code>. The result of this smash is:</p>

 <ul>
 <li>If <code>x == y</code>, both stones are destroyed, and</li>
 <li>If <code>x != y</code>, the stone of weight <code>x</code> is destroyed, and the stone of weight <code>y</code> has new weight <code>y - x</code>.</li>
 </ul>

 <p>At the end of the game, there is <strong>at most one</strong> stone left.</p>

 <p>Return <em>the weight of the last remaining stone</em>. If there are no stones left, return <code>0</code>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> stones = [2,7,4,1,8,1]
 <strong>Output:</strong> 1
 <strong>Explanation:</strong>
 We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
 we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
 we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> stones = [1]
 <strong>Output:</strong> 1
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= stones .length &lt;= 30</code></li>
 <li><code>1 &lt;= stones[i] &lt;= 1000</code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Heap (Priority Queue)</li></div></div><br><div><li>👍 6294</li><li>👎 145</li></div>
 */
public class LastStoneWeight extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {2,7,4,1,8,1}, 1},
                {new int[] {1}, 1},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] stones, int expected) {
        softAssert.as(String.format("stones = %s", Arrays.toString(stones)))
                  .assertThat(lastStoneWeight(stones))
                  .isEqualTo(expected);
    }

    public int lastStoneWeight(int[] stones) {
        if (stones.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < stones.length; i++) {
            pq.offer(stones[i]);
        }

        while (pq.size() > 1) {
            int largestStone = pq.poll();
            int secondLargestStone = pq.poll();
            int newStone = largestStone - secondLargestStone;
            pq.offer(newStone);
        }
        return pq.peek();
    }
}
