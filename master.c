#include "cubex_external_functions.h"
#include "cubex_main.h"
#include "cubex_private.h"

extern int allocdiff;

int main(int argc, char* argv[]){
	initialize(argc, argv);
	cubex_main();
	return allocdiff;
}
