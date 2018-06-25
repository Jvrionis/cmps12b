//--------------------------------------------------------------------
// Assignment:  PA 2
// Author:      James Vrionis  
// Program:     Search.java
// Purpose:     This program takes in more than two command line 
//              arguments, then searches file1 for the word and 
//              the line number. Since this program uses  
//              BinarySearch to find the target word(s), if the 
//              original line number of the word is to be found 
//              MergeSort must be called first. sort the word array 
//              using merge sort then search it using binary search. 
//              Modified Merge Sort and merge to pass an array for 
//              word and and line number. 
//              Search <file name> [target1 tartget2 ...]
//
//--------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;


//--------------------------------------------------------------------


class Search {
//--------------------------------------------------------------------  
// mergeSort()
// sorts subarrays 

    public static void 
    mergeSort(String[] word, int[] lineNumber, int first, int last) {
        int mid;
        
        // if first is less than last, then it would be of length 0, 
        // do nothing
        if( first < last ) {  
            mid = (first + last)/2;  
            mergeSort(word,lineNumber,first,mid); 
            mergeSort(word, lineNumber, mid+1, last);
            merge(word, lineNumber, first, mid, last);
        }
    }

//--------------------------------------------------------------------
// merge()
// sRight stands for String Right and sLeft for String Left.
// merge sorted subarrays sRight[first...mid] and sLeftg[mid+1...last]    
// keeps track of line number numL[first...mid] numR[mid+1...last]

    public static void 
    merge(String[] word, int[] lineNumber, int first, int mid, 
                                                        int last) {
        int i, j, k; 
        
        // n1 and n2 repersent lengths of the 
        // sub arrays X[first...mid] and X[mid+1...last]
        int n1 = mid - first + 1;
        int n2 = last - mid; 
   
        // Two subarrays to keep track of words
        String[] sLeft = new String[n1];
        String[] sRight = new String[n2];
        
        // Two subarrays to keep track of lineNumber
        int[] numL = new int[n1];
        int[] numR = new int[n2];
                 
        // sort 
        for( i=0; i < n1; i++ ) {
            sLeft[i] = word[first+i];
            numL[i] = lineNumber[first+i];
        }
        for( j=0; j<n2; j++ ) { 
            sRight[j] = word[mid+j+1];
            numR[j] = lineNumber[mid+j+1];
        }
        
        i = 0; j = 0;
        for( k = first; k <= last; k++ ) {
            if( i < n1 && j < n2 ) {
                //if(sleft[i] < sRight[j] {
                if(sLeft[i].compareTo(sRight[j]) > 0) {
                    word[k] = sLeft[i];
                    lineNumber[k] = numL[i];
                    i++;                    
                }
                else {
                    word[k] = sRight[j];
                    lineNumber[k] = numR[j];
                    j++;
                }
            }
            else if( i<n1 ) {
                word[k] = sLeft[i];
                lineNumber[k] = numL[i];
                i++;
            }
            else{ // j<n2
                word[k] = sRight[j];
                lineNumber[k] = numR[j];
                j++;
            }
        }
    }

//--------------------------------------------------------------------
// binarySearch ()
// modified: to take a String []

    public static String 
    binarySearch(String[] word, int[] lineNumber, int first, 
                                            int last, String target) {
        int mid;

        if ( first > last) {
            return target + " not found!"; 
        }
        else {
            // Find the middle of array.
            // deal with case i only have one element in my subarrray. 

            mid = (first + last) / 2;
            // uncomment line below to watch mid output
            // System.out.println(
            // " mid is = " + mid  + ' ' + target + ' ' + word[mid]);
            
            if( word[mid].compareTo(target) == 0 ) {
                return target + " found on line " + lineNumber[mid];
                
            }
            else if( word[mid].compareTo(target) < 0 ) {
                    return binarySearch(
                        word, lineNumber, first, mid, target);
             }
            else { 
                return binarySearch(
                    word, lineNumber, mid+1, last, target);
            }
          }
    
    }

//--------------------------------------------------------------------
// main() 
// IOException: Signals an I/O exception of some sort has occurred 
// (failed or interrupted I/O operations) read the input file, read 
// the target file, sort items in file1, sort items while keeping 
// track of original index, search the sorted items, then return 
// line number and word. 

    public static void 
    main(String[] args) throws IOException {
        
        // Check the number of command line arguments. 
        // must be at least 2 If args.length is less than 2 print 
        // error message to console
        if(args.length < 2) {
            System.err.println(
                "Usage: Search file target1 [target2 ..] ");
            System.exit(1);
        }
        
        int i, n = 0;
        String line = null;
        Scanner in = new Scanner(new File(args[0]));
        
        // Read lines from in, extract and print tokens from 
        // each line System.out.println(line) can be used to 
        // see the list before sort. 
        while(in.hasNextLine()) {
            // System.out.println(line);            
            line = in.nextLine();
            n++;
        }  
        
        in = new Scanner(new File(args[0]));
         
        String[] X = new String[n];
        int[] lineNumber = new int[n];
        
        for(i =1; i <= lineNumber.length; i++) {
            lineNumber[i-1] = i;
        }
        
        for(i = 0; in.hasNextLine(); i ++ ) {
            line = in.nextLine();
            X[i] = line;
        }
        
        
        mergeSort(X, lineNumber,0,X.length-1);
        /*
        // used this to to print out Array after array

        System.out.println();
        System.out.println(" <- List after MergeSort -> ");
        mergeSort(X, lineNumber,0,X.length-1);
        
        System.out.println();
        System.out.println();
        for(i = 0; i < n; i++)
        {
            System.out.println(X[i]);
        }
        System.out.println();
        */
        for(i = 1; i < args.length; i++) {
            System.out.println(
                binarySearch(X,lineNumber, 0, X.length-1, args[i]));
        }

        in.close();
    }

}
                

/*                    
    OUTPUT:
    # java Search file1 entire beginning possibly specified key value initial before dictionary however
    entire found on line 1
    beginning found on line 2
    possibly found on line 3
    specified found on line 4
    key found on line 5
    value found on line 6
    initial found on line 7
    before found on line 8
    dictionary found on line 9
    however found on line 10

*/
                    
