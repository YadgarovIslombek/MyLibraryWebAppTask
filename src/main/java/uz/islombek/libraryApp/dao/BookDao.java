package uz.islombek.libraryApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.islombek.libraryApp.model.Book;

import java.util.List;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addBook(Book book){
        jdbcTemplate.update("INSERT INTO Book(bookName,author,year) VALUES (?,?,?)",book.getBookName(),book.getAuthor(),book.getYear());
    }
    public List<Book> getListBook(){
        return jdbcTemplate.query("SELECT * FROM Book ",new BeanPropertyRowMapper<>(Book.class));
    }
    public List<Book> takeBooks(int id){
        return jdbcTemplate.query("select bookname from person JOIN book on person.id = book.person_id where person_id = ?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class));

    }
    public Book getBookById(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id= ?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    public void updateBook(int id,Book book){
        jdbcTemplate.update("UPDATE Book SET bookName=?,author = ?,year=? where id = ?",book.getBookName(),book.getAuthor(),book.getYear(),id);
    }

}
