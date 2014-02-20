<%-- 
    Rendered when a checked exception occurs
    This is a result of the configurations in the application context
        (servlet-context.xml in this case)
    
    Specific or custom Exception handling can be configured 
        in servlet-context.xml also
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        A checked exception has occurred
        <div style="border:1px solid red; color:#red;padding:20px;">
            ${exception.message}
        </div>
    </body>
</html>
