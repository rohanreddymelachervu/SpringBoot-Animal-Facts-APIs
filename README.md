# SpringBoot-Animal-Facts-APIs
SpringBoot-Animal-Facts-APIs <br/>
Endpoints: <br/>
1. Get Animal Fact: POST to http://<localhost/IP>:(port)/getFact with POST body of JSON  {"animalName":"<animal_name>"} replace animal_name with either cat or dog. This endpoint returns a random fact about that animal and logs access info to Access Log Entity in the Embedded H2 DataBase which is in-memory (non persistent). 
example POST request for this project: http://192.168.0.108:8081/getFact with POST Body as {"animalType":"dog"} <br/>
2. Get Access Log Info: GET to http://<localhost/IP>:(port)/access/log) and add a header with Key: "Authorization" and Value: "chatment-access-log-api-key-hlTYXuavbTbxWRARrhvRj112i6zBbEBhtHwUdUGvUbhv0fO6uOhtVyHAxKJFIvVV8aqIPVbWQLdTwJZ0oMV3BLK8ypRnd3s3bhug" (this API Key is read by a custom filter from the application.properties file and verified within this). This endpoint returns all the data from Access Log Entity from the Embedded DataBase. <br/>
 3. Access Log API-Key is configured in application.properties of Spring Boot in src\main\resources. It is used by ChatmentAccessLogFilter for access log API authentication. <br/>
 4. ChatmentAccessLogFilter is a custom filter which intercepts the http request and authenticates request to the endpoint /access/log using APi-Key in the request header. <br/>
5. The Embedded in-memory H2 database has 4 tables: animal_traits, cat_traits, dog_traits and access_log. The animal_traits table was designed for combined animal facts which is deprecated in this release. The other 3 tables are used in this project. <br/>
schema.sql and data.sql are database initialization files which are placed under src/main/resources. <br/>
Database can be accessed via web console at http://<localhost/IP:(port)/h2-console access details are <br/>
Driver class: org.h2.Driver <br/>
JDBC URL: jdbc:h2:mem:animal_traits_db <br/>
User Name: rohan <br/>
Password: password <br/>
Steps to run project: <br/>
1.) Navigate to directory of the project. <br/>
2.) execute mvn install -DskipTests <br/>
3.) Now go to directory of the jar file <br/>
4.) execute java -jar <filename.jar> <br/>
5.) Use PostMan to send POST or GET requests to specific endpoints. <br/>


(C) Rohan Reddy Melachervu - 2022
