package jobmatchingsystem.model;

public class Match {
	private Candidate candidate;
	private Job job;
	private int skillsMatched;
	private boolean experienceMatched;
	
	

	public Match(Candidate candidate, Job job, int skillsMatched, boolean experienceMatched) {
		this.candidate = candidate;
		this.job = job;
		this.skillsMatched = skillsMatched;
		this.experienceMatched = experienceMatched;
		
	}
	
	
		public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public int getSkillsMatched() {
		return skillsMatched;
	}

	public void setSkillsMatched(int skillsMatched) {
		this.skillsMatched = skillsMatched;
	}

	public boolean isExperienceMatched() {
		return experienceMatched;
	}

	public void setExperienceMatched(boolean experienceMatched) {
		this.experienceMatched = experienceMatched;
	}


	
	@Override
	public String toString() {
	    return candidate.getname() + " matches job " + job.gettitle()
	           + " | Skills matched: " + skillsMatched
	           + " | Experience matched: " + experienceMatched;
	}

		
}
