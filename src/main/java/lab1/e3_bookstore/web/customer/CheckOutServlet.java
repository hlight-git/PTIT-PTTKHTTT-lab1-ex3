package lab1.e3_bookstore.web.customer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/books/order/checkout")
public class CheckOutServlet extends CustomerServlet {
    private void showCheckOutForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("checkOut.jsp").forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!validate(request, response)){
            return;
        }
        backToCart(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!validate(request, response)){
            return;
        }
        showCheckOutForm(request, response);
    }
}
