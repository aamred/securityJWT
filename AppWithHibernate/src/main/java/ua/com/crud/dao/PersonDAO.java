package ua.com.crud.dao;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ua.com.crud.models.Person;

/**
 * @author Anton Muzhytskyi
 */
@Component
public class PersonDAO {
	
//	private final JdbcTemplate jdbcTemplate;	
//	@Autowired
//	public PersonDAO(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}
	
	private final SessionFactory sessionFactory;
	
	
		@Autowired
	    public PersonDAO(SessionFactory sessionFactory) {
			super();
			this.sessionFactory = sessionFactory;
		}

		@Transactional(readOnly = true)
		public List<Person> index() {
	        //return jdbcTemplate.query("SELECT *FROM Person", new BeanPropertyRowMapper<>(Person.class));
	    	Session session = sessionFactory.getCurrentSession();
	    	return session.createQuery("select p from Person p", Person.class).getResultList();
			
	    }

		@Transactional(readOnly=true)
	    public Person show(int id) {
			// return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
			//	      .stream().findAny().orElse(null);
			Session session = sessionFactory.getCurrentSession();
			return session.get(Person.class, id);
	    }

		
		@Transactional
	    public void save(Person person) {
			//jdbcTemplate.update("INSERT INTO Person VALUES(1, ?, ?, ?)", person.getName(), person.getAge(),
			//	           person.getEmail());
			Session session = sessionFactory.getCurrentSession();
	        session.save(person);
	    }

		
		@Transactional
	    public void update(int id, Person updatedPerson) {
			//jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?", updatedPerson.getName(),
			//         updatedPerson.getAge(), updatedPerson.getEmail(), id);
			Session session = sessionFactory.getCurrentSession();
			Person personToBeUpdated = session.get(Person.class, id);
			
			personToBeUpdated.setName(updatedPerson.getName());
			personToBeUpdated.setAge(updatedPerson.getAge());
			personToBeUpdated.setEmail(updatedPerson.getEmail());
	    }

		
		@Transactional
	    public void delete(int id) {
			//jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
			Session session = sessionFactory.getCurrentSession();
			session.remove(session.get(Person.class, id));     //remove = delete
	    }
	
}
