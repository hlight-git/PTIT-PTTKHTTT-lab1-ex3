package lab1.e3_bookstore.web.management.account;

import lab1.e3_bookstore.dao.AccountDAO;
import lab1.e3_bookstore.model.Account;
import lab1.e3_bookstore.web.management.AdminServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/accounts/")
public class AccountServlet extends AdminServlet {
    private static final long serialVersionUID = 1L;
    protected AccountDAO accountDAO;
    private List<Account> accounts;

    public void init(){
        accountDAO = new AccountDAO();
        accounts = accountDAO.selectAll();
    }
    private void showAccountsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        if (search != null){
            String conditions =
                    "username LIKE '%%s%' OR phone LIKE '%%s%' OR address LIKE '%%s%';"
                            .replace("%s", search);
            accounts = accountDAO.selectWhere(conditions);
        }
        else {
            accounts = accountDAO.selectAll();
        }
        request.setAttribute("accounts", accounts);
        request.getRequestDispatcher("accounts.jsp").forward(request, response);
    }
    private void changeRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            for (Account account : accounts) {
                account.setAdmin(request.getParameter(account.getUsername()) != null);
                accountDAO.update(account);
            }
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        changeRole(request, response);
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!validate(request, response)) {
            return;
        }
        showAccountsList(request, response);
    }
}
