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
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/util.js'></script>
    <script type='text/javascript' src='dwr/interface/jFileUpload.js'></script>
</head>
<script>
    function uploadFiles(n) {
        jFileUpload.uploadFileWithInputStream(_file, null, function (data) {
            alert(data);
            dwr.util.setValue('image', image);
        });
    }
</script>
<body onload="dwr.util.useLoadingMessage()">
<table>
    <tr>
        <td>Image</td>
        <td><input type="file" id="uploadImage"/></td>
    </tr>
    <tr>
        <td colspan="2">
            <button onclick="uploadFiles(3)">upload File</button>
        </td>
    </tr>
</table>
<div>
    <img alt="" src="" id="image"/>
</div>
</body>
</html>
