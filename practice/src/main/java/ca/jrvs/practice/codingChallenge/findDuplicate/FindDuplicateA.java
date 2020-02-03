package ca.jrvs.practice.codingChallenge.findDuplicate;

import java.util.Arrays;

public class FindDuplicateA {

  public static int findDuplicate(int[] nums){
    Arrays.sort(nums);
    for (int i =1 ; i<nums.length; i++){
      if (nums[i] == nums[i-1]){
        return nums[i];
      }
    }
    return 0;
  }

}
