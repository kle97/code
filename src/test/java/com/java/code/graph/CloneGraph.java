package com.java.code.graph;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 <p>Given a reference of a node in a <strong><a href="https://en.wikipedia.org/wiki/Connectivity_(graph_theory)#Connected_graph" target="_blank">connected</a></strong> undirected graph.</p>

 <p>Return a <a href="https://en.wikipedia.org/wiki/Object_copying#Deep_copy" target="_blank"><strong>deep copy</strong></a> (clone) of the graph.</p>

 <p>Each node in the graph contains a value (<code>int</code>) and a list (<code>List[Node]</code>) of its neighbors.</p>

 <pre>
 class Node {
 public int val;
 public List&lt;Node&gt; neighbors;
 }
 </pre>

 <p>&nbsp;</p>

 <p><strong>Test case format:</strong></p>

 <p>For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with <code>val == 1</code>, the second node with <code>val == 2</code>, and so on. The graph is represented in the test case using an adjacency list.</p>

 <p><b>An adjacency list</b> is a collection of unordered <b>lists</b> used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.</p>

 <p>The given node will always be the first node with <code>val = 1</code>. You must return the <strong>copy of the given node</strong> as a reference to the cloned graph.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2019/11/04/133_clone_graph_question.png" style="width: 454px; height: 500px;" />
 <pre>
 <strong>Input:</strong> adjList = [[2,4],[1,3],[2,4],[1,3]]
 <strong>Output:</strong> [[2,4],[1,3],[2,4],[1,3]]
 <strong>Explanation:</strong> There are 4 nodes in the graph.
 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 </pre>

 <p><strong class="example">Example 2:</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2020/01/07/graph.png" style="width: 163px; height: 148px;" />
 <pre>
 <strong>Input:</strong> adjList = [[]]
 <strong>Output:</strong> [[]]
 <strong>Explanation:</strong> Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> adjList = []
 <strong>Output:</strong> []
 <strong>Explanation:</strong> This an empty graph, it does not have any nodes.
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li>The number of nodes in the graph is in the range <code>[0, 100]</code>.</li>
 <li><code>1 &lt;= Node.val &lt;= 100</code></li>
 <li><code>Node.val</code> is unique for each node.</li>
 <li>There are no repeated edges and no self-loops in the graph.</li>
 <li>The Graph is connected and all nodes can be visited starting from the given node.</li>
 </ul>

 <div><div>Related Topics</div><div><li>Hash Table</li><li>Depth-First Search</li><li>Breadth-First Search</li><li>Graph</li></div></div><br><div><li>👍 10023</li><li>👎 4046</li></div>
 */
public class CloneGraph extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[][] {
                        new int[] {2,4},
                        new int[] {1,3},
                        new int[] {2,4},
                        new int[] {1,3},
                }},
                {new int[][] {new int[] {}}}
        };
    }

    @Test(dataProvider = "data")
    public void test(int[][] adjacentList) {
        softAssert.as(String.format("adjList = %s", Arrays.deepToString(adjacentList)))
                  .assertThat(cloneGraph(adjacentListToNode(adjacentList)).toString())
                  .isEqualTo(adjacentListToNode(adjacentList).toString());
    }

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Map<Integer, Node> nodeMap = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node);
        nodeMap.put(node.val, new Node(node.val));
        Node returnedNode = null;
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            Node newNode = nodeMap.get(currentNode.val);
            if (returnedNode == null) {
                returnedNode = newNode;
            }
            for (int i = 0; i < currentNode.neighbors.size(); i++) {
                Node currentNeighbor = currentNode.neighbors.get(i);
                if (!nodeMap.containsKey(currentNeighbor.val)) {
                    Node newNeighbor = new Node(currentNeighbor.val);
                    newNode.neighbors.add(newNeighbor);
                    nodeMap.put(currentNeighbor.val, newNeighbor);
                    queue.offer(currentNeighbor);
                } else {
                    Node newNeighbor = nodeMap.get(currentNeighbor.val);
                    newNode.neighbors.add(newNeighbor);
                }
            }
        }

        return returnedNode;
    }

    public static class Node {
        public int val;
        public List<Node> neighbors;
        public List<Integer> neighborInNumbers = new ArrayList<>();
        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }

        public String toString() {
            Map<Integer, Node> nodeMap = new TreeMap<>();
            Queue<Node> queue = new ArrayDeque<>();
            nodeMap.put(val, this);
            queue.offer(this);
            while (!queue.isEmpty()) {
                Node currentNode = queue.poll();
                for (int i = 0; i < currentNode.neighbors.size(); i++) {
                    Node newNode = currentNode.neighbors.get(i);
                    if (!nodeMap.containsKey(newNode.val)) {
                        nodeMap.put(newNode.val, newNode);
                        queue.offer(newNode);
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            boolean first = true;
            sb.append("[");
            for (var entry : nodeMap.entrySet()) {
                if (!first) {
                    sb.append(", ");
                } else {
                    first = false;
                }
                Node node = entry.getValue();
                Collections.sort(node.neighbors, (a, b) -> a.val - b.val);
                sb.append(node.neighbors.stream().map(n -> n.val).collect(Collectors.toList()));
            }
            sb.append("]");
            return sb.toString().trim();
        }
    }

    public Node adjacentListToNode(int[][] adjacentList) {
        Map<Integer, Node> nodeMap = new HashMap<>();
        Node firstNode = null;
        for (int i = 0; i < adjacentList.length; i++) {
            Node newNode = new Node(i + 1);
            for (int j = 0; j < adjacentList[i].length; j++) {
                newNode.neighborInNumbers.add(adjacentList[i][j]);
            }
            nodeMap.put(i + 1, newNode);
            if (firstNode == null) {
                firstNode = newNode;
            }
        }
        for (var entry : nodeMap.entrySet()) {
            Node node = entry.getValue();
            for (int i = 0; i < node.neighborInNumbers.size(); i++) {
                node.neighbors.add(nodeMap.get(node.neighborInNumbers.get(i)));
            }
        }
        return firstNode;
    }
}
