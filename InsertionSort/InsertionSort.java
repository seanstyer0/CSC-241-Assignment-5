/*
Name: Sean Styer
File: InsertionSort.java
Input: Path of a .txt file of integers
Output: The integers in a linked list sorted with insertion sort
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class InsertionSort{
  public static void main(String[]args){
    String path = args[0];
    
    //create an array of strings from the user file and print the unsorted array
    ListNode unsortedHead = createList(path);

    //sort the linked list using insertion sort
    ListNode sortedHead = sort(unsortedHead, 0);

    //print the sorted linked list
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

  //sort a linked list of integers from greatest to least using insertion sort
  private static ListNode sort(ListNode head,int sorted){
    ListNode current = head;
    ListNode key = head;

    //pass through the linked list to find an unsorted node
    for(int i =0; i<=sorted; i++){
      key=key.next;
    }

    if(key!=null){
      //remove the key from the list
      remove(head,key);
      key.next=null;

      //traverse through the list and place the node in it's appropriate position
      for(int i =0; i<=sorted; i++) {
          //if it's the largest, put it at the beginning of the list
          if (current == head && key.val > current.val) {
            key.next = current;
            head = sort(key, ++sorted);
            return head;
          }

          //If it's somewhere in the middle, insert it between two nodes
          else if(current.val>=key.val && current.next.val<=key.val) {
            insert(current,key);
            head = sort(head,++sorted);
            return head;
          }

          //if you get to the end of the list and it isn't bigger than anything,
          //put it at the end of the list
          else if(i==sorted){
            insert(current,key);
            head = sort(head, ++sorted);
            return head;
          }

          else{
            current=current.next;
          }
        }
      }
        return head;
    }
    private static void remove(ListNode head, ListNode remove){
        ListNode current = head;
        while(true){
            //if the next node is the node to be removed, change the .next reference
            //of current to skip the desired node
            if(current.next == remove){
              current.next = current.next.next;
              break;
            }
            //keep traversing the list
            else{
              current=current.next;
            }
        }
    }

    private static void insert(ListNode first, ListNode middle){
        ListNode end = first.next;
        first.next = middle;
        middle.next = end;
    }
}
  class ListNode{
    int val;
    ListNode next;
    public ListNode(int x){
      val = x;
    }
  }
