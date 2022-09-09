package lab1.e3_bookstore.web.anonymous;

import lab1.e3_bookstore.dao.AccountDAO;
import lab1.e3_bookstore.model.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountDAO accountDao;

    public void init(){
        accountDao = new AccountDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("curAccount") != null){
            response.sendRedirect(request.getContextPath() + "/home");
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = new Account(
                request.getParameter("username"),
                request.getParameter("password"),
                request.getParameter("phone"),
                request.getParameter("address"),
                false
        );
        try {
            if (accountDao.selectByID(account.getUsername()) == null){
                accountDao.insert(account);
                request.setAttribute("message", "Register successfully!");
            }
            else {
                request.setAttribute("message", "Account already exists!");
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        doGet(request, response);
//        request.setAttribute("registerMessage", "");
    }
}
