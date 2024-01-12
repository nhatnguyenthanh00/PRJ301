<%-- 
    Document   : detail_class
    Created on : Jul 11, 2023, 2:09:58 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Student"%>
<%@page import="model.Class"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table.main {
                width:100%;
                height:600px;
                border:1px solid black;
            }

            table.main #logo{

                width:15%;
                height:15%;
            }
            table.main #banner{
                width:85%;
                height:15%;
                text-align: right;
                font-size: 200%;
            }
            table.main #menu1{
                background: #373a3c;

                width:15%;
                height:70%;
                vertical-align: top;

            }
            table.main #content1{
                /*                background:yellow;*/
                width:85%;
                height:70%;
                background-color: white;
                vertical-align: top;
            }
            #result{
                text-align: left;
                margin-top: 10px;
            }
            table.main #footer1{
                background:#9CE8E5;
                width:100%;
                height:15%;
                font-size:25;
            }

            /*            #haha{
                            background-image: url(images/bg_image.jpg);
                            background-position: center center;
                        }*/

        </style>
    </head>
    <body>
        <table class='main'>
            <tr>
                <td id='logo'><img src='images/logo.png' width='150px'></td>
                <td id='banner' background='images/banner2.jpg'>
                    <%
                        Student st = (Student)session.getAttribute("student");
                        out.print("Hello "+st.getName());
                    %>
                    <form action="home" method="get">
                        <input type="submit" name="logout" value="Logout">
                    </form>
                </td>
            </tr>

            <tr>
                <td id='menu1'>
                    <div>
                        <a href="home.jsp" style="color: whitesmoke">Home</a> <br>
                    </div>
                    <div style="color: whitesmoke"> 
                        My Course<br>
                    </div>

                    <div style="margin-left:10px">
                        <%
                            ArrayList<Class> listclass = (ArrayList<Class>)session.getAttribute("listclass");
                            for(Class c : listclass){
                                out.print("<div><a href='class?classid="+c.getClassID()+"' style='color: whitesmoke'> "+c.getClassName()+"</a></div><br>");
                            }
                        %>
                    </div>


                </td>
                <td id='content1'>
                    <b>${myclass.getClassName()}</b>

                    <form action="class" method="post">
                        <input type="hidden" name="classid" value="${myclass.getClassID()}">
                        <input type="submit" name="unenroll" value="UNENROLL THIS CLASS">
                    </form>
                        Link tài liệu<br>
                    <c:forEach items="${listlink}" var="d">
                        <a href="${d.getDocumentLink()}" style="color:yellowgreen">${d.getDocumentLink()}</a> <br>
                    </c:forEach>
                        BÀI TẬP ÔN LUYỆN <br>
                        <c:forEach items="${listquizz}" var="q">
                            <a href="quizz?quizzid=${q.getQuizzID()}"style="color:pink">${q.getQuizzName()}</a>
                             <br>
                        </c:forEach>
                        
                </td>
            </tr>

            <tr id='footer1'>
                <td colspan='2' align='center'>@Copyright by Nguyen Thanh Nhat</td>	
            </tr>
        </table>
    </body>
</html>
