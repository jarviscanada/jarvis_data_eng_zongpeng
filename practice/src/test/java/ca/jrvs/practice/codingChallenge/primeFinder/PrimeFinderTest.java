package ca.jrvs.practice.codingChallenge.primeFinder;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrimeFinderTest {

  @Test
  public void tester(){
    int count = PrimeFinder.primeFinder(20);
    assertEquals(8, count);
  }

}