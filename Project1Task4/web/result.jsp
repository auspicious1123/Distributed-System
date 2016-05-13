<%-- 
    Document   : result
    Created on : Jan 31, 2016, 3:15:41 PM
    Author     : Rui
--%>
<%= request.getAttribute("doctype") %>   
<%@page import="java.util.List"%>
<%@page import="project1task4.Project1Task4Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Select bird.</title>
    </head>
    <body>
        <center>
        <form action="Project1Task4Servlet" method="GET">
        <h1>Here is a picture of a
            <%= request.getParameter("birdName") %> <br>
            from <a href = "http://nationalzoo.si.edu/scbi/migratorybirds/featured_photo/">Smithsonian Migratory Bird Center Bird Photo Gallery</a>
        </h1>
        <img src="<%= request.getAttribute("img")%>" ><br>
        <%= "© " + request.getAttribute("author").toString().substring(3, request.getAttribute("author").toString().length()-1)%><br>
            Select another bird to display:
            <select name = "birdName">
                <% 
                    /**
                     * use Project1Task4Model to read the bird name for orignal web page.
                     */
                    Project1Task4Model model = new Project1Task4Model();
                    List<String> list =  model.extractName();
                    for(int i = 0; i < list.size(); i++) {
                        out.println(list.get(i));
                    }
                %>
            </select>
            <input type="submit" value="Click here">
        </center>
    </body>
</html>
