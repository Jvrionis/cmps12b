/*
 *  DictionaryTest.java
 *  James Michael Vrionis
 *
 * 
 */

public class DictionaryTest {

   public static void main(String[] args) {
      String TestPrint;
      Dictionary C = new Dictionary();
      C.insert("1","here");
      C.insert("2","is");
      C.insert("3","my");
      C.insert("4","tester");
      C.insert("5","for");
      C.insert("6", "Dictionary");
      C.insert("7", "Client");
      System.out.println(C);

      System.out.println("size = " + C.size()); 

      System.out.println(C);

      TestPrint = C.lookup("1");
      // Check if null value returned by lookup:
      System.out.println("Key = 1 " +(TestPrint == null ?"not found": ("value=" +TestPrint)));
      System.out.println("--- calling isEmpty(): "+ C.isEmpty() + " has been called");

      System.out.println("--- Calling makeEmpty()--- ");
      C.makeEmpty();

      System.out.println("--- isEmpty() called: " + C.isEmpty());
      System.out.println("--- size of C after isEmpty() = " + C.size());
      TestPrint = C.lookup("4");
      System.out.println("Key = 4 " +(TestPrint == null ?"not found": ("value=" +TestPrint)));

      TestPrint = C.lookup("2");
      System.out.println("Key = 2 " +(TestPrint == null ?"not found": ("value=" +TestPrint)));
      
      // Test insert 
      C.insert("iAMshowingAtest", "anotherTEST");
      C.insert("cheese", "2");
      C.insert("bread", "3");
      System.out.println(C);
      System.out.println("size = " + C.size());

      System.out.println("Now, deleting bread and cheese");
      C.delete("bread");
      C.delete("cheese");

      System.out.println(C);
      System.out.println("Now size = " + C.size());

      C.delete("cheese"); // A Test for KeyNotFoundException

//      TestPrint = C.lookup("3");
/*      System.out.println("--- makeEmpty() 

       
      String v;
      Dictionary A = new Dictionary();
      A.insert("1","a");
      A.insert("2","b");
      A.insert("3","c");
      A.insert("4","d");
      A.insert("5","e");
      A.insert("6","f");
      A.insert("7","g");
      System.out.println(A);

      v = A.lookup("1");
      System.out.println("key=1 "+(v==null?"not found":("value="+v)));
      v = A.lookup("3");
      System.out.println("key=3 "+(v==null?"not found":("value="+v)));
      v = A.lookup("7");
      System.out.println("key=7 "+(v==null?"not found":("value="+v)));
      v = A.lookup("8");
      System.out.println("key=8 "+(v==null?"not found":("value="+v)));
      System.out.println();

      // A.insert("2","f");  // causes DuplicateKeyException

      A.delete("1");
      A.delete("3");
      A.delete("7");
      System.out.println(A);

      // A.delete("8");  // causes KeyNotFoundException

      System.out.println(A.isEmpty());
      System.out.println(A.size());
      A.makeEmpty();
      System.out.println(A.isEmpty());
      System.out.println(A);
*/
   }
}
