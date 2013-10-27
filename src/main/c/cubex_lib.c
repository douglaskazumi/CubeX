#include "cubex_lib.h"

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
	case 2: //finite integer iterable
		object=x3malloc(sizeof(finiteIntegerIterable_t));
		object->numFields=-2;
		((iterable_t *)object)->type=INTEGER_F
		break;
	case 3: //infinite integer iterable
		object=x3malloc(sizeof(infiniteIntegerIterable_t));
		object->numFields=-2;
		((iterable_t *)object)->type=INTEGER_INF
		break;
	case 4: //finite bool iterable
		object=x3malloc(sizeof(finiteBooleanIterable_t));
		object->numFields=-2;
		((iterable_t *)object)->type=BOOLEAN_F
		break;
	case 5: //"infinite" bool iterable
		object=x3malloc(sizeof(infiniteBooleanIterable_t));
		object->numFields=-2;
		((iterable_t *)object)->type=BOOLEAN_INF
		break;
	case 6: // general iterable
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
		return (Object *)0xDEADBEEF; //shouldn't happen
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
		case BOOLEAN_F:
			finiteBooleanIterable_t *actualIter = (finiteBooleanIterable_t *)iter;
			if(actualIter->current > actualIter->last)
				return false;
			return true;
			break;
		case BOOLEAN_INF:
			infiniteBooleanIterable_t *actualIter = (infiniteBooleanIterable_t *)iter;
			if(actualIter->current > true) // >1
				return false;
			return true;
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
		return (Object *)0xDEADBEEF; //shouldn't happen
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
		case BOOLEAN_F:
		case BOOLEAN_INF:
			finiteBooleanIterable_t *actualIter = (finiteBooleanIterable_t *)iter;
			bool cur=(actualIter->current)++;

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

integer_t * create_Integer(int val)
{
	integer_t *integer = (integer_t *)createObject(0);
	integer->value = val;
	return integer;
}

boolean_t * create_Boolean(bool val)
{
	boolean_t *boolean = (boolean_t *)createObject(1);
	boolean->value = val;
	return boolean;
}

iterable_t * createIterable(iterabletype_t type, )

