package sample;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
    private int id;
    private String name;
    private String author;
    private Date dateOfPublish; // to make the month start from 1 every time we going to set the value of the month I decrease it by on, and if I sent the value I increase it by 1
    private int quantity;
    private int numberOfRentedBooks;
    private String category;
    private static int nextId = 0; // this variable will increase for each book added, so each book will have a unique Id

    /**
     * @param name the name of the book
     * @param author the author name
     * @param yearOfPublish the year when the book is published
     * @param monthOfPublish the month when the book is published
     */
    public Book(String name, String author, int yearOfPublish, int monthOfPublish, String category) {
        this.id = nextId++; // we will assign the Id then we will increment the Id
        this.name = name;
        this.author = author;
        dateOfPublish = new Date(yearOfPublish, monthOfPublish - 1, 1); // I gonna set the day with one because we ganna ignore it
        this.category = category;
        this.quantity = 1;
        this.numberOfRentedBooks = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDateOfString() {
        return dateOfPublish.getYear() + " " + (dateOfPublish.getMonth() + 1); // we increase the month by 1 because the the months in date class in java starts from 0
    }

    public void setDateOfPublish(int yearOfPublish, int monthOfPublish) {
        this.dateOfPublish = new Date(yearOfPublish, monthOfPublish - 1, 0);
    }

    public int getQuantity() {
        return quantity;
    }

    public int getNumberOfRentedBooks() {
        return numberOfRentedBooks;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public void decreaseQuantity() {
        this.quantity--;
        this.numberOfRentedBooks++;
    }

    public static void setNextId(int nextId) {
        Book.nextId = nextId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
