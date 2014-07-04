<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
    </head>
    <body>
        <h1>Admin-Only Page</h1>
        <p>Hello, ${userDetails.username}!</p>

        <p>User authorities: ${userAuthorities}</p>

        <a href="/index.htm">Back</a>
    </body>
</html>