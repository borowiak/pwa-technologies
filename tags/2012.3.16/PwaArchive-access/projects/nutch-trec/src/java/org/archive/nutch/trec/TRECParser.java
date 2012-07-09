/* Generated By:JavaCC: Do not edit this line. TRECParser.java */
package org.archive.nutch.trec;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.nutch.metadata.Metadata;
import org.apache.nutch.parse.Parse;
import org.apache.nutch.parse.ParseStatus;
import org.apache.nutch.parse.ParseUtil;
import org.apache.nutch.parse.ParseImpl;
import org.apache.nutch.protocol.Content;
import org.apache.nutch.crawl.CrawlDatum;
import org.apache.nutch.util.mime.MimeType;
import org.apache.nutch.util.mime.MimeTypeException;
import org.apache.nutch.fetcher.FetcherOutput;
import org.apache.nutch.fetcher.FetcherOutputFormat;
import org.apache.nutch.fetcher.Fetcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Date;
import java.io.IOException;
import java.lang.StackOverflowError;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.HashMap;
import java.util.Map;
import java.lang.StringBuilder;
import java.net.MalformedURLException;
import java.net.URL;
//TODO MC
import org.apache.nutch.metadata.Nutch; 
import org.archive.access.nutch.Nutchwax;
import org.apache.nutch.util.StringUtil;
import org.apache.nutch.scoring.ScoringFilters;
import org.apache.nutch.net.URLFilters;
import org.apache.nutch.net.URLNormalizers;
import org.apache.nutch.parse.ParseData;
import org.apache.nutch.parse.Outlink;
import org.archive.access.nutch.jobs.ImportArcs.WaxFetcherOutputFormat;
//TODO MC

public class TRECParser implements TRECParserConstants {
	private static boolean indexAll;
	private static  final String WHITESPACE = "\\s+";
	public static final String CONTENT_TYPE_KEY = "content-type";
	public static final String CONTENT_LENGTH = "contentLength";
	public static final String DATE_KEY = "date";
	public static final String TEXT_TYPE = "text/";
	public static final String APPLICATION_TYPE = "application/";
	public static final String DOCNO_KEY = "DOCNO";
	public static final String COLLECTION = "collection";
	public static final String ARC_NAME = "arcname";

	/* Date formats from apache-httpclient */
	public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
	public static final String PATTERN_RFC1036 = "EEEE, dd-MMM-yy HH:mm:ss zzz";
	public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";
	private static final Collection DATE_PATTERNS = Arrays.asList(
			new String[] { PATTERN_ASCTIME, PATTERN_RFC1036, PATTERN_RFC1123 } );
	public final Log LOG = LogFactory.getLog(TRECParser.class);


	public void main(String args[]) throws ParseException,
	IOException {
	}

	protected boolean skip(final String mimetype) {
		boolean decision = false;
		// Are we to index all content?
				if (!indexAll) {
					if ((mimetype == null) ||
							(!mimetype.startsWith(TEXT_TYPE) &&
									!mimetype.startsWith(APPLICATION_TYPE))) {
						// Skip any but basic types.
						decision = true;
					}
				}
				return decision;
	}
	public static Date parseDate(
			String dateValue) throws ParseException {

		if (dateValue.length() > 1
				&& dateValue.startsWith("'")
				&& dateValue.endsWith("'")
		) {
			dateValue = dateValue.substring (1, dateValue.length() - 1);
		}

		SimpleDateFormat dateParser = null;
		Iterator formatIter = DATE_PATTERNS.iterator();

		while (formatIter.hasNext()) {
			String format = (String) formatIter.next();
			if (dateParser == null) {
				dateParser = new SimpleDateFormat(format, Locale.US);
				dateParser.setTimeZone(TimeZone.getTimeZone("GMT"));
			} else {
				dateParser.applyPattern(format);
			}
			try {
				return dateParser.parse(dateValue);
			} catch (java.text.ParseException pe) {
				// ignore this exception, we will try the next format
			}
		}
		throw new ParseException("Unable to parse the date " + dateValue);
	}

