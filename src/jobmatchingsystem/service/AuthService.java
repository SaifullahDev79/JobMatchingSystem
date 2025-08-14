package jobmatchingsystem.service;

import java.util.Optional;
import jobmatchingsystem.model.*;

public interface AuthService {
	
	// override them in the jdbcauthservice class
	Optional<User> login(String username, String rawPassword);
	boolean register(String username , String rawPassword, String role); // return false if username taken
}
