package com.java.code.dynamicprogramming;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

/**
 <p>Given a string <code>s</code> and a dictionary of strings <code>wordDict</code>, return <code>true</code> if <code>s</code> can be segmented into a space-separated sequence of one or more dictionary words.</p>

 <p><strong>Note</strong> that the same word in the dictionary may be reused multiple times in the segmentation.</p>

 <p>&nbsp;</p>
 <p><strong class="example">Example 1:</strong></p>

 <pre>
 <strong>Input:</strong> s = "leetcode", wordDict = ["leet","code"]
 <strong>Output:</strong> true
 <strong>Explanation:</strong> Return true because "leetcode" can be segmented as "leet code".
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> s = "applepenapple", wordDict = ["apple","pen"]
 <strong>Output:</strong> true
 <strong>Explanation:</strong> Return true because "applepenapple" can be segmented as "apple pen apple".
 Note that you are allowed to reuse a dictionary word.
 </pre>

 <p><strong class="example">Example 3:</strong></p>

 <pre>
 <strong>Input:</strong> s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 <strong>Output:</strong> false
 </pre>

 <p>&nbsp;</p>
 <p><strong>Constraints:</strong></p>

 <ul>
 <li><code>1 &lt;= s.length &lt;= 300</code></li>
 <li><code>1 &lt;= wordDict.length &lt;= 1000</code></li>
 <li><code>1 &lt;= wordDict[i].length &lt;= 20</code></li>
 <li><code>s</code> and <code>wordDict[i]</code> consist of only lowercase English letters.</li>
 <li>All the strings of <code>wordDict</code> are <strong>unique</strong>.</li>
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Hash Table</li><li>String</li><li>Dynamic Programming</li><li>Trie</li><li>Memoization</li></div></div><br><div><li>👍 17938</li><li>👎 856</li></div>
 */
public class WordBreak extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {"leetcode", List.of("leet", "code"), true},
                {"applepenapple", List.of("apple", "pen"), true},
                {"catsandog", List.of("cats","dog","sand","and","cat"), false},
                {"bb", List.of("a","b","bbb","bbbb"), true},
                {"aaaaaaa", List.of("aaaa","aaa"), true},
                {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", List.of("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"), false},
        };
    }

    @Test(dataProvider = "data")
    public void test(String s, List<String> wordDict, boolean expected) {
        softAssert.as(String.format("s = %s, wordDict = %s", s, wordDict))
                  .assertThat(wordBreak(s, wordDict))
                  .isEqualTo(expected);
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        return wordBreak(s, words, "", 0, new HashMap<>());
    }

    public boolean wordBreak(String s, Set<String> wordDict, String currentWord, int currentIndex, Map<String, Boolean> memoized) {
        if (currentIndex == s.length()) {
            return currentWord.isEmpty();
        }

        String key = currentWord + currentIndex;
        if (!memoized.containsKey(key)) {
            boolean result;
            String newWord = currentWord + s.charAt(currentIndex);
            if (wordDict.contains(newWord)) {
                result = wordBreak(s, wordDict, newWord, currentIndex + 1, memoized);
                result = result || wordBreak(s, wordDict, "", currentIndex + 1, memoized);
            } else {
                result = wordBreak(s, wordDict, newWord, currentIndex + 1, memoized);
            }
            memoized.put(key, result);
        }
        return memoized.get(key);
    }
}
