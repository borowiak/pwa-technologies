# 2015.0?.?? ([r4](https://code.google.com/p/pwa-technologies/source/detail?r=4)??) #

  * Consistency between PWA and Opensearch.
  * Change URL search results page to provide version dates with links to archived pages using URLs following the Wayback syntax.



# 2013.10.14 ([r480](https://code.google.com/p/pwa-technologies/source/detail?r=480)) #
TagCloudSetup updated.

A full dump of release 480 is available at:
  * http://sobre.arquivo.pt/~pwa/PWA-TechnologiesSourceCodeDump22-11-2013/
  * https://drive.google.com/folderview?id=0B9fSuJY2vxOESmltSmlIUTJoWDA&usp=sharing
  * https://www.dropbox.com/sh/qtdfrw4xu3qxiqn/Whva8XSvfB
  * http://visibilidade.net/awp/PWA-TechnologiesSourceCodeDump22-11-2013/
  * FCCN address (DVDs and Tape backups, check www.fccn.pt): Campus do Laboratório Nacional de Engenharia Civil, LNEC, Av. do Brasil n.º 101, 1700-066 LISBOA, Portugal
  * FCCN data center: backup at server t1.tomba.fccn.pt:/home/nutchwax/pwa/PWA-TechnologiesSourceCodeDump22-11-2013



# 2013.08.05 ([r478](https://code.google.com/p/pwa-technologies/source/detail?r=478)) #
Homepage was updated with new values of archived data.

<br>


<h1>2013.05.17 (<a href='https://code.google.com/p/pwa-technologies/source/detail?r=475'>r475</a>)</h1>
<h3>New:</h3>

<ul><li>Added <code>inspector</code> utility for debugging purposes</li></ul>

<h3>Change:</h3>

<ul><li>Corrected jar files dependencies<br>
</li><li>Fixed XSS vulnerability in spell checker project<br>
</li><li>Minor fixes from code quality platform<br>
</li><li>Changed the location of hunspell</li></ul>

<br>


<h1>2012.07.16 (<a href='https://code.google.com/p/pwa-technologies/source/detail?r=433'>r433</a>)</h1>
<h3>Change:</h3>

<ul><li>Changed information of the number of documents available for searching<br>
</li><li>Added link to see more highlights</li></ul>


<br>


<h1>2012.07.10 (<a href='https://code.google.com/p/pwa-technologies/source/detail?r=411'>r411</a>)</h1>
<h3>New:</h3>

<ul><li>Path to blacklist file is configurable. To allow adding new documents to blacklist, we added possibility of changing the path to the blacklist.</li></ul>

<h3>Change:</h3>

<ul><li>Added web analytics tracking when displaying archived pages<br>
</li><li>Only translate the datepicker to PT when browsing in that language.</li></ul>

<br>

<h1>2012.3.30 (<a href='https://code.google.com/p/pwa-technologies/source/detail?r=401'>r401</a>)</h1>

<h3>Changes:</h3>
<ul><li>A wrong configuration was presenting the result page as the intro page.</li></ul>


<br>
<h1>2012.3.20 (<a href='https://code.google.com/p/pwa-technologies/source/detail?r=400'>r400</a>)</h1>

<h3>Changes:</h3>
<ul><li>Added information for testing</li></ul>


<br>
<h1>2012.3.16 (<a href='https://code.google.com/p/pwa-technologies/source/detail?r=399'>r399</a>)</h1>

<h3>New:</h3>
<ul><li>"PwaLogsMiner" project added. This code is used for processing the access logs<br>
</li><li>Apply TFxIDF and BM25 functions over all index fields.<br>
</li><li>Added a new temporal feature for the ranking algorithm. It uses the number of versions and time span between changed versions.</li></ul>

<h3>Changes:</h3>
<ul><li>Changed the threshold for the minimum number of terms that occur only in the anchor field when matching a document.<br>
</li><li>Adding dependencies no longer available on repositories.<br>
</li><li>Updated the Portuguese texts to respect the new Portuguese Ortographic Agreement.<br>
</li><li>Increased the number of matched documents returned by Nutch to 2K.<br>
</li><li>Several bugfixes in the TFxIDF code.<br>
</li><li>Uped the number of anchor terms used by the ranking algorithm.<br>
</li><li>Fixed the project building that failed due to missing libs and classes.<br>
</li><li>Created an in-project maven repository to handle deprecated/old dependencies.<br>
</li><li>Index servers response timeout set to 60 seconds.<br>
</li><li>The memcache timeout set to 5 seconds.<br>
</li><li>Updated information and resources links for project resources in Maven file.<br>
</li><li>Other fixes.</li></ul>

<h3>Removed:</h3>
<ul><li>Remove the WERA sub-project. We don't use it and seems deprecated.</li></ul>


<br>
<h1>2012.1.10 (<a href='https://code.google.com/p/pwa-technologies/source/detail?r=346'>r346</a>)</h1>

<h3>Changes:</h3>
<ul><li>Fixed a problem where queries with quotes returned zero results.</li></ul>


<br>
<h1>2012.1.5 (<a href='https://code.google.com/p/pwa-technologies/source/detail?r=345'>r345</a>)</h1>

