package com.java.code.tree;

import com.java.code.common.BaseTest;
import com.java.code.linkedlist.LinkedListCycle;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

/**
 <p>Given the <code>root</code> of a binary tree, invert the tree, and return <em>its root</em>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2021/03/14/invert1-tree.jpg" style="width: 500px; height: 165px;" />
 <pre>
 <strong>Input:</strong> root = [4,2,7,1,3,6,9]
 <strong>Output:</strong> [4,7,2,9,6,3,1]
 </pre>

 <p><strong class="example">Example 2:</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2021/03/14/invert2-tree.jpg" style="width: 500px; height: 120px;" />
 <pre>
 <strong>Input:</strong> root = [2,1,3]
 <strong>Output:</strong> [2,3,1]
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> root = []
 <strong>Output:</strong> []
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li>The number of nodes in the tree is in the range <code>[0, 100]</code>.</li>
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Tree</li><li>Depth-First Search</li><li>Breadth-First Search</li><li>Binary Tree</li></div></div><br><div><li>👍 14526</li><li>👎 241</li></div>
 */
public class InvertBinaryTree extends BaseTest {


    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {getNode(new int[] {4,2,7,1,3,6,9}), getNode(new int[] {4,7,2,9,6,3,1})},
                {getNode(new int[] {2,1,3}), getNode(new int[] {2,3,1})},
                {getNode(new int[] {}), getNode(new int[] {})},
        };
    }

    @Test(dataProvider = "data")
    public void test(TreeNode root, TreeNode expected) {
        softAssert.as(String.format("root = %s", Arrays.toString(showAll(root))))
                  .assertThat(showAll(invertTree(root)))
                  .isEqualTo(showAll(expected));
    }

    public TreeNode invertTree(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        if(root != null) {
            deque.offerLast(root);
        }
        while (!deque.isEmpty()) {
            TreeNode current = deque.pollFirst();
            if (current.left != null) {
                deque.offerLast(current.left);
            }
            if (current.right != null) {
                deque.offerLast(current.right);
            }
            TreeNode temp = current.right;
            current.right = current.left;
            current.left = temp;
        }
        return root;
    }

    public TreeNode getNode(int[] data) {
        if (data.length == 0) {
            return null;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode root = new TreeNode(data[0]);
        deque.offerLast(root);
        for (int i = 1; i < data.length; i++) {
            TreeNode current = new TreeNode(data[i]);
            deque.offerLast(current);
            boolean added = false;
            while (deque.peekFirst() != null && !added) {
                TreeNode parent = deque.peekFirst();
                if (parent.left == null) {
                    parent.left = current;
                    added = true;
                } else if (parent.right == null) {
                    parent.right = current;
                    added = true;
                } else {
                    deque.pollFirst();
                }
            }
        }
        return root;
    }

    public int[] showAll(TreeNode node) {
        if (node == null) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerLast(node);
        list.add(node.val);
        while (!deque.isEmpty()) {
            TreeNode current = deque.pollFirst();
            if (current.left != null) {
                deque.offerLast(current.left);
                list.add(current.left.val);
            }
            if (current.right != null) {
                deque.offerLast(current.right);
                list.add(current.right.val);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
