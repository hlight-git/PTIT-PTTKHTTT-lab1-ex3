package lab1.e3_bookstore.dao;

import lab1.e3_bookstore.dao.base.BaseEntityDAO;
import lab1.e3_bookstore.model.Account;
import lab1.e3_bookstore.model.Book;
import lab1.e3_bookstore.model.Orders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrdersDAO extends BaseEntityDAO <Orders, Integer> {
    private AccountDAO accountDAO;
    public OrdersDAO() {
        super("orders", "id", "customer_username", "create_at");
        accountDAO = new AccountDAO();
    }

    @Override
    public List<Orders> selectAll() {
        return null;
    }

    @Override
    public List<Orders> selectWhere(String conditions) {
        return null;
    }

    @Override
    public Orders selectByID(Integer integer) {
        return null;
    }

    @Override
    public int insert(Orders orders) throws ClassNotFoundException, SQLException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement(query.INSERT(true));
        preparedStatement.setString(1, orders.getCustomer().getUsername());
        preparedStatement.setTimestamp(2, orders.getCreateAt());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int update(Orders orders) throws ClassNotFoundException, SQLException {
        return 0;
    }

    @Override
    public int delete(Integer integer) throws ClassNotFoundException, SQLException {
        return 0;
    }

    public Orders selectLastOrderOf(Account account) throws ClassNotFoundException, SQLException {
        Orders res = null;
        try(PreparedStatement preparedStatement =
                    getConnection()
                            .prepareStatement(
                                    query.SELECT_WHERE(
                                            "customer_username = ? ORDER BY create_at DESC;"))){
            preparedStatement.setString(1, account.getUsername());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                res = new Orders(
                        rs.getInt("id"),
                        accountDAO.selectByID(rs.getString("customer_username")),
                        rs.getTimestamp("create_at")
                );
                break;
            }
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return res;
    }
}
