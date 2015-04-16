package auto;

import static org.junit.Assert.*;
import main.PlanningApp;
import main.Project;
import main.User;

import org.junit.Before;
import org.junit.Test;

import exceptions.NoSuchUserException;
import exceptions.OperationNotAllowedException;

public class AddProject extends SampleDataSetup {

	PlanningApp planApp;

	@Test
	public void testMain() throws Exception {

		// login as a superuser
		planApp.login("MaGa");

		// add the project to a existing user.
		planApp.addProject("WirelessString", "MiJh");

		// test if the projectleader is the right one.
		assertEquals("Micheal Jhonson", planApp.getProjectByName("WirelessString").getProjectLeader().getName());
	}

	@Test
	public void testIsNotSuperUser() {

		// login as a not superuser
		planApp.login("MiJh");

		try {
			planApp.addProject("WirelessString", "MiJh");
			fail("cannot add a project when the current user is not a superUser");
		} catch (OperationNotAllowedException e) {

		} catch (NoSuchUserException e) {
			fail("The user is known");
		}
	}

	@Test
	public void testSuperDosntAddCorrectUser() {

		// login as a superUser
		planApp.login("MaGa");

		try {
			// this method should throw an exception, because the initials does
			// not correspond to a user in the app
			planApp.addProject("FreeDrinks", "AaAa");
			fail("should not be able to add a project with a incorrect user");
		} catch (OperationNotAllowedException e) {
			fail("the name should be free and the active user should be super");
		} catch (NoSuchUserException e) {

		}
	}

	@Test
	public void testProjectNameAllreadyExist() {
		// login as a superUser
		planApp.login("MaGa");

		try {
			// this method should throw an exception, because the initials does
			// not correspond to a user in the app
			planApp.addProject("SolStorm", "MiNe");
			fail("should not be able to add a project with a name that allready exist");
		} catch (OperationNotAllowedException e) {
			
		} catch (NoSuchUserException e) {
			fail("the name should be free and the active user should be super");
		}
	}

}
