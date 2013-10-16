parser grammar CubeXParser;

@header{
package main.util;

import java.util.Arrays;
import main.expression.*;
import main.statement.*;
import main.program.*;
import main.type.*;
}

options { tokenVocab = CubeXLexer; }

type returns [CubeXType x]
	: v=NAMEUSINGLE
	{$x = new CubeXTypeVariable($v.text);}
	| { boolean ttest=false;}v=NAMEU ({ttest=true;} t=typelist)?
	{try {$x = CubeXTypeClass.NewCubeXTypeClass($v.text, ttest?$t.x:null);} catch(Exception e) { }}
	| t1=type AMP t2=type
	{ $x = new CubeXTypeIntersection($t1.x,$t2.x);}
	| THING
	{ $x = CubeXType.getThing();}
	| NOTHING
	{ $x = CubeXType.getNothing();};
	
typelist returns [ArrayList<CubeXType> x]
	: {$x=new ArrayList<CubeXType>();}
	(LANGLE (t=type {$x.add($t.x);} (COMMA t=type {$x.add($t.x);})*)? RANGLE)?;
	
typevarlist returns [ArrayList<CubeXTypeVariable> x]
	: {$x = new ArrayList<CubeXTypeVariable>();}
	(LANGLE (v=NAMEUSINGLE {$x.add(new CubeXTypeVariable($v.text));} (COMMA v=NAMEUSINGLE {$x.add(new CubeXTypeVariable($v.text));})*)? RANGLE)?;
	
arglist returns [ArrayList<CubeXArgument> x]
	:  {$x = new ArrayList<CubeXArgument>();}
	(v=NAMEL COLON t=type {$x.add(new CubeXArgument(new CubeXVariable($v.text),$t.x));} 
	(COMMA v=NAMEL COLON t=type {$x.add(new CubeXArgument(new CubeXVariable($v.text),$t.x));})*)?;
	
scheme returns [CubeXScheme x]
	: t=typevarlist LPAREN alist=arglist RPAREN COLON rt=type
		{$x = new CubeXScheme($t.x, $alist.x, $rt.x);};

exprlist returns [ArrayList<CubeXExpression> x]
	: {$x=new ArrayList<CubeXExpression>();}
	(e=expr {$x.add($e.x);} (COMMA e=expr {$x.add($e.x);})*)?;
	
expr returns [CubeXExpression x]
	: LPAREN e=expr RPAREN {$x = $e.x;}
	| TRUE {$x = new CubeXBoolean(true);}
	| FALSE {$x = new CubeXBoolean(false);}
	| INT {$x = new CubeXInteger($INT.int);}
	| STRING {$x = new CubeXString($STRING.text);}
	| e1=expr DOT v=NAMEL t=typelist LPAREN elist=exprlist RPAREN
	{$x=new CubeXFunctionCall($e1.x, $v.text, $t.x, $elist.x);}
	| var=(NAMEL | NAMEU) t=typelist LPAREN elist=exprlist RPAREN
	{$x=new CubeXFunctionCall(null, $var.text, $t.x, $elist.x);}
	| v=NAMEL
	{$x=new CubeXVariable($v.text);}
	| LBRACK elist=exprlist RBRACK
	{$x = new CubeXIterable($elist.x);}
	| op=(DASH|BANG) e1=expr
	{$x = $op.type==DASH ? new CubeXFunctionCall($e1.x,"negative",null) : new CubeXFunctionCall($e1.x,"negate",null);}
	| e1=expr op=(STAR|SLASH|PERCENT) e2=expr
	{$x = $op.type==STAR ? new CubeXFunctionCall($e1.x,"times",$e2.x) :
		$op.type==SLASH ? new CubeXFunctionCall($e1.x,"divide",$e2.x) : new CubeXFunctionCall($e1.x,"modulo",$e2.x);}
	| e1=expr op=(PLUS|DASH) e2=expr
	{$x = $op.type==PLUS ? new CubeXFunctionCall($e1.x,"plus", $e2.x) : new CubeXFunctionCall($e1.x,"minus",$e2.x) ;}
	|  e1=expr op=(DOTDOTDOT|LESSDOTDOT)
	{$x = $op.type==DOTDOTDOT ? new CubeXFunctionCall($e1.x,"onwards",new CubeXBoolean(true)) : new CubeXFunctionCall($e1.x,"onwards",new CubeXBoolean(false));}
	| e1=expr op=(DOTDOT|LESSDOT|DOTLESS|LESSLESS) e2=expr
	{$x = $op.type==DOTDOT ? new CubeXFunctionCall($e1.x,"through",null, new ArrayList<CubeXExpression>(Arrays.asList($e2.x, new CubeXBoolean(true), new CubeXBoolean(true))))
		: $op.type==LESSDOT ? new CubeXFunctionCall($e1.x,"through",null, new ArrayList<CubeXExpression>(Arrays.asList($e2.x, new CubeXBoolean(false), new CubeXBoolean(true)))) 
		: $op.type==DOTLESS ? new CubeXFunctionCall($e1.x,"through",null, new ArrayList<CubeXExpression>(Arrays.asList($e2.x, new CubeXBoolean(true), new CubeXBoolean(false)))) 
		: new CubeXFunctionCall($e1.x,"through",null, new ArrayList<CubeXExpression>(Arrays.asList($e2.x, new CubeXBoolean(false), new CubeXBoolean(false))));}
	| e1=expr PLUSPLUS e2=expr
	{$x= new CubeXAppend($e1.x, $e2.x);}
	| e1=expr op=(LANGLE|LANGLEEQUAL|RANGLEEQUAL|RANGLE) e2=expr
	{$x = $op.type==LANGLE ? new CubeXFunctionCall($e1.x,"lessThan",null, new ArrayList<CubeXExpression>(Arrays.asList($e2.x, new CubeXBoolean(true))) )
		: $op.type==LANGLEEQUAL ? new CubeXFunctionCall($e1.x,"lessThan",null, new ArrayList<CubeXExpression>(Arrays.asList($e2.x, new CubeXBoolean(false))))
		: $op.type==RANGLEEQUAL ? new CubeXFunctionCall($e2.x,"lessThan",null, new ArrayList<CubeXExpression>(Arrays.asList($e1.x, new CubeXBoolean(false))))
		: new CubeXFunctionCall($e2.x,"lessThan",null, new ArrayList<CubeXExpression>(Arrays.asList($e1.x, new CubeXBoolean(true))));}
	| e1=expr op=(EQUALEQUAL|BANGEQUAL) e2=expr
		{$x = $op.type==EQUALEQUAL ? new CubeXFunctionCall($e1.x,"equals", $e2.x) : new CubeXFunctionCall(new CubeXFunctionCall($e1.x,"equals", $e2.x),"negate",null);}
	| e1=expr AMP e2=expr
		{$x = new CubeXFunctionCall($e1.x,"and", $e2.x);}
	| e1=expr PIPE e2=expr
		{$x = new CubeXFunctionCall($e1.x,"or", $e2.x);};
	
