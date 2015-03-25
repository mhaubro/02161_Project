package main;

public class User implements Comparable<User>{
	
	private String name;
	private String id;
	
	public User(String name, String id){
		this.name = name;
		this.id = id;
	}
	
	public String getID(){
		return new String(id);
	}

	@Override
	public int compareTo(User u) {
		return this.id.compareTo(u.getID());
	}

}
