package ca.jrvs.practice.codingChallenge.reverseLinkedList;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.Test;

public class ReverseLinkedListTest {

  @Test
  public void tester(){
    Integer[] nums = {1,2,3,4,5,6,7,8};
    LinkedList<Integer> listA = new LinkedList<Integer>(Arrays.asList(nums));
    LinkedList<Integer> newListA = ReverseLinkedListA.reverseLinkedList(listA);
    LinkedList<Integer> listB = new LinkedList<Integer>(Arrays.asList(nums));
    LinkedList<Integer> newListB = ReverseLinkedListB.reverseLinkedList(listB);
    assertEquals(nums[0], newListA.removeLast());
    assertEquals(nums[7], newListA.removeFirst());
    assertEquals(nums[0], newListB.removeLast());
    assertEquals(nums[7], newListB.removeFirst());
  }
}