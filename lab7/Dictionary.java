//------------------------------------------------------------------------------
//// lab7
//// Program: Dictionary.java
//// Author:  James Michael Vrionis
//// ID    :  JVrionis
//// Date  :  12-1-16
//// 
////----------------------------------------------------------------------------


public class Dictionary implements DictionaryInterface {
   
   // Private Inner Node
   private class Node {
      Pair item;
      Node left;
      Node right;

      Node(Pair x) {
         item = x;
         left = null;
         right = null;
      }
   }
   // Private Inner Pair
   private class Pair {
      String key;
      String value;

      Pair(String k, String v) {
         key = k;
         value = v;
      }
   }
   // Private Fields
   private Node root;
   private int numItems;

   // Constructor
   public Dictionary() {
      root = null;
      numItems = 0;
   }
   // Private Helper Functions ------------------------------------------

   // findKey()
   // returns the Node containing key k in the subtree rooted at R,
   // or returns Null if no such Node exists 
   private Node findKey(Node R, String k) {
      if (R == null || R.item.key.equals(k)) return R;
      if (R.item.key.compareToIgnoreCase(k) > 0) 
         return findKey(R.left, k);      
      else return findKey(R.right, k);
   }

   // findParent()
   // returns the parent of N in the subtree rooted at R,
   // or returns NULL if N is equal to R. (pre: R != NULL) 
   Node findParent(Node N, Node R) {
      Node P = null;
      if (N != R) {
         P = R;
         while(P.left != N && P.right != N) {
            if (N.item.key.compareToIgnoreCase(P.item.key) < 0)
               P = P.left;
            else
               P = P.right;
         }
      }
      return P;
   }

   // findLeftmost()
   // returns the leftmost Node in the subtree rooted at R, or NULL if R is NULL 
   Node findLeftmost(Node R) {
      Node L = R;
      if (L != null)
         while(L.left != null) L = L.left; 
         //for ( ; L.left != null; L = L.left);
         return L;
      }

   // printInOrder()
   // printes the (key, value) pairs belonging to the subtree rooted at R in order
   // of increasing keys to file pointed to by out 
   void printInOrder(Node R) {
      if (R != null) {
         printInOrder(R.left); 
         System.out.println(R.item.key + " " + R.item.value); 
         printInOrder(R.right);
      }
   }

   // deleteAll()
   // deletes all Nodes in the subtree rooted at N 
   void deleteAll(Node N) {
      if (N != null) {
         deleteAll(N.left);
         deleteAll(N.right);
      }
   }

   // ADT Operations ------------------------------------------
   

   // isEmpty()
   // pre: none
   // post: returns true if D is empty, false otherwise
   public boolean isEmpty() {
      return (numItems == 0);
   }

   // size()
   // pre: none 
   // post: returns the number of (key, value) pairs in D
   public int size() {
      return numItems;
   }

   // lookup()
   // pre: none 
   // returns the value v sucvh that (k, v) is in D, 
   // or returns null if no such value v exists
   public String lookup(String key) {
      Node N;
      N = findKey(root, key);
      // if true N=Null, if false 
      return (N == null ? null : N.item.value);
   }

   // insert()
   // pre: lookup(key)==null
   // post: !isEmpty(), size() is increased by one 
   // inserts new (key,value) pair into this Dictionary
   public void insert(String key, String value) throws DuplicateKeyException{
      Node N, A, B, Find;
      Find = findKey(root,key);
      if(Find !=null ) { // findKey(root,key) 
         throw new DuplicateKeyException("Dictionary Error: insert() cannot insert duplicate keys");
      }
      N = new Node(new Pair(key, value));
      B = null;
      A = root;
      while( A != null ) {
         B = A;
         if(A.item.key.compareToIgnoreCase(key)>0) A = A.left;
         else A = A.right;
      }

      if( B == null ) root = N;
      else if(B.item.key.compareToIgnoreCase(key)>0) B.left = N;
      else B.right = N;
      numItems++;
   }

   // delete()
   // pre: lookup(key)!=null 
   // deletes pair with the given key
   public void delete(String key) throws KeyNotFoundException{
      Node N, P, S;
      if(findKey(root, key)==null){
         throw new KeyNotFoundException("Dictionary Error: delete() cannot delete non-existent key");
      }
      N = findKey(root, key);
      if( N.left == null && N.right == null ) {
         if( N == root ){
            root = null;
         }else{
            P = findParent(N, root);           
          if( P.right == N ) P.right = null;
          else P.left = null;
         }
      }else if( N.right == null ) {
         if( N == root ){
            root = N.left;
         }else{
            P = findParent(N, root);
            if( P.right == N ) P.right = N.left;
            else P.left = N.left;
         }
      }else if( N.left == null ){
         if( N == root ){
            root = N.right;
         }else{
            P = findParent(N, root);
            if( P.right == N ) P.right = N.right;
            else P.left = N.right;
         }
      }else {  
         S = findLeftmost(N.right);
         N.item.key = S.item.key;
         N.item.value = S.item.value;
         P = findParent(S, N);
         if( P.right == S ) P.right = S.right;
         else P.left = S.right;
      }
      numItems--;
   }

   // makeEmpty()
   // pre: none 
   // post: resets to the empty state
   public void makeEmpty() {
      deleteAll(root);
      root = null;
      numItems = 0;
   }

   // toString()
   // overrides object's toString method 
   public String toString(){
      printInOrder(root);
      return "";
   }
}
