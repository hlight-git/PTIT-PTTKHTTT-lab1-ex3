<%@ page import="lab1.e3_bookstore.model.Account" %>
<%@ page import="lab1.e3_bookstore.model.BookInCart" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/8/2022
  Time: 10:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Account account = (Account) request.getSession().getAttribute("curAccount");%>
        <html>
<head>
    <title>Order</title>
</head>
<h1>Order</h1>
<label>Address: <%=account.getAddress()%></label><br>
<label>Phone number: <%=account.getPhone()%></label>
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
        <td><input name="<%=b.getBook().getId()%>" type="number" value="<%=b.getQuantity()%>"></td>
        <td><%=String.format("%.2f", b.getBook().getPrice())%></td>
        <td><%=String.format("%.2f", b.getBook().getPrice() * b.getQuantity())%>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>
<label>Subtotal: <%=(account.getCart().getBooksInCart().stream().
        mapToDouble(bic -> (bic.getQuantity() * bic.getBook().getPrice())).sum())%></label>
<br>
<a href="<%=request.getContextPath()%>/home">Back to cart</a>
<body>

</body>
</html>
