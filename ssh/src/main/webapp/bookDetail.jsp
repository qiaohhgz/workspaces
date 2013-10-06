<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>Book Detail</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link href="css/table.css" rel="stylesheet"/>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/util.js'></script>
    <script type='text/javascript' src='dwr/interface/jBook.js'></script>
    <script type='text/javascript' src='scripts/bookLogic.js'></script>
    <script type="text/javascript">
        window.onload = function () {
            init();
        }
    </script>
</head>

<body>
<table border="0" cellpadding="0" cellspacing="0" class="apex_ir_html_export">
    <tr>
        <td>Add Book:
            <input id="name" type="text" size="30" maxlength="1000"/>
            <input type="button" value="Add" onclick="addOneBook()"/>
        </td>
    </tr>
    <tr>
        <td>Search:
            <input id="searchName" type="text" size="30" maxlength="1000" onkeyup="searchBook()"/>
        </td>
    </tr>
</table>
<br/>
<table id="books" border="0" cellpadding="0" cellspacing="0" class="apex_ir_html_export">
    <tr>
        <th>编号</th>
        <th>名称</th>
        <th>操作</th>
    </tr>
    <tr>
        <th>${book.id}</th>
        <th>${book.name}</th>
        <th>&nbsp;</th>
    </tr>
</table>
</body>
</html>
