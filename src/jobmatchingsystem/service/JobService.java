package jobmatchingsystem.service;

import jobmatchingsystem.model.*;

import java.util.*;

public interface JobService {

	List<Job> findall();
	
	void add(Job j);
	
}
