#include "cubex_lib.h"
#include "cubex_main.h"
#include "cubex_external_functions.h"

vTable_t * vt_Iterable;

vTable_t * vt_Cons;

vTable_t * vt_String;

vTable_t * vt_Integer;

vTable_t * vt_Boolean;

vTable_t * vt_Character;

vTable_t * vt_Nil;

object_t * __strToCons( object_t *  v_iter);
object_t * c_Nil();
void cint_Nil(object_t *this);
object_t * _Nil_getFirst(object_t *this);
object_t * _Nil_getRest(object_t *this);
object_t * _Nil_getIterable(object_t *this);
object_t * _Nil_append(object_t *this,  object_t *  v_l);
object_t * _Nil_reverse(object_t *this);
object_t * c_Cons(object_t *ca_0, object_t *ca_1);
void cint_Cons(object_t *this, object_t *ca_0, object_t *ca_1);
object_t * _Cons_getFirst(object_t *this);
object_t * _Cons_getRest(object_t *this);
object_t * _Cons_getIterable(object_t *this);
object_t * _Cons_append(object_t *this,  object_t *  v_l);
object_t * _Cons_reverse(object_t *this);
object_t *v_input;
	object_t * vtemp_30 = NULL;
	object_t * v_str = NULL;
	object_t * vtemp_27 = NULL;
	object_t * vtemp_28 = NULL;
	object_t * vtemp_29 = NULL;
	object_t * vtemp_25 = NULL;
	object_t * v_i = NULL;
	object_t * vtemp_26 = NULL;
