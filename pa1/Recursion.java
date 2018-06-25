//-------------------------------------------------------------------
// Program:      Recursion.java
// Author:       James Michael Vrionis
// Date:         10-6-16
// 
//-------------------------------------------------------------------

class Recursion {
//-------------------------------------------------------------------
    //ReverseArray1()
    // Pre: -1 2 6 12 9 2 -5 -2 8 5 7
    // Post: 7 5 8 -2 -5 2 9 12 6 2 -1  
    static void reverseArray1(int [] X, int n, int [] Y) 
    {
        if( n == 0) return; 
        else if(n > 0) 
        {
            Y[n-1] = X[X.length-n];
            reverseArray1(X,n-1,Y);
        }
    }

//-------------------------------------------------------------------
    // ReverseArray2()
    // Pre: -1 2 6 12 9 2 -5 -2 8 5 7
    // Post: 7 5 8 -2 -5 2 9 12 6 2 -1  
    static void reverseArray2(int [] X, int n, int [] Y) 
    {
        if(n == 0) return;
        else if( n > 0 ) 
        { 
            reverseArray2(X, n-1, Y);
            Y[n-1] = X[X.length-n];
        }
    }

//-------------------------------------------------------------------
    // ReverseArray3()
    // Reverses the subarray X[i,...,j] consisting from 
    // index i to index j 
    // 1) swap i and j, then recursively call on the 
    // subarray X[i+1,...,j-1]
    // index i+1 to index j-1. Call reverseArray3() with 
    // i= 0 and j = X.length-1
    // Pre: -1 2 6 12 9 2 -5 -2 8 5 7
    // Post: 7 5 8 -2 -5 2 9 12 6 2 -1  
    static void reverseArray3(int [] X, int i, int j)
    {
        if(i>=j) return;
        else {
            int temp = X[j];
            X[j] = X[i];
            X[i] = temp;
            reverseArray3(X,i+1,j-1);    
        }
    }

//-------------------------------------------------------------------
    // maxArrayIndex()
    // Pre:  -1 2 6 12 9 2 -5 -2 8 5 7
    // Index: 0 1 2 3  4 5  6  7 8 9 10
    // Post: Index of max = 3.
    static int maxArrayIndex(int [] X, int p, int r) {
        int midIndice = 0;
        if(p < r )  // Base Case
        {
            midIndice = (p+r)/2; 
            int Left = maxArrayIndex(X,p,midIndice);
            int Right = maxArrayIndex(X,midIndice+1,r);
            if (X[Left] > X[Right]) midIndice = Left;
            else if (X[Right] > X[Left]) midIndice = Right; 
        }
        return midIndice;
    }

//-------------------------------------------------------------------

    // minArrayIndex()
    // Pre:  -1 2 6 12 9 2 -5 -2 8 5 7
    // Index: 0 1 2 3  4 5  6  7 8 9 10
    // Post: Index of min = 6.
    static int minArrayIndex(int [] X, int p, int r) {
        int midIndice = 0;
        if(p < r )  //Base Case
        {
            midIndice = (p+r)/2; 
            int Left = minArrayIndex(X,p,midIndice);
            int Right = minArrayIndex(X,p+1,r);
            if (X[Left] < X[Right]) midIndice = Left;
            else if (X[Right] < X[Left]) midIndice = Right; 
        }
        return midIndice;
    }
//-------------------------------------------------------------------

    // main() with code provided from assignment
    public static void main(String[] args) 
    {
        // Populate 3 Arrays all of length A
        int [] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
        int [] B = new int[A.length];
        int [] C = new int[A.length];

        // Calculate the max and min then return the index number
        int minIndex = minArrayIndex(A, 0, A.length-1);
        int maxIndex = maxArrayIndex(A, 0, A.length-1);
        
        // Print out Array A
        // Output:  -1 2 6 12 9 2 -5 -2 8 5 7
        for(int x: A) System.out.print ( x + " ");
        System.out.println();
        System.out.println();       

        // Print out minIndex = 6:
        System.out.println( "minIndex = " + minIndex );
        System.out.println();

        // Print out maxIndex = 3 
        System.out.println( "maxIndex = " + maxIndex );
        System.out.println();

        //ReverseArray1()
        // Pre: -1 2 6 12 9 2 -5 -2 8 5 7
        // Post: 7 5 8 -2 -5 2 9 12 6 2 -1  
        reverseArray1(A, A.length, B);
        for(int x: B) System.out.print (x + " ");
        System.out.println();

        //ReverseArray2()
        // Pre: -1 2 6 12 9 2 -5 -2 8 5 7
        // Post: 7 5 8 -2 -5 2 9 12 6 2 -1  
        reverseArray2(A, A.length, C);
        for(int x: C) System.out.print (x + " ");
        System.out.println();
        
        //ReverseArray3()
        // Pre: -1 2 6 12 9 2 -5 -2 8 5 7
        // Post: 7 5 8 -2 -5 2 9 12 6 2 -1  
        reverseArray3(A, 0, A.length-1);
        for(int x: A) System.out.print(x + " ");
        System.out.println(); 

    } 
}
//-------------------------------------------------------------------


/*
The output of the program will be the following six lines:
-1 2 6 12  9 2 -5 -2 8 5 7 
minIndex = 6
maxIndex = 3
7 5 8 -2 -5 2 9 12 6 2 -1
7 5 8 -2 -5 2 9 12 6 2 -1
7 5 8 -2 -5 2 9 12 6 2 -1
*/


