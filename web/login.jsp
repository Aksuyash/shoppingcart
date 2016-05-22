<%-- 
    Document   : Login
    Created on : May 21, 2016, 8:51:43 PM
    Author     : suyashadhikary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form method="post">
            <div style="padding: 100px 0 0 250px;">
                <div id="login-box">
                    <h2>Login Page</h2>
                    Please provide your credential to use this website
                    <br>
                    <br>
                    <div id="login-box-name" style="margin-top:20px;">User Id:</div>
                    <div id="login-box-field" style="margin-top:20px;">
                        <input name="userId" class="form-login" title="Username" value="" size="30" maxlength="50" />
                    </div>
                    <div id="login-box-name">Password:</div>
                    <div id="login-box-field">
                        <input name="password" type="password" class="form-login" title="Password" value="" size="30" maxlength="48" />
                    </div>
                    <br />
                    <span class="login-box-options">
                        New User?  <a href="register.jsp" style="margin-left:30px;">Register Here</a>
                    </span>
                    <br />
                    <br />
                    <input style="margin-left:100px;" type="submit" value="Login" />
                </div>
            </div>
        </form>

    </body>
</html>
