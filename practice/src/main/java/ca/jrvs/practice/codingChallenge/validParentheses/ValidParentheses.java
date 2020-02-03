package ca.jrvs.practice.codingChallenge.validParentheses;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class ValidParentheses {

  private HashMap<Character,Character> chars = new HashMap<Character, Character>();
  private Stack<Character> stack = new Stack<Character>();

  public boolean isValid(String s) {
    chars.put(')','(');
    chars.put(']','[');
    chars.put('}','{');
    int len = s.length();
    for (int i = 0; i<len; i++){
      char c = s.charAt(i);
      if (c=='(' || c=='[' || c=='{'){
        stack.push(c);
      } else if (chars.containsKey(c)){
        if (stack.empty()){
          return false;
        }
        if (chars.get(c)!=stack.pop()){
          return false;
        }
      }
    }
    if (!stack.empty()){
      return false;
    }
    return true;
  }
}
