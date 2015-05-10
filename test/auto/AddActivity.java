package auto;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import main.Activity;
import main.PlanningApp;
import main.Project;
import main.Timespan;
import main.User;

import org.junit.*;

import exceptions.*;

public class AddActivity extends SampleDataSetup{
	
	//Tilføjer aktivitet som projektleder. Skal godkende.
	@Test
	public void testMain() throws Exception{

		Timespan timespan = new Timespan(new GregorianCalendar(2015,1,1), new GregorianCalendar(2015,3,1));
		
		planApp.login("MiNe"); 
		planApp.getProjectByName("Solstorm").addActivity("Regndans", "Der skal også vand til!", timespan, 10);
		
		assertEquals(1,planApp.getProjectByName("Solstorm").getNumberOfActivities());
		assertEquals(true, planApp.getProjectByName("Solstorm").containsActivity("Regndans"));
		
	}
	
	@Test
	public void testIsSuper() throws Exception{
		Timespan timespan = new Timespan(new GregorianCalendar(2015,1,1), new GregorianCalendar(2015,3,1));
		
		planApp.login("Admin"); 
		planApp.getProjectByName("Solstorm").addActivity("Regndans", "Der skal også vand til!", timespan, 10);
		
		assertEquals(1,planApp.getProjectByName("Solstorm").getNumberOfActivities());
		assertEquals(true, planApp.getProjectByName("Solstorm").containsActivity("Regndans"));
	}
	
	//Tilføjer aktivitet som almen bruger. Skal fejle.
	@Test
	public void testNotProjectLeader() throws Exception{
		
		Timespan timespan = new Timespan(new GregorianCalendar(2015,1,1), new GregorianCalendar(2015,3,1));
		
		planApp.login("DaSc");

		try{
			planApp.getProjectByName("Solstorm").addActivity("Regndans", "Der skal også vand til!", timespan, 10);
			fail();
		} catch (OperationNotAllowedException e){
			//assertEquals("The action is not allowed.",e.getMessage());
		}
		assertEquals(0,planApp.getProjectByName("Solstorm").getNumberOfActivities());
		assertEquals(false, planApp.getProjectByName("Solstorm").containsActivity("Regndans"));
	}
	
	@Test
	public void testNameIsInUse() throws OperationNotAllowedException, NoSuchUserException{
		Timespan timespan = new Timespan(new GregorianCalendar(2015,1,1), new GregorianCalendar(2015,3,1));
		
		planApp.login("MiNe"); 
		planApp.getProjectByName("Solstorm").addActivity("Regndans", "Der skal også vand til!", timespan, 10);
		
		try {
			planApp.getProjectByName("Solstorm").addActivity("Regndans", "Der skal også vand til!", timespan, 10);
			fail();
		} catch (OperationNotAllowedException e){
			
		}
		assertEquals(1,planApp.getProjectByName("Solstorm").getNumberOfActivities());
		assertEquals(true, planApp.getProjectByName("Solstorm").containsActivity("Regndans"));
	}
	
	@Test
	public void testBudgettetTimeIsPositive() throws NoSuchUserException, OperationNotAllowedException{
		Timespan timespan = new Timespan(new GregorianCalendar(2015,1,1), new GregorianCalendar(2015,3,1));
		
		planApp.login("MiNe"); 
		try{
			planApp.getProjectByName("Solstorm").addActivity("Regndans", "Der skal også vand til!", timespan, -1);
			fail();
		} catch (OperationNotAllowedException e){
			
		}
		
	}
	@Test
	public void testTimeSpanIsNotNull() throws NoSuchUserException{
		Timespan timespan = null;
		
		planApp.login("MiNe"); 
		try{
			planApp.getProjectByName("Solstorm").addActivity("Regndans", "Der skal også vand til!", timespan, 1);
			fail();
		} catch (OperationNotAllowedException e){
			
		}
		
	}
	
	@Test//Tester for samme som de foregående, men for løse aktiviteter. Det skal noteres, at der er 3 aktiviteter i forvejen fra DatasampleSetup.
	public void addLooseActivity() throws Exception{
		Timespan timespan = new Timespan(new GregorianCalendar(2015,1,1), new GregorianCalendar(2015,3,1));
		
		planApp.login("Admin"); 
		planApp.addActivity("Regndans", "Der skal også vand til!", timespan, 10);
		
		assertEquals(1+3,planApp.getAllActivities().size());
		assertEquals(true, planApp.getActivityByName("Regndans") != null);
		
		planApp.logout();
		//Der er herfra 3 + 1 aktiviteter i planningAppen
		//Tester at kun superuser kan tilføje aktivitet
		planApp.login("MiNe");
		try {
			planApp.addActivity("Regndans2222222", "Der skal også vand til!", timespan, 10);
			fail();
		} catch (OperationNotAllowedException e){
			
		}
		assertEquals(1+3,planApp.getAllActivities().size());
		planApp.logout();
		planApp.login("Admin");
		//Tester, at navnet ikke er i brug
		try {
			planApp.addActivity("Regndans", "Der skal også vand til! Meget mere vand!", timespan, 5);
			fail();
		} catch (OperationNotAllowedException e){
			
		}
		assertEquals(1+3,planApp.getAllActivities().size());
		//Tester at budgetteret tid >= 0
		try{
			planApp.addActivity("Ferie", "Det skal i ikke bruge tid på...", timespan, -1);
			fail();
		} catch (OperationNotAllowedException e){
			
		}
		assertEquals(1+3,planApp.getAllActivities().size());
		//Tester at de er et timespan-objekt
		timespan = null;
		
		planApp.login("MiNe"); 
		try{
			planApp.addActivity("Kursus", "Get smart", timespan, 1);
			fail();
		} catch (OperationNotAllowedException e){
			
		}
		assertEquals(1+3,planApp.getAllActivities().size());
	}
	
}






