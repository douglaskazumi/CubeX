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
} object_t;

/* Built in object sections */

typedef struct {
	//vTable
	unsigned int refCount;
	int numFields;
	signed int value;
} integer_t;

typedef struct {
	//vTable
	unsigned int refCount;
	int numFields;
	bool value;
} boolean_t;

/* ITERABLE SECTION*/

typedef enum {INTEGER_F=2, INTEGER_INF=3, BOOLEAN_F=4, INTEGER_INF=5, OBJECT=6, INPUT=7} iterabletype_t

typedef struct {
	//vTable
	unsigned int refCount;
	int numFields;
	iterabletype_t type;
} iterable_t;

typedef struct {
	//vTable
	unsigned int refCount;
	int numFields;

	iterabletype_t type;

	unsigned int numEntries;
	Object **array;
	unsigned int index;
} finiteGeneralIterable_t;

typedef struct {
	//vTable
	unsigned int refCount;
	int numFields;

	iterabletype_t type;

	signed int current;
	signed int last;

} finiteIntegerIterable_t;

typedef struct {
	//vTable
	unsigned int refCount;
	int numFields;

	iterabletype_t type;

	bool current;
	bool last;

} finiteBooleanIterable_t;

typedef struct {
	//vTable
	unsigned int refCount;
	int numFields;

	iterabletype_t type;

	signed int current;

} infiniteIntegerIterable_t;

typedef struct {
	//vTable
	unsigned int refCount;
	int numFields;

	iterabletype_t type;

	bool current;

} infiniteBooleanIterable_t;


object_t * createObject(int type);
void gc(object_t *target);

bool iterableHasNext(object_t * iter);
object_t * iterableNext(object_t * iter);

integer_t * create_Integer(int val);
boolean_t * create_Boolean(bool val);


#endif
