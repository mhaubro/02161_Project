package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.IntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import sun.util.calendar.BaseCalendar.Date;
import exceptions.NoSuchUserException;
import exceptions.OperationNotAllowedException;

public class PlanningApp {

	private static final User admin = new User("Admin Administrator", "Admin");

	private TreeSet<User> users = new TreeSet<User>();
	private TreeSet<String> isSuper = new TreeSet<String>();
	private TreeSet<Project> projects = new TreeSet<Project>();
	private ArrayList<Activity> activities = new ArrayList<Activity>();

	private User ActiveUser;
	private int projectRunningNumber = 0;

	public Project getProject(String id) {
		for (Project p : projects) {
			if (p.getID().equals(id)) {
				return p;
			}
		}
		return null;
	}

	public User getUserByInitials(String initials) throws NoSuchUserException {
		for (User u : users) {
			if (u.getInitials().equalsIgnoreCase(initials)) {
				return u;
			}
		}
		throw new NoSuchUserException("could not find any user with the initials " + initials);

	}

	public void removeUserByInitials(String initials) {
		users.removeIf(u -> u.getInitials().equals(initials));
	}

	public void login(String initials) throws NoSuchUserException {
		if (initials.equalsIgnoreCase("Admin")) {
			ActiveUser = admin;
		} else {
			ActiveUser = users.parallelStream()
					.filter(u -> u.getInitials().equals(initials))
					.findFirst().orElseThrow(() -> new NoSuchUserException("Cant login as a user that does not exist"));
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
		if (projects.parallelStream().anyMatch(p -> p.getName().equalsIgnoreCase(name)))
			throw new OperationNotAllowedException("A project by the name [" + name + "] does already exist");

		if (!users.parallelStream().anyMatch(u -> u.getInitials().equalsIgnoreCase(userInitials)))
			throw new NoSuchUserException("Cant find user by the initials: " + userInitials);

		if (!isSuperByInitials(this.ActiveUser.getInitials()))
			throw new OperationNotAllowedException(this.ActiveUser.getInitials() + " is not a superuser");

		String projectNumber = getProjectNumber();
		User user = getUserByInitials(userInitials);
		projects.add(new Project(name, projectNumber, user, this));

		this.projectRunningNumber++;

		System.out.println("Added Project: name = " + name + " | runningNumber =  " + projectNumber + " | user = "
				+ userInitials);

	}

	public void addActivity(String name, String description, Timespan timespan, int BudgettetTime)
			throws OperationNotAllowedException {

		activities.add(new Activity(name, description, timespan, null, this, BudgettetTime));
	}

	public void logout() {
		ActiveUser = null;
	}

	public User getActiveUser() {
		return ActiveUser;
	}

	public void addUser(String name) {
		String initials = generateInitials(name);
		users.add(new User(name, initials));
		System.out.println("Added the user: name = " + name + " | initials = " + initials);
	}

	private String generateInitials(String name) {

		// generate first suggested initials
		String[] splittedName = name.split(" ");
		String tempInitials = splittedName[0].substring(0, 2) + splittedName[splittedName.length - 1].substring(0, 2);

		while (initialsExist(tempInitials)) {
			tempInitials = getRandomInitials();
		}
		return tempInitials;
	}

	private String getRandomInitials() {
		Random random = new Random();

		String result = "";
		for (int i = 0; i < 4; i++) {
			result += Character.toChars(65 + random.nextInt(26));
		}
		return result;
	}

	private boolean initialsExist(String initials) {
		return users.parallelStream().anyMatch(u -> u.getInitials().equalsIgnoreCase(initials));
	}

	private String getProjectNumber() {
		GregorianCalendar date = new GregorianCalendar();

		String yearString = Integer.toString(date.get(Calendar.YEAR));
		yearString = yearString.substring(yearString.length() - 2);

		String runningString = Integer.toString(projectRunningNumber);
		while (runningString.length() < 4) {
			runningString = "0" + runningString;
		}

		return yearString + runningString;
	}
	
	public ArrayList<User> getAvailableUsers(Timespan timespan){
		ArrayList<User> freePeople = new ArrayList<User>();
		for (User u : users){
			if (u.isAvailable(timespan)){
				freePeople.add(u);
			}
		}
		
		return freePeople;
	}
	
	public List<User> getUsers(){
		return users.parallelStream().sorted().collect(Collectors.toList());
	}

	public ArrayList<Activity> getAllActivities() {
		ArrayList<Activity> returnList = new ArrayList<Activity>();
		
		for (Project P : projects){
			returnList.addAll(P.getAllActivities());
		}
		
		for (Activity A : activities){
			returnList.add(A);
		}
		
		return returnList;
	}

}
