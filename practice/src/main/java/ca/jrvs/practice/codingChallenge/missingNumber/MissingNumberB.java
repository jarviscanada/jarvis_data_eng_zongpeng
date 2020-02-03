package ca.jrvs.practice.codingChallenge.missingNumber;

import java.util.HashSet;

public class MissingNumberB {
  HashSet<Integer> set = new HashSet<Integer>();
  public int missingNumber(int[] nums) {
    for (int i = 0; i<nums.length; i++){
      set.add(nums[i]);
    }
    for (int i=0; i<=nums.length; i++){
      if (!set.contains(i)){
        return i;
      }
    }
    return -1;
  }

}