void init_VTables()
{
	vTable_t * vtable;
	iTable_t * itable;
	iTableEntry_t *curEntry;
	func * curVEntry;

/*Iterable*/
	vtable = x3malloc(sizeof(vTable_t) + 0*sizeof(func));
	itable = x3malloc(sizeof(iTable_t) + 1*sizeof(iTableEntry_t));
	itable->numEntries = 1;
	curEntry = (iTableEntry_t *)(itable+1);
		curEntry->typeId = 3;
		curEntry->functionIndex = 1;
		curEntry++;

	vtable->iTable = itable;
	curVEntry = (func *)(vtable+1);
	vt_Iterable = vtable;
/*Cons*/
	vtable = x3malloc(sizeof(vTable_t) + 10*sizeof(func));
	itable = x3malloc(sizeof(iTable_t) + 2*sizeof(iTableEntry_t));
	itable->numEntries = 2;
	curEntry = (iTableEntry_t *)(itable+1);
		curEntry->typeId = 5;
		curEntry->functionIndex = 1;
		curEntry++;
		curEntry->typeId = 7;
		curEntry->functionIndex = 6;
		curEntry++;

	vtable->iTable = itable;
	curVEntry = (func *)(vtable+1);
	*(curVEntry)=(func)(_Cons_getFirst);
	curVEntry++;
	*(curVEntry)=(func)(_Cons_getRest);
	curVEntry++;
	*(curVEntry)=(func)(_Cons_getIterable);
	curVEntry++;
	*(curVEntry)=(func)(_Cons_append);
	curVEntry++;
	*(curVEntry)=(func)(_Cons_reverse);
	curVEntry++;
	*(curVEntry)=(func)(_Cons_getFirst);
	curVEntry++;
	*(curVEntry)=(func)(_Cons_getRest);
	curVEntry++;
	*(curVEntry)=(func)(_Cons_getIterable);
	curVEntry++;
	*(curVEntry)=(func)(_Cons_append);
	curVEntry++;
	*(curVEntry)=(func)(_Cons_reverse);
	curVEntry++;
	vt_Cons = vtable;
/*String*/
	vtable = x3malloc(sizeof(vTable_t) + 1*sizeof(func));
	itable = x3malloc(sizeof(iTable_t) + 2*sizeof(iTableEntry_t));
	itable->numEntries = 2;
	curEntry = (iTableEntry_t *)(itable+1);
		curEntry->typeId = 3;
		curEntry->functionIndex = 1;
		curEntry++;
		curEntry->typeId = 3;
		curEntry->functionIndex = 1;
		curEntry++;

	vtable->iTable = itable;
	curVEntry = (func *)(vtable+1);
	*(curVEntry)=(func)(_String_equals);
	curVEntry++;
	vt_String = vtable;
/*Integer*/
	vtable = x3malloc(sizeof(vTable_t) + 10*sizeof(func));
	itable = x3malloc(sizeof(iTable_t) + 1*sizeof(iTableEntry_t));
	itable->numEntries = 1;
	curEntry = (iTableEntry_t *)(itable+1);
		curEntry->typeId = 0;
		curEntry->functionIndex = 1;
		curEntry++;

	vtable->iTable = itable;
	curVEntry = (func *)(vtable+1);
	*(curVEntry)=(func)(_Integer_negative);
	curVEntry++;
	*(curVEntry)=(func)(_Integer_times);
	curVEntry++;
	*(curVEntry)=(func)(_Integer_divide);
	curVEntry++;
	*(curVEntry)=(func)(_Integer_modulo);
	curVEntry++;
	*(curVEntry)=(func)(_Integer_plus);
	curVEntry++;
	*(curVEntry)=(func)(_Integer_minus);
	curVEntry++;
	*(curVEntry)=(func)(_Integer_through);
	curVEntry++;
	*(curVEntry)=(func)(_Integer_onwards);
	curVEntry++;
	*(curVEntry)=(func)(_Integer_lessThan);
	curVEntry++;
	*(curVEntry)=(func)(_Integer_equals);
	curVEntry++;
	vt_Integer = vtable;
/*Boolean*/
	vtable = x3malloc(sizeof(vTable_t) + 7*sizeof(func));
	itable = x3malloc(sizeof(iTable_t) + 1*sizeof(iTableEntry_t));
	itable->numEntries = 1;
	curEntry = (iTableEntry_t *)(itable+1);
		curEntry->typeId = 1;
		curEntry->functionIndex = 1;
		curEntry++;

	vtable->iTable = itable;
	curVEntry = (func *)(vtable+1);
	*(curVEntry)=(func)(_Boolean_negate);
	curVEntry++;
	*(curVEntry)=(func)(_Boolean_and);
	curVEntry++;
	*(curVEntry)=(func)(_Boolean_or);
	curVEntry++;
	*(curVEntry)=(func)(_Boolean_through);
	curVEntry++;
	*(curVEntry)=(func)(_Boolean_onwards);
	curVEntry++;
	*(curVEntry)=(func)(_Boolean_lessThan);
	curVEntry++;
	*(curVEntry)=(func)(_Boolean_equals);
	curVEntry++;
	vt_Boolean = vtable;
/*Character*/
	vtable = x3malloc(sizeof(vTable_t) + 2*sizeof(func));
	itable = x3malloc(sizeof(iTable_t) + 1*sizeof(iTableEntry_t));
	itable->numEntries = 1;
	curEntry = (iTableEntry_t *)(itable+1);
		curEntry->typeId = 2;
		curEntry->functionIndex = 1;
		curEntry++;

	vtable->iTable = itable;
	curVEntry = (func *)(vtable+1);
	*(curVEntry)=(func)(_Character_unicode);
	curVEntry++;
	*(curVEntry)=(func)(_Character_equals);
	curVEntry++;
	vt_Character = vtable;
/*Nil*/
	vtable = x3malloc(sizeof(vTable_t) + 10*sizeof(func));
	itable = x3malloc(sizeof(iTable_t) + 2*sizeof(iTableEntry_t));
	itable->numEntries = 2;
	curEntry = (iTableEntry_t *)(itable+1);
		curEntry->typeId = 5;
		curEntry->functionIndex = 1;
		curEntry++;
		curEntry->typeId = 6;
		curEntry->functionIndex = 6;
		curEntry++;

	vtable->iTable = itable;
	curVEntry = (func *)(vtable+1);
	*(curVEntry)=(func)(_Nil_getFirst);
	curVEntry++;
	*(curVEntry)=(func)(_Nil_getRest);
	curVEntry++;
	*(curVEntry)=(func)(_Nil_getIterable);
	curVEntry++;
	*(curVEntry)=(func)(_Nil_append);
	curVEntry++;
	*(curVEntry)=(func)(_Nil_reverse);
	curVEntry++;
	*(curVEntry)=(func)(_Nil_getFirst);
	curVEntry++;
	*(curVEntry)=(func)(_Nil_getRest);
	curVEntry++;
	*(curVEntry)=(func)(_Nil_getIterable);
	curVEntry++;
	*(curVEntry)=(func)(_Nil_append);
	curVEntry++;
	*(curVEntry)=(func)(_Nil_reverse);
	curVEntry++;
	vt_Nil = vtable;
}
void gc_allVTable()
{
gc_vTable(vt_Iterable);
gc_vTable(vt_Cons);
gc_vTable(vt_String);
gc_vTable(vt_Integer);
gc_vTable(vt_Boolean);
gc_vTable(vt_Character);
gc_vTable(vt_Nil);
	return;
}
object_t * createObject(int type, int startingRefs)
{
	int numfields=-1;
	int i=0;
	object_t * object;
	switch(type)
	{
	case 0: 
		object=x3malloc(sizeof(integer_t));
		object->numFields=-1;
		object->vTable=vt_Integer;
		((integer_t *)object)->value=0;
		break;
	case 1: 
		object=x3malloc(sizeof(boolean_t));
		object->numFields=-1;
		object->vTable=vt_Boolean;
		((boolean_t *)object)->value=false;
		break;
	case 2:
		object=x3malloc(sizeof(character_t));
		object->numFields=-1;
		object->vTable=vt_Character;
		((character_t *)object)->value=0;
		break;
	case 3:
		object=x3malloc(sizeof(iterable_t));
		object->numFields=-2;
		object->vTable=vt_Iterable;
		break;
	case 4:
		object=x3malloc(sizeof(iterable_t));
		object->numFields=-2;
		object->vTable=vt_String;
		break;

	case 6: /*Nil*/
		object=x3malloc(sizeof(object_t) + 0*sizeof(object_t *));
		numfields = 0;
		object->numFields=numfields;
		object->vTable=vt_Nil;
		break;

	case 7: /*Cons*/
		object=x3malloc(sizeof(object_t) + 2*sizeof(object_t *));
		numfields = 2;
		object->numFields=numfields;
		object->vTable=vt_Cons;
		break;

	}
	object->refCount=startingRefs;
	if(numfields==-1)
		return object;
	for(i=0; i<numfields; ++i)
		*(((int *)(((object_t *)object)+1))+i)=NULL;
	return object;
}

#define NOGC

bool gc(object_t *obj)
{
#ifndef NOGC
	iterable_t *iter;
	iterableEntry_t *iterEntry;
	int i;
	if(obj==NULL)
		return true;

	if(obj->numFields==-3)
	{
		gc_iterableIndex((iterableIndex_t *)obj);
		return true;
	}

	if(obj->refCount != 0)
		return false;

	if(obj->numFields==-1)
	{
		x3free(obj);
		return true;
	}
	if(obj->numFields==-2)
	{
		iter = (iterable_t *)obj;

		for(i=0; i<iter->numEntries; i++)
		{
			iterEntry = iter->entries[i];
			if(iter->entries[i]==NULL || iterEntry->refCount!=0 || iter->entries[i]->magic!=0x12345678)
				continue;
			switch(iterEntry->type)
			{
			case OBJECT:
				gc(((objectIterableEntry_t *)iterEntry)->obj);
				break;
			case INPUT:
				gc((object_t *)(((inputIterableEntry_t *)iterEntry)->store));
				break;
			case STRING:
				x3free(((stringIterableEntry_t *)iterEntry)->string);
			case RANGE:
			case INFINITE:
				break;
			}
			iter->entries[i]->magic=0;
			x3free(iter->entries[i]);
			iter->entries[i]=NULL;
		}
		x3free(iter->entries);
		x3free(obj);
		return true;
	}

	for(i=0; i<obj->numFields; i++)
	{
		gc(*(((object_t **)(obj+1))+i));
	}
	x3free(obj);
#endif
	return true;
}

