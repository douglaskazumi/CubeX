#ifndef _CUBEX_LIB
#define _CUBEX_LIB

extern int allocdiff;

#define true 1
#define false 0
#define NULL 0

typedef int* func;

typedef int bool;

typedef struct {
	void *iTable;
} vTable_t;

typedef struct {
	unsigned int numEntries;
} iTable_t;

typedef struct {
	unsigned int typeId;
	unsigned int functionIndex;
} iTableEntry_t;

typedef struct {
	void *vTable;
	int refCount;
	int numFields;
} object_t;

/* Built in object sections */

typedef struct {
	void *vTable;
	int refCount;
	int numFields;
	signed int value;
} integer_t;

typedef struct {
	void *vTable;
	int refCount;
	int numFields;
	bool value;
} boolean_t;

typedef struct {
	void *vTable;
	int refCount;
	int numFields;
	char value;
} character_t;

/* ITERABLE SECTION*/

typedef enum {RANGE, INFINITE, OBJECT, INPUT, STRING} iterableValue_t;


typedef struct {
	int magic;
	int refCount;
	iterableValue_t type;
} iterableEntry_t;

typedef struct {
	int objmagic;
	void *vTable;
	int refCount;
	int numFields;
	unsigned int numEntries;
	iterableEntry_t **entries;
} iterable_t;


typedef struct {
	int magic;
	int refCount;
	iterableValue_t type;
	object_t *obj;
} objectIterableEntry_t;

typedef struct {
	int magic;
	int refCount;
	iterableValue_t type;
	int start;
	int end;
} rangeIterableEntry_t;

typedef struct {
	int magic;
	int refCount;
	iterableValue_t type;
	int start;
} infiniteIterableEntry_t;

typedef struct {
	int magic;
	int refCount;
	iterableValue_t type;
	iterable_t *store;
} inputIterableEntry_t;

typedef struct {
	int magic;
	int refCount;
	iterableValue_t type;
	unsigned int length;
	char *string;
} stringIterableEntry_t;


typedef struct {
	void *vTable;
	int refCount;
	int numFields;
	unsigned int index;
	unsigned int innerIndex;
} iterableIndex_t;







/* Allocates the neccessary memory */
object_t * createObject(int type, int startingRefs);
bool gc(object_t *obj);
object_t* gc_inc(object_t *obj);
object_t* gc_dec(object_t *obj);
void gc_allVTable();
void gc_vTable(vTable_t *vtable);
void gc_iterableIndex(iterableIndex_t* iterIndex);

iterableIndex_t * createIndexer();
bool iterableHasNext(object_t *obj, iterableIndex_t *indexer);
object_t * iterableNext(object_t * iter, iterableIndex_t *indexer);
object_t * iterableAppend(object_t *, object_t *);
object_t * iterableAppend_sr(object_t * obj1, object_t * obj2, int startingRefs);

/*These functions create and populate the built-in types
 * Strings are just general Iterables*/
object_t * createInteger(int val, int startingRefs);
object_t * createBoolean(bool val, int startingRefs);
object_t * createCharacter(char val, int startingRefs);

object_t * createIterable_value(object_t *value, int startingRefs); /*maybe extend this to do more then one*/
object_t * createIterable_finiteInt(int first, int last, int startingRefs);
object_t * createIterable_infiniteInt(int first, int startingRefs);
object_t * createIterable_string(char *str, int len, int startingRefs, bool isConstantString);
object_t * getInput();

void * getMethod(object_t *obj, unsigned int myTypeId, unsigned int functionIndex);

object_t *__character(int unicode);
object_t *__string(object_t *chars);
object_t *_Character_equals(object_t *__this__, object_t *that);
object_t *_Character_unicode(object_t *__this__);
object_t *_Boolean_equals(object_t *__this__, bool that);
object_t *_Boolean_lessThan(object_t *__this__, bool that, bool strict);
object_t *_Boolean_onwards(object_t *__this__, bool inclusive);
object_t *_Boolean_through(object_t *__this__, bool that, bool includeLower, bool includeUpper);
object_t *_Boolean_or(object_t *__this__, object_t *that);
object_t *_Boolean_and(object_t *__this__, object_t *that);
object_t *_Boolean_negate(object_t *__this__);
object_t *_Integer_equals(object_t *__this__, object_t *that);
object_t *_Integer_lessThan(object_t *__this__, int that, int strict);
object_t *_Integer_onwards(object_t *__this__, int inclusive);
object_t *_Integer_through(object_t *__this__, int that, bool includeLower, bool includeUpper);
object_t *_Integer_minus(object_t *__this__, object_t *that);
object_t *_Integer_plus(object_t *__this__, object_t *that);
object_t *_Integer_modulo(object_t *__this__, int that);
object_t *_Integer_divide(object_t *__this__, int that);
object_t *_Integer_times(object_t *__this__, object_t *that);
object_t *_Integer_negative(object_t *__this__);
object_t *_String_equals(object_t *__this__, object_t *that);

void init_VTables();
object_t * cubex_main_int();

#endif
