jaxws-with-spring
=======================

JAX-WS Spring Integration Example

```
mvn jetty:run
```

# 第一种方式

使用`WSSpringServlet`和`ContextLoaderListener`以及使用`wss:binding`配置

[http://localhost:8080/hello](http://localhost:8080/hello)

[http://localhost:8080/hello?wsdl](http://localhost:8080/hello?wsdl)

[http://localhost:8080/jaxws-spring](http://localhost:8080/jaxws-spring)

[http://localhost:8080/jaxws-spring?wsdl](http://localhost:8080/jaxws-spring?wsdl)

# 第二种方式

使用`WSServletContextListener`和`sun-jaxws.xml`方式

web.xml中添加内容

```
	<listener>
		<listener-class>
			com.sun.xml.ws.transport.http.servlet.WSServletContextListener
		</listener-class>
	</listener>
	<servlet>
		<servlet-name>sayhello</servlet-name>
		<servlet-class>com.sun.xml.ws.transport.http.servlet.WSServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>sayhello</servlet-name>
		<url-pattern>/sayhello</url-pattern>
	</servlet-mapping>
```

sun-jaxws.xml中内容

```
<endpoints xmlns="http://java.sun.com/xml/ns/jax-ws/ri/runtime" version="2.0">
	<endpoint name="WebServiceImpl"
		      implementation="com.javacodegeeks.enterprise.ws.WebServiceImpl"
		      url-pattern="/sayhello" />
</endpoints>
```
该方式不需要在`applicationContext.xml`中配置任何内容

[http://localhost:8080/sayhello](http://localhost:8080/sayhello)

[http://localhost:8080/sayhello?wsdl](http://localhost:8080/sayhello?wsdl)

# 参考链接

[http://www.mkyong.com/webservices/jax-ws/jax-ws-spring-integration-example/](http://www.mkyong.com/webservices/jax-ws/jax-ws-spring-integration-example/)

[http://examples.javacodegeeks.com/enterprise-java/jws/jax-ws-spring-integration-example/](http://examples.javacodegeeks.com/enterprise-java/jws/jax-ws-spring-integration-example/)

[http://examples.javacodegeeks.com/enterprise-java/jws/jax-ws-web-services-on-tomcat/](http://examples.javacodegeeks.com/enterprise-java/jws/jax-ws-web-services-on-tomcat/)