object_t* gc_inc(object_t *obj)
{
#ifndef NOGC
	iterable_t *iter;
	iterableEntry_t *iterEntry;
	int i;
	if(obj==NULL)
		return NULL;

	if(obj->numFields==-3)
		return obj;

	obj->refCount+=1;

	if(obj->numFields==-1)
	{
		return obj;
	}
	else if (obj->numFields==-2)
	{
		iter = (iterable_t *)obj;
		for(i=0; i<iter->numEntries; i++)
		{
			iterEntry = iter->entries[i];
			iterEntry->refCount+=1;
			switch(iterEntry->type)
			{
			case OBJECT:
				gc_inc(((objectIterableEntry_t *)iterEntry)->obj);
				break;
			case INPUT:
				gc_inc((object_t *)(((inputIterableEntry_t *)iterEntry)->store));
				break;
			default:
				break;
			}
		}
		return obj;
	}

	for(i=0; i<obj->numFields; i++)
	{
		gc_inc(*(((object_t **)(obj+1))+i));
	}
#endif
	return obj;
}

object_t* gc_dec(object_t *obj)
{
#ifndef NOGC
	iterable_t *iter;
	iterableEntry_t *iterEntry;
	int i;
	if(obj==NULL)
		return NULL;

	if(obj->numFields==-3)
		return obj;

	obj->refCount-=1;

	if(obj->numFields==-1)
	{
		return obj;
	}
	else if (obj->numFields==-2)
	{
		iter = (iterable_t *)obj;
		for(i=0; i<iter->numEntries; i++)
		{
			iterEntry = iter->entries[i];
			iterEntry->refCount--;
			switch(iterEntry->type)
			{
			case OBJECT:
				gc_dec(((objectIterableEntry_t *)iterEntry)->obj);
				break;
			case INPUT:
				gc_dec((object_t *)(((inputIterableEntry_t *)iterEntry)->store));
				break;
			default:
				break;
			}
		}
		return obj;
	}

	for(i=0; i<obj->numFields; i++)
	{
		gc_dec(*(((object_t **)(obj+1))+i));
	}
#endif
	return obj;
}


void gc_vTable(vTable_t *vtable)
{
#ifndef NOGC
	x3free(vtable->iTable);
	x3free(vtable);
#endif
	return;
}

iterableIndex_t * createIndexer()
{
	iterableIndex_t * indexer = (iterableIndex_t *)x3malloc(sizeof(iterableIndex_t));
	indexer->index=0;
	indexer->innerIndex=0;
	indexer->numFields = -3;
	return indexer;
}

void gc_iterableIndex(iterableIndex_t* iterIndex)
{
#ifndef NOGC
	x3free(iterIndex);
#endif
}

bool iterableHasNext(object_t *obj, iterableIndex_t *indexer)
{
	int lineLength;
	iterable_t *iter;
	bool stored;
	iterableEntry_t *entry;
	iterableIndex_t *innerIndexer;
	if(obj == NULL)
		return false;
	if(obj->numFields!=-2 || indexer==NULL)
	{
		return false;
	}

	iter = (iterable_t *)obj;
	if(indexer->index >= iter->numEntries){
		return false;
	}

	entry = (iter->entries)[indexer->index];
	if(entry->type==INPUT )
	{
		innerIndexer=createIndexer();
		innerIndexer->index=indexer->innerIndex;
		stored = iterableHasNext((object_t *)(((inputIterableEntry_t *)entry)->store), innerIndexer);
		gc_iterableIndex(innerIndexer);
		if(stored)
			return true;

		lineLength = next_line_len();
		if(lineLength==0)
		{
			indexer->index+=1;
			indexer->innerIndex=0;
			return iterableHasNext(obj, indexer);
		}
	}

	return true;
}

object_t * iterableAppend(object_t * obj1, object_t * obj2)
{
	return iterableAppend_sr(obj1, obj2, 0);
}

object_t * iterableAppend_sr(object_t * obj1, object_t * obj2, int startingRefs)
{
	iterable_t *iter1;
	iterable_t *iter2;
	iterable_t *newIter;
	int numE1;
	int numE2;
	int newNumEntries;
	iterableEntry_t ** entryTable;
	int i;

	if(obj1==NULL && obj2!=NULL)
		return obj2;
	if(obj1!=NULL && obj2==NULL)
		return obj1;
	if(obj1==NULL && obj2==NULL)
		return NULL;
	if(obj1->numFields!=-2 || obj2->numFields!=-2)
		return NULL;

	iter1 = (iterable_t *)obj1;
	iter2 = (iterable_t *)obj2;

	newIter = (iterable_t *) createObject(3,startingRefs);

	numE1 = iter1->numEntries;
	numE2 = iter2->numEntries;
	newNumEntries = numE1+numE2;

	newIter->numEntries=newNumEntries;
	entryTable = (iterableEntry_t **)x3malloc(newNumEntries*sizeof(iterableEntry_t*));


	for(i=0; i<numE1; i++)
	{
		entryTable[i]=(iter1->entries)[i];
	}
	for(i=0; i<numE2; i++)
	{
		entryTable[numE1+i]=(iter2->entries)[i];
	}

	newIter->entries = entryTable;

#ifndef NOGC

	if(obj1->refCount==0)
	{
		x3free(iter1->entries);
		x3free(iter1);
	}
	if(obj1!=obj2 && obj2->refCount==0)
	{
		x3free(iter2->entries);
		x3free(iter2);
	}

#endif

	return (object_t *)newIter;
}


