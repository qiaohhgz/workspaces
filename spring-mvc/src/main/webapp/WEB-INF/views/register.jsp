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
    $.extend($, {ajaxJson: function (options) {
        var ops = {
            type: "POST",
            url: "",
            timeout: 200000,
            dataType: "json",
            contentType: "application/json; charset=UTF-8",
            cache: false,    //This will force requested pages not to be cached by the browser
            processData: false //To avoid making query String instead of JSON
        };
        $.extend(ops, options);
        if (typeof ops.data == "object") {
            ops.data = JSON.stringify(ops.data);
        }
        $.ajax(ops);
    }
    });

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
            data: JSON.stringify(req),
            timeout: 200000,
            contentType: "application/json; charset=UTF-8",
            cache: false,    //This will force requested pages not to be cached by the browser
            processData: false, //To avoid making query String instead of JSON
            success: successCallBack,
            error: errorCallBack,
            complete: completeCallBack
        });
    }

    function jsonExOne() {
        $.ajax({
            type: "GET",
            url: getFullUrl("jsonEx"),
            success: successCallBack,
            error: errorCallBack,
            complete: completeCallBack
        });
    }

    function jsonEx(o) {
        $.ajax({
            type: "GET",
            url: getFullUrl("exception/getPagesAsJson?type=" + o.dataset["type"]),
            success: successCallBack,
            error: errorCallBack,
            complete: completeCallBack
        });
    }

    function successCallBack(data, status) {
        alert(JSON.stringify(data));
    }
    function errorCallBack(event, status, ex) {
        alert("error message = " + ex);
    }
    function completeCallBack(event, status) {

    }
</script>
<body>
<h1>
    Spring MVC!
</h1>

<button onclick="getServerTime(this)">get server time</button>
<button onclick="register()">register</button>
<button onclick="jsonExOne()">test json exception</button>
<a href="${pageContext.request.contextPath}/pageEx">test page exception</a>
<a href="${pageContext.request.contextPath}/exception/getPages?type=error">test custom exception</a>
<a href="${pageContext.request.contextPath}/exception/getPages?type=io-error">test exception</a>
<button onclick="jsonEx(this)" data-type="error">test json exception</button>

</body>
</html>