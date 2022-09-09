package lab1.e3_bookstore.web;

import lab1.e3_bookstore.dao.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/books/")
public class BookListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected BookDAO bookDAO;

    public void init(){
        bookDAO = new BookDAO();
    }
    private void showBooksList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        if (search != null){
            String conditions =
                    "id LIKE '%%s%' OR title LIKE '%%s%' OR price LIKE '%%s%';"
                            .replace("%s", search);
            request.setAttribute("books", bookDAO.selectWhere(conditions));
        }
        else {
            request.setAttribute("books", bookDAO.selectAll());
        }
        request.getRequestDispatcher("books.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // read - select
       showBooksList(request, response);
    }
}
