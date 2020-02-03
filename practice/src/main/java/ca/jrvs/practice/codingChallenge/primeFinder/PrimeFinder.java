package ca.jrvs.practice.codingChallenge.primeFinder;

import java.util.Arrays;

/**
 * Find count of prime numbers less or equal than a given number
 * https://www.notion.so/Count-Primes-4e33f7d387104e739a1391876a0ba74c
 * Time complexity O(n*log(log(n)))
 * Space complexity O(n)
 */
public class PrimeFinder {

  public static int primeFinder(int n){
    boolean[] indicator = new boolean[n+1];
    Arrays.fill(indicator, Boolean.TRUE);
    indicator[0] = false;
    indicator[1] = false;
    for (int i = 2; i*i<=n; i++){
      if (!indicator[i]){
        continue;
      }
      for (int j = i * i; j <= n; j+=i){
        indicator[j] = false;
      }
    }
    int count = 0;
    for (boolean b : indicator){
      if (b){
        count++;
      }
    }
    return count;
  }

}
