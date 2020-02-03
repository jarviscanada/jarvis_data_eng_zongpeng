package ca.jrvs.practice.validAnagram;

import java.util.HashMap;

public class ValidAnagram {

  HashMap<Character, Integer> map = new HashMap<Character, Integer>();

  public boolean isAnagram(String s, String t) {
    if (s.length()!=t.length()){
      return false;
    }
    for (int i=0; i<s.length(); i++ ){
      if (map.containsKey(s.charAt(i))){
        map.put(s.charAt(i), map.get(s.charAt(i))+1);
      } else {
        map.put(s.charAt(i), 1);
      }
    }
    for (int i=0; i<t.length(); i++){
      if (!map.containsKey(t.charAt(i))){
        return false;
      }
      int count = map.get(t.charAt(i));
      if (count==0){
        return false;
      } else if (count==1){
        map.remove(t.charAt(i));
      } else {
        map.put(t.charAt(i), count-1);
      }
    }
    return map.isEmpty();
  }

}
