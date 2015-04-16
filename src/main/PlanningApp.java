package main;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PlanningApp {

	private static final User admin = new User("Admin Administrator", "Admin");

	private TreeMap<String, User> users = new TreeMap<String, User>();
	private TreeSet<String> isSuper = new TreeSet<String>();
	private Set<Project> projects = new TreeSet<Project>();

	private User currentUser;

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

	public void addUser(String name) throws Exception {
		String[] nameArray = name.split(" ");
		String initials = nameArray[0].substring(0, 2) + nameArray[1].substring(0, 2);

		if (users.containsKey(initials)) {
			throw new Exception("a user with the same initials is already in the system.");
		}
		users.put(initials, new User(name, initials));
		if (isSuper.size() == 0){
			isSuper.add(users.firstKey());
		}
	}

	public User getUserByInitials(String initials) {
		return users.getOrDefault(initials, null);
	}

	public void removeUserByInitials(String initials) {
		users.remove(initials);
		isSuper.remove(initials);
		if (isSuper.size() == 0 && users.size()!=0){
			isSuper.add(users.firstKey());
		}
	}

	public void login(String initials) {
		if (initials.equals("Admin")) {
			currentUser = admin;
		} else {
			currentUser = users.getOrDefault(initials, null);
		}
	}
	
	public boolean isSuperByInitials(String initials){
		if (initials.equals("Admin")){
			return true;
		}else{
			return isSuper.contains(initials);
		}
	}
	
	public void makeSuper(String initials){
		if (users.containsKey(initials)){
			isSuper.add(initials);
		}
	}
	
	public void revokeSuper(String initials){
		isSuper.remove(initials);
	}

	public int getNumberOfEmployes() {
		return users.size();
	}

}
