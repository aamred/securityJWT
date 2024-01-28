package ua.com.securityApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.com.securityApp.models.Person;

/**
 * @author Anton Muzhytskyi
 */

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
	Optional<Person> findByUsername(String username);
}
