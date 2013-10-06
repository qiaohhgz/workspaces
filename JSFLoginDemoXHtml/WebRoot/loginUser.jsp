<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP ' loginUser.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<f:view>
	<f:loadBundle var="bundle" basename="com.jsfdemo.MessageBundle" />
		<h:form id="loginForm2">
			<h:outputLabel for="userName">
				<h:outputText value="#{bundle.user_name_label }"></h:outputText>
			</h:outputLabel>
			<h:inputText id="userName" required="true"
				value="#{userBean.userName }"></h:inputText>
			<br />
			<h:outputLabel for="password">
				<h:outputText value="#{bundle.user_password_label }"></h:outputText>
			</h:outputLabel>
			<h:inputSecret id="password" required="true"
				value="#{userBean.password }"></h:inputSecret>
			<br />
			<h:commandButton value="#{bundle.login_button_label }" type="submit"
				action="#{userBean.loginUser }"></h:commandButton>
		</h:form>
	</f:view>
</body>
</html>
