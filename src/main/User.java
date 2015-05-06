package main;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class User implements Comparable<User>{
	
	private String name;
	private String initials;
	
	private Set<Report> reports = new TreeSet<Report>();
	private Set<Activity> plannedActivities = new TreeSet<Activity>();
	private List<PlannedWork> plans = new ArrayList<PlannedWork>();
	
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
		for(PlannedWork p : plans){
			if (p.plansOverlapTimespan(t)){
				return false;
			}
		}
		return true;
	}
	
	public boolean contains(String key){
		return false;
	}
	
	public void addReport(Report r){
		reports.add(r);
		System.out.println("Addded a report to " + getInitials());
		
		if (!plannedActivities.contains(r.activity)){
			plannedActivities.add(r.activity);
		}
		
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

	public boolean hasOverlapingReport(Timespan tempTime) {
		return reports.parallelStream().anyMatch(R -> R.overlap(tempTime));
	}
	
	public List<PlannedWork> getPlans(){
		return Collections.unmodifiableList(plans);
	}

	public List<PlannedWork> getPlans(Timespan t){
		return Collections.unmodifiableList(plans.parallelStream().filter(p -> p.plansOverlapTimespan(t)).collect(Collectors.toList()));

	}
	
	public void addPlannedWork(PlannedWork plannedWork){
		plans.add(plannedWork);
	}
	
	public void deletePlannedWork(PlannedWork plannedWork){
		plans.remove(plannedWork);

	}
	
	public List<Activity> getActivities(){
		return plannedActivities.parallelStream().sorted().collect(Collectors.toList());
	}
	
}
