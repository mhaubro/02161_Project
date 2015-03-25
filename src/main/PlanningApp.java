package main;

import java.util.Collections;
import java.util.Set;

public class PlanningApp {
	
	private Set<User> users;
	
	public Set<User> getUsers(){
		return Collections.unmodifiableSet(users);
	}
	
	public void setUsers(Set<User> users){
		this.users = users;
	}

}
