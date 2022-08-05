# Lab Application

### 1. Clone the application

<pre> $ git clone https://github.com/SariTepee/lab.git </pre>

### 2. Database Configuration

Postgresql is used as the database.

Open src/main/resources/application.properties change 
<ul>
    <li>spring.datasource.url</li> (Create a database named lab or replace it with an existing database name)
    <li>spring.datasource.username</li>
    <li>spring.datasource.password</li>
</ul>

### 3. Launch

The project is created with Maven. There are 2 different ways to run your app without IDE.

Open terminal and enter the lab folder we downloaded

#### Way-1

<pre> mvn install </pre>
and come to position lab/target <strong>(cd target)</strong>  
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

# WHAT DID I DO

<ul>
    <li>Save, delete, get and update operations.</li>
    <li>Search operations.</li>
    <li>Pagination and sorting operations.</li>
    <li>Test operations.</li>
    <li>Software conforming to code standards.</li>
</ul>

### Used Technologies

<ul>
    <li>Spring</li>
    <li>Thymeleaf</li>
    <li>JPA</li>
    <li>Maven</li>
    <li>Bootstrap</li>
</ul>

## Screenshots

### Login Page
![](https://github.com/SariTepee/lab/blob/master/screenshots/login.png)

### Admin Reports Page
![](https://github.com/SariTepee/lab/blob/master/screenshots/admin_reports.png)

### User Reports Page
![](https://github.com/SariTepee/lab/blob/master/screenshots/user_reports.png)

### Search Report Page
![](https://github.com/SariTepee/lab/blob/master/screenshots/searchReport.png)

### Report Info Page
![](https://github.com/SariTepee/lab/blob/master/screenshots/reportInfo.png)

### Save Report Page
![](https://github.com/SariTepee/lab/blob/master/screenshots/saveReport.png)

### Admin Lab Workers Page
![](https://github.com/SariTepee/lab/blob/master/screenshots/admin_labWorkers.png)

### User Lab Worker Page
![](https://github.com/SariTepee/lab/blob/master/screenshots/user_labWorkers.png)

### Lab Worker Info Page
![](https://github.com/SariTepee/lab/blob/master/screenshots/labWorkerInfo.png)

### Save Lab Worker Page
![](https://github.com/SariTepee/lab/blob/master/screenshots/saveLabWorker.png)
