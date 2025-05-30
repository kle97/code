package com.java.code.array;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>Given an array <code>arr</code>,&nbsp;replace every element in that array with the greatest element among the elements to its&nbsp;right, and replace the last element with <code>-1</code>.</p>

 <p>After doing so, return the array.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> arr = [17,18,5,4,6,1]
 <strong>Output:</strong> [18,6,6,6,1,-1]
 <strong>Explanation:</strong> 
 - index 0 --&gt; the greatest element to the right of index 0 is index 1 (18).
 - index 1 --&gt; the greatest element to the right of index 1 is index 4 (6).
 - index 2 --&gt; the greatest element to the right of index 2 is index 4 (6).
 - index 3 --&gt; the greatest element to the right of index 3 is index 4 (6).
 - index 4 --&gt; the greatest element to the right of index 4 is index 5 (1).
 - index 5 --&gt; there are no elements to the right of index 5, so we put -1.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> arr = [400]
 <strong>Output:</strong> [-1]
 <strong>Explanation:</strong> There are no elements to the right of index 0.
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= arr .length &lt;= 10<sup>4</sup></code></li> 
 <li><code>1 &lt;= arr[i] &lt;= 10<sup>5</sup></code></li> 
 </ul>

 <div><div>Related Topics</div><div><li>Array</li></div></div><br><div><li>👍 2482</li><li>👎 237</li></div>
 */
public class ReplaceElementsWithGreatestElementOnRightSide extends BaseTest {

    @DataProvider(name = "ReplaceElementsWithGreatestElementOnRightSide")
    public Object[][] data() {
        return new Object[][]{
                {new int[]{17,18,5,4,6,1}, new int[]{18,6,6,6,1,-1}},
                {new int[]{400}, new int[]{-1}},
        };
    }

    @Test(dataProvider = "ReplaceElementsWithGreatestElementOnRightSide")
    public void test(int[] nums, int[] expected) {
        softAssert.as(Arrays.toString(nums))
                  .assertThat(replaceElements(nums))
                  .isEqualTo(expected);
    }

    public int[] replaceElements(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int max = arr[i + 1];
            for (int j = arr.length - 1; j >= i + 2 ; j--) {
                if (arr[j] > max) {
                    max = arr[j];
                }
            }
            arr[i] = max;
        }
        
        arr[arr.length - 1] = -1;
        return arr;
    }
}
