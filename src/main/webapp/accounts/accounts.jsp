<%@ page import="lab1.e3_bookstore.model.Account" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/9/2022
  Time: 8:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accounts</title>
</head>
<body>
<div>
    <h1>Accounts list</h1>
    <form action="<%=request.getContextPath()%>/accounts/" method="get">
        <label>Search:</label>
        <input name="search">
        <button>Search</button>
    </form>
    <form action="<%=request.getContextPath()%>/accounts/" method="post">
    <table border="1">
        <thead>
        <tr>
            <th>Username</th>
            <th>Password</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Admin</th>
        </tr>
        </thead>
        <tbody>
        <%for (Account acc: (List<Account>)request.getAttribute("accounts")) {%>
            <tr>
                <td><%=acc.getUsername()%></td>
                <td><%=acc.getPassword()%></td>
                <td><%=acc.getPhone()%></td>
                <td><%=acc.getAddress()%></td>
                <td><input type="checkbox" name="<%=acc.getUsername()%>" value="<%=acc.getUsername()%>" <%=acc.isAdmin()?"checked":""%>></td>
            </tr>
        <%}%>
        </tbody>
    </table>
        <button>Update</button>
    </form>
</div>
<a href="<%=request.getContextPath()%>/home">Back to home</a>
</body>
</html>