object_t * iterableNext(object_t * obj, iterableIndex_t *indexer)
{
	iterable_t *iter;
	iterableEntry_t *entry;
	rangeIterableEntry_t * rangeEntry;
	object_t *value;
	infiniteIterableEntry_t * infEntry;
	objectIterableEntry_t * objEntry;
	stringIterableEntry_t * strEntry;
	inputIterableEntry_t * inpEntry;
	iterableIndex_t *innerIndexer;
	iterable_t *newTempStoredValue;
	iterable_t *oldStore;
	int refs=0;
	int len=0;
	char * buff;
	iterable_t *str;

	/*Assumes iter has a next item to return*/
	if(obj==NULL)
		return NULL;
	if(obj->numFields!=-2)
	{
		return (object_t *)0xDEADBEEF; /*shouldn't happen*/
	}

	iter = (iterable_t *)obj;
	if(indexer->index >= iter->numEntries){
		return NULL;
	}

	entry = (iter->entries)[indexer->index];

	switch(entry->type)
	{
	case RANGE:
		rangeEntry = (rangeIterableEntry_t *)entry;
		value = createInteger(rangeEntry->start + indexer->innerIndex, 0);
		if(indexer->innerIndex >= rangeEntry->end - rangeEntry->start)
		{
			indexer->innerIndex=0;
			indexer->index+=1;
		}
		else
		{
			indexer->innerIndex+=1;
		}
		return value;
		break;
	case INFINITE:
		infEntry = (infiniteIterableEntry_t *)entry;
		value = createInteger(infEntry->start + indexer->innerIndex, 0);
		indexer->innerIndex+=1;
		return value;
		break;
	case OBJECT:
		objEntry = (objectIterableEntry_t *)entry;
		value = objEntry->obj;
		indexer->innerIndex=0;
		indexer->index+=1;
		return value;
		break;
	case STRING:
		strEntry = (stringIterableEntry_t *)entry;
		len=strEntry->length;
		value = createCharacter((strEntry->string)[indexer->innerIndex], 0);
		if(indexer->innerIndex+1 >= len)
		{
			indexer->innerIndex=0;
			indexer->index+=1;
		}
		else
		{
			indexer->innerIndex+=1;
		}
		return value;
		break;
	case INPUT:
			inpEntry = (inputIterableEntry_t *)entry;
			innerIndexer=createIndexer();
			innerIndexer->index=indexer->innerIndex;
			if(iterableHasNext((object_t *)(inpEntry->store), innerIndexer))
			{
				value =  iterableNext((object_t *)(inpEntry->store), innerIndexer);
				gc_iterableIndex(innerIndexer);
				indexer->innerIndex+=1;
				return value;
			}
			gc_iterableIndex(innerIndexer);
			len = next_line_len();
			buff = (char *)x3malloc((len+1)*sizeof(char)); /*null terminated ? */
			read_line(buff);
			refs=inpEntry->refCount;
			str = (iterable_t *)createIterable_string(buff, len,refs, false);

			oldStore = inpEntry->store;
			newTempStoredValue = (iterable_t *)createIterable_value((object_t *)str,refs);
			inpEntry->store = (iterable_t *)iterableAppend_sr((object_t *)oldStore,(object_t *)newTempStoredValue, refs);
#ifndef NOGC
			if(oldStore!=NULL)
			{
				x3free(oldStore->entries);
				x3free(oldStore);
				x3free(newTempStoredValue->entries);
				x3free(newTempStoredValue);
			}
#endif
			indexer->innerIndex+=1;

			return (object_t *)str;
			break;
	}

	return NULL;
}

object_t *_String_equals(object_t *__this__, object_t *that)
{
	stringIterableEntry_t *str1 = (stringIterableEntry_t *)(*(((iterable_t *)__this__)->entries));
	stringIterableEntry_t *str2 = (stringIterableEntry_t *)(*(((iterable_t *)that)->entries));
	unsigned int len=str1->length;
	unsigned int i;

	if(len!=str2->length)
	{
		gc(gc_dec(__this__));
		gc(gc_dec(that));
		return (object_t *)createBoolean(false, 0);
	}

	for(i=0; i<len; i++)
	{
		if(str1->string[i]!=str2->string[i])
		{
			gc(gc_dec(__this__));
			gc(gc_dec(that));
			return (object_t *)createBoolean(false, 0);
		}
	}
	gc(gc_dec(__this__));
	gc(gc_dec(that));
	return (object_t *)createBoolean(true, 0);
}

object_t *_Integer_negative(object_t *__this__)
{
	object_t * res=(object_t *)createInteger(-(((integer_t *)__this__)->value), 0);
	gc(gc_dec(__this__));
	return res;
}

object_t *_Integer_times(object_t *__this__, object_t *that)
{
	int val = (((integer_t *)__this__)->value) * (((integer_t *)that)->value);
	gc(gc_dec(__this__));
	gc(gc_dec(that));
	return (object_t *)createInteger(val, 0);
}

object_t *_Integer_divide(object_t *__this__, object_t *that)
{
	int val1 = (((integer_t *)__this__)->value);
	int val2 = (((integer_t *)that)->value);
	gc(gc_dec(__this__));
	gc(gc_dec(that));
	if(val2==0)
		return NULL;
	return (object_t *)createIterable_value(createInteger(val1/val2, 0), 0);
}

object_t *_Integer_modulo(object_t *__this__, object_t *that)
{
	int val1 = (((integer_t *)__this__)->value);
	int val2 = (((integer_t *)that)->value);
	gc(gc_dec(__this__));
	gc(gc_dec(that));
	if(val2==0)
		return NULL;

	return (object_t *)createIterable_value(createInteger(val1%val2, 0), 0);
}

object_t *_Integer_plus(object_t *__this__, object_t *that)
{
	int val = (((integer_t *)__this__)->value) + (((integer_t *)that)->value);
	gc(gc_dec(__this__));
	gc(gc_dec(that));
	return (object_t *)createInteger(val, 0);
}

object_t *_Integer_minus(object_t *__this__, object_t *that)
{
	int val = (((integer_t *)__this__)->value) - (((integer_t *)that)->value);
	gc(gc_dec(__this__));
	gc(gc_dec(that));
	return (object_t *)createInteger(val, 0);
}

