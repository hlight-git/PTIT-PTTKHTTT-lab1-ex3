package lab1.e3_bookstore.web.management;

import lab1.e3_bookstore.model.Account;
import lab1.e3_bookstore.web.AuthenticationRequiredServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public abstract class AdminServlet extends AuthenticationRequiredServlet {
    protected boolean validate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Account curAccount = (Account) httpSession.getAttribute("curAccount");
        if (curAccount == null || !curAccount.isAdmin()){
            backToHome(request, response);
            return false;
        }
        return true;
    }
    protected void backToHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/home");
    }

    protected void backToBooksList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/books");
    }
}
