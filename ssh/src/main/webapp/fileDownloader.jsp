<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">

    <title>DwrFileUploader</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<script>
    function fileDownloader() {
        var ele = document.getElementById("handFrame");
        ele.src = "bookReport";
        ele.src = "excelReport";
    }
</script>
<body>
<button onclick="fileDownloader()">File Downloader</button>
<iframe id="handFrame" style="display: none;" width="0" height="0" src="error.jsp"></iframe>
<%--<iframe id="handFrame" src="error.jsp"></iframe>--%>
</body>
</html>
