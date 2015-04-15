package main;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PlanningApp {
	
	private static final User admin = new User("Admin Administrator", "Admin");

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

	public Set<Project> searchProject(String searchKey) {
		return projects.stream()
				.filter(p -> p.contains(searchKey))
				.collect(Collectors.toSet());

	}

	public Set<User> getUsers() {
		// TODO change to avoid reference.
		return users;
	}

	public void addUser(String name) {
		// TODO Auto-generated method stub
		users.add(new User(name, name));
	}

	public User getUserByInitials(String string) {
		return null;//TODO get user by initials
	}

	public void removeUserByInitials(String string) {
		// TODO Auto-generated method stub
		
	}

	public void login(String string) {
		// TODO Auto-generated method stub
		
	}

	public int getNumberOfEmployes() {
		// TODO Auto-generated method stub
		return 0;
	}

}
