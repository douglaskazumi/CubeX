#include "cubex_lib.h"

Object newObject(struct type, void *params){

}

void gc(Object target){

}


bool iterableHasNext(Object *obj)
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
		case INTEGER:
			if(iter->isFinite)
			{
				finiteIntegerIterable_t *actualIter = (finiteIntegerIterable_t *)iter;
				if(actualIter->current > actualIter->last)
					return false;
				return true;
			}
			else
			{
				return true;
			}
		break;
		case BOOLEAN:

			if(iter->isFinite)
			{
				finiteBooleanIterable_t *actualIter = (finiteBooleanIterable_t *)iter;
				if(actualIter->current > actualIter->last)
					return false;
				return true;
			}
			else
			{
				infiniteBooleanIterable_t *actualIter = (infiniteBooleanIterable_t *)iter;
				if(actualIter->current > true) // >1
					return false;
				return true;
			}

		break;
	}

}


Object * iterableNext(Object * iter)
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
		case INTEGER:
				finiteIntegerIterable_t *actualIter = (finiteIntegerIterable_t *)iter;
				int cur=(actualIter->current)++;

				//TODO : create int object here
				//return createInteger(cur);
				return NULL;
		break;
		case BOOLEAN:
			finiteBooleanIterable_t *actualIter = (finiteBooleanIterable_t *)iter;
			bool cur=(actualIter->current)++;

			//TODO : create int object here
			//return createInteger(cur);
			return NULL;
		break;
	}
}
