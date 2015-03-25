package main;

import java.util.Collections;
import java.util.Set;

public class PlanningApp {
	
	private Set<User> users;
	private Set<Project> projects;
	
	public Set<User> getUsers(){
		return Collections.unmodifiableSet(users);
	}
	
	public void addProject(Project p){
		projects.add(p);
	}
	
	public Project getProject(String id){
		for (Project p : projects){
			if (p.getID() == id) return p;
		}
		return null;
	}

}
