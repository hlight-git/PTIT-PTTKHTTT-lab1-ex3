package lab1.e3_bookstore.web.anonymous;

import lab1.e3_bookstore.Test;
import lab1.e3_bookstore.dao.AccountDAO;
import lab1.e3_bookstore.dao.BookDAO;
import lab1.e3_bookstore.dao.BookInCartDAO;
import lab1.e3_bookstore.model.Account;
import lab1.e3_bookstore.model.Book;
import lab1.e3_bookstore.model.BookInCart;
import lab1.e3_bookstore.model.Cart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountDAO accountDAO;
    private BookInCartDAO bookInCartDAO;
    private BookDAO bookDAO;

    public void init(){
        accountDAO = new AccountDAO();
        bookInCartDAO = new BookInCartDAO();
        bookDAO = new BookDAO();
    }
    private Account loginValidate(Account loginAcc){
        Account acc = accountDAO.selectByID(loginAcc.getUsername());
        if (acc == null){
            return null;
        }
        if (acc.getPassword().equals(loginAcc.getPassword())){
            return acc;
        }
        return loginAcc;
    }
    private void loadAccountData(Account account, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("curAccount", account);
        Cart cart = new Cart(bookInCartDAO.selectAllOf(account));
        account.setCart(cart);
    }
    public boolean tryLogin(Account loginAcc, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (loginAcc != null) {
            Account result = loginValidate(loginAcc);
            if (result == null){
                request.setAttribute("message", "Account doesn't exist.");
            }
            else if (result == loginAcc){
                request.setAttribute("message", "Wrong username or password.");
            }
            else {
                loadAccountData(result, request, response);
                response.sendRedirect(request.getContextPath() + "/home");
                return true;
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
        return false;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        tryLogin((Account) session.getAttribute("curAccount"), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account loginAcc = new Account(username, password);
        tryLogin(loginAcc, request, response);
    }
}
