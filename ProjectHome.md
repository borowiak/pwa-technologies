The Portuguese Web Archive (PWA) main goal is the preservation and access of web contents that are no longer available online.

During the developing of the PWA IR (information retrieval) system we faced limitations in searching speed, quality of results, scalability and usability. To cope with this, we modified the archive-access project (http://archive-access.sourceforge.net/) to support our web archive IR requirements. Nutchwax, Nutch and Wayback’s code were adapted to meet the requirements. Several optimizations were added, such as simplifications in the way document versions are searched and several bottlenecks were resolved.

The PWA search engine is a public service at http://archive.pt and a research platform for web archiving. As it predecessor Nutch, it runs over Hadoop clusters for distributed computing following the map-reduce paradigm. Its major features include fast full-text search, URL search, phrase search, faceted search (date, format, site), and sorting by relevance and date.

The PWA search engine is highly scalable and its architecture is flexible enough to enable the deployment of different configurations to respond to the different needs. Currently, it serves an archive collection searchable by full-text with 180 million documents ranging between 1996 and 2010.


<a href='https://code.google.com/p/pwa-technologies/wiki/MainFeatures'>Main features</a>

To access the source code:
  * <a href='https://code.google.com/p/pwa-technologies/source/checkout'>Check-out the latest source code through SVN or</a>;
  * <a href='http://sobre.arquivo.pt/~pwa/PWA-TechnologiesSourceCodeDump22-11-2013/'>Download the dump files of this project generated in 22 November, 2013.</a>

Follow these steps to deploy the system:
  1. <a href='https://code.google.com/p/pwa-technologies/wiki/Compile'>Compile</a>.
  1. <a href='https://code.google.com/p/pwa-technologies/wiki/Install'>Install</a>.
  1. <a href='https://code.google.com/p/pwa-technologies/wiki/Operation'>Operation</a>.



The system provides an `OpenSearch API` to let you embed the search results into your website or application:
  1. <a href='https://code.google.com/p/pwa-technologies/wiki/OpenSearch'>OpenSearch</a>.

See more information at <a href='https://code.google.com/p/pwa-technologies/w/list'>Wiki</a>.