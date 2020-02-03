package ca.jrvs.practice.codingChallenge.containsDuplicate;

/** Checking if an array contains duplicate values
 * url https://www.notion.so/Contains-Duplicate-b906e254c6e64566a2be0565470952cc
 * use sorting
 * Time complexity: O(n log(n))
 * Space complexity: O(1)
 */

import java.util.Arrays;

public class ContainsDuplicateA {

  public static boolean containsDuplicate(int[] nums){
    Arrays.sort(nums);

    for (int i = 1; i<nums.length; i++){
      if (nums[i] == nums[i-1]){
        return true;
      }
    }
    return false;
  }


}
