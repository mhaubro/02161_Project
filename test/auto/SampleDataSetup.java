package auto;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import library04.Address;
import library04.LibraryApp;
import library04.actor.User;
import library04.media.Book;
import library04.media.Medium;
import main.PlanningApp;
import main.Timespan;

import org.junit.Before;

public class SampleDataSetup {

	PlanningApp planApp;

	/**
	 * Create the sample data for the search test cases. This method is executed
	 * each time one of the test methods is run. In contrast to old JUnit
	 * versions, it is now the @Before annotations that marks this method to be
	 * run before each test case and not the name setUp().
	 * 
	 * This method is inherited by subclasses. Thus any tests that needs sample
	 * data, should inherit from this class and add sample data as necessary.
	 */
	@Before
	public void setUp() throws Exception {
		planApp = new PlanningApp();

		planApp.login("Admin");

		// adds some users
		planApp.addUser("Jeff Smith");
		planApp.addUser("Micheal Jhonson");
		planApp.addUser("Sabrina Murdoch");
		planApp.addUser("Philomena Atwood");
		planApp.addUser("Dan Schoen");
		planApp.addUser("Gabriella Woerner");
		planApp.addUser("Syreeta Robinett");
		planApp.addUser("Millie Nealon");
		planApp.addUser("Jarret Cotto");
		planApp.addUser("Marnie Hopton");
		planApp.addUser("Zachariah Beckstead");
		planApp.addUser("Isiah SteinBerger");

		
		// add projects
		planApp.addProject("Solstorm", "MiNe");
		planApp.addProject("HalfLife 3", "ZaBe");

		
		// add activities to the projects
		planApp.getProjectByName("HalfLife 3").addActivity(
				"Engine", 
				"make a game engine",
				new Timespan(
						new GregorianCalendar(2015, 2, 3), 
						new GregorianCalendar(2015, 4, 5)), 
				20);
		
		planApp.getProjectByName("HalfLife 3").addActivity(
				"Design", 
				"make a fairly intelligent design",
				new Timespan(
						new GregorianCalendar(2015, 2, 5), 
						new GregorianCalendar(2015, 5, 5)), 
				25);
		
		
		// add a loose activity
		planApp.addActivity(
				"Course", 
				"learn to iceskate",
				new Timespan(
						new GregorianCalendar(2015, 5, 3), 
						new GregorianCalendar(2015, 8, 2)), 
				20);
		
		planApp.getProjectByName("HalfLife 3").getActivityByName("Design").planWork("DaSc",new Timespan(
						new GregorianCalendar(2015, 5, 3,12,30,0), 
						new GregorianCalendar(2015, 8, 2,16,0,0)));

	}

}
