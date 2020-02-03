package ca.jrvs.practice.codingChallenge.findDuplicate;

import java.util.HashSet;

public class FindDuplicateB {
  HashSet<Integer> set = new HashSet<Integer>();
  public int findDuplicate(int[] nums) {
    for (int i =0; i<nums.length; i++){
      if (set.contains(nums[i])){
        return nums[i];
      }
      set.add(nums[i]);
    }
    return -1;
  }
}
