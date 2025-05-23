package com.java.code.array;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 <p>Determine if a&nbsp;<code>9 x 9</code> Sudoku board&nbsp;is valid.&nbsp;Only the filled cells need to be validated&nbsp;<strong>according to the following rules</strong>:</p>

 <ol> 
 <li>Each row&nbsp;must contain the&nbsp;digits&nbsp;<code>1-9</code> without repetition.</li> 
 <li>Each column must contain the digits&nbsp;<code>1-9</code>&nbsp;without repetition.</li> 
 <li>Each of the nine&nbsp;<code>3 x 3</code> sub-boxes of the grid must contain the digits&nbsp;<code>1-9</code>&nbsp;without repetition.</li> 
 </ol>

 <p><strong>Note:</strong></p>

 <ul> 
 <li>A Sudoku board (partially filled) could be valid but is not necessarily solvable.</li> 
 <li>Only the filled cells need to be validated according to the mentioned&nbsp;rules.</li> 
 </ul>

 <p>&nbsp;</p> 
 <p><strong class="example">Example 1:</strong></p> 
 <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png" style="height:250px; width:250px" /> 
 <pre>
 <strong>Input:</strong> board = 
 [["5","3",".",".","7",".",".",".","."]
 ,["6",".",".","1","9","5",".",".","."]
 ,[".","9","8",".",".",".",".","6","."]
 ,["8",".",".",".","6",".",".",".","3"]
 ,["4",".",".","8",".","3",".",".","1"]
 ,["7",".",".",".","2",".",".",".","6"]
 ,[".","6",".",".",".",".","2","8","."]
 ,[".",".",".","4","1","9",".",".","5"]
 ,[".",".",".",".","8",".",".","7","9"]]
 <strong>Output:</strong> true
 </pre>

 <p><strong class="example">Example 2:</strong></p>

 <pre>
 <strong>Input:</strong> board = 
 [["8","3",".",".","7",".",".",".","."]
 ,["6",".",".","1","9","5",".",".","."]
 ,[".","9","8",".",".",".",".","6","."]
 ,["8",".",".",".","6",".",".",".","3"]
 ,["4",".",".","8",".","3",".",".","1"]
 ,["7",".",".",".","2",".",".",".","6"]
 ,[".","6",".",".",".",".","2","8","."]
 ,[".",".",".","4","1","9",".",".","5"]
 ,[".",".",".",".","8",".",".","7","9"]]
 <strong>Output:</strong> false
 <strong>Explanation:</strong> Same as Example 1, except with the <strong>5</strong> in the top left corner being modified to <strong>8</strong>. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 </pre>

 <p>&nbsp;</p> 
 <p><strong>Constraints:</strong></p>

 <ul> 
 <li><code>board .length == 9</code></li> 
 <li><code>board[i] .length == 9</code></li> 
 <li><code>board[i][j]</code> is a digit <code>1-9</code> or <code>'.'</code>.</li> 
 </ul>

 <div><div>Related Topics</div><div><li>Array</li><li>Hash Table</li><li>Matrix</li></div></div><br><div><li>👍 10262</li><li>👎 1075</li></div>
 */
public class ValidSudoku extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new char[][] {
                        {'5','3','.','.','7','.','.','.','.'},
                        {'6','.','.','1','9','5','.','.','.'},
                        {'.','9','8','.','.','.','.','6','.'},
                        {'8','.','.','.','6','.','.','.','3'},
                        {'4','.','.','8','.','3','.','.','1'},
                        {'7','.','.','.','2','.','.','.','6'},
                        {'.','6','.','.','.','.','2','8','.'},
                        {'.','.','.','4','1','9','.','.','5'},
                        {'.','.','.','.','8','.','.','7','9'},
                }, true},
                {new char[][] {
                        {'8','3','.','.','7','.','.','.','.'},
                        {'6','.','.','1','9','5','.','.','.'},
                        {'.','9','8','.','.','.','.','6','.'},
                        {'8','.','.','.','6','.','.','.','3'},
                        {'4','.','.','8','.','3','.','.','1'}, 
                        {'7','.','.','.','2','.','.','.','6'},
                        {'.','6','.','.','.','.','2','8','.'},
                        {'.','.','.','4','1','9','.','.','5'},
                        {'.','.','.','.','8','.','.','7','9'},
                }, false},
                {new char[][] {
                        {'5','3','.','.','7','.','.','.','.'},
                        {'6','.','.','1','9','5','.','.','.'},
                        {'.','9','8','.','.','.','.','6','.'},
                        {'8','.','.','.','6','.','.','.','3'},
                        {'4','.','.','8','.','3','.','.','1'},
                        {'7','.','.','.','2','8','.','.','6'},
                        {'.','6','.','.','.','.','2','8','.'},
                        {'.','.','.','4','1','9','.','.','5'},
                        {'.','.','.','.','8','.','.','7','9'},
                }, false},
        };
    }

    @Test(dataProvider = "data")
    public void test(char[][] board, boolean expected) {
        softAssert.as(String.format("board = %s", Arrays.deepToString(board)))
                  .assertThat(isValidSudoku(board))
                  .isEqualTo(expected);
    }

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            char[] row = new char[10];
            char[] column = new char[10];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int value = board[i][j] - '0';
                    if (row[value] != '\u0000') {
                        return false;
                    } else {
                        row[value] = board[i][j];
                    }
                }

                if (board[j][i] != '.') {
                    int value = board[j][i] - '0';
                    if (column[value] != '\u0000') {
                        return false;
                    } else {
                        column[value] = board[j][i];
                    }
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            char[] section = new char[10];
            for (int j = 0; j < 3; j++) {
                for (int k = i * 3; k < i * 3 + 3; k++) {
                    if (board[j][k] != '.') {
                        int value = board[j][k] - '0';
                        if (section[value] != '\u0000') {
                            return false;
                        } else {
                            section[value] = board[j][k];
                        }
                    }
                }
            }

            section = new char[10];
            for (int j = 3; j < 6; j++) {
                for (int k = i * 3; k < i * 3 + 3; k++) {
                    if (board[j][k] != '.') {
                        int value = board[j][k] - '0';
                        if (section[value] != '\u0000') {
                            return false;
                        } else {
                            section[value] = board[j][k];
                        }
                    }
                }
            }

            section = new char[10];
            for (int j = 6; j < 9; j++) {
                for (int k = i * 3; k < i * 3 + 3; k++) {
                    if (board[j][k] != '.') {
                        int value = board[j][k] - '0';
                        if (section[value] != '\u0000') {
                            return false;
                        } else {
                            section[value] = board[j][k];
                        }
                    }
                }
            }
        }
        
        return true;
    }
}
