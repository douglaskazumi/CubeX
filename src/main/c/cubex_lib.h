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

typedef enum {INTEGER_F, INTEGER_INF, OBJECT, INPUT} iterabletype_t

typedef struct {
	void **vTable;
	unsigned int refCount;
	int numFields;
	iterabletype_t type;
} iterable_t;

typedef struct {
	void **vTable;
	unsigned int refCount;
	int numFields;

	iterabletype_t type;

	unsigned int numEntries;
	object_t **array;
	unsigned int index;
} finiteGeneralIterable_t;

typedef struct {
	void **vTable;
	unsigned int refCount;
	int numFields;

	iterabletype_t type;

	signed int current;
	signed int last;

} finiteIntegerIterable_t;

typedef struct {
	void **vTable;
	unsigned int refCount;
	int numFields;

	iterabletype_t type;

	bool current;
	bool last;

} finiteBooleanIterable_t;

typedef struct {
	void **vTable;
	unsigned int refCount;
	int numFields;

	iterabletype_t type;

	int numPreEntries;
	object_t **array;
	signed int current;

} infiniteIntegerIterable_t;

//Allocates the neccessary memory
object_t * createObject(int type);
void gc(object_t *target);

bool iterableHasNext(object_t * iter);
object_t * iterableNext(object_t * iter);


/*These functions create and populate the built-in types
 * Strings are just general Iterables*/
object_t * createInteger(int val);
object_t * createBoolean(bool val);
object_t * createCharacter(char val);

object_t * createIterable_values(object_t **values, int numValues);
object_t * createIterable_finiteInt(int first, int last);
object_t * createIterable_infiniteInt(int first);

#endif
