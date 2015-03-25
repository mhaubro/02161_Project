package main;

import java.util.Set;
import java.util.TreeSet;

public class PlanningApp {

	private Set<User> users = new TreeSet<User>();
	private Set<Project> projects = new TreeSet<Project>();

	public void addProject(Project p) {
		if (p != null)
			projects.add(p);
	}

	public Project getProject(String id) {
		for (Project p : projects) {
			if (p.getID().equals(id))
				return p;
		}
		return null;
	}

}
