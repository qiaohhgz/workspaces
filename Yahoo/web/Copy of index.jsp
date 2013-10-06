<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="http://yui.yahooapis.com/3.6.0/build/yui/yui-min.js"></script>
<link rel="stylesheet" href="file:///D:/JimQiao/Workspaces/yui/build/cssreset/reset-min.css">
<link rel="stylesheet" href="file:///D:/JimQiao/Workspaces/yui/build/cssfonts/fonts-min.css">
<link rel="stylesheet" href="file:///D:/JimQiao/Workspaces/yui/build/cssbase/base-min.css">
<script type="text/javascript" src="dialog/loginDialog.js"></script>
<script type="text/javascript" src="dialog/debugDialog.js"></script>
</head>
<body class="yui3-skin-sam">
<jsp:include page="dialog/loginDialog.xhtml"></jsp:include>
<jsp:include page="dialog/debugDialog.xhtml"></jsp:include>
<button onclick="loginDialog.show()">Login</button>
</body>
</html>