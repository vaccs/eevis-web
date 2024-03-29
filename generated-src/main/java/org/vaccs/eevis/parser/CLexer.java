// Generated from org/vaccs/eevis/parser/C.g4 by ANTLR 4.7.2
package org.vaccs.eevis.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, MINUS=3, ASSIGN=4, TIMES=5, DIVIDE=6, PLUS=7, LP=8, RP=9, 
		SIGNED=10, UNSIGNED=11, CHAR=12, SHORT=13, INT=14, LONG=15, PUT=16, ID=17, 
		NUM=18, HEXNUM=19, NEWLINE=20, WS=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "MINUS", "ASSIGN", "TIMES", "DIVIDE", "PLUS", "LP", "RP", 
			"SIGNED", "UNSIGNED", "CHAR", "SHORT", "INT", "LONG", "PUT", "ALPHA", 
			"DIGIT", "UNDERSCORE", "ID", "POSITIVE", "NUM", "HEXNUM", "NEWLINE", 
			"WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "','", "'-'", "'='", "'*'", "'/'", "'+'", "'('", "')'", 
			"'signed'", "'unsigned'", "'char'", "'short'", "'int'", "'long'", "'put'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "MINUS", "ASSIGN", "TIMES", "DIVIDE", "PLUS", "LP", 
			"RP", "SIGNED", "UNSIGNED", "CHAR", "SHORT", "INT", "LONG", "PUT", "ID", 
			"NUM", "HEXNUM", "NEWLINE", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public CLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "C.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27\u00a0\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3"+
		"\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\7\25z\n\25\f\25\16\25}\13"+
		"\25\3\26\3\26\3\27\3\27\7\27\u0083\n\27\f\27\16\27\u0086\13\27\3\27\5"+
		"\27\u0089\n\27\3\30\3\30\3\30\3\30\6\30\u008f\n\30\r\30\16\30\u0090\3"+
		"\31\5\31\u0094\n\31\3\31\3\31\3\31\3\31\3\32\6\32\u009b\n\32\r\32\16\32"+
		"\u009c\3\32\3\32\2\2\33\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\35\20\37\21!\22#\2%\2\'\2)\23+\2-\24/\25\61\26\63\27"+
		"\3\2\7\4\2C\\c|\3\2\62;\3\2\63;\5\2\62;CHch\4\2\13\13\"\"\2\u00a3\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2)\3\2\2\2"+
		"\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\3\65\3\2\2\2\5\67\3\2"+
		"\2\2\79\3\2\2\2\t;\3\2\2\2\13=\3\2\2\2\r?\3\2\2\2\17A\3\2\2\2\21C\3\2"+
		"\2\2\23E\3\2\2\2\25G\3\2\2\2\27N\3\2\2\2\31W\3\2\2\2\33\\\3\2\2\2\35b"+
		"\3\2\2\2\37f\3\2\2\2!k\3\2\2\2#o\3\2\2\2%q\3\2\2\2\'s\3\2\2\2)u\3\2\2"+
		"\2+~\3\2\2\2-\u0088\3\2\2\2/\u008a\3\2\2\2\61\u0093\3\2\2\2\63\u009a\3"+
		"\2\2\2\65\66\7=\2\2\66\4\3\2\2\2\678\7.\2\28\6\3\2\2\29:\7/\2\2:\b\3\2"+
		"\2\2;<\7?\2\2<\n\3\2\2\2=>\7,\2\2>\f\3\2\2\2?@\7\61\2\2@\16\3\2\2\2AB"+
		"\7-\2\2B\20\3\2\2\2CD\7*\2\2D\22\3\2\2\2EF\7+\2\2F\24\3\2\2\2GH\7u\2\2"+
		"HI\7k\2\2IJ\7i\2\2JK\7p\2\2KL\7g\2\2LM\7f\2\2M\26\3\2\2\2NO\7w\2\2OP\7"+
		"p\2\2PQ\7u\2\2QR\7k\2\2RS\7i\2\2ST\7p\2\2TU\7g\2\2UV\7f\2\2V\30\3\2\2"+
		"\2WX\7e\2\2XY\7j\2\2YZ\7c\2\2Z[\7t\2\2[\32\3\2\2\2\\]\7u\2\2]^\7j\2\2"+
		"^_\7q\2\2_`\7t\2\2`a\7v\2\2a\34\3\2\2\2bc\7k\2\2cd\7p\2\2de\7v\2\2e\36"+
		"\3\2\2\2fg\7n\2\2gh\7q\2\2hi\7p\2\2ij\7i\2\2j \3\2\2\2kl\7r\2\2lm\7w\2"+
		"\2mn\7v\2\2n\"\3\2\2\2op\t\2\2\2p$\3\2\2\2qr\t\3\2\2r&\3\2\2\2st\7a\2"+
		"\2t(\3\2\2\2u{\5#\22\2vz\5#\22\2wz\5%\23\2xz\5\'\24\2yv\3\2\2\2yw\3\2"+
		"\2\2yx\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2\2\2|*\3\2\2\2}{\3\2\2\2~\177"+
		"\t\4\2\2\177,\3\2\2\2\u0080\u0084\5+\26\2\u0081\u0083\5%\23\2\u0082\u0081"+
		"\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\u0089\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0089\7\62\2\2\u0088\u0080\3"+
		"\2\2\2\u0088\u0087\3\2\2\2\u0089.\3\2\2\2\u008a\u008b\7\62\2\2\u008b\u008c"+
		"\7z\2\2\u008c\u008e\3\2\2\2\u008d\u008f\t\5\2\2\u008e\u008d\3\2\2\2\u008f"+
		"\u0090\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\60\3\2\2"+
		"\2\u0092\u0094\7\17\2\2\u0093\u0092\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\u0096\7\f\2\2\u0096\u0097\3\2\2\2\u0097\u0098\b\31"+
		"\2\2\u0098\62\3\2\2\2\u0099\u009b\t\6\2\2\u009a\u0099\3\2\2\2\u009b\u009c"+
		"\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e\3\2\2\2\u009e"+
		"\u009f\b\32\2\2\u009f\64\3\2\2\2\n\2y{\u0084\u0088\u0090\u0093\u009c\3"+
		"\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}