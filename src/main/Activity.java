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
	
	private Timespan timespan;
	private Project project;
	private ArrayList<User> users = new ArrayList<User>(); //OBSOBSOBS
	private ArrayList<Report> reports = new ArrayList<Report>(); //OBSOBSOBS
	private ArrayList<PlannedWork> plannedWork = new ArrayList<PlannedWork>(); //OBSOBSOBS
	
	public Activity(String name, String task, Timespan t, Project p, int budgettetTime){
		this.name = name;
		this.task = task;
		this.timespan = t;
		this.project = p;
	}
	public Activity(String name, String task, Timespan t, int budgettetTime){
		this.name = name;
		this.task = task;
		this.timespan = t;
		this.project = null;
	}

	public Activity copy() {
		// TODO Auto-generated method stub
		return null;
	}

	public void planWork(String string, Timespan timespan2) {
		// TODO Auto-generated method stub
		
	}

	public void registreTime(Timespan timespan, String note) throws OperationNotAllowedException, OverlapException{
		if (timespan == null){
			throw new OperationNotAllowedException("cant register time, when not given a timespan");
		}
		
		// TODO Auto-generated method stub
		
	}

	public double getRegistretTime() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getName(){
		return this.name;
	}

}
