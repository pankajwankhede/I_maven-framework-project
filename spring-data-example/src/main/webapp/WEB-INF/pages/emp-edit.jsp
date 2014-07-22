<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit Emp page</title>
</head>
<body>
<h1>Edit Emp page</h1>
<form:form method="POST" commandName="emp" action="${pageContext.request.contextPath}/emp/edit/${emp.empid}.html" >
<table>
<tbody>
<tr>
<td>Emp name:</td>
<td><form:input path="empname" /></td>
<td><form:errors path="empname" cssStyle="color: red;"/></td>
</tr>
<tr>
<td>Emp empaddress:</td>
<td><form:input path="empaddress" /></td>
<td><form:errors path="empaddress" cssStyle="color: red;"/></td>
</tr>
<tr>
<td><input type="submit" value="Create" /></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>
</form:form>
<a href="${pageContext.request.contextPath}/index">Home page</a>
</body>
</html>