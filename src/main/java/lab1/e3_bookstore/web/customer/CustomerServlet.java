package lab1.e3_bookstore.web.customer;

import lab1.e3_bookstore.model.Account;
import lab1.e3_bookstore.web.AuthenticationRequiredServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class CustomerServlet extends AuthenticationRequiredServlet {
    @Override
    protected boolean validate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Account curAccount = (Account) httpSession.getAttribute("curAccount");
        if (curAccount == null || curAccount.isAdmin()){
            backToHome(request, response);
            return false;
        }
        return true;
    }

    protected void backToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/books/cart");
    }
}
