package ua.com.securityApp.controllers;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ua.com.securityApp.dto.AuthenticatonDTO;
import ua.com.securityApp.dto.PersonDTO;
import ua.com.securityApp.models.Person;
import ua.com.securityApp.security.JWTUtil;
import ua.com.securityApp.services.RegistrationService;
import ua.com.securityApp.util.PersonValidator;

/**
 * @author Anton Muzhytskyi
 */

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final RegistrationService registrationService;
	private final PersonValidator personValidator;
	private final JWTUtil jwtUtil;
	private final ModelMapper modelMapper;
	private final AuthenticationMnager authenticationManager;
	
	
	@Autowired
	public AuthController(RegistrationService registrationService, PersonValidator personValidator
			, ModelMapper modelMapper, JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
		this.registrationService = registrationService;
		this.personValidator = personValidator;
		this.modelMapper = modelMapper;
		this.jwtUtil = jwtUtil; 
		this.authenticationManager = authenticationManager;
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "/auth/login";
	}

	@GetMapping("/registration")
	public String registrationPage(@ModelAttribute("person") Person person) {
		return "/auth/registration";
	}
	
	@PostMapping("/registration")
	public Map<String, String>  performRegistration(@RequestBody @Valid PersonDTO personDTO,
			BindingResult bindingResult) {
		Person person =convertToPerson(personDTO);
		personValidator.validate(person, bindingResult);
		if(bindingResult.hasErrors())
			return Map.of("message", "Error");
		
		registrationService.register(person);
		
		String token = jwtUtil.generateToken(person.getUsername());
		return Map.of("jwt-token", token);
	}
	
	
	@PostMapping("/login")
	public Map<String, String> performLogin (@RequestBody AuthenticatonDTO authenticationDTO){
		UsernamePasswordAuthenticationToken authInputToken = 
				new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername()
						, authenticationDTO.getPassword());
		try {
		authenticationManager.authenticate(authInputToken);
		} catch (BadCredentialsException e){
			return Map.of("message", "incorrect credentials");
		}
		
		String token = jwtUtil.generateToken(authenticationDTO.getUsername());
		return Map.of("jwt-token", token);
	}
	
	public Person convertToPerson(PersonDTO personDTO) {
		return this.modelMapper.map(personDTO, Person.class);
	}
}


