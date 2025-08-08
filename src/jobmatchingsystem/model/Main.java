package jobmatchingsystem.model;

import java.io.IOException;
import java.util.*;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		FIleManager fm = new FIleManager();
		
		List<Candidate> candidates = new ArrayList<>();
		
		candidates.add(new Candidate(113, "Mark", 7 , Arrays.asList("SQL", "Java", "Python")));
		candidates.add(new Candidate(114, "Bob", 5, Arrays.asList("Excel", "SQL", "Python","PowerBI")));
		
		fm.writecandtocsv(candidates, "C:\\Users\\dashi\\Desktop\\candidates.csv");
		
		List<Candidate> loadedCandidates = fm.readCandfromcsv("C:\\Users\\dashi\\Desktop\\candidates.csv");
		
		
		
		List<Job> jobs =new ArrayList<>();
		jobs.add(new Job(10,"Backend Engineer" , 2 , Arrays.asList("Java","SpringBoot","SQL")));
		jobs.add(new Job(11, "Data Engineer", 3, Arrays.asList("SQL", "Excel", "PowerBI","Python")));
		
		
		fm.writejobtocsv(jobs, "C:\\Users\\dashi\\Desktop\\Jobs.csv");
		
		List<Job> loadedJobs = fm.readjobfromcsv("C:\\Users\\dashi\\Desktop\\Jobs.csv");
		
		for (Candidate c : loadedCandidates) {
	    System.out.println(c);
	}
	for (Job j : loadedJobs) {
	    System.out.println(j);
	}


		List<Match> matched = Matcher.findmatches(loadedCandidates, loadedJobs);
		
		for(Match m: matched) {
			System.out.println(m);
		}
	}
}
