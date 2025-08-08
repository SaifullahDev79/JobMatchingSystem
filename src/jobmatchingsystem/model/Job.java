package jobmatchingsystem.model;

import java.util.List;
public class Job {
	private int id;
	private String title;
	private int min_experience;
	private List<String> req_skills;
	
	
	public Job(int id, String title , int min_experience , List<String> req_skills) {
		this.id = id ;
		this.title = title;
		this.min_experience = min_experience;
		this.req_skills= req_skills;
	}
	
	public int getid() {
		return id;
	}
	public String gettitle() {
		return title;
	}
	public int getmin_experience() {
		return min_experience;
	}
	public List<String>getreq_skills(){
		return req_skills;
	}
	
	
	public void setid(int id) {
		this.id = id;
	}
	public void settitle(String title) {
		this.title = title;
	}
	public void setmin_experience(int min_experience) {
		this.min_experience = min_experience;
	}
	public void setreq_skills (List<String> req_skills) {
		this.req_skills = req_skills;
	}
	
	public String toString () {
		return "Job id = " + id + "," + "Title= " + title + 
				"minimum experience = " + min_experience + "required skills = " + req_skills ;
	}
}
