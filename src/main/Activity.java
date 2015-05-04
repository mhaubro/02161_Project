package main;

import java.util.ArrayList;
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
	private ArrayList<User> users = new ArrayList<User>(); // OBSOBSOBS
	private ArrayList<Report> reports = new ArrayList<Report>(); // OBSOBSOBS
	private ArrayList<PlannedWork> plans = new ArrayList<PlannedWork>(); // OBSOBSOBS

	public Activity(String name, String task, Timespan t, Project p, PlanningApp planApp, int budgettetTime) {
		this.name = name;
		this.task = task;
		this.timespan = t;
		this.project = p;
		this.planApp = planApp;
	}

	public Activity copy() {
		// TODO Auto-generated method stub
		return null;
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
	
	public ArrayList<PlannedWork> getPlans(){
		return plans;
	}

	public ArrayList<PlannedWork> getPlans(Timespan t){
		ArrayList<PlannedWork>plansInTimespan = new ArrayList<PlannedWork>();
		for(PlannedWork p : plans){
			if(p.plansOverlapTimespan(t)){
				plansInTimespan.add(p);
			}
		}
		return plansInTimespan;
	}
	
	public void deletePlannedWork(PlannedWork plannedWork){
		//int i = 0;
		plans.remove(plannedWork);
//		for(int i = 0; i < plans.size(); i++){
//			if (plans.get(i).equals(plannedWork)){
//				System.out.println("Der er tale om et plannedWork der slettes");
//				plans.remove(i);
//			}
//		}
	}
	
}
