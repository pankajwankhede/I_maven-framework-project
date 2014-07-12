<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<body>
		<center><h1>欢迎页面</h1>
		用户名：<s:property value="j_username"/><br>
		密码：<s:property value="j_password"/><br>
		活动编号：<s:property value="activity_id"/><br>
		活动名称： <input type="text" name="activity_name" value="<s:property value='activity_name' />"/> <s:property value='activity_name'/> <br>
		开始时间：<s:property value="startDate"/><br>
		结束时间：<s:property value="endDate"/><br>
		<a href="timeout.jsp">退出</a>
		</center>
</body>
</html>