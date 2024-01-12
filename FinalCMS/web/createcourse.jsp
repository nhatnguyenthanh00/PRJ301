<%-- 
    Document   : createcourse
    Created on : Jul 18, 2023, 9:56:56 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action='addcourse'>
        Category: 
        <select name='cateid'>
            <c:forEach items="${data}" var="c">
                <option value="${c.getCateID()}" >${c.getName()}</option>
            </c:forEach>
            
        </select>
        <br>      
        Course name : <input type='text' name='classname' required=""> <br>
        Teacher name : <input type='text' name='teachername' required=""> <br>
        <input type="submit" value='CREATE COURSE' name='create'>
        </form>
        ${mes}
    </body>
</html>
