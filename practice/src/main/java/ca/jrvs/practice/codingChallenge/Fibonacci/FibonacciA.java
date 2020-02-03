package ca.jrvs.practice.codingChallenge.Fibonacci;

/**
 * ticket URL: https://www.notion.so/Fibonacci-Number-Climbing-Stairs-86b63c05931141f59ea93994ac00ca14
 * Solution approach: Recursion
 * time complexity is O(2^n) since it forms a binary tree
 * space complexity is O(n) since the height of tree is n (the max amount of function in stack is n)
 */
public class FibonacciA {

  public static int fib(int num){
    if (num == 1 || num == 0){
      return 1;
    } else {
      return fib(num-1) + fib(num-2);
    }
  }

  public static int fib(Integer num){
    if (num == null){
      throw new IllegalArgumentException("The input is null.");
    }
    return fib(num.intValue());
  }
}
