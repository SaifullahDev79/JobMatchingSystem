package jobmatchingsystem.UI; // lowercase package names by convention

import jobmatchingsystem.model.*;
import jobmatchingsystem.service.CandidateService;
import jobmatchingsystem.service.JobService;

import java.util.*;

public class ConsoleMenu {
    private final Scanner myScanner = new Scanner(System.in);

    // Services for DB operations
    private final CandidateService candSvc;
    private final JobService jobSvc;

    // In-memory lists for display/matching
    private List<Candidate> candidates = new ArrayList<>();
    private List<Job> jobs = new ArrayList<>();

    public ConsoleMenu(CandidateService candSvc, JobService jobSvc) {
        this.candSvc = candSvc;
        this.jobSvc = jobSvc;
    }

    public void run() {
        refreshFromDb();

        while (true) {
            printHeader();
            switch (readline("Choose an option: ")) {
                case "1" -> listCandidates();
                case "2" -> listJobs();
                case "3" -> addCandidate();
                case "4" -> addJob();
                case "5" -> showMatches();
                case "6" -> refreshFromDb(); // reload latest data from DB
                case "7" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
            waitForEnter();
        }
    }

    private void refreshFromDb() {
        candidates = candSvc.findall();
        jobs = jobSvc.findall();
        System.out.println("Loaded data from database.");
    }

    private void waitForEnter() {
        System.out.println("\nPress Enter to continue...");
        myScanner.nextLine();
    }

    private void showMatches() {
        System.out.println("\n*** Matches ***");
        if (candidates.isEmpty() || jobs.isEmpty()) {
            System.out.println("Need at least one candidate and one job.");
            return;
        }
        List<Match> matches = Matcher.findMatches(candidates, jobs);
        if (matches.isEmpty()) {
            System.out.println("No matches found.");
            return;
        }
        matches.forEach(m -> System.out.println(m.toString()));
    }

    private void addJob() {
        System.out.println("\n*** Add Job ***");
        int id = readInt("ID (integer): ");
        String title = readNonEmpty("Title: ");
        int minExp = readInt("Minimum experience (years): ");
        String reqCsv = readNonEmpty("Required Skills (comma-separated): ");
        List<String> reqSkills = parseSkills(reqCsv);

        jobSvc.add(new Job(id, title, minExp, reqSkills));
        refreshFromDb();
        System.out.println("Job added to database.");
    }

    private void addCandidate() {
        System.out.println("\n*** Add Candidate ***");
        int id = readInt("ID (integer only): ");
        String name = readNonEmpty("Name: ");
        int exp = readInt("Years of experience: ");
        String skillsCsv = readNonEmpty("Skills (comma-separated): ");

        List<String> skills = parseSkills(skillsCsv);
        candSvc.add(new Candidate(id, name, exp, skills));
        refreshFromDb();
        System.out.println("Candidate added to database.");
    }

    private List<String> parseSkills(String csv) {
        String[] parts = csv.split(",");
        List<String> out = new ArrayList<>();
        for (String p : parts) {
            String s = p.trim();
            if (!s.isEmpty()) out.add(s);
        }
        return out;
    }

    private String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = myScanner.nextLine().trim();
            if (!s.isEmpty() && s.length() > 2) return s;
            System.out.println("Value must be at least 3 characters.");
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = myScanner.nextLine().trim();
            try {
                int value = Integer.parseInt(s);
                if (value < 0) {
                    System.out.println("ID cannot be negative. Please try again.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private void listJobs() {
        System.out.println("\n*** Jobs ***");
        if (jobs.isEmpty()) {
            System.out.println("none");
            return;
        }
        jobs.forEach(j -> System.out.println(
                j.getId() + " | " + j.getTitle() + " | " +
                j.getMinExperience() + " yrs | " + j.getRequiredSkills()));
    }

    private void listCandidates() {
        System.out.println("\n*** Candidates ***");
        if (candidates.isEmpty()) {
            System.out.println("none");
            return;
        }
        candidates.forEach(c -> System.out.println(
                c.getId() + " | " + c.getName() + " | " +
                c.getExperience() + " yrs | " + c.getSkills()));
    }

    private String readline(String prompt) {
        System.out.print(prompt);
        return myScanner.nextLine().trim();
    }

    private void printHeader() {
        System.out.println("\n*** Job Matching System ***");
        System.out.println("1) List Candidates");
        System.out.println("2) List Jobs");
        System.out.println("3) Add Candidate");
        System.out.println("4) Add Job");
        System.out.println("5) Run Matching");
        System.out.println("6) Refresh from DB");
        System.out.println("7) Exit");
    }
}
