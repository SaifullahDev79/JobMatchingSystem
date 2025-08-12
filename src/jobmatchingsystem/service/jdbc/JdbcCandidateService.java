package jobmatchingsystem.service.jdbc;

import jobmatchingsystem.service.*;
import jobmatchingsystem.model.*;
import jobmatchingsystem.persistence.*;

import java.sql.*;
import java.util.*;

public class JdbcCandidateService implements CandidateService{

	@Override
	public List<Candidate> findall() {
		String sql = "SELECT id, name, year_exp, skills_csv FROM candidates ORDER BY id";
		List<Candidate> out = new ArrayList<>();
		
		try
		( Connection conn = Db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int exp = rs.getInt("year_exp");
				String csv = rs.getString("skills_csv");
				
				  List<String> skills = (csv == null || csv.isBlank())
	                        ? new ArrayList<>()
	                        : Arrays.stream(csv.split(","))
	                                .map(String::trim)
	                                .filter(s -> !s.isEmpty())
	                                .toList();
				  
				  out.add(new Candidate(id,name,exp,skills));
						
					
				}
			}
		catch(SQLException ex) {
			throw new RuntimeException("Failed to load Candidates", ex);
		}
		return out;
		}
	

	@Override
	public void add(Candidate c) {
		String sql = "INSERT INTO candidates(id, name, year_exp, skills_csv) VALUES (?,?,?,?) ";
		String csv = String.join(",", c.getSkills());
		
		try(Connection conn = Db.getConnection();
			PreparedStatement ps =  conn.prepareStatement(sql);
								){
			ps.setInt(1, c.getId());
			ps.setString(2, c.getName());
			ps.setInt(3, c.getExperience());
			ps.setString(4, csv);
			
			ps.executeUpdate();
		}
		 catch (SQLException e) {
			    System.err.println("SQLState=" + e.getSQLState() + " Code=" + e.getErrorCode());
			    throw new RuntimeException("Failed to insert Candidate: " + e.getMessage(), e);
			}
		
		
	}
	
	

}
