#ifndef _CUBEX_LIB
#define _CUBEX_LIB

#define true 1
#define false 0
#define NULL 0

typedef char bool;

typedef struct {
	//vTable
	int refCount;
	int numFields;
} object;

/* ITERABLE SECTION*/

typedef enum {INTEGER, BOOLEAN, OBJECT} iterabletype_t

typedef struct {
	//vTable
	unsigned int refCount;
	int numFields;
	iterabletype_t type;
	bool isFinite;
} iterable_t;

typedef struct {
	//vTable
	unsigned int refCount;
	int numFields;

	iterabletype_t type;
	bool isFinite;

	unsigned int numEntries;
	Object **array;
	unsigned int index;
} finiteGeneralIterable_t;

typedef struct {
	//vTable
	unsigned int refCount;
	int numFields;

	iterabletype_t type;
	bool isFinite;

	signed int current;
	signed int last;

} finiteIntegerIterable_t;

typedef struct {
	//vTable
	unsigned int refCount;
	int numFields;

	iterabletype_t type;
	bool isFinite;

	bool current;
	bool last;

} finiteBooleanIterable_t;

typedef struct {
	//vTable
	unsigned int refCount;
	int numFields;

	iterabletype_t type;
	bool isFinite;

	signed int current;

} infiniteIntegerIterable_t;

typedef struct {
	//vTable
	unsigned int refCount;
	int numFields;

	iterabletype_t type;
	bool isFinite;

	bool current;

} infiniteBooleanIterable_t;


Object * newObject(struct type, void *params);
void gc(Object *target);
bool iterableHasNext(Object * iter);
Object * iterableNext(Object * iter);

#endif
