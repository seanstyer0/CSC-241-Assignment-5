/*
Name: Sean Styer
File: SelectionSort.java
Input: Path to a file of integers
Output: One sorted linked list using selection sort
*/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SelectionSort{
  public static void main(String[]args){
    //get the file path for the data fole
    String filePath = args[0];

    //enter the values into a linked list
    ListNode unsortedHead = createList(filePath);

    //sort the list using selection sort and print it out
    ListNode sortedHead = sort(unsortedHead);

    ListNode current = sortedHead;

    while(current.next != null){
      System.out.print(current.val + " ");
      current = current.next;
    }
    System.out.print(current.val);
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

  //use selection sort to sort a linked list of integers
  public static ListNode sort(ListNode head){
    int max = Integer.MIN_VALUE;

    ListNode current = head;
    ListNode maxLocation = null;
    ListNode findMax = null;

    while(current.next != null){
      maxLocation = null;
      max = Integer.MIN_VALUE;
      findMax = current;

      //pass through the entire list and find the max, saving its location
      while(findMax != null){
        if(findMax.val > max){
          max = findMax.val;
          maxLocation = findMax;
        }
        findMax = findMax.next;
      }

      //swap the values in the current node and the next node
      swap(current, maxLocation);

      ListNode print = head;

      //step the current node forward one node
      current = current.next;
    }
    //return the head of the sorted list
    return head;
  }

  //swap the values in two ListNodes
  private static void swap(ListNode a, ListNode b){
      int temp = a.val;
      a.val = b.val;
      b.val = temp;
  }
}
class ListNode{
  int val;
  ListNode next;
  public ListNode(int x){
    val = x;
  }
}
