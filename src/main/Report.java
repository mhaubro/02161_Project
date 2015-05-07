package main;

public class Report implements Comparable<Report>{

	private String note;
	private String id;

	final Timespan timespan;
	final Activity activity;
	final User user;

	public Report(String n, Timespan t, Activity a, User u) {
		this.note = n;
		this.timespan = t;
		this.activity = a;
		this.user = u;
	}

	public Activity getActivity() {
		return activity.copy();
	}

	public User getUser() {
		return user;
	}

	public String getID() {
		return id;
	}

	public String getNote() {
		return note;
	}

	public Report copy() {
		return new Report(note, timespan, activity, user);
	}

	public boolean overlap(Timespan t) {
		return timespan.overlap(t);
	}

	@Override
	public int compareTo(Report otherReport) {
		return this.timespan.startTime.compareTo(otherReport.timespan.startTime);
	}

	public double getReportedTime() {
		//System.out.println("Bugtest");
		return timespan.getTime();
	}

}
