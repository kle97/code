package com.java.code.binarysearch;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

/**
 * <p>Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.</p>
 * <p>Implement the<span>&nbsp;</span><code>TimeMap</code><span>&nbsp;</span>class:</p>
 * <ul>
 * <li><code>TimeMap()</code><span>&nbsp;</span>Initializes the object of the data structure.</li>
 * <li><code>void set(String key, String value, int timestamp)</code><span>&nbsp;</span>Stores the key<span>&nbsp;</span><code>key</code><span>&nbsp;</span>with the value<span>&nbsp;</span><code>value</code><span>&nbsp;</span>at the given time<span>&nbsp;</span><code>timestamp</code>.</li>
 * <li><code>String get(String key, int timestamp)</code><span>&nbsp;</span>Returns a value such that<span>&nbsp;</span><code>set</code><span>&nbsp;</span>was called previously, with<span>&nbsp;</span><code>timestamp_prev &lt;= timestamp</code>. If there are multiple such values, it returns the value associated with the largest<span>&nbsp;</span><code>timestamp_prev</code>. If there are no values, it returns<span>&nbsp;</span><code>""</code>.</li>
 * </ul>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input</strong>
 * ["TimeMap", "set", "get", "get", "set", "get", "get"]
 * [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
 * <strong>Output</strong>
 * [null, null, "bar", "bar", null, "bar2", "bar2"]
 *
 * <strong>Explanation</strong>
 * TimeMap timeMap = new TimeMap();
 * timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
 * timeMap.get("foo", 1);         // return "bar"
 * timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
 * timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
 * timeMap.get("foo", 4);         // return "bar2"
 * timeMap.get("foo", 5);         // return "bar2"
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>1 &lt;= key.length, value.length &lt;= 100</code></li>
 * <li><code>key</code><span>&nbsp;</span>and<span>&nbsp;</span><code>value</code><span>&nbsp;</span>consist of lowercase English letters and digits.</li>
 * <li><code>1 &lt;= timestamp &lt;= 10<sup>7</sup></code></li>
 * <li>All the timestamps<span>&nbsp;</span><code>timestamp</code><span>&nbsp;</span>of<span>&nbsp;</span><code>set</code><span>&nbsp;</span>are strictly increasing.</li>
 * <li>At most<span>&nbsp;</span><code>2 * 10<sup>5</sup></code><span>&nbsp;</span>calls will be made to<span>&nbsp;</span><code>set</code><span>&nbsp;</span>and<span>&nbsp;</span><code>get</code>.</li>
 * </ul>
 */
public class TimeBasedKeyValueStore extends BaseTest {

    @Test
    public void test() {
        TimeMap obj = new TimeMap();
        obj.set("foo", "bar", 1);
        String param1 = obj.get("foo", 1);
        softAssert.as(String.format("key = %s, timestamp = %d", "foo", 1)).assertThat(param1).isEqualTo("bar");

        String param2 = obj.get("foo", 3);
        softAssert.as(String.format("key = %s, timestamp = %d", "foo", 3)).assertThat(param2).isEqualTo("bar");

        obj.set("foo", "bar2", 4);
        String param3 = obj.get("foo", 4);
        softAssert.as(String.format("key = %s, timestamp = %d", "foo", 4)).assertThat(param3).isEqualTo("bar2");

        String param4 = obj.get("foo", 5);
        softAssert.as(String.format("key = %s, timestamp = %d", "foo", 5)).assertThat(param4).isEqualTo("bar2");

    }

    class TimeMap {

        Map<String, List<Pair>> map = new HashMap<>();

        public TimeMap() {
        }

        public void set(String key, String value, int timestamp) {
            List<Pair> list;

            if (!map.containsKey(key)) {
                list = new ArrayList<>();
            } else {
                list = map.get(key);
            }

            list.add(new Pair(value, timestamp));
            map.put(key, list);
        }

        public String get(String key, int timestamp) {
            if (map.containsKey(key)) {
                String result = "";
                List<Pair> list = map.get(key);
                int i = 0;
                int j = list.size() - 1;
                while (i <= j) {
                    int mid = (i + j) / 2;
                    Pair midPair = list.get(mid);
                    if (midPair.timestamp == timestamp) {
                        result = midPair.value;
                        break;
                    } else if (timestamp < midPair.timestamp) {
                        j = mid - 1;
                    } else {
                        result = midPair.value;
                        i = mid + 1;
                    }
                }

                return result;
            }

            return "";
        }

        class Pair {
            private String value;
            private int timestamp;
            public Pair(String value, int timestamp) {
                this.value = value;
                this.timestamp = timestamp;
            }
        }
    }
}
