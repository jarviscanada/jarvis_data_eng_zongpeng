package ca.jrvs.practice.codingChallenge.twoSum;

import static org.junit.Assert.*;

import org.junit.Test;

public class TwoSumTest {

  @Test
  public void tester(){
    int[] arr = new int[]{2, 3, 5, 4, 2, 0};
    int[] matchA = TwoSumA.twoSum(arr, 7);
    int[] matchB = TwoSumB.twoSum(arr, 7);
    int[] matchC = TwoSumC.twoSum(arr, 7);
    assertEquals(2, matchA[0]);
    assertEquals(5, matchA[1]);
    assertEquals(2, matchB[0]);
    assertEquals(5, matchB[1]);
    assertEquals(3, matchC[0]);
    assertEquals(4, matchC[1]);
  }

}