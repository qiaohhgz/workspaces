<%--
  Created by IntelliJ IDEA.
  User: JimQiao
  Date: 10/31/13
  Time: 2:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    var pageState = {
        contextPath: "${pageContext.request.contextPath}"
    }
    function getFullUrl(url) {
        return pageState.contextPath + "/" + url;
    }
</script>
<script type="text/javascript">
    function getServerTime(btn) {
        $.ajax({
            type: "GET",
            url: getFullUrl("getServerTime"),
            success: function (data, status) {
                btn.innerHTML = data;
                console.log("success status = " + status);
            },
            error: function (event, status, ex) {
                console.log("error status = " + status);
            },
            complete: function (event, status) {
                console.log("completed status = " + status);
            }
        });
    }

    function register() {
        var req = {name: "jim", hobby: [
            {name: "yumaoqiu", type: "typeValue"}
        ]};
        $.ajax({
            type: "POST",
            url: getFullUrl("reg"),
            traditional: true,
            data: "json=" + JSON.stringify(req),
            success: function (data, status) {
                console.log("success status = " + status);
            },
            error: function (event, status, ex) {
                console.log("error status = " + status);
            },
            complete: function (event, status) {
                console.log("completed status = " + status);
            }
        });
    }

</script>
<body>
<h1>
    Spring MVC!
</h1>

<button onclick="getServerTime(this)">get server time</button>
<button onclick="register()">register</button>

</body>
</html>