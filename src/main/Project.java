package main;

public class Project implements Comparable<Project> {
	
	private String name;
	private String id;
	
	private User projectLeader;
	
	public Project(String name, String id, User projectLeader){
		this.name = name;
		this.id = id;
		this.projectLeader = projectLeader;
	}

	public String getID() {
		return new String(id);
	}

	public String getFollowup() {
		// TODO create the real followup
		return "this is a followUp test message";
	}
	
	public boolean contains(String key){
		if (name.contains(key) || id.contains(key)){
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Project p) {
		return this.id.compareTo(p.getID());
	}

	public void addActivity(String string, String string2, Timespan timespan, int i) {
		// TODO Auto-generated method stub
		
	}

	public Activity getActivityByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public User getProjectLeader() {
		return this.projectLeader;
	}


	

}
