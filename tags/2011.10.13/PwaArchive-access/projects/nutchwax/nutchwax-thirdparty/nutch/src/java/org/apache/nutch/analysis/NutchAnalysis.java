/* Generated By:JavaCC: Do not edit this line. NutchAnalysis.java */
package org.apache.nutch.analysis;

import java.io.StringReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.nutch.searcher.Query;
import org.apache.nutch.searcher.QueryFilters;
import org.apache.nutch.searcher.Query.Clause;
import org.apache.nutch.util.NutchConfiguration;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;

import java.io.*;
import java.util.*;

/** The JavaCC-generated Nutch lexical analyzer and query parser. */
public class NutchAnalysis implements NutchAnalysisConstants {

  private static final String[] STOP_WORDS = {
/*    "a", "and", "are", "as", "at", "be", "but", "by",
    "for", "if", "in", "into", "is", "it",
    "no", "not", "of", "on", "or", "s", "such",
    "t", "that", "the", "their", "then", "there", "these",
    "they", "this", "to", "was", "will", "with"
 */
  };

  private static final Set STOP_SET = StopFilter.makeStopSet(STOP_WORDS);

  private Analyzer analyzer = null;
  private String queryString;
  private QueryFilters queryFilters;


  /** Constructs a nutch analysis. */
  public NutchAnalysis(String query, Analyzer analyzer) {
    this(new FastCharStream(new StringReader(query)));
    this.analyzer = analyzer;
  }

  /** True iff word is a stop word.  Stop words are only removed from queries.
   * Every word is indexed.  */
  public static boolean isStopWord(String word) {
    return STOP_SET.contains(word);
  }

  /** Construct a query parser for the text in a reader. */
  public static Query parseQuery(String queryString, Configuration conf) throws IOException {
    return parseQuery(queryString, null, conf);
  }

  /** Construct a query parser for the text in a reader. */
  public static Query parseQuery(String queryString, Analyzer analyzer, Configuration conf)
    throws IOException {
    NutchAnalysis parser = new NutchAnalysis(
          queryString, (analyzer != null) ? analyzer : new NutchDocumentAnalyzer(conf));
    parser.queryString = queryString;
    parser.queryFilters = new QueryFilters(conf);
    return parser.parse(conf);
  }

  /** For debugging. */
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      System.out.print("Query: ");
      String line = in.readLine();
      System.out.println(parseQuery(line, NutchConfiguration.create()));
    }
  }

/** Parse a query. */
  final public Query parse(Configuration conf) throws ParseException {
  Query query = new Query(conf);
  ArrayList terms;
  Token token;
  String field;
  boolean stop;
  boolean prohibited;
    nonOpOrTerm();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case WORD:
      case ACRONYM:
      case SIGRAM:
      case PLUS:
      case MINUS:
      case QUOTE:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      stop=true; prohibited=false; field = Clause.DEFAULT_FIELD;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
          jj_consume_token(PLUS);
              stop=false;
          break;
        case MINUS:
          jj_consume_token(MINUS);
                                        stop=false;prohibited=true;
          break;
        default:
          jj_la1[1] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[2] = jj_gen;
        ;
      }
      if (jj_2_1(2147483647)) {
        token = jj_consume_token(WORD);
        jj_consume_token(COLON);
                             field = token.image;
      } else {
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case QUOTE:
        terms = phrase(field);
                           stop=false;
        break;
      case WORD:
      case ACRONYM:
      case SIGRAM:
        // quoted terms or
              terms = compound(field);
        break;
      default:
        jj_la1[3] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      nonOpOrTerm();
      String[] array = (String[])terms.toArray(new String[terms.size()]);

      if (stop
          && field == Clause.DEFAULT_FIELD
          && terms.size()==1
          && isStopWord(array[0])) {
        // ignore stop words only when single, unadorned terms in default field
      } else {
        if (prohibited)
          query.addProhibitedPhrase(array, field);
        else
          query.addRequiredPhrase(array, field);
      }
    }
    {if (true) return query;}
    throw new Error("Missing return statement in function");
  }

