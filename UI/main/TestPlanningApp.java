package main;

import java.util.GregorianCalendar;

public class TestPlanningApp extends PlanningApp {
	
	public TestPlanningApp(){
		super();
		
		try {
			setup();
		} catch (Exception e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		
	}
	
	private void setup()throws Exception{
		login("Admin");
		
		//add superusers
		addUser("Mathias Gammelmark");
		makeSuper("MaGa");
		
		addUser("Martin Haubro");
		makeSuper("MaHa");
			
		// adds some users
		addUser("Jeff Smith");
		addUser("Micheal Jhonson");
		addUser("Sabrina Murdoch");
		addUser("Philomena Atwood");
		addUser("Dan Schoen");
		addUser("Gabriella Woerner");
		addUser("Syreeta Robinett");
		addUser("Millie Nealon");
		addUser("Jarret Cotto");
		addUser("Marnie Hopton");
		addUser("Zachariah Beckstead");
		addUser("Isiah SteinBerger");

		
		// add projects
		addProject("Solstorm", "MiNe");
		addProject("HalfLife 3", "ZaBe");

		
		// add activities to the projects
		getProjectByName("HalfLife 3").addActivity(
				"Engine", 
				"make a game engine",
				new Timespan(
						new GregorianCalendar(2015, 2, 3), 
						new GregorianCalendar(2015, 4, 5)), 
				20);
		
		getProjectByName("HalfLife 3").addActivity(
				"Design", 
				"make a fairly intelligent design",
				new Timespan(
						new GregorianCalendar(2015, 2, 5), 
						new GregorianCalendar(2015, 5, 5)), 
				25);
		
		
		// add a loose activity
		addActivity(
				"Course", 
				"learn to iceskate",
				new Timespan(
						new GregorianCalendar(2015, 5, 3), 
						new GregorianCalendar(2015, 8, 2)), 
				20);
	}

}
