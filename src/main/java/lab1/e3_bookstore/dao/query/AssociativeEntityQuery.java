package lab1.e3_bookstore.dao.query;

import lab1.e3_bookstore.dao.base.BaseAssociativeEntityDAO;

import java.util.Arrays;
import java.util.List;

public final class AssociativeEntityQuery {
    private BaseAssociativeEntityDAO dao;

    public AssociativeEntityQuery(BaseAssociativeEntityDAO dao){
        this.dao = dao;
    }

    public String SELECT_ALL() {
        return String.format("SELECT * FROM %s WHERE %s = ?;", dao.getTable(), dao.getPid());
    }

    public String SELECT_EXACT(){
        return String.format("SELECT * FROM %s WHERE %s = ? AND %s = ?;", dao.getTable(), dao.getPid(), dao.getSid());
    }

    public String SELECT_WHERE(String conditions) {
        return String.format("SELECT * FROM %s WHERE %s;", dao.getTable(), conditions);
    }

    public String INSERT() {
        String cols = "(" + String.join(", ", dao.getColumns()) + ")";
        String values = "(" +
                String.join(", ",
                        Arrays.asList(
                                new String(new char[dao.getColumns().size()])
                                        .replace("\0", "?")
                                        .split("")
                        )
                ) +
                ")";
        return String.format("INSERT INTO %s %s VALUES %s;", dao.getTable(), cols, values);
    }

    public String UPDATE() {
        String set = String.join(" = ?, ", dao.getColumns()) + " = ?";
        return String.format("UPDATE %s SET %s WHERE %s = ? AND %s = ?;", dao.getTable(), set, dao.getPid(), dao.getSid());
    }

    public String DELETE() {
        return String.format("DELETE FROM %s WHERE %s = ? AND %s = ?;", dao.getTable(), dao.getPid(), dao.getSid());
    }

    public String DELETE_ALL(){
        return String.format("DELETE FROM %s WHERE %s = ?;", dao.getTable(), dao.getPid());
    }
}
