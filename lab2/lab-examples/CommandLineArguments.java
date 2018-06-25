// CommandLineArguments.java
 
class CommandLineArguments {

    public static void main(String[] args) {
        
        // n is the amount of arguments. 
        // java <Program.java> <argument1> <argument2> <argument...> 
        // where n is the <argument(s)> 

        int n = args.length; 

        // Print out (args.length)
        // n arguments mean <argument1> <argument2> <..> <argumentn>
        // prints out the number of arguments n. 
        System.out.println("args.length = " + n);

        // keep printing the arguments 
        for(int i = 0; i<n; i++) System.out.println(args[i]);
            // System.out.println(args[i]); It prints the literal String
            // arguments that were typed into the command line.
    }
}



/* args cannot handle special characters such as !@#$%^&*() they will cause the program
 * to halt
 */













