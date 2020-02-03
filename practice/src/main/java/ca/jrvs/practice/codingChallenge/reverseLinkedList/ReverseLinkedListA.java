package ca.jrvs.practice.codingChallenge.reverseLinkedList;

/** Reverse a linked list (original list is modified to empty)
 * url https://www.notion.so/Reverse-Linked-List-7ceecefadc4d486fb0290af2e3933fe7
 * Time complexity O(n)
 * Space complexity O(1)
 */

import java.util.LinkedList;

public class ReverseLinkedListA {

  public static <E> LinkedList<E> reverseLinkedList (LinkedList<E> list){
    int len = list.size();
    LinkedList<E> newList = new LinkedList();
    for (int i = 0; i<len; i++){
      E element = list.removeFirst();
      newList.addFirst(element);
    }
    return newList;
  }

}
