// 
//		Dictionary.c
// 		James Vrionis
// 		A Dictionary ADT based on the linked list data structure. Elements of the 
//      Dictionary will be pairs of Strings called key and value. (key, value): 
//      keys are distinct while values may be repeated. Implement the seven 
//      given ADT operations given from pa3 into c.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "Dictionary.h"

/* NodeObj */
typedef struct NodeObj {
	char* key;
	char* value;
	struct NodeObj* next;
} NodeObj;

/* NODE */
typedef NodeObj* Node;

/* newNode(): 
 * Constructor for Node type 
 */
Node newNode(char* k, char* v) {
	Node N = malloc(sizeof(NodeObj));
	assert(N != NULL);
	N->key = k;
	N->value = v;
	N->next = NULL;
	return(N);
} 

/* freeNode(): 
 * Destructor for Node type 
 */
void freeNode(Node* pN) {
	if(pN != NULL && *pN != NULL) {
		free(*pN);
		*pN = NULL;
	}
}

/* DictionaryObj
 * 
 */
typedef struct DictionaryObj {
	Node head;
	Node tail;
	int numItems;
} DictionaryObj;

/* newDictionary(): 
 * Dictionary Type Constructor  
 */
Dictionary newDictionary(void) {
 	Dictionary D = malloc(sizeof(DictionaryObj));
 	assert(D != NULL);
 	D->head = NULL;
 	D->tail = NULL;
 	D->numItems = 0;
 	return(D);	
}
 
 /* freeDictionary()
  * Dictionary Type Destructor
  */
void freeDictionary(Dictionary* pD) {
	if(pD != NULL && *pD != NULL) {
		if( !isEmpty(*pD))makeEmpty(*pD);
			free(*pD);
		*pD = NULL;	
	} 		
}

 /* isEmpty()
  * pre: none
  * post: return (true) 1 if S is empty, if not (false) 0 
  */
int isEmpty(Dictionary D) {
 	if(D == NULL) {
 		fprintf(stderr, "isEmpty Error: calling isEmpty on NULL Dictionary reference\n ");
 		exit(EXIT_FAILURE);
 	}
 	if(D->numItems > 0) {
 		return 0; // False
 	}
 	return 1; // True	
 }
 
 /* size()
  * pre: none
  * post: returns number of (key,value) pairs in D 
  */
int size(Dictionary D) {
	return D->numItems; 
 }

 /* lookup()
  * pre: none
  * post: Returns value v such that (k,v) in in D, or NULL if it's not there
  */
char* lookup(Dictionary D, char* k) {
	Node N = D->head;
	if(D == NULL) {
		fprintf(stderr, "Lookup Error: calling lookup on NULL Dictionary\n");
		exit(EXIT_FAILURE);
	}
	while(N != NULL) {
		if(strcmp(N->key,k)==0) return(N->value);
		N = N->next;
	}
		return(NULL);
}

 /* insert()
  * pre: none
  * post: Returns value v such that (k,v) in in D, or NULL if it's not there
  */
void insert(Dictionary D, char* k, char* v) {
	if(D-> numItems == 0) {
		D->head = D->tail = newNode(k,v);
	}else {
		Node N;
		N = newNode(k,v);
		D->tail->next = N;
		D->tail = N;
	}
	D->numItems++;
}	

 /* makeEmpty()
  * pre: none
  * post: Resets D to the Empty State.
  */
void makeEmpty(Dictionary D) {
	D->head = NULL;
	D->tail = NULL;
	D->numItems= 0; 
}
  
 /* delete()
  * pre: lookup(D,k) != NULL 
  * post: Deletes a (key,value) pair 
  */
void delete(Dictionary D, char* k) {
// Make an indirect pointer that points to *Address* of the thing well update
//	indirect = &head;
// walk the list, looking for the thing that thing that points to the entry we want to remove
// while ( (*indirect) != &key)
//    indirect = &(*indirect)->next;
// ... and just remove it.
// *indirect = entry -> next; 

	Node N = D->head;
	if(lookup(D,k) == NULL) {
		fprintf(stderr, "Delete Error: Key not found!\n");		
		exit(EXIT_FAILURE);
	}	
	if(strcmp(N->key,k)==0) {
		Node P = D->head;
		Node G = P->next;
		D->head = G;
		freeNode(&P);
	}
	else {
		while(N != NULL && N->next != NULL) {
			if(strcmp(N->next->key, k)== 0) {
				Node G = N;
				Node C = N->next;
				G->next = C->next;
				N=G;
				freeNode(&C);
			}	
		N = N->next;
		}
	}
	D->numItems--;
}

 /* printDictionary()
  * pre: none
  * post: prints text of D to file pointed to by out
  */
void printDictionary(FILE* out, Dictionary D) {
	Node N;
	if(D == NULL) {
		fprintf(stderr, "PrintDictionary Error: calling print function on NULL reference\n");
		exit(EXIT_FAILURE);	
	} 	
	for(N=D->head; N !=NULL; N=N->next) {
		fprintf(out, " %s %s\n", N->key, N->value);	
	}
 	fprintf(out,"\n");
 }
