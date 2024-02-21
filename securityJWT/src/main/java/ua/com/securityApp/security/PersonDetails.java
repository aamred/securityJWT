package ua.com.securityApp.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ua.com.securityApp.models.Person;

/**
 * @author Anton Muzhytskyi
 */

public class PersonDetails implements UserDetails {

	private final Person person;
	
	
	public PersonDetails(Person person) {
		this.person = person;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// if authorities(no role) vozvrashaem spisok authorities
		return Collections.singletonList(new SimpleGrantedAuthority(person.getRole()) );
	}

	@Override
	public String getPassword() {
		return this.person.getPassword();
	}

	@Override
	public String getUsername() {
		return this.person.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	//need for getting data about this person
	public Person getPerson() {
		return this.person;
	}	

}
