// Generated from CubeXLexer.g4 by ANTLR 4.0

package main.util;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CubeXLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TRUE=1, FALSE=2, IF=3, ELSE=4, WHILE=5, FOR=6, IN=7, RETURN=8, INTERFACE=9, 
		FUN=10, SUPER=11, CLASS=12, EXTENDS=13, THING=14, NOTHING=15, YIELDER=16, 
		YIELD=17, ITERABLE=18, INT=19, NAMEUSINGLE=20, NAMEU=21, NAMEL=22, STRING=23, 
		COLON=24, SEMICOLON=25, PLUSPLUS=26, BANG=27, STAR=28, SLASH=29, PERCENT=30, 
		PLUS=31, DASH=32, DOTDOT=33, LESSDOT=34, DOTLESS=35, LESSLESS=36, DOTDOTDOT=37, 
		LESSDOTDOT=38, DOT=39, COMMA=40, LANGLE=41, LANGLEEQUAL=42, RANGLEEQUAL=43, 
		RANGLE=44, EQUALEQUAL=45, BANGEQUAL=46, EQUAL=47, COLONEQUAL=48, AMP=49, 
		PIPE=50, LPAREN=51, RPAREN=52, LBRACK=53, RBRACK=54, LBRACE=55, RBRACE=56, 
		COMMENT1=57, COMMENT2=58, WS=59;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'true'", "'false'", "'if'", "'else'", "'while'", "'for'", "'in'", "'return'", 
		"'interface'", "'fun'", "'super'", "'class'", "'extends'", "'Thing'", 
		"'Nothing'", "'yielder'", "'yield'", "'Iterable'", "INT", "NAMEUSINGLE", 
		"NAMEU", "NAMEL", "STRING", "':'", "';'", "'++'", "'!'", "'*'", "'/'", 
		"'%'", "'+'", "'-'", "'..'", "'<.'", "'.<'", "'<<'", "'...'", "'<..'", 
		"'.'", "','", "'<'", "'<='", "'>='", "'>'", "'=='", "'!='", "'='", "':='", 
		"'&'", "'|'", "'('", "')'", "'['", "']'", "'{'", "'}'", "COMMENT1", "COMMENT2", 
		"WS"
	};
	public static final String[] ruleNames = {
		"TRUE", "FALSE", "IF", "ELSE", "WHILE", "FOR", "IN", "RETURN", "INTERFACE", 
		"FUN", "SUPER", "CLASS", "EXTENDS", "THING", "NOTHING", "YIELDER", "YIELD", 
		"ITERABLE", "INT", "NAMEUSINGLE", "NAMEU", "NAMEL", "STRING", "COLON", 
		"SEMICOLON", "PLUSPLUS", "BANG", "STAR", "SLASH", "PERCENT", "PLUS", "DASH", 
		"DOTDOT", "LESSDOT", "DOTLESS", "LESSLESS", "DOTDOTDOT", "LESSDOTDOT", 
		"DOT", "COMMA", "LANGLE", "LANGLEEQUAL", "RANGLEEQUAL", "RANGLE", "EQUALEQUAL", 
		"BANGEQUAL", "EQUAL", "COLONEQUAL", "AMP", "PIPE", "LPAREN", "RPAREN", 
		"LBRACK", "RBRACK", "LBRACE", "RBRACE", "COMMENT1", "COMMENT2", "WS"
	};


	public CubeXLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CubeXLexer.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 56: COMMENT1_action((RuleContext)_localctx, actionIndex); break;

		case 57: COMMENT2_action((RuleContext)_localctx, actionIndex); break;

		case 58: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void COMMENT1_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}
	private void COMMENT2_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4=\u016f\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t"+
		"\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4"+
		"(\t(\4)\t)\4*\t*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62"+
		"\t\62\4\63\t\63\4\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4"+
		":\t:\4;\t;\4<\t<\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\24\6\24\u00e9\n\24\r\24\16\24\u00ea\3\25\3\25\3\26\3"+
		"\26\6\26\u00f1\n\26\r\26\16\26\u00f2\3\27\3\27\7\27\u00f7\n\27\f\27\16"+
		"\27\u00fa\13\27\3\30\3\30\7\30\u00fe\n\30\f\30\16\30\u0101\13\30\3\30"+
		"\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36"+
		"\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3%\3&\3&\3"+
		"&\3&\3\'\3\'\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3+\3,\3,\3,\3-\3-\3.\3.\3"+
		".\3/\3/\3/\3\60\3\60\3\61\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65"+
		"\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3:\7:\u0158\n:\f:\16:\u015b"+
		"\13:\3:\3:\3:\3:\3;\3;\7;\u0163\n;\f;\16;\u0166\13;\3;\3;\3;\3;\3<\3<"+
		"\3<\3<\4\u00ff\u0164=\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n"+
		"\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%"+
		"\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1\61\32\1\63\33\1\65\34\1\67\35\19"+
		"\36\1;\37\1= \1?!\1A\"\1C#\1E$\1G%\1I&\1K\'\1M(\1O)\1Q*\1S+\1U,\1W-\1"+
		"Y.\1[/\1]\60\1_\61\1a\62\1c\63\1e\64\1g\65\1i\66\1k\67\1m8\1o9\1q:\1s"+
		";\2u<\3w=\4\3\2\f\3\62;\3C\\\3C\\\6\62;C\\aac|\3c|\6\62;C\\aac|\4\f\f"+
		"\17\17\4))bb\4\f\f\17\17\5\13\f\17\17\"\"\u0175\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2"+
		"Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3"+
		"\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2"+
		"\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\3y\3\2\2\2\5~\3\2\2\2\7\u0084\3\2"+
		"\2\2\t\u0087\3\2\2\2\13\u008c\3\2\2\2\r\u0092\3\2\2\2\17\u0096\3\2\2\2"+
		"\21\u0099\3\2\2\2\23\u00a0\3\2\2\2\25\u00aa\3\2\2\2\27\u00ae\3\2\2\2\31"+
		"\u00b4\3\2\2\2\33\u00ba\3\2\2\2\35\u00c2\3\2\2\2\37\u00c8\3\2\2\2!\u00d0"+
		"\3\2\2\2#\u00d8\3\2\2\2%\u00de\3\2\2\2\'\u00e8\3\2\2\2)\u00ec\3\2\2\2"+
		"+\u00ee\3\2\2\2-\u00f4\3\2\2\2/\u00fb\3\2\2\2\61\u0104\3\2\2\2\63\u0106"+
		"\3\2\2\2\65\u0108\3\2\2\2\67\u010b\3\2\2\29\u010d\3\2\2\2;\u010f\3\2\2"+
		"\2=\u0111\3\2\2\2?\u0113\3\2\2\2A\u0115\3\2\2\2C\u0117\3\2\2\2E\u011a"+
		"\3\2\2\2G\u011d\3\2\2\2I\u0120\3\2\2\2K\u0123\3\2\2\2M\u0127\3\2\2\2O"+
		"\u012b\3\2\2\2Q\u012d\3\2\2\2S\u012f\3\2\2\2U\u0131\3\2\2\2W\u0134\3\2"+
		"\2\2Y\u0137\3\2\2\2[\u0139\3\2\2\2]\u013c\3\2\2\2_\u013f\3\2\2\2a\u0141"+
		"\3\2\2\2c\u0144\3\2\2\2e\u0146\3\2\2\2g\u0148\3\2\2\2i\u014a\3\2\2\2k"+
		"\u014c\3\2\2\2m\u014e\3\2\2\2o\u0150\3\2\2\2q\u0152\3\2\2\2s\u0154\3\2"+
		"\2\2u\u0160\3\2\2\2w\u016b\3\2\2\2yz\7v\2\2z{\7t\2\2{|\7w\2\2|}\7g\2\2"+
		"}\4\3\2\2\2~\177\7h\2\2\177\u0080\7c\2\2\u0080\u0081\7n\2\2\u0081\u0082"+
		"\7u\2\2\u0082\u0083\7g\2\2\u0083\6\3\2\2\2\u0084\u0085\7k\2\2\u0085\u0086"+
		"\7h\2\2\u0086\b\3\2\2\2\u0087\u0088\7g\2\2\u0088\u0089\7n\2\2\u0089\u008a"+
		"\7u\2\2\u008a\u008b\7g\2\2\u008b\n\3\2\2\2\u008c\u008d\7y\2\2\u008d\u008e"+
		"\7j\2\2\u008e\u008f\7k\2\2\u008f\u0090\7n\2\2\u0090\u0091\7g\2\2\u0091"+
		"\f\3\2\2\2\u0092\u0093\7h\2\2\u0093\u0094\7q\2\2\u0094\u0095\7t\2\2\u0095"+
		"\16\3\2\2\2\u0096\u0097\7k\2\2\u0097\u0098\7p\2\2\u0098\20\3\2\2\2\u0099"+
		"\u009a\7t\2\2\u009a\u009b\7g\2\2\u009b\u009c\7v\2\2\u009c\u009d\7w\2\2"+
		"\u009d\u009e\7t\2\2\u009e\u009f\7p\2\2\u009f\22\3\2\2\2\u00a0\u00a1\7"+
		"k\2\2\u00a1\u00a2\7p\2\2\u00a2\u00a3\7v\2\2\u00a3\u00a4\7g\2\2\u00a4\u00a5"+
		"\7t\2\2\u00a5\u00a6\7h\2\2\u00a6\u00a7\7c\2\2\u00a7\u00a8\7e\2\2\u00a8"+
		"\u00a9\7g\2\2\u00a9\24\3\2\2\2\u00aa\u00ab\7h\2\2\u00ab\u00ac\7w\2\2\u00ac"+
		"\u00ad\7p\2\2\u00ad\26\3\2\2\2\u00ae\u00af\7u\2\2\u00af\u00b0\7w\2\2\u00b0"+
		"\u00b1\7r\2\2\u00b1\u00b2\7g\2\2\u00b2\u00b3\7t\2\2\u00b3\30\3\2\2\2\u00b4"+
		"\u00b5\7e\2\2\u00b5\u00b6\7n\2\2\u00b6\u00b7\7c\2\2\u00b7\u00b8\7u\2\2"+
		"\u00b8\u00b9\7u\2\2\u00b9\32\3\2\2\2\u00ba\u00bb\7g\2\2\u00bb\u00bc\7"+
		"z\2\2\u00bc\u00bd\7v\2\2\u00bd\u00be\7g\2\2\u00be\u00bf\7p\2\2\u00bf\u00c0"+
		"\7f\2\2\u00c0\u00c1\7u\2\2\u00c1\34\3\2\2\2\u00c2\u00c3\7V\2\2\u00c3\u00c4"+
		"\7j\2\2\u00c4\u00c5\7k\2\2\u00c5\u00c6\7p\2\2\u00c6\u00c7\7i\2\2\u00c7"+
		"\36\3\2\2\2\u00c8\u00c9\7P\2\2\u00c9\u00ca\7q\2\2\u00ca\u00cb\7v\2\2\u00cb"+
		"\u00cc\7j\2\2\u00cc\u00cd\7k\2\2\u00cd\u00ce\7p\2\2\u00ce\u00cf\7i\2\2"+
		"\u00cf \3\2\2\2\u00d0\u00d1\7{\2\2\u00d1\u00d2\7k\2\2\u00d2\u00d3\7g\2"+
		"\2\u00d3\u00d4\7n\2\2\u00d4\u00d5\7f\2\2\u00d5\u00d6\7g\2\2\u00d6\u00d7"+
		"\7t\2\2\u00d7\"\3\2\2\2\u00d8\u00d9\7{\2\2\u00d9\u00da\7k\2\2\u00da\u00db"+
		"\7g\2\2\u00db\u00dc\7n\2\2\u00dc\u00dd\7f\2\2\u00dd$\3\2\2\2\u00de\u00df"+
		"\7K\2\2\u00df\u00e0\7v\2\2\u00e0\u00e1\7g\2\2\u00e1\u00e2\7t\2\2\u00e2"+
		"\u00e3\7c\2\2\u00e3\u00e4\7d\2\2\u00e4\u00e5\7n\2\2\u00e5\u00e6\7g\2\2"+
		"\u00e6&\3\2\2\2\u00e7\u00e9\t\2\2\2\u00e8\u00e7\3\2\2\2\u00e9\u00ea\3"+
		"\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb(\3\2\2\2\u00ec\u00ed"+
		"\t\3\2\2\u00ed*\3\2\2\2\u00ee\u00f0\t\4\2\2\u00ef\u00f1\t\5\2\2\u00f0"+
		"\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2"+
		"\2\2\u00f3,\3\2\2\2\u00f4\u00f8\t\6\2\2\u00f5\u00f7\t\7\2\2\u00f6\u00f5"+
		"\3\2\2\2\u00f7\u00fa\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9"+
		".\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb\u00ff\7$\2\2\u00fc\u00fe\n\b\2\2\u00fd"+
		"\u00fc\3\2\2\2\u00fe\u0101\3\2\2\2\u00ff\u0100\3\2\2\2\u00ff\u00fd\3\2"+
		"\2\2\u0100\u0102\3\2\2\2\u0101\u00ff\3\2\2\2\u0102\u0103\7$\2\2\u0103"+
		"\60\3\2\2\2\u0104\u0105\7<\2\2\u0105\62\3\2\2\2\u0106\u0107\7=\2\2\u0107"+
		"\64\3\2\2\2\u0108\u0109\7-\2\2\u0109\u010a\7-\2\2\u010a\66\3\2\2\2\u010b"+
		"\u010c\7#\2\2\u010c8\3\2\2\2\u010d\u010e\7,\2\2\u010e:\3\2\2\2\u010f\u0110"+
		"\7\61\2\2\u0110<\3\2\2\2\u0111\u0112\7\'\2\2\u0112>\3\2\2\2\u0113\u0114"+
		"\7-\2\2\u0114@\3\2\2\2\u0115\u0116\7/\2\2\u0116B\3\2\2\2\u0117\u0118\7"+
		"\60\2\2\u0118\u0119\7\60\2\2\u0119D\3\2\2\2\u011a\u011b\7>\2\2\u011b\u011c"+
		"\7\60\2\2\u011cF\3\2\2\2\u011d\u011e\7\60\2\2\u011e\u011f\7>\2\2\u011f"+
		"H\3\2\2\2\u0120\u0121\7>\2\2\u0121\u0122\7>\2\2\u0122J\3\2\2\2\u0123\u0124"+
		"\7\60\2\2\u0124\u0125\7\60\2\2\u0125\u0126\7\60\2\2\u0126L\3\2\2\2\u0127"+
		"\u0128\7>\2\2\u0128\u0129\7\60\2\2\u0129\u012a\7\60\2\2\u012aN\3\2\2\2"+
		"\u012b\u012c\7\60\2\2\u012cP\3\2\2\2\u012d\u012e\7.\2\2\u012eR\3\2\2\2"+
		"\u012f\u0130\7>\2\2\u0130T\3\2\2\2\u0131\u0132\7>\2\2\u0132\u0133\7?\2"+
		"\2\u0133V\3\2\2\2\u0134\u0135\7@\2\2\u0135\u0136\7?\2\2\u0136X\3\2\2\2"+
		"\u0137\u0138\7@\2\2\u0138Z\3\2\2\2\u0139\u013a\7?\2\2\u013a\u013b\7?\2"+
		"\2\u013b\\\3\2\2\2\u013c\u013d\7#\2\2\u013d\u013e\7?\2\2\u013e^\3\2\2"+
		"\2\u013f\u0140\7?\2\2\u0140`\3\2\2\2\u0141\u0142\7<\2\2\u0142\u0143\7"+
		"?\2\2\u0143b\3\2\2\2\u0144\u0145\7(\2\2\u0145d\3\2\2\2\u0146\u0147\7~"+
		"\2\2\u0147f\3\2\2\2\u0148\u0149\7*\2\2\u0149h\3\2\2\2\u014a\u014b\7+\2"+
		"\2\u014bj\3\2\2\2\u014c\u014d\7]\2\2\u014dl\3\2\2\2\u014e\u014f\7_\2\2"+
		"\u014fn\3\2\2\2\u0150\u0151\7}\2\2\u0151p\3\2\2\2\u0152\u0153\7\177\2"+
		"\2\u0153r\3\2\2\2\u0154\u0159\7b\2\2\u0155\u0158\5s:\2\u0156\u0158\n\t"+
		"\2\2\u0157\u0155\3\2\2\2\u0157\u0156\3\2\2\2\u0158\u015b\3\2\2\2\u0159"+
		"\u0157\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u015c\3\2\2\2\u015b\u0159\3\2"+
		"\2\2\u015c\u015d\7)\2\2\u015d\u015e\3\2\2\2\u015e\u015f\b:\2\2\u015ft"+
		"\3\2\2\2\u0160\u0164\7%\2\2\u0161\u0163\13\2\2\2\u0162\u0161\3\2\2\2\u0163"+
		"\u0166\3\2\2\2\u0164\u0165\3\2\2\2\u0164\u0162\3\2\2\2\u0165\u0167\3\2"+
		"\2\2\u0166\u0164\3\2\2\2\u0167\u0168\t\n\2\2\u0168\u0169\3\2\2\2\u0169"+
		"\u016a\b;\3\2\u016av\3\2\2\2\u016b\u016c\t\13\2\2\u016c\u016d\3\2\2\2"+
		"\u016d\u016e\b<\4\2\u016ex\3\2\2\2\n\2\u00ea\u00f2\u00f8\u00ff\u0157\u0159"+
		"\u0164";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}