package jobmatchingsystem.model;

import java.util.*;

public class Matcher {
    public static List<Match> findMatches(List<Candidate> candidates, List<Job> jobs) {
        List<Match> matches = new ArrayList<>();
        for (Candidate c : candidates) {
            for (Job j : jobs) {
                int skillsMatched = 0;
                for (String req : j.getRequiredSkills()) {
                    if (c.getSkills().stream().anyMatch(s -> s.equalsIgnoreCase(req.trim()))) {
                        skillsMatched++;
                    }
                }
                boolean expOk = c.getExperience() >= j.getMinExperience();
                if (skillsMatched > 0 || expOk) {
                    matches.add(new Match(c, j, skillsMatched, expOk));
                }
            }
        }
        return matches;
    }
}