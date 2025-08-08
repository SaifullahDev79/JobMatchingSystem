package jobmatchingsystem.model;

import java.util.List;

public class Candidate {

	private int id;
	private String name ;
	private int experience;
	private List<String> skills;
	
	
	public Candidate(int id , String name, int experience, List<String> skills) {
		this.id = id ;
		this.name = name;
		this.experience = experience;
		this.skills = skills;
	}
	
	
	// Encapsulation with getters to access modify private while allowing the indirect access.
	public int getid() {
		return id;
	}
	public String getname() {
		return name;
	}
	public int getexperience() {
		return experience;
	}
	public List<String> getskills(){
		return skills;
	}
	// Encapsulation with setters to set the primitives without accessing them directly.
	
	public void setid(int id) {
		this.id = id;
	}
	public void setname (String name) {
		this.name = name;
	}
	public void setexperience(int experience) {
		this.experience = experience;
	}
	public void setSkills(List<String>skills) {
		this.skills = skills;
	}
	
	public String toString () {
		return "Candidate id = " + id + "," + "name= " + name + 
				"professional experience = " + experience + "professional skills = " + skills ;
	}
}