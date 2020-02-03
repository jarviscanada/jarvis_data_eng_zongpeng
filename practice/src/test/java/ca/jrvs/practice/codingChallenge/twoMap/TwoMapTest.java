package ca.jrvs.practice.codingChallenge.twoMap;

import static org.junit.Assert.*;

import java.util.HashMap;
import org.junit.Test;

public class TwoMapTest {

  @Test
  public void twoMap(){
    HashMap<Integer, String> map1 = new HashMap<Integer, String>();
    HashMap<Integer, String> map2 = new HashMap<Integer, String>();
    map1.put(10, "10");
    map2.put(10, "10");
    assertTrue(TwoMapA.twoMap(map1, map2));

  }
}