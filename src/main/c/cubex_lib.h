#ifndef _CUBEX_LIB
#define _CUBEX_LIB

typedef struct {
	//vTable
	int refCount;
	int numFields;
} Object;

Object newObject(struct type, void *params);
void gc(Object target);

#endif
