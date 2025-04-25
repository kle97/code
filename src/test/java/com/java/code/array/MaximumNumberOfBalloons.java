package com.java.code.array;

import java.util.HashMap;
import java.util.Map;

/**
 <p>Given a string <code>text</code>, you want to use the characters of <code>text</code> to form as many instances of the word <strong>"balloon"</strong> as possible.</p>

 <p>You can use each character in <code>text</code> <strong>at most once</strong>. Return the maximum number of instances that can be formed.</p>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p>

 <p><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/09/05/1536_ex1_upd.JPG" style="width: 132px; height: 35px;" /></strong></p>

 <pre>
 <strong>Input:</strong> text = "nlaebolko"
 <strong>Output:</strong> 1
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <p><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/09/05/1536_ex2_upd.JPG" style="width: 267px; height: 35px;" /></strong></p>

 <pre>
 <strong>Input:</strong> text = "loonbalxballpoon"
 <strong>Output:</strong> 2
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> text = "leetcode"
 <strong>Output:</strong> 0
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>1 &lt;= text .length &lt;= 10<sup>4</sup></code></li> 
 <li><code>text</code> consists of lower case English letters only.</li> 
 </ul>

 <div><div>Related Topics</div><div><li>Hash Table</li><li>String</li><li>Counting</li></div></div><br><div><li>👍 1647</li><li>👎 96</li></div>
 */
public class MaximumNumberOfBalloons {

    public int maxNumberOfBalloons(String text) {
        Map<Character, Integer> map = new HashMap<>();
        String balloonText = "balloon";
        for (int i = 0; i < balloonText.length(); i++) {
            char ch = balloonText.charAt(i);
            map.put(ch, 0);
        }

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            }
        }

        int min = text.length();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char ch = entry.getKey();
            int value = entry.getValue();
            if (ch == 'l' || ch == 'o') {
                value = value/2;
            }

            if (value < min) {
                min = value;
            }
        }

        return min;
    }
}
