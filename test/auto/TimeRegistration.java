package auto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.GregorianCalendar;

import main.Timespan;

import org.junit.Test;

import exceptions.OperationNotAllowedException;
import exceptions.OverlapException;

/**
 * 
 * @author Mathias Gammelmark
 *
 */
public class TimeRegistration extends SampleDataSetup {

	@Test
	public void testMain() throws Exception {
		// login as a normal user
		planApp.login("DaSc");

		Timespan tempTime = new Timespan(new GregorianCalendar(2015, 5, 3, 12, 30, 0), new GregorianCalendar(2015, 5,
				3, 16, 0, 0));

		assertFalse(planApp.getActiveUser().hasOverlapingReport(tempTime));
		assertEquals(0d, planApp.getProjectByName("HalfLife 3").getActivityByName("Engine").getRegistretTime(), 0);

		planApp.getProjectByName("HalfLife 3").getActivityByName("Engine")
				.registreTime(tempTime, "This is some serius work...");

		assertTrue(planApp.getActiveUser().hasOverlapingReport(tempTime));
		assertEquals(3.5d, planApp.getProjectByName("HalfLife 3").getActivityByName("Engine").getRegistretTime(), 0);
	}

	@Test
	public void testOverlapingReport() throws Exception {

		// login as a normal user
		planApp.login("DaSc");

		Timespan tempTime = new Timespan(new GregorianCalendar(2015, 5, 3, 12, 30, 0), new GregorianCalendar(2015, 5,
				3, 16, 0, 0));
		Timespan overlappingTime = new Timespan(new GregorianCalendar(2015, 5, 3, 15, 0, 0), new GregorianCalendar(
				2015, 5, 3, 16, 0, 0));

		assertFalse(planApp.getActiveUser().hasOverlapingReport(tempTime));
		assertEquals(0d, planApp.getProjectByName("HalfLife 3").getActivityByName("Engine").getRegistretTime(), 0);

		planApp.getProjectByName("HalfLife 3").getActivityByName("Engine")
				.registreTime(tempTime, "This is some serius work...");

		assertTrue(planApp.getActiveUser().hasOverlapingReport(tempTime));
		assertEquals(3.5d, planApp.getProjectByName("HalfLife 3").getActivityByName("Engine").getRegistretTime(), 0);

		try {
			// try to register time in antoher activity to be sure that it is in
			// the user the overlapping check is.
			planApp.getProjectByName("HalfLife 3").getActivityByName("Design")
					.registreTime(overlappingTime, "This is some serius overlapping work...");
			fail("");
		} catch (OverlapException e) {
			assertTrue(e.getMessage().equals(
					"Cant register time, the active user has a overlapping report"));
		}

		assertTrue(planApp.getActiveUser().hasOverlapingReport(tempTime));
		assertEquals(3.5d, planApp.getProjectByName("HalfLife 3").getActivityByName("Engine").getRegistretTime(), 0);
	}

	@Test
	public void testUserIsNotLoggedIn() throws Exception {

		// making sure there isnt a user logged in
		planApp.logout();

		Timespan tempTime = new Timespan(new GregorianCalendar(2015, 5, 3, 12, 30, 0), new GregorianCalendar(2015, 5,
				3, 16, 0, 0));

		// assertFalse(planApp.getActiveUser().overlapingReport(tempTime));
		assertEquals(0d, planApp.getProjectByName("HalfLife 3").getActivityByName("Engine").getRegistretTime(), 0);

		try {
			planApp.getProjectByName("HalfLife 3").getActivityByName("Engine")
					.registreTime(tempTime, "This is some serius work...");
			fail("it should not be possible to register time when noone is logged in");
		} catch (OperationNotAllowedException e) {
			assertTrue(e.getMessage().equals("cant register time, when no user is logged in."));
		}

		// assertTrue(planApp.getActiveUser().overlapingReport(tempTime));
		assertEquals(0d, planApp.getProjectByName("HalfLife 3").getActivityByName("Engine").getRegistretTime(), 0.01d);

	}

	@Test
	public void testTimespanIsNull() throws Exception {

		// login as a normal user
		planApp.login("DaSc");

		try {
			planApp.getProjectByName("HalfLife 3").getActivityByName("Engine")
					.registreTime(null, "This is some serius work...");
			fail("cant register time when the timespan is empty");
		} catch (OperationNotAllowedException e) {
			assertEquals("Cant register time, the given timespan is null", e.getMessage());
		}
	}
	
	

}
