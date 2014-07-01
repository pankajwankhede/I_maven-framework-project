spring3-mvc-simple
=======================

基于全注解配置的Spring MVC例子

需要注意的文件:

* [SampleController.java](src/main/java/org/spring3/mvc/simple/controller/SampleController.java) 控制器
* [WebConfig.java](src/main/java/org/spring3/mvc/simple/springconfig/WebConfig.java) 基于注解的web配置
* [web.xml](src/main/webapp/WEB-INF/web.xml) web.xml配置


## 1. 使用maven tomcat plugin方式运行

Tomcat 7

```bash
mvn tomcat7:run
```

[http://localhost:8080/spring3-mvc-simple](http://localhost:8080/spring3-mvc-simple)

Tomcat 6

```bash
mvn tomcat6:run
```

[http://localhost:8080/spring3-mvc-simple](http://localhost:8080/spring3-mvc-simple)

## 2. 使用jetty plugin方式运行

```bash
mvn jetty:run
```

[http://localhost:8080/home](http://localhost:8080/home)

## 3. 使用gradle方式运行

```bash
gradle jettyRunWar
```

[http://localhost:8080/home](http://localhost:8080/home)
