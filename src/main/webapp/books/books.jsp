<%@ page import="lab1.e3_bookstore.model.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="lab1.e3_bookstore.model.Account" %>
<%--
  Created by IntelliJ IDEA.
  Account: ADMIN
  Date: 9/2/2022
  Time: 4:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Account curAccount = (Account) request.getSession().getAttribute("curAccount");
    boolean isAdmin = curAccount != null && curAccount.isAdmin();
%>
<%
    Object message = request.getAttribute("message");
%>
<html>
<head>
    <title>Books</title>
</head>
<body>
<div>
    <h1>Books list</h1>
    <form action="<%=request.getContextPath()%>/books" method="get">
        <label>Search:</label>
        <input name="search">
        <button>Search</button>
    </form>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%for (Book b: (List <Book>)request.getAttribute("books")) {%>
                <tr>
                    <td><%=b.getId()%></td>
                    <td><%=b.getTitle()%></td>
                    <td><%=String.format("%.2f", b.getPrice())%></td>
                    <td>
                        <% if (isAdmin){%>
                            <form action="<%=request.getContextPath()%>/books/edit" method="get">
                                <button name="id" value="<%=b.getId()%>">Edit</button>
                            </form>
                            <form action="<%=request.getContextPath()%>/books/delete" method="post">
                                <button name="id" value="<%=b.getId()%>">Delete</button>
                            </form>
                        <%} else {%>
                            <form action="<%=request.getContextPath()%>/books/cart" method="post">
                                <input name="quantity" type="number" min="1" value="1">
                                <button name="id" value="<%=b.getId()%>">Add</button>
                            </form>
                        <%}%>
                    </td>
                </tr>
            <%}%>
        </tbody>
    </table>
</div>
<div>
    <a href="<%=isAdmin?"new":"book/cart"%>"><%=isAdmin?"Add book":"Check cart"%></a>
</div>
<% if(message != null){%>
<label>
    <%=message.toString()%>
</label>
<%}%>
<a href="<%=request.getContextPath()%>/home">Back to home</a>
</body>
</html>
