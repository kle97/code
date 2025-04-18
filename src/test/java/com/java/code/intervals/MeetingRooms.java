package com.java.code.intervals;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Given an array of meeting time interval objects consisting of start and end times</p>
 * <p>[[start_1,end_1],[start_2,end_2],...] (start_i < end_i), </p>
 * <p>determine if a person could add all meetings to their schedule without any conflicts.<p>
 * <pre>
 * <strong>Example 1: </strong>
 * <strong>Input: </strong> intervals = [(0,30),(5,10),(15,20)]
 * <strong>Output: </strong> false
 * <strong>Explanation: </strong> (0,30) and (5,10) will conflict (0,30) and (15,20) will conflict
 * </pre>
 * <pre>
 * <strong>Example 2: </strong>
 * <strong>Input: </strong> intervals = [(5,8),(9,15)]
 * <strong>Output: </strong> true
 * </pre>
 *
 * <p><strong>Note: </strong> (0,8),(8,10) is not considered a conflict at 8</p>
 * <p></p>
 * <p><strong>Constraints: </strong></p>
 * <ul>
 * <li>0 <= intervals.length <= 500</li>
 * <li>0 <= intervals[i].start < intervals[i].end <= 1,000,000</li>
 * </ul>
 */
public class MeetingRooms extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {new ArrayList<>(List.of(new Interval(0, 30), new Interval(5, 10), new Interval(15, 20))), false},
                {new ArrayList<>(List.of(new Interval(5,8), new Interval(9,15))), true},
        };
    }

    @Test(dataProvider = "data")
    public void test(List<Interval> intervals, boolean expected) {
        softAssert.as(String.format("intervals = %s", intervals))
                  .assertThat(canAttendMeetings(intervals))
                  .isEqualTo(expected);
    }

    public boolean canAttendMeetings(List<Interval> intervals) {
        intervals.sort((a, b) -> a.start - b.start);
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start < intervals.get(i - 1).end) {
                return false;
            }
        }
        return true;
    }

    public static class Interval {
        private final int start;
        private final int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public String toString() {
            return "(" + start + "," + end + ")";
        }
    }
}
