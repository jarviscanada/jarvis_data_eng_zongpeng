package ca.jrvs.practice.codingChallenge.containsDuplicate;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContainsDuplicateTest {

  @Test
  public void tester(){
    int[] nums1 = {1,2,4,6,4,3,67,4,3};
    int[] nums2 = {3,4,6,7,8,9,12,454,765,14};

    assertTrue(ContainsDuplicateA.containsDuplicate(nums1));
    assertTrue(ContainsDuplicateB.containsDuplicate(nums1));
    assertFalse(ContainsDuplicateA.containsDuplicate(nums2));
    assertFalse(ContainsDuplicateB.containsDuplicate(nums2));
  }

}