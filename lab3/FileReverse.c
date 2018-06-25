/*
 *  FileReverse.c
 *  It will take two command line arguments naming 
 *  the input and output files respectively
 *  Read each word in the input file, then print it 
 *  backwards on a line by itself.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* String Reverse function */
void stringReverse(char* s) {
    /* from i to j=strlen(s)-1 because '\0' */
    int i = 0, len = strlen(s)-1;
    char end;
    
    /* start at the end of file then work backwards */
    for ( i = 0; i < len ; i ++, len--) {
        end = s[i]; 
        s[i] = s[len];
        s[len] = end;
    }
}

int main(int argc, char* argv[] ) {
    
    FILE* in; /* File handle for input */
    FILE* out; /* File handle for output */
    char word[256]; /* char array to store words from previous input file */

    if( argc != 3) {
        printf("Usuage: %s <input file> <output file> \n", argv[0]);
        exit(EXIT_FAILURE);
    }

    /* open input file for reading  */
    in = fopen(argv[1],"r");
    if( in == NULL) {
        printf("Unable to read from file %s\n", argv[1]);
        exit(EXIT_FAILURE);
    }

    /* open output file for writing */
    out = fopen(argv[2], "w");
    if( out == NULL ) {
        printf("Unable to write to file %s\n", argv[2]);

    }
    
    /* Read words from input file, call stringReverse, 
       then print on separate lines to the output file  */
    while( fscanf( in, "%s", word) != EOF ) {
        stringReverse(word);
        fprintf(out, "%s\n", word);
    }

    fclose(in); /* close input file */
    fclose(out); /* close output file */
    return(EXIT_SUCCESS);
}

