<%-- 
    Document   : inputURL
    Created on : Mar 29, 2016, 9:27:02 PM
    Author     : Rui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%= request.getAttribute("doctype") %>--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Given a image URL</title>
    </head>
    <body>
        <p>Given a image URL.</p>
        <form action="imageTaggingSerlvet" method="GET">
            <label for="letter">Type the url.</label>
            <input type="text" name="imageURL" value="" /><br>
            <input type="submit" value="Click Here" />
        </form>
    </body>
</html>
