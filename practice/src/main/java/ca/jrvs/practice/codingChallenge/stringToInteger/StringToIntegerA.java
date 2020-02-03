package ca.jrvs.practice.codingChallenge.stringToInteger;

public class StringToIntegerA {

  public static int stringToInteger(String str){
    int num;
    try {
      num = Integer.parseInt(str);
    } catch (NumberFormatException e){
      throw new IllegalArgumentException("Cannot be converted");
    }
    return num;
  }

}
