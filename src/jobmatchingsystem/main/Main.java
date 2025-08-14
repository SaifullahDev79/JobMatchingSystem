package jobmatchingsystem.main;

import jobmatchingsystem.service.CandidateService;
import jobmatchingsystem.service.JobService;
import jobmatchingsystem.service.jdbc.JdbcCandidateService;
import jobmatchingsystem.service.jdbc.JdbcJobService;
import jobmatchingsystem.UI.ConsoleMenu;
import jobmatchingsystem.service.AuthService;
import jobmatchingsystem.service.jdbc.*;

public class Main {
    public static void main(String[] args) {
        CandidateService candSvc = new JdbcCandidateService();
        JobService jobSvc = new JdbcJobService();
        AuthService auth  = new JdbcAuthService();

        new ConsoleMenu(candSvc, jobSvc, auth).run();
        
        
        
    }

}






//package jobmatchingsystem.main;
//
//import jobmatchingsystem.IO.*;
//import jobmatchingsystem.model.*;
//import java.util.*;
//import jobmatchingsystem.UI.*;
//
//public class Main {
//    public static void main(String[] args) {
//        FileManager fm = new FileManager();
//
//        // Use project-relative paths
//        String candPath = "candidates.csv";
//        String jobPath  = "Jobs.csv";
//
//        // Seed (optional — or load existing)
//        List<Candidate> candidates = List.of(
//            new Candidate(113, "Mark", 7, Arrays.asList("SQL", "Java", "Python")),
//            new Candidate(114, "Bob", 5, Arrays.asList("Excel", "SQL", "Python", "PowerBI"))
//        );
//        fm.writeCandidatesCsv(candidates, candPath);
//
//        List<Job> jobs = List.of(
//            new Job(10, "Backend Engineer", 2, Arrays.asList("Java","SpringBoot","SQL")),
//            new Job(11, "Data Engineer", 3, Arrays.asList("SQL","Excel","PowerBI","Python"))
//        );
//        fm.writeJobsCsv(jobs, jobPath);
//
//        // Load + match
//        var loadedCandidates = fm.readCandidatesCsv(candPath);
//        var loadedJobs = fm.readJobsCsv(jobPath);
//        Matcher.findMatches(loadedCandidates, loadedJobs).forEach(System.out::println);
    	//new ConsoleMenu().run();
    	
    	
 
