package ca.jrvs.practice.codingChallenge.twoSum;

import java.util.HashMap;

/**
 * Using hashmap
 * URL: https://www.notion.so/Two-Sum-03ed8416fb8744ecb135fa066db9cc8f
 * Time complexity O(n)
 * Space complexity O(n)
 */
public class TwoSumC {

  public static int[] twoSum (int[] arr, int sum){
    int match;
    HashMap<Integer, Integer> dict = new HashMap<Integer, Integer>();
    for ( int i=0; i<arr.length; i++ ){
      match = sum - arr[i];
      if (dict.containsKey(match)){
        return new int[]{match, arr[i]};
      } else {
        dict.put(arr[i], i);
      }
    }
    return new int[]{};
  }

}
