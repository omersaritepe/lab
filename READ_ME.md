# Lab Application

### 1. Clone the application

<pre> $ git clone  </pre>

### 2. Database Configuration

Postgresql is used as the database.

<ul>
    <li> Open src/main/resources/application.properties </li>
    <li> If you run the application locally,change spring.datasource.username and spring.datasource.password as per your mysql installation</li>
</ul>

### 3. Launch

The project is created with Maven. There are 2 different ways to run your app without IDE.

#### Way-1
Open terminal 
<pre> mvn install </pre>
and come to position lab/target  
<pre> java -jar lab-0.0.1-SNAPSHOT.jar </pre>

The application runs from http://localhost:8080/

#### Way-2
Open terminal in lab
<pre> ./mvnw install </pre>
and
<pre> ./mvnw spring-boot:run </pre>

The application runs from http://localhost:8080/

### In Program

User login required to access the program.

There are 2 users in this app.

For admin login:
<pre> username: admin, password: admin </pre>

For user login:
<pre> username: user, password: user </pre>

## Screenshots