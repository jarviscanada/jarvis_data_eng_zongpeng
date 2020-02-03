package ca.jrvs.practice.codingChallenge.missingNumber;

public class MissingNumberA {
  public int missingNumber(int[] nums) {
    int sum=0;
    int sumArray=0;
    for (int i = 0; i<nums.length; i++){
      sum= sum + i +1;
      sumArray+=nums[i];
    }
    return sum - sumArray;
  }

}
