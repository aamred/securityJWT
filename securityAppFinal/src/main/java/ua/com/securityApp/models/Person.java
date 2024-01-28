package ua.com.securityApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * @author Anton Muzhytskyi
 */

@Entity
@Table(name="Person")
public class Person {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "The name should not be empty")
	@Size(min = 2, max = 100, message = "Name should be from 2 to 100 symbols length")
	@Column(name = "username")
	private String username;
	
	
	@Min(value = 1900, message = "Year of Birth should be older then 1900")
	@Column(name = "year_of_birth")
	private int yearOfBirth;
	
	@Column(name = "password")
	private String password;
	
	@Column(name="role")
	private String role;

	public Person() {
	}

	public Person(
			@NotEmpty(message = "The name should not be empty") @Size(min = 2, max = 100, message = "Name should be from 2 to 100 symbols length") String username,
			@Min(value = 1900, message = "Year of Birth should be older then 1900") int yearOfBirth) {
		this.username = username;
		this.yearOfBirth = yearOfBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
}