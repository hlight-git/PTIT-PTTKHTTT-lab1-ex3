<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/8/2022
  Time: 9:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check out</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/books/order/" method="post">
    <select>
        <option>FakeBank</option>
        <option>UnrealBank</option>
    </select>
    <label>Account number:</label><input type="text">
    <label>PIN:</label><input type="text" maxlength="6" minlength="6">
    <button>Confirm</button>
</form>
<a href="<%=request.getContextPath()%>/books/cart">Back to cart</a>
</body>
</html>
