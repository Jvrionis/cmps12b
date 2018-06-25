//----------------------------------------------------------------------------
//      FileCopy.java
//     
//      The java.util package contains the Scanner Class, and the 
//      java.io package contains classes PrintWriter and FileWriter. 
//      They perform 'simple' input and 'simple' output operations. 
//      FileCopy.java: copies one file to another just like the unix command
//      cp
//
//------------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

class FileCopy {
    public static void main(String[] args) throws IOException {
        // Check the number of command line arguments
        // make sure they are at least 2
        if(args.length < 2) {
            System.out.println("Usuage: FileCope <input file> <output file>");
            System.exit(1);
        }
        
        // Open file(s) 
        // <Get a file> called <in> and set it equal to a new object
        // make an object that makes an object that has the first arugment and 
        // call it in.

        Scanner in = new Scanner(new File(args[0]));
        
        // Make a PrintWriter class called <out>
        // 1) PrintWriter constructor takes a FileWriter object for initialization
        // 2) Then it is in turn initialized by a String
        // 3) The String has the name of an output file, in this case called out 

        PrintWriter out = new PrintWriter(new FileWriter(args[1]));
        
        // Read lines from in, write lines to out
        // hasnextLine() is a boolean that returns true if there is another 
        // line in the input of this Scanner.
        // a String <line> takes the value of current line 
        // out.println( line ) : prints the value of current line into the 
        // file <out>

        while( in.hasNextLine() ) {
            String line = in.nextLine();
            out.println( line ); 
        }
        
        //close files
        // More specifically: it closes the Scanner.
        // close in Scanner and close out Scanner.
        in.close();
        out.close();
    }
}





/*  *    *    *    *    *    *    *    *    *    *    *    *   *    *    *   *   *
 * All this info can be obtained from-> http://docs.oracle.com/javase/7/docs/api/
 *
 *
 *     // For example, this code allows a user to read a number from System.in:
 *
 *       Scanner sc = new Scanner(System.in);
 *           int i = sc.nextInt();
 *
 *    // As another example, this code allows long types to be assigned 
 *       from entries in a file myNumbers:
 *                 
 *       Scanner sc = new Scanner(new File("myNumbers"));
 *       while (sc.hasNextLong()) {
 *          long aLong = sc.nextLong();
 *       }
 * *    *    *    *    *    *    *    *    *    *    *    *   *    *    *   *   *
 *
 *      // The scanner can also use delimiters other than whitespace. This example
 *      // reads several items in from a string:
 *
 *       String input = "1 fish 2 fish red fish blue fish";
 *       Scanner s = new Scanner(input).useDelimiter("\\s*fish\\s*");
 *       System.out.println(s.nextInt());
 *       System.out.println(s.nextInt());
 *       System.out.println(s.next());
 *       System.out.println(s.next());
 *       s.close(); 
 *
 *      // Output:
 *      1
 *      2
 *      Red
 *      Blue
 *
 *  *    *    *    *    *    *    *    *    *    *    *    *   *    *    *   *   */

