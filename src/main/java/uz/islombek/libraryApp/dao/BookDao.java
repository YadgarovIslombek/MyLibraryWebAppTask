package uz.islombek.libraryApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.islombek.libraryApp.model.Book;
import uz.islombek.libraryApp.model.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {
//    private final JdbcTemplate jdbcTemplate;
//    @Autowired
//    public BookDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public void addBook(Book book){
//        jdbcTemplate.update("INSERT INTO Book(bookName,author,year) VALUES (?,?,?)",book.getBookName(),book.getAuthor(),book.getYear());
//    }
//    public List<Book> getListBook(){
//        return jdbcTemplate.query("SELECT * FROM Book ",new BeanPropertyRowMapper<>(Book.class));
//    }
//    public List<Book> takeBooks(int id){
//        return jdbcTemplate.query("select bookname from person JOIN book on person.id = book.person_id where person_id = ?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class));
//
//    }
//    public Book getBookById(int id){
//        return jdbcTemplate.query("SELECT * FROM Book WHERE id= ?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
//    }
//    public Optional<Person> getOwnerBook(int id){
//        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id WHERE Book.id = ?",
//                new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//    }
//
//    public int takePersonId(int id){
//        return jdbcTemplate.update("select person_id from book where id = ?",new Integer[]{id},new BeanPropertyRowMapper<>(Integer.class));
//    }
//    public void updateBook(int id,Book book){
//        jdbcTemplate.update("UPDATE Book SET bookName=?,author = ?,year=? where id = ?",book.getBookName(),book.getAuthor(),book.getYear(),id);
//    }
//
//    public void returnLibraryBook(int id){
//        jdbcTemplate.update("UPDATE book set person_id = null where id = ?",id);
//    }
//    public void assign(int id,Person selectedPerson){
//        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", selectedPerson.getId(), id);
//    }

}
