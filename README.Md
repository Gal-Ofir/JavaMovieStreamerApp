# JavaMovieStreamerApp

### Simple Java Servlet that streams (most) movie files. Served from local filesystem

#### Technologies:

- Maven
- Spring Boot
- JSP
- jQuery
- <code>html5 \<video> tag</code>

##### Installation and usage:


- `git clone https://github.com/Gal-Ofir/JavaMovieStreamerApp.git`
- in `movie.properties` change `movies.directory` to your desired movie directory
- `mvn clean install -DskipTests`

<b>IF YOU WISH TO RUN TESTS</b>

First, in `movie.test.properties` change:

- `test.dir=YOUR_TEST_DIR`
- `test.dir.dir.in.dir=A_DIRECTORY(NOT FILE)_IN_TEST_DIR`
- `test.filename=A_(VALID)_FILE_IN_TEST_DIR`

Then run
<code>mvn clean install </code>

