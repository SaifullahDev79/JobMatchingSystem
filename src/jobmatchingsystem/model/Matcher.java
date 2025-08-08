package jobmatchingsystem.model;

import java.util.*;
public class Matcher {
	public static List<Match> findmatches(List<Candidate> candidates , List<Job> jobs){
		List<Match> match = new ArrayList<>();
		
		for(Candidate c: candidates ) {
			for(Job j : jobs) {
				int skillsMatched =0;
				
				for(String skills : j.getreq_skills())   {
					if (c.getskills().contains(skills)) {
						skillsMatched ++;
					}
				}
			
		
		boolean experienceMatched = c.getexperience() >= j.getmin_experience();
		
		if (skillsMatched >0 || experienceMatched) {
			match.add(new Match(c, j , skillsMatched, experienceMatched));
		}
		}
		}
		return match;
	}

}
