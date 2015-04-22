package main;

import java.util.ArrayList;
import java.util.TreeSet;

import exceptions.NoSuchUserException;
import exceptions.OperationNotAllowedException;

public class PlanningApp {

	private static final User admin = new User("Admin Administrator", "Admin");

	private TreeSet<User> users = new TreeSet<User>();
	private TreeSet<String> isSuper = new TreeSet<String>();
	private TreeSet<Project> projects = new TreeSet<Project>();
	private ArrayList<Activity> activities = new ArrayList<Activity>();

	private User ActiveUser;

	public Project getProject(String id) {
		for (Project p : projects) {
			if (p.getID().equals(id)) {
				return p;
			}
		}
		return null;
	}

	public User getUserByInitials(String initials) {
		for (User u : users) {
			if (u.getInitials().equals(initials)) {
				return u;
			}
		}
		return null;

	}

	public void removeUserByInitials(String initials) {
		users.removeIf(u -> u.getInitials().equals(initials));
	}

	public void login(String initials) throws NoSuchUserException {
		if (initials.equals("Admin")) {
			ActiveUser = admin;
		} else {
			ActiveUser = users.parallelStream()
					.filter(u -> u.getInitials().equals(initials))
					.findFirst().orElseThrow(NoSuchUserException::new);
		}
	}

	public boolean isSuperByInitials(String initials) {
		if (initials.equals("Admin")) {
			return true;
		} else {
			return isSuper.contains(initials);
		}
	}

	public void makeSuper(String initials) {
		if (users.parallelStream().anyMatch(u -> u.getInitials().equals(initials))) {
			isSuper.add(initials);
		}
	}

	public void revokeSuper(String initials) {
		isSuper.remove(initials);
	}

	public int getNumberOfEmployes() {
		return users.size();
	}

	public Project getProjectByName(String name) {
		for (Project p : projects) {
			if (p.getName().equalsIgnoreCase(name)) {
				return p;
			}
		}
		return null;
	}

	public void addProject(String name, String userInitials) throws OperationNotAllowedException, NoSuchUserException {

	}

	public void addActivity(String name, String description, Timespan timespan, int BudgettetTime)
			throws OperationNotAllowedException {

		activities.add(new Activity(name, description, timespan, BudgettetTime));
	}

	public void logout() {
		ActiveUser = null;
	}

	public User getActiveUser() {
		return ActiveUser;
	}

}