<h3>New:</h3>
<ul><li>Added the code of the "PwaProcessor" project. This project facilitates the processing of archived files in the ARC format.</li></ul>

<h3>Changes:</h3>
<ul><li>The grid of versions on the history page was incorrectly being filled.<br>
</li><li>One of the i18n files wasn't being called.<br>
</li><li>Remove old, uneeded UI files.<br>
</li><li>One of the i18n files wasn't being called.</li></ul>


<br>
<h1>2012.1.2 (<a href='https://code.google.com/p/pwa-technologies/source/detail?r=337'>r337</a>)</h1>

<h3>New:</h3>
<ul><li>New graphical layout<br>
</li><li>Added several new term-based and temporal ranking features (BM25, TFxIDF, PageRank, URLDepth, Age, BoostNewer, BoostOlder, URLSlash, BoostNewerOlder, LuceneSimilarity, MinSpan, LinInlinks, NumberVersions, SPanVersions, TimePointDivergence)<br>
</li><li>Added memcached/membase support for global data sharing between all collections.<br>
</li><li>Added the code of the spellchecker(PwaSpellchecker)</li></ul>

<h3>Changes:</h3>
<ul><li>A wrong date was being displayed for search results.<br>
</li><li>Fixed cases where specific types of queries changed the language of the UI.<br>
</li><li>Fixed several issues where the UI language reverted to the default language.<br>
</li><li>Fix XHTML validation errors on several pages.<br>
</li><li>Lucene and Nutch similarity function normalized with an exponential decay.<br>
</li><li>Ranking functions updated in configuration file.<br>
</li><li>Fix integer concatenation of floats division on the scorer.<br>
</li><li>Several bugfixes on ranking features and algorithms.<br>
</li><li>Caches for URL version and flags of new version were removed.<br>
</li><li>Change spellchecker to use Hunspell instead of Aspell.<br>
</li><li>Changes and updated in the footer and sponsors.<br>
</li><li>Force the request encoding before it is read/used.<br>
</li><li>Deprecating taglibs-i18n on the OpenSearch JSP in favor of taglibs-fmt.<br>
</li><li>Fixed missing dependencies.<br>
</li><li>I18n updates and fixes.<br>
</li><li>Several UI fixes.<br>
</li><li>Some code refactoring.<br>
</li><li>other bugfixes .</li></ul>

<h3>Removed:</h3>
<ul><li>Removed unused code.<br>
</li><li>Remove non-working wayback and spellchecker configurations.<br>
</li><li>Removed lucene cache. It has not been used.<br>
</li><li>Removed old parameter "search.max.hits" in favor of "max.fulltext.matches.ranked".</li></ul>


<br>
<h1>2011.12.06 (<a href='https://code.google.com/p/pwa-technologies/source/detail?r=207'>r207</a>)</h1>

<h3>Changes:</h3>
<ul><li>Fixed several problems in the opensearch response that were not respecting<br>
the specification.<br>
</li><li>PWA xsd, for opensearch, changed.</li></ul>


<br>
<h1>2011.10.20 (<a href='https://code.google.com/p/pwa-technologies/source/detail?r=199'>r199</a>)</h1>

<h3>Changes:</h3>
<ul><li>Switch old MCTES logo for the temporary logo of the MEC(Portuguese Ministry of<br>
Science, Technology and Higher Education).</li></ul>


<br>
<h1>2011.10.13 (<a href='https://code.google.com/p/pwa-technologies/source/detail?r=192'>r192</a>)</h1>

<h3>Changes:</h3>
<ul><li>Fix unescaped ampersand and wrong MIME-type for the OpenSearch RSS</li></ul>


<br>
<h1>2011.10.11 (<a href='https://code.google.com/p/pwa-technologies/source/detail?r=191'>r191</a>)</h1>

<h3>New:</h3>
<ul><li>Added OpenSearch auto-discovery by browsers (<a href='https://code.google.com/p/pwa-technologies/issues/detail?id=1'>issue 1</a>).<br>
</li><li>Added XML Schema for the PWA namespace.</li></ul>

<h3>Changes:</h3>
<ul><li>Updated OpenSearch code to spec 1.1.<br>
</li><li>Added OpenSearch Time extension namespace.<br>
</li><li>Added RFC3339 to opensearch time extension.<br>
</li><li>Remove old and commented code of the opensearch service.<br>
</li><li>OpenSearch bug fixes.<br>
</li><li>Read global parameters for the ranking algorithms instead of those being hard-coded<br>
</li><li>Update the OpenSearch description to allow the OpenSearch time extension for RSS+XML (<a href='https://code.google.com/p/pwa-technologies/issues/detail?id=2'>issue 2</a>).</li></ul>


<br>
<h1>2011.7.29 (<a href='https://code.google.com/p/pwa-technologies/source/detail?r=151'>r151</a>)</h1>

<h3>New:</h3>
<ul><li>Import of the Portuguese Web Archive code<br>
</li><li>Added resource files and management scripts</li></ul>

<h3>Change:</h3>
<ul><li>Added info to the project configuration files<br>
</li><li>Fix an overwrite with old configuration files and compiling the project.</li></ul>

<h3>Removed:</h3>
<ul><li>Deprecated classes and libraries