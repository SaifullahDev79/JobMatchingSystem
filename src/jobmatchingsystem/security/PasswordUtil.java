package jobmatchingsystem.security;

import java.nio.charset.*;
import java.security.MessageDigest;

public class PasswordUtil {
	public static String sha256(String raw) { // take a raw pass and return hexa string
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256"); // creates md, the hashing engine
			byte[] bytes = md.digest(raw.getBytes(StandardCharsets.UTF_8));// creates bytes[] to store bytes
			// digest does the hashing but in binary so to convert getBytes is used
			
			StringBuilder sb = new StringBuilder();
			for( byte b : bytes) { // run through every byte in bytes
				sb.append(String.format("%02x",b)); // convert every b into hexadecimal from decimal and append.
				
			}
			return sb.toString(); // returns the final hexadecimal appended 
		}
		catch(Exception ex) {
			throw new RuntimeException ("Failed to Hash Passwords" , ex);
		}
		
	}
}
