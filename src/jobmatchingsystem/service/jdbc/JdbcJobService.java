package jobmatchingsystem.service.jdbc;


import jobmatchingsystem.model.Job;
import jobmatchingsystem.service.*;
import jobmatchingsystem.persistence.*;

import java.sql.*;
import java.util.*;

public class JdbcJobService implements JobService {

	@Override
	public List<Job> findall() {
		String sql = "Select id,title,min_years,req_csv  From jobs Order BY id ";
		List<Job> out = new ArrayList<>();
		
		try(Connection conn = Db.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
						ResultSet rs = ps.executeQuery())
		{
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				int min_years = rs.getInt("min_years");
				String csv = rs.getString("req_csv");
				
				// create an arraylist to store the required skills in a list
				
				List<String> req = new ArrayList<>();
				
				// Check if csv is not null and not just spaces
				
				if(csv!=null && !csv.isBlank()) {
					// Split the list into array and split string skills by comma
					String[] parts = csv.split(",");
					
				
				// Loop through each part
				for(String p:parts) {
					String skill = p.trim();// extra space is trimmed
				
				if(!skill.isEmpty()) {
					req.add(skill);   // add skill to req list if the skill is not empty ( skill,(),skill)
						}
					}
				}
				out.add(new Job(id,title,min_years,req));
			}
		}
			catch(SQLException ex) {
			throw new RuntimeException("Failed to load jobs", ex);	
			}
		return out;
	}
		

	@Override
	public void add(Job j) {
		String sql = "INSERT INTO jobs (id, title, min_years, req_csv) VALUES (?, ?, ?, ?)";
		String csv = String.join(",", j.getRequiredSkills()); 
		
		try (Connection conn = Db.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, j.getId());
	            ps.setString(2, j.getTitle());
	            ps.setInt(3, j.getMinExperience());
	            ps.setString(4, csv);
	            ps.executeUpdate();

	        }
		catch(SQLException ex) {
			System.err.println("SQLState=" + ex.getSQLState() + " Code=" + ex.getErrorCode());
            throw new RuntimeException("Failed to insert job: " + ex.getMessage(), ex);
		}
		
	}

	
}
