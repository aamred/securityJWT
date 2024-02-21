package ua.com.securityApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.com.securityApp.models.Person;
import ua.com.securityApp.services.PersonDetailsService;

/**
 * @author Anton Muzhytskyi
 */

@Component
public class PersonValidator implements Validator{
	
	private final PersonDetailsService personDetailsService;
	
	@Autowired
	public PersonValidator(PersonDetailsService personDetailsService) {
		this.personDetailsService = personDetailsService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Person person = (Person)target;
		
		try {
			personDetailsService.loadUserByUsername(person.getUsername());
		} catch (UsernameNotFoundException ignored) {
			
		}
		errors.rejectValue("username","", "Person with this name already exist");
		
	}

}
