package uz.islombek.libraryApp.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import uz.islombek.libraryApp.model.Person;

import java.util.List;

@Component
public class PersonDaOWithHibernate {
    private SessionFactory sessionFactory;

    @Autowired
    public PersonDaOWithHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addPerson(Person person) {

    }

    @Transactional
    public List<Person> getListPerson() {
        Session session = sessionFactory.getCurrentSession();

        //oddiy hibernate kod
        return session.createQuery("select p from Person p", Person.class)
                .getResultList();

    }

    @Transactional
    public Person getPersonById(int id) {
        return null;
    }

    @Transactional
    public void updatePerson(int id, Person person) {
    }

}
