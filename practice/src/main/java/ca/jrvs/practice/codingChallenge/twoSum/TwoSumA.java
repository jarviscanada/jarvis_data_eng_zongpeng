package ca.jrvs.practice.codingChallenge.twoSum;

import java.lang.reflect.Array;

/**
 * Brute force (two nested for loop)
 * https://www.notion.so/Two-Sum-03ed8416fb8744ecb135fa066db9cc8f
 * Time complexity O(n^2)
 * Space complexity O(1)
 */
public class TwoSumA {

  public static int[] twoSum(int[] arr, int sum) {
    for (int num1 : arr){
      for (int num2 : arr){
        if (num1 + num2 == sum){
          return new int[]{num1, num2};
        }
      }
    }
    return new int[]{};
  }
}
