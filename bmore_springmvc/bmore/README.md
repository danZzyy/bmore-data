# Baltimore Bike Data Web App

### Setup
To run this app you will need:
* Maven
* MySQL
* Java 8

1. Clone this repo into a directory on your machine
2. The database .sql file can be found at `bmore-data/db/dumps/bmore.sql`
  * In your Database management application (I use MySQL Workbench) import `bmore.sql` into your db server
  * In `bmore-data/main/resources/applicationContext.xml` on line 35 change `jdbc:mysql://127.0.0.1:3306/bmore` to the path of your bmore database database
3. In `bmore-data/bmore_springmvc/bmore/` , notice that pom.xml and run `mvn compile`. This will install all the libraries and dependencies the Spring MVC Java backend uses
4. `mvn tomcat:run` will start the server
5. Now that the server is running the main webpage can be found at `http://localhost:8080/app/loc/`
  * NOTE: The map appears not to render on page load sometimes, in which case opening the browser inspector fixes it

`http://localhost:8080/app/loc/`
This is the webpage of the Angular CRUD application

`http://localhost:8080/app/`
This is the webpage of the about page/prototype I worked on before this I redesigned the app
