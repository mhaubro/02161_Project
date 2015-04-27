package main;

import java.util.ArrayList;

import exceptions.NoSuchUserException;
import exceptions.OperationNotAllowedException;

public class Project implements Comparable<Project> {
	
	private ArrayList<Activity> activities;
	private String name;
	private String id;
	private PlanningApp planApp;
	private User projectLeader;
	
	public Project(String name, String id, User projectLeader, PlanningApp planApp){
		this.planApp = planApp;
		this.name = name;
		this.id = id;
		this.projectLeader = projectLeader;
		this.activities = new ArrayList<Activity>();
	}

	public String getID() {
		return new String(id);
	}
	
	public String getName(){
		return name;
	}

	public String getFollowup() {
		// TODO create the real followup
		return "this is a followUp test message";
	}
	
	public boolean contains(String key){
		if (name.contains(key) || id.contains(key)){
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Project p) {
		return this.id.compareTo(p.getID());
	}

	public void addActivity(String name, String description, Timespan timespan, int budgettetTime) throws OperationNotAllowedException {
		//  
		if (planApp.getActiveUser().equals(this.projectLeader)|| planApp.isSuperByInitials(planApp.getActiveUser().getInitials())){
			Activity newAct = new Activity(name, description, timespan, this, budgettetTime);
			activities.add(newAct);
		} else {
			throw new OperationNotAllowedException("Kun projektlederen kan oprette aktiviteter");
		}
		
	}

	public Activity getActivityByName(String name) {
		// 
		for (Activity activity : activities){
			if (activity.getName().equalsIgnoreCase(name)){
				return activity;
			}
		}
		return null;
	}

	public User getProjectLeader() {
		return this.projectLeader;
	}

	public int getNumberOfActivities(){//Metode brugt til JUnit-tests
		return activities.size();
	}
	
	public boolean containsActivity(String name){
		for (Activity activity : activities){
			if (activity.getName().equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}

	public void switchProjectLeader(String initials) throws OperationNotAllowedException, NoSuchUserException {
		User activeUser = planApp.getActiveUser();
		if (!this.projectLeader.equals(activeUser) && !planApp.isSuperByInitials(activeUser.getInitials()))
			throw new OperationNotAllowedException("the active user is not projectleader [activeUser = " + planApp.getActiveUser().getInitials() + " | ProjectLeader = " + this.projectLeader.getInitials());
		
		User newProjectLeader = planApp.getUserByInitials(initials);
		
		this.projectLeader = newProjectLeader;
	}

}
