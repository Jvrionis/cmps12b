//--------------------------------------------------------------------
//    James Michael Vrionis           
//    Lab 2 
//    FileReverse.java
//    
//    This program takes two command line arguments and gives the 
//    names of the input and output files. It reads each line of 
//    input, then parses the tokens, the print each token backwards 
//    to the output file on a line by itself. File in will be called 
//    'in' and file out will be called 'out'
//
//--------------------------------------------------------------------




import java.io.*;
import java.util.Scanner;

class FileReverse {
    public static String stringReverse(String s, int n) {
        if ( s.length() == 1) return s;
        else return stringReverse(s.substring(1), n-1) + s.charAt(0);
    }

    public static void main(String [] args) throws IOException  {

        // we need a Scanner, PrintWriter, String line, 
        // String[] token, and a lineNumber
        Scanner in = null;
        PrintWriter out  = null;
        String line = null;
        String[] token = null;
        int n, lineNumber = 0;

        // Check to see if command line arguements are 
        // greater than 2
        if (args.length < 2) {
            System.out.println(
              "Usuage: FileCopy <input file> <output file> ");
            System.exit(1);
        }
    
       // Now lets open the file 'in'
       in  = new Scanner(new File(args[0]));
       out = new PrintWriter(new FileWriter(args[1]));
       
       // Read lines from <input file> then extract and print 
       // the tokens from each subsequent line

       while( in.hasNextLine() ) { 
           lineNumber++;
           // tream leading and trailing spaces, then add one 
           // trailing space so the split works on blank lines
           line = in.nextLine().trim() + " ";

           // Split line arround the white space
           token = line.split("\\s+");
           
           //now print out tokens
           n = token.length;
           for  (int i = 0; i < n; i++) {
               out.println(stringReverse(token[i], token[i].length()));
           }
       }
    
      // Close Files <input> <output>
      in.close();
      out.close();
    }
}        
   

