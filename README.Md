# JavaMovieStreamerApp

### Simple Java Servlet that streams (most) movie files. Served from local filesystem

#### Technologies:

- Maven
- Spring Boot
- JSP
- jQuery
- <code>html5 \<video> tag</code>

##### Installation and usage:

<table>
<tr> git clone https://github.com/Gal-Ofir/JavaMovieStreamerApp.git </tr>
<tr> change <i>movie.properties</i> value <b>movies.directory</b> to your desired movie directory </tr>
<tr> mvn clean install -DskipTests </tr>
</table>

<b>IF YOU WISH TO RUN TESTS</b>

First, change <i>movie.test.properties</i> values:
<table>
<tr>test.dir=YOUR_TEST_DIR</tr>
<tr>test.dir.dir.in.dir=A_DIRECTORY(NOT FILE)_IN_TEST_DIR<tr>
<tr>test.filename=A_(VALID)_FILE_IN_TEST_DIR</tr>
</table>

Then run
<code>mvn clean install </code>

