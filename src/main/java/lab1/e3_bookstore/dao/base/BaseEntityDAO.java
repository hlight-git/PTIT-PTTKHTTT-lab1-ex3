package lab1.e3_bookstore.dao.base;

import lab1.e3_bookstore.dao.query.EntityQuery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseEntityDAO <T, ID> extends BaseDAO{
    private String idCol;
    protected EntityQuery query;
    protected BaseEntityDAO(String table, String idCol, String... otherCols){
        super(table, otherCols, idCol);
        this.idCol = idCol;
        query = new EntityQuery(this);
    }
    public String getIdCol() {
        return idCol;
    }

    public void setIdCol(String idCol) {
        this.idCol = idCol;
    }

    public abstract List<T> selectAll();
    public abstract List<T> selectWhere(String conditions);
    public abstract T selectByID(ID id);
    public abstract int insert(T t) throws ClassNotFoundException, SQLException;
    public abstract int update(T t) throws ClassNotFoundException, SQLException;
    public abstract int delete(ID id) throws ClassNotFoundException, SQLException;
}
