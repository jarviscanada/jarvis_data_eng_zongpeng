package ca.jrvs.practice.codingChallenge.findLargestSmallest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FindLargestSmallestC {

  public static Integer findLargest(List<Integer> list){
    return Collections.max(list);
  }

  public static Integer findSmallest(List<Integer> list){
    return Collections.min(list);
  }
}
