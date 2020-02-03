package ca.jrvs.practice.codingChallenge.duplicateCharacter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DuplicateCharacter {

  private static HashMap<Character, Integer> map = new HashMap<>();

  public static Character[] duplicateCharacter(String str){
    List<Character> list = new ArrayList<>();
    for (int i =0; i<str.length(); i++){
      if (!map.containsKey(str.charAt(i))){
        map.put(str.charAt(i), 1);
      } else if (map.get(str.charAt(i))>=2){
        continue;
      } else {
        list.add(str.charAt(i));
      }
    }
    return  list.toArray(new Character[list.size()]);
  }
}
