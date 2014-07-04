spring3-security-example
=========================

This is an example of a basic Spring 3.2.x MVC application protected by Spring Security.

To run the app locally, run *mvn tomcat7:run*.

In your browser, go to http://127.0.0.1:9090

By default there are four users:

* user / password
* admin / password
* user-disabled / password
* admin-disabled / password

All passwords are simply *password*. The 3rd and 4th user are, of course, disabled within
the database *active* boolean value, and will not be able to login.

While Spring Security makes it relatively easy (once the basic filter mechanism is understood), to
add basic web security to an application, it is not exactly straightforward when deviating from the
default database based schema for users and roles.

The SQL files to create and insert data are located under */src/main/resources/spring/db*.

This application uses an embedded HSQL database with the following schema:

***Users table***

* id (UUID)
* name (Unique varchar)
* role (FK to Roles(id))
* password (varchar)
* active (boolean)

***Roles table***

* id (UUID)
* name (Unique varchar)

--------------------------------

The Spring Security configuration is located under */src/main/resources/spring/spring-security.xml*.

The basic idea is as follows:

1. An *HsqlAuthenticationProvider* bean is defined which overrides the framework class *AbstractUserDetailsAuthenticationProvider*.
This class is responsible for checking the credentials passed by Spring Security's 
default login page. The provider has a Spring data source injected for database access.

2. Various abstract methods overridden to perform the authentication. The class checks to ensure that the user is in the database, 
that the password matches, and the user is active. The provider uses the data source to join on the user name and role table to pull
the user and its role from the database. Using Spring's *JdbcTemplate.queryForObject()*, an exception is thrown automatically if a single
row is not returned.

3. If the user is valid, the method returns a Spring Security *UsernamePasswordAuthenticationToken*. If not, an exception is thrown.

4. Spring takes care of the rest - if successful, the token is added to the session which enables the application to obtain the user
and its roles anytime from Spring Security. If not, Spring Security will take the error message and display an error back on the auto
generated login page.

5. Once the user is logged in, she will be redirected to the main index.htm page. Within the spring-security.xml configuration, this page is 
defined to require either roles *USER* or *ADMIN* (based on the */*** expression) joined with *hasAnyRole('USER, ADMIN')*.

6. The *index.htm* page has a link to *admin.htm*. Only users with *ADMIN* role are able to access this page based on expression *hasAnyRole('ADMIN')*.

7. Note that anyone, even users that have yet to login to the app, can access files located behind the /resources URL. For example, before
logging in, go to the URL: http://127.0.0.1:9090/resources/unprotected.js
In many cases, resources (CSS, javascript, images, etc.) may need to be protected.

