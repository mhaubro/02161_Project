package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import exceptions.NoSuchUserException;
import exceptions.OperationNotAllowedException;
import exceptions.OverlapException;
import exceptions.TimeSpanIsNotValidException;
import exceptions.UserAlreadyPlannedException;

public class Activity {

	private String name;
	private String task;

	private int budgettedTime;

	private PlanningApp planApp;
	private Timespan timespan;
	private Project project;
	private ArrayList<User> users = new ArrayList<User>(); 
	private ArrayList<Report> reports = new ArrayList<Report>(); 
	private ArrayList<PlannedWork> plans = new ArrayList<PlannedWork>(); 

	public Activity(String name, String task, Timespan t, Project p, PlanningApp planApp, int budgettetTime) {
		this.name = name;
		this.task = task;
		this.timespan = t;
		this.project = p;
		this.planApp = planApp;
		this.budgettedTime = budgettetTime;
	}

	public void planWork(String userId, Timespan planTime) throws NoSuchUserException, OperationNotAllowedException, UserAlreadyPlannedException, TimeSpanIsNotValidException {
		if(planTime == null){
			throw new TimeSpanIsNotValidException("TimeSpan-object does not exist");
		} else if(!(this.project.getProjectLeader().getInitials().equalsIgnoreCase(this.getActiveUser().getInitials()) || planApp.isSuperByInitials(getActiveUser().getInitials()))){
			throw new OperationNotAllowedException("The active user is not project leader");
		} else if (!this.getUserByInitials(userId).isAvailable(planTime)){
			throw new UserAlreadyPlannedException("The User is already planned at the time");
		} else if (planTime.getTime() > 24){
			throw new TimeSpanIsNotValidException("Plans can't be for more than 24 hours");
		}
		User user = this.planApp.getUserByInitials(userId);
		//Danner plannedWork-objekt
		PlannedWork plannedWork = new PlannedWork(user, planTime, this);
		//Gemmer dette:
		plannedWork.getUser().addPlannedWork(plannedWork);
		this.plans.add(plannedWork);
	}

	public void registreTime(Timespan timespan, String note) throws OperationNotAllowedException, OverlapException {
		User activeUser = getActiveUser();
		if (activeUser == null)
			throw new OperationNotAllowedException("cant register time, when no user is logged in.");
		if (timespan == null)
			throw new OperationNotAllowedException("Cant register time, the given timespan is null");
		if (activeUser.hasOverlapingReport(timespan)) {
			throw new OverlapException("Cant register time, the active user has a overlapping report");
		}

		Report newReport = new Report(note, timespan, this, activeUser);
		
		reports.add(newReport);
		activeUser.addReport(newReport);
		
		if (!users.contains(activeUser)){
			users.add(activeUser);
		}
	}

	public double getRegistretTime() {
		double sum = 0;
		for (Report r : reports){
			sum += r.getReportedTime();
		}
		return sum;
	}

	public String getName() {
		return this.name;
	}

	private User getActiveUser() throws OperationNotAllowedException {
		if (planApp == null)
			throw new OperationNotAllowedException("cant return active user, No planningApp linked to this activity");
		return planApp.getActiveUser();
	}
	
	private User getUserByInitials(String userID) throws OperationNotAllowedException, NoSuchUserException {
		if (planApp == null)
			throw new OperationNotAllowedException("cant return active user, No planningApp linked to this activity");
		return planApp.getUserByInitials(userID);
	}

	public List<PlannedWork> getPlans(Timespan t){
		List<PlannedWork>plansInTimespan = new ArrayList<PlannedWork>();
		for(PlannedWork p : plans){
			if(p.plansOverlapTimespan(t)){
				plansInTimespan.add(p);
			}
		}
		return Collections.unmodifiableList(plansInTimespan);
	}
	
	public void deletePlannedWork(PlannedWork plannedWork){
		plans.remove(plannedWork);
	}

	public double getPlannedTime() {
		double plannedTime = 0;
		for (PlannedWork plan : this.plans){
			plannedTime += plan.getTime();
		}
		return plannedTime;
	}
	
	public int getBudgettetTime(){
		return this.budgettedTime;
	}
	
	public List<Report> getReports(){
		return Collections.unmodifiableList(reports);
	}
	
	public List<PlannedWork> getPlans(){
		return Collections.unmodifiableList(plans);
	}
	
}
