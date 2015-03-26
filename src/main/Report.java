package main;

public class Report {

	private String note;
	private String id;

	private Timespan timespan;
	private Activity activity;
	private User user;

	public Report(String n, Timespan t, Activity a, User u) {
		this.note = n;
		this.timespan = t;
		this.activity = a;
		this.user = u;
	}

	public Timespan getTime() {
		return timespan.copy();
	}

	public Activity getActivity() {
		return activity.copy();
	}

	public User getUser() {
		return user.copy();
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

}
