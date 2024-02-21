package ua.com.securityApp.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @author Anton Muzhytskyi
 */

@Service
public class AdminService {
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")    // or hasRole('ROLE_GOD')
	public void doAdminStuff() {
		System.out.println("Only amin can be here");
	}

}
