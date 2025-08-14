package sanitytest;

//import jobmatchingsystem.model.Candidate;
import jobmatchingsystem.model.Job;
import jobmatchingsystem.service.*;
import jobmatchingsystem.service.jdbc.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
    	
    	
    	AuthService auth = new JdbcAuthService();

    	System.out.println("Register: " + auth.register("testuser", "testpass", "user")); // true first time, false if duplicate
    	System.out.println("Login OK: " + auth.login("testuser", "testpass").isPresent()); // true
    	System.out.println("Login NG: " + auth.login("testuser", "wrong").isPresent());    // false

        }
}
