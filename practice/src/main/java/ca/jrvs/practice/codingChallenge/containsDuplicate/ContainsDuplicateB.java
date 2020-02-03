package ca.jrvs.practice.codingChallenge.containsDuplicate;

/** Checking if an array contains duplicate values
 * url https://www.notion.so/Contains-Duplicate-b906e254c6e64566a2be0565470952cc
 * using hashset
 * Time complexity: O(n)
 * Space complexity: O(n)
 */

import java.util.HashSet;

public class ContainsDuplicateB {

  public static boolean containsDuplicate(int[] nums) {
    HashSet<Integer> set = new HashSet<Integer>();
    for (int num : nums){
      if (set.contains(num)){
        return true;
      }
      set.add(num);
    }
    return false;
  }

}
