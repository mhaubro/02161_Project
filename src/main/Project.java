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
		String str1 = "FollowUp on project: " + this.name + "\n";
		String str2 = "Project Leader: " + this.projectLeader.getName() + "\n";
		String str3 = "\n";
		String str4 = "Activities and their states:\n";
		String str5 = "Activity\tBudgettet Time\tPlanned Time\tRegistered Time\tRegistered/Planned\tBudgettet - Registered\n";
		//Something something math something får dannet de linjer, hvor der fortælles om aktiviteter.
		String actiString = "";
		double totalRegisteredTime = 0;
		double totalBudgettetTime = 0;
		double timeLeftOnProjectModifier = 0;//Is either 0 or higher
		for (Activity activity : activities){
			double plannedTime = activity.getPlannedTime();
			double registeredTime = activity.getRegistretTime();
			int budgettetTime = activity.getBudgettetTime();
			totalRegisteredTime += registeredTime;
			totalBudgettetTime += budgettetTime;
			if (registeredTime > budgettetTime){
				timeLeftOnProjectModifier += registeredTime - budgettetTime;
			}
			actiString = actiString + activity.getName() + "\n\t\t" +(int)budgettetTime + "\t\t"+ plannedTime + "\t\t" + registeredTime
					+ "\t\t" + (((int)(registeredTime/plannedTime*100+0.5))/100.0) + "\t\t" + (budgettetTime - registeredTime) + "\n";
		}
		
		String str6 = "\n";
		String str7 = "Amount of work done relative to budgettet time: " + (int)(totalRegisteredTime/totalBudgettetTime*100) + " %\n";
		String str8 = "Expected workhours left on project: " + (totalBudgettetTime - totalRegisteredTime + timeLeftOnProjectModifier) + "\n";
		
		return str1+str2+str3+str4+str5+actiString+str6+str7+str8;
	}
	
	private List<User> getAllUsers(){//Returnerer alle users i projektets plans eller activities samt projektlederen.
		List<User> usersInProject = new ArrayList<User>();
		for (Activity activity : activities){
			for (Report report : activity.getReports()){
				if (!usersInProject.contains(report.getUser())){
					usersInProject.add(report.getUser());
				}
			}
			for(PlannedWork plan : activity.getPlans()){
				if (!usersInProject.contains(plan.getUser())){
					usersInProject.add(plan.getUser());
				}
			}
		}
		return usersInProject;
	}
	
	public String getBigFollowup(){
		String small = getFollowup();
		String str1 = "\n";
		String str2 = "Employees on the project\n";
		String str3 = "User\tPlanned time\tRegistered time\n";
		//Something something math something
		List<User> users = getAllUsers();
		double[][] userData = new double[users.size()][2];
		//Dermed er alle users fundet, og datasæt er initialiseret
		//Der itereres, og alle data gemmes. Næppe en effektiv algoritme, but it does the job.
		for (Activity activity : activities){
			for (PlannedWork plan : activity.getPlans()){
				for (int i = 0; i < users.size(); i++){
					if(plan.getUser().equals(users.get(i))){
						userData[i][0] += plan.getTime();
						break;
					}
				}
			}
			for (Report report : activity.getReports()){
				for (int i = 0; i < users.size(); i++){
					if(report.getUser().equals(users.get(i))){
						userData[i][1] += report.getReportedTime();
						break;
					} 
				}
			}
		}
		//Data er gemt. Der skal printes strings.
		String userString = "";
		for (int i = 0; i < users.size(); i++){
			userString += users.get(i).getInitials() + "\t" + userData[i][0] + "\t\t" + userData[i][1] + "\n";
		}
		
		return small + str1 + str2 + str3 + userString;
	}

//	public boolean contains(String key) {
//		if (name.contains(key) || id.contains(key)) {
//			return true;
//		}
//		return false;
//	}

	@Override
	public int compareTo(Project p) {
		return this.id.compareTo(p.getID());
	}

	public void addActivity(String name, String description, Timespan timespan, int budgettetTime)
			throws OperationNotAllowedException {
		//Hvis User er projektleder og/eller superuser samt at navnet på aktiviteen er unikt
		if (!(getActiveUser().equals(this.projectLeader) || isActiveUserSuperuser())){
			throw new OperationNotAllowedException("Only the project leader can create activities.");
		} else if (getActivityByName(name) != null) {
			throw new OperationNotAllowedException("There is alredy an activity with this name.");
		} else if (timespan == null) {
			throw new OperationNotAllowedException("There has to be a given timespan to create a project.");
		} else if (budgettetTime < 0) {
			throw new OperationNotAllowedException("Budgettet time must be at least 0.");
		} else {
			activities.add(new Activity(name, description, timespan, this, planApp, budgettetTime));
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
