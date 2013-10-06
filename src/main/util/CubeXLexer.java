package main.util;

// Generated from CubeXLexer.g4 by ANTLR 4.0
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
		FUN=10, SUPER=11, CLASS=12, EXTENDS=13, THING=14, NOTHING=15, INT=16, 
		NAMEUSINGLE=17, NAMEU=18, NAMEL=19, STRING=20, COLON=21, SEMICOLON=22, 
		PLUSPLUS=23, BANG=24, STAR=25, SLASH=26, PERCENT=27, PLUS=28, DASH=29, 
		DOTDOT=30, LESSDOT=31, DOTLESS=32, LESSLESS=33, DOTDOTDOT=34, LESSDOTDOT=35, 
		DOT=36, COMMA=37, LANGLE=38, LANGLEEQUAL=39, RANGLEEQUAL=40, RANGLE=41, 
		EQUALEQUAL=42, BANGEQUAL=43, EQUAL=44, COLONEQUAL=45, AMP=46, PIPE=47, 
		LPAREN=48, RPAREN=49, LBRACK=50, RBRACK=51, LBRACE=52, RBRACE=53, COMMENT1=54, 
		COMMENT2=55, WS=56;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'true'", "'false'", "'if'", "'else'", "'while'", "'for'", "'in'", "'return'", 
		"'interface'", "'fun'", "'super'", "'class'", "'extends'", "'Thing'", 
		"'Nothing'", "INT", "NAMEUSINGLE", "NAMEU", "NAMEL", "STRING", "':'", 
		"';'", "'++'", "'!'", "'*'", "'/'", "'%'", "'+'", "'-'", "'..'", "'<.'", 
		"'.<'", "'<<'", "'...'", "'<..'", "'.'", "','", "'<'", "'<='", "'>='", 
		"'>'", "'=='", "'!='", "'='", "':='", "'&'", "'|'", "'('", "')'", "'['", 
		"']'", "'{'", "'}'", "COMMENT1", "COMMENT2", "WS"
	};
	public static final String[] ruleNames = {
		"TRUE", "FALSE", "IF", "ELSE", "WHILE", "FOR", "IN", "RETURN", "INTERFACE", 
		"FUN", "SUPER", "CLASS", "EXTENDS", "THING", "NOTHING", "INT", "NAMEUSINGLE", 
		"NAMEU", "NAMEL", "STRING", "COLON", "SEMICOLON", "PLUSPLUS", "BANG", 
		"STAR", "SLASH", "PERCENT", "PLUS", "DASH", "DOTDOT", "LESSDOT", "DOTLESS", 
		"LESSLESS", "DOTDOTDOT", "LESSDOTDOT", "DOT", "COMMA", "LANGLE", "LANGLEEQUAL", 
		"RANGLEEQUAL", "RANGLE", "EQUALEQUAL", "BANGEQUAL", "EQUAL", "COLONEQUAL", 
		"AMP", "PIPE", "LPAREN", "RPAREN", "LBRACK", "RBRACK", "LBRACE", "RBRACE", 
		"COMMENT1", "COMMENT2", "WS"
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
		case 53: COMMENT1_action((RuleContext)_localctx, actionIndex); break;

		case 54: COMMENT2_action((RuleContext)_localctx, actionIndex); break;

		case 55: WS_action((RuleContext)_localctx, actionIndex); break;
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
		"\2\4:\u0152\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t"+
		"\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4"+
		"(\t(\4)\t)\4*\t*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62"+
		"\t\62\4\63\t\63\4\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\3"+
		"\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\21\6\21\u00cc\n\21\r\21\16\21\u00cd\3\22\3\22"+
		"\3\23\3\23\6\23\u00d4\n\23\r\23\16\23\u00d5\3\24\3\24\7\24\u00da\n\24"+
		"\f\24\16\24\u00dd\13\24\3\25\3\25\7\25\u00e1\n\25\f\25\16\25\u00e4\13"+
		"\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3"+
		"\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3!\3"+
		"!\3\"\3\"\3\"\3#\3#\3#\3#\3$\3$\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3(\3)"+
		"\3)\3)\3*\3*\3+\3+\3+\3,\3,\3,\3-\3-\3.\3.\3.\3/\3/\3\60\3\60\3\61\3\61"+
		"\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\3\67\7\67"+
		"\u013b\n\67\f\67\16\67\u013e\13\67\3\67\3\67\3\67\3\67\38\38\78\u0146"+
		"\n8\f8\168\u0149\138\38\38\38\38\39\39\39\39\4\u00e2\u0147:\3\3\1\5\4"+
		"\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16"+
		"\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1"+
		"/\31\1\61\32\1\63\33\1\65\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1E$"+
		"\1G%\1I&\1K\'\1M(\1O)\1Q*\1S+\1U,\1W-\1Y.\1[/\1]\60\1_\61\1a\62\1c\63"+
		"\1e\64\1g\65\1i\66\1k\67\1m8\2o9\3q:\4\3\2\f\3\62;\3C\\\3C\\\6\62;C\\"+
		"aac|\3c|\6\62;C\\aac|\4\f\f\17\17\4))bb\4\f\f\17\17\5\13\f\17\17\"\"\u0158"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3"+
		"\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2"+
		"\2\2o\3\2\2\2\2q\3\2\2\2\3s\3\2\2\2\5x\3\2\2\2\7~\3\2\2\2\t\u0081\3\2"+
		"\2\2\13\u0086\3\2\2\2\r\u008c\3\2\2\2\17\u0090\3\2\2\2\21\u0093\3\2\2"+
		"\2\23\u009a\3\2\2\2\25\u00a4\3\2\2\2\27\u00a8\3\2\2\2\31\u00ae\3\2\2\2"+
		"\33\u00b4\3\2\2\2\35\u00bc\3\2\2\2\37\u00c2\3\2\2\2!\u00cb\3\2\2\2#\u00cf"+
		"\3\2\2\2%\u00d1\3\2\2\2\'\u00d7\3\2\2\2)\u00de\3\2\2\2+\u00e7\3\2\2\2"+
		"-\u00e9\3\2\2\2/\u00eb\3\2\2\2\61\u00ee\3\2\2\2\63\u00f0\3\2\2\2\65\u00f2"+
		"\3\2\2\2\67\u00f4\3\2\2\29\u00f6\3\2\2\2;\u00f8\3\2\2\2=\u00fa\3\2\2\2"+
		"?\u00fd\3\2\2\2A\u0100\3\2\2\2C\u0103\3\2\2\2E\u0106\3\2\2\2G\u010a\3"+
		"\2\2\2I\u010e\3\2\2\2K\u0110\3\2\2\2M\u0112\3\2\2\2O\u0114\3\2\2\2Q\u0117"+
		"\3\2\2\2S\u011a\3\2\2\2U\u011c\3\2\2\2W\u011f\3\2\2\2Y\u0122\3\2\2\2["+
		"\u0124\3\2\2\2]\u0127\3\2\2\2_\u0129\3\2\2\2a\u012b\3\2\2\2c\u012d\3\2"+
		"\2\2e\u012f\3\2\2\2g\u0131\3\2\2\2i\u0133\3\2\2\2k\u0135\3\2\2\2m\u0137"+
		"\3\2\2\2o\u0143\3\2\2\2q\u014e\3\2\2\2st\7v\2\2tu\7t\2\2uv\7w\2\2vw\7"+
		"g\2\2w\4\3\2\2\2xy\7h\2\2yz\7c\2\2z{\7n\2\2{|\7u\2\2|}\7g\2\2}\6\3\2\2"+
		"\2~\177\7k\2\2\177\u0080\7h\2\2\u0080\b\3\2\2\2\u0081\u0082\7g\2\2\u0082"+
		"\u0083\7n\2\2\u0083\u0084\7u\2\2\u0084\u0085\7g\2\2\u0085\n\3\2\2\2\u0086"+
		"\u0087\7y\2\2\u0087\u0088\7j\2\2\u0088\u0089\7k\2\2\u0089\u008a\7n\2\2"+
		"\u008a\u008b\7g\2\2\u008b\f\3\2\2\2\u008c\u008d\7h\2\2\u008d\u008e\7q"+
		"\2\2\u008e\u008f\7t\2\2\u008f\16\3\2\2\2\u0090\u0091\7k\2\2\u0091\u0092"+
		"\7p\2\2\u0092\20\3\2\2\2\u0093\u0094\7t\2\2\u0094\u0095\7g\2\2\u0095\u0096"+
		"\7v\2\2\u0096\u0097\7w\2\2\u0097\u0098\7t\2\2\u0098\u0099\7p\2\2\u0099"+
		"\22\3\2\2\2\u009a\u009b\7k\2\2\u009b\u009c\7p\2\2\u009c\u009d\7v\2\2\u009d"+
		"\u009e\7g\2\2\u009e\u009f\7t\2\2\u009f\u00a0\7h\2\2\u00a0\u00a1\7c\2\2"+
		"\u00a1\u00a2\7e\2\2\u00a2\u00a3\7g\2\2\u00a3\24\3\2\2\2\u00a4\u00a5\7"+
		"h\2\2\u00a5\u00a6\7w\2\2\u00a6\u00a7\7p\2\2\u00a7\26\3\2\2\2\u00a8\u00a9"+
		"\7u\2\2\u00a9\u00aa\7w\2\2\u00aa\u00ab\7r\2\2\u00ab\u00ac\7g\2\2\u00ac"+
		"\u00ad\7t\2\2\u00ad\30\3\2\2\2\u00ae\u00af\7e\2\2\u00af\u00b0\7n\2\2\u00b0"+
		"\u00b1\7c\2\2\u00b1\u00b2\7u\2\2\u00b2\u00b3\7u\2\2\u00b3\32\3\2\2\2\u00b4"+
		"\u00b5\7g\2\2\u00b5\u00b6\7z\2\2\u00b6\u00b7\7v\2\2\u00b7\u00b8\7g\2\2"+
		"\u00b8\u00b9\7p\2\2\u00b9\u00ba\7f\2\2\u00ba\u00bb\7u\2\2\u00bb\34\3\2"+
		"\2\2\u00bc\u00bd\7V\2\2\u00bd\u00be\7j\2\2\u00be\u00bf\7k\2\2\u00bf\u00c0"+
		"\7p\2\2\u00c0\u00c1\7i\2\2\u00c1\36\3\2\2\2\u00c2\u00c3\7P\2\2\u00c3\u00c4"+
		"\7q\2\2\u00c4\u00c5\7v\2\2\u00c5\u00c6\7j\2\2\u00c6\u00c7\7k\2\2\u00c7"+
		"\u00c8\7p\2\2\u00c8\u00c9\7i\2\2\u00c9 \3\2\2\2\u00ca\u00cc\t\2\2\2\u00cb"+
		"\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2"+
		"\2\2\u00ce\"\3\2\2\2\u00cf\u00d0\t\3\2\2\u00d0$\3\2\2\2\u00d1\u00d3\t"+
		"\4\2\2\u00d2\u00d4\t\5\2\2\u00d3\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5"+
		"\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6&\3\2\2\2\u00d7\u00db\t\6\2\2"+
		"\u00d8\u00da\t\7\2\2\u00d9\u00d8\3\2\2\2\u00da\u00dd\3\2\2\2\u00db\u00d9"+
		"\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc(\3\2\2\2\u00dd\u00db\3\2\2\2\u00de"+
		"\u00e2\7$\2\2\u00df\u00e1\n\b\2\2\u00e0\u00df\3\2\2\2\u00e1\u00e4\3\2"+
		"\2\2\u00e2\u00e3\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00e5\3\2\2\2\u00e4"+
		"\u00e2\3\2\2\2\u00e5\u00e6\7$\2\2\u00e6*\3\2\2\2\u00e7\u00e8\7<\2\2\u00e8"+
		",\3\2\2\2\u00e9\u00ea\7=\2\2\u00ea.\3\2\2\2\u00eb\u00ec\7-\2\2\u00ec\u00ed"+
		"\7-\2\2\u00ed\60\3\2\2\2\u00ee\u00ef\7#\2\2\u00ef\62\3\2\2\2\u00f0\u00f1"+
		"\7,\2\2\u00f1\64\3\2\2\2\u00f2\u00f3\7\61\2\2\u00f3\66\3\2\2\2\u00f4\u00f5"+
		"\7\'\2\2\u00f58\3\2\2\2\u00f6\u00f7\7-\2\2\u00f7:\3\2\2\2\u00f8\u00f9"+
		"\7/\2\2\u00f9<\3\2\2\2\u00fa\u00fb\7\60\2\2\u00fb\u00fc\7\60\2\2\u00fc"+
		">\3\2\2\2\u00fd\u00fe\7>\2\2\u00fe\u00ff\7\60\2\2\u00ff@\3\2\2\2\u0100"+
		"\u0101\7\60\2\2\u0101\u0102\7>\2\2\u0102B\3\2\2\2\u0103\u0104\7>\2\2\u0104"+
		"\u0105\7>\2\2\u0105D\3\2\2\2\u0106\u0107\7\60\2\2\u0107\u0108\7\60\2\2"+
		"\u0108\u0109\7\60\2\2\u0109F\3\2\2\2\u010a\u010b\7>\2\2\u010b\u010c\7"+
		"\60\2\2\u010c\u010d\7\60\2\2\u010dH\3\2\2\2\u010e\u010f\7\60\2\2\u010f"+
		"J\3\2\2\2\u0110\u0111\7.\2\2\u0111L\3\2\2\2\u0112\u0113\7>\2\2\u0113N"+
		"\3\2\2\2\u0114\u0115\7>\2\2\u0115\u0116\7?\2\2\u0116P\3\2\2\2\u0117\u0118"+
		"\7@\2\2\u0118\u0119\7?\2\2\u0119R\3\2\2\2\u011a\u011b\7@\2\2\u011bT\3"+
		"\2\2\2\u011c\u011d\7?\2\2\u011d\u011e\7?\2\2\u011eV\3\2\2\2\u011f\u0120"+
		"\7#\2\2\u0120\u0121\7?\2\2\u0121X\3\2\2\2\u0122\u0123\7?\2\2\u0123Z\3"+
		"\2\2\2\u0124\u0125\7<\2\2\u0125\u0126\7?\2\2\u0126\\\3\2\2\2\u0127\u0128"+
		"\7(\2\2\u0128^\3\2\2\2\u0129\u012a\7~\2\2\u012a`\3\2\2\2\u012b\u012c\7"+
		"*\2\2\u012cb\3\2\2\2\u012d\u012e\7+\2\2\u012ed\3\2\2\2\u012f\u0130\7]"+
		"\2\2\u0130f\3\2\2\2\u0131\u0132\7_\2\2\u0132h\3\2\2\2\u0133\u0134\7}\2"+
		"\2\u0134j\3\2\2\2\u0135\u0136\7\177\2\2\u0136l\3\2\2\2\u0137\u013c\7b"+
		"\2\2\u0138\u013b\5m\67\2\u0139\u013b\n\t\2\2\u013a\u0138\3\2\2\2\u013a"+
		"\u0139\3\2\2\2\u013b\u013e\3\2\2\2\u013c\u013a\3\2\2\2\u013c\u013d\3\2"+
		"\2\2\u013d\u013f\3\2\2\2\u013e\u013c\3\2\2\2\u013f\u0140\7)\2\2\u0140"+
		"\u0141\3\2\2\2\u0141\u0142\b\67\2\2\u0142n\3\2\2\2\u0143\u0147\7%\2\2"+
		"\u0144\u0146\13\2\2\2\u0145\u0144\3\2\2\2\u0146\u0149\3\2\2\2\u0147\u0148"+
		"\3\2\2\2\u0147\u0145\3\2\2\2\u0148\u014a\3\2\2\2\u0149\u0147\3\2\2\2\u014a"+
		"\u014b\t\n\2\2\u014b\u014c\3\2\2\2\u014c\u014d\b8\3\2\u014dp\3\2\2\2\u014e"+
		"\u014f\t\13\2\2\u014f\u0150\3\2\2\2\u0150\u0151\b9\4\2\u0151r\3\2\2\2"+
		"\n\2\u00cd\u00d5\u00db\u00e2\u013a\u013c\u0147";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}