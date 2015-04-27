package main;

import java.util.Set;
import java.util.TreeSet;

public class User implements Comparable<User>{
	
	private String name;
	private String initials;
	
	private Set<Report> reports = new TreeSet<Report>();
	private Set<Activity> plannedActivities = new TreeSet<Activity>();
	
	public User(String name, String initials){
		this.name = name;
		this.initials = initials;
	}
	
	public String getName(){
		return name;
	}
	
	public String getInitials(){
		return initials;
	}
	
	public boolean isAvailable(Timespan t){
		return false;
	}
	
	public boolean contains(String key){
		return false;
	}
	
	public void addReport(Report r){
		
	}
	
	public void addActivity(Activity a){
		
	}

	@Override
	public int compareTo(User u) {
		return this.initials.compareTo(u.getInitials());
	}

	public User copy() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean overlapingReport(Timespan tempTime) {
		// TODO Auto-generated method stub
		return false;
	}

}
