package lab1.e3_bookstore.dao.base;

import lab1.e3_bookstore.dao.query.AssociativeEntityQuery;

import java.sql.SQLException;
import java.util.List;

public abstract class BaseAssociativeEntityDAO<O, P, S> extends BaseDAO{
    private String pid;
    private String sid;
    protected AssociativeEntityQuery query;

    protected BaseAssociativeEntityDAO(String table, String pid, String sid, String... otherCols){
        super(table, otherCols, pid, sid);
        this.pid = pid;
        this.sid = sid;
        query = new AssociativeEntityQuery(this);
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public abstract List<O> selectAllOf(P p);
    public abstract O selectExact(P p, S s);
    public abstract List<O> selectWhere(String conditions);
    public abstract int insert(O o) throws ClassNotFoundException, SQLException;
    public abstract int update(O o) throws ClassNotFoundException, SQLException;
    public abstract int delete(O o) throws ClassNotFoundException, SQLException;
    public abstract int deleteAllOf(P p) throws ClassNotFoundException, SQLException;
}