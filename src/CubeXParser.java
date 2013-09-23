// Generated from CubeXParser.g4 by ANTLR 4.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CubeXParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT1=54, COMMENT2=55, CLASS=12, LBRACK=50, STAR=25, WHILE=5, AMP=46, 
		PLUSPLUS=23, LANGLE=38, RANGLEEQUAL=40, LBRACE=52, BANGEQUAL=43, THING=14, 
		FOR=6, NAMEUSINGLE=17, EQUALEQUAL=42, DOTDOT=30, DOTDOTDOT=34, LESSLESS=33, 
		LPAREN=48, IF=3, RPAREN=49, SLASH=26, IN=7, COMMA=37, EQUAL=44, RETURN=8, 
		NOTHING=15, PLUS=28, PIPE=47, SUPER=11, RANGLE=41, DOT=36, RBRACK=51, 
		RBRACE=53, PERCENT=27, DASH=29, LESSDOTDOT=35, ELSE=4, LESSDOT=31, BANG=24, 
		SEMICOLON=22, INT=16, NAMEL=19, TRUE=1, DOTLESS=32, NAMEU=18, COLON=21, 
		WS=56, LANGLEEQUAL=39, INTERFACE=9, FUN=10, FALSE=2, EXTENDS=13, COLONEQUAL=45, 
		STRING=20;
	public static final String[] tokenNames = {
		"<INVALID>", "'true'", "'false'", "'if'", "'else'", "'while'", "'for'", 
		"'in'", "'return'", "'interface'", "'fun'", "'super'", "'class'", "'extends'", 
		"'Thing'", "'Nothing'", "INT", "NAMEUSINGLE", "NAMEU", "NAMEL", "STRING", 
		"':'", "';'", "'++'", "'!'", "'*'", "'/'", "'%'", "'+'", "'-'", "'..'", 
		"'<.'", "'.<'", "'<<'", "'...'", "'<..'", "'.'", "','", "'<'", "'<='", 
		"'>='", "'>'", "'=='", "'!='", "'='", "':='", "'&'", "'|'", "'('", "')'", 
		"'['", "']'", "'{'", "'}'", "COMMENT1", "COMMENT2", "WS"
	};
	public static final int
		RULE_type = 0, RULE_typelist = 1, RULE_typevarlist = 2, RULE_arglist = 3, 
		RULE_scheme = 4, RULE_exprlist = 5, RULE_expr = 6, RULE_stat = 7, RULE_functiondecl = 8, 
		RULE_function = 9, RULE_interfacex3 = 10, RULE_classx3 = 11, RULE_testprogram = 12, 
		RULE_program = 13;
	public static final String[] ruleNames = {
		"type", "typelist", "typevarlist", "arglist", "scheme", "exprlist", "expr", 
		"stat", "functiondecl", "function", "interfacex3", "classx3", "testprogram", 
		"program"
	};

	@Override
	public String getGrammarFileName() { return "CubeXParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public CubeXParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class TypeContext extends ParserRuleContext {
		public int _p;
		public CubeXType x;
		public TypeContext t1;
		public Token v;
		public TypelistContext t;
		public TypeContext t2;
		public TerminalNode NOTHING() { return getToken(CubeXParser.NOTHING, 0); }
		public TerminalNode THING() { return getToken(CubeXParser.THING, 0); }
		public TerminalNode NAMEUSINGLE() { return getToken(CubeXParser.NAMEUSINGLE, 0); }
		public TerminalNode AMP() { return getToken(CubeXParser.AMP, 0); }
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TypelistContext typelist() {
			return getRuleContext(TypelistContext.class,0);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TerminalNode NAMEU() { return getToken(CubeXParser.NAMEU, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public TypeContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState, _p);
		TypeContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, RULE_type);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			switch (_input.LA(1)) {
			case NAMEUSINGLE:
				{
				setState(29); ((TypeContext)_localctx).v = match(NAMEUSINGLE);
				((TypeContext)_localctx).x =  new CubeXTypeVariable((((TypeContext)_localctx).v!=null?((TypeContext)_localctx).v.getText():null));
				}
				break;
			case NAMEU:
				{
				 boolean ttest=false;
				setState(32); ((TypeContext)_localctx).v = match(NAMEU);
				setState(35);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					ttest=true;
					setState(34); ((TypeContext)_localctx).t = typelist();
					}
					break;
				}
				((TypeContext)_localctx).x =  new CubeXTypeClass((((TypeContext)_localctx).v!=null?((TypeContext)_localctx).v.getText():null), ttest?((TypeContext)_localctx).t.x:null);
				}
				break;
			case THING:
				{
				setState(38); match(THING);
				 ((TypeContext)_localctx).x =  CubeXType.getThing();
				}
				break;
			case NOTHING:
				{
				setState(40); match(NOTHING);
				 ((TypeContext)_localctx).x =  CubeXType.getNothing();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(51);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeContext(_parentctx, _parentState, _p);
					_localctx.t1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(44);
					if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "3 >= $_p");
					setState(45); match(AMP);
					setState(46); ((TypeContext)_localctx).t2 = type(4);
					 ((TypeContext)_localctx).x =  new CubeXTypeIntersection(((TypeContext)_localctx).t1.x,((TypeContext)_localctx).t2.x);
					}
					} 
				}
				setState(53);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TypelistContext extends ParserRuleContext {
		public ArrayList<CubeXType> x;
		public TypeContext t;
		public TerminalNode COMMA(int i) {
			return getToken(CubeXParser.COMMA, i);
		}
		public TerminalNode RANGLE() { return getToken(CubeXParser.RANGLE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CubeXParser.COMMA); }
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TerminalNode LANGLE() { return getToken(CubeXParser.LANGLE, 0); }
		public TypelistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typelist; }
	}

	public final TypelistContext typelist() throws RecognitionException {
		TypelistContext _localctx = new TypelistContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_typelist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((TypelistContext)_localctx).x = new ArrayList<CubeXType>();
			setState(70);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(55); match(LANGLE);
				setState(67);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << THING) | (1L << NOTHING) | (1L << NAMEUSINGLE) | (1L << NAMEU))) != 0)) {
					{
					setState(56); ((TypelistContext)_localctx).t = type(0);
					_localctx.x.add(((TypelistContext)_localctx).t.x);
					setState(64);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(58); match(COMMA);
						setState(59); ((TypelistContext)_localctx).t = type(0);
						_localctx.x.add(((TypelistContext)_localctx).t.x);
						}
						}
						setState(66);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(69); match(RANGLE);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypevarlistContext extends ParserRuleContext {
		public ArrayList<CubeXTypeVariable> x;
		public Token v;
		public List<TerminalNode> NAMEUSINGLE() { return getTokens(CubeXParser.NAMEUSINGLE); }
		public TerminalNode COMMA(int i) {
			return getToken(CubeXParser.COMMA, i);
		}
		public TerminalNode NAMEUSINGLE(int i) {
			return getToken(CubeXParser.NAMEUSINGLE, i);
		}
		public TerminalNode RANGLE() { return getToken(CubeXParser.RANGLE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CubeXParser.COMMA); }
		public TerminalNode LANGLE() { return getToken(CubeXParser.LANGLE, 0); }
		public TypevarlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typevarlist; }
	}

	public final TypevarlistContext typevarlist() throws RecognitionException {
		TypevarlistContext _localctx = new TypevarlistContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_typevarlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((TypevarlistContext)_localctx).x =  new ArrayList<CubeXTypeVariable>();
			setState(87);
			_la = _input.LA(1);
			if (_la==LANGLE) {
				{
				setState(73); match(LANGLE);
				setState(84);
				_la = _input.LA(1);
				if (_la==NAMEUSINGLE) {
					{
					setState(74); ((TypevarlistContext)_localctx).v = match(NAMEUSINGLE);
					_localctx.x.add(new CubeXTypeVariable((((TypevarlistContext)_localctx).v!=null?((TypevarlistContext)_localctx).v.getText():null)));
					setState(81);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(76); match(COMMA);
						setState(77); ((TypevarlistContext)_localctx).v = match(NAMEUSINGLE);
						_localctx.x.add(new CubeXTypeVariable((((TypevarlistContext)_localctx).v!=null?((TypevarlistContext)_localctx).v.getText():null)));
						}
						}
						setState(83);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(86); match(RANGLE);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArglistContext extends ParserRuleContext {
		public ArrayList<CubeXArgument> x;
		public Token v;
		public TypeContext t;
		public List<TerminalNode> COLON() { return getTokens(CubeXParser.COLON); }
		public TerminalNode COMMA(int i) {
			return getToken(CubeXParser.COMMA, i);
		}
		public List<TerminalNode> NAMEL() { return getTokens(CubeXParser.NAMEL); }
		public List<TerminalNode> COMMA() { return getTokens(CubeXParser.COMMA); }
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TerminalNode NAMEL(int i) {
			return getToken(CubeXParser.NAMEL, i);
		}
		public TerminalNode COLON(int i) {
			return getToken(CubeXParser.COLON, i);
		}
		public ArglistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arglist; }
	}

	public final ArglistContext arglist() throws RecognitionException {
		ArglistContext _localctx = new ArglistContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_arglist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((ArglistContext)_localctx).x =  new ArrayList<CubeXArgument>();
			setState(105);
			_la = _input.LA(1);
			if (_la==NAMEL) {
				{
				setState(90); ((ArglistContext)_localctx).v = match(NAMEL);
				setState(91); match(COLON);
				setState(92); ((ArglistContext)_localctx).t = type(0);
				_localctx.x.add(new CubeXArgument(new CubeXVariable((((ArglistContext)_localctx).v!=null?((ArglistContext)_localctx).v.getText():null)),((ArglistContext)_localctx).t.x));
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(94); match(COMMA);
					setState(95); ((ArglistContext)_localctx).v = match(NAMEL);
					setState(96); match(COLON);
					setState(97); ((ArglistContext)_localctx).t = type(0);
					_localctx.x.add(new CubeXArgument(new CubeXVariable((((ArglistContext)_localctx).v!=null?((ArglistContext)_localctx).v.getText():null)),((ArglistContext)_localctx).t.x));
					}
					}
					setState(104);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SchemeContext extends ParserRuleContext {
		public CubeXScheme x;
		public TypelistContext t;
		public ArglistContext alist;
		public TypeContext rt;
		public ArglistContext arglist() {
			return getRuleContext(ArglistContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CubeXParser.COLON, 0); }
		public TerminalNode RPAREN() { return getToken(CubeXParser.RPAREN, 0); }
		public TypelistContext typelist() {
			return getRuleContext(TypelistContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(CubeXParser.LPAREN, 0); }
		public SchemeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scheme; }
	}

	public final SchemeContext scheme() throws RecognitionException {
		SchemeContext _localctx = new SchemeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_scheme);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107); ((SchemeContext)_localctx).t = typelist();
			setState(108); match(LPAREN);
			setState(109); ((SchemeContext)_localctx).alist = arglist();
			setState(110); match(RPAREN);
			setState(111); match(COLON);
			setState(112); ((SchemeContext)_localctx).rt = type(0);
			((SchemeContext)_localctx).x =  new CubeXScheme(((SchemeContext)_localctx).t.x, ((SchemeContext)_localctx).alist.x, ((SchemeContext)_localctx).rt.x);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprlistContext extends ParserRuleContext {
		public ArrayList<CubeXExpression> x;
		public ExprContext e;
		public TerminalNode COMMA(int i) {
			return getToken(CubeXParser.COMMA, i);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CubeXParser.COMMA); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprlist; }
	}

	public final ExprlistContext exprlist() throws RecognitionException {
		ExprlistContext _localctx = new ExprlistContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_exprlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((ExprlistContext)_localctx).x = new ArrayList<CubeXExpression>();
			setState(127);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << INT) | (1L << NAMEU) | (1L << NAMEL) | (1L << STRING) | (1L << BANG) | (1L << DASH) | (1L << DOTDOTDOT) | (1L << LESSDOTDOT) | (1L << LPAREN) | (1L << LBRACK))) != 0)) {
				{
				setState(116); ((ExprlistContext)_localctx).e = expr(0);
				_localctx.x.add(((ExprlistContext)_localctx).e.x);
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(118); match(COMMA);
					setState(119); ((ExprlistContext)_localctx).e = expr(0);
					_localctx.x.add(((ExprlistContext)_localctx).e.x);
					}
					}
					setState(126);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public int _p;
		public CubeXExpression x;
		public ExprContext e1;
		public Token op;
		public ExprContext e;
		public Token INT;
		public Token STRING;
		public Token var;
		public TypelistContext t;
		public ExprlistContext elist;
		public Token v;
		public ExprContext e2;
		public TerminalNode RBRACK() { return getToken(CubeXParser.RBRACK, 0); }
		public TerminalNode LBRACK() { return getToken(CubeXParser.LBRACK, 0); }
		public TerminalNode STAR() { return getToken(CubeXParser.STAR, 0); }
		public TerminalNode PERCENT() { return getToken(CubeXParser.PERCENT, 0); }
		public TerminalNode DASH() { return getToken(CubeXParser.DASH, 0); }
		public TerminalNode AMP() { return getToken(CubeXParser.AMP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode PLUSPLUS() { return getToken(CubeXParser.PLUSPLUS, 0); }
		public TerminalNode LESSDOTDOT() { return getToken(CubeXParser.LESSDOTDOT, 0); }
		public TerminalNode RANGLEEQUAL() { return getToken(CubeXParser.RANGLEEQUAL, 0); }
		public TerminalNode LANGLE() { return getToken(CubeXParser.LANGLE, 0); }
		public TerminalNode BANGEQUAL() { return getToken(CubeXParser.BANGEQUAL, 0); }
		public TerminalNode LESSDOT() { return getToken(CubeXParser.LESSDOT, 0); }
		public TerminalNode EQUALEQUAL() { return getToken(CubeXParser.EQUALEQUAL, 0); }
		public TerminalNode INT() { return getToken(CubeXParser.INT, 0); }
		public TerminalNode BANG() { return getToken(CubeXParser.BANG, 0); }
		public TerminalNode DOTDOT() { return getToken(CubeXParser.DOTDOT, 0); }
		public TerminalNode DOTDOTDOT() { return getToken(CubeXParser.DOTDOTDOT, 0); }
		public TerminalNode NAMEL() { return getToken(CubeXParser.NAMEL, 0); }
		public TerminalNode DOTLESS() { return getToken(CubeXParser.DOTLESS, 0); }
		public TerminalNode TRUE() { return getToken(CubeXParser.TRUE, 0); }
		public TerminalNode LESSLESS() { return getToken(CubeXParser.LESSLESS, 0); }
		public TypelistContext typelist() {
			return getRuleContext(TypelistContext.class,0);
		}
		public ExprlistContext exprlist() {
			return getRuleContext(ExprlistContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(CubeXParser.LPAREN, 0); }
		public TerminalNode NAMEU() { return getToken(CubeXParser.NAMEU, 0); }
		public TerminalNode RPAREN() { return getToken(CubeXParser.RPAREN, 0); }
		public TerminalNode SLASH() { return getToken(CubeXParser.SLASH, 0); }
		public TerminalNode LANGLEEQUAL() { return getToken(CubeXParser.LANGLEEQUAL, 0); }
		public TerminalNode PIPE() { return getToken(CubeXParser.PIPE, 0); }
		public TerminalNode PLUS() { return getToken(CubeXParser.PLUS, 0); }
		public TerminalNode RANGLE() { return getToken(CubeXParser.RANGLE, 0); }
		public TerminalNode DOT() { return getToken(CubeXParser.DOT, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode FALSE() { return getToken(CubeXParser.FALSE, 0); }
		public TerminalNode STRING() { return getToken(CubeXParser.STRING, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExprContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState, _p);
		ExprContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, RULE_expr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(130);
				((ExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==BANG || _la==DASH) ) {
					((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(131); ((ExprContext)_localctx).e1 = expr(10);
				((ExprContext)_localctx).x =  (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==DASH ? new CubeXNegative(((ExprContext)_localctx).e.x) : new CubeXNegate(((ExprContext)_localctx).e.x);
				}
				break;

			case 2:
				{
				setState(134);
				((ExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==DOTDOTDOT || _la==LESSDOTDOT) ) {
					((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(135); ((ExprContext)_localctx).e1 = expr(7);
				((ExprContext)_localctx).x =  (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==DOTDOTDOT ? new CubeXOnwards(((ExprContext)_localctx).e1.x, true) : new CubeXOnwards(((ExprContext)_localctx).e1.x, false);
				}
				break;

			case 3:
				{
				setState(138); match(LPAREN);
				setState(139); ((ExprContext)_localctx).e = expr(0);
				setState(140); match(RPAREN);
				((ExprContext)_localctx).x =  ((ExprContext)_localctx).e.x;
				}
				break;

			case 4:
				{
				setState(143); match(TRUE);
				((ExprContext)_localctx).x =  new CubeXBoolean(true);
				}
				break;

			case 5:
				{
				setState(145); match(FALSE);
				((ExprContext)_localctx).x =  new CubeXBoolean(false);
				}
				break;

			case 6:
				{
				setState(147); ((ExprContext)_localctx).INT = match(INT);
				((ExprContext)_localctx).x =  new CubeXInteger((((ExprContext)_localctx).INT!=null?Integer.valueOf(((ExprContext)_localctx).INT.getText()):0));
				}
				break;

			case 7:
				{
				setState(149); ((ExprContext)_localctx).STRING = match(STRING);
				((ExprContext)_localctx).x =  new CubeXString((((ExprContext)_localctx).STRING!=null?((ExprContext)_localctx).STRING.getText():null));
				}
				break;

			case 8:
				{
				setState(151);
				((ExprContext)_localctx).var = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==NAMEU || _la==NAMEL) ) {
					((ExprContext)_localctx).var = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(152); ((ExprContext)_localctx).t = typelist();
				setState(153); match(LPAREN);
				setState(154); ((ExprContext)_localctx).elist = exprlist();
				setState(155); match(RPAREN);
				((ExprContext)_localctx).x = new CubeXFunctionCall(null, (((ExprContext)_localctx).var!=null?((ExprContext)_localctx).var.getText():null), ((ExprContext)_localctx).t.x, ((ExprContext)_localctx).elist.x);
				}
				break;

			case 9:
				{
				setState(158); ((ExprContext)_localctx).v = match(NAMEL);
				((ExprContext)_localctx).x = new CubeXVariable((((ExprContext)_localctx).v!=null?((ExprContext)_localctx).v.getText():null));
				}
				break;

			case 10:
				{
				setState(160); match(LBRACK);
				setState(161); ((ExprContext)_localctx).elist = exprlist();
				setState(162); match(RBRACK);
				((ExprContext)_localctx).x =  new CubeXIterable(((ExprContext)_localctx).elist.x);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(218);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(216);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(167);
						if (!(9 >= _localctx._p)) throw new FailedPredicateException(this, "9 >= $_p");
						setState(168);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STAR) | (1L << SLASH) | (1L << PERCENT))) != 0)) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(169); ((ExprContext)_localctx).e2 = expr(10);
						((ExprContext)_localctx).x =  (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==STAR ? new CubeXMultiply(((ExprContext)_localctx).e1.x, ((ExprContext)_localctx).e2.x) :
						          		(((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==SLASH ? new CubeXDivide(((ExprContext)_localctx).e1.x, ((ExprContext)_localctx).e2.x) : new CubeXMod(((ExprContext)_localctx).e1.x, ((ExprContext)_localctx).e2.x);
						}
						break;

					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(172);
						if (!(8 >= _localctx._p)) throw new FailedPredicateException(this, "8 >= $_p");
						setState(173);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==DASH) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(174); ((ExprContext)_localctx).e2 = expr(9);
						((ExprContext)_localctx).x =  (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==PLUS ? new CubeXPlus(((ExprContext)_localctx).e1.x, ((ExprContext)_localctx).e2.x) : new CubeXMinus(((ExprContext)_localctx).e1.x, ((ExprContext)_localctx).e2.x) ;
						}
						break;

					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(177);
						if (!(6 >= _localctx._p)) throw new FailedPredicateException(this, "6 >= $_p");
						setState(178);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DOTDOT) | (1L << LESSDOT) | (1L << DOTLESS) | (1L << LESSLESS))) != 0)) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(179); ((ExprContext)_localctx).e2 = expr(7);
						((ExprContext)_localctx).x =  (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==DOTDOT ? new CubeXThrough(((ExprContext)_localctx).e1.x, ((ExprContext)_localctx).e2.x, true, true) 
						          		: (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==LESSDOT ? new CubeXThrough(((ExprContext)_localctx).e1.x, ((ExprContext)_localctx).e2.x, false, true) 
						          		: (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==DOTLESS ? new CubeXThrough(((ExprContext)_localctx).e1.x, ((ExprContext)_localctx).e2.x, true, false) 
						          		: new CubeXThrough(((ExprContext)_localctx).e1.x, ((ExprContext)_localctx).e2.x, false, false);
						}
						break;

					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(182);
						if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "5 >= $_p");
						setState(183); match(PLUSPLUS);
						setState(184); ((ExprContext)_localctx).e2 = expr(6);
						((ExprContext)_localctx).x =  new CubeXAppend(((ExprContext)_localctx).e1.x, ((ExprContext)_localctx).e2.x);
						}
						break;

					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(187);
						if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "4 >= $_p");
						setState(188);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LANGLE) | (1L << LANGLEEQUAL) | (1L << RANGLEEQUAL) | (1L << RANGLE))) != 0)) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(189); ((ExprContext)_localctx).e2 = expr(5);
						((ExprContext)_localctx).x =  (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==LANGLE ? new CubeXLessThan(((ExprContext)_localctx).e1.x, ((ExprContext)_localctx).e2.x, true) 
						          		: (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==LANGLEEQUAL ? new CubeXLessThan(((ExprContext)_localctx).e1.x, ((ExprContext)_localctx).e2.x, false) 
						          		: (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==RANGLEEQUAL ? new CubeXLessThan(((ExprContext)_localctx).e2.x, ((ExprContext)_localctx).e1.x, false) 
						          		: new CubeXLessThan(((ExprContext)_localctx).e2.x, ((ExprContext)_localctx).e1.x, true);
						}
						break;

					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(192);
						if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "3 >= $_p");
						setState(193);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQUALEQUAL || _la==BANGEQUAL) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(194); ((ExprContext)_localctx).e2 = expr(4);
						((ExprContext)_localctx).x =  (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==EQUALEQUAL ? new CubeXEqual(((ExprContext)_localctx).e1.x,((ExprContext)_localctx).e2.x) : new CubeXNegate(new CubeXEqual(((ExprContext)_localctx).e1.x,((ExprContext)_localctx).e2.x));
						}
						break;

					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(197);
						if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
						setState(198); match(AMP);
						setState(199); ((ExprContext)_localctx).e2 = expr(3);
						((ExprContext)_localctx).x =  new CubeXAnd(((ExprContext)_localctx).e1.x,((ExprContext)_localctx).e2.x);
						}
						break;

					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(202);
						if (!(1 >= _localctx._p)) throw new FailedPredicateException(this, "1 >= $_p");
						setState(203); match(PIPE);
						setState(204); ((ExprContext)_localctx).e2 = expr(2);
						((ExprContext)_localctx).x =  new CubeXOr(((ExprContext)_localctx).e1.x,((ExprContext)_localctx).e2.x);
						}
						break;

					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(207);
						if (!(14 >= _localctx._p)) throw new FailedPredicateException(this, "14 >= $_p");
						setState(208); match(DOT);
						setState(209); ((ExprContext)_localctx).v = match(NAMEL);
						setState(210); ((ExprContext)_localctx).t = typelist();
						setState(211); match(LPAREN);
						setState(212); ((ExprContext)_localctx).elist = exprlist();
						setState(213); match(RPAREN);
						((ExprContext)_localctx).x = new CubeXFunctionCall(((ExprContext)_localctx).e1.x, (((ExprContext)_localctx).v!=null?((ExprContext)_localctx).v.getText():null), ((ExprContext)_localctx).t.x, ((ExprContext)_localctx).elist.x);
						}
						break;
					}
					} 
				}
				setState(220);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public CubeXStatement x;
		public StatContext s;
		public Token v;
		public ExprContext e1;
		public StatContext s1;
		public StatContext s2;
		public ExprContext e;
		public TerminalNode RPAREN() { return getToken(CubeXParser.RPAREN, 0); }
		public TerminalNode IN() { return getToken(CubeXParser.IN, 0); }
		public TerminalNode RBRACE() { return getToken(CubeXParser.RBRACE, 0); }
		public TerminalNode WHILE() { return getToken(CubeXParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(CubeXParser.LBRACE, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public TerminalNode ELSE() { return getToken(CubeXParser.ELSE, 0); }
		public TerminalNode RETURN() { return getToken(CubeXParser.RETURN, 0); }
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode FOR() { return getToken(CubeXParser.FOR, 0); }
		public TerminalNode SEMICOLON() { return getToken(CubeXParser.SEMICOLON, 0); }
		public TerminalNode NAMEL() { return getToken(CubeXParser.NAMEL, 0); }
		public TerminalNode COLONEQUAL() { return getToken(CubeXParser.COLONEQUAL, 0); }
		public TerminalNode LPAREN() { return getToken(CubeXParser.LPAREN, 0); }
		public TerminalNode IF() { return getToken(CubeXParser.IF, 0); }
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stat);
		int _la;
		try {
			setState(273);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				((StatContext)_localctx).x =  new CubeXBlock();
				setState(222); match(LBRACE);
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << RETURN) | (1L << NAMEL) | (1L << LBRACE))) != 0)) {
					{
					{
					setState(223); ((StatContext)_localctx).s = stat();
					((CubeXBlock)_localctx.x).add(((StatContext)_localctx).s.x);
					}
					}
					setState(230);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(231); match(RBRACE);
				((StatContext)_localctx).x = ((CubeXBlock)_localctx.x).reduceBlock();
				}
				break;
			case NAMEL:
				enterOuterAlt(_localctx, 2);
				{
				setState(233); ((StatContext)_localctx).v = match(NAMEL);
				setState(234); match(COLONEQUAL);
				setState(235); ((StatContext)_localctx).e1 = expr(0);
				setState(236); match(SEMICOLON);
				((StatContext)_localctx).x =  new CubeXAssignment((((StatContext)_localctx).v!=null?((StatContext)_localctx).v.getText():null), ((StatContext)_localctx).e1.x);
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				boolean elsetest=false;
				setState(240); match(IF);
				setState(241); match(LPAREN);
				setState(242); ((StatContext)_localctx).e1 = expr(0);
				setState(243); match(RPAREN);
				setState(244); ((StatContext)_localctx).s1 = stat();
				setState(248);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					elsetest=true;
					setState(246); match(ELSE);
					setState(247); ((StatContext)_localctx).s2 = stat();
					}
					break;
				}
				((StatContext)_localctx).x =  new CubeXIfStatement(((StatContext)_localctx).e1.x, ((StatContext)_localctx).s1.x, elsetest?((StatContext)_localctx).s2.x:null);
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 4);
				{
				setState(252); match(WHILE);
				setState(253); match(LPAREN);
				setState(254); ((StatContext)_localctx).e = expr(0);
				setState(255); match(RPAREN);
				setState(256); ((StatContext)_localctx).s = stat();
				((StatContext)_localctx).x =  new CubeXWhileStatement(((StatContext)_localctx).e.x, ((StatContext)_localctx).s.x);
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 5);
				{
				setState(259); match(FOR);
				setState(260); match(LPAREN);
				setState(261); ((StatContext)_localctx).v = match(NAMEL);
				setState(262); match(IN);
				setState(263); ((StatContext)_localctx).e = expr(0);
				setState(264); match(RPAREN);
				setState(265); ((StatContext)_localctx).s = stat();
				((StatContext)_localctx).x =  new CubeXForStatement((((StatContext)_localctx).v!=null?((StatContext)_localctx).v.getText():null), ((StatContext)_localctx).e.x, ((StatContext)_localctx).s.x);
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 6);
				{
				setState(268); match(RETURN);
				setState(269); ((StatContext)_localctx).e = expr(0);
				setState(270); match(SEMICOLON);
				((StatContext)_localctx).x =  new CubeXReturnStatement(((StatContext)_localctx).e.x) ;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctiondeclContext extends ParserRuleContext {
		public CubeXFunctionDeclaration x;
		public Token v;
		public SchemeContext sch;
		public SchemeContext scheme() {
			return getRuleContext(SchemeContext.class,0);
		}
		public TerminalNode NAMEL() { return getToken(CubeXParser.NAMEL, 0); }
		public TerminalNode FUN() { return getToken(CubeXParser.FUN, 0); }
		public FunctiondeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functiondecl; }
	}

	public final FunctiondeclContext functiondecl() throws RecognitionException {
		FunctiondeclContext _localctx = new FunctiondeclContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_functiondecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275); match(FUN);
			setState(276); ((FunctiondeclContext)_localctx).v = match(NAMEL);
			setState(277); ((FunctiondeclContext)_localctx).sch = scheme();
			((FunctiondeclContext)_localctx).x = new CubeXFunctionDeclaration((((FunctiondeclContext)_localctx).v!=null?((FunctiondeclContext)_localctx).v.getText():null), ((FunctiondeclContext)_localctx).sch.x);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public CubeXFunction x;
		public FunctiondeclContext d;
		public ExprContext e;
		public StatContext s;
		public TerminalNode SEMICOLON() { return getToken(CubeXParser.SEMICOLON, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FunctiondeclContext functiondecl() {
			return getRuleContext(FunctiondeclContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(CubeXParser.EQUAL, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_function);
		try {
			setState(290);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(280); ((FunctionContext)_localctx).d = functiondecl();
				setState(281); match(EQUAL);
				setState(282); ((FunctionContext)_localctx).e = expr(0);
				setState(283); match(SEMICOLON);
				((FunctionContext)_localctx).x =  new CubeXFunction(((FunctionContext)_localctx).d.x,((FunctionContext)_localctx).e.x);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(286); ((FunctionContext)_localctx).d = functiondecl();
				setState(287); ((FunctionContext)_localctx).s = stat();
				((FunctionContext)_localctx).x =  new CubeXFunction(((FunctionContext)_localctx).d.x,((FunctionContext)_localctx).s.x);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Interfacex3Context extends ParserRuleContext {
		public CubeXInterface x;
		public Token n;
		public TypevarlistContext tvlist;
		public TypeContext extt;
		public FunctiondeclContext decl;
		public FunctionContext f;
		public TerminalNode RBRACE() { return getToken(CubeXParser.RBRACE, 0); }
		public List<FunctiondeclContext> functiondecl() {
			return getRuleContexts(FunctiondeclContext.class);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FunctiondeclContext functiondecl(int i) {
			return getRuleContext(FunctiondeclContext.class,i);
		}
		public TerminalNode LBRACE() { return getToken(CubeXParser.LBRACE, 0); }
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public TerminalNode SEMICOLON(int i) {
			return getToken(CubeXParser.SEMICOLON, i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(CubeXParser.SEMICOLON); }
		public TerminalNode INTERFACE() { return getToken(CubeXParser.INTERFACE, 0); }
		public TypevarlistContext typevarlist() {
			return getRuleContext(TypevarlistContext.class,0);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public TerminalNode EXTENDS() { return getToken(CubeXParser.EXTENDS, 0); }
		public TerminalNode NAMEU() { return getToken(CubeXParser.NAMEU, 0); }
		public Interfacex3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfacex3; }
	}

	public final Interfacex3Context interfacex3() throws RecognitionException {
		Interfacex3Context _localctx = new Interfacex3Context(_ctx, getState());
		enterRule(_localctx, 20, RULE_interfacex3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			boolean extttest=false;
			setState(293); match(INTERFACE);
			setState(294); ((Interfacex3Context)_localctx).n = match(NAMEU);
			setState(296);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(295); ((Interfacex3Context)_localctx).tvlist = typevarlist();
				}
				break;
			}
			setState(302);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(298); match(EXTENDS);
				setState(299); ((Interfacex3Context)_localctx).extt = type(0);
				extttest=true;
				}
			}

			setState(304); match(LBRACE);
			ArrayList<CubeXFunction> decls = new ArrayList<CubeXFunction>();
			setState(315);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FUN) {
				{
				setState(313);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					{
					setState(306); ((Interfacex3Context)_localctx).decl = functiondecl();
					setState(307); match(SEMICOLON);
					decls.add(new CubeXFunction(((Interfacex3Context)_localctx).decl.x));
					}
					}
					break;

				case 2:
					{
					{
					setState(310); ((Interfacex3Context)_localctx).f = function();
					decls.add(((Interfacex3Context)_localctx).f.x);
					}
					}
					break;
				}
				}
				setState(317);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(318); match(RBRACE);
			((Interfacex3Context)_localctx).x =  new CubeXInterface((((Interfacex3Context)_localctx).n!=null?((Interfacex3Context)_localctx).n.getText():null), ((Interfacex3Context)_localctx).tvlist.x, extttest?((Interfacex3Context)_localctx).extt.x:null, decls);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Classx3Context extends ParserRuleContext {
		public CubeXClass x;
		public Token n;
		public TypevarlistContext tvlist;
		public ArglistContext alist;
		public TypeContext extt;
		public StatContext s;
		public ExprlistContext superlist;
		public FunctionContext f;
		public ArglistContext arglist() {
			return getRuleContext(ArglistContext.class,0);
		}
		public List<TerminalNode> RPAREN() { return getTokens(CubeXParser.RPAREN); }
		public TerminalNode CLASS() { return getToken(CubeXParser.CLASS, 0); }
		public TerminalNode RPAREN(int i) {
			return getToken(CubeXParser.RPAREN, i);
		}
		public TerminalNode RBRACE() { return getToken(CubeXParser.RBRACE, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public TerminalNode LBRACE() { return getToken(CubeXParser.LBRACE, 0); }
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode SEMICOLON() { return getToken(CubeXParser.SEMICOLON, 0); }
		public TerminalNode SUPER() { return getToken(CubeXParser.SUPER, 0); }
		public TerminalNode LPAREN(int i) {
			return getToken(CubeXParser.LPAREN, i);
		}
		public TypevarlistContext typevarlist() {
			return getRuleContext(TypevarlistContext.class,0);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public TerminalNode EXTENDS() { return getToken(CubeXParser.EXTENDS, 0); }
		public ExprlistContext exprlist() {
			return getRuleContext(ExprlistContext.class,0);
		}
		public List<TerminalNode> LPAREN() { return getTokens(CubeXParser.LPAREN); }
		public TerminalNode NAMEU() { return getToken(CubeXParser.NAMEU, 0); }
		public Classx3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classx3; }
	}

	public final Classx3Context classx3() throws RecognitionException {
		Classx3Context _localctx = new Classx3Context(_ctx, getState());
		enterRule(_localctx, 22, RULE_classx3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321); match(CLASS);
			setState(322); ((Classx3Context)_localctx).n = match(NAMEU);
			setState(324);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(323); ((Classx3Context)_localctx).tvlist = typevarlist();
				}
				break;
			}
			setState(326); match(LPAREN);
			setState(327); ((Classx3Context)_localctx).alist = arglist();
			setState(328); match(RPAREN);
			boolean extttest=false;
			setState(334);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(330); match(EXTENDS);
				setState(331); ((Classx3Context)_localctx).extt = type(0);
				extttest=true;
				}
			}

			setState(336); match(LBRACE);
			ArrayList<CubeXStatement> stats = new ArrayList<CubeXStatement>();
			setState(343);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << RETURN) | (1L << NAMEL) | (1L << LBRACE))) != 0)) {
				{
				{
				setState(338); ((Classx3Context)_localctx).s = stat();
				stats.add(((Classx3Context)_localctx).s.x);
				}
				}
				setState(345);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			boolean supertest=false;
			setState(353);
			_la = _input.LA(1);
			if (_la==SUPER) {
				{
				setState(347); match(SUPER);
				setState(348); match(LPAREN);
				setState(349); ((Classx3Context)_localctx).superlist = exprlist();
				setState(350); match(RPAREN);
				supertest=true;
				}
			}

			setState(355); match(SEMICOLON);
			ArrayList<CubeXFunction> funs = new ArrayList<CubeXFunction>();
			setState(362);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FUN) {
				{
				{
				setState(357); ((Classx3Context)_localctx).f = function();
				funs.add(((Classx3Context)_localctx).f.x);
				}
				}
				setState(364);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(365); match(RBRACE);
			((Classx3Context)_localctx).x =  new CubeXClass((((Classx3Context)_localctx).n!=null?((Classx3Context)_localctx).n.getText():null), ((Classx3Context)_localctx).tvlist.x, ((Classx3Context)_localctx).alist.x, extttest?((Classx3Context)_localctx).extt.x:null, stats, supertest?((Classx3Context)_localctx).superlist.x:null, funs);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TestprogramContext extends ParserRuleContext {
		public CubeXProgram x;
		public ProgramContext p;
		public ProgramContext program() {
			return getRuleContext(ProgramContext.class,0);
		}
		public TestprogramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testprogram; }
	}

	public final TestprogramContext testprogram() throws RecognitionException {
		TestprogramContext _localctx = new TestprogramContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_testprogram);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368); ((TestprogramContext)_localctx).p = program();
			((TestprogramContext)_localctx).x =  ((TestprogramContext)_localctx).p.x;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgramContext extends ParserRuleContext {
		public CubeXProgram x;
		public StatContext s;
		public ProgramContext p;
		public FunctionContext f;
		public Interfacex3Context i;
		public Classx3Context c;
		public List<Classx3Context> classx3() {
			return getRuleContexts(Classx3Context.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode CLASS() { return getToken(CubeXParser.CLASS, 0); }
		public ProgramContext program() {
			return getRuleContext(ProgramContext.class,0);
		}
		public Interfacex3Context interfacex3(int i) {
			return getRuleContext(Interfacex3Context.class,i);
		}
		public List<Interfacex3Context> interfacex3() {
			return getRuleContexts(Interfacex3Context.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public Classx3Context classx3(int i) {
			return getRuleContext(Classx3Context.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_program);
		try {
			int _alt;
			setState(421);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				((ProgramContext)_localctx).x =  new CubeXProgram();
				setState(372); match(CLASS);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				((ProgramContext)_localctx).x =  new CubeXProgram();
				setState(374); ((ProgramContext)_localctx).s = stat();
				_localctx.x.addPiece(((ProgramContext)_localctx).s.x);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				((ProgramContext)_localctx).x =  new CubeXProgram();
				setState(381); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(378); ((ProgramContext)_localctx).s = stat();
						_localctx.x.addPiece(((ProgramContext)_localctx).s.x);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(383); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				} while ( _alt!=2 && _alt!=-1 );
				setState(385); ((ProgramContext)_localctx).p = program();
				_localctx.x.addPieces(((ProgramContext)_localctx).p.x);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				((ProgramContext)_localctx).x =  new CubeXProgram();
				setState(392); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(389); ((ProgramContext)_localctx).f = function();
						_localctx.x.addPiece(((ProgramContext)_localctx).f.x);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(394); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				} while ( _alt!=2 && _alt!=-1 );
				setState(396); ((ProgramContext)_localctx).p = program();
				_localctx.x.addPieces(((ProgramContext)_localctx).p.x);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				((ProgramContext)_localctx).x =  new CubeXProgram();
				setState(403); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(400); ((ProgramContext)_localctx).i = interfacex3();
						_localctx.x.addPiece(((ProgramContext)_localctx).i.x);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(405); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				} while ( _alt!=2 && _alt!=-1 );
				setState(407); ((ProgramContext)_localctx).p = program();
				_localctx.x.addPieces(((ProgramContext)_localctx).p.x);
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				((ProgramContext)_localctx).x =  new CubeXProgram();
				setState(414); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(411); ((ProgramContext)_localctx).c = classx3();
						_localctx.x.addPiece(((ProgramContext)_localctx).c.x);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(416); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				} while ( _alt!=2 && _alt!=-1 );
				setState(418); ((ProgramContext)_localctx).p = program();
				_localctx.x.addPieces(((ProgramContext)_localctx).p.x);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0: return type_sempred((TypeContext)_localctx, predIndex);

		case 6: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return 9 >= _localctx._p;

		case 2: return 8 >= _localctx._p;

		case 3: return 6 >= _localctx._p;

		case 4: return 5 >= _localctx._p;

		case 5: return 4 >= _localctx._p;

		case 6: return 3 >= _localctx._p;

		case 7: return 2 >= _localctx._p;

		case 8: return 1 >= _localctx._p;

		case 9: return 14 >= _localctx._p;
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 3 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\2\3:\u01aa\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\5\2&\n\2\3\2\3\2\3\2\3\2\3\2\5\2-\n\2\3\2\3\2\3\2\3"+
		"\2\3\2\7\2\64\n\2\f\2\16\2\67\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3"+
		"A\n\3\f\3\16\3D\13\3\5\3F\n\3\3\3\5\3I\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\7\4R\n\4\f\4\16\4U\13\4\5\4W\n\4\3\4\5\4Z\n\4\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\7\5g\n\5\f\5\16\5j\13\5\5\5l\n\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7}\n\7\f\7\16\7\u0080\13"+
		"\7\5\7\u0082\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\5\b\u00a8\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\7\b\u00db\n\b\f\b\16\b\u00de\13\b\3\t\3\t\3\t\3\t"+
		"\3\t\7\t\u00e5\n\t\f\t\16\t\u00e8\13\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00fb\n\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\5\t\u0114\n\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\5\13\u0125\n\13\3\f\3\f\3\f\3\f\5\f\u012b\n\f\3\f\3\f"+
		"\3\f\3\f\5\f\u0131\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u013c\n"+
		"\f\f\f\16\f\u013f\13\f\3\f\3\f\3\f\3\r\3\r\3\r\5\r\u0147\n\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\5\r\u0151\n\r\3\r\3\r\3\r\3\r\3\r\7\r\u0158\n\r"+
		"\f\r\16\r\u015b\13\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0164\n\r\3\r\3\r"+
		"\3\r\3\r\3\r\7\r\u016b\n\r\f\r\16\r\u016e\13\r\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\6\17\u0180\n\17"+
		"\r\17\16\17\u0181\3\17\3\17\3\17\3\17\3\17\3\17\3\17\6\17\u018b\n\17\r"+
		"\17\16\17\u018c\3\17\3\17\3\17\3\17\3\17\3\17\3\17\6\17\u0196\n\17\r\17"+
		"\16\17\u0197\3\17\3\17\3\17\3\17\3\17\3\17\3\17\6\17\u01a1\n\17\r\17\16"+
		"\17\u01a2\3\17\3\17\3\17\5\17\u01a8\n\17\3\17\2\20\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\2\n\4\32\32\37\37\3$%\3\24\25\3\33\35\3\36\37\3 #\3(+"+
		"\3,-\u01d6\2,\3\2\2\2\48\3\2\2\2\6J\3\2\2\2\b[\3\2\2\2\nm\3\2\2\2\fu\3"+
		"\2\2\2\16\u00a7\3\2\2\2\20\u0113\3\2\2\2\22\u0115\3\2\2\2\24\u0124\3\2"+
		"\2\2\26\u0126\3\2\2\2\30\u0143\3\2\2\2\32\u0172\3\2\2\2\34\u01a7\3\2\2"+
		"\2\36\37\b\2\1\2\37 \7\23\2\2 -\b\2\1\2!\"\b\2\1\2\"%\7\24\2\2#$\b\2\1"+
		"\2$&\5\4\3\2%#\3\2\2\2%&\3\2\2\2&\'\3\2\2\2\'-\b\2\1\2()\7\20\2\2)-\b"+
		"\2\1\2*+\7\21\2\2+-\b\2\1\2,\36\3\2\2\2,!\3\2\2\2,(\3\2\2\2,*\3\2\2\2"+
		"-\65\3\2\2\2./\6\2\2\3/\60\7\60\2\2\60\61\5\2\2\2\61\62\b\2\1\2\62\64"+
		"\3\2\2\2\63.\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66\3\3"+
		"\2\2\2\67\65\3\2\2\28H\b\3\1\29E\7(\2\2:;\5\2\2\2;B\b\3\1\2<=\7\'\2\2"+
		"=>\5\2\2\2>?\b\3\1\2?A\3\2\2\2@<\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2"+
		"CF\3\2\2\2DB\3\2\2\2E:\3\2\2\2EF\3\2\2\2FG\3\2\2\2GI\7+\2\2H9\3\2\2\2"+
		"HI\3\2\2\2I\5\3\2\2\2JY\b\4\1\2KV\7(\2\2LM\7\23\2\2MS\b\4\1\2NO\7\'\2"+
		"\2OP\7\23\2\2PR\b\4\1\2QN\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TW\3\2"+
		"\2\2US\3\2\2\2VL\3\2\2\2VW\3\2\2\2WX\3\2\2\2XZ\7+\2\2YK\3\2\2\2YZ\3\2"+
		"\2\2Z\7\3\2\2\2[k\b\5\1\2\\]\7\25\2\2]^\7\27\2\2^_\5\2\2\2_h\b\5\1\2`"+
		"a\7\'\2\2ab\7\25\2\2bc\7\27\2\2cd\5\2\2\2de\b\5\1\2eg\3\2\2\2f`\3\2\2"+
		"\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2il\3\2\2\2jh\3\2\2\2k\\\3\2\2\2kl\3\2"+
		"\2\2l\t\3\2\2\2mn\5\4\3\2no\7\62\2\2op\5\b\5\2pq\7\63\2\2qr\7\27\2\2r"+
		"s\5\2\2\2st\b\6\1\2t\13\3\2\2\2u\u0081\b\7\1\2vw\5\16\b\2w~\b\7\1\2xy"+
		"\7\'\2\2yz\5\16\b\2z{\b\7\1\2{}\3\2\2\2|x\3\2\2\2}\u0080\3\2\2\2~|\3\2"+
		"\2\2~\177\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0081v\3\2\2\2\u0081"+
		"\u0082\3\2\2\2\u0082\r\3\2\2\2\u0083\u0084\b\b\1\2\u0084\u0085\t\2\2\2"+
		"\u0085\u0086\5\16\b\2\u0086\u0087\b\b\1\2\u0087\u00a8\3\2\2\2\u0088\u0089"+
		"\t\3\2\2\u0089\u008a\5\16\b\2\u008a\u008b\b\b\1\2\u008b\u00a8\3\2\2\2"+
		"\u008c\u008d\7\62\2\2\u008d\u008e\5\16\b\2\u008e\u008f\7\63\2\2\u008f"+
		"\u0090\b\b\1\2\u0090\u00a8\3\2\2\2\u0091\u0092\7\3\2\2\u0092\u00a8\b\b"+
		"\1\2\u0093\u0094\7\4\2\2\u0094\u00a8\b\b\1\2\u0095\u0096\7\22\2\2\u0096"+
		"\u00a8\b\b\1\2\u0097\u0098\7\26\2\2\u0098\u00a8\b\b\1\2\u0099\u009a\t"+
		"\4\2\2\u009a\u009b\5\4\3\2\u009b\u009c\7\62\2\2\u009c\u009d\5\f\7\2\u009d"+
		"\u009e\7\63\2\2\u009e\u009f\b\b\1\2\u009f\u00a8\3\2\2\2\u00a0\u00a1\7"+
		"\25\2\2\u00a1\u00a8\b\b\1\2\u00a2\u00a3\7\64\2\2\u00a3\u00a4\5\f\7\2\u00a4"+
		"\u00a5\7\65\2\2\u00a5\u00a6\b\b\1\2\u00a6\u00a8\3\2\2\2\u00a7\u0083\3"+
		"\2\2\2\u00a7\u0088\3\2\2\2\u00a7\u008c\3\2\2\2\u00a7\u0091\3\2\2\2\u00a7"+
		"\u0093\3\2\2\2\u00a7\u0095\3\2\2\2\u00a7\u0097\3\2\2\2\u00a7\u0099\3\2"+
		"\2\2\u00a7\u00a0\3\2\2\2\u00a7\u00a2\3\2\2\2\u00a8\u00dc\3\2\2\2\u00a9"+
		"\u00aa\6\b\3\3\u00aa\u00ab\t\5\2\2\u00ab\u00ac\5\16\b\2\u00ac\u00ad\b"+
		"\b\1\2\u00ad\u00db\3\2\2\2\u00ae\u00af\6\b\4\3\u00af\u00b0\t\6\2\2\u00b0"+
		"\u00b1\5\16\b\2\u00b1\u00b2\b\b\1\2\u00b2\u00db\3\2\2\2\u00b3\u00b4\6"+
		"\b\5\3\u00b4\u00b5\t\7\2\2\u00b5\u00b6\5\16\b\2\u00b6\u00b7\b\b\1\2\u00b7"+
		"\u00db\3\2\2\2\u00b8\u00b9\6\b\6\3\u00b9\u00ba\7\31\2\2\u00ba\u00bb\5"+
		"\16\b\2\u00bb\u00bc\b\b\1\2\u00bc\u00db\3\2\2\2\u00bd\u00be\6\b\7\3\u00be"+
		"\u00bf\t\b\2\2\u00bf\u00c0\5\16\b\2\u00c0\u00c1\b\b\1\2\u00c1\u00db\3"+
		"\2\2\2\u00c2\u00c3\6\b\b\3\u00c3\u00c4\t\t\2\2\u00c4\u00c5\5\16\b\2\u00c5"+
		"\u00c6\b\b\1\2\u00c6\u00db\3\2\2\2\u00c7\u00c8\6\b\t\3\u00c8\u00c9\7\60"+
		"\2\2\u00c9\u00ca\5\16\b\2\u00ca\u00cb\b\b\1\2\u00cb\u00db\3\2\2\2\u00cc"+
		"\u00cd\6\b\n\3\u00cd\u00ce\7\61\2\2\u00ce\u00cf\5\16\b\2\u00cf\u00d0\b"+
		"\b\1\2\u00d0\u00db\3\2\2\2\u00d1\u00d2\6\b\13\3\u00d2\u00d3\7&\2\2\u00d3"+
		"\u00d4\7\25\2\2\u00d4\u00d5\5\4\3\2\u00d5\u00d6\7\62\2\2\u00d6\u00d7\5"+
		"\f\7\2\u00d7\u00d8\7\63\2\2\u00d8\u00d9\b\b\1\2\u00d9\u00db\3\2\2\2\u00da"+
		"\u00a9\3\2\2\2\u00da\u00ae\3\2\2\2\u00da\u00b3\3\2\2\2\u00da\u00b8\3\2"+
		"\2\2\u00da\u00bd\3\2\2\2\u00da\u00c2\3\2\2\2\u00da\u00c7\3\2\2\2\u00da"+
		"\u00cc\3\2\2\2\u00da\u00d1\3\2\2\2\u00db\u00de\3\2\2\2\u00dc\u00da\3\2"+
		"\2\2\u00dc\u00dd\3\2\2\2\u00dd\17\3\2\2\2\u00de\u00dc\3\2\2\2\u00df\u00e0"+
		"\b\t\1\2\u00e0\u00e6\7\66\2\2\u00e1\u00e2\5\20\t\2\u00e2\u00e3\b\t\1\2"+
		"\u00e3\u00e5\3\2\2\2\u00e4\u00e1\3\2\2\2\u00e5\u00e8\3\2\2\2\u00e6\u00e4"+
		"\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e9\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9"+
		"\u00ea\7\67\2\2\u00ea\u0114\b\t\1\2\u00eb\u00ec\7\25\2\2\u00ec\u00ed\7"+
		"/\2\2\u00ed\u00ee\5\16\b\2\u00ee\u00ef\7\30\2\2\u00ef\u00f0\b\t\1\2\u00f0"+
		"\u0114\3\2\2\2\u00f1\u00f2\b\t\1\2\u00f2\u00f3\7\5\2\2\u00f3\u00f4\7\62"+
		"\2\2\u00f4\u00f5\5\16\b\2\u00f5\u00f6\7\63\2\2\u00f6\u00fa\5\20\t\2\u00f7"+
		"\u00f8\b\t\1\2\u00f8\u00f9\7\6\2\2\u00f9\u00fb\5\20\t\2\u00fa\u00f7\3"+
		"\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fd\b\t\1\2\u00fd"+
		"\u0114\3\2\2\2\u00fe\u00ff\7\7\2\2\u00ff\u0100\7\62\2\2\u0100\u0101\5"+
		"\16\b\2\u0101\u0102\7\63\2\2\u0102\u0103\5\20\t\2\u0103\u0104\b\t\1\2"+
		"\u0104\u0114\3\2\2\2\u0105\u0106\7\b\2\2\u0106\u0107\7\62\2\2\u0107\u0108"+
		"\7\25\2\2\u0108\u0109\7\t\2\2\u0109\u010a\5\16\b\2\u010a\u010b\7\63\2"+
		"\2\u010b\u010c\5\20\t\2\u010c\u010d\b\t\1\2\u010d\u0114\3\2\2\2\u010e"+
		"\u010f\7\n\2\2\u010f\u0110\5\16\b\2\u0110\u0111\7\30\2\2\u0111\u0112\b"+
		"\t\1\2\u0112\u0114\3\2\2\2\u0113\u00df\3\2\2\2\u0113\u00eb\3\2\2\2\u0113"+
		"\u00f1\3\2\2\2\u0113\u00fe\3\2\2\2\u0113\u0105\3\2\2\2\u0113\u010e\3\2"+
		"\2\2\u0114\21\3\2\2\2\u0115\u0116\7\f\2\2\u0116\u0117\7\25\2\2\u0117\u0118"+
		"\5\n\6\2\u0118\u0119\b\n\1\2\u0119\23\3\2\2\2\u011a\u011b\5\22\n\2\u011b"+
		"\u011c\7.\2\2\u011c\u011d\5\16\b\2\u011d\u011e\7\30\2\2\u011e\u011f\b"+
		"\13\1\2\u011f\u0125\3\2\2\2\u0120\u0121\5\22\n\2\u0121\u0122\5\20\t\2"+
		"\u0122\u0123\b\13\1\2\u0123\u0125\3\2\2\2\u0124\u011a\3\2\2\2\u0124\u0120"+
		"\3\2\2\2\u0125\25\3\2\2\2\u0126\u0127\b\f\1\2\u0127\u0128\7\13\2\2\u0128"+
		"\u012a\7\24\2\2\u0129\u012b\5\6\4\2\u012a\u0129\3\2\2\2\u012a\u012b\3"+
		"\2\2\2\u012b\u0130\3\2\2\2\u012c\u012d\7\17\2\2\u012d\u012e\5\2\2\2\u012e"+
		"\u012f\b\f\1\2\u012f\u0131\3\2\2\2\u0130\u012c\3\2\2\2\u0130\u0131\3\2"+
		"\2\2\u0131\u0132\3\2\2\2\u0132\u0133\7\66\2\2\u0133\u013d\b\f\1\2\u0134"+
		"\u0135\5\22\n\2\u0135\u0136\7\30\2\2\u0136\u0137\b\f\1\2\u0137\u013c\3"+
		"\2\2\2\u0138\u0139\5\24\13\2\u0139\u013a\b\f\1\2\u013a\u013c\3\2\2\2\u013b"+
		"\u0134\3\2\2\2\u013b\u0138\3\2\2\2\u013c\u013f\3\2\2\2\u013d\u013b\3\2"+
		"\2\2\u013d\u013e\3\2\2\2\u013e\u0140\3\2\2\2\u013f\u013d\3\2\2\2\u0140"+
		"\u0141\7\67\2\2\u0141\u0142\b\f\1\2\u0142\27\3\2\2\2\u0143\u0144\7\16"+
		"\2\2\u0144\u0146\7\24\2\2\u0145\u0147\5\6\4\2\u0146\u0145\3\2\2\2\u0146"+
		"\u0147\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u0149\7\62\2\2\u0149\u014a\5"+
		"\b\5\2\u014a\u014b\7\63\2\2\u014b\u0150\b\r\1\2\u014c\u014d\7\17\2\2\u014d"+
		"\u014e\5\2\2\2\u014e\u014f\b\r\1\2\u014f\u0151\3\2\2\2\u0150\u014c\3\2"+
		"\2\2\u0150\u0151\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0153\7\66\2\2\u0153"+
		"\u0159\b\r\1\2\u0154\u0155\5\20\t\2\u0155\u0156\b\r\1\2\u0156\u0158\3"+
		"\2\2\2\u0157\u0154\3\2\2\2\u0158\u015b\3\2\2\2\u0159\u0157\3\2\2\2\u0159"+
		"\u015a\3\2\2\2\u015a\u015c\3\2\2\2\u015b\u0159\3\2\2\2\u015c\u0163\b\r"+
		"\1\2\u015d\u015e\7\r\2\2\u015e\u015f\7\62\2\2\u015f\u0160\5\f\7\2\u0160"+
		"\u0161\7\63\2\2\u0161\u0162\b\r\1\2\u0162\u0164\3\2\2\2\u0163\u015d\3"+
		"\2\2\2\u0163\u0164\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0166\7\30\2\2\u0166"+
		"\u016c\b\r\1\2\u0167\u0168\5\24\13\2\u0168\u0169\b\r\1\2\u0169\u016b\3"+
		"\2\2\2\u016a\u0167\3\2\2\2\u016b\u016e\3\2\2\2\u016c\u016a\3\2\2\2\u016c"+
		"\u016d\3\2\2\2\u016d\u016f\3\2\2\2\u016e\u016c\3\2\2\2\u016f\u0170\7\67"+
		"\2\2\u0170\u0171\b\r\1\2\u0171\31\3\2\2\2\u0172\u0173\5\34\17\2\u0173"+
		"\u0174\b\16\1\2\u0174\33\3\2\2\2\u0175\u0176\b\17\1\2\u0176\u01a8\7\16"+
		"\2\2\u0177\u0178\b\17\1\2\u0178\u0179\5\20\t\2\u0179\u017a\b\17\1\2\u017a"+
		"\u01a8\3\2\2\2\u017b\u017f\b\17\1\2\u017c\u017d\5\20\t\2\u017d\u017e\b"+
		"\17\1\2\u017e\u0180\3\2\2\2\u017f\u017c\3\2\2\2\u0180\u0181\3\2\2\2\u0181"+
		"\u017f\3\2\2\2\u0181\u0182\3\2\2\2\u0182\u0183\3\2\2\2\u0183\u0184\5\34"+
		"\17\2\u0184\u0185\b\17\1\2\u0185\u01a8\3\2\2\2\u0186\u018a\b\17\1\2\u0187"+
		"\u0188\5\24\13\2\u0188\u0189\b\17\1\2\u0189\u018b\3\2\2\2\u018a\u0187"+
		"\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u018a\3\2\2\2\u018c\u018d\3\2\2\2\u018d"+
		"\u018e\3\2\2\2\u018e\u018f\5\34\17\2\u018f\u0190\b\17\1\2\u0190\u01a8"+
		"\3\2\2\2\u0191\u0195\b\17\1\2\u0192\u0193\5\26\f\2\u0193\u0194\b\17\1"+
		"\2\u0194\u0196\3\2\2\2\u0195\u0192\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u0195"+
		"\3\2\2\2\u0197\u0198\3\2\2\2\u0198\u0199\3\2\2\2\u0199\u019a\5\34\17\2"+
		"\u019a\u019b\b\17\1\2\u019b\u01a8\3\2\2\2\u019c\u01a0\b\17\1\2\u019d\u019e"+
		"\5\30\r\2\u019e\u019f\b\17\1\2\u019f\u01a1\3\2\2\2\u01a0\u019d\3\2\2\2"+
		"\u01a1\u01a2\3\2\2\2\u01a2\u01a0\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3\u01a4"+
		"\3\2\2\2\u01a4\u01a5\5\34\17\2\u01a5\u01a6\b\17\1\2\u01a6\u01a8\3\2\2"+
		"\2\u01a7\u0175\3\2\2\2\u01a7\u0177\3\2\2\2\u01a7\u017b\3\2\2\2\u01a7\u0186"+
		"\3\2\2\2\u01a7\u0191\3\2\2\2\u01a7\u019c\3\2\2\2\u01a8\35\3\2\2\2$%,\65"+
		"BEHSVYhk~\u0081\u00a7\u00da\u00dc\u00e6\u00fa\u0113\u0124\u012a\u0130"+
		"\u013b\u013d\u0146\u0150\u0159\u0163\u016c\u0181\u018c\u0197\u01a2\u01a7";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}