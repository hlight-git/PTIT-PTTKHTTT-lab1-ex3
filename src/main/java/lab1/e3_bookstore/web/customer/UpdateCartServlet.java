package lab1.e3_bookstore.web.customer;

import lab1.e3_bookstore.dao.BookDAO;
import lab1.e3_bookstore.dao.BookInCartDAO;
import lab1.e3_bookstore.model.Account;
import lab1.e3_bookstore.model.Book;
import lab1.e3_bookstore.model.BookInCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/books/cart/update")
public class UpdateCartServlet extends CustomerServlet{
    private static final long serialVersionUID = 1L;
    private BookInCartDAO bookInCartDAO;

    public void init(){
        bookInCartDAO = new BookInCartDAO();
    }
    private void updateCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        updateSessionCart(request, response);
        updateDatabaseCart(request, response);
        backToCart(request, response);
    }
    private void updateDatabaseCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = ((Account) request.getSession().getAttribute("curAccount"));
        try {
            for (BookInCart bic:account.getCart().getBooksInCart()){
                BookInCart tmp = bookInCartDAO.selectExact(account, bic.getBook());
                if (tmp == null) {
                    bookInCartDAO.insert(bic);
                } else {
                    bookInCartDAO.update(bic);
                }
            }
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    private void updateSessionCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = ((Account) request.getSession().getAttribute("curAccount"));
        for (BookInCart bic:account.getCart().getBooksInCart()){
            bic.setQuantity(
                    Integer.parseInt(
                            request.getParameter(
                                    Integer.toString(
                                            bic.getBook().getId()
                                    )
                            )
                    )
            );
        }
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
        updateCart(request, response);
    }
}
