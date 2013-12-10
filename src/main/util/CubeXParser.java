// Generated from CubeXParser.g4 by ANTLR 4.0

package main.util;

import java.util.Arrays;
import main.expression.*;
import main.statement.*;
import main.program.*;
import main.type.*;

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
		COMMENT1=57, COMMENT2=58, CLASS=12, LBRACK=53, STAR=28, WHILE=5, AMP=49, 
		PLUSPLUS=26, LANGLE=41, RANGLEEQUAL=43, LBRACE=55, BANGEQUAL=46, THING=15, 
		FOR=6, NAMEUSINGLE=20, EQUALEQUAL=45, DOTDOT=33, DOTDOTDOT=37, LESSLESS=36, 
		EXTENDSITERABLE=13, LPAREN=51, IF=3, RPAREN=52, SLASH=29, IN=7, COMMA=40, 
		EQUAL=47, YIELD=18, YIELDER=17, RETURN=8, NOTHING=16, PIPE=50, PLUS=31, 
		SUPER=11, RANGLE=44, DOT=39, RBRACK=54, RBRACE=56, PERCENT=30, DASH=32, 
		LESSDOTDOT=38, ELSE=4, LESSDOT=34, INT=19, SEMICOLON=25, BANG=27, NAMEL=22, 
		TRUE=1, DOTLESS=35, NAMEU=21, COLON=24, WS=59, LANGLEEQUAL=42, INTERFACE=9, 
		FUN=10, FALSE=2, EXTENDS=14, COLONEQUAL=48, STRING=23;
	public static final String[] tokenNames = {
		"<INVALID>", "'true'", "'false'", "'if'", "'else'", "'while'", "'for'", 
		"'in'", "'return'", "'interface'", "'fun'", "'super'", "'class'", "EXTENDSITERABLE", 
		"'extends'", "'Thing'", "'Nothing'", "'yielder'", "'yield'", "INT", "NAMEUSINGLE", 
		"NAMEU", "NAMEL", "STRING", "':'", "';'", "'++'", "'!'", "'*'", "'/'", 
		"'%'", "'+'", "'-'", "'..'", "'<.'", "'.<'", "'<<'", "'...'", "'<..'", 
		"'.'", "','", "'<'", "'<='", "'>='", "'>'", "'=='", "'!='", "'='", "':='", 
		"'&'", "'|'", "'('", "')'", "'['", "']'", "'{'", "'}'", "COMMENT1", "COMMENT2", 
		"WS"
	};
	public static final int
		RULE_type = 0, RULE_typelist = 1, RULE_typevarlist = 2, RULE_arglist = 3, 
		RULE_scheme = 4, RULE_exprlist = 5, RULE_expr = 6, RULE_stat = 7, RULE_functiondecl = 8, 
		RULE_function = 9, RULE_yieldfun = 10, RULE_interfacex3 = 11, RULE_yielder = 12, 
		RULE_classx3 = 13, RULE_testprogram = 14, RULE_program = 15;
	public static final String[] ruleNames = {
		"type", "typelist", "typevarlist", "arglist", "scheme", "exprlist", "expr", 
		"stat", "functiondecl", "function", "yieldfun", "interfacex3", "yielder", 
		"classx3", "testprogram", "program"
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
			setState(46);
			switch (_input.LA(1)) {
			case NAMEUSINGLE:
				{
				setState(33); ((TypeContext)_localctx).v = match(NAMEUSINGLE);
				((TypeContext)_localctx).x =  new CubeXTypeVariable((((TypeContext)_localctx).v!=null?((TypeContext)_localctx).v.getText():null));
				}
				break;
			case NAMEU:
				{
				 boolean ttest=false;
				setState(36); ((TypeContext)_localctx).v = match(NAMEU);
				setState(39);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					ttest=true;
					setState(38); ((TypeContext)_localctx).t = typelist();
					}
					break;
				}
				try {((TypeContext)_localctx).x =  CubeXTypeClass.NewCubeXTypeClass((((TypeContext)_localctx).v!=null?((TypeContext)_localctx).v.getText():null), ttest?((TypeContext)_localctx).t.x:null);} catch(Exception e) { }
				}
				break;
			case THING:
				{
				setState(42); match(THING);
				 ((TypeContext)_localctx).x =  CubeXType.getThing();
				}
				break;
			case NOTHING:
				{
				setState(44); match(NOTHING);
				 ((TypeContext)_localctx).x =  CubeXType.getNothing();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(55);
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
					setState(48);
					if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "3 >= $_p");
					setState(49); match(AMP);
					setState(50); ((TypeContext)_localctx).t2 = type(4);
					 ((TypeContext)_localctx).x =  new CubeXTypeIntersection(((TypeContext)_localctx).t1.x,((TypeContext)_localctx).t2.x);
					}
					} 
				}
				setState(57);
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
			setState(74);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(59); match(LANGLE);
				setState(71);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << THING) | (1L << NOTHING) | (1L << NAMEUSINGLE) | (1L << NAMEU))) != 0)) {
					{
					setState(60); ((TypelistContext)_localctx).t = type(0);
					_localctx.x.add(((TypelistContext)_localctx).t.x);
					setState(68);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(62); match(COMMA);
						setState(63); ((TypelistContext)_localctx).t = type(0);
						_localctx.x.add(((TypelistContext)_localctx).t.x);
						}
						}
						setState(70);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(73); match(RANGLE);
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
			setState(91);
			_la = _input.LA(1);
			if (_la==LANGLE) {
				{
				setState(77); match(LANGLE);
				setState(88);
				_la = _input.LA(1);
				if (_la==NAMEUSINGLE) {
					{
					setState(78); ((TypevarlistContext)_localctx).v = match(NAMEUSINGLE);
					_localctx.x.add(new CubeXTypeVariable((((TypevarlistContext)_localctx).v!=null?((TypevarlistContext)_localctx).v.getText():null)));
					setState(85);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(80); match(COMMA);
						setState(81); ((TypevarlistContext)_localctx).v = match(NAMEUSINGLE);
						_localctx.x.add(new CubeXTypeVariable((((TypevarlistContext)_localctx).v!=null?((TypevarlistContext)_localctx).v.getText():null)));
						}
						}
						setState(87);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(90); match(RANGLE);
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
			setState(109);
			_la = _input.LA(1);
			if (_la==NAMEL) {
				{
				setState(94); ((ArglistContext)_localctx).v = match(NAMEL);
				setState(95); match(COLON);
				setState(96); ((ArglistContext)_localctx).t = type(0);
				_localctx.x.add(new CubeXArgument(new CubeXVariable((((ArglistContext)_localctx).v!=null?((ArglistContext)_localctx).v.getText():null)),((ArglistContext)_localctx).t.x));
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(98); match(COMMA);
					setState(99); ((ArglistContext)_localctx).v = match(NAMEL);
					setState(100); match(COLON);
					setState(101); ((ArglistContext)_localctx).t = type(0);
					_localctx.x.add(new CubeXArgument(new CubeXVariable((((ArglistContext)_localctx).v!=null?((ArglistContext)_localctx).v.getText():null)),((ArglistContext)_localctx).t.x));
					}
					}
					setState(108);
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
		public TypevarlistContext t;
		public ArglistContext alist;
		public TypeContext rt;
		public ArglistContext arglist() {
			return getRuleContext(ArglistContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CubeXParser.COLON, 0); }
		public TerminalNode RPAREN() { return getToken(CubeXParser.RPAREN, 0); }
		public TypevarlistContext typevarlist() {
			return getRuleContext(TypevarlistContext.class,0);
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
			setState(111); ((SchemeContext)_localctx).t = typevarlist();
			setState(112); match(LPAREN);
			setState(113); ((SchemeContext)_localctx).alist = arglist();
			setState(114); match(RPAREN);
			setState(115); match(COLON);
			setState(116); ((SchemeContext)_localctx).rt = type(0);
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
			setState(131);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << INT) | (1L << NAMEU) | (1L << NAMEL) | (1L << STRING) | (1L << BANG) | (1L << DASH) | (1L << LPAREN) | (1L << LBRACK))) != 0)) {
				{
				setState(120); ((ExprlistContext)_localctx).e = expr(0);
				_localctx.x.add(((ExprlistContext)_localctx).e.x);
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(122); match(COMMA);
					setState(123); ((ExprlistContext)_localctx).e = expr(0);
					_localctx.x.add(((ExprlistContext)_localctx).e.x);
					}
					}
					setState(130);
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
		public TerminalNode LESSDOTDOT() { return getToken(CubeXParser.LESSDOTDOT, 0); }
		public TerminalNode PLUSPLUS() { return getToken(CubeXParser.PLUSPLUS, 0); }
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
		public TerminalNode TRUE() { return getToken(CubeXParser.TRUE, 0); }
		public TerminalNode DOTLESS() { return getToken(CubeXParser.DOTLESS, 0); }
		public TypelistContext typelist() {
			return getRuleContext(TypelistContext.class,0);
		}
		public TerminalNode LESSLESS() { return getToken(CubeXParser.LESSLESS, 0); }
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
				setState(134);
				((ExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==BANG || _la==DASH) ) {
					((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(135); ((ExprContext)_localctx).e1 = expr(10);
				((ExprContext)_localctx).x =  (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==DASH ? new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"negative",null) : new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"negate",null);
				}
				break;

			case 2:
				{
				setState(138); match(LPAREN);
				setState(139); ((ExprContext)_localctx).e = expr(0);
				setState(140); match(RPAREN);
				((ExprContext)_localctx).x =  ((ExprContext)_localctx).e.x;
				}
				break;

			case 3:
				{
				setState(143); match(TRUE);
				((ExprContext)_localctx).x =  new CubeXBoolean(true);
				}
				break;

			case 4:
				{
				setState(145); match(FALSE);
				((ExprContext)_localctx).x =  new CubeXBoolean(false);
				}
				break;

			case 5:
				{
				setState(147); ((ExprContext)_localctx).INT = match(INT);
				((ExprContext)_localctx).x =  new CubeXInteger((((ExprContext)_localctx).INT!=null?Integer.valueOf(((ExprContext)_localctx).INT.getText()):0));
				}
				break;

			case 6:
				{
				setState(149); ((ExprContext)_localctx).STRING = match(STRING);
				((ExprContext)_localctx).x =  new CubeXString((((ExprContext)_localctx).STRING!=null?((ExprContext)_localctx).STRING.getText():null));
				}
				break;

			case 7:
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

			case 8:
				{
				setState(158); ((ExprContext)_localctx).v = match(NAMEL);
				((ExprContext)_localctx).x = new CubeXVariable((((ExprContext)_localctx).v!=null?((ExprContext)_localctx).v.getText():null));
				}
				break;

			case 9:
				{
				setState(160); match(LBRACK);
				setState(161); ((ExprContext)_localctx).elist = exprlist();
				setState(162); match(RBRACK);
				((ExprContext)_localctx).x =  new CubeXIterable(((ExprContext)_localctx).elist.x);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(221);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(219);
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
						((ExprContext)_localctx).x =  (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==STAR ? new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"times",((ExprContext)_localctx).e2.x) :
						          		(((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==SLASH ? new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"divide",((ExprContext)_localctx).e2.x) : new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"modulo",((ExprContext)_localctx).e2.x);
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
						((ExprContext)_localctx).x =  (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==PLUS ? new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"plus", ((ExprContext)_localctx).e2.x) : new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"minus",((ExprContext)_localctx).e2.x) ;
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
						((ExprContext)_localctx).x =  (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==DOTDOT ? new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"through",null, new ArrayList<CubeXExpression>(Arrays.asList(((ExprContext)_localctx).e2.x, new CubeXBoolean(true), new CubeXBoolean(true))))
						          		: (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==LESSDOT ? new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"through",null, new ArrayList<CubeXExpression>(Arrays.asList(((ExprContext)_localctx).e2.x, new CubeXBoolean(false), new CubeXBoolean(true)))) 
						          		: (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==DOTLESS ? new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"through",null, new ArrayList<CubeXExpression>(Arrays.asList(((ExprContext)_localctx).e2.x, new CubeXBoolean(true), new CubeXBoolean(false)))) 
						          		: new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"through",null, new ArrayList<CubeXExpression>(Arrays.asList(((ExprContext)_localctx).e2.x, new CubeXBoolean(false), new CubeXBoolean(false))));
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
						((ExprContext)_localctx).x =  (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==LANGLE ? new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"lessThan",null, new ArrayList<CubeXExpression>(Arrays.asList(((ExprContext)_localctx).e2.x, new CubeXBoolean(true))) )
						          		: (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==LANGLEEQUAL ? new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"lessThan",null, new ArrayList<CubeXExpression>(Arrays.asList(((ExprContext)_localctx).e2.x, new CubeXBoolean(false))))
						          		: (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==RANGLEEQUAL ? new CubeXFunctionCall(((ExprContext)_localctx).e2.x,"lessThan",null, new ArrayList<CubeXExpression>(Arrays.asList(((ExprContext)_localctx).e1.x, new CubeXBoolean(false))))
						          		: new CubeXFunctionCall(((ExprContext)_localctx).e2.x,"lessThan",null, new ArrayList<CubeXExpression>(Arrays.asList(((ExprContext)_localctx).e1.x, new CubeXBoolean(true))));
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
						((ExprContext)_localctx).x =  (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==EQUALEQUAL ? new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"equals", ((ExprContext)_localctx).e2.x) : new CubeXFunctionCall(new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"equals", ((ExprContext)_localctx).e2.x),"negate",null);
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
						((ExprContext)_localctx).x =  new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"and", ((ExprContext)_localctx).e2.x);
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
						((ExprContext)_localctx).x =  new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"or", ((ExprContext)_localctx).e2.x);
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

					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(216);
						if (!(7 >= _localctx._p)) throw new FailedPredicateException(this, "7 >= $_p");
						setState(217);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==DOTDOTDOT || _la==LESSDOTDOT) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						((ExprContext)_localctx).x =  (((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getType():0)==DOTDOTDOT ? new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"onwards",new CubeXBoolean(true)) : new CubeXFunctionCall(((ExprContext)_localctx).e1.x,"onwards",new CubeXBoolean(false));
						}
						break;
					}
					} 
				}
				setState(223);
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
		public TerminalNode YIELD() { return getToken(CubeXParser.YIELD, 0); }
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
			setState(281);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				((StatContext)_localctx).x =  new CubeXBlock();
				setState(225); match(LBRACE);
				setState(231);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << RETURN) | (1L << YIELD) | (1L << NAMEL) | (1L << LBRACE))) != 0)) {
					{
					{
					setState(226); ((StatContext)_localctx).s = stat();
					((CubeXBlock)_localctx.x).add(((StatContext)_localctx).s.x);
					}
					}
					setState(233);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(234); match(RBRACE);
				((StatContext)_localctx).x = ((CubeXBlock)_localctx.x).reduceBlock();
				}
				break;
			case NAMEL:
				enterOuterAlt(_localctx, 2);
				{
				setState(236); ((StatContext)_localctx).v = match(NAMEL);
				setState(237); match(COLONEQUAL);
				setState(238); ((StatContext)_localctx).e1 = expr(0);
				setState(239); match(SEMICOLON);
				((StatContext)_localctx).x =  new CubeXAssignment((((StatContext)_localctx).v!=null?((StatContext)_localctx).v.getText():null), ((StatContext)_localctx).e1.x);
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				boolean elsetest=false;
				setState(243); match(IF);
				setState(244); match(LPAREN);
				setState(245); ((StatContext)_localctx).e1 = expr(0);
				setState(246); match(RPAREN);
				setState(247); ((StatContext)_localctx).s1 = stat();
				setState(251);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					elsetest=true;
					setState(249); match(ELSE);
					setState(250); ((StatContext)_localctx).s2 = stat();
					}
					break;
				}
				((StatContext)_localctx).x =  new CubeXIfStatement(((StatContext)_localctx).e1.x, ((StatContext)_localctx).s1.x, elsetest?((StatContext)_localctx).s2.x:null);
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 4);
				{
				setState(255); match(WHILE);
				setState(256); match(LPAREN);
				setState(257); ((StatContext)_localctx).e = expr(0);
				setState(258); match(RPAREN);
				setState(259); ((StatContext)_localctx).s = stat();
				((StatContext)_localctx).x =  new CubeXWhileStatement(((StatContext)_localctx).e.x, ((StatContext)_localctx).s.x);
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 5);
				{
				setState(262); match(FOR);
				setState(263); match(LPAREN);
				setState(264); ((StatContext)_localctx).v = match(NAMEL);
				setState(265); match(IN);
				setState(266); ((StatContext)_localctx).e = expr(0);
				setState(267); match(RPAREN);
				setState(268); ((StatContext)_localctx).s = stat();
				((StatContext)_localctx).x =  new CubeXForStatement((((StatContext)_localctx).v!=null?((StatContext)_localctx).v.getText():null), ((StatContext)_localctx).e.x, ((StatContext)_localctx).s.x);
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 6);
				{
				setState(271); match(RETURN);
				setState(272); ((StatContext)_localctx).e = expr(0);
				setState(273); match(SEMICOLON);
				((StatContext)_localctx).x =  new CubeXReturnStatement(((StatContext)_localctx).e.x) ;
				}
				break;
			case YIELD:
				enterOuterAlt(_localctx, 7);
				{
				setState(276); match(YIELD);
				setState(277); ((StatContext)_localctx).e = expr(0);
				setState(278); match(SEMICOLON);
				((StatContext)_localctx).x =  new CubeXYieldStatement(((StatContext)_localctx).e.x) ;
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
		public CubeXFunctionHeader x;
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
			setState(283); match(FUN);
			setState(284); ((FunctiondeclContext)_localctx).v = match(NAMEL);
			setState(285); ((FunctiondeclContext)_localctx).sch = scheme();
			((FunctiondeclContext)_localctx).x = new CubeXFunctionHeader((((FunctiondeclContext)_localctx).v!=null?((FunctiondeclContext)_localctx).v.getText():null), ((FunctiondeclContext)_localctx).sch.x);
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
			setState(298);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(288); ((FunctionContext)_localctx).d = functiondecl();
				setState(289); match(EQUAL);
				setState(290); ((FunctionContext)_localctx).e = expr(0);
				setState(291); match(SEMICOLON);
				((FunctionContext)_localctx).x =  new CubeXFunction(((FunctionContext)_localctx).d.x,((FunctionContext)_localctx).e.x);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(294); ((FunctionContext)_localctx).d = functiondecl();
				setState(295); ((FunctionContext)_localctx).s = stat();
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

	public static class YieldfunContext extends ParserRuleContext {
		public CubeXYielder x;
		public StatContext s;
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode YIELDER() { return getToken(CubeXParser.YIELDER, 0); }
		public YieldfunContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yieldfun; }
	}

	public final YieldfunContext yieldfun() throws RecognitionException {
		YieldfunContext _localctx = new YieldfunContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_yieldfun);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300); match(YIELDER);
			setState(301); ((YieldfunContext)_localctx).s = stat();
			 ((YieldfunContext)_localctx).x = new CubeXYielder(((YieldfunContext)_localctx).s.x); 
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
		enterRule(_localctx, 22, RULE_interfacex3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			boolean extttest=false;
			setState(305); match(INTERFACE);
			setState(306); ((Interfacex3Context)_localctx).n = match(NAMEU);
			setState(308);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(307); ((Interfacex3Context)_localctx).tvlist = typevarlist();
				}
				break;
			}
			setState(314);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(310); match(EXTENDS);
				setState(311); ((Interfacex3Context)_localctx).extt = type(0);
				extttest=true;
				}
			}

			setState(316); match(LBRACE);
			ArrayList<CubeXFunction> decls = new ArrayList<CubeXFunction>();
			setState(327);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FUN) {
				{
				setState(325);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					{
					setState(318); ((Interfacex3Context)_localctx).decl = functiondecl();
					setState(319); match(SEMICOLON);
					decls.add(new CubeXFunction(((Interfacex3Context)_localctx).decl.x));
					}
					}
					break;

				case 2:
					{
					{
					setState(322); ((Interfacex3Context)_localctx).f = function();
					decls.add(((Interfacex3Context)_localctx).f.x);
					}
					}
					break;
				}
				}
				setState(329);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(330); match(RBRACE);
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

	public static class YielderContext extends ParserRuleContext {
		public CubeXClassYielder x;
		public Token n;
		public TypevarlistContext tvlist;
		public ArglistContext alist;
		public TypelistContext t;
		public TypelistContext typelist;
		public StatContext s;
		public ExprlistContext superlist;
		public YieldfunContext y;
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
		public YieldfunContext yieldfun() {
			return getRuleContext(YieldfunContext.class,0);
		}
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
		public TypelistContext typelist() {
			return getRuleContext(TypelistContext.class,0);
		}
		public ExprlistContext exprlist() {
			return getRuleContext(ExprlistContext.class,0);
		}
		public TerminalNode EXTENDSITERABLE() { return getToken(CubeXParser.EXTENDSITERABLE, 0); }
		public List<TerminalNode> LPAREN() { return getTokens(CubeXParser.LPAREN); }
		public TerminalNode NAMEU() { return getToken(CubeXParser.NAMEU, 0); }
		public YielderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yielder; }
	}

	public final YielderContext yielder() throws RecognitionException {
		YielderContext _localctx = new YielderContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_yielder);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333); match(CLASS);
			setState(334); ((YielderContext)_localctx).n = match(NAMEU);
			setState(336);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(335); ((YielderContext)_localctx).tvlist = typevarlist();
				}
				break;
			}
			setState(338); match(LPAREN);
			setState(339); ((YielderContext)_localctx).alist = arglist();
			setState(340); match(RPAREN);
			setState(341); match(EXTENDSITERABLE);
			setState(342); ((YielderContext)_localctx).t = ((YielderContext)_localctx).typelist = typelist();
			setState(343); match(LBRACE);
			ArrayList<CubeXStatement> stats = new ArrayList<CubeXStatement>();
			setState(350);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << RETURN) | (1L << YIELD) | (1L << NAMEL) | (1L << LBRACE))) != 0)) {
				{
				{
				setState(345); ((YielderContext)_localctx).s = stat();
				stats.add(((YielderContext)_localctx).s.x);
				}
				}
				setState(352);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			boolean supertest=false;
			setState(361);
			_la = _input.LA(1);
			if (_la==SUPER) {
				{
				setState(354); match(SUPER);
				setState(355); match(LPAREN);
				setState(356); ((YielderContext)_localctx).superlist = exprlist();
				setState(357); match(RPAREN);
				setState(358); match(SEMICOLON);
				supertest=true;
				}
			}

			setState(363); ((YielderContext)_localctx).y = yieldfun();
			ArrayList<CubeXFunction> funs = new ArrayList<CubeXFunction>();
			setState(370);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FUN) {
				{
				{
				setState(365); ((YielderContext)_localctx).f = function();
				funs.add(((YielderContext)_localctx).f.x);
				}
				}
				setState(372);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(373); match(RBRACE);
			((YielderContext)_localctx).x =  new CubeXClassYielder((((YielderContext)_localctx).n!=null?((YielderContext)_localctx).n.getText():null), ((YielderContext)_localctx).tvlist.x, ((YielderContext)_localctx).alist.x, ((YielderContext)_localctx).typelist.x, stats, supertest?((YielderContext)_localctx).superlist.x:null, ((YielderContext)_localctx).y.x, funs);
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
		enterRule(_localctx, 26, RULE_classx3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376); match(CLASS);
			setState(377); ((Classx3Context)_localctx).n = match(NAMEU);
			setState(379);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				setState(378); ((Classx3Context)_localctx).tvlist = typevarlist();
				}
				break;
			}
			setState(381); match(LPAREN);
			setState(382); ((Classx3Context)_localctx).alist = arglist();
			setState(383); match(RPAREN);
			boolean extttest=false;
			setState(389);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(385); match(EXTENDS);
				setState(386); ((Classx3Context)_localctx).extt = type(0);
				extttest=true;
				}
			}

			setState(391); match(LBRACE);
			ArrayList<CubeXStatement> stats = new ArrayList<CubeXStatement>();
			setState(398);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << RETURN) | (1L << YIELD) | (1L << NAMEL) | (1L << LBRACE))) != 0)) {
				{
				{
				setState(393); ((Classx3Context)_localctx).s = stat();
				stats.add(((Classx3Context)_localctx).s.x);
				}
				}
				setState(400);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			boolean supertest=false;
			setState(409);
			_la = _input.LA(1);
			if (_la==SUPER) {
				{
				setState(402); match(SUPER);
				setState(403); match(LPAREN);
				setState(404); ((Classx3Context)_localctx).superlist = exprlist();
				setState(405); match(RPAREN);
				setState(406); match(SEMICOLON);
				supertest=true;
				}
			}

			ArrayList<CubeXFunction> funs = new ArrayList<CubeXFunction>();
			setState(417);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FUN) {
				{
				{
				setState(412); ((Classx3Context)_localctx).f = function();
				funs.add(((Classx3Context)_localctx).f.x);
				}
				}
				setState(419);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(420); match(RBRACE);
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
		enterRule(_localctx, 28, RULE_testprogram);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423); ((TestprogramContext)_localctx).p = program();
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
		public YielderContext y;
		public Classx3Context c;
		public List<Classx3Context> classx3() {
			return getRuleContexts(Classx3Context.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public YielderContext yielder(int i) {
			return getRuleContext(YielderContext.class,i);
		}
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
		public List<YielderContext> yielder() {
			return getRuleContexts(YielderContext.class);
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
		enterRule(_localctx, 30, RULE_program);
		try {
			int _alt;
			setState(485);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				((ProgramContext)_localctx).x =  new CubeXProgram();
				setState(427); ((ProgramContext)_localctx).s = stat();
				_localctx.x.addPiece(((ProgramContext)_localctx).s.x);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				((ProgramContext)_localctx).x =  new CubeXProgram();
				setState(434); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(431); ((ProgramContext)_localctx).s = stat();
						_localctx.x.addPiece(((ProgramContext)_localctx).s.x);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(436); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				} while ( _alt!=2 && _alt!=-1 );
				setState(438); ((ProgramContext)_localctx).p = program();
				_localctx.x.addPieces(((ProgramContext)_localctx).p.x);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				((ProgramContext)_localctx).x =  new CubeXProgram();
				setState(445); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(442); ((ProgramContext)_localctx).f = function();
						_localctx.x.addPiece(((ProgramContext)_localctx).f.x);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(447); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				} while ( _alt!=2 && _alt!=-1 );
				setState(449); ((ProgramContext)_localctx).p = program();
				_localctx.x.addPieces(((ProgramContext)_localctx).p.x);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				((ProgramContext)_localctx).x =  new CubeXProgram();
				setState(456); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(453); ((ProgramContext)_localctx).i = interfacex3();
						_localctx.x.addPiece(((ProgramContext)_localctx).i.x);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(458); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
				} while ( _alt!=2 && _alt!=-1 );
				setState(460); ((ProgramContext)_localctx).p = program();
				_localctx.x.addPieces(((ProgramContext)_localctx).p.x);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				((ProgramContext)_localctx).x =  new CubeXProgram();
				setState(467); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(464); ((ProgramContext)_localctx).y = yielder();
						_localctx.x.addPiece(((ProgramContext)_localctx).y.x);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(469); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
				} while ( _alt!=2 && _alt!=-1 );
				setState(471); ((ProgramContext)_localctx).p = program();
				_localctx.x.addPieces(((ProgramContext)_localctx).p.x);
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				((ProgramContext)_localctx).x =  new CubeXProgram();
				setState(478); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(475); ((ProgramContext)_localctx).c = classx3();
						_localctx.x.addPiece(((ProgramContext)_localctx).c.x);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(480); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
				} while ( _alt!=2 && _alt!=-1 );
				setState(482); ((ProgramContext)_localctx).p = program();
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

		case 10: return 7 >= _localctx._p;
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
		"\2\3=\u01ea\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20"+
		"\4\21\t\21\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2*\n\2\3\2\3\2\3\2\3\2\3\2\5"+
		"\2\61\n\2\3\2\3\2\3\2\3\2\3\2\7\28\n\2\f\2\16\2;\13\2\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\7\3E\n\3\f\3\16\3H\13\3\5\3J\n\3\3\3\5\3M\n\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\7\4V\n\4\f\4\16\4Y\13\4\5\4[\n\4\3\4\5\4^\n\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5k\n\5\f\5\16\5n\13\5\5\5p"+
		"\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u0081"+
		"\n\7\f\7\16\7\u0084\13\7\5\7\u0086\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00a8\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00de\n\b\f\b\16\b\u00e1\13"+
		"\b\3\t\3\t\3\t\3\t\3\t\7\t\u00e8\n\t\f\t\16\t\u00eb\13\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00fe\n\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u011c\n\t\3\n\3\n\3\n\3\n"+
		"\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u012d\n\13"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\5\r\u0137\n\r\3\r\3\r\3\r\3\r\5\r\u013d"+
		"\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u0148\n\r\f\r\16\r\u014b"+
		"\13\r\3\r\3\r\3\r\3\16\3\16\3\16\5\16\u0153\n\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\7\16\u015f\n\16\f\16\16\16\u0162\13\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u016c\n\16\3\16\3\16\3\16"+
		"\3\16\3\16\7\16\u0173\n\16\f\16\16\16\u0176\13\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\5\17\u017e\n\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17"+
		"\u0188\n\17\3\17\3\17\3\17\3\17\3\17\7\17\u018f\n\17\f\17\16\17\u0192"+
		"\13\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u019c\n\17\3\17\3"+
		"\17\3\17\3\17\7\17\u01a2\n\17\f\17\16\17\u01a5\13\17\3\17\3\17\3\17\3"+
		"\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\6\21\u01b5\n\21"+
		"\r\21\16\21\u01b6\3\21\3\21\3\21\3\21\3\21\3\21\3\21\6\21\u01c0\n\21\r"+
		"\21\16\21\u01c1\3\21\3\21\3\21\3\21\3\21\3\21\3\21\6\21\u01cb\n\21\r\21"+
		"\16\21\u01cc\3\21\3\21\3\21\3\21\3\21\3\21\3\21\6\21\u01d6\n\21\r\21\16"+
		"\21\u01d7\3\21\3\21\3\21\3\21\3\21\3\21\3\21\6\21\u01e1\n\21\r\21\16\21"+
		"\u01e2\3\21\3\21\3\21\5\21\u01e8\n\21\3\21\2\22\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \2\n\4\35\35\"\"\3\27\30\3\36 \3!\"\3#&\3+.\3/\60\3\'"+
		"(\u021a\2\60\3\2\2\2\4<\3\2\2\2\6N\3\2\2\2\b_\3\2\2\2\nq\3\2\2\2\fy\3"+
		"\2\2\2\16\u00a7\3\2\2\2\20\u011b\3\2\2\2\22\u011d\3\2\2\2\24\u012c\3\2"+
		"\2\2\26\u012e\3\2\2\2\30\u0132\3\2\2\2\32\u014f\3\2\2\2\34\u017a\3\2\2"+
		"\2\36\u01a9\3\2\2\2 \u01e7\3\2\2\2\"#\b\2\1\2#$\7\26\2\2$\61\b\2\1\2%"+
		"&\b\2\1\2&)\7\27\2\2\'(\b\2\1\2(*\5\4\3\2)\'\3\2\2\2)*\3\2\2\2*+\3\2\2"+
		"\2+\61\b\2\1\2,-\7\21\2\2-\61\b\2\1\2./\7\22\2\2/\61\b\2\1\2\60\"\3\2"+
		"\2\2\60%\3\2\2\2\60,\3\2\2\2\60.\3\2\2\2\619\3\2\2\2\62\63\6\2\2\3\63"+
		"\64\7\63\2\2\64\65\5\2\2\2\65\66\b\2\1\2\668\3\2\2\2\67\62\3\2\2\28;\3"+
		"\2\2\29\67\3\2\2\29:\3\2\2\2:\3\3\2\2\2;9\3\2\2\2<L\b\3\1\2=I\7+\2\2>"+
		"?\5\2\2\2?F\b\3\1\2@A\7*\2\2AB\5\2\2\2BC\b\3\1\2CE\3\2\2\2D@\3\2\2\2E"+
		"H\3\2\2\2FD\3\2\2\2FG\3\2\2\2GJ\3\2\2\2HF\3\2\2\2I>\3\2\2\2IJ\3\2\2\2"+
		"JK\3\2\2\2KM\7.\2\2L=\3\2\2\2LM\3\2\2\2M\5\3\2\2\2N]\b\4\1\2OZ\7+\2\2"+
		"PQ\7\26\2\2QW\b\4\1\2RS\7*\2\2ST\7\26\2\2TV\b\4\1\2UR\3\2\2\2VY\3\2\2"+
		"\2WU\3\2\2\2WX\3\2\2\2X[\3\2\2\2YW\3\2\2\2ZP\3\2\2\2Z[\3\2\2\2[\\\3\2"+
		"\2\2\\^\7.\2\2]O\3\2\2\2]^\3\2\2\2^\7\3\2\2\2_o\b\5\1\2`a\7\30\2\2ab\7"+
		"\32\2\2bc\5\2\2\2cl\b\5\1\2de\7*\2\2ef\7\30\2\2fg\7\32\2\2gh\5\2\2\2h"+
		"i\b\5\1\2ik\3\2\2\2jd\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mp\3\2\2\2"+
		"nl\3\2\2\2o`\3\2\2\2op\3\2\2\2p\t\3\2\2\2qr\5\6\4\2rs\7\65\2\2st\5\b\5"+
		"\2tu\7\66\2\2uv\7\32\2\2vw\5\2\2\2wx\b\6\1\2x\13\3\2\2\2y\u0085\b\7\1"+
		"\2z{\5\16\b\2{\u0082\b\7\1\2|}\7*\2\2}~\5\16\b\2~\177\b\7\1\2\177\u0081"+
		"\3\2\2\2\u0080|\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082"+
		"\u0083\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0085z\3\2\2\2"+
		"\u0085\u0086\3\2\2\2\u0086\r\3\2\2\2\u0087\u0088\b\b\1\2\u0088\u0089\t"+
		"\2\2\2\u0089\u008a\5\16\b\2\u008a\u008b\b\b\1\2\u008b\u00a8\3\2\2\2\u008c"+
		"\u008d\7\65\2\2\u008d\u008e\5\16\b\2\u008e\u008f\7\66\2\2\u008f\u0090"+
		"\b\b\1\2\u0090\u00a8\3\2\2\2\u0091\u0092\7\3\2\2\u0092\u00a8\b\b\1\2\u0093"+
		"\u0094\7\4\2\2\u0094\u00a8\b\b\1\2\u0095\u0096\7\25\2\2\u0096\u00a8\b"+
		"\b\1\2\u0097\u0098\7\31\2\2\u0098\u00a8\b\b\1\2\u0099\u009a\t\3\2\2\u009a"+
		"\u009b\5\4\3\2\u009b\u009c\7\65\2\2\u009c\u009d\5\f\7\2\u009d\u009e\7"+
		"\66\2\2\u009e\u009f\b\b\1\2\u009f\u00a8\3\2\2\2\u00a0\u00a1\7\30\2\2\u00a1"+
		"\u00a8\b\b\1\2\u00a2\u00a3\7\67\2\2\u00a3\u00a4\5\f\7\2\u00a4\u00a5\7"+
		"8\2\2\u00a5\u00a6\b\b\1\2\u00a6\u00a8\3\2\2\2\u00a7\u0087\3\2\2\2\u00a7"+
		"\u008c\3\2\2\2\u00a7\u0091\3\2\2\2\u00a7\u0093\3\2\2\2\u00a7\u0095\3\2"+
		"\2\2\u00a7\u0097\3\2\2\2\u00a7\u0099\3\2\2\2\u00a7\u00a0\3\2\2\2\u00a7"+
		"\u00a2\3\2\2\2\u00a8\u00df\3\2\2\2\u00a9\u00aa\6\b\3\3\u00aa\u00ab\t\4"+
		"\2\2\u00ab\u00ac\5\16\b\2\u00ac\u00ad\b\b\1\2\u00ad\u00de\3\2\2\2\u00ae"+
		"\u00af\6\b\4\3\u00af\u00b0\t\5\2\2\u00b0\u00b1\5\16\b\2\u00b1\u00b2\b"+
		"\b\1\2\u00b2\u00de\3\2\2\2\u00b3\u00b4\6\b\5\3\u00b4\u00b5\t\6\2\2\u00b5"+
		"\u00b6\5\16\b\2\u00b6\u00b7\b\b\1\2\u00b7\u00de\3\2\2\2\u00b8\u00b9\6"+
		"\b\6\3\u00b9\u00ba\7\34\2\2\u00ba\u00bb\5\16\b\2\u00bb\u00bc\b\b\1\2\u00bc"+
		"\u00de\3\2\2\2\u00bd\u00be\6\b\7\3\u00be\u00bf\t\7\2\2\u00bf\u00c0\5\16"+
		"\b\2\u00c0\u00c1\b\b\1\2\u00c1\u00de\3\2\2\2\u00c2\u00c3\6\b\b\3\u00c3"+
		"\u00c4\t\b\2\2\u00c4\u00c5\5\16\b\2\u00c5\u00c6\b\b\1\2\u00c6\u00de\3"+
		"\2\2\2\u00c7\u00c8\6\b\t\3\u00c8\u00c9\7\63\2\2\u00c9\u00ca\5\16\b\2\u00ca"+
		"\u00cb\b\b\1\2\u00cb\u00de\3\2\2\2\u00cc\u00cd\6\b\n\3\u00cd\u00ce\7\64"+
		"\2\2\u00ce\u00cf\5\16\b\2\u00cf\u00d0\b\b\1\2\u00d0\u00de\3\2\2\2\u00d1"+
		"\u00d2\6\b\13\3\u00d2\u00d3\7)\2\2\u00d3\u00d4\7\30\2\2\u00d4\u00d5\5"+
		"\4\3\2\u00d5\u00d6\7\65\2\2\u00d6\u00d7\5\f\7\2\u00d7\u00d8\7\66\2\2\u00d8"+
		"\u00d9\b\b\1\2\u00d9\u00de\3\2\2\2\u00da\u00db\6\b\f\3\u00db\u00dc\t\t"+
		"\2\2\u00dc\u00de\b\b\1\2\u00dd\u00a9\3\2\2\2\u00dd\u00ae\3\2\2\2\u00dd"+
		"\u00b3\3\2\2\2\u00dd\u00b8\3\2\2\2\u00dd\u00bd\3\2\2\2\u00dd\u00c2\3\2"+
		"\2\2\u00dd\u00c7\3\2\2\2\u00dd\u00cc\3\2\2\2\u00dd\u00d1\3\2\2\2\u00dd"+
		"\u00da\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2"+
		"\2\2\u00e0\17\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2\u00e3\b\t\1\2\u00e3\u00e9"+
		"\79\2\2\u00e4\u00e5\5\20\t\2\u00e5\u00e6\b\t\1\2\u00e6\u00e8\3\2\2\2\u00e7"+
		"\u00e4\3\2\2\2\u00e8\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2"+
		"\2\2\u00ea\u00ec\3\2\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00ed\7:\2\2\u00ed"+
		"\u011c\b\t\1\2\u00ee\u00ef\7\30\2\2\u00ef\u00f0\7\62\2\2\u00f0\u00f1\5"+
		"\16\b\2\u00f1\u00f2\7\33\2\2\u00f2\u00f3\b\t\1\2\u00f3\u011c\3\2\2\2\u00f4"+
		"\u00f5\b\t\1\2\u00f5\u00f6\7\5\2\2\u00f6\u00f7\7\65\2\2\u00f7\u00f8\5"+
		"\16\b\2\u00f8\u00f9\7\66\2\2\u00f9\u00fd\5\20\t\2\u00fa\u00fb\b\t\1\2"+
		"\u00fb\u00fc\7\6\2\2\u00fc\u00fe\5\20\t\2\u00fd\u00fa\3\2\2\2\u00fd\u00fe"+
		"\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\b\t\1\2\u0100\u011c\3\2\2\2\u0101"+
		"\u0102\7\7\2\2\u0102\u0103\7\65\2\2\u0103\u0104\5\16\b\2\u0104\u0105\7"+
		"\66\2\2\u0105\u0106\5\20\t\2\u0106\u0107\b\t\1\2\u0107\u011c\3\2\2\2\u0108"+
		"\u0109\7\b\2\2\u0109\u010a\7\65\2\2\u010a\u010b\7\30\2\2\u010b\u010c\7"+
		"\t\2\2\u010c\u010d\5\16\b\2\u010d\u010e\7\66\2\2\u010e\u010f\5\20\t\2"+
		"\u010f\u0110\b\t\1\2\u0110\u011c\3\2\2\2\u0111\u0112\7\n\2\2\u0112\u0113"+
		"\5\16\b\2\u0113\u0114\7\33\2\2\u0114\u0115\b\t\1\2\u0115\u011c\3\2\2\2"+
		"\u0116\u0117\7\24\2\2\u0117\u0118\5\16\b\2\u0118\u0119\7\33\2\2\u0119"+
		"\u011a\b\t\1\2\u011a\u011c\3\2\2\2\u011b\u00e2\3\2\2\2\u011b\u00ee\3\2"+
		"\2\2\u011b\u00f4\3\2\2\2\u011b\u0101\3\2\2\2\u011b\u0108\3\2\2\2\u011b"+
		"\u0111\3\2\2\2\u011b\u0116\3\2\2\2\u011c\21\3\2\2\2\u011d\u011e\7\f\2"+
		"\2\u011e\u011f\7\30\2\2\u011f\u0120\5\n\6\2\u0120\u0121\b\n\1\2\u0121"+
		"\23\3\2\2\2\u0122\u0123\5\22\n\2\u0123\u0124\7\61\2\2\u0124\u0125\5\16"+
		"\b\2\u0125\u0126\7\33\2\2\u0126\u0127\b\13\1\2\u0127\u012d\3\2\2\2\u0128"+
		"\u0129\5\22\n\2\u0129\u012a\5\20\t\2\u012a\u012b\b\13\1\2\u012b\u012d"+
		"\3\2\2\2\u012c\u0122\3\2\2\2\u012c\u0128\3\2\2\2\u012d\25\3\2\2\2\u012e"+
		"\u012f\7\23\2\2\u012f\u0130\5\20\t\2\u0130\u0131\b\f\1\2\u0131\27\3\2"+
		"\2\2\u0132\u0133\b\r\1\2\u0133\u0134\7\13\2\2\u0134\u0136\7\27\2\2\u0135"+
		"\u0137\5\6\4\2\u0136\u0135\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u013c\3\2"+
		"\2\2\u0138\u0139\7\20\2\2\u0139\u013a\5\2\2\2\u013a\u013b\b\r\1\2\u013b"+
		"\u013d\3\2\2\2\u013c\u0138\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013e\3\2"+
		"\2\2\u013e\u013f\79\2\2\u013f\u0149\b\r\1\2\u0140\u0141\5\22\n\2\u0141"+
		"\u0142\7\33\2\2\u0142\u0143\b\r\1\2\u0143\u0148\3\2\2\2\u0144\u0145\5"+
		"\24\13\2\u0145\u0146\b\r\1\2\u0146\u0148\3\2\2\2\u0147\u0140\3\2\2\2\u0147"+
		"\u0144\3\2\2\2\u0148\u014b\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2"+
		"\2\2\u014a\u014c\3\2\2\2\u014b\u0149\3\2\2\2\u014c\u014d\7:\2\2\u014d"+
		"\u014e\b\r\1\2\u014e\31\3\2\2\2\u014f\u0150\7\16\2\2\u0150\u0152\7\27"+
		"\2\2\u0151\u0153\5\6\4\2\u0152\u0151\3\2\2\2\u0152\u0153\3\2\2\2\u0153"+
		"\u0154\3\2\2\2\u0154\u0155\7\65\2\2\u0155\u0156\5\b\5\2\u0156\u0157\7"+
		"\66\2\2\u0157\u0158\7\17\2\2\u0158\u0159\5\4\3\2\u0159\u015a\79\2\2\u015a"+
		"\u0160\b\16\1\2\u015b\u015c\5\20\t\2\u015c\u015d\b\16\1\2\u015d\u015f"+
		"\3\2\2\2\u015e\u015b\3\2\2\2\u015f\u0162\3\2\2\2\u0160\u015e\3\2\2\2\u0160"+
		"\u0161\3\2\2\2\u0161\u0163\3\2\2\2\u0162\u0160\3\2\2\2\u0163\u016b\b\16"+
		"\1\2\u0164\u0165\7\r\2\2\u0165\u0166\7\65\2\2\u0166\u0167\5\f\7\2\u0167"+
		"\u0168\7\66\2\2\u0168\u0169\7\33\2\2\u0169\u016a\b\16\1\2\u016a\u016c"+
		"\3\2\2\2\u016b\u0164\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u016d\3\2\2\2\u016d"+
		"\u016e\5\26\f\2\u016e\u0174\b\16\1\2\u016f\u0170\5\24\13\2\u0170\u0171"+
		"\b\16\1\2\u0171\u0173\3\2\2\2\u0172\u016f\3\2\2\2\u0173\u0176\3\2\2\2"+
		"\u0174\u0172\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0177\3\2\2\2\u0176\u0174"+
		"\3\2\2\2\u0177\u0178\7:\2\2\u0178\u0179\b\16\1\2\u0179\33\3\2\2\2\u017a"+
		"\u017b\7\16\2\2\u017b\u017d\7\27\2\2\u017c\u017e\5\6\4\2\u017d\u017c\3"+
		"\2\2\2\u017d\u017e\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u0180\7\65\2\2\u0180"+
		"\u0181\5\b\5\2\u0181\u0182\7\66\2\2\u0182\u0187\b\17\1\2\u0183\u0184\7"+
		"\20\2\2\u0184\u0185\5\2\2\2\u0185\u0186\b\17\1\2\u0186\u0188\3\2\2\2\u0187"+
		"\u0183\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u0189\3\2\2\2\u0189\u018a\79"+
		"\2\2\u018a\u0190\b\17\1\2\u018b\u018c\5\20\t\2\u018c\u018d\b\17\1\2\u018d"+
		"\u018f\3\2\2\2\u018e\u018b\3\2\2\2\u018f\u0192\3\2\2\2\u0190\u018e\3\2"+
		"\2\2\u0190\u0191\3\2\2\2\u0191\u0193\3\2\2\2\u0192\u0190\3\2\2\2\u0193"+
		"\u019b\b\17\1\2\u0194\u0195\7\r\2\2\u0195\u0196\7\65\2\2\u0196\u0197\5"+
		"\f\7\2\u0197\u0198\7\66\2\2\u0198\u0199\7\33\2\2\u0199\u019a\b\17\1\2"+
		"\u019a\u019c\3\2\2\2\u019b\u0194\3\2\2\2\u019b\u019c\3\2\2\2\u019c\u019d"+
		"\3\2\2\2\u019d\u01a3\b\17\1\2\u019e\u019f\5\24\13\2\u019f\u01a0\b\17\1"+
		"\2\u01a0\u01a2\3\2\2\2\u01a1\u019e\3\2\2\2\u01a2\u01a5\3\2\2\2\u01a3\u01a1"+
		"\3\2\2\2\u01a3\u01a4\3\2\2\2\u01a4\u01a6\3\2\2\2\u01a5\u01a3\3\2\2\2\u01a6"+
		"\u01a7\7:\2\2\u01a7\u01a8\b\17\1\2\u01a8\35\3\2\2\2\u01a9\u01aa\5 \21"+
		"\2\u01aa\u01ab\b\20\1\2\u01ab\37\3\2\2\2\u01ac\u01ad\b\21\1\2\u01ad\u01ae"+
		"\5\20\t\2\u01ae\u01af\b\21\1\2\u01af\u01e8\3\2\2\2\u01b0\u01b4\b\21\1"+
		"\2\u01b1\u01b2\5\20\t\2\u01b2\u01b3\b\21\1\2\u01b3\u01b5\3\2\2\2\u01b4"+
		"\u01b1\3\2\2\2\u01b5\u01b6\3\2\2\2\u01b6\u01b4\3\2\2\2\u01b6\u01b7\3\2"+
		"\2\2\u01b7\u01b8\3\2\2\2\u01b8\u01b9\5 \21\2\u01b9\u01ba\b\21\1\2\u01ba"+
		"\u01e8\3\2\2\2\u01bb\u01bf\b\21\1\2\u01bc\u01bd\5\24\13\2\u01bd\u01be"+
		"\b\21\1\2\u01be\u01c0\3\2\2\2\u01bf\u01bc\3\2\2\2\u01c0\u01c1\3\2\2\2"+
		"\u01c1\u01bf\3\2\2\2\u01c1\u01c2\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c4"+
		"\5 \21\2\u01c4\u01c5\b\21\1\2\u01c5\u01e8\3\2\2\2\u01c6\u01ca\b\21\1\2"+
		"\u01c7\u01c8\5\30\r\2\u01c8\u01c9\b\21\1\2\u01c9\u01cb\3\2\2\2\u01ca\u01c7"+
		"\3\2\2\2\u01cb\u01cc\3\2\2\2\u01cc\u01ca\3\2\2\2\u01cc\u01cd\3\2\2\2\u01cd"+
		"\u01ce\3\2\2\2\u01ce\u01cf\5 \21\2\u01cf\u01d0\b\21\1\2\u01d0\u01e8\3"+
		"\2\2\2\u01d1\u01d5\b\21\1\2\u01d2\u01d3\5\32\16\2\u01d3\u01d4\b\21\1\2"+
		"\u01d4\u01d6\3\2\2\2\u01d5\u01d2\3\2\2\2\u01d6\u01d7\3\2\2\2\u01d7\u01d5"+
		"\3\2\2\2\u01d7\u01d8\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d9\u01da\5 \21\2\u01da"+
		"\u01db\b\21\1\2\u01db\u01e8\3\2\2\2\u01dc\u01e0\b\21\1\2\u01dd\u01de\5"+
		"\34\17\2\u01de\u01df\b\21\1\2\u01df\u01e1\3\2\2\2\u01e0\u01dd\3\2\2\2"+
		"\u01e1\u01e2\3\2\2\2\u01e2\u01e0\3\2\2\2\u01e2\u01e3\3\2\2\2\u01e3\u01e4"+
		"\3\2\2\2\u01e4\u01e5\5 \21\2\u01e5\u01e6\b\21\1\2\u01e6\u01e8\3\2\2\2"+
		"\u01e7\u01ac\3\2\2\2\u01e7\u01b0\3\2\2\2\u01e7\u01bb\3\2\2\2\u01e7\u01c6"+
		"\3\2\2\2\u01e7\u01d1\3\2\2\2\u01e7\u01dc\3\2\2\2\u01e8!\3\2\2\2))\609"+
		"FILWZ]lo\u0082\u0085\u00a7\u00dd\u00df\u00e9\u00fd\u011b\u012c\u0136\u013c"+
		"\u0147\u0149\u0152\u0160\u016b\u0174\u017d\u0187\u0190\u019b\u01a3\u01b6"+
		"\u01c1\u01cc\u01d7\u01e2\u01e7";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}