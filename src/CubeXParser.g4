parser grammar CubeXParser;
options { tokenVocab = CubeXLexer; }

type 
	: NAMEUSINGLE
	| NAMEU typelist?
	| type AMP type
	| THING
	| NOTHING;
	
typelist
	: LANGLE (type (COMMA type)*)? RANGLE;
	
typevarlist
	: LANGLE (NAMEUSINGLE (COMMA NAMEUSINGLE)*)? RANGLE;
	
arglist
	:  (NAMEL COLON type (COMMA NAMEL COLON type)*)?;
	
scheme
	: typelist? LPAREN arglist RPAREN COLON type;

exprlist
	: (expr (COMMA expr)*)?;
	
expr
	: LPAREN expr RPAREN
	| TRUE
	| FALSE
	| INT
	| STRING
	| expr DOT NAMEL typelist? LPAREN exprlist RPAREN
	| (NAMEL | NAMEU) typelist? LPAREN exprlist RPAREN
	| NAMEL
	| LBRACK exprlist RBRACK
	| (DASH|BANG) expr
	| expr (STAR|SLASH|PERCENT) expr
	| expr (PLUS|DASH) expr
	| (DOTDOTDOT|LESSDOTDOT) expr
	| expr (DOTDOT|LESSDOT|DOTLESS|LESSLESS) expr
	| expr PLUSPLUS
	| expr (LANGLE|LANGLEEQUAL|RANGLEEQUAL|RANGLE) expr
	| expr (EQUALEQUAL|BANGEQUAL) expr
	| expr AMP expr
	| expr PIPE expr;
	
stat
	: LBRACE stat* RBRACE
	| NAMEL COLONEQUAL expr SEMICOLON
	| IF LPAREN expr RPAREN stat (ELSE stat)?
	| WHILE LPAREN expr RPAREN stat
	| FOR LPAREN NAMEL IN expr RPAREN stat
	| RETURN expr SEMICOLON;

functiondecl
	: FUN NAMEL scheme;
	
function
	: functiondecl EQUAL expr SEMICOLON
	| functiondecl stat;
	
interfacex3
	: INTERFACE NAMEU typevarlist? (EXTENDS type)? LBRACE (functiondecl (SEMICOLON|stat|(EQUAL expr SEMICOLON)))* RBRACE;

classx3
	: CLASS NAMEU typevarlist? LPAREN arglist RPAREN (EXTENDS type)? LBRACE stat* (SUPER LPAREN exprlist RPAREN)? SEMICOLON function* RBRACE;

program
	: stat
	| stat+ program
	| (FUN NAMEL scheme stat)+ program
	| interfacex3 program
	| classx3 program;
	
	