object_t *_Integer_through(object_t *__this__, object_t *that, object_t *includeLower, object_t *includeUpper)
{
	int val1 = ((integer_t *)__this__)->value;
	int val2 = ((integer_t *)that)->value;
	bool low = ((boolean_t *)includeLower)->value;
	bool high = ((boolean_t *)includeUpper)->value;
	gc(gc_dec(__this__));
	gc(gc_dec(that));
	gc(gc_dec(includeLower));
	gc(gc_dec(includeUpper));
	if(!low)
		val1+=1;
	if(!high)
		val2-=1;

	if(val1>val2)
		return NULL;
	if(val1==val2)
		return (object_t *)createIterable_value(createInteger(val1, 0), 0);

	return (object_t *)createIterable_finiteInt(val1, val2, 0);
}

object_t *_Integer_onwards(object_t *__this__, object_t *inclusive)
{
	int val = ((integer_t *)__this__)->value;
	bool inc = ((boolean_t *)inclusive)->value;
	gc(gc_dec(__this__));
	gc(gc_dec(inclusive));
	if(!inc)
		val+=1;

	return (object_t *)createIterable_infiniteInt(val, 0);
}

object_t *_Integer_lessThan(object_t *__this__, object_t *that, object_t *strict)
{
	int val = (((integer_t *)__this__)->value) - (((integer_t *)that)->value);
	bool stct = ((boolean_t *)strict)->value;
	gc(gc_dec(__this__));
	gc(gc_dec(that));
	gc(gc_dec(strict));
	if(stct)
		return (object_t *)createBoolean(val<0, 0);
	if(!stct)
		return (object_t *)createBoolean(val<=0, 0);
	return NULL;
}

object_t *_Integer_equals(object_t *__this__, object_t *that)
{
	int val = (((integer_t *)__this__)->value) - (((integer_t *)that)->value);
	gc(gc_dec(__this__));
	gc(gc_dec(that));
		return (object_t *)createBoolean(val==0, 0);
}

object_t *_Boolean_negate(object_t *__this__)
{
	bool val = ((boolean_t *)__this__)->value;
	gc(gc_dec(__this__));
	return (object_t *)createBoolean(!val, 0);
}

object_t *_Boolean_and(object_t *__this__, object_t *that)
{
	bool val1 = ((boolean_t *)__this__)->value;
	bool val2 = ((boolean_t *)that)->value;
	gc(gc_dec(__this__));
	gc(gc_dec(that));
	return (object_t *)createBoolean(val1&&val2, 0);
}

object_t *_Boolean_or(object_t *__this__, object_t *that)
{
	bool val1 = ((boolean_t *)__this__)->value;
	bool val2 = ((boolean_t *)that)->value;
	gc(gc_dec(__this__));
	gc(gc_dec(that));
	return (object_t *)createBoolean(val1||val2, 0);
}

object_t *_Boolean_through(object_t *__this__, object_t *that, object_t *includeLower, object_t *includeUpper)
{
	bool val1 = ((boolean_t *)__this__)->value;
	bool val2 = ((boolean_t *)that)->value;
	bool low = ((boolean_t *)includeLower)->value;
	bool high = ((boolean_t *)includeUpper)->value;
	gc(gc_dec(__this__));
	gc(gc_dec(that));
	gc(gc_dec(includeLower));
	gc(gc_dec(includeUpper));

	if(!low && !val1)
		val1=true;
	if(!low && val1)
		return NULL;

	if(!high && !val2)
		return NULL;
	if(!high && val2)
		val2=false;

	if(val1==val2)
		return (object_t *)createIterable_value(createBoolean(val1, 0),0);

	return (object_t *)iterableAppend(createIterable_value(createBoolean(val1, 0),0),createIterable_value(createBoolean(val2, 0),0));
}

object_t *_Boolean_onwards(object_t *__this__, object_t *inclusive)
{
	bool val1 = ((boolean_t *)__this__)->value;
	bool inc = ((boolean_t *)inclusive)->value;
	gc(gc_dec(__this__));
	gc(gc_dec(inclusive));
	if(val1 && !inc)
		return NULL;
	if(val1 && inc)
		return (object_t *)createIterable_value(createBoolean(true, 0),0);
	if(!val1 && !inc)
		return (object_t *)createIterable_value(createBoolean(true, 0),0);

	return (object_t *)iterableAppend(createIterable_value(createBoolean(false, 0),0),createIterable_value(createBoolean(true, 0),0));
}

object_t *_Boolean_lessThan(object_t *__this__, object_t *that, object_t *strict)
{
	bool val1 = ((boolean_t *)__this__)->value;
	bool val2 = ((boolean_t *)that)->value;
	bool bstrict = ((boolean_t *)strict)->value;
	gc(gc_dec(__this__));
	gc(gc_dec(that));
	gc(gc_dec(strict));

	if(bstrict && (val1==val2))
		return (object_t *)createBoolean(false, 0);
	if(!bstrict && (val1==val2))
		return (object_t *)createBoolean(true, 0);


	if(val1&&!val2)
		return (object_t *)createBoolean(false, 0);

	return (object_t *)createBoolean(true, 0);

}


object_t *_Boolean_equals(object_t *__this__, object_t *that)
{
	bool val1 = ((boolean_t *)__this__)->value;
	bool val2 = ((boolean_t *)that)->value;
	gc(gc_dec(__this__));
	gc(gc_dec(that));
	return (object_t *)createBoolean(val1==val2, 0);
}


object_t *_Character_unicode(object_t *__this__)
{
	char val = ((character_t *)__this__)->value;
	gc(gc_dec(__this__));
	return createInteger(charuni(val), 0);
}

object_t *_Character_equals(object_t *__this__, object_t *that)
{
	char val1 = ((character_t *)__this__)->value;
	char val2 = ((character_t *)that)->value;
	gc(gc_dec(__this__));
	gc(gc_dec(that));
	return (object_t *)createBoolean(val1==val2, 0);
}

