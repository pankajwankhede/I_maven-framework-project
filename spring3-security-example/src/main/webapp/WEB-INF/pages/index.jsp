<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype>
<html>
<head>
    <title>Home</title>
</head>
<body>
<div>
    <h1>All authenticated users can see this page</h1>

    <sec:authorize access="isAuthenticated()">
        <p>Hello, ${userDetails.username}!</p>
        <p>User authorities: ${userAuthorities}</p>
        <p><a href="/j_spring_security_logout">Sign Out</a></p>
    </sec:authorize>

    <p>
    <h2>Only Admins will be able to see this page.</h2>
    <a href="/admin.htm">Admin page</a>
    </p>
</div>
</body>
</html>