package ca.jrvs.practice.codingChallenge.oddOrEven;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class OddOrEvenTest {

  @Test
  public void oddEvenTester(){
    assertEquals("odd", OddOrEvenA.oddEven(11));
    assertEquals("odd", OddOrEvenA.oddEven((Integer) 11));
    assertEquals("odd", OddOrEvenB.oddEven(11));
    assertEquals("odd", OddOrEvenB.oddEven((Integer) 11));
    assertEquals("even", OddOrEvenA.oddEven(10));
    assertEquals("even", OddOrEvenA.oddEven((Integer) 10));
    assertEquals("even", OddOrEvenB.oddEven(10));
    assertEquals("even", OddOrEvenB.oddEven((Integer) 10));
    try {
      Integer num = null;
      assertEquals("odd", OddOrEvenA.oddEven(num));
    } catch (NullPointerException e){
      Assert.fail();
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
  }

}