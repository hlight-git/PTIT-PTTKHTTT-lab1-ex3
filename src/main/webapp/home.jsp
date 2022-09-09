<%@ page import="lab1.e3_bookstore.model.Account" %><%--
  Created by IntelliJ IDEA.
  Account: ADMIN
  Date: 8/26/2022
  Time: 12:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Account curAccount = (Account) request.getSession().getAttribute("curAccount");
    boolean isAdmin = curAccount != null && curAccount.isAdmin();
%>
<head>
    <title>Home</title>
</head>
<body>
<label>Hello <strong><%=((Account)request.getSession().getAttribute("curAccount")).getUsername()%></strong>, let's go to...
    </label>
<br/>
    <%if (isAdmin){%>
        <a href="books/">Book management</a><br/>
        <a href="accounts/">Account management</a><br/>
    <%} else {%>
        <a href="books/">Shopping</a><br/>
        <a href="books/cart">Check Cart</a><br/>
    <%}%>
    <br>
    <form action="<%=request.getContextPath()%>/home" method="post">
        <button>Log out</button>
    </form>
</body>
</html>
