<%-- 
    Document   : result
    Created on : Feb 1, 2016, 1:24:05 PM
    Author     : Rui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result of task3</title>
    </head>
    <body>
        <h1>Check palindrome result: </h1>
        <li>The input string is : <b><%= request.getAttribute("inputText") %></b>
        <li>Is it palindrome? true or false : <b><%= request.getAttribute("result") %></b>
    </body>
</html>
