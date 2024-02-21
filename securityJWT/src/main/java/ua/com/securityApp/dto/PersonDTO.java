package ua.com.securityApp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * @author Anton Muzhytskyi
 */

public class PersonDTO {

	@NotEmpty(message = "The name should not be empty")
	@Size(min = 2, max = 100, message = "Name should be from 2 to 100 symbols length")
	private String username;
	
	
	@Min(value = 1900, message = "Year of Birth should be older then 1900")
	private int yearOfBirth;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
