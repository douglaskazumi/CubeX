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
		NAMEU=17, NAMEL=18, STRING=19, COLON=20, SEMICOLON=21, PLUSPLUS=22, BANG=23, 
		STAR=24, SLASH=25, PERCENT=26, PLUS=27, DASH=28, DOTDOT=29, LESSDOT=30, 
		DOTLESS=31, LESSLESS=32, DOTDOTDOT=33, LESSDOTDOT=34, DOT=35, LANGLE=36, 
		LANGLEEQUAL=37, RANGLEEQUAL=38, RANGLE=39, EQUALEQUAL=40, BANGEQUAL=41, 
		AMP=42, PIPE=43, LPAREN=44, RPAREN=45, LBRACE=46, RBRACE=47, COMMENT1=48, 
		COMMENT2=49, WS=50;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'true'", "'false'", "'if'", "'else'", "'while'", "'for'", "'in'", "'return'", 
		"'interface'", "'fun'", "'super'", "'class'", "'extends'", "'Thing'", 
		"'Nothing'", "INT", "NAMEU", "NAMEL", "STRING", "':'", "';'", "'++'", 
		"'!'", "'*'", "'/'", "'%'", "'+'", "'-'", "'..'", "'<.'", "'.<'", "'<<'", 
		"'...'", "'<..'", "'.'", "'<'", "'<='", "'>='", "'>'", "'=='", "'!='", 
		"'&'", "'|'", "'('", "')'", "'{'", "'}'", "COMMENT1", "COMMENT2", "WS"
	};
	public static final String[] ruleNames = {
		"TRUE", "FALSE", "IF", "ELSE", "WHILE", "FOR", "IN", "RETURN", "INTERFACE", 
		"FUN", "SUPER", "CLASS", "EXTENDS", "THING", "NOTHING", "INT", "NAMEU", 
		"NAMEL", "STRING", "COLON", "SEMICOLON", "PLUSPLUS", "BANG", "STAR", "SLASH", 
		"PERCENT", "PLUS", "DASH", "DOTDOT", "LESSDOT", "DOTLESS", "LESSLESS", 
		"DOTDOTDOT", "LESSDOTDOT", "DOT", "LANGLE", "LANGLEEQUAL", "RANGLEEQUAL", 
		"RANGLE", "EQUALEQUAL", "BANGEQUAL", "AMP", "PIPE", "LPAREN", "RPAREN", 
		"LBRACE", "RBRACE", "COMMENT1", "COMMENT2", "WS"
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
		case 47: COMMENT1_action((RuleContext)_localctx, actionIndex); break;

		case 48: COMMENT2_action((RuleContext)_localctx, actionIndex); break;

		case 49: WS_action((RuleContext)_localctx, actionIndex); break;
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
		"\2\4\64\u013a\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4"+
		"(\t(\4)\t)\4*\t*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62"+
		"\t\62\4\63\t\63\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\6\21\u00c0\n\21\r\21\16"+
		"\21\u00c1\3\22\3\22\7\22\u00c6\n\22\f\22\16\22\u00c9\13\22\3\23\3\23\7"+
		"\23\u00cd\n\23\f\23\16\23\u00d0\13\23\3\24\3\24\7\24\u00d4\n\24\f\24\16"+
		"\24\u00d7\13\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\30\3\30"+
		"\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37"+
		"\3\37\3\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3$\3$\3%\3%\3"+
		"&\3&\3&\3\'\3\'\3\'\3(\3(\3)\3)\3)\3*\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3"+
		"/\3/\3\60\3\60\3\61\3\61\3\61\7\61\u0123\n\61\f\61\16\61\u0126\13\61\3"+
		"\61\3\61\3\61\3\61\3\62\3\62\7\62\u012e\n\62\f\62\16\62\u0131\13\62\3"+
		"\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\4\u00d5\u012f\64\3\3\1\5\4\1\7"+
		"\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33"+
		"\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1"+
		"\61\32\1\63\33\1\65\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1E$\1G%\1"+
		"I&\1K\'\1M(\1O)\1Q*\1S+\1U,\1W-\1Y.\1[/\1]\60\1_\61\1a\62\2c\63\3e\64"+
		"\4\3\2\n\3\62;\3C\\\6\62;C\\aac|\3c|\6\62;C\\aac|\4))bb\4\f\f\17\17\5"+
		"\13\f\17\17\"\"\u0140\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2"+
		"\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2"+
		"Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3"+
		"\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\3g\3\2\2\2\5l\3\2\2"+
		"\2\7r\3\2\2\2\tu\3\2\2\2\13z\3\2\2\2\r\u0080\3\2\2\2\17\u0084\3\2\2\2"+
		"\21\u0087\3\2\2\2\23\u008e\3\2\2\2\25\u0098\3\2\2\2\27\u009c\3\2\2\2\31"+
		"\u00a2\3\2\2\2\33\u00a8\3\2\2\2\35\u00b0\3\2\2\2\37\u00b6\3\2\2\2!\u00bf"+
		"\3\2\2\2#\u00c3\3\2\2\2%\u00ca\3\2\2\2\'\u00d1\3\2\2\2)\u00da\3\2\2\2"+
		"+\u00dc\3\2\2\2-\u00de\3\2\2\2/\u00e1\3\2\2\2\61\u00e3\3\2\2\2\63\u00e5"+
		"\3\2\2\2\65\u00e7\3\2\2\2\67\u00e9\3\2\2\29\u00eb\3\2\2\2;\u00ed\3\2\2"+
		"\2=\u00f0\3\2\2\2?\u00f3\3\2\2\2A\u00f6\3\2\2\2C\u00f9\3\2\2\2E\u00fd"+
		"\3\2\2\2G\u0101\3\2\2\2I\u0103\3\2\2\2K\u0105\3\2\2\2M\u0108\3\2\2\2O"+
		"\u010b\3\2\2\2Q\u010d\3\2\2\2S\u0110\3\2\2\2U\u0113\3\2\2\2W\u0115\3\2"+
		"\2\2Y\u0117\3\2\2\2[\u0119\3\2\2\2]\u011b\3\2\2\2_\u011d\3\2\2\2a\u011f"+
		"\3\2\2\2c\u012b\3\2\2\2e\u0136\3\2\2\2gh\7v\2\2hi\7t\2\2ij\7w\2\2jk\7"+
		"g\2\2k\4\3\2\2\2lm\7h\2\2mn\7c\2\2no\7n\2\2op\7u\2\2pq\7g\2\2q\6\3\2\2"+
		"\2rs\7k\2\2st\7h\2\2t\b\3\2\2\2uv\7g\2\2vw\7n\2\2wx\7u\2\2xy\7g\2\2y\n"+
		"\3\2\2\2z{\7y\2\2{|\7j\2\2|}\7k\2\2}~\7n\2\2~\177\7g\2\2\177\f\3\2\2\2"+
		"\u0080\u0081\7h\2\2\u0081\u0082\7q\2\2\u0082\u0083\7t\2\2\u0083\16\3\2"+
		"\2\2\u0084\u0085\7k\2\2\u0085\u0086\7p\2\2\u0086\20\3\2\2\2\u0087\u0088"+
		"\7t\2\2\u0088\u0089\7g\2\2\u0089\u008a\7v\2\2\u008a\u008b\7w\2\2\u008b"+
		"\u008c\7t\2\2\u008c\u008d\7p\2\2\u008d\22\3\2\2\2\u008e\u008f\7k\2\2\u008f"+
		"\u0090\7p\2\2\u0090\u0091\7v\2\2\u0091\u0092\7g\2\2\u0092\u0093\7t\2\2"+
		"\u0093\u0094\7h\2\2\u0094\u0095\7c\2\2\u0095\u0096\7e\2\2\u0096\u0097"+
		"\7g\2\2\u0097\24\3\2\2\2\u0098\u0099\7h\2\2\u0099\u009a\7w\2\2\u009a\u009b"+
		"\7p\2\2\u009b\26\3\2\2\2\u009c\u009d\7u\2\2\u009d\u009e\7w\2\2\u009e\u009f"+
		"\7r\2\2\u009f\u00a0\7g\2\2\u00a0\u00a1\7t\2\2\u00a1\30\3\2\2\2\u00a2\u00a3"+
		"\7e\2\2\u00a3\u00a4\7n\2\2\u00a4\u00a5\7c\2\2\u00a5\u00a6\7u\2\2\u00a6"+
		"\u00a7\7u\2\2\u00a7\32\3\2\2\2\u00a8\u00a9\7g\2\2\u00a9\u00aa\7z\2\2\u00aa"+
		"\u00ab\7v\2\2\u00ab\u00ac\7g\2\2\u00ac\u00ad\7p\2\2\u00ad\u00ae\7f\2\2"+
		"\u00ae\u00af\7u\2\2\u00af\34\3\2\2\2\u00b0\u00b1\7V\2\2\u00b1\u00b2\7"+
		"j\2\2\u00b2\u00b3\7k\2\2\u00b3\u00b4\7p\2\2\u00b4\u00b5\7i\2\2\u00b5\36"+
		"\3\2\2\2\u00b6\u00b7\7P\2\2\u00b7\u00b8\7q\2\2\u00b8\u00b9\7v\2\2\u00b9"+
		"\u00ba\7j\2\2\u00ba\u00bb\7k\2\2\u00bb\u00bc\7p\2\2\u00bc\u00bd\7i\2\2"+
		"\u00bd \3\2\2\2\u00be\u00c0\t\2\2\2\u00bf\u00be\3\2\2\2\u00c0\u00c1\3"+
		"\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\"\3\2\2\2\u00c3\u00c7"+
		"\t\3\2\2\u00c4\u00c6\t\4\2\2\u00c5\u00c4\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7"+
		"\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8$\3\2\2\2\u00c9\u00c7\3\2\2\2"+
		"\u00ca\u00ce\t\5\2\2\u00cb\u00cd\t\6\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00d0"+
		"\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf&\3\2\2\2\u00d0"+
		"\u00ce\3\2\2\2\u00d1\u00d5\7$\2\2\u00d2\u00d4\13\2\2\2\u00d3\u00d2\3\2"+
		"\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6"+
		"\u00d8\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00d9\7$\2\2\u00d9(\3\2\2\2\u00da"+
		"\u00db\7<\2\2\u00db*\3\2\2\2\u00dc\u00dd\7=\2\2\u00dd,\3\2\2\2\u00de\u00df"+
		"\7-\2\2\u00df\u00e0\7-\2\2\u00e0.\3\2\2\2\u00e1\u00e2\7#\2\2\u00e2\60"+
		"\3\2\2\2\u00e3\u00e4\7,\2\2\u00e4\62\3\2\2\2\u00e5\u00e6\7\61\2\2\u00e6"+
		"\64\3\2\2\2\u00e7\u00e8\7\'\2\2\u00e8\66\3\2\2\2\u00e9\u00ea\7-\2\2\u00ea"+
		"8\3\2\2\2\u00eb\u00ec\7/\2\2\u00ec:\3\2\2\2\u00ed\u00ee\7\60\2\2\u00ee"+
		"\u00ef\7\60\2\2\u00ef<\3\2\2\2\u00f0\u00f1\7>\2\2\u00f1\u00f2\7\60\2\2"+
		"\u00f2>\3\2\2\2\u00f3\u00f4\7\60\2\2\u00f4\u00f5\7>\2\2\u00f5@\3\2\2\2"+
		"\u00f6\u00f7\7>\2\2\u00f7\u00f8\7>\2\2\u00f8B\3\2\2\2\u00f9\u00fa\7\60"+
		"\2\2\u00fa\u00fb\7\60\2\2\u00fb\u00fc\7\60\2\2\u00fcD\3\2\2\2\u00fd\u00fe"+
		"\7>\2\2\u00fe\u00ff\7\60\2\2\u00ff\u0100\7\60\2\2\u0100F\3\2\2\2\u0101"+
		"\u0102\7\60\2\2\u0102H\3\2\2\2\u0103\u0104\7>\2\2\u0104J\3\2\2\2\u0105"+
		"\u0106\7>\2\2\u0106\u0107\7?\2\2\u0107L\3\2\2\2\u0108\u0109\7@\2\2\u0109"+
		"\u010a\7?\2\2\u010aN\3\2\2\2\u010b\u010c\7@\2\2\u010cP\3\2\2\2\u010d\u010e"+
		"\7?\2\2\u010e\u010f\7?\2\2\u010fR\3\2\2\2\u0110\u0111\7#\2\2\u0111\u0112"+
		"\7?\2\2\u0112T\3\2\2\2\u0113\u0114\7(\2\2\u0114V\3\2\2\2\u0115\u0116\7"+
		"~\2\2\u0116X\3\2\2\2\u0117\u0118\7*\2\2\u0118Z\3\2\2\2\u0119\u011a\7+"+
		"\2\2\u011a\\\3\2\2\2\u011b\u011c\7}\2\2\u011c^\3\2\2\2\u011d\u011e\7\177"+
		"\2\2\u011e`\3\2\2\2\u011f\u0124\7b\2\2\u0120\u0123\5a\61\2\u0121\u0123"+
		"\n\7\2\2\u0122\u0120\3\2\2\2\u0122\u0121\3\2\2\2\u0123\u0126\3\2\2\2\u0124"+
		"\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0127\3\2\2\2\u0126\u0124\3\2"+
		"\2\2\u0127\u0128\7)\2\2\u0128\u0129\3\2\2\2\u0129\u012a\b\61\2\2\u012a"+
		"b\3\2\2\2\u012b\u012f\7%\2\2\u012c\u012e\13\2\2\2\u012d\u012c\3\2\2\2"+
		"\u012e\u0131\3\2\2\2\u012f\u0130\3\2\2\2\u012f\u012d\3\2\2\2\u0130\u0132"+
		"\3\2\2\2\u0131\u012f\3\2\2\2\u0132\u0133\t\b\2\2\u0133\u0134\3\2\2\2\u0134"+
		"\u0135\b\62\3\2\u0135d\3\2\2\2\u0136\u0137\t\t\2\2\u0137\u0138\3\2\2\2"+
		"\u0138\u0139\b\63\4\2\u0139f\3\2\2\2\n\2\u00c1\u00c7\u00ce\u00d5\u0122"+
		"\u0124\u012f";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}