spring3-rmi-httpinvoker-example
=======================

基于Spring Http invoker的远程方法调用

先使用```mvn jetty:run```运行服务端，再运行```HttpUserServiceClient```client端。注意看控制台日志输出

注意的配置文件

server

* [spring-servlet.xml](src/main/webapp/WEB-INF/spring-servlet.xml) 切记该文件应位于WEB-INF/目录下而不是classpath目录下，否则启动报找不到文件异常
* [web.xml](src/main/webapp/WEB-INF/web.xml)


client

* [HttpClientApplicationContext.xml](src/main/resources/HttpClientApplicationContext.xml)
* [HttpUserServiceClient.java](src/main/java/net/aimeizi/rmi/httpinvoke/example/http/client/HttpUserServiceClient.java)