# spring-boot-postgres

This repository is a basic setup to create a RESTful API with spring.

It uses a PostgreSQL database for 'production' and an H2 in-memory database for tests. This is configured with Springs's profiles.

The tests include repository tests for testing custom SQL queries and MockMvc tests for the REST controllers, including a setup for Test driven documentation.

Spring security with JDBC authentication is used to secure the Controllers.

## Requirements

PostgreSQL
JDK 7 or higher

## Building

----
	$ ./gradlew build 
----

## Running

### First time setup
At first the PostgreSQL database has to be created manually

1. Setup PostgreSQL
    * Create a database user 
    
        ----
          $ sudo -u postgres createuser -P -d java
        ----
    * Create the database
    
        ----
          $ sudo -u postgres createdb -O java springbootdb
        ----
2. Setup Tables automatically

      ----
        $ java -jar build/libs/springboot_postgres-1.0.jar -Dspring.profiles.active=setup
      ----

### Normal operation

----
	$ java -jar build/libs/springboot_postgres-1.0.jar
----

### Documentation

The documentation of the RESTful API is found in build/asciidoc/html5/index.html.
It is created by Spring REST Docs with src/docs/asciidoc/index.adoc as skeleton and files in build/generated-snippets created by REST Docs with the help of unit tests.