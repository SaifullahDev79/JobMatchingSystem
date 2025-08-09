package jobmatchingsystem.UI;

import jobmatchingsystem.IO.*;
//import jobmatchingsystem.main.*;
import jobmatchingsystem.model.*;

import java.util.*;



public class ConsoleMenu {
	private final Scanner myScanner = new Scanner(System.in);
	private final FileManager fm = new FileManager();
	
	private final String candPath = "C:\\Users\\dashi\\Desktop\\candidates.csv";
	private final String jobPath = "C:\\Users\\dashi\\Desktop\\Jobs.csv";
	
	
	private List<Candidate> candidates = new ArrayList<>();
	private List<Job> jobs = new ArrayList<>();
	
	public void run() {
		candidates = fm.readCandidatesCsv(candPath);
		jobs = fm.readJobsCsv(jobPath);
		
		while (true) {
			printHeader();
			switch (readline("Choose an option: ")) {
			case "1" -> listCandidates();
			case "2" -> listJobs();
			case "3" -> addCandidate();
			case "4" -> addJob();
			case "5" -> showMatches();
			case "6" -> save();
			case "7" -> { save(); System.out.println("Goodbye!"); return;}
			default -> System.out.println("Invalid choice. Try again.");
			
			}
			waitforEnter();
		}
	}

	private void waitforEnter() {
		System.out.println();
		System.out.println("Press Enter to continue ");
		myScanner.nextLine();
	}

	private void save() {
		fm.writeCandidatesCsv(candidates, candPath);
		fm.writeJobsCsv(jobs, jobPath);
		System.out.println("Saved to CSV");
	}

	private void showMatches() {
		System.out.println();
		System.out.println("*** Matches ***");
		if(candidates.isEmpty() || jobs.isEmpty()) {
			System.out.println("Need atleast one candidate and one job");
			return;
		}
		List<Match> matches = Matcher.findMatches(candidates, jobs);
		if(matches.isEmpty()) {
			System.out.println("No matches found. ");
			return;
		}
		matches.forEach(m -> System.out.println(m.toString())); // converting each object m from the list 
		// matches toString and displaying it.
		
	}

	private void addJob() {
		System.out.println();
		System.out.println("*** Add Job *** ");
		int id = readInt("ID (integer):");
		String title = readNonEmpty("Title: ");
		int minExp = readInt("Minimum experience (years) :");
		String reqCsv = readNonEmpty("Required Skills (comma - seperated) :");
		List <String> reqSkills = parseSkills (reqCsv);
		jobs.add(new Job(id, title, minExp,  reqSkills));
		
	}

	private void addCandidate() {
		System.out.println();
		System.out.println("*** Add Candidate ***");
		int id = readInt("ID (integer only) : " );
		String name = readNonEmpty("Name : " );
		int exp = readInt ("Years of experience( integer only) : ");
		String skillsCsv = readNonEmpty("Skills (comma-seperated) : " );
		
		List<String> skills = parseSkills(skillsCsv); 
	candidates.add(new Candidate (id, name, exp, skills));
	System.out.println("Candidate added");
	}

	private List<String> parseSkills(String csv) { // the response to skills is passed in this method
		 String [] parts = csv.split (","); // stores every skills that is comma seperated into the array 
		List<String> out = new ArrayList<>();// a new arraylist is formed to store the array parts
		for (String p : parts) {// to go through each element in parts
			String s = p.trim();// eliminates extra spaces and stores the results inside s
			if(!s.isEmpty())out.add(s);// s is now ready and added to the list 's'.
		}
		return out;// out is displayed when parseSkills (response of skills).
	}

	private String readNonEmpty(String prompt) {
		while(true) {
			System.out.println(prompt);
			String s = myScanner.nextLine().trim();
			if(!s.isEmpty() && s.length()>2) return s;
			System.out.println(" Value must be atleast 3 characters. ");
		}
	}

	private int readInt(String prompt ) {
		while (true) {
			
				System.out.println(prompt);
				String s = myScanner.nextLine().trim();
			
		try {	// edge case 1 : Negative number
		int value = Integer.parseInt(s);
		if(value < 0) {
			System.out.println("ID cannot be negative, please enter the correct id.");
			continue; // restarts the loop;
		}
		
		
		
		return value;
		} catch(NumberFormatException e){ // edge case 2: not valid integer
			System.out.println("Please enter a valid integer");
			
		}
		}
	}

	private void listJobs() {
		System.out.println();
		System.out.println("*** Jobs ***");
		if (jobs.isEmpty()) {
			System.out.println("none");
			
		}
		jobs.forEach(
				j-> System.out.println(j.getId()+ " | "+ j.getTitle() + " | " + j.getMinExperience() + " yrs | " + j.getRequiredSkills()));
	}

	private  void listCandidates() {
		System.out.println();
		System.out.println("*** Candidates ***");
		if(candidates.isEmpty()) {
			System.out.println("none");
			return ; }
		candidates.forEach
		(c -> System.out.println(c.getId()+ " | " + c.getName() + " | " + c.getExperience() + " yrs | " + c.getSkills()));
		}
	

	private String readline(String prompt) {
		System.out.print(prompt);
		return myScanner.nextLine().trim();
	}

	private void printHeader() {
		System.out.println("*** Job Matching System *** ");
		System.out.println(" 1) List Candidates");
		System.out.println(" 2) List Jobs");
		System.out.println(" 3) Add Candidate");
		System.out.println(" 4) Add Job");
		System.out.println(" 5) Run Matching ");
		System.out.println(" 6) Save");
		System.out.println(" 7) Save & Exit");
		
	}
}
