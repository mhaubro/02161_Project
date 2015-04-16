package main;

import java.util.Set;
import java.util.TreeSet;

public class Activity {
	
	private String name;
	private String task;
	private String id;
	
	private int budgettedTime;
	private int startWeek;
	private int endWeek;
	
	private Timespan timespan;
	private Project project;
	private Set<User> users = new TreeSet<User>();
	private Set<Report> reports = new TreeSet<Report>();
	private Set<PlannedWork> plannedWork = new TreeSet<PlannedWork>();
	
	public Activity(String name, String task, String id, Timespan t, Project p){
		this.name = name;
		this.task = task;
		this.id = id;
		this.timespan = t;
		this.project = p;
	}

	public Activity copy() {
		// TODO Auto-generated method stub
		return null;
	}

	public void planWork(String string, Timespan timespan2) {
		// TODO Auto-generated method stub
		
	}

}
