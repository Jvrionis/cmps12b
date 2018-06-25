//------------------------------------------------------------------------------------
// Pa5
// Program: DictionaryTest.c
// Author:  James Michael Vrionis
// ID    :  JVrionis
// Date  :  11-30-16
// 
//------------------------------------------------------------------------------------

#include <stdio.h>
#include <stdlib.h>
#include "Dictionary.h"

int main(void) {
   Dictionary D = newDictionary();
   
   insert(D,50,12);
   insert(D,12,12);
   insert(D,34,2);
   insert(D,23,1);
   insert(D,68,15);
   insert(D,"last",16);

/*
   insert(D,112,16);
   insert(D,45,45);
   insert(D,58,23);
   insert(D,5,0);
   insert(D,99,13);
 */

   printDictionary(D,stdout);

   delete(D,50);
   delete(D,23);
   delete(D,"last");
   
   printDictionary(D,stdout);
   
   makeEmpty(D);
   freeDictionary(&D);
   
   //printDictionary(D,stdout);

}
