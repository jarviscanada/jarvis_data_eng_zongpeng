package ca.jrvs.practice.codingChallenge.Fibonacci;

import static org.junit.Assert.*;

import org.junit.Test;

public class FibonacciTest {

  @Test
  public void fibonacciTest(){
    int num = FibonacciA.fib(10);
    assertEquals(89, num);
    System.out.println(num);
    num = FibonacciA.fib(99);
    System.out.println(num);
  }
}