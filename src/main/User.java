package main;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class User implements Comparable<User>{
	
	private String name;
	private String initials;
	
	private Set<Report> reports = new TreeSet<Report>();
	private Set<Activity> plannedActivities = new TreeSet<Activity>();
	private Set<PlannedWork> plans = new TreeSet<PlannedWork>();
	
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
	
	public Set<PlannedWork> getPlans(){
		return plans;
	}

	public Set<PlannedWork> getPlans(Timespan t){
		Set<PlannedWork> plansInTimespan = new TreeSet<PlannedWork>();
		for (PlannedWork p : plans){
			if (p.plansOverlapTimespan(t)){
				plansInTimespan.add(p);
			}
		}
		return plansInTimespan;
	}
	
	public void addPlannedWork(PlannedWork plannedWork){
		plans.add(plannedWork);
	}
	
	public void deletePlannedWork(PlannedWork plannedWork){
		plans.remove(plannedWork);
//		int i = 0;
//		for (PlannedWork p : plans){
//			if(p.equals(plannedWork)){
//				plans.remove(i);
//			}
//			i++;
//		}
	}
	
	public List<Activity> getActivities(){
		return plannedActivities.parallelStream().sorted().collect(Collectors.toList());
	}
	
}
