package lab1.e3_bookstore.web.customer;

import lab1.e3_bookstore.dao.BookInCartDAO;
import lab1.e3_bookstore.dao.OrdersBookDAO;
import lab1.e3_bookstore.dao.OrdersDAO;
import lab1.e3_bookstore.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/books/order/")
public class OrderServlet extends CustomerServlet {
    private static final long serialVersionUID = 1L;
    private OrdersDAO ordersDAO;
    private BookInCartDAO bookInCartDAO;
    private OrdersBookDAO ordersBookDAO;

    public void init(){
        bookInCartDAO = new BookInCartDAO();
        ordersBookDAO = new OrdersBookDAO();
        ordersDAO = ordersBookDAO.getOrdersDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!validate(request, response)){
            return;
        }
        backToCart(request, response);
    }
    private void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = ((Account) request.getSession().getAttribute("curAccount"));
        try{
            bookInCartDAO.deleteAllOf(account);
            account.setCart(new Cart());
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    private void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = ((Account) request.getSession().getAttribute("curAccount"));
        try {
            Orders orders = new Orders(account);
            ordersDAO.insert(orders);
            Orders createdOrders = ordersDAO.selectLastOrderOf(account);
            List<OrdersBook> ordersBooks =
                    account.getCart().getBooksInCart().stream()
                            .map(bic ->
                                    new OrdersBook(
                                            createdOrders,
                                            bic.getBook(),
                                            bic.getBook().getPrice(),
                                            bic.getQuantity()
                                    )
                            ).collect(Collectors.toList());
            for (OrdersBook ob:ordersBooks){
                ordersBookDAO.insert(ob);
            }
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    private void showOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("order.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!validate(request, response)){
            return;
        }
        createOrder(request, response);
        showOrder(request, response);
        clearCart(request, response);
    }
}