	final public void Input(ParseUtil pu, OutputCollector output, JobConf conf, String segmentName, 
			String collectionName, String filename, URLNormalizers urlNormalizers, URLFilters filters) throws ParseException, IOException {
		Token docNumber = null;
		TRECDoc d = new TRECDoc();
		StringBuffer docBody = new StringBuffer();
		String mimetype = null;
		Metadata metaData = new Metadata();
		Date fetchDate = null;
		
		label_1:
			while (true) {
				switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
				case DOC_BEGIN:
					;
					break;
				default:
					jj_la1[0] = jj_gen;
				break label_1;
				}
				try {
				  try {
					jj_consume_token(DOC_BEGIN);
					label_2:
						while (true) {
							switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
							case 23:
							case 24:
							case 25:
							case 26:
								;
								break;
							default:
								jj_la1[1] = jj_gen;
							break label_2;
							}
							switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
							case 23:
								jj_consume_token(23);
								break;
							case 24:
								jj_consume_token(24);
								break;
							case 25:
								jj_consume_token(25);
								break;
							case 26:
								jj_consume_token(26);
								break;
							default:
								jj_la1[2] = jj_gen;
							jj_consume_token(-1);
							throw new ParseException();
							}
						}
					label_3:
						while (true) {
							switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
							case DOCNO_BEGIN:
								jj_consume_token(DOCNO_BEGIN);
								docNumber = jj_consume_token(DOCNO);
								jj_consume_token(DOCNO_END);
								label_4:
									while (true) {
										switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
										case 23:
										case 24:
										case 25:
										case 26:
											;
											break;
										default:
											jj_la1[3] = jj_gen;
										break label_4;
										}
										switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
										case 23:
											jj_consume_token(23);
											break;
										case 24:
											jj_consume_token(24);
											break;
										case 25:
											jj_consume_token(25);
											break;
										case 26:
											jj_consume_token(26);
											break;
										default:
											jj_la1[4] = jj_gen;
										jj_consume_token(-1);
										throw new ParseException();
										}
									}
								break;
							case OLDDOCNO_BEGIN:
								jj_consume_token(OLDDOCNO_BEGIN);
								jj_consume_token(DOCNO);
								jj_consume_token(OLDDOCNO_END);
								label_5:
									while (true) {
										switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
										case 23:
										case 24:
										case 25:
										case 26:
											;
											break;
										default:
											jj_la1[5] = jj_gen;
										break label_5;
										}
										switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
										case 23:
											jj_consume_token(23);
											break;
										case 24:
											jj_consume_token(24);
											break;
										case 25:
											jj_consume_token(25);
											break;
										case 26:
											jj_consume_token(26);
											break;
										default:
											jj_la1[6] = jj_gen;
										jj_consume_token(-1);
										throw new ParseException();
										}
									}
								break;
							default:
								jj_la1[7] = jj_gen;
							jj_consume_token(-1);
							throw new ParseException();
							}
							switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
							case DOCNO_BEGIN:
							case OLDDOCNO_BEGIN:
								;
								break;
							default:
								jj_la1[8] = jj_gen;
							break label_3;
							}
						}
						jj_consume_token(DOCHDR_BEGIN);
						docHdrBody(d);
						jj_consume_token(DOCHDR_END);
						docBody = new StringBuffer(body());
						switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
						case DOC_END:
							jj_consume_token(DOC_END);
							break;
						case 0:
							jj_consume_token(0);
							break;
						default:
							jj_la1[9] = jj_gen;
						jj_consume_token(-1);
						throw new ParseException();
						}
						label_6:
							while (true) {
								switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
								case 23:
								case 24:
								case 25:
								case 26:
									;
									break;
								default:
									jj_la1[10] = jj_gen;
								break label_6;
								}
								switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
								case 23:
									jj_consume_token(23);
									break;
								case 24:
									jj_consume_token(24);
									break;
								case 25:
									jj_consume_token(25);
									break;
								case 26:
									jj_consume_token(26);
									break;
								default:
									jj_la1[11] = jj_gen;
								jj_consume_token(-1);
								throw new ParseException();
								}
							}
				  }
				  catch (Error err) { // TODO MC - to catch 
					 LOG.error("Error:"+err.getMessage());
					 throw new ParseException(err.getMessage());
				  }			
				}
				catch (Exception e) {
					LOG.error("Parsing exception:"+e.toString());
					StringBuilder buffer = new StringBuilder(16000); // TODO MC
					Token t=null;	
					
					do {
						try {
							t = getNextToken();
							buffer.append(t.image);
						} 					 						
						catch (Error err) {
							LOG.error("Parsing nested error:"+err.toString());
						}
						catch (Exception enest) {
							LOG.error("Parsing nested exception:"+enest.toString());
						}
					} 
					while (t.kind != DOC_END && t.kind != EOF);
					docBody.append(buffer.toString()); // TODO MC
					if (t.kind != EOF) {
						// Eat up DOC_END.
						try {
							t = getNextToken();
						} 						 
						catch (Error err) {
							LOG.error("Parsing nested error2:"+err.toString());
						}
						catch (Exception enest) {
							LOG.error("Parsing nested exception2:"+enest.toString());
						}
					}					
					
					// If error, skip this doc. completely.
					//{if (true) return;} TODO MC - this line ignores all other files from gz files
					LOG.error("Parsing will continue with text:"); // TODO MC
					LOG.error(""+(new String(docBody.toString().getBytes()))); // TODO MC
				}				
				d.docNumber = docNumber.image;
				d.bodyLength = new Integer(docBody.length());
							
			
				/* TODO MC - normalize and filter URL*/
				String url=d.urlString;				
				try {
			      url = urlNormalizers.normalize(url,URLNormalizers.SCOPE_FETCHER);
			      url = filters.filter(url); // filter the url
			    }
			    catch (Exception e) {
			   	 	LOG.error("Error:"+e.getMessage());
			   	 	throw new ParseException(e.getMessage());
			    }
			    
				LOG.info("Importing DocNo:" + d.docNumber + " url:" + url + " oldurl:" +d.urlString);
			    if (url!=null) { // for instance if the url is too large then it is null		    	  			    	  			    		
				 
			    	//Go through headers
			    	for (Iterator it=d.headers.entrySet().iterator(); it.hasNext(); ) {
			    		Map.Entry me = (Map.Entry)it.next();
			    		String key = (String)me.getKey();
			    		String value = (String)me.getValue();
			    		// Find a mimetype
			    		if (key.toLowerCase().equals(
							CONTENT_TYPE_KEY)) {
			    			// Is it valid
			    			try {
			    				mimetype = value.toLowerCase().
			    				replaceAll(WHITESPACE,"-");
			    				if (mimetype == null) {
			    					mimetype = "no-type";
			    				}
			    				new MimeType(value.toLowerCase());
			    			} catch (MimeTypeException e) {
			    				mimetype = "no-type";
			    			}
			    			if (skip(mimetype)) { //XXX
			    			}
			    		}
			    		// Parse a fetch date from the http headers
			    		if (key.toLowerCase().equals(DATE_KEY)) {
			    			try {
			    				fetchDate = parseDate(value);
			    			} catch (ParseException e) {
			    				// Need to log this
			    				// Parse exception, default date will be inserted later
			    				LOG.error("Date Exception " + e.getMessage());
			    			}
			    		}
			    		// Add the rest of headers to the metadata
			    		metaData.set(key, value);
			    	}
			    	
			    	// Set metadata document number
			    	metaData.set(DOCNO_KEY,d.docNumber);
			    	// Set mimetype
			    	metaData.set(CONTENT_TYPE_KEY, mimetype);
			    	//Set length
			    	metaData.set(CONTENT_LENGTH, d.bodyLength.toString());
			    	// Set Segment Name
			    	metaData.set(Nutch.SEGMENT_NAME_KEY, segmentName);
			    	//Set md5
			    	metaData.set(Nutch.SIGNATURE_KEY, MD5Hash.digest(docBody.toString().getBytes()).toString());
			    	// Set collection
			    	metaData.set(COLLECTION,collectionName); // TODO MC
			    	// Set arcname
			    	metaData.set(ARC_NAME,filename); // TODO MC


			    	//If we didn't get a date Just use a random one.
			    	if (fetchDate == null) {
			    		fetchDate = new Date(Long.decode("1151693552").longValue());
			    	}              

			    	//start timer
			    	// long startTime = System.currentTimeMillis();

			    	//Make a content object
			    	Content content = new Content(url,url, docBody.toString().getBytes(), mimetype, metaData, conf);

			    	Parse parse = null;
			    	ParseStatus parseStatus;
			    	try {
			    		parse = pu.parse(content);
			    		parseStatus = parse.getData().getStatus();
			    	} 
			    	catch (final Exception e) {
			    		parseStatus = new ParseStatus(e);
			    		LOG.error("error: unknown "+parseStatus.toString());
			    		if(!parseStatus.isSuccess()) {
			    			LOG.error("parse failure");
			    		}
			    	}
			    	catch (StackOverflowError soe){
			    		parseStatus = new ParseStatus(soe);
			    		LOG.error("error: StackOverflowError "+parseStatus.toString());
			    		if(!parseStatus.isSuccess()) {
			    			LOG.error("parse failure");
			    		}
			    	}

			    	if(parseStatus.isSuccess()) {
			    		CrawlDatum datum = new CrawlDatum();
			    		datum.setStatus(CrawlDatum.STATUS_FETCH_SUCCESS);
			    		datum.setFetchTime(fetchDate.getTime());
					
			    		// Score at this stage is 1.0f.
			    		metaData.set(Nutch.SCORE_KEY, Float.toString(datum.getScore())); // TODO MC
																
			    		// WritableComparable outkey = new UTF8(d.urlString);
			    		WritableComparable outkey = new Text(url); 
			    		Writable outvalue = new FetcherOutput(datum, null, new ParseImpl(parse)); 								
										
			    		// output.collect(outkey, outvalue); 
			    		Text key=Nutchwax.generateWaxKey(outkey, collectionName);
			    		output.collect(key, outvalue);												
			    	}
			    }
					
				d = new TRECDoc();
				metaData = new Metadata();
			}

		jj_consume_token(0);
	}

	final public String body() throws ParseException {
		Token bd = null;
		StringBuilder buffer = new StringBuilder(16000);
		label_7:
			while (true) {
				switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
				case BODY:
					;
					break;
				default:
					jj_la1[12] = jj_gen;
				break label_7;
				}
				bd = jj_consume_token(BODY);
				buffer.append(bd.image);
			}
		{if (true) return(buffer.toString());}
		throw new Error("Missing return statement in function");
	}

	final public void docHdrBody(TRECDoc d) throws ParseException {
		Token hd = null;
		url(d);
		httpResponse(d);
		label_8:
			while (true) {
				header(d);
				switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
				case HEADER_LABEL:
					;
					break;
				default:
					jj_la1[13] = jj_gen;
				break label_8;
				}
			}
	}

	final public void header(TRECDoc d) throws ParseException {
		Token la = null, ct = null;
		StringBuilder buffer = new StringBuilder();
		try {
			la = jj_consume_token(HEADER_LABEL);
			label_9:
				while (true) {
					switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
					case HEADER_CONTENT:
						;
						break;
					default:
						jj_la1[14] = jj_gen;
					break label_9;
					}
					ct = jj_consume_token(HEADER_CONTENT);
					buffer.append(ct.image);
				}
			jj_consume_token(HEADER_END);
			d.headers.put(la.image.substring(0,la.image.length()-2), buffer.toString());
		} catch (ParseException e) {
			LOG.error(e.toString());
			Token t;
			do {
				t = getNextToken();
			} while (t.kind != DOC_END && t.kind != EOF);
		}
	}

	final public void url(TRECDoc d) throws ParseException {
		Token ur=null,b1=null;
		b1 = jj_consume_token(URL_BGN);
		ur = jj_consume_token(URLTXT);
		switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
		case URLEND:
			jj_consume_token(URLEND);
			break;
		case UMSGBGN:
			jj_consume_token(UMSGBGN);
			label_10:
				while (true) {
					switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
					case UMSGBODY:
						;
						break;
					default:
						jj_la1[15] = jj_gen;
					break label_10;
					}
					jj_consume_token(UMSGBODY);
				}
			jj_consume_token(UMSGEND);
			break;
		default:
			jj_la1[16] = jj_gen;
		jj_consume_token(-1);
		throw new ParseException();
		}
		d.urlString = b1.image + ur.image;
	}

	final public void httpResponse(TRECDoc d) throws ParseException {
		Token code=null,b1=null,b2=null,b3=null;
		code = jj_consume_token(CODE);
		label_11:
			while (true) {
				switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
				case MSGU:
					;
					break;
				default:
					jj_la1[17] = jj_gen;
				break label_11;
				}
				b2 = jj_consume_token(MSGU);
			}
		b3 = jj_consume_token(HEND);
		d.responseCode = code.image;
	}

	public TRECParserTokenManager token_source;
	SimpleCharStream jj_input_stream;
	public Token token, jj_nt;
	private int jj_ntk;
	private int jj_gen;
	final private int[] jj_la1 = new int[18];
	static private int[] jj_la1_0;
	static {
		jj_la1_0();
	}
	private static void jj_la1_0() {
		jj_la1_0 = new int[] {0x2,0x7800000,0x7800000,0x7800000,0x7800000,0x7800000,0x7800000,0x14,0x14,0x200001,0x7800000,0x7800000,0x400000,0x200,0x400,0x80000,0x60000,0x4000,};
	}

	public TRECParser(java.io.InputStream stream) {
		this(stream, null);
	}
	public TRECParser(java.io.InputStream stream, String encoding) {
		try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
		token_source = new TRECParserTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 18; i++) jj_la1[i] = -1;
	}

	public void ReInit(java.io.InputStream stream) {
		ReInit(stream, null);
	}
	public void ReInit(java.io.InputStream stream, String encoding) {
		try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 18; i++) jj_la1[i] = -1;
	}

	public TRECParser(java.io.Reader stream) {
		jj_input_stream = new SimpleCharStream(stream, 1, 1);
		token_source = new TRECParserTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 18; i++) jj_la1[i] = -1;
	}

	public void ReInit(java.io.Reader stream) {
		jj_input_stream.ReInit(stream, 1, 1);
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 18; i++) jj_la1[i] = -1;
	}

	public TRECParser(TRECParserTokenManager tm) {
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 18; i++) jj_la1[i] = -1;
	}

	public void ReInit(TRECParserTokenManager tm) {
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 18; i++) jj_la1[i] = -1;
	}

	final private Token jj_consume_token(int kind) throws ParseException {
		Token oldToken;
		if ((oldToken = token).next != null) token = token.next;
		else token = token.next = token_source.getNextToken();
		jj_ntk = -1;	
		if (token.kind == kind) {
			jj_gen++;
			return token;
		}
		token = oldToken;
		jj_kind = kind;
		throw generateParseException();
	}

	final public Token getNextToken() {
		if (token.next != null) token = token.next;
		else token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		jj_gen++;
		return token;
	}

	final public Token getToken(int index) {
		Token t = token;
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

	public ParseException generateParseException() {
		jj_expentries.removeAllElements();
		boolean[] la1tokens = new boolean[27];
		for (int i = 0; i < 27; i++) {
			la1tokens[i] = false;
		}
		if (jj_kind >= 0) {
			la1tokens[jj_kind] = true;
			jj_kind = -1;
		}
		for (int i = 0; i < 18; i++) {
			if (jj_la1[i] == jj_gen) {
				for (int j = 0; j < 32; j++) {
					if ((jj_la1_0[i] & (1<<j)) != 0) {
						la1tokens[j] = true;
					}
				}
			}
		}
		for (int i = 0; i < 27; i++) {
			if (la1tokens[i]) {
				jj_expentry = new int[1];
				jj_expentry[0] = i;
				jj_expentries.addElement(jj_expentry);
			}
		}
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

}

class TRECDoc {
	public String docNumber = "";
	public String urlString  = "";
	public String responseCode = "";
	public Integer bodyLength = null;
	Map<String, String> headers = new HashMap<String, String>();
}
