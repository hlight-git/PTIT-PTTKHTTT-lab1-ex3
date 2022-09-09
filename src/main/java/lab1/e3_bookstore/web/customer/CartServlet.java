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

@WebServlet("/books/cart")
public class CartServlet extends CustomerServlet {
    private static final long serialVersionUID = 1L;
    private BookInCartDAO bookInCartDAO;
    private BookDAO bookDAO;

    public void init(){
        bookInCartDAO = new BookInCartDAO();
        bookDAO = bookInCartDAO.getBookDAO();
    }
    private void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
    private void addBookToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = ((Account) request.getSession().getAttribute("curAccount"));
        Book book = bookDAO.selectByID(Integer.parseInt(request.getParameter("id")));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        boolean found = false;
        for(BookInCart bic:account.getCart().getBooksInCart()){
            if (bic.getBook().getId() == book.getId()){
                bic.setQuantity(bic.getQuantity() + quantity);
                found = true;
                break;
            }
        }
        if (!found) {
            account.getCart().getBooksInCart().add(new BookInCart(account, book, quantity));
        }
        backToBooksList(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!validate(request, response)){
            return;
        }
        showCart(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!validate(request, response)){
            return;
        }
        addBookToCart(request, response);
    }
}
