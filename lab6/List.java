//------------------------------------------------------------------------------------
// Lab6
// Program: List.java
// Author:  James Michael Vrionis
// ID    :  JVrionis
// Date  :  11-8-16
// Purpose: The goal of this assignment is to gain experience with an advanced feature 
// 			in Java called Generics (similar to the notion of Templates in C++.)
//------------------------------------------------------------------------------------

@SuppressWarnings("overrides") // overrides is case sensistive
public class List<T> implements ListInterface<T> {
   // Node Class
   private class Node {
      T item;
      Node next;

      Node (T x) {
         item = x;
         next = null;
      }
   }

   // Class Fields 
   private Node head;
   private int numItems;

   // Constructor 
   public List(){
      head = null;
      numItems = 0;
   }

   // find
   // Helper Function 
   private Node find(int index) {
      Node N = head;
      for(int i = 1; i < index; i++)
         N = N.next;
      return N;
   }

///// ADT OPERATIONS -------------------------------------------

   // isEmpty
   // pre: none
   // post: returns true if this List is empty, false otherwisez
   public boolean isEmpty() {
      return (numItems == 0);
   }

   // size
   // pre: none
   // post: returns the number of elements in this List
   public int size() {
      return numItems;
   }

   // get
   // pre: 1 <= index <= size()
   // post: returns item at position index
   public T get(int index) throws ListIndexOutOfBoundsException {
      if (index < 1 || index > numItems) {
         throw new ListIndexOutOfBoundsException(index + " is not a valid index");
      }
      Node N = find(index);
      return N.item;
   }

   // add
   // inserts newItem in this List at position index
   // pre: 1 <= index <= size()+1
   // post: !isEmpty(), items to the right of newItem are renumbered
   public void add(int index, T newItem) throws ListIndexOutOfBoundsException {
      if (index < 1 || index > numItems+1) {
         throw new ListIndexOutOfBoundsException(index + " is not a valid index");
      }
      if (index == 1) {
         Node N = new Node(newItem);
         N.next = head;
         head = N;
      } else {
         Node A = find(index-1);
         Node B = A.next;
         A.next = new Node(newItem);
         A = A.next;
         A.next = B;

      }
      numItems++;
   }

   // remove
   // deletes item from position index
   // pre: 1 <= index <= size() 
   // post: items to the right of deleted item are renumbered
   public void remove(int index) throws ListIndexOutOfBoundsException {
      if (index < 1 || index > numItems) {
         throw new ListIndexOutOfBoundsException(index + " is not a valid index");
      }
      if (index == 1) {
         Node N = head;
         head = head.next;
         N.next = null;
      } else {
         Node A = find(index-1);
         Node B = A.next;
         A.next = B.next;
         B.next = null;
      }
      numItems--;
   }

   // removeAll
   // pre: none
   // post: isEmpty()
   public void removeAll() {
      head = null;
      numItems = 0;
   }

   // toString() 
   // pre: none
   // post: Overides Object's toString()
   public String toString() {
      String s = "";
      for (Node N = head; N != null; N = N.next) {
         s += N.item.toString() + " ";
      }
      return s;
   }

   //equals()
   //pre: none
   //post: returns using getClass(): which the runtime class associated with an Object.
   @SuppressWarnings("unchecked")
   public boolean equals(Object rhs) {
      boolean eq = false;
      List<T> R = null;
      Node N = null;
      Node M = null;

      if (this.getClass() == rhs.getClass()) {
         R = (List<T>)rhs;
         eq = (this.numItems == R.numItems);

         N = this.head;
         M = R.head;
         while (eq && N != null) {
            eq = (N.item == M.item);
            N = N.next;
            M = M.next;
         }
      }
      return eq;
   }
}
