parser grammar CubeXParser;
options { tokenVocab = CubeXLexer; }

type returns [CubeXType x]
	: v=NAMEUSINGLE
	{$x = new CubeXTypeVariable($v.text);}
	| { boolean ttest=false;}v=NAMEU ({ttest=true;} t=typelist)?
	{$x = new CubeXTypeClass($v.text, ttest?$t.x:null);}
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
	: t=typelist LPAREN alist=arglist RPAREN COLON rt=type
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
	{$x = $op.type==DASH ? new CubeXNegative($e.x) : new CubeXNegate($e.x);}
	| e1=expr op=(STAR|SLASH|PERCENT) e2=expr
	{$x = $op.type==STAR ? new CubeXMultiply($e1.x, $e2.x) :
		$op.type==SLASH ? new CubeXDivide($e1.x, $e2.x) : new CubeXMod($e1.x, $e2.x);}
	| e1=expr op=(PLUS|DASH) e2=expr
	{$x = $op.type==PLUS ? new CubeXPlus($e1.x, $e2.x) : new CubeXMinus($e1.x, $e2.x) ;}
	| op=(DOTDOTDOT|LESSDOTDOT) e1=expr
	{$x = $op.type==DOTDOTDOT ? new CubeXOnwards($e1.x, true) : new CubeXOnwards($e1.x, false);}
	| e1=expr op=(DOTDOT|LESSDOT|DOTLESS|LESSLESS) e2=expr
	{$x = $op.type==DOTDOT ? new CubeXThrough($e1.x, $e2.x, true, true) 
		: $op.type==LESSDOT ? new CubeXThrough($e1.x, $e2.x, false, true) 
		: $op.type==DOTLESS ? new CubeXThrough($e1.x, $e2.x, true, false) 
		: new CubeXThrough($e1.x, $e2.x, false, false);}
	| e1=expr PLUSPLUS e2=expr
	{$x= new CubeXAppend($e1.x, $e2.x);}
	| e1=expr op=(LANGLE|LANGLEEQUAL|RANGLEEQUAL|RANGLE) e2=expr
	{$x = $op.type==LANGLE ? new CubeXLessThan($e1.x, $e2.x, true) 
		: $op.type==LANGLEEQUAL ? new CubeXLessThan($e1.x, $e2.x, false) 
		: $op.type==RANGLEEQUAL ? new CubeXLessThan($e2.x, $e1.x, false) 
		: new CubeXLessThan($e2.x, $e1.x, true);}
	| e1=expr op=(EQUALEQUAL|BANGEQUAL) e2=expr
		{$x = $op.type==EQUALEQUAL ? new CubeXEqual($e1.x,$e2.x) : new CubeXNegate(new CubeXEqual($e1.x,$e2.x));}
	| e1=expr AMP e2=expr
		{$x = new CubeXAnd($e1.x,$e2.x);}
	| e1=expr PIPE e2=expr
		{$x = new CubeXOr($e1.x,$e2.x);};
	
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
	
functiondecl returns [CubeXFunctionDeclaration x]
	: FUN v=NAMEL sch=scheme
		{$x=new CubeXFunctionDeclaration($v.text, $sch.x);};
	
function returns [CubeXFunction x]
	: d=functiondecl EQUAL e=expr SEMICOLON
	{$x = new CubeXFunction($d.x,$e.x);}
	| d=functiondecl s=stat
	{$x = new CubeXFunction($d.x,$s.x);};
	
interfacex3 returns [CubeXInterface x]
	: {boolean extttest=false;}INTERFACE n=NAMEU tvlist=typevarlist? (EXTENDS extt=type {extttest=true;})? LBRACE {ArrayList<CubeXFunction> decls = new ArrayList<CubeXFunction>();} ((decl=functiondecl SEMICOLON {decls.add(new CubeXFunction($decl.x));})|(f=function {decls.add($f.x);}))* RBRACE
		{$x = new CubeXInterface($n.text, $tvlist.x, extttest?$extt.x:null, decls);};

classx3 returns [CubeXClass x]
	: CLASS n=NAMEU tvlist=typevarlist? LPAREN alist=arglist RPAREN {boolean extttest=false;} (EXTENDS extt=type {extttest=true;})? LBRACE {ArrayList<CubeXStatement> stats = new ArrayList<CubeXStatement>();} (s=stat {stats.add($s.x);})* {boolean supertest=false;}(SUPER LPAREN superlist=exprlist RPAREN {supertest=true;})? SEMICOLON {ArrayList<CubeXFunction> funs = new ArrayList<CubeXFunction>();}(f=function {funs.add($f.x);})* RBRACE
    {$x = new CubeXClass($n.text, $tvlist.x, $alist.x, extttest?$extt.x:null, stats, supertest?$superlist.x:null, funs);};

testprogram returns [CubeXProgram x]
: p=program {$x = $p.x;};

program returns [CubeXProgram x]
	: {$x = new CubeXProgram();} CLASS
	| {$x = new CubeXProgram();} s=stat {$x.addPiece($s.x);}
	| {$x = new CubeXProgram();} (s=stat {$x.addPiece($s.x);})+ p=program {$x.addPieces($p.x);}
	| {$x = new CubeXProgram();}(f=function {$x.addPiece($f.x);})+ p=program  {$x.addPieces($p.x);}
	| {$x = new CubeXProgram();}(i=interfacex3 {$x.addPiece($i.x);})+ p=program {$x.addPieces($p.x);}
	| {$x = new CubeXProgram();}(c=classx3 {$x.addPiece($c.x);})+ p=program {$x.addPieces($p.x);};
	
	