package ca.jrvs.practice.codingChallenge.swapNumbers;

public class SwapNumbersB {

  public static int[] swapNumber(int[] nums){
    nums[0] = nums[1] + nums[0];
    nums[1] = nums[0] - nums[1];
    nums[0] = nums[0] - nums[1];
    return nums;
  }

}
