<%-- 
    Document   : menu_login
    Created on : Jun 19, 2023, 2:03:22 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            #menu_login{
                background-image: url(images/alpha.jpg);
                background-position: center center;
                background-size: 100%;
                height: 500px;
                padding-top: 100px;
            }
            #inp{
                text-align: center;
                background-color: yellow;
/*                color: yellow;
                background: yellow;*/
/*                width: 40%;*/
                height : 200px;
                margin: auto;
                padding-left: 50px;
                /*                justify-items: flex;*/
            }
        </style>
    </head>
    <body>
        <form action="home" method="post">
            <div id='menu_login'>

                <div name='inp'>
                    <b><i style="color: yellow ">Log in using your account on FPT.EDU.VN </i></b><br>
                    <b>Account </b><br><input type="text" name="account" required> <br>
                    <b>Password</b><br><input type="text" name="password" required> <br>
                    <input type="submit" name='login' value="Login"> <br>
                    <b style="color:red">${mes}</b>
                </div>
            </div>
        </form>
        
    </body>
</html>
