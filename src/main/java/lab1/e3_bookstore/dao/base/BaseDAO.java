package lab1.e3_bookstore.dao.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseDAO {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/ex3?useSSL=false";
    private static final String jdbcUser = "root";
    private static final String jdbcPass = "Hung001201023360.";

    private String table;
    private List<String> columns;

    public BaseDAO(String table, String[] otherColumns, String... idColumns){
        this.table = table;
        this.columns = new ArrayList<>();
        this.columns.addAll(Arrays.asList(idColumns));
        this.columns.addAll(Arrays.asList(otherColumns));
    }

    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
    }
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

}
