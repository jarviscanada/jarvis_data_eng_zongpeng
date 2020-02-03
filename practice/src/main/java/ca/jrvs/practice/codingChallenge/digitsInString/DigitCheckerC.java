package ca.jrvs.practice.codingChallenge.digitsInString;

/**
 * Check if a string contains digits only using ASCII
 * Accept +/- at the beginning and only 1 decimal point
 * https://www.notion.so/Check-if-a-String-contains-only-digits-ef9ddc562d2c472a98f690ed589337d8
 * time complexity O(n) (time complexity of regex match)
 * space complexity O(1)
 */
public class DigitCheckerC {

  public static boolean digitChecker (String str) {
    return str.matches("^[+-]?[0-9]+.?[0-9]+");
  }

}
