package ca.jrvs.apps.practice;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RegexExcImpUnitTest {

  RegexExcImp regexExcImpTest;

  @Before
  public void setUp() throws Exception {
    regexExcImpTest = new RegexExcImp();
  }

  @Test
  public void matchJpeg() {
    assertFalse(regexExcImpTest.matchJpeg(".jpg"));
    assertFalse(regexExcImpTest.matchJpeg(".jpeg"));
    assertTrue(regexExcImpTest.matchJpeg("1234.jpeg"));
    assertTrue(regexExcImpTest.matchJpeg("abcd.jpeg"));
    assertTrue(regexExcImpTest.matchJpeg("a b c d .jpeg"));
    assertFalse(regexExcImpTest.matchJpeg("1234jpeg"));
    assertFalse(regexExcImpTest.matchJpeg(""));
    assertFalse(regexExcImpTest.matchJpeg(". jpeg"));
  }

  @Test
  public void matchIp() {
    assertTrue(regexExcImpTest.matchIp("1.1.1.1"));
    assertTrue(regexExcImpTest.matchIp("111.111.111.111"));
    assertTrue(regexExcImpTest.matchIp("11.11.11.11"));
    assertFalse(regexExcImpTest.matchIp("1.1.1.1."));
    assertFalse(regexExcImpTest.matchIp("1111.1.1.1"));
    assertFalse(regexExcImpTest.matchIp("a.1.1.1"));
  }

  @Test
  public void isEmptyLine() {
    assertTrue(regexExcImpTest.isEmptyLine(""));
    assertTrue(regexExcImpTest.isEmptyLine("    "));
    assertFalse(regexExcImpTest.isEmptyLine("     something"));
    assertFalse(regexExcImpTest.isEmptyLine("something"));
  }
}