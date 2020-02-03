package ca.jrvs.practice.codingChallenge.digitsInString;

/**
 * Check if a string contains digits only using ASCII
 * Accept +/- at the beginning and only 1 decimal point
 * https://www.notion.so/Check-if-a-String-contains-only-digits-ef9ddc562d2c472a98f690ed589337d8
 * time complexity O(n)
 * space complexity O(1)
 */
public class DigitCheckerA {

  public static boolean digitChecker (String str){
    int len = str.length();
    int count = 0;
    for (int i=0; i<len; i++){
      if (i==0 && (str.charAt(i)=='+' || str.charAt(i)=='-')){
        continue;
      }
      if (str.charAt(i) == '.'){
        if (count >= 1){
          return false;
        }
        count = count + 1;
        continue;
      }
      if (str.charAt(i) < '0' || str.charAt(i) > '9'){
        return false;
      }
    }
    return true;
  }

}
