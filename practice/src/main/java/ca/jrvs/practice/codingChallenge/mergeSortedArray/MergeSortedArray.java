package ca.jrvs.practice.codingChallenge.mergeSortedArray;

/** Merge two sorted list
 * url https://www.notion.so/Merge-Sorted-Array-b6ce14a065e64090a7afa2b04ca6aa49
 * Time complexity O(m+n)
 * Space complexity O(1)
 */
public class MergeSortedArray {

  public static void mergeSortedArray(int[] nums1, int m, int[] nums2, int n){
    int index = m + n -1;
    int count1 = m;
    int count2 = n;
    for (int i = index; i>=0; i--){
      if (count1 <= 0){
        nums1[i] = nums2[count2-1];
        count2--;
      } else if (count2 <= 0) {
        nums1[i] = nums1[count1-1];
        count1--;
      } else if (nums1[count1-1] > nums2[count2-1]){
        nums1[i] = nums1[count1-1];
        count1--;
      } else {
        nums1[i] = nums2[count2-1];
        count2--;
      }
    }
  }

}
