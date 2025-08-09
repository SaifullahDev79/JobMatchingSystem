package jobmatchingsystem.model;

import java.util.List;

public class Job {
    private int id;
    private String title;
    private int minExperience;
    private List<String> requiredSkills;

    public Job(int id, String title, int minExperience, List<String> requiredSkills) {
        this.id = id; this.title = title; this.minExperience = minExperience; this.requiredSkills = requiredSkills;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getMinExperience() { return minExperience; }
    public List<String> getRequiredSkills() { return requiredSkills; }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setMinExperience(int minExperience) { this.minExperience = minExperience; }
    public void setRequiredSkills(List<String> requiredSkills) { this.requiredSkills = requiredSkills; }

    @Override public String toString() {
        return "Job{id=" + id + ", title='" + title + "', minExperience=" + minExperience +
               ", requiredSkills=" + requiredSkills + "}";
    }
}