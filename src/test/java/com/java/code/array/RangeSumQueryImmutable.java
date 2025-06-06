package com.java.code.array;

/**
 <p>Given an integer array <code>nums</code>, handle multiple queries of the following type:</p>

 <ol> 
 <li>Calculate the <strong>sum</strong> of the elements of <code>nums</code> between indices <code>left</code> and <code>right</code> <strong>inclusive</strong> where <code>left &lt;= right</code>.</li> 
 </ol>

 <p>Implement the <code>NumArray</code> class:</p>

 <ul> 
 <li><code>NumArray(int[] nums)</code> Initializes the object with the integer array <code>nums</code>.</li> 
 <li><code>int sumRange(int left, int right)</code> Returns the <strong>sum</strong> of the elements of <code>nums</code> between indices <code>left</code> and <code>right</code> <strong>inclusive</strong> (i .e. <code>nums[left] + nums[left + 1] + ... + nums[right]</code>).</li> 
 </ul>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input</strong>
 ["NumArray", "sumRange", "sumRange", "sumRange"]
 [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 <strong>Output</strong>
 [null, 1, -1, -3]

 <strong>Explanation</strong>
 NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
 numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
 numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= nums .length &lt;= 10<sup>4</sup></code></li> 
 <li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li> 
 <li><code>0 &lt;= left &lt;= right &lt; nums .length</code></li> 
 <li>At most <code>10<sup>4</sup></code> calls will be made to <code>sumRange</code>.</li> 
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Design</li><li>Prefix Sum</li></div></div><br><div><li>👍 3110</li><li>👎 1884</li></div>
 */
public class RangeSumQueryImmutable {
    
    static class NumArray {
        
        private final int[] nums;
        private final int[] prefixSums;

        public NumArray(int[] nums) {
            this.nums = nums;
            prefixSums = new int[nums.length];
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                prefixSums[i] = sum;
                sum += nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return prefixSums[right] - prefixSums[left] + nums[right];
        }
    }
}
