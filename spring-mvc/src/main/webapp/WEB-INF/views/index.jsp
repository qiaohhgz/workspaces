<%--
  Created by IntelliJ IDEA.
  User: JimQiao
  Date: 11/4/13
  Time: 10:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<h2>Spring MVC @ExceptionHandler Example</h2>

<c:if test="${not empty msg}">
    <h4>${msg}</h4>
</c:if>

</body>
</html>