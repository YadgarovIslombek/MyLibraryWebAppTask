package uz.islombek.libraryApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.islombek.libraryApp.model.Person;

import java.util.List;

@Component
public class PersonDaO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDaO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void addPerson(Person person){
        jdbcTemplate.update("INSERT INTO Person(name,year)VALUES (?,?)",person.getName(),person.getYear());
    }
    public List<Person> getListPerson(){
        return jdbcTemplate.query("SELECT * FROM PERSON ",new BeanPropertyRowMapper<>(Person.class));
    }
    public
    Person getPersonById(int id){
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE id= ?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    public void updatePerson(int id,Person person){
        jdbcTemplate.update("UPDATE Person SET name=?,year=? where id = ?",person.getName(),person.getYear(),id);
    }

}
