<%-- 
    Document   : index
    Created on : Jan 24, 2016, 3:28:17 PM
    Author     : Rui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello! User, Welcome to our page! </h1>
        <form action="ComputeHashes" method="GET">
            Please enter a string of text data here:<br><br>
            Input text: <input type="text" name="InputTextData">
            <br><br>
            Please choose one kind of hash function.<br>
            <input type="radio" name="method" value="MD5" checked> MD5<br>
            <input type="radio" name="method" value="SHA-1"> SHA-1<br><br>
            <input type="submit">
            <br>
        </form>
    </body>
</html>
