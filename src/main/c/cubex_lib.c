#include "cubex_lib.h"

/*
 *
 * This function can be built dynamically from java, this is just a model of the real version.
 *
 * type is a unique identifier for every class as generated from java.
 *
 *
 */

object_t * createObject(int type, unsigned int startingRefs)
{
	object_t * object;
	switch(type)
	{
	case 0:
		object=x3malloc(sizeof(integer_t));
		object->numFields=-1;
		((integer_t *)object)->value=0;
		break;
	case 1:
		object=x3malloc(sizeof(boolean_t));
		object->numFields=-1;
		((boolean_t *)object)->value=false;
		break;
	case 2:
		object=x3malloc(sizeof(character_t));
		object->numFields=-1;
		object->value=0;
		break;
	case 3:
		object=x3malloc(sizeof(iterable_t));
		object->numFields=-2;
		object->vTable=NULL;
		break;

	}
	object->refCount=startingRefs;
	return object;
}

void gc(object_t *target){
	if(target->refcount != 0)
		return;
}

iterableIndex_t * createIndexer()
{
	iterableIndex_t * indexer = (iterableIndex_t *)x3malloc(sizeof(iterableIndex_t));
	indexer->index=0;
	indexer->innerIndex=0;
	return indexer;
}

bool iterableHasNext(object_t *obj, iterableIndex_t *indexer)
{
	if(obj == NULL)
		return false;
	if(obj->numFields!=-2 || indexer==NULL)
	{
		return false;
	}

	iterable_t *iter = (iterable_t *)obj;
	if(indexer->index >= iter->numEntries)
		return false;

	iterableEntry_t *entry = (iter->entries)[indexer->index];
	if(entry->type==INPUT )
	{
		int lineLength = next_line_length();
		if(lineLength==0)
			return false;
	}

	return true;
	break;
}

}

object_t * iterableAppend(object_t * obj1, object_t * obj2)
{

	if(obj1==NULL && obj2!=NULL)
		return obj2;
	if(obj1!=NULL && obj2==NULL)
		return obj1;
	if(obj1==NULL && obj2==NULL)
		return NULL;
	if(obj1->numFields!=-2 || obj2->numFields!=-2)
		return NULL;

	iterable_t *iter1 = (iterable_t *)obj1;
	iterable_t *iter2 = (iterable_t *)obj2;

	iterable_t *newIter = (iterable_t *) createObject(3,0);

	int numE1 = iter1->numEntries;
	int numE2 = iter2->numEntries;
	int newNumEntries = numE1+numE2;

	newIter->numEntries=newNumEntries;
	iterableEntry_t **entryTable = (iterableEntry_t **)x3malloc(newNumEntries*sizeof(iterableEntry_t*));


	for(int i=0; i<numE1; i++)
	{
		entryTable[i]=(iter1->entries)[i]
	}
	for(int i=0; i<numE2; i++)
	{
		entryTable[numE1+i]=(iter2->entries)[i]
	}

	newIter->entries = entryTable;

	gc(obj1);
	gc(obj2);
	return newIter;

}


