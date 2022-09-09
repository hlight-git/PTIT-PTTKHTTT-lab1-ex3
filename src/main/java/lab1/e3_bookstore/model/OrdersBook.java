package lab1.e3_bookstore.model;

public class OrdersBook {
    private Orders orders;
    private Book book;
    private float price;
    private int quantity;

    public OrdersBook(Orders orders, Book book, float price, int quantity) {
        this.orders = orders;
        this.book = book;
        this.price = price;
        this.quantity = quantity;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
