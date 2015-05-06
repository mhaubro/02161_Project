package main;

import java.util.ArrayList;
import java.util.List;

import exceptions.NoSuchUserException;
import exceptions.OperationNotAllowedException;

public class Project implements Comparable<Project> {

	private ArrayList<Activity> activities;
	private String name;
	private String id;
	private PlanningApp planApp;
	private User projectLeader;

	public Project(String name, String id, User projectLeader, PlanningApp planApp) {
		this.planApp = planApp;
		this.name = name;
		this.id = id;
		this.projectLeader = projectLeader;
		this.activities = new ArrayList<Activity>();
	}

	public String getID() {
		return new String(id);
	}

	public String getName() {
		return name;
	}

	public String getFollowup() {
		// TODO create the real followup
		return "this is a followUp test message";
	}

	@Override
	public int compareTo(Project p) {
		return this.id.compareTo(p.getID());
	}

	public void addActivity(String name, String description, Timespan timespan, int budgettetTime)
			throws OperationNotAllowedException {
		//
		if (getActiveUser().equals(this.projectLeader) || isActiveUserSuperuser()) {
			Activity newAct = new Activity(name, description, timespan, this, planApp, budgettetTime);
			activities.add(newAct);
		} else {
			throw new OperationNotAllowedException("Kun projektlederen kan oprette aktiviteter");
		}

	}

	private boolean isActiveUserSuperuser() throws OperationNotAllowedException {
		return planApp.isSuperByInitials(getActiveUser().getInitials());
	}

	public Activity getActivityByName(String name) {
		//
		for (Activity activity : activities) {
			if (activity.getName().equalsIgnoreCase(name)) {
				return activity;
			}
		}
		return null;
	}

	public User getProjectLeader() {
		return this.projectLeader;
	}

	public int getNumberOfActivities() {// Metode brugt til JUnit-tests
		return activities.size();
	}

	public boolean containsActivity(String name) {
		for (Activity activity : activities) {
			if (activity.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	public void switchProjectLeader(String initials) throws OperationNotAllowedException, NoSuchUserException {
		User activeUser = getActiveUser();
		if (!this.projectLeader.equals(activeUser) && !isActiveUserSuperuser())
			throw new OperationNotAllowedException("the active user is not projectleader [activeUser = "
					+ planApp.getActiveUser().getInitials() + " | ProjectLeader = " + this.projectLeader.getInitials());

		User newProjectLeader = planApp.getUserByInitials(initials);

		this.projectLeader = newProjectLeader;
	}

	private User getActiveUser() throws OperationNotAllowedException {
		if (planApp == null)
			throw new OperationNotAllowedException("cant return active user, No planningApp linked to this activity");
		return planApp.getActiveUser();
	}

	public ArrayList<Activity> getAllActivities() {
		ArrayList<Activity> returnList = new ArrayList<Activity>();
		
		for (Activity A: activities){
			returnList.add(A);
		}
		
		return returnList;
	}

}
