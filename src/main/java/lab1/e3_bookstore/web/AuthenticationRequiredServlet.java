package lab1.e3_bookstore.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AuthenticationRequiredServlet extends HttpServlet {
    protected abstract boolean validate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    protected void backToBooksList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/books");
    }
    protected void backToHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
