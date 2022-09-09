<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 8/26/2022
  Time: 12:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<%
    Object message = request.getAttribute("message");
%>
<div>
    <h1>Register</h1>
    <form action="<%=request.getContextPath()%>/register" method="post">
        <table>
            <tr>
                <td>UserName</td>
                <td><input type="text" name="username" required/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" required/></td>
            </tr>
            <tr>
                <td>Phone number</td>
                <td><input type="text" name="phone" required/></td>
            </tr>
            <tr>
                <td>Address</td>
                <td><input type="text" name="address" required/></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form>
    <% if(message != null){%>
        <label>
            <%=message.toString()%>
        </label>
    <%}%>
</div>
<a href="<%=request.getContextPath()%>/home">Back to home</a>
</body>
</html>
