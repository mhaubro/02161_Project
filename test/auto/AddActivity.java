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

public class AddActivity extends SampleDataSetup{
	
	//Tilføjer aktivitet som projektleder. Skal godkende.
	@Test
	public void testMain() throws Exception{

		Timespan timespan = new Timespan(new GregorianCalendar(2015,3,1), new GregorianCalendar(2015,1,1));
		
		planApp.login("MiNe"); 
		planApp.getProjectByName("Solstorm").addActivity("Regndans", "Der skal også vand til!", timespan, 10);
		
		assertEquals(1,planApp.getProjectByName("Solstorm").getNumberOfActivities());
		assertEquals(true, planApp.getProjectByName("Solstorm").containsActivity("Regndans"));
		
	}
	
	//Tilføjer aktivitet som SuperUser. Skal godkende
//	@Test
//	public void testIsSuperUser(){
//		
//		
//		Activity activity = new Activity("UML Diagrammer","UML","UMLD",timeSpan, project1, 100);//EDIT TIL Activity-object: Der er også int, int på til sidst - ugenumre/budgettet time
//
//	}
//	
	//Tilføjer aktivitet som almen bruger. Skal fejle.
	@Test
	public void testAltnernative() throws Exception{
		
		Timespan timespan = new Timespan(new GregorianCalendar(2015,3,1), new GregorianCalendar(2015,1,1));
		
		planApp.login("DaSc");

		try{
			planApp.getProjectByName("Solstorm").addActivity("Regndans", "Der skal også vand til!", timespan, 10);
			fail();
		} catch (OperationNotAllowedException e){
			assertEquals("The action is not allowed.",e.message);
		}
		assertEquals(0,planApp.getProjectByName("Solstorm").getNumberOfActivities());
		assertEquals(false, planApp.getProjectByName("Solstorm").containsActivity("Regndans"));
	}
	
	
	
}






