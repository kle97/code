package com.java.code.tree;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

/**
 <p>Given the roots of two binary trees <code>root</code> and <code>subRoot</code>, return <code>true</code> if there is a subtree of <code>root</code> with the same structure and node values of<code> subRoot</code> and <code>false</code> otherwise.</p>

 <p>A subtree of a binary tree <code>tree</code> is a tree that consists of a node in <code>tree</code> and all of this node's descendants. The tree <code>tree</code> could also be considered as a subtree of itself.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p> 
 <img alt="" src="https://assets.leetcode.com/uploads/2021/04/28/subtree1-tree.jpg" style="width: 532px; height: 400px;" /> 
 <pre>
 <strong>Input:</strong> root = [3,4,5,1,2], subRoot = [4,1,2]
 <strong>Output:</strong> true
 </pre>

 <p><strong class="example">Example 2:</strong></p> 
 <img alt="" src="https://assets.leetcode.com/uploads/2021/04/28/subtree2-tree.jpg" style="width: 502px; height: 458px;" /> 
 <pre>
 <strong>Input:</strong> root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 <strong>Output:</strong> false
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li>The number of nodes in the <code>root</code> tree is in the range <code>[1, 2000]</code>.</li> 
 <li>The number of nodes in the <code>subRoot</code> tree is in the range <code>[1, 1000]</code>.</li> 
 <li><code>-10<sup>4</sup> &lt;= root .val &lt;= 10<sup>4</sup></code></li> 
 <li><code>-10<sup>4</sup> &lt;= subRoot .val &lt;= 10<sup>4</sup></code></li> 
 </ul>

 <div><div>Related Topics</div><div><li>Tree</li><li>Depth-First Search</li><li>String Matching</li><li>Binary Tree</li><li>Hash Function</li></div></div><br><div><li>👍 8500</li><li>👎 559</li></div>
 */
public class SubtreeOfAnotherTree extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {getNode(new Integer[] {3,4,5,1,2}), getNode(new Integer[] {4,1,2}), true},
                {getNode(new Integer[] {3,4,5,1,2,null,null,null,null,0}), getNode(new Integer[] {4,1,2}), false},
                {getNode(new Integer[] {1}), getNode(new Integer[] {1}), true},
                {getNode(new Integer[] {1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,2}),
                 getNode(new Integer[] {1,null,1,null,1,null,1,null,1,null,1,2}), true},
        };
    }

    @Test(dataProvider = "data")
    public void test(TreeNode root, TreeNode subRoot, boolean expected) {
        softAssert.as(String.format("root = %s, subRoot = %s", Arrays.toString(showAll(root)), Arrays.toString(showAll(subRoot))))
                  .assertThat(isSubtree(root, subRoot))
                  .isEqualTo(expected);
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null || subRoot == null) {
            return false;
        }

        if (isSameTree(root, subRoot)) {
            return true;
        }
        if (root.left != null) {
            if (isSubtree(root.left, subRoot)) {
                return true;
            }
        }
        if (root.right != null) {
            if (isSubtree(root.right, subRoot)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (!Objects.equals(p.val, q.val)) {
            return false;
        }

        boolean result = true;
        if (p.left != null && q.left != null) {
            result = isSameTree(p.left, q.left);
        } else if (p.left != q.left) {
            return false;
        }
        if (p.right != null && q.right != null) {
            result = result && isSameTree(p.right, q.right);
        } else if (p.right != q.right) {
            return false;
        }
        return result;
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
