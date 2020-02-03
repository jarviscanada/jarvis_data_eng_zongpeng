package ca.jrvs.practice.codingChallenge.reverseLinkedList;

/**Reverse a linked list
 * url https://www.notion.so/Reverse-Linked-List-7ceecefadc4d486fb0290af2e3933fe7
 * Time complexity O(n)
 * Space complexity O(n)
 */

import java.util.LinkedList;

public class ReverseLinkedListB {

  public static <E> LinkedList<E> reverseLinkedList(LinkedList<E> list){
    if (list.size() == 0){
      return list;
    }
    E element = list.removeFirst();
    LinkedList<E> newList = reverseLinkedList(list);
    newList.addLast(element);
    return newList;
  }

}
