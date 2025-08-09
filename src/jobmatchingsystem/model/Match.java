package jobmatchingsystem.model;

public class Match {
    private Candidate candidate;
    private Job job;
    private int skillsMatched;
    private boolean experienceMatched;

    public Match(Candidate candidate, Job job, int skillsMatched, boolean experienceMatched) {
        this.candidate = candidate; this.job = job;
        this.skillsMatched = skillsMatched; this.experienceMatched = experienceMatched;
    }

    public Candidate getCandidate() { return candidate; }
    public Job getJob() { return job; }
    public int getSkillsMatched() { return skillsMatched; }
    public boolean isExperienceMatched() { return experienceMatched; }

    @Override public String toString() {
        return candidate.getName() + " matches job " + job.getTitle() +
               " | Skills matched: " + skillsMatched +
               " | Experience matched: " + experienceMatched;
    }
}