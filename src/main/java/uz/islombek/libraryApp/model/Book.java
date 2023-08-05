package uz.islombek.libraryApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "bookName")
    private String bookName;
    @Column(name = "author")
    private String  author;
    @Column(name = "year")
    private int year;

    public Book(int id, String bookName, String author, int year) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.year = year;
    }


    public Book() {
    }

    public Book(String bookName, String author, int year) {
        this.bookName = bookName;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}
