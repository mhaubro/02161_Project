package main;

public class Project {
	
	private String name;
	private String id;
	
	private User projectLeader;
	
	public Project(String name, String id, User projectLeader){
		this.name = name;
		this.id = id;
		this.projectLeader = projectLeader;
	}

	public String getID() {
		return id;
	}

	public String getFollowup() {
		// TODO create the real followup
		return "this is a followUp test message";
	}

}
