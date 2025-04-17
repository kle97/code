package com.java.code.tree;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

/**
 <p>Given the <code>root</code> of a binary tree, return <em>the length of the <strong>diameter</strong> of the tree</em>.</p>

 <p>The <strong>diameter</strong> of a binary tree is the <strong>length</strong> of the longest path between any two nodes in a tree. This path may or may not pass through the <code>root</code>.</p>

 <p>The <strong>length</strong> of a path between two nodes is represented by the number of edges between them.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p> 
 <img alt="" src="https://assets.leetcode.com/uploads/2021/03/06/diamtree.jpg" style="width: 292px; height: 302px;" /> 
 <pre>
 <strong>Input:</strong> root = [1,2,3,4,5]
 <strong>Output:</strong> 3
 <strong>Explanation:</strong> 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> root = [1,2]
 <strong>Output:</strong> 1
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li> 
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
 </ul>

 <div><div>Related Topics</div><div><li>Tree</li><li>Depth-First Search</li><li>Binary Tree</li></div></div><br><div><li>👍 14621</li><li>👎 1147</li></div>
 */
public class DiameterOfBinaryTree extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {getNode(new Integer[] {1,2,3,4,5}), 3},
                {getNode(new Integer[] {1,2}), 1},
                {getNode(new Integer[] {1,2,3,4,5,null,null,null,6}), 4},
                {getNode(new Integer[] {3,2,4,null,null,1}), 3},
        };
    }

    @Test(dataProvider = "data")
    public void test(TreeNode root, int expected) {
        softAssert.as(String.format("root = %s", Arrays.toString(showAll(root))))
                  .assertThat(diameterOfBinaryTree(root))
                  .isEqualTo(expected);
    }

    private int maxLength;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxLength = 0;
        dfs(root);
        return maxLength;
    }

    public int dfs(TreeNode current) {
        int leftMaxLength = 0;
        if (current.left != null) {
            leftMaxLength = dfs(current.left);
        }
        int rightMaxLength = 0;
        if (current.right != null) {
            rightMaxLength = dfs(current.right);
        }

        maxLength = Math.max(leftMaxLength + rightMaxLength, maxLength);
        return Math.max(leftMaxLength, rightMaxLength) + 1;
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
