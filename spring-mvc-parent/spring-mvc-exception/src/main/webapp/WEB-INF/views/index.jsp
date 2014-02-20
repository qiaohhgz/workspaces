<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <script src="http://malsup.github.com/jquery.form.js" type="text/javascript"></script>
        <script type="text/javascript">
            $("#ex").ajaxForm({
                success: function() {
                    alert('done successfully');
                },
                error: function(xhr, status, err) {
                    alert(xhr.responseText);
                }
            });
        </script>
        <style type="text/css">
            a:link, a:visited, a:hover {
                color:blue;
            }
        </style>
    </head>

    <body>
        <p>
            <fieldset>
                <legend>Demonstrate non-ajax exception handling </legend>
                <div style="border: 1px solid #eaeaea;margin-top:20px;">
                    <p>
                        <form method="POST" action="ex">
                            <input type="submit" value="POST Exception" />
                            <br /><br />
                            <input type="radio" name="execptionType" value="unchecked" checked="checked">Unchecked RuntimeException going to unchecked.jsp</input>
                            <br />
                            <input type="radio" name="execptionType" value="checked">Checked Exception going to checked.jsp</input>
                        </form>
                    </p>
                </div>
            </fieldset>
        </p>
        <p>
            <fieldset>
                <legend>Demonstrate AJAX exception handling </legend>
                <form action="ex" method="GET" id="ex">
                    <input type="submit" value="AJAX Exception" />
                </form>
            </fieldset>
        </p>
        <p>
            <fieldset>
                <legend>Demonstrate jQuery AJAX .load() exception handling </legend>
                <p>
                    <div id="loadHere" style="border:1px solid #ccc"></div>
                </p>
                <a href="" id="load" >$.load(&quot;index&quot;)</a>
                <script type="text/javascript">
                    $(document).ready( function(){
                        $('#load').click( function(){
                            $('#loadHere').load( "loadme", function(responseText, textStatus, XMLHttpRequest){
                                if( XMLHttpRequest.status != 200) {
                                    alert(responseText);
                                }
                            });
                            return false;
                        });
                    });
                </script>
            </fieldset>
        </p>
        <fieldset>
            <legend>Demonstrate exception handling at the @Controller level overriding the global handler</legend>
            <p>
                <a href="null">Invoke a NullPointerException which is handled locally in the controller</a>
            </p>
        </fieldset>
    </body>
</html>
