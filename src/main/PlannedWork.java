package main;

public class PlannedWork implements Comparable<PlannedWork> {

	private Timespan timespan;
	private Activity activity;
	private User user;
	
	public PlannedWork(User user, Timespan timespan, Activity activity){
		this.timespan = timespan;
		this.activity = activity;
		this.user = user;
	}

	@Override
	public int compareTo(PlannedWork otherPlannedWork) {
		return this.timespan.startTime.compareTo(otherPlannedWork.timespan.startTime);
	}

	public boolean plansOverlapTimespan(Timespan t) {
		return t.overlap(timespan);
	}
	
	public User getUser(){
		return this.user;
	}
	
	public void deletePlannedWork(){
		this.activity.deletePlannedWork(this);
		this.user.deletePlannedWork(this);
	}
	
	public double getTime(){
		return this.timespan.getTime();
	}
	
//	public Timespan getTimespan(){
//		return this.timespan;
//	}
//	
//	public Activity getActivity(){
//		return this.activity;
//	}
}
