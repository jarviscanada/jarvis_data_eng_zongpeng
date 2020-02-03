package ca.jrvs.practice.codingChallenge.stringToInteger;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringToIntegerTest {

  @Test
  public void tester(){
    String str = "666";
    int numA = StringToIntegerA.stringToInteger(str);
    int numB = StringToIntegerB.stringToInteger(str);
    assertEquals(666, numA);
    assertEquals(666, numB);
  }

}