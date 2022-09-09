package lab1.e3_bookstore.dao;

import lab1.e3_bookstore.dao.base.BaseAssociativeEntityDAO;
import lab1.e3_bookstore.model.Book;
import lab1.e3_bookstore.model.Orders;
import lab1.e3_bookstore.model.OrdersBook;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersBookDAO extends BaseAssociativeEntityDAO<OrdersBook, Orders, Book> {
    private OrdersDAO ordersDAO;
    private BookDAO bookDAO;
    public OrdersBookDAO(){
        super("orders_book", "orders_id", "book_id", "price", "quantity");
        ordersDAO = new OrdersDAO();
        bookDAO = new BookDAO();
    }

    public OrdersDAO getOrdersDAO() {
        return ordersDAO;
    }

    @Override
    public List<OrdersBook> selectAllOf(Orders orders) {
        List<OrdersBook> res = new ArrayList<>();
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(query.SELECT_ALL())){
            preparedStatement.setInt(1, orders.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                res.add(
                        new OrdersBook(
                                ordersDAO.selectByID(rs.getInt("orders_id")),
                                bookDAO.selectByID(rs.getInt("book_id")),
                                rs.getFloat("price"),
                                rs.getInt("quantiy")
                        )
                );
            }
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public OrdersBook selectExact(Orders orders, Book book) {
        // code later...
        return null;
    }

    @Override
    public List<OrdersBook> selectWhere(String conditions) {
        // code later...
        return null;
    }

    @Override
    public int insert(OrdersBook ob) throws ClassNotFoundException, SQLException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement(query.INSERT());
        preparedStatement.setInt(1, ob.getOrders().getId());
        preparedStatement.setInt(2, ob.getBook().getId());
        preparedStatement.setFloat(3, ob.getPrice());
        preparedStatement.setInt(4, ob.getQuantity());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int update(OrdersBook o) throws ClassNotFoundException, SQLException {
        // code later...
        return 0;
    }

    @Override
    public int delete(OrdersBook o) throws ClassNotFoundException, SQLException {
        // code later...
        return 0;
    }

    @Override
    public int deleteAllOf(Orders orders) throws ClassNotFoundException, SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(query.DELETE_ALL());
        preparedStatement.setInt(1, orders.getId());
        return preparedStatement.executeUpdate();
    }
}
