package jobmatchingsystem.model;

import java.util.List;

public class Candidate {
    private int id;
    private String name;
    private int experience;
    private List<String> skills;

    public Candidate(int id, String name, int experience, List<String> skills) {
        this.id = id; this.name = name; this.experience = experience; this.skills = skills;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getExperience() { return experience; }
    public List<String> getSkills() { return skills; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setExperience(int experience) { this.experience = experience; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    @Override public String toString() {
        return "Candidate{id=" + id + ", name='" + name + "', experience=" + experience +
               ", skills=" + skills + "}";
    }
}   