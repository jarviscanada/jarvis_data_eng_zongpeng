package ca.jrvs.practice.dataStructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

public abstract class TestGround {

  public abstract int count();


  public static void main(String[] args) {
    int left = 0;
    int right = 10;
    int mid = (right - left)/ 2;
    System.out.println(left++);
    System.out.println(left++);
    System.out.println(mid);
    char c = '1';
    int num = c%48;
    System.out.println(c%'0');
    HashSet<Integer> set = new HashSet<Integer>();
    System.out.println("9.9".matches("^[+-]?[0-9]+.?[0-9]+"));
    String str = "abcde";
    System.out.println(str.substring(1));
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    map.put('a', 1);
    map.remove('a');
    System.out.println(map);
    int[] arr = {1,2,3,5,6,87};
    System.out.println(12^4);
  }
}
