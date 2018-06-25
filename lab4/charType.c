/*         
 *   James Vrionis
 *   charType.c
 *   Reads input from file containing characters of four types:
 *	  alphabetic characters(upper & lower case), numeric 
 *   characters(#s 0-9), punctuation, and whitespace 
 *   (tab and newline).
 *			
 *		output types into these four types previous stated 
 *    returning a singular or plural 'string' when neccessary.		  
 */

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <assert.h>
#include <string.h>

#define MAX_STRING_LENGTH 100

// function prototype 
void extract_chars(char* s, char* a, char* d, char* p, char* w);

// function main which takes command line arguments 
int main(int argc, char* argv[]){
   
   FILE* in;        // handle for input file                  
   FILE* out;       // handle for output file                 
   char* line;      // string holding input line              
   char* alphabetical; // string holding all alphabetical chars 
   char* digit; 	// string holding all digit chars 
   char* punctuation; // string holding all punctuational chars 
   char* whitespace; // string holding all white space chars 
   
   // check command line for the  correct number of arguments 
   if( argc != 3 ) {
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   // open input file for reading 
   if( (in=fopen(argv[1], "r"))== NULL ) {
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   // open output file for writing 
   if( (out=fopen(argv[2], "w"))== NULL ) {
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   // allocate strings line and alphabetical on the heap 
   line = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   alphabetical = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   digit = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   punctuation = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   whitespace = calloc(MAX_STRING_LENGTH+1, sizeof(char));
   assert(line != NULL && alphabetical != NULL && digit != NULL && punctuation != NULL && whitespace != NULL);
  

   // read each line in input file, extract alpha-numeric characters
   int A=0, D=0, P=0, W=0, m=1;
   //malloc(sizeof(int));
   while( fgets(line, MAX_STRING_LENGTH, in) != NULL ) {
      extract_chars(line, alphabetical, digit, punctuation, whitespace);
	  A=0; D=0; P=0; W=0;
	  while(alphabetical[A] != '\0') { 
	      A++;
	  }
	  while(digit[D] != '\0') { 
	      D++;
	  }
	  while(punctuation[P] != '\0') { 
	      P++;
	  }
	  while(whitespace[W] != '\0') { 
	      W++;
	  }
	  
	  // fprintf(out, "line %d contains:\n", m);
	  // fprintf(out, "%d alphabetic characters: %s\n", A, alphabetical );
	  // fprintf(out, "%d numeric characters: %s\n", D, digit );
	  // fprintf(out, "%d punctuation characters: %s\n", P, punctuation );
	  // fprintf(out, "%d whitespace characters: %s\n", W, whitespace );
	  //m++;       
      
      fprintf(out, "line %d contains:\n", m); 
      if ( A > 1) { fprintf(out, "%d alphabetic characters: %s\n", A, alphabetical ); }
      else { fprintf(out, "%d alphabetic character: %s\n", A, alphabetical ); }

      if (D > 1) { fprintf(out, "%d numeric characters: %s\n", D, digit );}
      else { fprintf(out, "%d numeric character: %s\n", D, digit );}

      if(P > 1) {fprintf(out, "%d punctuation characters: %s\n", P, punctuation );}
      else {fprintf(out, "%d punctuation character: %s\n", P, punctuation );}

      if(W > 1) { fprintf(out, "%d whitespace characters: %s\n", W, whitespace );}
      else { fprintf(out, "%d whitespace character: %s\n", W, whitespace );}
      
      m++;
   
   }

   // free heap memory
   //free(m) and set stack pointers to NULL
   free(line);
   line = NULL;
   
   free(alphabetical);
   alphabetical = NULL;
   
   free(digit);
   digit = NULL;
   
   free(punctuation);
   punctuation = NULL;
   
   free(whitespace);
   whitespace = NULL;

   // close input and output files 
   fclose(in);
   fclose(out);

   return EXIT_SUCCESS;
}

// function definition 
void extract_chars(char* s, char* a, char* d, char* p, char* w) {
   int i=0, j=0, k=0, l=0, n=0;
   while(s[i]!='\0' && i<MAX_STRING_LENGTH) {
      if( isalpha( (int)s[i]) ) { 
          a[j++] = s[i];
      }
	  else if( isdigit( (int)s[i]) ) { 
	      d[k++] = s[i];
	  }
	  else if( ispunct( (int)s[i]) ) { 
	      p[l++] = s[i];
	  }
	  else if( isspace( (int)s[i]) ) { 
	  	  w[n++] = s[i];
	  }
	  else {	
	  }
      i++;
   }
   a[j] = '\0';
   d[k] = '\0';
   p[l] = '\0';
   w[n] = '\0';
}
