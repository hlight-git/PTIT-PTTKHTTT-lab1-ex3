package lab1.e3_bookstore.dao;

import lab1.e3_bookstore.dao.base.BaseEntityDAO;
import lab1.e3_bookstore.model.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends BaseEntityDAO<Account, String> {

    public AccountDAO(){
        super("account", "username", "password", "phone", "address", "is_admin");
    }

    @Override
    public List<Account> selectAll() {
        List<Account> res = new ArrayList<>();
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(query.SELECT_ALL())){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                res.add(
                        new Account(
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("phone"),
                                rs.getString("address"),
                                rs.getBoolean("is_admin")
                        )
                );
            }
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public List<Account> selectWhere(String conditions) {
        List<Account> res = new ArrayList<>();
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(query.SELECT_WHERE(conditions))){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                res.add(
                        new Account(
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("phone"),
                                rs.getString("address"),
                                rs.getBoolean("is_admin")
                        )
                );
            }
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Account selectByID(String username) {
        Account res = null;
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(query.SELECT_BY_ID())){
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                res = new Account(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getBoolean("is_admin")
                );
            }
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int insert(Account account) throws ClassNotFoundException, SQLException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement(query.INSERT(false));
        preparedStatement.setString(1, account.getUsername());
        preparedStatement.setString(2, account.getPassword());
        preparedStatement.setString(3, account.getPhone());
        preparedStatement.setString(4, account.getAddress());
        preparedStatement.setBoolean(5, account.isAdmin());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int update(Account account) throws ClassNotFoundException, SQLException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement(query.UPDATE());
        preparedStatement.setString(1, account.getUsername());
        preparedStatement.setString(2, account.getPassword());
        preparedStatement.setString(3, account.getPhone());
        preparedStatement.setString(4, account.getAddress());
        preparedStatement.setBoolean(5, account.isAdmin());
        preparedStatement.setString(6, account.getUsername());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(String username) throws ClassNotFoundException, SQLException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                        query.DELETE()
                );
        preparedStatement.setString(1, username);
        return preparedStatement.executeUpdate();
    }
}
