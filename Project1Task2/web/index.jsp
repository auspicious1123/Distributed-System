<%-- 
    Document   : index
    Created on : Jan 24, 2016, 10:54:24 PM
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
        <h1>Project1task2: Calculator App!</h1>
        <form action="BigCalc" method="GET">
            Please enter two integers and choose one operation:<br><br>
            Integer 1: <input type="number" name="Integer1">
            <br><br>
            Integer 2: <input type="number" name="Integer2">
            <br><br>
            Operation:
            <select name = "Operation">
                <option value="add">add</option>
                <option value="multiply">multiply</option>
                <option value="relativelyPrime">relatively Prime</option>
                <option value="mod">mod</option>
                <option value="modInverse">mod Inverse</option>
                <option value="power">power</option>  
            </select>
            <br><br>
            <input type="submit">
            <br>
        </form>
    </body>
</html>
