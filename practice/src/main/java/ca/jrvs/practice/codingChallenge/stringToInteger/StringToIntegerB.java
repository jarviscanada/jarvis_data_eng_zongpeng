package ca.jrvs.practice.codingChallenge.stringToInteger;

public class StringToIntegerB {

  public static int stringToInteger(String str){
    int len = str.length();
    int result = 0;
    for ( int i=0; i<len; i++ ){
      char c = str.charAt(i);
      if (c < '0' || c > '9') {
        throw new IllegalArgumentException("Input string contains non-number characters.");
      }
      result = result + c%'0' * ((int) Math.pow(10, len-1-i));
    }
    return result;
  }

}
