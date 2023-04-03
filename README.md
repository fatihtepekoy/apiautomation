# Cucumber 7 - JUnit 5 - Apache HttpClient 5 - Java 17 Api Automation Project Skeleton with working sample rest api

Hi all,
This is the simple and well designed setup with Cucumber 7, Junit 5, Apache HttpClient 5 (alternative to the Rest Assured which is not thread safe) and using Java 17.

Also I wanted to give an example that how you can make your api automation test project structure. The packages and classes named according to the free open rest api. 

The project also has a static method for getting token.(oauth2). It is commented out now. First comment in the code part and need to enter your cridentials to properties file.   It has also a generic datatable configration class, so that you easly can read cucumber datatables.

Feel free to give suggestions and your contributions. I will try to make this project work with an open api, so that you can easly run and see the test results.

I will update this readme file soon with the topics below

Topics
* Why apache httpClient ? It is thread safe and it is the best alternative way to RestAssured which is not thread safe
* What is transaction, CommonRequestContext, step and operation object? Why do they stand for? 
* AccessToken and example code part
* Generating the urls wiht path variables and query variables
* ObjectPayload and String payload
* Pico container and how you can use it for dependency injection to run your test in step methods placed in different classes




PS: The free res api service let one person send 100 request a day.
