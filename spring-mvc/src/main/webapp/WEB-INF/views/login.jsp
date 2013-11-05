<%--
  Created by IntelliJ IDEA.
  User: JimQiao
  Date: 11/1/13
  Time: 3:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function postLoginOne() {
        $.ajax({
            type: "POST",
            data: {username: $("#username").val(), password: $("#password").val()},
            url: "${pageContext.request.contextPath}/loginOne",
            success: loginSuccess,
            error: loginError
        });
    }

    function postLoginTwo() {
        $.ajax({
            type: "POST",
            data: {username: $("#username").val(), password: $("#password").val()},
            url: "${pageContext.request.contextPath}/loginTwo",
            success: loginSuccess,
            error: loginError
        });
    }

    function loginSuccess(data, status) {
        alert(data);
    }

    function loginError(event, status, ex) {
        console.log(ex.message);
    }
</script>
<body>
Login <a href="${pageContext.request.contextPath}/logout">log out</a>

<form method="post">
    <input type="text" placeholder="username" id="username" name="username" tabindex="1"> <br>
    <input type="password" placeholder="password" id="password" name="password" tabindex="2"><br>
    <input tabindex="-1" type="submit" value="post login one" formaction="${pageContext.request.contextPath}/loginOne">
    <input tabindex="-1" type="submit" value="post login two" formaction="${pageContext.request.contextPath}/loginOne">
</form>
</body>
</html>