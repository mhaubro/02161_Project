package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import exceptions.NoSuchUserException;
import exceptions.OperationNotAllowedException;

public class PlanningApp {

	private static final User admin = new User("Admin Administrator", "Admin");

	private TreeSet<User> users = new TreeSet<User>();
	private TreeSet<String> isSuper = new TreeSet<String>();
	private TreeSet<Project> projects = new TreeSet<Project>();

	private User ActiveUser;

	public void addProject(Project p) {
		
	}

	public Project getProject(String id) {
		
	}

	public void addUser(String name) throws Exception {
		
	}

	public User getUserByInitials(String initials) {
		
	}

	public void removeUserByInitials(String initials) {
		
	}

	public void login(String initials) {
		if (initials.equals("Admin")) {
			ActiveUser = admin;
		} else {
			ActiveUser = users.parallelStream().filter(u -> u.getInitials().equals(initials)).
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
		if (users.parallelStream().anyMatch(u -> u.getInitials().equals(initials))){
			isSuper.add(initials);
		}
	}
	
	public void revokeSuper(String initials){
		isSuper.remove(initials);
	}

	public int getNumberOfEmployes() {
		return users.size();
	}

	public Project getProjectByName(String name) {
		for (Project p : projects){
			if (p.getName().equalsIgnoreCase(name)){
				return p;
			}
		}
		return null;
	}

	public void addProject(String name, String userInitials) throws OperationNotAllowedException, NoSuchUserException{
		
		
	}

	public void addActivity(String name, String desciption, Timespan timespan, int BudgettetTime) {
		// TODO Auto-generated method stub
		
	}

	public void logout() {
		ActiveUser = null;
	}

	public User getActiveUser() {
		return ActiveUser;
	}

}
