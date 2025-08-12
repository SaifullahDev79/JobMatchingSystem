package sanitytest;

//import jobmatchingsystem.model.Candidate;
import jobmatchingsystem.model.Job;
import jobmatchingsystem.service.*;
import jobmatchingsystem.service.jdbc.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
    	
    	
            JobService jobSvc = new JdbcJobService();

            // Insert one job row (change the ID if it already exists)
            jobSvc.add(new Job(900, "Backend Engineer", 2, List.of("Java", "SQL", "SpringBoot")));

            // Read all rows
            jobSvc.findall().forEach(j -> 
                System.out.println(j.getId() + " | " + j.getTitle() + " | " +
                                   j.getMinExperience() + " | " + j.getRequiredSkills())
            );
        }
}
