<%@ page import="lab1.e3_bookstore.model.Book" %><%--
  Created by IntelliJ IDEA.
  Account: ADMIN
  Date: 9/2/2022
  Time: 4:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Book editingBook = (Book) request.getAttribute("editingBook");%>
<%
    Object message = request.getAttribute("message");
%>
<html>
<head>
    <title>Book management</title>
</head>
<body>
<h1><%=(editingBook == null) ? "Add" : "Edit"%> book</h1>
<form action="<%=request.getContextPath()%>/books/<%=(editingBook == null) ? "new":"edit"%>" method="post">
    <div>
        <label>ID:</label>
        <input
                name="id"
                value="<%=(editingBook != null)?editingBook.getId():""%>"
                readonly>
    </div>
    <div>
        <label>Title:</label>
        <input
                name="title"
                value="<%=(editingBook != null)?editingBook.getTitle():""%>"
        >
    </div>
    <div>
        <label>Price:</label>
        <input
                name="price"
                value="<%=(editingBook != null)?
                    String.format("%.2f", editingBook.getPrice()):""%>"
        >
    </div>
    <button>Add</button>
    <a href="books/">Go back</a>
</form>
<% if(message != null){%>
    <label>
        <%=message.toString()%>
    </label>
<%}%>
</body>
</html>
