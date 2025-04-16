package com.java.code.linkedlist;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 <p>You are given the heads of two sorted linked lists <code>list1</code> and <code>list2</code>.</p>

 <p>Merge the two lists into one <strong>sorted</strong> list. The list should be made by splicing together the nodes of the first two lists.</p>

 <p>Return <em>the head of the merged linked list</em>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/merge_ex1.jpg" style="width: 662px; height: 302px;" />
 <pre>
 <strong>Input:</strong> list1 = [1,2,4], list2 = [1,3,4]
 <strong>Output:</strong> [1,1,2,3,4,4]
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> list1 = [], list2 = []
 <strong>Output:</strong> []
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> list1 = [], list2 = [0]
 <strong>Output:</strong> [0]
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li>The number of nodes in both lists is in the range <code>[0, 50]</code>.</li>
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li>
 <li>Both <code>list1</code> and <code>list2</code> are sorted in <strong>non-decreasing</strong> order.</li>
 </ul>

 <div><div>Related Topics</div><div><li>Linked List</li><li>Recursion</li></div></div><br><div><li>👍 23147</li><li>👎 2274</li></div>
 */
public class MergeTwoSortedList extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {getNode(new int[] {1, 2, 4}), getNode(new int[] {1, 3, 4}), getNode(new int[] {1, 1, 2, 3, 4, 4})},
                {getNode(new int[] {}), getNode(new int[] {}), getNode(new int[] {})},
                {getNode(new int[] {}), getNode(new int[] {0}), getNode(new int[] {0})},
        };
    }

    @Test(dataProvider = "data")
    public void test(ListNode list1, ListNode list2, ListNode expected) {
        softAssert.as(String.format("list1 = %s, list2 = %s", Arrays.toString(showAll(list1)), Arrays.toString(showAll(list2))))
                  .assertThat(showAll(mergeTwoLists(list1, list2)))
                  .isEqualTo(showAll(expected));
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = list2;
        if (list2 == null && list1 != null || list1 != null && list1.val < list2.val) {
            head = list1;
            list1 = list1.next;
        } else if (list2 != null) {
            list2 = list2.next;
        }
        ListNode current = head;
        while (list1 != null || list2 != null) {
            if (list2 == null || list1 != null && list1.val < list2.val) {
                current.next = list1;
                current = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                current = list2;
                list2 = list2.next;
            }
        }
        return head;
    }

    public ListNode getNode(int[] data) {
        if (data.length == 0) {
            return null;
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
