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

@WebServlet("/books/edit")
public class EditBookServlet extends AdminServlet {
    private static final long serialVersionUID = 1L;

    private BookDAO bookDAO;

    public void init(){
        bookDAO = new BookDAO();
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("editingBook", bookDAO.selectByID(id));
        request.getRequestDispatcher("updateBook.jsp").forward(request, response);
    }
    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        float price = Float.parseFloat(request.getParameter("price"));
        try {
            bookDAO.update(
                    new Book(
                            id,
                            title,
                            price
                    )
            );
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        backToBooksList(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!validate(request, response)){
            return;
        }
        showEditForm(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!validate(request, response)){
            return;
        }
        updateBook(request, response);
    }
}
