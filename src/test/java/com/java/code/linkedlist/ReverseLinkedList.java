package com.java.code.linkedlist;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 <p>Given the <code>head</code> of a singly linked list, reverse the list, and return <em>the reversed list</em>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/rev1ex1.jpg" style="width: 542px; height: 222px;" />
 <pre>
 <strong>Input:</strong> head = [1,2,3,4,5]
 <strong>Output:</strong> [5,4,3,2,1]
 </pre>

 <p><strong class="example">Example 2:</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/rev1ex2.jpg" style="width: 182px; height: 222px;" />
 <pre>
 <strong>Input:</strong> head = [1,2]
 <strong>Output:</strong> [2,1]
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> head = []
 <strong>Output:</strong> []
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li>The number of nodes in the list is the range <code>[0, 5000]</code>.</li>
 <li><code>-5000 &lt;= Node.val &lt;= 5000</code></li>
 </ul>

 <p>&nbsp;</p>
 <p><strong>Follow up:</strong> A linked list can be reversed either iteratively or recursively. Could you implement both?</p>

 <div><div>Related Topics</div><div><li>Linked List</li><li>Recursion</li></div></div><br><div><li>👍 22756</li><li>👎 517</li></div>
 */
public class ReverseLinkedList extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {getNode(new int[] {1, 2, 3, 4, 5}), getNode(new int[] {5, 4, 3, 2, 1})},
                {getNode(new int[] {1, 2}), getNode(new int[] {2, 1})},
                {getNode(new int[] {}), getNode(new int[] {})},
        };
    }

    @Test(dataProvider = "data")
    public void test(ListNode head, ListNode expected) {
        softAssert.as(String.format("head = %s", Arrays.toString(showAll(head))))
                  .assertThat(showAll(reverseList(head)))
                  .isEqualTo(showAll(expected));
    }

    public ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode prev = null;
        ListNode next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public ListNode getNode(int[] data) {
        if (data.length == 0) {
            return new ListNode(Integer.MIN_VALUE);
        }
        ListNode head = new ListNode(data[0]);
        ListNode prev = head;
        for (int i = 1; i < data.length; i++) {
            ListNode current = new ListNode(data[i]);
            prev.next = current;
            prev = current;
        }
        return head;
    }

    public int[] showAll(ListNode node) {
        List<Integer> list = new ArrayList<>();
        ListNode current = node;
        while (current != null) {
            if (current.val != Integer.MIN_VALUE) {
                list.add(current.val);
            }
            current = current.next;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public class ListNode {
        int val;
        ListNode next;

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
