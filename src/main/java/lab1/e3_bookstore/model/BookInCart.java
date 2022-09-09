package lab1.e3_bookstore.model;

public class BookInCart {
    private Account customer;
    private Book book;
    private int quantity;

    public BookInCart(Account customer, Book book, int quantity) {
        this.customer = customer;
        this.book = book;
        this.quantity = quantity;
    }

    public Account getCustomer() {
        return customer;
    }

    public void setCustomer(Account customer) {
        this.customer = customer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
