package ca.jrvs.practice.codingChallenge.findLargestSmallest;

import java.util.List;
import java.util.NoSuchElementException;

public class FindLargestSmallestB {

  public static Integer findLargest(List<Integer> list){
    Integer max = list.stream().mapToInt(v -> v).max().orElseThrow(NoSuchElementException::new);
    return max;
  }

  public static Integer findSmallest(List<Integer> list){
    Integer min = list.stream().mapToInt(v -> v).min().orElseThrow(NoSuchElementException::new);
    return min;
  }

}
