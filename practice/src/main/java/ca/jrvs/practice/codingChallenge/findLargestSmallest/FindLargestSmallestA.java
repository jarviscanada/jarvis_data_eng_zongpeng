package ca.jrvs.practice.codingChallenge.findLargestSmallest;

import java.util.List;

public class FindLargestSmallestA {

  public static Integer findLargest(List<Integer> list){
    Integer max = Integer.MIN_VALUE;
    for (int i =0; i<list.size(); i++){
      if (list.get(i)>max){
        max = list.get(i);
      }
    }
    return max;
  }

  public static Integer findSmallest(List<Integer> list){
    Integer min = Integer.MAX_VALUE;
    for (int i =0; i<list.size(); i++){
      if (list.get(i)<min){
        min = list.get(i);
      }
    }
    return min;
  }

}
