package jobmatchingsystem.IO;

import jobmatchingsystem.model.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileManager {
    public void writeCandidatesCsv(List<Candidate> candidates, String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("CandidateId,Name,Experience,Skills"); bw.newLine();
            for (Candidate c : candidates) {
                String skills = String.join(";", c.getSkills());
                bw.write(c.getId() + "," + c.getName() + "," + c.getExperience() + "," + skills);
                bw.newLine();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public List<Candidate> readCandidatesCsv(String path) {
        List<Candidate> out = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine(); // header
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",", -1);
                int id = Integer.parseInt(d[0]);
                String name = d[1];
                int exp = Integer.parseInt(d[2]);
                List<String> skills = Arrays.stream(d[3].split(";"))
                        .map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toList());
                out.add(new Candidate(id, name, exp, skills));
            }
        } catch (IOException e) { e.printStackTrace(); }
        return out;
    }

    public void writeJobsCsv(List<Job> jobs, String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("Id,Title,MinExperience,RequiredSkills"); bw.newLine();
            for (Job j : jobs) {
                bw.write(j.getId() + "," + j.getTitle() + "," + j.getMinExperience() + "," +
                         String.join(";", j.getRequiredSkills()));
                bw.newLine();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public List<Job> readJobsCsv(String path) {
        List<Job> out = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",", -1);
                int id = Integer.parseInt(d[0]);
                String title = d[1];
                int minExp = Integer.parseInt(d[2]);
                List<String> req = Arrays.stream(d[3].split(";"))
                        .map(String::trim).filter(s -> !s.isEmpty()).toList();
                out.add(new Job(id, title, minExp, req));
            }
        } catch (IOException e) { e.printStackTrace(); }
        return out;
    }
}
