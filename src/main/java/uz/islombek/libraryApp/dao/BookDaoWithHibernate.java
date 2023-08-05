package uz.islombek.libraryApp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import uz.islombek.libraryApp.model.Book;
import uz.islombek.libraryApp.model.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDaoWithHibernate {
    private final SessionFactory sessionFactory;
    @Autowired
    public BookDaoWithHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void addBook(Book book){

    }
    @Transactional
    public List<Book> getListBook(){
        return null;
    }
    @Transactional
    public List<Book> takeBooks(int id){
        return null;

    }
    @Transactional
    public Book getBookById(int id){
        return null;
    }
    @Transactional
    public Optional<Person> getOwnerBook(int id){
        return null;
    }
    @Transactional
    public int takePersonId(int id) {
        return 0;
    }
    @Transactional
    public void updateBook(int id,Book book){
    }
    @Transactional
    public void returnLibraryBook(int id){
    }
    @Transactional
    public void assign(int id,Person selectedPerson){
    }

}
