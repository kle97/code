package com.java.code.tree;

import com.java.code.common.BaseTest;
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
 <li><code>-100 &lt;= Node .val &lt;= 100</code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Tree</li><li>Depth-First Search</li><li>Breadth-First Search</li><li>Binary Tree</li></div></div><br><div><li>👍 14526</li><li>👎 241</li></div>
 */
public class InvertBinaryTree extends BaseTest {


    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {getNode(new Integer[] {4,2,7,1,3,6,9}), getNode(new Integer[] {4,7,2,9,6,3,1})},
                {getNode(new Integer[] {2,1,3}), getNode(new Integer[] {2,3,1})},
                {getNode(new Integer[] {}), getNode(new Integer[] {})},
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

    public TreeNode getNode(Integer[] data) {
        if (data.length == 0 || data[0] == null) {
            return null;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode root = new TreeNode(data[0]);
        deque.offerLast(root);
        for (int i = 1; i < data.length; i++) {
            TreeNode current = new TreeNode(data[i]);
            deque.offerLast(current);
            boolean added = false;
            while (!added) {
                TreeNode parent = deque.peekFirst();
                if (parent == null || parent.val == null) {
                    deque.pollFirst();
                    continue;
                }
                if (!parent.leftChildAdded) {
                    parent.left = current.val != null ? current : null;
                    parent.leftChildAdded = true;
                    added = true;
                } else if (!parent.rightChildAdded) {
                    parent.right = current.val != null ? current : null;
                    parent.rightChildAdded = true;
                    added = true;
                } else {
                    deque.pollFirst();
                }
            }
        }
        return root;
    }

    public Integer[] showAll(TreeNode node) {
        if (node == null) {
            return new Integer[0];
        }
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerLast(node);
        list.add(node.val);
        while (!deque.isEmpty()) {
            TreeNode current = deque.pollFirst();
            if (current == null || current.val == null) {
                list.add(null);
                continue;
            }
            if (current.left != null && current.left.val != null) {
                deque.offerLast(current.left);
                list.add(current.left.val);
            } else {
                list.add(null);
            }

            if (current.right != null && current.right.val != null) {
                deque.offerLast(current.right);
                list.add(current.right.val);
            } else {
                list.add(null);
            }
        }
        while (list.get(list.size() - 1) == null) {
            list.remove(list.size() - 1);
        }
        return list.toArray(new Integer[0]);
    }

    public static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;
        boolean leftChildAdded;
        boolean rightChildAdded;
        TreeNode() {}
        TreeNode(Integer val) { this.val = val; }
        TreeNode(Integer val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
