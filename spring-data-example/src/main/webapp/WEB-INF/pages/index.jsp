<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Home page</title>
</head>
<body>
<h1>Home page</h1>
<p>
Welcome to "Emp application".<br/>
<i>${message}</i><br/>
<a href="${pageContext.request.contextPath}/emp/create.html">Create a new emp</a><br/>
<a href="${pageContext.request.contextPath}/emp/list.html">View all emp</a><br/>
</p>
</body>
</html>