package ca.jrvs.practice.codingChallenge.twoSum;

import java.util.Arrays;

/**
 * Find match after sorting array
 * Url: https://www.notion.so/Two-Sum-03ed8416fb8744ecb135fa066db9cc8f
 * Time complexity O(log(n)) as quick sort is used / O(log(n)) for finding the matched pair
 * Space complexity O(log(n)) as quick sort is used
 */
public class TwoSumB {

  public static int[] twoSum(int[] arr, int sum){
    Arrays.sort(arr);
    int right = arr.length;
    int left = 0;
    int mid = right/2;
    int match;
    int[] result = new int[2];
    for ( int num : arr){
      match = sum - num;
      if( arr[mid] < match){
        left = mid;
        mid = ( right - mid ) /2 + mid;
      } else if ( arr[mid] > match){
        right = mid;
        mid = (mid - left) / 2 + left;
      } else {
        result = new int[]{num, arr[mid]};
        break;
      }
    }
    if (arr.length == 0){
      throw new IllegalArgumentException("No match found.");
    } else {
      return result;
    }

  }

}
