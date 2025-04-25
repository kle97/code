package com.java.code.array;

import java.util.ArrayList;
import java.util.List;

/**
 <p>Given an array <code>nums</code> of <code>n</code> integers where <code>nums[i]</code> is in the range <code>[1, n]</code>, return <em>an array of all the integers in the range</em> <code>[1, n]</code> <em>that do not appear in</em> <code>nums</code>.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p> 
 <pre><strong>Input:</strong> nums = [4,3,2,7,8,2,3,1]
 <strong>Output:</strong> [5,6]
 </pre>
 <p><strong class="example">Example 2:</strong></p> 
 <pre><strong>Input:</strong> nums = [1,1]
 <strong>Output:</strong> [2]
 </pre> 
 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>n == nums  .length</code></li> 
 <li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li> 
 <li><code>1 &lt;= nums[i] &lt;= n</code></li> 
 </ul>

 <p>&nbsp;</p> 
 <p><strong>Follow up:</strong> Could you do it without extra space and in <code>O(n)</code> runtime? You may assume the returned list does not count as extra space.</p>

 <div><div>Related Topics</div><div><li>Array</li><li>Hash Table</li></div></div><br><div><li>👍 9180</li><li>👎 472</li></div>
 */
public class FindAllNumbersDisappearedInAnArray {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int[] nums2 = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            nums2[nums[i]] = nums[i];
        }
        for (int i = 1; i < nums2.length; i++) {
            if(nums2[i] == 0) {
                list.add(i);
            }
        }
        return list;
    }
}