object_t *__string(object_t *chars)
{
	iterable_t *iter = (iterable_t *)chars;
	iterableEntry_t * entry;
	int i;
	int j;
	int k;
	objectIterableEntry_t *oEntry;
	stringIterableEntry_t *sEntry;
	unsigned int totalLength = 0;
	char *buf;

	if(chars==NULL)
	{
		return NULL;
	}

	for(i=0; i<iter->numEntries; i++)
	{
		entry = (iter->entries)[i];
		switch(entry->type)
		{
		case OBJECT:
			oEntry = (objectIterableEntry_t *)entry;
			totalLength++;

			break;
		case STRING:
			sEntry = (stringIterableEntry_t *)entry;
			totalLength += sEntry->length;
			break;
		default:
			break;
		}
	}

	buf = (char *)x3malloc(totalLength*sizeof(char));
	j=0;
	for(i=0; i<iter->numEntries; i++)
	{
		entry = (iter->entries)[i];
		switch(entry->type)
		{
		case OBJECT:
			oEntry = (objectIterableEntry_t *)entry;
			buf[j] = ((character_t *)(oEntry->obj))->value;
			j++;
			break;
		case STRING:
			sEntry = (stringIterableEntry_t *)entry;
			for(k=0; k<sEntry->length; k++)
			{
				buf[j]=(sEntry->string)[k];
				j++;
			}
			break;
		default:
			break;
		}
	}

	gc(gc_dec(chars));
	return (object_t *)createIterable_string(buf, totalLength, 0, false);
}

object_t *__character(object_t *unicode)
{
	object_t *res = (object_t *)createCharacter(unichar(((integer_t *)unicode)->value),0);
	gc(gc_dec(unicode));
	return res;
}


/******************************\
 * OBJECT INITIALIZER SECTION *
\******************************/

object_t * createInteger(int val, int startingRefs)
{
	integer_t *integer = (integer_t *)createObject(0, startingRefs);
	integer->value = val;
	return (object_t *)integer;
}

object_t * createBoolean(bool val, int startingRefs)
{
	boolean_t *boolean = (boolean_t *)createObject(1, startingRefs);
	boolean->value = val;
	return (object_t *)boolean;
}

object_t * createCharacter(char val, int startingRefs)
{
	character_t *character = (character_t *)createObject(2, startingRefs);
	character->value = val;
	return (object_t *)character;
}


object_t * createIterable_value(object_t *value, int startingRefs)
{

	iterableEntry_t **entryPtr;
	iterable_t * iter = (iterable_t *)createObject(3, startingRefs);
	objectIterableEntry_t *entry = (objectIterableEntry_t *)x3malloc(sizeof(objectIterableEntry_t));
	entry->type=OBJECT;
	entry->obj=value;
	entry->magic=0x12345678;
	entry->refCount=startingRefs;

	iter->numEntries=1;

	entryPtr = (iterableEntry_t **)x3malloc(1*sizeof(iterableEntry_t*));
	*(entryPtr) = (iterableEntry_t *)entry;

	iter->entries=entryPtr;
	return (object_t *)iter;
}

object_t * createIterable_finiteInt(int first, int last, int startingRefs)
{
	rangeIterableEntry_t *entry;
	iterableEntry_t **entryPtr;
	iterable_t * iter = (iterable_t *)createObject(3, startingRefs);

	if(last<first)
	{
		iter->numEntries=0;
		iter->entries=NULL;
		return (object_t *)iter;
	}

	entry = (rangeIterableEntry_t *)x3malloc(sizeof(rangeIterableEntry_t));
	entry->type=RANGE;
	entry->start=first;
	entry->end=last;
	entry->magic=0x12345678;
	entry->refCount=startingRefs;

	iter->numEntries=1;

	entryPtr = (iterableEntry_t **)x3malloc(1*sizeof(iterableEntry_t*));
	*(entryPtr) = (iterableEntry_t *)entry;

	iter->entries=entryPtr;
	return (object_t *)iter;
}
object_t * createIterable_infiniteInt(int first, int startingRefs)
{
	iterableEntry_t **entryPtr;
	iterable_t * iter = (iterable_t *)createObject(3, startingRefs);

	rangeIterableEntry_t *entry = (rangeIterableEntry_t *)x3malloc(sizeof(rangeIterableEntry_t));
	entry->type=INFINITE;
	entry->start=first;
	entry->magic=0x12345678;
	entry->refCount=startingRefs;

	iter->numEntries=1;

	entryPtr = (iterableEntry_t **)x3malloc(1*sizeof(iterableEntry_t*));
	*(entryPtr) = (iterableEntry_t *)entry;

	iter->entries=entryPtr;
	return (object_t *)iter;
}

void memcopy(void * src, void * dest, int count)
{
	while (count--) *(unsigned char *)dest++ = *(unsigned char *)src++;
}

object_t * createIterable_string(char *str, int len, int startingRefs, bool isConstantString)
{

	stringIterableEntry_t *entry;
	iterable_t * iter = (iterable_t *)createObject(4, startingRefs);

	iterableEntry_t **entryPtr;

	char * loc = str;

	if(len==0)
		return NULL;

	entryPtr = (iterableEntry_t **)x3malloc(sizeof(iterableEntry_t*));

	if(isConstantString)
	{
		loc = (char *)x3malloc(len*sizeof(char));
		memcopy(str, loc, len);
	}


	entry = (stringIterableEntry_t *)x3malloc(sizeof(stringIterableEntry_t));
	entry->type=STRING;
	entry->string=loc;
	entry->length=len;
	entry->magic=0x12345678;
	entry->refCount=startingRefs;

	*(entryPtr) = (iterableEntry_t *)entry;

	iter->numEntries=1;

	iter->entries=entryPtr;
	return (object_t *)iter;
}

bool isTrue(object_t *obj)
{
	bool val = ((boolean_t *)obj)->value;
	gc(obj);
	return val;
}

