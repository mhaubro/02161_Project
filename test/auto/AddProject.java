package auto;

import static org.junit.Assert.*;
import main.PlanningApp;
import main.Project;
import main.User;

import org.junit.Before;
import org.junit.Test;

public class AddProject {

	PlanningApp planApp;

	@Before
	public void setUp() throws Exception {
		planApp = new PlanningApp();
	}

	@Test
	public void testMain() {
		
		// the initial setup for the test.
		User u = new User("user1", "u1");
		Project p = new Project("project1", "1234", u);
		

		// adding the project to the planningApp
		planApp.addProject(p);
	}

	@Test
	public void testIsNotSuperUser() {

	}

	@Test
	public void testSuperDosntAddUser() {

	}

}
