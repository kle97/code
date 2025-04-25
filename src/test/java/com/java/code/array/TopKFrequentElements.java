package com.java.code.array;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 <p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the</em> <code>k</code> <em>most frequent elements</em>. You may return the answer in <strong>any order</strong>.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p> 
 <pre><strong>Input:</strong> nums = [1,1,1,2,2,3], k = 2
 <strong>Output:</strong> [1,2]
 </pre>
 <p><strong class="example">Example 2:</strong></p> 
 <pre><strong>Input:</strong> nums = [1], k = 1
 <strong>Output:</strong> [1]
 </pre> 
 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= nums .length &lt;= 10<sup>5</sup></code></li> 
 <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
 <li><code>k</code> is in the range <code>[1, the number of unique elements in the array]</code>.</li> 
 <li>It is <strong>guaranteed</strong> that the answer is <strong>unique</strong>.</li> 
 </ul>

 <p>&nbsp;</p> 
 <p><strong>Follow up:</strong> Your algorithm's time complexity must be better than <code>O(n log n)</code>, where n is the array's size.</p>

 <div><div>Related Topics</div><div><li>Array</li><li>Hash Table</li><li>Divide and Conquer</li><li>Sorting</li><li>Heap (Priority Queue)</li><li>Bucket Sort</li><li>Counting</li><li>Quickselect</li></div></div><br><div><li>👍 16706</li><li>👎 617</li></div>
 */
public class TopKFrequentElements extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {1,1,1,2,2,3}, 2, new int[]{1,2}},
                {new int[] {1}, 1, new int[]{1}},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] nums, int k, int[] expected) {
        softAssert.as(String.format("nums = %s, k = %d", Arrays.toString(nums), k))
                  .assertThat(Arrays.stream(topKFrequent(nums, k)).boxed().collect(Collectors.toList()))
                  .isEqualNoOrder(Arrays.stream(expected).boxed().collect(Collectors.toList()));
    }

    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(map.entrySet());
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll().getKey();
        }

        return result;
    }
    
}
