package auto;

import static org.junit.Assert.*;
import main.PlanningApp;
import main.User;

import org.junit.Before;
import org.junit.Test;

import exceptions.NoSuchUserException;

public class AddUser {

	PlanningApp planApp;

	@Before
	public void setUp() throws Exception {
		planApp = new PlanningApp();
	}

	@Test
	public void testMain() throws Exception {
		
		planApp.login("Admin");

		// checks if the user is in the app.
		try {
			planApp.getUserByInitials("JeSm");
			fail("the user is not added yet");
		} catch (NoSuchUserException e) {}
		
		assertEquals(0,planApp.getNumberOfEmployes());

		// adds the user
		planApp.addUser("Jeff Smith");

		// checks if the user now is in the app, in a correctly fashion.
		assertEquals(1,planApp.getNumberOfEmployes());
		assertTrue(planApp.getUserByInitials("JeSm").getName().equals("Jeff Smith"));
		
		planApp.addUser("Johanna Olson");
		assertEquals(2,planApp.getNumberOfEmployes());
		
		planApp.removeUserByInitials("JoOl");
		assertEquals(1,planApp.getNumberOfEmployes());
		
		planApp.removeUserByInitials("JeSm");
		assertEquals(0,planApp.getNumberOfEmployes());
		
		
	}

	/*
	@Test
	public void testSuperUser() throws Exception {
		
		planApp.login("Admin");
		assertEquals(0,planApp.getNumberOfEmployes());

		// add a user to an empty planningApp
		planApp.addUser("Jeff Smith");

		// tests if the only user is superuser
		assertEquals(1,planApp.getNumberOfEmployes());
		assertTrue(planApp.isSuperByInitials("JeSm"));
		
		// adds another user, which should not be superuser
		planApp.addUser("Micheal Hanson");
		assertEquals(2,planApp.getNumberOfEmployes());
		assertFalse(planApp.isSuperByInitials("MiHa"));
		
		// removes the last superuser, while still leaving another user in the app
		planApp.removeUserByInitials("JeSm");
		assertEquals(1,planApp.getNumberOfEmployes());
		
		assertTrue(planApp.isSuperByInitials("MiHa"));
		
	}*/

}
