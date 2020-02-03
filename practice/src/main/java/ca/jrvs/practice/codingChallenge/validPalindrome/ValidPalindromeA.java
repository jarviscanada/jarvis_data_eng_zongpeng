package ca.jrvs.practice.codingChallenge.validPalindrome;

public class ValidPalindromeA {

  public boolean isPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;
    String lower = s.toLowerCase();
    while (left < right) {
      if (isAlphanumeric(lower.charAt(left))) {
        left++;
        continue;
      } else if (isAlphanumeric(lower.charAt(right))) {
        right--;
        continue;
      } else if (lower.charAt(left) != lower.charAt(right)) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }

  private boolean isAlphanumeric(char c) {
    return (c < 'a' || c > 'z') && (c < '0' || c > '9');
  }
}
