# Chatment-SpringBoot-Animal-Facts-APIs
Chatment-SpringBoot-Animal-Facts-APIs
Endpoints:
1. Get Animal Fact: POST to http://<localhost/IP>:<port>/getFact with POST body of JSON  {"animalType":"<animal_name>"} replace animal_name with either cat or dog. This endpoint returns a random fact about that animal and logs access info to Access Log Entity in the Embedded H2 DataBase which is in-memory (non persistent). 
example POST request for this project: http://192.168.0.108:8081/getFact with POST Body as {"animalType":"dog"} 

2. Get Access Log Info: GET to http://<localhost/IP>:<port>/access/log) and add a header with Key: "Authorization" and Value: "chatment-access-log-api-key-hlTYXuavbTbxWRARrhvRj112i6zBbEBhtHwUdUGvUbhv0fO6uOhtVyHAxKJFIvVV8aqIPVbWQLdTwJZ0oMV3BLK8ypRnd3s3bhug" (this API Key is read by a custom filter from the application.properties file and verified within this). This endpoint returns all the data from Access Log Entity from the Embedded DataBase. 

 3. Access Log API-Key is configured in application.properties of Spring Boot in src\main\resources. It is used by ChatmentAccessLogFilter for access log API authentication.
 
 4. ChatmentAccessLogFilter is a custom filter which intercepts the http request and authenticates request to the endpoint /access/log using APi-Key in the request header.
 
5. The Embedded Database has 4 entities: animal_traits, cat_traits, dog_traits and access_log. The animal_traits entity was used for testing and I forgot to delete before building. the rest 3 entities are used in this project.
schema.sql and data.sql under src/main/resources initialize the schema and entities during run time of project.
Database can be access via console at http://<localhost/IP:<port>/h2-console
Steps to run project: 
1.) Navigate to directory of the project.
2.) execute mvn install -DskipTests
3.) Now go to directory of the jar file 
4.) execute java -jar <filename.jar> 
5.) Use PostMan to send POST or GET request to specific endpoints.
