package jobmatchingsystem.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;
public class FIleManager {
	
	
	public void writecandtocsv(List<Candidate> candidates , String filepath) {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))) {
			bw.write("CandidateId,Name,Experience,Skills ");
			bw.newLine();
			
			for(Candidate c : candidates) {
				String line = c.getid()+ "," + c.getname() + ","+ c.getexperience() +","+ String.join(";", c.getskills());
				bw.write(line);
				bw.newLine();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public List<Candidate> readCandfromcsv(String filepath){
		List<Candidate> candidates = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))){
			 br.readLine();
			 String line;
			while((line= br.readLine()) != null) {
				String [] data = line.split(",",-1);
				int id= Integer.parseInt(data[0]);
				String name = data[1];
				int experience = Integer.parseInt(data[2]);
				List<String> Skills = Arrays.asList(data[3].split(";"));
				candidates.add(new Candidate(id,name,experience,Skills));
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return candidates;
	}
	
	public void writejobtocsv(List<Job> jobs,String filepath) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))) {
			bw.write("Id,Title,min_experience,req_skills");
			bw.newLine();
			for(Job j: jobs) {
			String line = j.getid()+","+j.gettitle()+","+j.getmin_experience()+","+ String.join(";", j.getreq_skills());
			bw.write(line);
			bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} 
	
	public List<Job> readjobfromcsv(String filepath){
		List<Job> jobs = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(filepath))) {
			br.readLine();
			String line;
			while ((line=br.readLine())!= null) {
				String [] data = line.split(",",-1);
				int id = Integer.parseInt(data[0]);
				String title = data[1];
				int min_experience = Integer.parseInt(data[2]);
				List<String> requiredSkills = Arrays.asList(data[3].split(";"));
				
				jobs.add(new Job(id, title , min_experience , requiredSkills));
			}
			
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return jobs;
	}
}
