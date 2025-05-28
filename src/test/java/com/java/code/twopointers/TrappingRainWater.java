package com.java.code.twopointers;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>Given <code>n</code> non-negative integers representing an elevation map where the width of each bar is <code>1</code>, compute how much water it can trap after raining.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p> 
 <img src="https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png" style="width: 412px; height: 161px;" /> 
 <pre>
 <strong>Input:</strong> height = [0,1,0,2,1,0,1,3,2,1,2,1]
 <strong>Output:</strong> 6
 <strong>Explanation:</strong> The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> height = [4,2,0,3,2,5]
 <strong>Output:</strong> 9
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>n == height.length</code></li> 
 <li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li> 
 <li><code>0 &lt;= height[i] &lt;= 10<sup>5</sup></code></li> 
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Two Pointers</li><li>Dynamic Programming</li><li>Stack</li><li>Monotonic Stack</li></div></div><br><div><li>👍 34012</li><li>👎 597</li></div>
 */
public class TrappingRainWater extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new int[] {0,1,0,2,1,0,1,3,2,1,2,1}, 6},
                {new int[] {4,2,0,3,2,5}, 9},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] height, int expected) {
        softAssert.as(String.format("height = %s", Arrays.toString(height)))
                  .assertThat(trap(height))
                  .isEqualTo(expected);
    }

    public int trap(int[] height) {
        int result = 0;
        int leftMax = 0;
        int leftMaxIndex = height.length - 1;
        int rightMax = 0;
        int rightMaxIndex = 0;
        for (int k = 1; k < height.length - 1; k++) {
            int currentHeight = height[k];

            if (leftMaxIndex > k) {
                leftMax = 0;
                for (int i = k; i >= 0; i--) {
                    if (height[i] > leftMax) {
                        leftMax = height[i];
                        leftMaxIndex = i;
                    }
                }
            }

            if (rightMaxIndex < k) {
                rightMax = 0;
                for (int j = k; j < height.length; j++) {
                    if (height[j] > rightMax) {
                        rightMax = height[j];
                        rightMaxIndex = j;
                    }
                }
            }

            int containedWall = Math.min(leftMax, rightMax);
            int containedWater = containedWall - currentHeight;
            if (containedWater > 0) {
                result += containedWater;
            }
            
            if (currentHeight > leftMax) {
                leftMax = currentHeight;
                leftMaxIndex = k;
            }
        }
        return result;
    }
}
