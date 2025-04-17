package com.java.code.tree;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

/**
 <p>Given the <code>root</code> of a binary tree, return <em>its maximum depth</em>.</p>

 <p>A binary tree's <strong>maximum depth</strong>&nbsp;is the number of nodes along the longest path from the root node down to the farthest leaf node.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p> 
 <img alt="" src="https://assets.leetcode.com/uploads/2020/11/26/tmp-tree.jpg" style="width: 400px; height: 277px;" /> 
 <pre>
 <strong>Input:</strong> root = [3,9,20,null,null,15,7]
 <strong>Output:</strong> 3
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> root = [1,null,2]
 <strong>Output:</strong> 2
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li>The number of nodes in the tree is in the range <code>[0, 10<sup>4</sup>]</code>.</li> 
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
 </ul>

 <div><div>Related Topics</div><div><li>Tree</li><li>Depth-First Search</li><li>Breadth-First Search</li><li>Binary Tree</li></div></div><br><div><li>👍 13460</li><li>👎 259</li></div>
 */
public class MaximumDepthOfBinaryTree extends BaseTest {
    
    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {getNode(new Integer[] {3,9,20,null,null,15,7}), 3},
                {getNode(new Integer[] {1,null,2}), 2},
        };
    }

    @Test(dataProvider = "data")
    public void test(TreeNode root, int expected) {
        softAssert.as(String.format("root = %s", Arrays.toString(showAll(root))))
                  .assertThat(maxDepth(root))
                  .isEqualTo(expected);
    }

    public int maxDepth(TreeNode root) {
        int maxDepth = 0;
        Map<TreeNode, Integer> depthMap = new HashMap<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        if (root != null) {
            deque.offerLast(root);
            depthMap.put(root, 1);
        }
        while (!deque.isEmpty()) {
            TreeNode current = deque.pollFirst();
            int currentDepth = depthMap.get(current);
            maxDepth = Math.max(currentDepth, maxDepth);
            if (current.left != null) {
                deque.offerLast(current.left);
                depthMap.put(current.left, currentDepth + 1);
            }
            if (current.right != null) {
                deque.offerLast(current.right);
                depthMap.put(current.right, currentDepth + 1);
            }
        }
        return maxDepth;
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
