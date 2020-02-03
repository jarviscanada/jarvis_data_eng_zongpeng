package ca.jrvs.practice.codingChallenge.queueFromStack;

import java.util.Stack;

public class QueueFromStackA <E> {

  private Stack<E> stack1 = new Stack<E>();
  private Stack<E> stack2 = new Stack<E>();

  public void push(E x) {
    while (!stack1.empty()){
      stack2.push(stack1.pop());
    }
    stack2.push(x);
    while (!stack2.empty()){
      stack1.push(stack1.pop());
    }
  }

  public E pop() {
    return stack1.pop();
  }

  public E peek() {
    return stack1.peek();
  }

  public boolean empty() {
    return stack1.empty();
  }

}
