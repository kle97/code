package com.java.code.tree;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

/**
 <p>Given a binary tree, determine if it is <span data-keyword="height-balanced"><strong>height-balanced</strong></span>.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2020/10/06/balance_1.jpg" style="width: 342px; height: 221px;" />
 <pre>
 <strong>Input:</strong> root = [3,9,20,null,null,15,7]
 <strong>Output:</strong> true
 </pre>

 <p><strong class="example">Example 2:</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2020/10/06/balance_2.jpg" style="width: 452px; height: 301px;" />
 <pre>
 <strong>Input:</strong> root = [1,2,2,3,3,null,null,4,4]
 <strong>Output:</strong> false
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> root = []
 <strong>Output:</strong> true
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li>The number of nodes in the tree is in the range <code>[0, 5000]</code>.</li>
 <li><code>-10<sup>4</sup> &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Tree</li><li>Depth-First Search</li><li>Binary Tree</li></div></div><br><div><li>👍 11294</li><li>👎 761</li></div>
 */
public class BalancedBinaryTree extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {getNode(new Integer[] {3,9,20,null,null,15,7}), true},
                {getNode(new Integer[] {1,2,2,3,3,null,null,4,4}), false},
                {getNode(new Integer[] {}), true},
        };
    }

    @Test(dataProvider = "data")
    public void test(TreeNode root, boolean expected) {
        softAssert.as(String.format("root = %s", Arrays.toString(showAll(root))))
                  .assertThat(isBalanced(root))
                  .isEqualTo(expected);
    }

    private boolean isBalanced;

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        isBalanced = true;
        dfs(root);
        return isBalanced;
    }

    public int dfs(TreeNode current) {
        int leftDepth = 0;
        if (current.left != null) {
            leftDepth = dfs(current.left);
        }

        int rightDepth = 0;
        if (current.right != null) {
            rightDepth = dfs(current.right);
        }

        if (Math.abs(leftDepth - rightDepth) > 1) {
            isBalanced = false;
        }
        return Math.max(leftDepth, rightDepth) + 1;
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
