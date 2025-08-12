package jobmatchingsystem.service;

import jobmatchingsystem.model.*;
import java.util.*;

public interface CandidateService {

	List<Candidate> findall();
	 void add(Candidate c); // methods in an interface are by default public and abstract
	
	
}
