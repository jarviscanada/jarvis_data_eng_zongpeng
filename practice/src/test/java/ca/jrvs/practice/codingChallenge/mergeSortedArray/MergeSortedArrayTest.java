package ca.jrvs.practice.codingChallenge.mergeSortedArray;

import static org.junit.Assert.*;

import org.junit.Test;

public class MergeSortedArrayTest {

  @Test
  public void tester(){
    int[] nums1 = {1, 3, 4, 7, 0, 0, 0, 0, 0, 0};
    int[] nums2 = {2, 5, 8};
    MergeSortedArray.mergeSortedArray(nums1, 4, nums2, nums2.length);
    assertEquals(8, nums1[6]);
    assertEquals(0, nums1[7]);
    assertEquals(5, nums1[4]);
  }

}