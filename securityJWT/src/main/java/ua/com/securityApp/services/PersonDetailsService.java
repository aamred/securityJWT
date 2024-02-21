package ua.com.securityApp.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.com.securityApp.models.Person;
import ua.com.securityApp.repositories.PeopleRepository;
import ua.com.securityApp.security.PersonDetails;

/**
 * @author Anton Muzhytskyi
 */

@Service
public class PersonDetailsService implements UserDetailsService {
	
	private final PeopleRepository peopleRepository;
		
	@Autowired
	public PersonDetailsService(PeopleRepository peopleRepository) {
		this.peopleRepository = peopleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Person> person =peopleRepository.findByUsername(username);
		
		//if(person.empty() == null) 
			//throw new UsernameNotFoundException("User Not Found");
		return new PersonDetails(person.get());
	}
	
}
