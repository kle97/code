package com.java.code.linkedlist;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 <p>Given <code>head</code>, the head of a linked list, determine if the linked list has a cycle in it.</p>

 <p>There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the&nbsp;<code>next</code>&nbsp;pointer. Internally, <code>pos</code>&nbsp;is used to denote the index of the node that&nbsp;tail's&nbsp;<code>next</code>&nbsp;pointer is connected to.&nbsp;<strong>Note that&nbsp;<code>pos</code>&nbsp;is not passed as a parameter</strong>.</p>

 <p>Return&nbsp;<code>true</code><em> if there is a cycle in the linked list</em>. Otherwise, return <code>false</code>.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p> 
 <img alt="" src="https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png" style="width: 300px; height: 97px; margin-top: 8px; margin-bottom: 8px;" /> 
 <pre>
 <strong>Input:</strong> head = [3,2,0,-4], pos = 1
 <strong>Output:</strong> true
 <strong>Explanation:</strong> There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 </pre>

 <p><strong class="example">Example 2:</strong></p> 
 <img alt="" src="https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test2.png" style="width: 141px; height: 74px;" /> 
 <pre>
 <strong>Input:</strong> head = [1,2], pos = 0
 <strong>Output:</strong> true
 <strong>Explanation:</strong> There is a cycle in the linked list, where the tail connects to the 0th node.
 </pre>

 <p><strong class="example">Example 3:</strong></p> 
 <img alt="" src="https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test3.png" style="width: 45px; height: 45px;" /> 
 <pre>
 <strong>Input:</strong> head = [1], pos = -1
 <strong>Output:</strong> false
 <strong>Explanation:</strong> There is no cycle in the linked list.
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li>The number of the nodes in the list is in the range <code>[0, 10<sup>4</sup>]</code>.</li> 
 <li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li> 
 <li><code>pos</code> is <code>-1</code> or a <strong>valid index</strong> in the linked-list.</li> 
 </ul>

 <p>&nbsp;</p> 
 <p><strong>Follow up:</strong> Can you solve it using <code>O(1)</code> (i.e. constant) memory?</p>

 <div><div>Related Topics</div><div><li>Hash Table</li><li>Linked List</li><li>Two Pointers</li></div></div><br><div><li>👍 16347</li><li>👎 1491</li></div>
 */
public class LinkedListCycle extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {getNode(new int[] {3, 2, 0, -4}, 1), true},
                {getNode(new int[] {1, 2}, 0), true},
                {getNode(new int[] {1}, -1), false},
                {getNode(new int[] {1,1,1,1,1,1,1}, -1), false},
        };
    }

    @Test(dataProvider = "data")
    public void test(ListNode head, boolean expected) {
        softAssert.as(String.format("head = %s", Arrays.toString(showAll(head))))
                  .assertThat(hasCycle(head))
                  .isEqualTo(expected);
    }

    public boolean hasCycle(ListNode head) {
        ListNode current = head;
        ListNode fast = null;
        if (current != null && current.next != null) {
            fast = current.next.next;
        }
        while (current != null && fast != null) {
            if (current.val == fast.val && current.next != null && fast.next != null && current.next == fast.next) {
                return true;
            }
            current = current.next;
            if (fast.next != null) {
                fast = fast.next.next;
            }
        }
        return false;
    }

    public ListNode getNode(int[] data, int pos) {
        if (data.length == 0) {
            return null;
        }
        ListNode head = new ListNode(data[0]);
        ListNode prev = head;
        ListNode temp = head;
        for (int i = 1; i < data.length; i++) {
            ListNode current = new ListNode(data[i]);
            prev.next = current;
            prev = current;
            if (pos == i) {
                temp = current;
            }
        }
        if (pos >= 0) {
            prev.next = temp;
        }
        prev.isLast = true;
        return head;
    }

    public int[] showAll(ListNode node) {
        List<Integer> list = new ArrayList<>();
        ListNode current = node;
        while (current != null) {
            if (current.val != Integer.MIN_VALUE) {
                list.add(current.val);
            }
            if (current.isLast) {
                break;
            }

            current = current.next;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public class ListNode {
        int val;
        ListNode next;
        boolean isLast;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
