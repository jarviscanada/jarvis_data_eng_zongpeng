package ca.jrvs.practice.codingChallenge.Fibonacci;

import java.util.HashMap;

/**
 * ticket URL: https://www.notion.so/Fibonacci-Number-Climbing-Stairs-86b63c05931141f59ea93994ac00ca14
 * Solution approach: Recursion
 * time complexity is O(n) since all duplicated calculation is eliminated
 * space complexity is O(n) since the height of tree is n (the max amount of function in stack is n)
 */
public class FibonacciB {

  public static HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();;

  public static int fib(int num) {
    if (memo.containsKey(num)){
      return memo.get(num);
    } else if (num == 0 || num == 1){
      return 1;
    } else {
      Integer value = fib(num-1) + fib(num-2);
      memo.put(num, value);
      return value;
    }
  }

}
