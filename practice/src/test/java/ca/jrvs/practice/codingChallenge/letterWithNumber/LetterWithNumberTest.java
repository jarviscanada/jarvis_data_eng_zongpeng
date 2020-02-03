package ca.jrvs.practice.codingChallenge.letterWithNumber;

import static org.junit.Assert.*;

import org.junit.Test;

public class LetterWithNumberTest {

  @Test
  public void tester(){
    String str1 = "abc";
    assertEquals("a1b2c3", LetterWithNumber.letterWithNumber(str1));
    String str2 = "abcABC";
    assertEquals("a1b2c3A1B2C3", LetterWithNumber.letterWithNumber(str2));
    String str3 = "abcABC 123 abcABC";
    assertEquals("a1b2c3A1B2C3 123 a1b2c3A1B2C3", LetterWithNumber.letterWithNumber(str3));
  }

}