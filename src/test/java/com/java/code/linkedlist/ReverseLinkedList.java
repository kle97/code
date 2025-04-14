package com.java.code.linkedlist;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 *
 */
public class ReverseLinkedList extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {-1,0,3,5,9,12}, 9, 4},
                {new int[] {-1,0,3,5,9,12}, 2, -1},
                };
    }

    @Test(dataProvider = "data")
    public void test(ListNode head, ListNode expected) {
        softAssert.as(String.format("nums = %s, target = %d", Arrays.toString(nums), target))
                  .assertThat(reverseList(head))
                  .isEqualTo(expected);
    }

    public ListNode reverseList(ListNode head) {

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
