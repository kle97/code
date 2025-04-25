package com.java.code.tree;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

/**
 <p>Given the roots of two binary trees <code>p</code> and <code>q</code>, write a function to check if they are the same or not.</p>

 <p>Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2020/12/20/ex1.jpg" style="width: 622px; height: 182px;" />
 <pre>
 <strong>Input:</strong> p = [1,2,3], q = [1,2,3]
 <strong>Output:</strong> true
 </pre>

 <p><strong class="example">Example 2:</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2020/12/20/ex2.jpg" style="width: 382px; height: 182px;" />
 <pre>
 <strong>Input:</strong> p = [1,2], q = [1,null,2]
 <strong>Output:</strong> false
 </pre>

 <p><strong class="example">Example 3:</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2020/12/20/ex3.jpg" style="width: 622px; height: 182px;" />
 <pre>
 <strong>Input:</strong> p = [1,2,1], q = [1,1,2]
 <strong>Output:</strong> false
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li>The number of nodes in both trees is in the range <code>[0, 100]</code>.</li>
 <li><code>-10<sup>4</sup> &lt;= Node .val &lt;= 10<sup>4</sup></code></li>
 </ul>

 <div><div>Related Topics</div><div><li>Tree</li><li>Depth-First Search</li><li>Breadth-First Search</li><li>Binary Tree</li></div></div><br><div><li>👍 12094</li><li>👎 263</li></div>
 */
public class SameTree extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {getNode(new Integer[] {1,2,3}), getNode(new Integer[] {1,2,3}), true},
                {getNode(new Integer[] {1,2,}), getNode(new Integer[] {1,null,2}), false},
                {getNode(new Integer[] {1,2,1}), getNode(new Integer[] {1,1,2}), false},
                {getNode(new Integer[] {}), getNode(new Integer[] {}), true},
                {getNode(new Integer[] {2,2,2,null,2,null,null,2}), getNode(new Integer[] {2,2,2,2,null,2,null}), false},
                {getNode(new Integer[] {0,-5}), getNode(new Integer[] {0,-8}), false},
        };
    }

    @Test(dataProvider = "data")
    public void test(TreeNode p, TreeNode q, boolean expected) {
        softAssert.as(String.format("p = %s, q = %s", Arrays.toString(showAll(p)), Arrays.toString(showAll(q))))
                  .assertThat(isSameTree(p, q))
                  .isEqualTo(expected);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null) {
            return false;
        } else if (q == null) {
            return false;
        }

        return dfs(p, q);
    }

    public boolean dfs(TreeNode p, TreeNode q) {
        if (!Objects.equals(p.val, q.val)) {
            return false;
        }
        boolean result = true;
        if (p.left != null && q.left != null) {
            result = dfs(p.left, q.left);
        } else if (p.left != q.left) {
            result = false;
        }
        if (p.right != null && q.right != null) {
            result = result && dfs(p.right, q.right);
        } else if (p.right != q.right) {
            result = false;
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
