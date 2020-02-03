package ca.jrvs.practice.codingChallenge.rotateString;

public class RotateString {

  public boolean rotateString(String A, String B) {
    if (A.length() != B.length()){
      return false;
    }
    String twoA = A+A;
    return twoA.contains(B);
  }

}
