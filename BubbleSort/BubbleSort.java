/*
Name: Sean Styer
File: BubbleSort.java
Input: Path to a .txt file of integers
Output: One sorted linked list using bubble sort
*/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class BubbleSort{
  public static void main(String[] args){
    //take in the provided file path and convert the data file into a linked list
    String filePath = args[0];
    ListNode head = createList(filePath);

    //sort and print the sorted list
    sort(head);

    ListNode current = head;

    while(current.next != null){
      System.out.print(current.val + " ");
      current = current.next;
    }
    System.out.print(current.val);
  }

  //sort an array of integers using bubble sort
  public static void sort(ListNode head) {
    boolean didSwap = true;
    ListNode current = head;
    ListNode next = current.next;

    //keep sorting until a pass is performed without swaps
    while(didSwap){
      current = head;
      next = current.next;
      didSwap = false;

      while(next != null){
        //if the current value is less than the next value, swap them
        if(current.val < next.val){
          int temp = current.val;
          current.val = next.val;
          next.val = temp;
          didSwap = true;
        }

        //index the current and next pointers forward
        current = current.next;
        next = next.next;
      }
    }
  }

  //take in a text file of integers and enter them into a linked list
    public static ListNode createList(String filePath){

      File file = new File(filePath);

      //parse through the file and save each integer as a new ListNode
      try {
              Scanner in = new Scanner(file);
              if(in.hasNextInt()){
                ListNode head = new ListNode(in.nextInt());
                ListNode current = head;
                while (in.hasNextInt()) {
                    current.next = new ListNode(in.nextInt());
                    current = current.next;
                }
                return head;
              }
              in.close();
          }
          //in the event of an error, print the error
          catch (FileNotFoundException e) {
              e.printStackTrace();
          }

      return null;
    }

  public static void swapElements(int[] A, int a, int b){
    int temp = A[a];
    A[a] = A[b];
    A[b] = temp;
  }
}
class ListNode{
  int val;
  ListNode next;
  public ListNode(int x){
    val = x;
  }
}
