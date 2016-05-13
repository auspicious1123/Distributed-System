<%-- 
    Document   : index
    Created on : Jan 31, 2016, 2:41:10 PM
    Author     : Rui
--%>
<%= request.getAttribute("doctype") %> 
<%@page import="java.util.List"%>
<%@page import="project1task4.Project1Task4Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Tsype" content="text/html; charset=UTF-8">
        <title>Bird Gallery</title>
    </head>
    <body>
        <h1>Search the 
            <a href = "http://nationalzoo.si.edu/scbi/migratorybirds/featured_photo/">
                Smithsonian Migratory Bird Center Bird Photo Gallery</a>
                from images of migratory birds</h1>
        <form action="Project1Task4Servlet" method="GET">
            Select a bird to display:
            <br>
            <select name = "birdName">
                <% 
                    // Get all bird name from original webpage.
                    Project1Task4Model model = new Project1Task4Model();
                    List<String> list =  model.extractName();
                    for(int i = 0; i < list.size(); i++) {
                        out.println(list.get(i));
                    }
                %>
            </select>
            <br><br>
            <input type="submit" value="Click here">
            <br>
    </body>
</html>
