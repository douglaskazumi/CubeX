#include "cubex_lib.h"

<<<<<<< HEAD
/*
 *
 * This function can be built dynamically from java, this is just a model of the real version.
 *
 * type is a unique identifier for every class as generated from java.
 *
 *
 */
object_t * createObject(int type)
{
	object_t * object;
	switch(type)
	{
	case 0: //Integer
		object=x3malloc(sizeof(integer_t));
		object->numFields=-1;
		((integer_t *)object)->value=0;
		break;
	case 1: //Boolean
		object=x3malloc(sizeof(boolean_t));
		object->numFields=-1;
		((boolean_t *)object)->value=false;
		break;
	case 2: //Character
		object=x3malloc(sizeof(character_t));
		object->numFields=-1;
		object->value=0;
		break;
	case 3: //finite integer iterable
		object=x3malloc(sizeof(finiteIntegerIterable_t));
		object->numFields=-2;
		((iterable_t *)object)->type=INTEGER_F
		break;
	case 4: //infinite integer iterable
		object=x3malloc(sizeof(infiniteIntegerIterable_t));
		object->numFields=-2;
		((iterable_t *)object)->type=INTEGER_INF
		break;
	case 5: // general iterable
		object=x3malloc(sizeof(finiteGeneralIterable_t));
		object->numFields=-2;
		((iterable_t *)object)->type=OBJECT
		break;

	}
	object->refCount=1;
	return object;
}

void gc(object_t *target){
}


bool iterableHasNext(object_t *obj)
{
	if(obj->numFields!=-2)
	{
		return (object_t *)0xDEADBEEF; //shouldn't happen
	}

	iterable_t *iter = (iterable_t *)obj;

	switch (iter->type)
	{
		case OBJECT:
			finiteGeneralIterable_t *actualIter = (finiteGeneralIterable_t *)iter;
			if(actualIter->index >= actualIter->numEntries)
				return false;
			return true;
			break;
		case INTEGER_F:
			finiteIntegerIterable_t *actualIter = (finiteIntegerIterable_t *)iter;
			if(actualIter->current > actualIter->last)
				return false;
		case INTEGER_INF:
			return true;
			break;
		case INPUT:
			int lineLength = next_line_length();
			if(lineLength==0)
				return false;
			return true;

	}

}


object_t * iterableNext(object_t * iter)
{

	//Assumes iter has a next item to return

	if(obj->numFields!=-2)
	{
		return (object_t *)0xDEADBEEF; //shouldn't happen
	}

	iterable_t *iter = (iterable_t *)obj;

	switch (iter->type)
	{
		case OBJECT:
			finiteGeneralIterable_t *actualIter = (finiteGeneralIterable_t *)iter;
			return (actualIter->array)[actualIter->index];
		break;
		case INTEGER_F:
		case INTEGER_INF:
				finiteIntegerIterable_t *actualIter = (finiteIntegerIterable_t *)iter;
				int cur=(actualIter->current)++;

				//TODO : create int object here
				//return createInteger(cur);
				return NULL;
				break;
		case INPUT:
			int lineLength=next_line_length();
			if (lineLength==0)
				return NULL; //BAD

			//TODO create String object from read_line
			return NULL;
			break;
	}
	return NULL;
}

/******************************\
 * OBJECT INITIALIZER SECTION *
\******************************/

object_t * createInteger(int val)
{
	integer_t *integer = (integer_t *)createObject(0);
	integer->value = val;
	return (object_t *)integer;
}

object_t * createBoolean(bool val)
{
	boolean_t *boolean = (boolean_t *)createObject(1);
	boolean->value = val;
	return (object_t *)boolean;
}

object_t * createCharacter(char val)
{
	character_t *character = (character_t *)createObject(2);
	character->value = val;
	return (object_t *)character;
}

object_t * createIterable_values(object_t **values, int numValues)
{
	finiteGeneralIterable_t * iter = (finiteGeneralIterable_t *)createObject(5);
	iter->numEntries=numValues;
	iter->index=0;
	iter->array=values;
	return (object_t *)iter;
}
object_t * createIterable_finiteInt(int first, int last)
{
	finiteIntegerIterable_t * iter = (finiteIntegerIterable_t *)createObject(3);
	iter->current=first;
	iter->last=last;

	return (object_t *)iter;
}
object_t * createIterable_infiniteInt(int first)
{
	infiniteIntegerIterable_t * iter = (infiniteIntegerIterable_t *)createObject(4);
	iter->current=first;

	return (object_t *)iter;
}

