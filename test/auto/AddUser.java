package auto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import main.PlanningApp;
import main.User;

import org.junit.Test;



public class AddUser {


	@Test
	public void testAdd() throws Exception {
		
		PlanningApp planApp = new PlanningApp();
		User testUser1 = new User("Jeff Smith", "JS");
		User testUser2 = new User("Michal Svenson", "MS");
		
		
		assertFalse(planApp.getUsers().contains(testUser1));
		assertFalse(planApp.getUsers().contains(testUser2));
		
		planApp.addUser(testUser1);
		
		assertTrue(planApp.getUsers().contains(testUser1));
		assertFalse(planApp.getUsers().contains(testUser2));
		
		planApp.addUser(testUser2);
		
		assertTrue(planApp.getUsers().contains(testUser2));
		
	}

}