/** Parse an explcitly quoted phrase query.  Note that this may return a single
 * term, a trivial phrase.*/
  final public ArrayList phrase(String field) throws ParseException {
  int start;
  int end;
  ArrayList result = new ArrayList();
  String term;
    jj_consume_token(QUOTE);
    start = token.endColumn;
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
      case COLON:
      case SLASH:
      case DOT:
      case ATSIGN:
      case APOSTROPHE:
      case WHITE:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_2;
      }
      nonTerm();
    }
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case WORD:
      case ACRONYM:
      case SIGRAM:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_3;
      }
      term = term();
                    result.add(term);
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
        case MINUS:
        case COLON:
        case SLASH:
        case DOT:
        case ATSIGN:
        case APOSTROPHE:
        case WHITE:
          ;
          break;
        default:
          jj_la1[6] = jj_gen;
          break label_4;
        }
        nonTerm();
      }
    }
    end = token.endColumn;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case QUOTE:
      jj_consume_token(QUOTE);
      break;
    case 0:
      jj_consume_token(0);
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    if (this.queryFilters.isRawField(field)) {
      result.clear();
      result.add(queryString.substring(start, end));
    }
    {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

/** Parse a compound term that is interpreted as an implicit phrase query.
 * Compounds are a sequence of terms separated by infix characters.  Note that
 * htis may return a single term, a trivial compound. */
  final public ArrayList compound(String field) throws ParseException {
  int start;
  ArrayList result = new ArrayList();
  String term;
  StringBuffer terms = new StringBuffer();
    start = token.endColumn;
    term = term();
    terms.append(term).append(" ");
    //result.add(term);

    label_5:
    while (true) {
      if (jj_2_2(2147483647)) {
        ;
      } else {
        break label_5;
      }
      label_6:
      while (true) {
        infix();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
        case MINUS:
        case COLON:
        case SLASH:
        case DOT:
        case ATSIGN:
        case APOSTROPHE:
          ;
          break;
        default:
          jj_la1[8] = jj_gen;
          break label_6;
        }
      }
      term = term();
      terms.append(term).append(" ");
      //result.add(term);

    }
    if (this.queryFilters.isRawField(field)) {
//      result.clear();
      result.add(queryString.substring(start, token.endColumn));

    } else {
      org.apache.lucene.analysis.Token token;
      TokenStream tokens = analyzer.tokenStream(
                              field, new StringReader(terms.toString()));

      while (true) {
        try {
          token = tokens.next();
        } catch (IOException e) {
          token = null;
        }
        if (token == null) { break; }
        result.add(token.termText());
      }
      try {
        tokens.close();
      } catch (IOException e) {
        // ignore
      }
    }
    {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

/** Parse a single term. */
  final public String term() throws ParseException {
  Token token;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case WORD:
      token = jj_consume_token(WORD);
      break;
    case ACRONYM:
      token = jj_consume_token(ACRONYM);
      break;
    case SIGRAM:
      token = jj_consume_token(SIGRAM);
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return token.image;}
    throw new Error("Missing return statement in function");
  }

/** Parse anything but a term or a quote. */
  final public void nonTerm() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case WHITE:
      jj_consume_token(WHITE);
      break;
    case PLUS:
    case MINUS:
    case COLON:
    case SLASH:
    case DOT:
    case ATSIGN:
    case APOSTROPHE:
      infix();
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void nonTermOrEOF() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
    case MINUS:
    case COLON:
    case SLASH:
    case DOT:
    case ATSIGN:
    case APOSTROPHE:
    case WHITE:
      nonTerm();
      break;
    case 0:
      jj_consume_token(0);
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

/** Parse anything but a term or an operator (plur or minus or quote). */
  final public void nonOpOrTerm() throws ParseException {
    label_7:
    while (true) {
      if (jj_2_3(2)) {
        ;
      } else {
        break label_7;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case WHITE:
        jj_consume_token(WHITE);
        break;
      case COLON:
      case SLASH:
      case DOT:
      case ATSIGN:
      case APOSTROPHE:
        nonOpInfix();
        break;
      case PLUS:
      case MINUS:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
          jj_consume_token(PLUS);
          break;
        case MINUS:
          jj_consume_token(MINUS);
          break;
        default:
          jj_la1[12] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        nonTermOrEOF();
        break;
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

/** Characters which can be used to form compound terms. */
  final public void infix() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
      jj_consume_token(PLUS);
      break;
    case MINUS:
      jj_consume_token(MINUS);
      break;
    case COLON:
    case SLASH:
    case DOT:
    case ATSIGN:
    case APOSTROPHE:
      nonOpInfix();
      break;
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

/** Parse infix characters except plus and minus. */
  final public void nonOpInfix() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COLON:
      jj_consume_token(COLON);
      break;
    case SLASH:
      jj_consume_token(SLASH);
      break;
    case DOT:
      jj_consume_token(DOT);
      break;
    case ATSIGN:
      jj_consume_token(ATSIGN);
      break;
    case APOSTROPHE:
      jj_consume_token(APOSTROPHE);
      break;
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  final private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  final private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  final private boolean jj_3_1() {
    if (jj_scan_token(WORD)) return true;
    if (jj_scan_token(COLON)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_8()) {
    jj_scanpos = xsp;
    if (jj_3R_9()) return true;
    }
    return false;
  }

  final private boolean jj_3R_16() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(7)) {
    jj_scanpos = xsp;
    if (jj_scan_token(8)) {
    jj_scanpos = xsp;
    if (jj_3R_22()) return true;
    }
    }
    return false;
  }

  final private boolean jj_3_3() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(15)) {
    jj_scanpos = xsp;
    if (jj_3R_12()) {
    jj_scanpos = xsp;
    if (jj_3R_13()) return true;
    }
    }
    return false;
  }

  final private boolean jj_3R_25() {
    if (jj_3R_24()) return true;
    return false;
  }

  final private boolean jj_3R_27() {
    if (jj_3R_16()) return true;
    return false;
  }

  final private boolean jj_3R_20() {
    if (jj_3R_11()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_25()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  final private boolean jj_3R_10() {
    if (jj_3R_16()) return true;
    return false;
  }

  final private boolean jj_3R_19() {
    if (jj_3R_24()) return true;
    return false;
  }

  final private boolean jj_3_2() {
    Token xsp;
    if (jj_3R_10()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_10()) { jj_scanpos = xsp; break; }
    }
    if (jj_3R_11()) return true;
    return false;
  }

  final private boolean jj_3R_23() {
    if (jj_3R_24()) return true;
    return false;
  }

  final private boolean jj_3R_18() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_23()) {
    jj_scanpos = xsp;
    if (jj_scan_token(0)) return true;
    }
    return false;
  }

  final private boolean jj_3R_13() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(7)) {
    jj_scanpos = xsp;
    if (jj_scan_token(8)) return true;
    }
    if (jj_3R_18()) return true;
    return false;
  }

  final private boolean jj_3R_9() {
    if (jj_3R_15()) return true;
    return false;
  }

  final private boolean jj_3R_14() {
    if (jj_scan_token(QUOTE)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_19()) { jj_scanpos = xsp; break; }
    }
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_20()) { jj_scanpos = xsp; break; }
    }
    xsp = jj_scanpos;
    if (jj_scan_token(9)) {
    jj_scanpos = xsp;
    if (jj_scan_token(0)) return true;
    }
    return false;
  }

  final private boolean jj_3R_24() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(15)) {
    jj_scanpos = xsp;
    if (jj_3R_27()) return true;
    }
    return false;
  }

  final private boolean jj_3R_26() {
    if (jj_3R_16()) return true;
    return false;
  }

  final private boolean jj_3R_21() {
    Token xsp;
    if (jj_3R_26()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_26()) { jj_scanpos = xsp; break; }
    }
    if (jj_3R_11()) return true;
    return false;
  }

  final private boolean jj_3R_22() {
    if (jj_3R_17()) return true;
    return false;
  }

  final private boolean jj_3R_8() {
    if (jj_3R_14()) return true;
    return false;
  }

  final private boolean jj_3R_12() {
    if (jj_3R_17()) return true;
    return false;
  }

  final private boolean jj_3R_11() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(1)) {
    jj_scanpos = xsp;
    if (jj_scan_token(2)) {
    jj_scanpos = xsp;
    if (jj_scan_token(3)) return true;
    }
    }
    return false;
  }

  final private boolean jj_3R_15() {
    if (jj_3R_11()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_21()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  final private boolean jj_3R_17() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(10)) {
    jj_scanpos = xsp;
    if (jj_scan_token(11)) {
    jj_scanpos = xsp;
    if (jj_scan_token(12)) {
    jj_scanpos = xsp;
    if (jj_scan_token(13)) {
    jj_scanpos = xsp;
    if (jj_scan_token(14)) return true;
    }
    }
    }
    }
    return false;
  }

  public NutchAnalysisTokenManager token_source;
  public Token token, jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  public boolean lookingAhead = false;
  private boolean jj_semLA;
  private int jj_gen;
  final private int[] jj_la1 = new int[16];
  static private int[] jj_la1_0;
  static {
      jj_la1_0();
   }
   private static void jj_la1_0() {
      jj_la1_0 = new int[] {0x38e,0x180,0x180,0x20e,0xfd80,0xe,0xfd80,0x201,0x7d80,0xe,0xfd80,0xfd81,0x180,0xfd80,0x7d80,0x7c00,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[3];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  public NutchAnalysis(CharStream stream) {
    token_source = new NutchAnalysisTokenManager(stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(CharStream stream) {
    token_source.ReInit(stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public NutchAnalysis(NutchAnalysisTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(NutchAnalysisTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  final private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  final private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }

  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

  final public Token getToken(int index) {
    Token t = lookingAhead ? jj_scanpos : token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  final private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.Vector jj_expentries = new java.util.Vector();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      boolean exists = false;
      for (java.util.Enumeration e = jj_expentries.elements(); e.hasMoreElements();) {
        int[] oldentry = (int[])(e.nextElement());
        if (oldentry.length == jj_expentry.length) {
          exists = true;
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              exists = false;
              break;
            }
          }
          if (exists) break;
        }
      }
      if (!exists) jj_expentries.addElement(jj_expentry);
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  public ParseException generateParseException() {
    jj_expentries.removeAllElements();
    boolean[] la1tokens = new boolean[20];
    for (int i = 0; i < 20; i++) {
      la1tokens[i] = false;
    }
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 16; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 20; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.addElement(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.elementAt(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  final public void enable_tracing() {
  }

  final public void disable_tracing() {
  }

  final private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 3; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  final private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
