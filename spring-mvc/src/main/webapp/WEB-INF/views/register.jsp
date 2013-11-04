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
    <meta charset="UTF-8">
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
            success: successCallBack,
            error: errorCallBack,
            complete: completeCallBack
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
            success: successCallBack,
            error: errorCallBack,
            complete: completeCallBack
        });
    }

    function register2() {
        var req = {name: "jim", hobby: [
            {name: "yumaoqiu", type: "typeValue"}
        ]};
        $.ajax({
            type: "POST",
            url: getFullUrl("reg2"),
            data: JSON.stringify(req),
            timeout: 200000,
            contentType: "application/json; charset=UTF-8",
            cache: false,    //This will force requested pages not to be cached by the browser
            processData:false, //To avoid making query String instead of JSON
            success: successCallBack,
            error: errorCallBack,
            complete: completeCallBack
        });
    }

    function successCallBack(data, status) {
        alert(JSON.stringify(data));
        console.log("success status = " + status);
    }
    function errorCallBack(event, status, ex) {
        console.log("error status = " + status);
    }
    function completeCallBack(event, status) {
        console.log("completed status = " + status);
    }
</script>
<body>
<h1>
    Spring MVC!
</h1>

<button onclick="getServerTime(this)">get server time</button>
<button onclick="register()">register</button>
<button onclick="register2()">register2</button>

</body>
</html>