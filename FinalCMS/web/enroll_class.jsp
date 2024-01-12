<%-- 
    Document   : enroll_class
    Created on : Jul 11, 2023, 1:01:52 AM
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

                    <table border="1">
                        <tr>
                            <td>ClassName</td>
                            <td>TeacherName</td>
                            <td>Category</td>
                        </tr>
                        <tr>
                            <td><a href="class?classid=${myclass.getClassID()}" style="color:blue">${myclass.getClassName()}</a></td>
                            <td>${myclass.getTeacherName()}</td>
                            <td>${myclass.getCateName()}</td>
                        </tr>

                    </table>
                        <form action="class" method="post">
                            <input type="hidden" name="classid" value="${myclass.getClassID()}">
                            <input type="submit" name="enroll" value="ENROLL ME">
                        </form>
                </td>
            </tr>

            <tr id='footer1'>
                <td colspan='2' align='center'>@Copyright by Nguyen Thanh Nhat</td>	
            </tr>
        </table>
    </body>
</html>
