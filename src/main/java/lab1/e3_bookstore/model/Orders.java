package lab1.e3_bookstore.model;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

public class Orders {
    private int id;
    private Account customer;
    private List<Book> books;
    private Timestamp createAt;

    public Orders(int id, Account customer, Timestamp createAt) {
        this.id = id;
        this.customer = customer;
        this.createAt = createAt;
    }

    public Orders(Account account){
        customer = account;
        createAt = new Timestamp(new Date().getTime());
    }
    public int getId() {
        return id;
    }

    public Account getCustomer() {
        return customer;
    }

    public void setCustomer(Account customer) {
        this.customer = customer;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }
}
