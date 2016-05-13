<%-- 
    Document   : index
    Created on : Jan 25, 2016, 1:52:46 AM
    Author     : Rui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%= request.getAttribute("doctype") %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome to check palindrome page! </h1>
        <form action="Palin" method="GET">
            Please enter a string:<br><br>
            Input text: <input type="text" name="InputTextData">
            <br><br>
            <input type="submit">
            <br>
        </form>
    </body>
</html>