int unbox(object_t *obj)
{
	int val = ((integer_t *)obj)->value;
	gc(obj);
	return val;
}

object_t * box(int prim, bool isBool)
{
	if(isBool)
		return createBoolean(prim, 0);
	else
		return createInteger(prim, 0);
}

void * getMethod(object_t *obj, unsigned int myTypeId, unsigned int functionIndex)
{
	void *vTablePtr = obj->vTable;
	void *iTablePtr = ((vTable_t *)(vTablePtr))->iTable;
	unsigned int len = ((iTable_t *)iTablePtr)->numEntries;
	int i;
	iTableEntry_t *curPtr = (iTableEntry_t *)(((unsigned int *)iTablePtr)+1);

	unsigned int funOffset=0;
	for(i=0; i<len; i++)
	{
		if(curPtr->typeId==myTypeId)
		{
			funOffset=curPtr->functionIndex;
			break;
		}
		curPtr++;
	}

	if(funOffset==0)
		return NULL;
	else
		return *((func *)vTablePtr+funOffset+functionIndex);
}

object_t * getInput()
{
	inputIterableEntry_t *entry;
	iterableEntry_t **entryPtr;
	iterable_t * iter = (iterable_t *)createObject(3, 1);

	entry = (inputIterableEntry_t *)x3malloc(sizeof(inputIterableEntry_t));
	entry->type=INPUT;
	entry->store = NULL;
	entry->magic=0x12345678;
	entry->refCount=1;

	iter->numEntries=1;

	entryPtr = (iterableEntry_t **)x3malloc(1*sizeof(iterableEntry_t*));
	*(entryPtr) = (iterableEntry_t *)entry;

	iter->entries=entryPtr;
	return (object_t *)iter;

}


void cubex_main()
{
	object_t *returnValue;
	iterable_t *entry;
	iterableIndex_t *indexer;
	returnValue = gc_inc(cubex_main_int());
	if(returnValue == NULL)
	{
		gc(gc_dec(returnValue));
		gc(gc_dec(v_input));
		gc_allVTable();
		return;
	}

	indexer = createIndexer();
	while(iterableHasNext(returnValue,indexer))
	{
		entry = (iterable_t *)iterableNext(returnValue,indexer);

		if(entry==NULL)
		{
			print_line(NULL,0);
			continue;
		}

		print_line(((stringIterableEntry_t *)(entry->entries[0]))->string, (int)(((stringIterableEntry_t *)(entry->entries[0]))->length));
	}

	gc_iterableIndex(indexer);

	gc(gc_dec(returnValue));
	gc(gc_dec(v_input));
	gc_allVTable();
}
object_t * c_Nil()
{
object_t * this = createObject(6, 1);
cint_Nil(this);
return gc_dec(this);
}

void cint_Nil(object_t *this)
{
}

object_t * c_Cons(object_t *ca_0, object_t *ca_1)
{
object_t * this = createObject(7, 1);
cint_Cons(this, gc_dec(ca_0), gc_dec(ca_1));
return gc_dec(this);
}

void cint_Cons(object_t *this, object_t *ca_0, object_t *ca_1)
{
*(((object_t **)(this+1))+0) = gc_inc(ca_0);
*(((object_t **)(this+1))+1) = gc_inc(ca_1);
}

