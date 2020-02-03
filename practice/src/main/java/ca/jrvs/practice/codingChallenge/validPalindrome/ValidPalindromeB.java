package ca.jrvs.practice.codingChallenge.validPalindrome;

public class ValidPalindromeB {

  public boolean isPalindrome(String s) {
    String lower = s.toLowerCase();
    if(s.length() <= 1){
      return true;
    } else if(isAlphanumeric(lower.charAt(0))){
      return isPalindrome(lower.substring(1));
    } else if (isAlphanumeric(lower.charAt(s.length()-1))){
      return isPalindrome(lower.substring(0,s.length()-1));
    } else if (lower.charAt(0) != lower.charAt(s.length()-1)){
      return false;
    } else {
      return isPalindrome(lower.substring(1,s.length()-1));
    }
  }

  private boolean isAlphanumeric(char c){
    return (c<'a' || c>'z')&&(c<'0' || c>'9');
  }

}
