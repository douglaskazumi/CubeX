lexer grammar CubeXLexer;

@header{
package main.util;
}

TRUE : 'true';
FALSE : 'false';
IF : 'if';
ELSE : 'else';
WHILE : 'while';
FOR : 'for';
IN : 'in';
RETURN : 'return';
INTERFACE : 'interface';
FUN : 'fun';
SUPER : 'super';
CLASS : 'class';
EXTENDSITERABLE : 'extends' [ \t\r\n]* 'Iterable';
EXTENDS : 'extends';
THING : 'Thing';
NOTHING : 'Nothing';
YIELDER : 'yielder';
YIELD : 'yield';


INT : [0-9]+;
NAMEUSINGLE : [A-Z];
NAMEU : [A-Z][a-zA-Z0-9_]+;
NAMEL : [a-z][a-zA-Z0-9_]*;
STRING : '"' ~[\n\r]*? '"';

COLON : ':';
SEMICOLON : ';';
PLUSPLUS : '++';

BANG : '!';

STAR : '*';
SLASH : '/';
PERCENT : '%';
PLUS : '+';
DASH : '-';
DOTDOT : '..';
LESSDOT : '<.';
DOTLESS : '.<';
LESSLESS : '<<';
DOTDOTDOT  : '...';
LESSDOTDOT : '<..';
DOT : '.';
COMMA : ',';

LANGLE : '<';
LANGLEEQUAL : '<=';
RANGLEEQUAL : '>=';
RANGLE : '>';
EQUALEQUAL : '==';
BANGEQUAL : '!=';
EQUAL : '=';
COLONEQUAL : ':=';

AMP : '&';
PIPE : '|';

LPAREN : '(';
RPAREN : ')';
LBRACK : '[';
RBRACK : ']';
LBRACE : '{';
RBRACE : '}';

COMMENT1 : '`' (COMMENT1 | ~[`\'])* '\'' -> skip;
COMMENT2 : '#' .*? [\n\r] -> skip;
WS : [ \t\r\n] -> skip;