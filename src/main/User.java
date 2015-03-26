package main;

import java.util.Set;
import java.util.TreeSet;

public class User implements Comparable<User>{
	
	private String name;
	private String id;
	private boolean isSuperUser;
	
	private Set<Report> reports = new TreeSet<Report>();
	private Set<Activity> plannedActivities = new TreeSet<Activity>();
	
	public User(String name, String id){
		this.name = name;
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getID(){
		return id;
	}
	
	public boolean isSuperUser(){
		return isSuperUser;
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
		return this.id.compareTo(u.getID());
	}

	public User copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
