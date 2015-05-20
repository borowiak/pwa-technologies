This code was developed and tested in the Linux environment (Red Hat Enterprise Linux 5).

# Requirements #

Maven 2.x:
  * download from http://maven.apache.org/download.html
  * add bin directory to PATH
Apache Ant:
  * yum install ant
  * (see more at http://ant.apache.org/manual/install.html)
install Subversion:
  * yum install subversion
  * (see more at http://subversion.apache.org/packages.html)
Java SE 6.x (at least):
  * export JAVA\_HOME=/usr/java/default
  * (see more at http://www.oracle.com/technetwork/java/javase/downloads/index.html)
Apache Tomcat (5.5 at least):
  * (see http://tomcat.apache.org/download-55.cgi)

# Step-by-step #

Checkout Hadoop (branch-0.14):
  * `svn co http://svn.apache.org/repos/asf/hadoop/common/branches/branch-0.14 hadoop`
Install Hadoop:
  * `cd hadoop`
  * `ant` <br>
<ul><li><code>ant jar</code> <br>
</li><li><code>mvn install:install-file -DgroupId=org.apache -DartifactId=hadoop -Dversion=0.14.5-dev-core -Dpackaging=jar -Dfile=build/hadoop-0.14.5-dev-core.jar</code>
</li></ul><blockquote>This version of Hadoop (<a href='http://hadoop.apache.org/'>http://hadoop.apache.org/</a>) must be used for all mapreduce processing.<br>
Checkout PwaLucene + PwaArchiveAccess:<br>
</blockquote><ul><li><code>svn checkout http://pwa-technologies.googlecode.com/svn/trunk/ pwa-technologies</code>
Install PwaLucene:<br>
</li><li><code>cd pwa-technologies/PwaLucene</code>
</li><li><code>mvn install</code>
Install PwaArchiveAccess:<br>
</li><li><code>cd pwa-technologies/PwaArchive-access</code>
</li><li><code>mvn install</code>
</li><li><a href='https://code.google.com/p/pwa-technologies/wiki/ConfigureSearch?ts=1310028662&updated=ConfigureSearch'>configure</a>
</li><li><code>mvn install (only if configuration was changed)</code></li></ul>

The JAR and WAR files are available in:<br>
<ul><li><code>pwa-technologies/PwaArchive-access/projects/nutchwax/nutchwax-job/target/nutchwax-job-0.11.0-SNAPSHOT.jar</code>
</li><li><code>pwa-technologies/PwaArchive-access/projects/nutchwax/nutchwax-webapp/target/nutchwax-webapp-0.11.0-SNAPSHOT.war</code>
</li><li><code>pwa-technologies/PwaArchive-access/projects/wayback/wayback-webapp/target/wayback-1.2.1.war</code>
</li><li><code>pwa-technologies/PwaLucene/target/pwalucene-1.0.0-SNAPSHOT.jar</code></li></ul>

<h1>Others</h1>

Symbolic link of nutch for nutch-trec:<br>
<ul><li><code>cd pwa-technologies/PwaArchive-access</code>
</li><li><code>ln -s  ../../projects/nutchwax/nutchwax-thirdparty/nutch/ projects/nutch-trec/</code>
</li></ul><blockquote>This is only necessary if you will use the TREC datasets for tests.