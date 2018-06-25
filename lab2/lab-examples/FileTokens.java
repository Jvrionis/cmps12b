//----------------------------------------------------------------------
//
//      FileTokens.java
// 
//      A toke is a maximal substring containing no whitespace characters
//      with whitespace defined to be spaces, newlines, and tab
//      characters. Java String class contains a method called split() 
//      which decomposes a string into tokens, then returns a String 
//      array containing the tokens as its elements.
// 
//      
//----------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

class FileTokens {
    public static void main(String [] args) throws IOException {

        // This is here to count the lines of the text file
        int lineNumber = 0;

        // Check number of command line arguments is at least 2
        if(args.length < 2 ) {
            System.out.println( "Usage: FileCopy <input file> <output file>");
            System.exit(1);
        }

        // Open files
        // Scanner Constructor takes a File object and initializes it
        Scanner in = new Scanner(new File(args[0]));

        // PrintWriter Constructor makes a FilWriter object and initializes it
        PrintWriter out = new PrintWriter(new FileWriter(args[1]));

        // Read lines from in, extract and print tokens from each line
        // An invocation of this method of the form hasNext(pattern) behaves
        // in exactly the same way as the invocation hasNext(Pattern.compile(pattern)).
        // Returns true if this scanner has another token in its input. This method may
        // block while waiting for input to scan. The scanner does not advance past any
        // input.
       
        while( in.hasNextLine() ) {
           // after all tokens from the line have been read in, increment 
           // lineNumber by one. 
            lineNumber++;
            
            // Trim leading and trailing spaces, then add one trailing space
            // so split works on blank lines 
            
            String line = in.nextLine().trim() + " ";

            // Split line around white space
            // this holds the tokens..
            String[] token = line.split("\\s+");

            // print out tokens
            int n = token.length;
            out.println("Line " + lineNumber + " contains " + n +"  tokens:");
            
            // Keep printing tokens until there aren't any left, basically until
            // the end of the file, then exit the loop because i == n. 

            for(int i=0; i <n; i++) {
                out.println( " " + token[i]);

            }

        }
        // Close Files
        in.close();
        out.close();
    }
}










    





