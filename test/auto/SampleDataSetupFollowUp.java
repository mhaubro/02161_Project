package auto;

import java.util.GregorianCalendar;

import main.PlanningApp;
import main.Timespan;

import org.junit.Before;

/**
 * 
 * @author Martin Haubro
 *
 */
public class SampleDataSetupFollowUp extends SampleDataSetup{
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
		
		//add superusers
		planApp.addUser("Mathias Gammelmark");
		planApp.makeSuper("MaGa");
		
		planApp.addUser("Martin Haubro");
		planApp.makeSuper("MaHa");
	
		
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

		
		
		
		
		//Der tilføjes arbejde til projektet
		planApp.logout();
		planApp.login("DaSc");
		
		planApp.getProjectByName("HalfLife 3").getActivityByName("Design")
		.registreTime(new Timespan(
				new GregorianCalendar(2015, 6, 3, 8, 0), 
				new GregorianCalendar(2015, 6, 3, 16, 0)), "This is some serius Design work...");
		
		planApp.getProjectByName("HalfLife 3").getActivityByName("Design")
		.registreTime(new Timespan(
				new GregorianCalendar(2015, 6, 6, 8, 0), 
				new GregorianCalendar(2015, 6, 6, 16, 0)), "This is some serius Design work...");
		planApp.getProjectByName("HalfLife 3").getActivityByName("Engine")
		.registreTime(new Timespan(
				new GregorianCalendar(2015, 6, 4, 8, 0), 
				new GregorianCalendar(2015, 6, 4, 16, 0)), "This is some serius Engine work...");
		
		
		planApp.logout();
		planApp.login("GaWo");
		planApp.getProjectByName("HalfLife 3").getActivityByName("Design")
		.registreTime(new Timespan(
				new GregorianCalendar(2015, 6, 6, 8, 0), 
				new GregorianCalendar(2015, 6, 6, 12, 0)), "This is some serius Design work...");
		planApp.getProjectByName("HalfLife 3").getActivityByName("Engine")
		.registreTime(new Timespan(
				new GregorianCalendar(2015, 6, 4, 8, 0), 
				new GregorianCalendar(2015, 6, 4, 12, 0)), "This is some serius Engine work...");
		
		
		planApp.logout();
		//Logger ind som projektleder. Tilføjer arbejde + planer
		planApp.login("ZaBe");
		//Planlægger GaWo
		planApp.getProjectByName("HalfLife 3").getActivityByName("Engine").planWork("GaWo", new Timespan(
				new GregorianCalendar(2015, 6, 4, 8, 0), 
				new GregorianCalendar(2015, 6, 4, 12, 0)));
		planApp.getProjectByName("HalfLife 3").getActivityByName("Design").planWork("GaWo", new Timespan(
				new GregorianCalendar(2015, 6, 6, 8, 0), 
				new GregorianCalendar(2015, 6, 6, 12, 0)));
		//Planlægger ZaBe
		planApp.getProjectByName("HalfLife 3").getActivityByName("Engine").planWork("ZaBe", new Timespan(
				new GregorianCalendar(2015, 6, 6, 8, 0), 
				new GregorianCalendar(2015, 6, 6, 12, 0)));
		planApp.getProjectByName("HalfLife 3").getActivityByName("Engine").planWork("ZaBe", new Timespan(
				new GregorianCalendar(2015, 6, 12, 8, 0), 
				new GregorianCalendar(2015, 6, 12, 12, 0)));
		//Planlægger DaSc
		planApp.getProjectByName("HalfLife 3").getActivityByName("Design").planWork("DaSc", new Timespan(
				new GregorianCalendar(2015, 6, 3, 8, 0), 
				new GregorianCalendar(2015, 6, 3, 16, 0)));
		planApp.getProjectByName("HalfLife 3").getActivityByName("Design").planWork("DaSc", new Timespan(
				new GregorianCalendar(2015, 6, 4, 8, 0), 
				new GregorianCalendar(2015, 6, 4, 16, 0)));
		planApp.getProjectByName("HalfLife 3").getActivityByName("Engine").planWork("DaSc", new Timespan(
				new GregorianCalendar(2015, 6, 5, 8, 0), 
				new GregorianCalendar(2015, 6, 5, 16, 0)));
		//Registrerer ZaBe
		planApp.getProjectByName("HalfLife 3").getActivityByName("Engine")
		.registreTime(new Timespan(
				new GregorianCalendar(2015, 6, 6, 8, 0), 
				new GregorianCalendar(2015, 6, 6, 12, 0)), "This is some serius Engine work...");
		//Derved er der indtastet en masse data, som en followUp kan arbejde med
		planApp.logout();
		
	}	

}
