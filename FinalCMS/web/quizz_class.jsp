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
        <script>
            window.onload = function () {
                var timeLimit = 10; // Thời gian giới hạn (tính bằng giây)
                var display = document.querySelector('#timer');
                startTimer(timeLimit, display);
            };

            function startTimer(duration, display) {
                var startTime = Date.now();
                var timerDuration = duration * 1000; // Chuyển thời gian giới hạn thành mili-giây

                function timerTick() {
                    var elapsedTime = Date.now() - startTime;
                    var timeRemaining = timerDuration - elapsedTime;

                    if (timeRemaining <= 0) {
                        clearTimeout(timerInterval);
                        document.getElementById("quizForm").submit();
                        window.location.href = "quizz?submitquizz=1&quizzid=${quizzid}";
                        return;
                    }

                    var minutes = Math.floor(timeRemaining / (1000 * 60));
                    var seconds = Math.floor((timeRemaining % (1000 * 60)) / 1000);

                    minutes = minutes < 10 ? "0" + minutes : minutes;
                    seconds = seconds < 10 ? "0" + seconds : seconds;

                    display.textContent = minutes + ":" + seconds;
                }

                timerTick(); // Để đảm bảo rằng thời gian còn lại được hiển thị ngay khi trang web được tải

                var timerInterval = setInterval(timerTick, 1000);
            }

        </script>
    </head>
    <body>
        <table class='main'>
            <tr>
                <td id='logo'><img src='images/logo.png' width='150px'></td>
                <td id='banner'>
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
                    BAT DAU TEST THOI <br><!-- comment -->
                    <p>Thời gian còn lại: <span id="timer"></span></p>
                    <form id = "quizForm" action="quizz" method="get">
                        <c:forEach items="${listquestion}" var="q" varStatus="status">
                            <h5>Question ${status.index + 1}: ${q.getQuestionDetail()}</h5>
                             <c:if test="${q.val eq '1'}">
                                <input type="radio" name="ans_${status.index}" value="1" checked> ${q.getChoice1()}
                            </c:if>
                                <c:if test="${q.val ne '1'}">
                                <input type="radio" name="ans_${status.index}" value="1" > ${q.getChoice1()}
                            </c:if>
                            <c:if test="${q.val eq '2'}">
                                <input type="radio" name="ans_${status.index}" value="2" checked> ${q.getChoice2()} <br>
                            </c:if>
                                <c:if test="${q.val ne '2'}">
                                    <input type="radio" name="ans_${status.index}" value="2" > ${q.getChoice2()} <br>
                            </c:if>

                        </c:forEach>
                        <input type="hidden" name="quizzid" value="${quizzid}">
                        <input type="submit" name="submitquizz" value="Submit">

                    </form>
                </td>
            </tr>

            <tr id='footer1'>
                <td colspan='2' align='center'>@Copyright by Nguyen Thanh Nhat</td>	
            </tr>
        </table>
    </body>
</html>
