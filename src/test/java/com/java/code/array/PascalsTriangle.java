package com.java.code.array;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 <p>Given an integer <code>numRows</code>, return the first numRows of <strong>Pascal's triangle</strong>.</p>

 <p>In <strong>Pascal's triangle</strong>, each number is the sum of the two numbers directly above it as shown:</p> 
 <img alt="" src="https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif" style="height:240px; width:260px" /> 
 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p> 
 <pre><strong>Input:</strong> numRows = 5
 <strong>Output:</strong> [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 </pre>
 <p><strong class="example">Example 2:</strong></p> 
 <pre><strong>Input:</strong> numRows = 1
 <strong>Output:</strong> [[1]]
 </pre> 
 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= numRows &lt;= 30</code></li> 
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Dynamic Programming</li></div></div><br><div><li>👍 12383</li><li>👎 408</li></div>
 */
public class PascalsTriangle extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {5, List.of(List.of(1), List.of(1,1), List.of(1,2,1), List.of(1,3,3,1), List.of(1,4,6,4,1))},
                {1, List.of(List.of(1))},
        };
    }

    @Test(dataProvider = "data")
    public void test(int numRows, List<List<Integer>> expected) {
        softAssert.as(String.format("numRows = %d", numRows))
                  .assertThat(generate(numRows))
                  .isEqualTo(expected);
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> previous = List.of(1);
        list.add(previous);
        for (int i = 1; i < numRows; i++) {
            int length = previous.size() + 1;
            List<Integer> current = new ArrayList<>();
            for (int j = 0; j < length; j++) {
                if (j == 0 || j == length - 1) {
                    current.add(1);
                } else {
                    current.add(previous.get(j - 1) + previous.get(j));   
                }
            }
            previous = current;
            list.add(current);
        }
        return list;
    }
}
