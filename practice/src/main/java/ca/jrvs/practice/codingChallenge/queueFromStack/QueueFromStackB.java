package ca.jrvs.practice.codingChallenge.queueFromStack;

import java.util.Stack;

public class QueueFromStackB <E> {

  private Stack<E> stack1 = new Stack<E>();
  private Stack<E> stack2 = new Stack<E>();
  private E front;

  public void push(E item){
    if (stack1.empty()){
      front = item;
    }
    stack1.push(item);
  }

  public E pop(){
    if (stack2.empty()){
      while (!stack1.empty()){
        stack2.push(stack1.pop());
      }
    }
    return stack2.pop();
  }

  public E peek(){
    if (!stack2.empty()){
      return stack2.peek();
    }
    return front;
  }

  public boolean empty(){
    return (stack1.empty() && stack2.empty());
  }

}
