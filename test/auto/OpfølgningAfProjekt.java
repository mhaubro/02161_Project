package auto;

import static org.junit.Assert.*;
import main.PlanningApp;
import main.Project;
import main.User;

import org.junit.Before;
import org.junit.Test;

public class OpfølgningAfProjekt {

	PlanningApp planApp;

	@Before
	public void setUp() throws Exception {
		planApp = new PlanningApp();
	}

	@Test
	public void testMainScenario() {

		// the initial setup for the test.
		User u = new User("user1", "u1");
		Project p = new Project("project1", "1234", u);

		// adding the project to the planningApp
		planApp.addProject(p);
		
		
		//1. the user searches for a project.
		assertTrue(planApp.searchProject("123").contains(p));
		
		Project tempProject = planApp.getProject("1234");
		if (tempProject == null) {
			fail("tempProject should not be null");
		}
		String followup = tempProject.getFollowup();
		assertNotEquals("", followup);
		System.out.println(followup);
	}

}
