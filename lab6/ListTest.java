//-----------------------------------------------------------------------------
// Lab6
// Program: ListTest.java
// Author:  James Michael Vrionis
// ID    :  JVrionis
// Date  :  11-8-16
// Purpose: Test List.java ADT (generics) Using String, Integer  and Double
//-----------------------------------------------------------------------------

public class ListTest{
    public static void main(String[] args) {
        System.out.println("\n--------------------- Integer Test ---------------------");         
        List<Integer> G = new List<Integer>();
        List<Integer> H = new List<Integer>();
        List<List<Integer>> I = new List<List<Integer>>();
//        int m,n,p; 

        G.add(1, 12);
        G.add(2, 24);
        G.add(3, 48);
        H.add(1, 88);
        H.add(2, 120);
        H.add(3, 300);
        I.add(1, G);
        I.add(2, H);
      
        System.out.println("G: "+G);
        System.out.println("H: "+H);
        System.out.println("I: "+I);
        System.out.println("G.equals(G) is "+G.equals(G));
        System.out.println("G.equals(H) is "+G.equals(H));
        System.out.println("G.equals(I) is "+G.equals(I)); 
        System.out.println("G.size() is "+G.size());
        System.out.println("H.size() is "+H.size());
        System.out.println("I.size() is "+I.size());

        G.remove(1);
        H.remove(2);

        System.out.println("G.size() is "+G.size());
        System.out.println("H.size() is "+H.size());
        System.out.println("H.get(1) is "+H.get(1));
        System.out.println("I: "+I);
        System.out.println();
        try{
           System.out.println(G.get(200));
        }catch(ListIndexOutOfBoundsException e){
           System.out.println("Caught Exception: ");
           System.out.println(e);
           System.out.println("Continuing without interuption");
        }
        System.out.println();
        System.out.println("G.get(2) is "+G.get(2));
        System.out.println("I.get(1) is "+I.get(1));
      
        System.out.println("\n--------------------- Double Test ---------------------");         
        List<Double> D = new List<Double>();
        List<Double> E = new List<Double>();
        List<List<Double>> F = new List<List<Double>>();
        int m,n,p; 
        D.add(1, 12.2);
        D.add(2, 24.4);
        D.add(3, 48.4);
        E.add(1, 88.1);
        E.add(2, 120.13);
        E.add(3, 300.222);
        F.add(1, D);
        F.add(2, E);
      
        System.out.println("D: "+D);
        System.out.println("E: "+E);
        System.out.println("F: "+F);
        System.out.println("D.equals(D) is "+D.equals(D));
        System.out.println("D.equals(E) is "+D.equals(E));
        System.out.println("D.equals(F) is "+D.equals(F)); 
        System.out.println("D.size() is "+D.size());
        System.out.println("E.size() is "+E.size());
        System.out.println("F.size() is "+F.size());

        D.remove(1);
        E.remove(2);

        System.out.println("D.size() is "+D.size());
        System.out.println("E.size() is "+E.size());
        System.out.println("E.get(1) is "+E.get(1));
        System.out.println("F: "+F);
        System.out.println();
        try{
           System.out.println(D.get(200));
        }catch(ListIndexOutOfBoundsException e){
           System.out.println("Caught Exception: ");
           System.out.println(e);
           System.out.println("Continuing without interuption");
        }
        System.out.println();
        System.out.println("D.get(2) is "+D.get(2));
        System.out.println("F.get(1) is "+F.get(1));      
      

       
      
      
      
      System.out.println("\n--------------------- String Test ---------------------");         
      List<String> A = new List<String>();
      List<String> B = new List<String>();
      List<List<String>> C = new List<List<String>>();
      int i, j, k;

      A.add(1, "one");
      A.add(2, "two");
      A.add(3, "three");
      B.add(1, "ten");
      B.add(2, "twenty");
      B.add(3, "thirty");
      C.add(1, A);
      C.add(2, B);

      System.out.println("A: "+A);
      System.out.println("B: "+B);
      System.out.println("C: "+C);
      System.out.println("A.equals(A) is "+A.equals(A));
      System.out.println("A.equals(B) is "+A.equals(B));
      System.out.println("A.equals(C) is "+A.equals(C));
      System.out.println("A.size() is "+A.size());
      System.out.println("B.size() is "+B.size());
      System.out.println("C.size() is "+C.size());

      A.remove(1);
      B.remove(2);

      System.out.println("A.size() is "+A.size());
      System.out.println("B.size() is "+B.size());
      System.out.println("B.get(1) is "+B.get(1));
      System.out.println("C: "+C);
      System.out.println();
      try{
         System.out.println(A.get(200));
      }catch(ListIndexOutOfBoundsException e){
         System.out.println("Caught Exception: ");
         System.out.println(e);
         System.out.println("Continuing without interuption");
      }
      System.out.println();
      System.out.println("A.get(2) is "+A.get(2));
      System.out.println("C.get(1) is "+C.get(1));
   }
  
   
}

