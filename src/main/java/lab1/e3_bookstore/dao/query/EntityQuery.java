package lab1.e3_bookstore.dao.query;

import lab1.e3_bookstore.dao.base.BaseEntityDAO;

import java.util.Arrays;
import java.util.List;

public final class EntityQuery {
    private BaseEntityDAO dao;

    public EntityQuery(BaseEntityDAO dao) {
        this.dao = dao;
    }

    public String SELECT_ALL() {
        return String.format("SELECT * FROM %s;", dao.getTable());
    }

    public String SELECT_BY_ID() {
        return String.format("SELECT * FROM %s WHERE %s = ?;", dao.getTable(), dao.getIdCol());
    }

    public String SELECT_WHERE(String conditions) {
        return String.format("SELECT * FROM %s WHERE %s;", dao.getTable(), conditions);
    }

    public String INSERT(boolean isAutoIncrementId) {
        List<String> columns = dao.getColumns().subList(isAutoIncrementId?1:0, dao.getColumns().size());
        String cols = "(" + String.join(", ", columns) + ")";
        String values = "(" +
                String.join(", ",
                        Arrays.asList(
                                new String(new char[columns.size()])
                                        .replace("\0", "?")
                                        .split("")
                        )
                ) +
                ")";
        return String.format("INSERT INTO %s %s VALUES %s;", dao.getTable(), cols, values);
    }

    public String UPDATE() {
        String set = String.join(" = ?, ", dao.getColumns()) + " = ?";
        return String.format("UPDATE %s SET %s WHERE %s = ?;", dao.getTable(), set, dao.getIdCol());
    }

    public String DELETE() {
        return String.format("DELETE FROM %s WHERE %s = ?;", dao.getTable(), dao.getIdCol());
    }
}
