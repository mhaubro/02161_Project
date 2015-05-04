package main;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import exceptions.OperationNotAllowedException;
import exceptions.OverlapException;

public class Activity {

	private String name;
	private String task;

	private int budgettedTime;

	private PlanningApp planApp;
	private Timespan timespan;
	private Project project;
	private ArrayList<User> users = new ArrayList<User>(); // OBSOBSOBS
	private ArrayList<Report> reports = new ArrayList<Report>(); // OBSOBSOBS
	private ArrayList<PlannedWork> plannedWork = new ArrayList<PlannedWork>(); // OBSOBSOBS

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

	public void planWork(String string, Timespan timespan2) {
		// TODO Auto-generated method stub

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

}
