package lab1.e3_bookstore.dao;


import lab1.e3_bookstore.dao.base.BaseAssociativeEntityDAO;
import lab1.e3_bookstore.model.Account;
import lab1.e3_bookstore.model.Book;
import lab1.e3_bookstore.model.BookInCart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookInCartDAO extends BaseAssociativeEntityDAO<BookInCart, Account, Book> {
    private BookDAO bookDAO;
    private AccountDAO accountDAO;
    public BookInCartDAO(){
        super("book_in_cart", "customer_username", "book_id", "quantity");
        bookDAO = new BookDAO();
        accountDAO = new AccountDAO();
    }
    public BookDAO getBookDAO(){
        return bookDAO;
    }
    @Override
    public List<BookInCart> selectAllOf(Account customer) {
        List<BookInCart> res = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query.SELECT_ALL())){
            preparedStatement.setString(1, customer.getUsername());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                res.add(
                        new BookInCart(
                                accountDAO.selectByID(rs.getString("customer_username")),
                                bookDAO.selectByID(rs.getInt("book_id")),
                                rs.getInt("quantity")
                        )
                );
            }
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public BookInCart selectExact(Account customer, Book book) {
        BookInCart res = null;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query.SELECT_EXACT())){
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setInt(2, book.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                res = new BookInCart(
                        accountDAO.selectByID(rs.getString("customer_username")),
                        bookDAO.selectByID(rs.getInt("book_id")),
                        rs.getInt("quantity")
                );
            }
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public List<BookInCart> selectWhere(String conditions) {
        // code later...
        return null;
    }

    @Override
    public int insert(BookInCart bic) throws ClassNotFoundException, SQLException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement(query.INSERT());
        preparedStatement.setString(1, bic.getCustomer().getUsername());
        preparedStatement.setInt(2, bic.getBook().getId());
        preparedStatement.setInt(3, bic.getQuantity());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int update(BookInCart bic) throws ClassNotFoundException, SQLException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement(query.UPDATE());
        preparedStatement.setString(1, bic.getCustomer().getUsername());
        preparedStatement.setInt(2, bic.getBook().getId());
        preparedStatement.setInt(3, bic.getQuantity());
        preparedStatement.setString(4, bic.getCustomer().getUsername());
        preparedStatement.setInt(5, bic.getBook().getId());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(BookInCart o) throws ClassNotFoundException, SQLException {
        // code later...
        return 0;
    }

    @Override
    public int deleteAllOf(Account customer) throws ClassNotFoundException, SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(query.DELETE_ALL());
        preparedStatement.setString(1, customer.getUsername());
        return preparedStatement.executeUpdate();
    }

}
