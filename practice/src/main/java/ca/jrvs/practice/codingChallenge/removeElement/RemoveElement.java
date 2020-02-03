package ca.jrvs.practice.codingChallenge.removeElement;

public class RemoveElement {

  public int removeElement(int[] nums, int val) {
    int l = 0, r = nums.length-1;
    while (l <= r) {
      if (nums[l] == val)
        nums[l] = nums[r--];
      else
        l++;
    }
    return l;
  }

}
