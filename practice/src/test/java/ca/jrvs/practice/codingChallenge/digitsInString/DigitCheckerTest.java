package ca.jrvs.practice.codingChallenge.digitsInString;

import static org.junit.Assert.*;

import org.junit.Test;

public class DigitCheckerTest {

  @Test
  public void tester(){
    String num = "+1234";
    String decimal = "-0.1012";
    String word = "1bs.a1";

    assertTrue(DigitCheckerA.digitChecker(num));
    assertTrue(DigitCheckerB.digitChecker(num));
    assertTrue(DigitCheckerC.digitChecker(num));
    assertTrue(DigitCheckerA.digitChecker(decimal));
    assertFalse(DigitCheckerB.digitChecker(decimal));
    assertTrue(DigitCheckerC.digitChecker(decimal));
    assertFalse(DigitCheckerA.digitChecker(word));
    assertFalse(DigitCheckerB.digitChecker(word));
    assertFalse(DigitCheckerC.digitChecker(word));
  }

}