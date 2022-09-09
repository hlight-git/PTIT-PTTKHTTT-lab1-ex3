package lab1.e3_bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Account account;
    private List<BookInCart> bookInCarts;

    public Cart(){
        bookInCarts = new ArrayList<>();
    }
    public Cart(List<BookInCart> bookInCarts) {
        this.bookInCarts = bookInCarts;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<BookInCart> getBooksInCart() {
        return bookInCarts;
    }

    public void setBooksInCart(List<BookInCart> booksInCart) {
        this.bookInCarts = booksInCart;
    }
}
