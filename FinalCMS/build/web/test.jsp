<%-- 
    Document   : test
    Created on : Jul 11, 2023, 12:21:59 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Student"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World Test!</h1>
        <%
            Student st = (Student)session.getAttribute("student");
            if(st==null){
                out.print("lam gi con gi");
            }
            else out.print("Hello "+st.getName());
        %>
    </body>
</html>
