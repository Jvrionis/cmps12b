/* Program: Dictionary.java
 * Author:  James Michael Vrionis
 * ID    :  JVrionis
 * Date  :  11-3-16
 * Usuage:  A Dictionary ADT based on the linked list data structure. Elements of the 
 *          Dictionary will be pairs of Strings called key and value. (key, value): 
 *          keys are distinct while values may be repeated. Implement the seven 
 *          given ADT operations given.
 *
 *
 */

public class Dictionary implements DictionaryInterface  {

    // blackbox, private Node class:
    private class Node {
        String key;
        String value;
        Node next;

        Node(String key, String value) {
            this.key = key;
            this.value = value;
            next = null;
        }
    }
    
    // Dictionary fields:
    // head: is a reference to first Node in List
    // tail: is a reference to last Node in list
    // numItems: # of items in the dictionary
    private Node head;
    private Node tail;  
    private int numItems;
    

    // Dictionary Constructor:
    public Dictionary () {
        head = null;
        tail = null;
        numItems = 0;

    }
   
    // findKey()-------------------------------------------------------------------------
    // pre: Head != null
    // post: returns a reference to the Node with this key inside the dictionary
    private Node findKey(String key) {
        Node N = head;
        while( N != null) {
          if(key.compareTo(N.key) == 0) {
               return N;
           }
           else {
               N = N.next;
          }
        }
        return null;
    }

    // ADT Operations ===================================================================

    // isEmpty()-------------------------------------------------------------------------
    // pre: none
    // post: returns true if Dictionary contains no pairs (is empty), false otherwise
    public boolean isEmpty() {
        return(numItems == 0);
    }    
    

    // size()---------------------------------------------------------------------------
    // pre: none
    // post: returns number of (key, value) pairs in the Dictionary
    public int size() {
        return numItems;
    }
    
    // lookup()-------------------------------------------------------------------------
    // pre: none
    // post: returns pair if such pair is contained within the dictionary. 
    //       and null if no such key exists.
     public String lookup(String key) {  
        Node N = findKey(key);
        if( N == null) {
            return null;
        }
        return N.value;
     }

    // insert() 
    // pre: key must not already exist in the Dictionary: lookup(key) == null
    // post: inserts new (key,value) pair into the Dictionary
    public void insert(String key, String value) throws DuplicateKeyException {
        if (lookup(key) != null ) {
            throw new DuplicateKeyException("insert(): cannot insert duplicate keys" +key); 
        }
        if(head == null) {
            Node N = new Node(key,value);
            head = N;
            tail = N;
        }
        else if(head == tail) {
            Node N = head;
            N.next = new Node(key,value);
            tail = N.next;
        }
        else {
           Node N = tail;
           N.next = new Node(key,value);
           tail = N.next;
        }
        numItems++;
    }

    
    // delete()-------------------------------------------------------------------------
    // pre: checks to see if the key is there: lookup(key) != null
    // post: deletes pair with the give key value
    public void delete(String key) throws KeyNotFoundException {
        if(lookup(key) == null) {
           throw new KeyNotFoundException("delete(): cannot delete a non-existent key" + key);
        }
        else if(findKey(key) == head) {
            if(head == tail) {
                head = null;
                tail = null;
            }
            else {
                Node N = head;
                head = N.next;
                N = null;
            }
        }
        else if(findKey(key) == tail) {
            Node S = tail;
            Node P = head;
            while(P.next != tail) {
                P = P.next;
            }
            P.next = null;
            tail = P;
        }
        else {
           Node S = findKey(key);
           Node P = head;
           while(P.next != S) {
               P = P.next;
           }
           P.next = S.next;
           S = null;
        }
        numItems--;       
    }    

     
    // makeEmpty()-------------------------------------------------------------------------
    // pre: none
    // post: Resets the Dictionary to the empty state
    public void makeEmpty() {
        head = null;
        tail = null;
        numItems = 0;
    } 

    // toString()--------------------------------------------------------------------------
    // pre: none
    // post: prints the current state to stdout, Overides Object's toString()
    public String toString() {
        String s = "";
        for (Node N = head; N != null; N = N.next) {
            s += N.key + " " + N.value + "\n";
        }
        return s;
    }
}

