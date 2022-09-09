package lab1.e3_bookstore.web.management.book;

import lab1.e3_bookstore.dao.BookDAO;
import lab1.e3_bookstore.web.management.AdminServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/books/delete")
public class DeleteBookServlet extends AdminServlet {
    private static final long serialVersionUID = 1L;

    private BookDAO bookDAO;

    public void init(){
        bookDAO = new BookDAO();
    }
    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            bookDAO.delete(Integer.parseInt(request.getParameter("id")));
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
        backToHome(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!validate(request, response)){
            return;
        }
        deleteBook(request, response);
    }
}
