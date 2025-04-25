package com.java.code.heap;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 <p>You are part of a university admissions office and need to keep track of the <code>kth</code> highest test score from applicants in real-time. This helps to determine cut-off marks for interviews and admissions dynamically as new applicants submit their scores.</p>

 <p>You are tasked to implement a class which, for a given integer&nbsp;<code>k</code>, maintains a stream of test scores and continuously returns the&nbsp;<code>k</code>th highest test score&nbsp;<strong>after</strong>&nbsp;a new score has been submitted. More specifically, we are looking for the <code>k</code>th highest score in the sorted list of all scores.</p>

 <p>Implement the&nbsp;<code>KthLargest</code> class:</p>

 <ul>
 <li><code>KthLargest(int k, int[] nums)</code> Initializes the object with the integer <code>k</code> and the stream of test scores&nbsp;<code>nums</code>.</li>
 <li><code>int add(int val)</code> Adds a new test score&nbsp;<code>val</code> to the stream and returns the element representing the <code>k<sup>th</sup></code> largest element in the pool of test scores so far.</li>
 </ul>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <div class="example-block">
 <p><strong>Input:</strong><br /> <span class="example-io">["KthLargest", "add", "add", "add", "add", "add"]<br /> [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]</span></p>
 </div>

 <p><strong>Output:</strong> <span class="example-io">[null, 4, 5, 5, 8, 8]</span></p>

 <p><strong>Explanation:</strong></p>

 <p>KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);<br /> kthLargest.add(3); // return 4<br /> kthLargest.add(5); // return 5<br /> kthLargest.add(10); // return 5<br /> kthLargest.add(9); // return 8<br /> kthLargest.add(4); // return 8</p>

 <p><strong class="example">Example 2:</strong></p>

 <div class="example-block">
 <p><strong>Input:</strong><br /> <span class="example-io">["KthLargest", "add", "add", "add", "add"]<br /> [[4, [7, 7, 7, 7, 8, 3]], [2], [10], [9], [9]]</span></p>
 </div>

 <p><strong>Output:</strong> <span class="example-io">[null, 7, 7, 7, 8]</span></p>

 <p><strong>Explanation:</strong></p> KthLargest kthLargest = new KthLargest(4, [7, 7, 7, 7, 8, 3]);
 <br /> kthLargest.add(2); // return 7
 <br /> kthLargest.add(10); // return 7
 <br /> kthLargest.add(9); // return 7
 <br /> kthLargest.add(9); // return 8

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>0 &lt;= nums .length &lt;= 10<sup>4</sup></code></li>
 <li><code>1 &lt;= k &lt;= nums .length + 1</code></li>
 <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
 <li><code>-10<sup>4</sup> &lt;= val &lt;= 10<sup>4</sup></code></li>
 <li>At most <code>10<sup>4</sup></code> calls will be made to <code>add</code>.</li>
 </ul>

 <div><div>Related Topics</div><div><li>Tree</li><li>Design</li><li>Binary Search Tree</li><li>Heap (Priority Queue)</li><li>Binary Tree</li><li>Data Stream</li></div></div><br><div><li>👍 6061</li><li>👎 3873</li></div>
 */
public class KthLargestElementInAStream extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {3, new int[] {4, 5, 8, 2}, new int[] {3, 5, 10, 9, 4}, new Integer[] {null, 4, 5, 5, 8, 8}},
                {4, new int[] {7, 7, 7, 7, 8, 3}, new int[] {2, 10, 9, 9}, new Integer[] {null, 7, 7, 7, 8}},
        };
    }

    @Test(dataProvider = "data")
    public void test(int k, int[] nums, int[] values, Integer[] expected) {
        softAssert.as(String.format("k = %d, nums = %s, values = %s", k, Arrays.toString(nums), Arrays.toString(values)))
                  .assertThat(result(k, nums, values))
                  .isEqualTo(expected);
    }

    public Integer[] result(int k, int[] nums, int[] values) {
        KthLargest kthLargest = new KthLargest(k, nums);
        Integer[] result = new Integer[values.length + 1];
        for (int i = 0; i < values.length; i++) {
            result[i + 1] = kthLargest.add(values[i]);
        }
        return result;
    }

    public static class KthLargest {
        private final PriorityQueue<Integer> pq = new PriorityQueue<>();
        private final int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            for (int i = 0; i < nums.length; i++) {
                pq.offer(nums[i]);
            }
        }

        public int add(int val) {
            pq.offer(val);
            while (pq.size() > k) {
                pq.poll();
            }
            return pq.peek();
        }
    }
}
