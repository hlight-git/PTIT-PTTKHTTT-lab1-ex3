<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/8/2022
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="lab1.e3_bookstore.model.Cart" %>
<%@ page import="lab1.e3_bookstore.model.BookInCart" %>
<%@ page import="lab1.e3_bookstore.model.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% Account account = (Account) request.getSession().getAttribute("curAccount");%>
<html>
<head>
    <title>Books</title>
</head>
<body>
<div>
    <h1>Cart</h1>
    <form action="<%=request.getContextPath()%>/books/cart/update" method="post">
        <table border="1">
            <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Quantity</th>
                <th>Unit price</th>
                <th>Total price</th>
            </tr>
            </thead>
            <tbody>
            <%for (BookInCart b: account.getCart().getBooksInCart()) {%>
            <tr>
                <td><%=b.getBook().getId()%></td>
                <td><%=b.getBook().getTitle()%></td>
                <td><input name="<%=b.getBook().getId()%>" type="number" value="<%=b.getQuantity()%>" min="0"></td>
                <td><%=String.format("%.2f", b.getBook().getPrice())%></td>
                <td><%=String.format("%.2f", b.getBook().getPrice() * b.getQuantity())%>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
        <button>Update</button>
    </form>
</div>
<label>Subtotal: <%=(account.getCart().getBooksInCart().stream().
        mapToDouble(bic -> (bic.getQuantity() * bic.getBook().getPrice())).sum())%></label>
<br>
<form action="<%=request.getContextPath()%>/books/order/checkout" method="post">
    <button>Check out</button>
</form>
<a href="<%=request.getContextPath()%>/home">Back to home</a>
</body>
</html>
