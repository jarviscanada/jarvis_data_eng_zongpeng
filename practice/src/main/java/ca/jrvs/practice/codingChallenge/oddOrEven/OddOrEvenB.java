package ca.jrvs.practice.codingChallenge.oddOrEven;

/**
 * Ticket URL: https://www.notion.so/Sample-Check-if-a-number-is-even-or-odd-8b6b7c7a92954622ab53ab877e4eddcf
 * Solution approach: bit operator
 * Time complexity: O(1) It is constant time operation because there is no loop.
 */
public class OddOrEvenB {

  public static String oddEven(int num){
    if ((num >> 1)*2 == num){
      return "even";
    }
    return "odd";
  }

  public static String oddEven(Integer num){
    if (num == null){
      throw new IllegalArgumentException("Number you entered is null.");
    }
    return oddEven(num.intValue());
  }
}
