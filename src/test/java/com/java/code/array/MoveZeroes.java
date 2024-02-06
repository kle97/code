package com.java.code.array;

/**
 <p>Given an integer array <code>nums</code>, move all <code>0</code>'s to the end of it while maintaining the relative order of the non-zero elements.</p>

 <p><strong>Note</strong> that you must do this in-place without making a copy of the array.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p> 
 <pre><strong>Input:</strong> nums = [0,1,0,3,12]
 <strong>Output:</strong> [1,3,12,0,0]
 </pre>
 <p><strong class="example">Example 2:</strong></p> 
 <pre><strong>Input:</strong> nums = [0]
 <strong>Output:</strong> [0]
 </pre> 
 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li> 
 <li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li> 
 </ul>

 <p>&nbsp;</p> 
 <strong>Follow up:</strong> Could you minimize the total number of operations done?

 <div><div>Related Topics</div><div><li>Array</li><li>Two Pointers</li></div></div><br><div><li>👍 16094</li><li>👎 424</li></div>
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        int i = 0;
        int j = 0;
        while (i < nums.length && j < nums.length) {
            while (i < nums.length && nums[i] != 0) {
                i++;
            }

            while (j < nums.length && nums[j] == 0) {
                j++;
            }

            if (i < nums.length && j < nums.length && i < j) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            } else {
                j++;
            }
        }
    }
}
