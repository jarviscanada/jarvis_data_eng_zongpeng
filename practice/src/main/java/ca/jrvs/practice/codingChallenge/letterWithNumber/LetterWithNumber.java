package ca.jrvs.practice.codingChallenge.letterWithNumber;

/** Add number after each letter in a input string
 * url https://www.notion.so/Print-letter-with-number-d8896067b5044da6ae1873ba7eec99c4
 * Ignoring all character other than letter
 * Upper case letter has the same number as lower case letter
 * Time complexity O(n)
 * Space complexity O(n)
 */
public class LetterWithNumber {

  public static String letterWithNumber(String str) {
    int len = str.length();
    StringBuilder stringBuilder = new StringBuilder();
    for (int i =0; i<len; i++){
      char c = str.charAt(i);
      if(c>='a' && c<='z'){
        stringBuilder.append(c);
        stringBuilder.append(c%'a'+1);
      } else if (c>='A' && c<='Z'){
        stringBuilder.append(c);
        stringBuilder.append(c%'A'+1);
      } else {
        stringBuilder.append(c);
        continue;
      }
    }
    return stringBuilder.toString();
  }


}
