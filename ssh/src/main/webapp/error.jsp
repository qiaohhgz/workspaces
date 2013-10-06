<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>Simple jsp page</title>
	</head>
	<body>
		<h3>
			Exception:
		</h3>
		<s:property value="exception" />

		<h3>
			Stack trace:
		</h3>
		<pre>
        <s:property value="exceptionStack" />
    </pre>
	</body>
</html>