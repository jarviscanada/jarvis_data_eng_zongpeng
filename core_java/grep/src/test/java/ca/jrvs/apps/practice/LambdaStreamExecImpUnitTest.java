package ca.jrvs.apps.practice;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LambdaStreamExecImpUnitTest {

  LambdaStreamExec lse;

  @Before
  public void setUp() throws Exception {
    lse = new LambdaStreamExecImp();
  }

  @Test
  public void createStrStream() {
    List<String> expected = Arrays.asList("something", "secondSomething");
    List<String> result = lse.createStrStream("something", "secondSomething").collect(Collectors.toList());
    assertEquals(expected, result);
  }

  @Test
  public void toUpperCase() {
    List<String> expected = Arrays.asList("SOMETHING", "SECONDSOMETHING");
    List<String> result = lse.toUpperCase("SoMeThInG", "SeCoNDSomeTHing").collect(Collectors.toList());
    assertEquals(expected, result);
  }

  @Test
  public void filter() {
    List<String> expected = Arrays.asList("something");
    List<String> result = lse.filter(Stream.of("something", "secondSomething"), "second")
        .collect(Collectors.toList());
    assertEquals(expected, result);
  }

  @Test
  public void createIntStream() {
    Integer[] arr = {1, 2, 3, 4, 5};
    List<Integer> expected = Arrays.asList(arr);
    List<Integer> result = lse.createIntStream(new int[]{1,2,3,4,5}).boxed()
        .collect(Collectors.toList());
    assertEquals(expected, result);
  }

  @Test
  public void toList() {
    List<String> expected = Arrays.asList("something", "secondSomething");
    List<String> result = lse.toList(Stream.of("something", "secondSomething"));
    assertEquals(expected, result);
  }

  @Test
  public void squareRootIntStream() {
    List<Double> expected = Arrays.asList(1.0, 2.0, 3.0);
    List<Double> result = lse.squareRootIntStream(IntStream.of(new int[]{1, 4, 9})).boxed()
        .collect(Collectors.toList());
    assertEquals(expected, result);
  }

  @Test
  public void getOdd() {
    List<Integer> expected = Arrays.asList(1, 3, 5, 7, 9);
    List<Integer> result = lse.getOdd(IntStream.rangeClosed(1, 9)).boxed()
        .collect(Collectors.toList());
    assertEquals(expected, result);
  }

  @Test
  public void getLambdaPrinter() {
    Consumer<String> printer = lse.getLambdaPrinter("start>", "<end");
    printer.accept("Message body");
  }

  @Test
  public void printMessages() {
    String[] letters = {"A", "B", "C", "D"};
    lse.printMessages(letters, lse.getLambdaPrinter("Letter:", "...:D"));
  }

  @Test
  public void printOdd() {
    lse.printOdd(lse.createIntStream(6, 11), lse.getLambdaPrinter("Number:", "!?..."));
  }

  @Test
  public void flatNestedInt() {
    List<Integer> expected = Arrays.asList(25, 36, 81, 9, 16, 49);
    List<Integer> a = Arrays.asList(5, 6, 9);
    List<Integer> b = Arrays.asList(3, 4, 7);
    List<List<Integer>> d = Arrays.asList(a, b);
    List<Integer> result = lse.flatNestedInt(d.stream()).collect(Collectors.toList());
    assertEquals(expected, result);
  }

}