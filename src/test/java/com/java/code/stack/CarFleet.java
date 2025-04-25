package com.java.code.stack;

import com.java.code.common.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * <p>There are<span>&nbsp;</span><code>n</code><span>&nbsp;</span>cars going to the same destination along a one-lane road. The destination is<span>&nbsp;</span><code>target</code><span>&nbsp;</span>miles away.</p>
 * <p>You are given two integer array<span>&nbsp;</span><code>position</code><span>&nbsp;</span>and<span>&nbsp;</span><code>speed</code>, both of length<span>&nbsp;</span><code>n</code>, where<span>&nbsp;</span><code>position[i]</code><span>&nbsp;</span>is the position of the<span>&nbsp;</span><code>i<sup>th</sup></code><span>&nbsp;</span>car and<span>&nbsp;</span><code>speed[i]</code><span>&nbsp;</span>is the speed of the<span>&nbsp;</span><code>i<sup>th</sup></code><span>&nbsp;</span>car (in miles per hour).</p>
 * <p>A car can never pass another car ahead of it, but it can catch up to it&nbsp;and drive bumper to bumper<span>&nbsp;</span><strong>at the same speed</strong>. The faster car will<span>&nbsp;</span><strong>slow down</strong><span>&nbsp;</span>to match the slower car's speed. The distance between these two cars is ignored (i.e., they are assumed to have the same position).</p>
 * <p>A<span>&nbsp;</span><strong>car fleet</strong><span>&nbsp;</span>is some non-empty set of cars driving at the same position and same speed. Note that a single car is also a car fleet.</p>
 * <p>If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.</p>
 * <p>Return<span>&nbsp;</span><em>the<span>&nbsp;</span><strong>number of car fleets</strong><span>&nbsp;</span>that will arrive at the destination</em>.</p>
 * <p>&nbsp;</p>
 * <p><strong class="example">Example 1:</strong></p>
 * <pre><strong>Input:</strong> target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
 * <strong>Output:</strong> 3
 * <strong>Explanation:</strong>
 * The cars starting at 10 (speed 2) and 8 (speed 4) become a fleet, meeting each other at 12.
 * The car starting at 0 does not catch up to any other car, so it is a fleet by itself.
 * The cars starting at 5 (speed 1) and 3 (speed 3) become a fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.
 * Note that no other cars meet these fleets before the destination, so the answer is 3.
 * </pre>
 * <p><strong class="example">Example 2:</strong></p>
 * <pre><strong>Input:</strong> target = 10, position = [3], speed = [3]
 * <strong>Output:</strong> 1
 * <strong>Explanation:</strong> There is only one car, hence there is only one fleet.
 * </pre>
 * <p><strong class="example">Example 3:</strong></p>
 * <pre><strong>Input:</strong> target = 100, position = [0,2,4], speed = [4,2,1]
 * <strong>Output:</strong> 1
 * <strong>Explanation:</strong>
 * The cars starting at 0 (speed 4) and 2 (speed 2) become a fleet, meeting each other at 4. The fleet moves at speed 2.
 * Then, the fleet (speed 2) and the car starting at 4 (speed 1) become one fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.
 * </pre>
 * <p>&nbsp;</p>
 * <p><strong>Constraints:</strong></p>
 * <ul>
 * <li><code>n == position .length == speed .length</code></li>
 * <li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
 * <li><code>0 &lt; target &lt;= 10<sup>6</sup></code></li>
 * <li><code>0 &lt;= position[i] &lt; target</code></li>
 * <li>All the values of<span>&nbsp;</span><code>position</code><span>&nbsp;</span>are<span>&nbsp;</span><strong>unique</strong>.</li>
 * <li><code>0 &lt; speed[i] &lt;= 10<sup>6</sup></code></li>
 * </ul>
 */
public class CarFleet extends BaseTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {12, new int[] {10,8,0,5,3}, new int[]{2,4,1,1,3}, 3},
                {10, new int[] {3}, new int[]{3}, 1},
                {100, new int[] {0,2,4}, new int[]{4,2,1}, 1},
                {10, new int[] {0,4,2}, new int[]{2,1,3}, 1},
        };
    }

    @Test(dataProvider = "data")
    public void test(int target, int[] position, int[] speed, int expected) {
        softAssert.as(String.format("target = %d, position = %s, speed = %s", target, Arrays.toString(position), Arrays.toString(speed)))
                  .assertThat(carFleet(target, position, speed))
                  .isEqualTo(expected);
    }

    public int carFleet(int target, int[] position, int[] speed) {
        Car[] cars = new Car[position.length];
        for (int i = 0; i < position.length; i++) {
            cars[i] = new Car(position[i], speed[i]);
        }

        Arrays.sort(cars, (a, b) -> b.position - a.position);

        int numberOfFleets = 1;
        double lastArrivingTime = (double) (target - cars[0].position) / cars[0].speed;
        for (int i = 1; i < cars.length; i++) {
            Car current = cars[i];
            double currentArrivingTime = (double) (target - current.position) / current.speed;
            if (currentArrivingTime > lastArrivingTime) {
                numberOfFleets++;
                lastArrivingTime = currentArrivingTime;
            }

        }

        return numberOfFleets;
    }

    static class Car {
        private int position;
        private int speed;

        public Car(int position, int speed) {
            this.position = position;
            this.speed = speed;
        }
    }
}