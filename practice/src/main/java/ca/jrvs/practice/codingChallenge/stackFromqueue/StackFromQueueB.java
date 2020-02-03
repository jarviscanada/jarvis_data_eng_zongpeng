package ca.jrvs.practice.codingChallenge.stackFromqueue;

import java.util.LinkedList;
import java.util.Queue;

public class StackFromQueueB {

  private Queue<Integer> queue = new LinkedList<Integer>();

  public void push(int x) {
    int size = queue.size();
    queue.add(x);
    for (int i = 0; i<size; i++){
      queue.add(queue.remove());
    }
  }

  public int pop() {
    return queue.remove();
  }

  public int top() {
    return queue.peek();
  }

  public boolean empty() {
    return queue.isEmpty();
  }

}
