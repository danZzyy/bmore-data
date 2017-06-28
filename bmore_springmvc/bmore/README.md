# Baltimore Bike Data Web App

### Setup
To run this app you will need:
* Maven
* MySQL
* Java 8

1. Clone this repo into a directory on your machine
2. <DB DUMP>
3. In `bmore-data/bmore_springmvc/bmore/` , notice that pom.xml and run `mvn compile`. This will install all the libraries and dependencies the Spring MVC Java backend uses
4. `mvn tomcat:run` will start the server
5. Now that the server is running the main webpage can be found at `http://localhost:8080/app/loc/`
