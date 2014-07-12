<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
<title>Session Timeout</title>
<body>
<h2>Invalid Session</h2>
<%
	session.invalidate();
	response.sendRedirect(request.getContextPath()+"/");
%>
<p>
Your session appears to have timed out. Please <a href="<c:url value='/login/login.jsp'/>">start again</a>.
</p>
</body>
</html>