object_t * iterableNext(object_t * iter, iterableIndex_t *indexer)
{

	//Assumes iter has a next item to return
	if(iter==NULL)
		return NULL;
	if(obj->numFields!=-2)
	{
		return (object_t *)0xDEADBEEF; //shouldn't happen
	}

	iterable_t *iter = (iterable_t *)obj;
	if(indexer->index >= iter->numEntries)
	return NULL;

	iterableEntry_t *entry = (iter->entries)[indexer->index];

	switch(entry->type)
	{
	case RANGE:
		rangeIterableEntry_t * rangeEntry = (rangeIterableEntry_t *)entry;
		object_t *value = createInteger(rangeEntry->start + indexer->innerIndex);
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
		infiniteIterableEntry_t * infEntry = (infiniteIterableEntry_t *)entry;
		object_t *value = createInteger(infEntry->start + indexer->innerIndex);
		indexer->innerIndex+=1;
		return value;
		break;
	case OBJECT:
		objectIterableEntry_t * objEntry = (objectIterableEntry_t *)entry;
		object_t *value = objEntry->obj;
		indexer->innerIndex=0;
		indexer->index+=1;
		return value;
		break;
	case STRING:
		stringIterableEntry_t * strEntry = (stringIterableEntry_t *)entry;
		object_t *value = (object_t*)object_t * createCharacter((strEntry->string)[indexer->innerIndex], 0);
		if(indexer->innerIndex >= len-1)
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

		int len = next_line_length();
		char * buff = (char *)x3malloc((len+1)*sizeof(char)); //null terminated ?
		read_line(buff);
		iterable_t str = (iterable_t *)createIterable_string(buff, len, 0, false);
		return str;
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
		return createBoolean(false, 0);

	for(i=0; i<len; i++)
	{
		if(str1->string[i]!=str2.string[i])
			return createBoolean(false, 0);
	}
	return createBoolean(true, 0);
}

object_t *_Integer_negative(object_t *__this__)
{
	return createInteger(-(create-(((integer_t *)__this__)->value)), 0);
}

object_t *_Integer_times(object_t *__this__, object_t *that)
{
	int val = (((integer_t *)__this__)->value) * (((integer_t *)that)->value);
	return createInteger(val, 0);
}

object_t *_Integer_divide(object_t *__this__, object_t *that)
{
	int val1 = (((integer_t *)__this__)->value);
	int val2 = (((integer_t *)that)->value);

	if(val2==0)
		return NULL;
	return createIterable_value(createInteger(val1/val2, 0), 0);
}

object_t *_Integer_modulo(object_t *__this__, object_t *that)
{
	int val1 = (((integer_t *)__this__)->value);
	int val2 = (((integer_t *)that)->value);

	if(val2==0)
		return NULL;

	return createIterable_value(createInteger(val1%val2, 0), 0);
}

object_t *_Integer_plus(object_t *__this__, object_t *that)
{
	int val = (((integer_t *)__this__)->value) + (((integer_t *)that)->value);
	return createInteger(val, 0);
}

object_t *_Integer_minus(object_t *__this__, object_t *that)
{
	int val = (((integer_t *)__this__)->value) - (((integer_t *)that)->value);
	return createInteger(val, 0);
}

object_t *_Integer_through(object_t *__this__, object_t *that, object_t *includeLower, object_t *includeUpper)
{
	int val1 = ((integer_t *)__this__)->value;
	int val2 = ((integer_t *)that)->value;
	bool low = ((boolean_t *)includeLower)->value;
	bool high = ((boolean_t *)includeUpper)->value;
	if(!low)
		val1+=1;
	if(!high)
		val2-=1;

	if(val1>val2)
		return NULL;
	if(val1==val2)
		return createIterable_value(createInteger(val1, 0), 0);

	return createIterable_finiteInt(val1, val2, 0);
}

object_t *_Integer_onwards(object_t *__this__, object_t *inclusive)
{
	int val = ((integer_t *)__this__)->value;
	bool inc = ((boolean_t *)inclusive)->value;

	if(!inc)
		val+=1;

	return createIterable_infiniteInt(val, 0);
}

object_t *_Integer_lessThan(object_t *__this__, object_t *that, object_t *strict)
{
	int val = (((integer_t *)__this__)->value) - (((integer_t *)that)->value);
	if(strict)
		return createBoolean(val<0, 0);
	if(strict)
		return createBoolean(val<=0, 0);
}

object_t *_Integer_equals(object_t *__this__, object_t *that)
{
	int val = (((integer_t *)__this__)->value) - (((integer_t *)that)->value);
		return createBoolean(val==0, 0);
}

object_t *_Boolean_negate(object_t *__this__)
{
	bool val = ((boolean_t *)__this__)->value;
	return createBoolean(!val, 0);
}

object_t *_Boolean_and(object_t *__this__, object_t *that)
{
	bool val1 = ((boolean_t *)__this__)->value;
	bool val2 = ((boolean_t *)that)->value;
	return createBoolean(val1&&val2, 0);
}

object_t *_Boolean_or(object_t *__this__, object_t *that)
{
	bool val1 = ((boolean_t *)__this__)->value;
	bool val2 = ((boolean_t *)that)->value;
	return createBoolean(val1||val2, 0);
}

object_t *_Boolean_through(object_t *__this__, object_t *that, object_t *includeLower, object_t *includeUpper)
{
	bool val1 = ((boolean_t *)__this__)->value;
	bool val2 = ((boolean_t *)that)->value;
	bool low = ((boolean_t *)includeLower)->value;
	bool high = ((boolean_t *)includeUpper)->value;

	if(!low && !val1)
		val1=true;
	if(!low && val1)
		return NULL;

	if(!high && !val2)
		return NULL;
	if(!high && val2)
		val2=false;

	if(val1==val2)
		createIterable_value(createBoolean(val1, unsigned int startingRefs),0);

	return iterableAppend(createIterable_value(createBoolean(val1, 0),0),createIterable_value(createBoolean(val2, 0),0));
}

object_t *_Boolean_onward(object_t *__this__, object_t *inclusive)
{
	bool val1 = ((boolean_t *)__this__)->value;
	bool inc = ((boolean_t *)inclusive)->value;

	if(val1 && !inc)
		return NULL;
	if(val1 && inc)
		return createIterable_value(createBoolean(true, unsigned int startingRefs),0);
	if(!val1 && !inc)
		return createIterable_value(createBoolean(true, unsigned int startingRefs),0);

	return iterableAppend(createIterable_value(createBoolean(false, 0),0),createIterable_value(createBoolean(true, 0),0));
}

object_t *_Boolean_lessThan(object_t *__this__, object_t *that, object_t *strict)
{
	bool val1 = ((boolean_t *)__this__)->value;
	bool val2 = ((boolean_t *)that)->value;
	bool strict = ((boolean_t *)strict)->value;

	if(strict && (val1==val2))
		return createBoolean(false, 0);
	if(!strict && (val1==val2))
			return createBoolean(true, 0);


	if(val1&&!val2)
		return createBoolean(false, 0);

	return createBoolean(true, 0);

}


object_t *_Boolean_equals(object_t *__this__, object_t *that)
{
	bool val1 = ((boolean_t *)__this__)->value;
	bool val2 = ((boolean_t *)that)->value;
	return createBoolean(val1==val2, 0);
}


object_t *_Character_unicode(object_t *__this__)
{
	char val = ((character_t *)__this__)->value;
	return createInteger(charuni(val), 0);
}

object_t *_Boolean_equals(object_t *__this__, object_t *that)
{
	char val1 = ((character_t *)__this__)->value;
	char val2 = ((character_t *)that)->value;
	return createBoolean(val1==val2, 0);
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

	for(i=0; i<iter->numEntries; i++)
	{
		entry = (iter->entries)[i];
		switch(entry->type)
		{
		case OBJECT:
			oEntry = (objectIterableEntry_t *)entry;
			totalLength++;

			((character_t *)(oEntry->obj))->value
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

	return createIterable_string(buf, totalLength, 0, false);
}

object_t *__character(object_t *unicode)
{
	return createCharacter(unichar(((integer_t *)unicode)->value),0);
}


/******************************\
 * OBJECT INITIALIZER SECTION *
\******************************/

object_t * createInteger(int val, unsigned int startingRefs)
{
	integer_t *integer = (integer_t *)createObject(0, startingRefs);
	integer->value = val;
	return (object_t *)integer;
}

object_t * createBoolean(bool val, unsigned int startingRefs)
{
	boolean_t *boolean = (boolean_t *)createObject(1, startingRefs);
	boolean->value = val;
	return (object_t *)boolean;
}

object_t * createCharacter(char val, unsigned int startingRefs)
{
	character_t *character = (character_t *)createObject(2, startingRefs);
	character->value = val;
	return (object_t *)character;
}

createIterable_empty(unsigned int startingRefs)
{
	iterable_t * iter = (iterable_t *)createObject(3, startingRefs);

	iter->numEntries=0;
	iter->entries=NULL;

	return (object_t *)iter;
}

object_t * createIterable_value(object_t *value, unsigned int startingRefs)
{
	iterable_t * iter = (iterable_t *)createObject(3, startingRefs);
	objectIterableEntry_t *entry = (objectIterableEntry_t *)x3malloc(sizeof(objectIterableEntry_t));
	entry->type=OBJECT;
	entry->obj=value;

	iter->numEntries=1;

	iterableEntry_t **entryPtr = (iterableEntry_t **)x3malloc(1*sizeof(iterableEntry_t*));
	*(entryPtr) = (iterableEntry_t *)entry;

	iter->entries=entryPtr;
	return (object_t *)iter;
}

object_t * createIterable_finiteInt(int first, int last, unsigned int startingRefs)
{
	iterable_t * iter = (iterable_t *)createObject(3, startingRefs);

	if(last<first)
	{
		iter->numEntries=0;
		iter->entries=NULL;
		return (object_t *)iter;
	}

	rangeIterableEntry_t *entry = (rangeIterableEntry_t *)x3malloc(sizeof(rangeIterableEntry_t));
	entry->type=RANGE;
	entry->start=first;
	entry->end=last;

	iter->numEntries=1;

	iterableEntry_t **entryPtr = (iterableEntry_t **)x3malloc(1*sizeof(iterableEntry_t*));
	*(entryPtr) = (iterableEntry_t *)entry;

	iter->entries=entryPtr;
	return (object_t *)iter;
}
object_t * createIterable_infiniteInt(int first, unsigned int startingRefs)
{
	iterable_t * iter = (iterable_t *)createObject(3, startingRefs);

	rangeIterableEntry_t *entry = (rangeIterableEntry_t *)x3malloc(sizeof(rangeIterableEntry_t));
	entry->type=INFINITE;
	entry->start=first;

	iter->numEntries=1;

	iterableEntry_t **entryPtr = (iterableEntry_t **)x3malloc(1*sizeof(iterableEntry_t*));
	*(entryPtr) = (iterableEntry_t *)entry;

	iter->entries=entryPtr;
	return (object_t *)iter;
}

object_t * createIterable_string(char *str, int len, unsigned int startingRefs, bool isConstantString)
{
	iterable_t * iter = (iterable_t *)createObject(3, startingRefs);

	iterableEntry_t **entryPtr = (iterableEntry_t **)x3malloc(sizeof(iterableEntry_t*));

	char * loc = str;
	if(isConstantString)
	{
		loc = (char *)x3malloc(len*sizeof(char));
		memcpy(str, loc, len);
	}


	stringIterableEntry_t *entry = (stringIterableEntry_t *)x3malloc(sizeof(stringIterableEntry_t));
	entry->type=STRING;
	entry->string=loc;
	entry->length=len;

	*(entryPtr) = (iterableEntry_t *)entry;

	iter->numEntries=1;

	iter->entries=entryPtr;
	return (object_t *)iter;
}

void memcpy(void * src, void * dest, unsigned int count)
{
	while (count--) *dest++ = *src++;
}

void * getMethod(object_t *obj, unsigned int myTypeId, unsigned int functionIndex)
{
	void *vTablePtr = object_t->vTable;
	void *iTablePtr = ((vTable_t *)(vTablePtr))->iTable;
	unsigned int len = ((iTable_t *)iTablePtr)->numEntries;
	iTableEntry_t *curPtr = (iTableEntry_t *)(((unsigned int *)iTablePtr)+1);

	unsigned int funOffset=0;
	for(int i=0; i<len; i++)
	{
		if(curPtr->typeId==myTypeId)
		{
			funOffset=curPtr->functionIndex;
		}
	}

	if(funOffset==0)
		return NULL;
	else
		return (vTablePtr+funOffset);
}
