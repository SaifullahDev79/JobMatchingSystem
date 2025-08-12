package sanitytest;

import jobmatchingsystem.model.Candidate;
import jobmatchingsystem.service.jdbc.JdbcCandidateService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
    	
    	System.out.println("CLASSPATH = " + System.getProperty("java.class.path"));

        var candSvc = new JdbcCandidateService();

        try {
            // Insert one row (change ID if it already exists)
            candSvc.add(new Candidate(301, "TestUser", 6, List.of("Java", "SQL")));
            System.out.println("Inserted candidate 301.");
        } catch (RuntimeException e) {
            System.out.println("Insert skipped (maybe duplicate ID): " + e.getMessage());
        }

        // Read all rows
        candSvc.findall().forEach(c ->
            System.out.println(c.getId() + " | " + c.getName() + " | " + c.getExperience() + " | " + c.getSkills())
        );
    }
}
