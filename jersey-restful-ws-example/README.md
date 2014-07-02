# jersey-restful-ws-example

* 启动服务端

```
mvn jetty:run
```

* 启动客户端

`UserInfoClient.main()`

* 客户端运行返回结果

```
Client Response 
GET http://localhost:8080/rest/UserInfoService/name/Pavithra returned a response status of 200 OK
Response 
<User><Name>Pavithra</Name></User>


Client Response 
GET http://localhost:8080/rest/UserInfoService/age/25 returned a response status of 200 OK
Response 
<User><Age>25</Age></User>
```

* [http://www.javacodegeeks.com/2013/11/restful-web-services-with-java.html](http://www.javacodegeeks.com/2013/11/restful-web-services-with-java.html)