<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 8/25/2022
  Time: 11:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object message = request.getAttribute("message");
%>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <div>
        <h1>Login</h1>
        <form action="<%=request.getContextPath()%>/login" method="post">
            <table style="with: 100%">
                <tr>
                    <td>UserName</td>
                    <td><input type="text" name="username" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" /></td>
                </tr>

            </table>
            <input type="submit" value="Submit" />
        </form>
        <a href="<%=request.getContextPath()%>/register">Register</a>
    </div>
    <% if(message != null){%>
        <label>
            <%=message.toString()%>
        </label>
    <%}%>
</body>
</html>
