package ca.jrvs.practice.codingChallenge.digitsInString;

/**
 * Check if a string contains digits only using ASCII
 * Accept integer numbers only
 * https://www.notion.so/Check-if-a-String-contains-only-digits-ef9ddc562d2c472a98f690ed589337d8
 * time complexity O(n)
 * space complexity O(1)
 */
public class DigitCheckerB {

  public static boolean digitChecker(String str){
    try {
      int num = Integer.valueOf(str);
    } catch (NumberFormatException e){
      return false;
    }
    return true;
  }

}
