//------------------------------------------------------------------------------------
// Pa5
// Program: Dictionary.c
// Author:  James Michael Vrionis
// ID    :  JVrionis
// Date  :  11-30-16
// 
//------------------------------------------------------------------------------------

#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "Dictionary.h"

const int tableSize = 101; // must be a prime number

// Node Object
typedef struct NodeObj {
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode
// Node Type Constructor 
Node newNode(char* k, char* v) {
   Node N = malloc(sizeof(NodeObj)); // Node - NodeObj
   assert(N!=NULL);
   N->key = k;
   N->value = v;
   N->next = NULL;
   return(N);
}

// freeNode
// Free Node memory
void freeNode(Node* pN) {
   if (pN != NULL && *pN != NULL) {
      free(*pN);
      *pN = NULL;
   }
}

// DictionaryObj
// Create data type
typedef struct DictionaryObj {
   Node *table;
   int numItems;
} DictionaryObj;

// Dictionary
typedef struct DictionaryObj* Dictionary;

// Dictionary Type Constructor
Dictionary newDictionary() {
   Dictionary D = malloc(sizeof(DictionaryObj)); // Dictionary - DictionaryObj
   assert(D!=NULL);
   D->table = calloc(tableSize,sizeof(Node));
   assert(D->table !=NULL);
   D->numItems = 0;
   return D;
}

// Free Dictionarymemory
// Dictionary Type destructor
void freeDictionary(Dictionary* pD) {
   if (pD != NULL && *pD != NULL) {
      if (!isEmpty(*pD) ) makeEmpty(*pD);
      //makeEmpty(*pD);
      free((*pD)->table);
      free(*pD);
      *pD = NULL;
   }
}

// isEmpty()
// pre: none
// post: return 1 if S is empty, 0 otherwise
int isEmpty(Dictionary D) {
   if( D==NULL) {
      fprintf(stderr, "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if(D->numItems>0) {
      return 0;
   }
   return 1;
}

// rotate_left()
// bits rotated in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
   int sizeInBits = 8*sizeof(unsigned int);
   shift = shift & (sizeInBits - 1); //bitwise & 
   if ( shift == 0 ) {
      return value;
   }
   else {
      return (value << shift) | (value >> (sizeInBits - shift));
   }
}  

// pre_hash()
// Turn string into an unsigned int
unsigned int pre_hash(char* input) {
   unsigned int result = 0xBAE86554;
   while (*input) {
      result ^= *input++;
      result = rotate_left(result, 5);
   }  
   return result;
}

// hash()
// changes a string into an int within the range 0-tableSize-1
int hash(char* key) {
   return pre_hash(key)%tableSize;
}

// makeEmpty()
// pre: none
// post: Resets D to the empty state
void makeEmpty(Dictionary D) {
   Node N;
   if(D==NULL)
   {
      fprintf(stderr,"Dictionary Error: calling makeEmpty() on NULL Dictionary Reference");
      exit(EXIT_FAILURE);
   }
   for(int i = 0; i<tableSize; i++) {
      while(D->table[i] != NULL){
         N = D-> table[i];
         D->table[i]=N->next;
         freeNode(&N);
         N = NULL;
      }
   }
   D->numItems = 0; 
}

// size()
// pre: none
// post: returns # of (key,value) pairs in D
int size(Dictionary D) {
   return D->numItems;
}

// findKey()
// post: returns reference to Node with key in this dictionary
Node findKey(Dictionary D, char* k) {
   Node N;
   if( D==NULL ) {
      fprintf(stderr, "Dictionary Error: calling findKey() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   N = D->table[hash(k)];
   while(N != NULL) {
      if(strcmp(N->key,k)== 0)
         break;
      N = N->next;
   }
   return N;
}

// lookup()
// pre: none
// post: returns value v s.t. (k,v) is in D, otherwise NULL if no such value exists
char* lookup(Dictionary D, char* k) {
   Node N;
   if( D == NULL ) {
      fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary\n");
      exit(EXIT_FAILURE);
   }
   N = findKey(D,k); // using findKey helper function
   return (N == NULL ? NULL : N ->value);

   /*
   if(D->numItems == 0) {
      fprintf(stderr, "Dictionary Error: calling lookup() on empty Dictionary reference");
      exit(EXIT_FAILURE);
   }
   if(findKey(D,k) == NULL)
      return NULL;
   else
      return findKey(D,k)->value;*/
}

// insert()
// pre: lookup(D,k) != NULL
// post: inserts new(k,v) pair into D at head
void insert(Dictionary D, char* k, char* v) {
   Node N, F;
   F = findKey(D,k);
   //int h = hash(k);
   if(D == NULL) {
      fprintf(stderr, "Dictionary Error: calling insert on NULL Dictionary\n");
      exit(EXIT_FAILURE);
   }
   if(F != NULL) {
      fprintf(stderr, "Dictionary Error: calling insert() on a pre-existing key");
      exit(EXIT_FAILURE);
   } 
   
   int h = hash(k);
   if(D->table[h] == NULL) {
      D->table[h]  = newNode(k, v);
      D->numItems++; 
   }else {
      N = newNode(k, v);
      N->next = D->table[h];
      D->table[h] = N;
      D->numItems++;
   }
}

// delete()
// pre: lookup(D,k) != NULL
// post: deletes pair with the key k
void delete(Dictionary D, char* k) {
   Node N,A;
   N = findKey(D,k);
   
   if( D == NULL ) {
      fprintf(stderr, "Dictionary Error: Calling delete() on NULL Dictionary Reference\n");
      exit(EXIT_FAILURE);
   }
   if (D->numItems == 0 ) {
      fprintf(stderr, "Dictionary Error: cannot delete() empty Dictionary\n");
      exit(EXIT_FAILURE);
   }
   if(N == NULL) {
   //if(findKey(D,k) == NULL) {
      fprintf(stderr, "Dictionary Error: cannot delete() non-existent key \n");
      exit(EXIT_FAILURE);
   }
      
   // Deletes head with only one value in table slot
   if(N == D->table[hash(k)] && N->next == NULL) {
      freeNode(&N);
      D->table[hash(k)] = NULL;
   // Delete head > one value in table slot   
   }else if(N == D->table[hash(k)]) {      
      D->table[hash(k)] = N->next;
      N = NULL;
      freeNode(&N);
   }else {
      A = D->table[hash(k)];
      //walk list with A until we reach N and replace N with A.
      while(A->next != N) {
         A = A->next;
      }
      A->next = N->next;
      freeNode(&N);
   }
   D->numItems--;

}

// printDictionary()
// pre: none
// post: prints text representation of D to file out pointed to
void printDictionary(FILE* out, Dictionary D) {
   Node N;
   if( D==NULL ) {
      fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   // Search through 0 till tableSize
   for(int i = 0; i < tableSize; i++) {
      N = D->table[i];
   
      // while current node is not free
      while(N != NULL) {
         fprintf(out, "%s %s \n" , N->key, N->value);
         N = N->next;
      }
   } 
}