stat returns [CubeXStatement x]
	: {$x = new CubeXBlock();}
	LBRACE (s=stat {((CubeXBlock)$x).add($s.x);})* RBRACE {$x=((CubeXBlock)$x).reduceBlock();}
	| v=NAMEL COLONEQUAL e1=expr SEMICOLON
	{$x = new CubeXAssignment($v.text, $e1.x);}
	| {boolean elsetest=false;} IF LPAREN e1=expr RPAREN s1=stat ({elsetest=true;}ELSE s2=stat)?
		{$x = new CubeXIfStatement($e1.x, $s1.x, elsetest?$s2.x:null);}
	| WHILE LPAREN e=expr RPAREN s=stat
		{$x = new CubeXWhileStatement($e.x, $s.x);}
	| FOR LPAREN v=NAMEL IN e=expr RPAREN s=stat
	{$x = new CubeXForStatement($v.text, $e.x, $s.x);}
	| RETURN e=expr SEMICOLON
	{$x = new CubeXReturnStatement($e.x) ;};
	
functiondecl returns [CubeXFunctionHeader x]
	: FUN v=NAMEL sch=scheme
		{$x=new CubeXFunctionHeader($v.text, $sch.x);};
	
function returns [CubeXFunction x]
	: d=functiondecl EQUAL e=expr SEMICOLON
	{$x = new CubeXFunction($d.x,$e.x);}
	| d=functiondecl s=stat
	{$x = new CubeXFunction($d.x,$s.x);};
	
interfacex3 returns [CubeXInterface x]
	: {boolean extttest=false;}INTERFACE n=NAMEU tvlist=typevarlist? (EXTENDS extt=type {extttest=true;})? LBRACE {ArrayList<CubeXFunction> decls = new ArrayList<CubeXFunction>();} ((decl=functiondecl SEMICOLON {decls.add(new CubeXFunction($decl.x));})|(f=function {decls.add($f.x);}))* RBRACE
		{$x = new CubeXInterface($n.text, $tvlist.x, extttest?$extt.x:null, decls);};

classx3 returns [CubeXClass x]
	: CLASS n=NAMEU tvlist=typevarlist? LPAREN alist=arglist RPAREN {boolean extttest=false;} (EXTENDS extt=type {extttest=true;})? LBRACE {ArrayList<CubeXStatement> stats = new ArrayList<CubeXStatement>();} (s=stat {stats.add($s.x);})* {boolean supertest=false;}(SUPER LPAREN superlist=exprlist RPAREN SEMICOLON {supertest=true;})? {ArrayList<CubeXFunction> funs = new ArrayList<CubeXFunction>();}(f=function {funs.add($f.x);})* RBRACE
    {$x = new CubeXClass($n.text, $tvlist.x, $alist.x, extttest?$extt.x:null, stats, supertest?$superlist.x:null, funs);};

testprogram returns [CubeXProgram x]
: p=program {$x = $p.x;};

program returns [CubeXProgram x]
	: {$x = new CubeXProgram();} s=stat {$x.addPiece($s.x);}
	| {$x = new CubeXProgram();} (s=stat {$x.addPiece($s.x);})+ p=program {$x.addPieces($p.x);}
	| {$x = new CubeXProgram();}(f=function {$x.addPiece($f.x);})+ p=program  {$x.addPieces($p.x);}
	| {$x = new CubeXProgram();}(i=interfacex3 {$x.addPiece($i.x);})+ p=program {$x.addPieces($p.x);}
	| {$x = new CubeXProgram();}( c=classx3 {$x.addPiece($c.x);})+ p=program {$x.addPieces($p.x);};
	
	