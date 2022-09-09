package lab1.e3_bookstore.web.management.book;

import lab1.e3_bookstore.dao.BookDAO;
import lab1.e3_bookstore.model.Book;
import lab1.e3_bookstore.web.management.AdminServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/books/new")
public class AddBookServlet extends AdminServlet {
    private static final long serialVersionUID = 1L;

    private BookDAO bookDAO;

    public void init(){
        bookDAO = new BookDAO();
    }
    private void showAddNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("updateBook.jsp").forward(request, response);
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            bookDAO.insert(
                    new Book(
                            request.getParameter("title"),
                            Float.parseFloat(request.getParameter("price"))
                    )
            );
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        request.setAttribute("message", "Add new book successfully!");
        showAddNewForm(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!validate(request, response)){
            return;
        }
        showAddNewForm(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!validate(request, response)){
            return;
        }
        addBook(request, response);
    }
}
