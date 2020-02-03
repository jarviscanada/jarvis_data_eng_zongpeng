package ca.jrvs.practice.codingChallenge.missingNumber;

public class MissingNumberC {

  public int missingNumber(int[] nums) {
    int expectedSum = nums.length*(nums.length + 1)/2;
    int actualSum = 0;
    for (int num : nums) actualSum += num;
    return expectedSum - actualSum;
  }

}
