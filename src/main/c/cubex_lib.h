#ifndef _CUBEX_LIB
#define _CUBEX_LIB

#define true 1
#define false 0
#define NULL 0

typedef char bool;

typedef struct {
	void **vTable;
	unsigned int refCount;
	int numFields;
} object_t;

/* Built in object sections */

typedef struct {
	void **vTable;
	unsigned int refCount;
	int numFields;
	signed int value;
} integer_t;

typedef struct {
	void **vTable;
	unsigned int refCount;
	int numFields;
	bool value;
} boolean_t;

typedef struct {
	void **vTable;
	unsigned int refCount;
	int numFields;
	char value;
} character_t;

/* ITERABLE SECTION*/

typedef enum {RANGE, INFINITE, OBJECT, INPUT, STRING} iterableValue_t;

typedef struct {
	void **vTable;
	unsigned int refCount;
	int numFields;
	unsigned int numEntries;
	Entry_t **entries;
} iterable_t;

typedef struct {
	iterableValue_t type;
} iterableEntry_t;

typedef struct {
	iterableValue_t type;
	object_t *obj;
} objectIterableEntry_t;

typedef struct {
	iterableValue_t type;
	int start;
	int end;
} rangeIterableEntry_t

typedef struct {
	iterableValue_t type;
	int start;
} infiniteIterableEntry_t

typedef struct {
	iterableValue_t type;
} inputIterableEntry_t;

typedef struct {
	iterableValue_t type;
	unsigned int length;
	char *string;
} stringIterableEntry_t;


typedef struct {
	unsigned int index;
	unsigned int innerIndex;
} iterableIndex_t;







//Allocates the neccessary memory
object_t * createObject(int type);
void gc(object_t *target);

iterableIndex_t * createIndexer();
bool iterableHasNext(object_t *obj, iterableIndex_t *indexer);
object_t * iterableNext(object_t * iter, iterableIndex_t *indexer);
object_t * iterableAppend(object_t *, object_t *);

/*These functions create and populate the built-in types
 * Strings are just general Iterables*/
object_t * createInteger(int val, unsigned int startingRefs);
object_t * createBoolean(bool val, unsigned int startingRefs);
object_t * createCharacter(char val, unsigned int startingRefs);

object_t * createIterable_value(object_t *value, unsigned int startingRefs); //maybe extend this to do more then one
object_t * createIterable_finiteInt(int first, int last, unsigned int startingRefs);
object_t * createIterable_infiniteInt(int first, unsigned int startingRefs);

#endif