object_t * __strToCons( object_t *  v_iter)
{
	object_t * v_ret = NULL;
	object_t * v_c = NULL;
	object_t * vtemp_3 = NULL;
	object_t * vtemp_4 = NULL;
	object_t * vtemp_5 = NULL;
	object_t * vtemp_6 = NULL;
	object_t * vtemp_0 = NULL;
	object_t * vtemp_1 = NULL;
	object_t * vtemp_2 = NULL;
		vtemp_0 = gc_inc((c_Nil()));
			vtemp_1 = (object_t *)(v_ret);
	v_ret = (gc_inc(((((object_t * (*)(object_t *))(getMethod(vtemp_0, 6, 4)))(gc_inc(vtemp_0))))));
	gc(gc_dec(vtemp_1));
	vtemp_1 = NULL;
		gc(gc_dec(vtemp_0));
		vtemp_0 = NULL;
				vtemp_2 = (object_t *)createIndexer();
	vtemp_3 = gc_inc(v_iter);
	while(iterableHasNext(vtemp_3, (iterableIndex_t *)vtemp_2))
	{
		v_c = gc_inc(iterableNext(vtemp_3, (iterableIndex_t *)vtemp_2));
			vtemp_4 = (object_t *)(v_ret);
	v_ret = (gc_inc(((c_Cons(gc_inc(v_c), gc_inc(v_ret))))));
	gc(gc_dec(vtemp_4));
	vtemp_4 = NULL;
		gc(gc_dec(v_c));
		v_c = NULL;
	}
		gc_iterableIndex((iterableIndex_t *)vtemp_2);
		vtemp_2 = NULL;
		gc(gc_dec(vtemp_3));
		vtemp_3 = NULL;
		vtemp_5 = gc_inc(v_ret);
			vtemp_6 = gc_inc((((object_t * (*)(object_t *))(getMethod(vtemp_5, 7, 4)))(gc_inc(vtemp_5))));
		gc(gc_dec(vtemp_5));
		vtemp_5 = NULL;
	gc(gc_dec(v_iter));
	gc(gc_dec(v_ret));
	gc(gc_dec(v_c));
	gc(gc_dec(vtemp_3));
	gc(gc_dec(vtemp_4));
	gc(gc_dec(vtemp_5));
	gc(gc_dec(vtemp_0));
	gc(gc_dec(vtemp_1));
	gc(gc_dec(vtemp_2));
	return gc_dec(vtemp_6);

}
object_t * _Nil_getFirst(object_t *this)
{
	object_t * vtemp_7 = NULL;
	vtemp_7 = gc_inc(NULL);
gc_dec(this);
	return gc_dec(vtemp_7);

}
object_t * _Nil_getRest(object_t *this)
{
	object_t * vtemp_8 = NULL;
	vtemp_8 = gc_inc((c_Nil()));
gc_dec(this);
	return gc_dec(vtemp_8);

}
object_t * _Nil_getIterable(object_t *this)
{
	object_t * vtemp_9 = NULL;
	vtemp_9 = gc_inc(NULL);
gc_dec(this);
	return gc_dec(vtemp_9);

}
object_t * _Nil_append(object_t *this,  object_t *  v_l)
{
	object_t * vtemp_10 = NULL;
	vtemp_10 = gc_inc(v_l);
	gc(gc_dec(v_l));
gc_dec(this);
	return gc_dec(vtemp_10);

}
object_t * _Nil_reverse(object_t *this)
{
	object_t * vtemp_11 = NULL;
	vtemp_11 = gc_inc((c_Nil()));
gc_dec(this);
	return gc_dec(vtemp_11);

}
object_t * _Cons_getFirst(object_t *this)
{
	object_t * vtemp_12 = NULL;
	vtemp_12 = gc_inc(createIterable_value(*(((object_t **)(this+1))+0), 0));
gc_dec(this);
	return gc_dec(vtemp_12);

}
object_t * _Cons_getRest(object_t *this)
{
	object_t * vtemp_13 = NULL;
	vtemp_13 = gc_inc(*(((object_t **)(this+1))+1));
gc_dec(this);
	return gc_dec(vtemp_13);

}
object_t * _Cons_getIterable(object_t *this)
{
	object_t * vtemp_16 = NULL;
	object_t * vtemp_14 = NULL;
	object_t * vtemp_15 = NULL;
vtemp_15 = gc_inc(*(((object_t **)(this+1))+1));
;
	vtemp_16 = gc_inc(iterableAppend(createIterable_value(*(((object_t **)(this+1))+0), 0), (((object_t * (*)(object_t *))(getMethod(vtemp_15, 5, 2)))(gc_inc(vtemp_15)))));
		gc(gc_dec(vtemp_15));
		vtemp_15 = NULL;
	gc(gc_dec(vtemp_14));
	gc(gc_dec(vtemp_15));
gc_dec(this);
	return gc_dec(vtemp_16);

}
object_t * _Cons_append(object_t *this,  object_t *  v_l)
{
	object_t * vtemp_18 = NULL;
	object_t * vtemp_19 = NULL;
	object_t * vtemp_17 = NULL;
vtemp_18 = gc_inc(*(((object_t **)(this+1))+1));
;
	vtemp_19 = gc_inc((c_Cons(gc_inc(*(((object_t **)(this+1))+0)), gc_inc((((object_t * (*)(object_t *, object_t *))(getMethod(vtemp_18, 5, 3)))(gc_inc(vtemp_18), gc_inc(v_l)))))));
		gc(gc_dec(vtemp_18));
		vtemp_18 = NULL;
	gc(gc_dec(v_l));
	gc(gc_dec(vtemp_18));
	gc(gc_dec(vtemp_17));
gc_dec(this);
	return gc_dec(vtemp_19);

}
object_t * _Cons_reverse(object_t *this)
{
	object_t * vtemp_22 = NULL;
	object_t * vtemp_21 = NULL;
	object_t * vtemp_20 = NULL;
	object_t * vtemp_23 = NULL;
	object_t * vtemp_24 = NULL;
vtemp_22 = gc_inc(*(((object_t **)(this+1))+1));
vtemp_23 = gc_inc((((object_t * (*)(object_t *))(getMethod(vtemp_22, 5, 4)))(gc_inc(vtemp_22))));
;
	vtemp_24 = gc_inc((((object_t * (*)(object_t *, object_t *))(getMethod(vtemp_23, 5, 3)))(gc_inc(vtemp_23), gc_inc((c_Cons(gc_inc(*(((object_t **)(this+1))+0)), gc_inc((c_Nil()))))))));
		gc(gc_dec(vtemp_23));
		vtemp_23 = NULL;
	gc(gc_dec(vtemp_22));
	gc(gc_dec(vtemp_21));
	gc(gc_dec(vtemp_20));
	gc(gc_dec(vtemp_23));
gc_dec(this);
	return gc_dec(vtemp_24);

}

object_t * cubex_main_int()
{
	v_input=getInput();
	init_VTables();

		vtemp_25 = (object_t *)(v_str);
	v_str = (gc_inc((NULL)));
	gc(gc_dec(vtemp_25));
	vtemp_25 = NULL;
	vtemp_26 = (object_t *)createIndexer();
	vtemp_27 = gc_inc(v_input);
	while(iterableHasNext(vtemp_27, (iterableIndex_t *)vtemp_26))
	{
		v_i = gc_inc(iterableNext(vtemp_27, (iterableIndex_t *)vtemp_26));
			vtemp_28 = (object_t *)(v_str);
	v_str = (gc_inc((iterableAppend(v_str, v_i))));
	gc(gc_dec(vtemp_28));
	vtemp_28 = NULL;
		gc(gc_dec(v_i));
		v_i = NULL;
	}
		gc_iterableIndex((iterableIndex_t *)vtemp_26);
		vtemp_26 = NULL;
		gc(gc_dec(vtemp_27));
		vtemp_27 = NULL;
	vtemp_29 = gc_inc((__strToCons(gc_inc(v_str))));
		vtemp_30 = gc_inc(createIterable_value((__string(gc_inc((((object_t * (*)(object_t *))(getMethod(vtemp_29, 5, 2)))(gc_inc(vtemp_29)))))), 0));
		gc(gc_dec(vtemp_29));
		vtemp_29 = NULL;
	gc(gc_dec(v_str));
	gc(gc_dec(vtemp_27));
	gc(gc_dec(vtemp_28));
	gc(gc_dec(vtemp_29));
	gc(gc_dec(vtemp_25));
	gc(gc_dec(v_i));
	gc(gc_dec(vtemp_26));
	return gc_dec(vtemp_30);

}

