#ifndef _CUBEX_LIB
#define _CUBEX_LIB

typedef struct {
	//vTable
	int refCount;
	int numFields;
} Object;

//vTable number of methods
//iTable list of offSets for each interface/class
Object newObject(struct type, void* params, int vTable, int* iTable);
void gc(Object target);

#endif
