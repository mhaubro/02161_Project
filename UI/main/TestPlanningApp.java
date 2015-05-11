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
		
		logout();
		login("DaSc");
		
		getProjectByName("HalfLife 3").getActivityByName("Design")
		.registreTime(new Timespan(
				new GregorianCalendar(2015, 6, 3, 8, 0), 
				new GregorianCalendar(2015, 6, 3, 16, 0)), "This is some serius Design work...");
		
		getProjectByName("HalfLife 3").getActivityByName("Design")
		.registreTime(new Timespan(
				new GregorianCalendar(2015, 6, 6, 8, 0), 
				new GregorianCalendar(2015, 6, 6, 16, 0)), "This is some serius Design work...");
		getProjectByName("HalfLife 3").getActivityByName("Engine")
		.registreTime(new Timespan(
				new GregorianCalendar(2015, 6, 4, 8, 0), 
				new GregorianCalendar(2015, 6, 4, 16, 0)), "This is some serius Engine work...");
		
		
		logout();
		login("GaWo");
		getProjectByName("HalfLife 3").getActivityByName("Design")
		.registreTime(new Timespan(
				new GregorianCalendar(2015, 6, 6, 8, 0), 
				new GregorianCalendar(2015, 6, 6, 12, 0)), "This is some serius Design work...");
		getProjectByName("HalfLife 3").getActivityByName("Engine")
		.registreTime(new Timespan(
				new GregorianCalendar(2015, 6, 4, 8, 0), 
				new GregorianCalendar(2015, 6, 4, 12, 0)), "This is some serius Engine work...");
		
		
		logout();
		//Logger ind som projektleder. Tilføjer arbejde + planer
		login("ZaBe");
		//Planlægger GaWo
		getProjectByName("HalfLife 3").getActivityByName("Engine").planWork("GaWo", new Timespan(
				new GregorianCalendar(2015, 6, 4, 8, 0), 
				new GregorianCalendar(2015, 6, 4, 12, 0)));
		getProjectByName("HalfLife 3").getActivityByName("Design").planWork("GaWo", new Timespan(
				new GregorianCalendar(2015, 6, 6, 8, 0), 
				new GregorianCalendar(2015, 6, 6, 12, 0)));
		//Planlægger ZaBe
		getProjectByName("HalfLife 3").getActivityByName("Engine").planWork("ZaBe", new Timespan(
				new GregorianCalendar(2015, 6, 6, 8, 0), 
				new GregorianCalendar(2015, 6, 6, 12, 0)));
		getProjectByName("HalfLife 3").getActivityByName("Engine").planWork("ZaBe", new Timespan(
				new GregorianCalendar(2015, 6, 12, 8, 0), 
				new GregorianCalendar(2015, 6, 12, 12, 0)));
		//Planlægger DaSc
		getProjectByName("HalfLife 3").getActivityByName("Design").planWork("DaSc", new Timespan(
				new GregorianCalendar(2015, 6, 3, 8, 0), 
				new GregorianCalendar(2015, 6, 3, 16, 0)));
		getProjectByName("HalfLife 3").getActivityByName("Design").planWork("DaSc", new Timespan(
				new GregorianCalendar(2015, 6, 4, 8, 0), 
				new GregorianCalendar(2015, 6, 4, 16, 0)));
		getProjectByName("HalfLife 3").getActivityByName("Engine").planWork("DaSc", new Timespan(
				new GregorianCalendar(2015, 6, 5, 8, 0), 
				new GregorianCalendar(2015, 6, 5, 16, 0)));
		//Registrerer ZaBe
		getProjectByName("HalfLife 3").getActivityByName("Engine")
		.registreTime(new Timespan(
				new GregorianCalendar(2015, 6, 6, 8, 0), 
				new GregorianCalendar(2015, 6, 6, 12, 0)), "This is some serius Engine work...");
		//Derved er der indtastet en masse data, som en followUp kan arbejde med
		logout();
	}

}
