package auto;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import main.Activity;
import main.PlanningApp;
import main.Project;
import main.Timespan;
import main.User;

import org.junit.*;//Skal måske deles i en addPlan og check isPlanned
public class Planning extends SampleDataSetup{
	
	Timespan timespan1;
	Timespan timespan2;
	
	//Først testes, at den rette liste af uplanlagte medarbejdere dukker op
	//Dernæst planlægges en medarbejder
	//Dernæst tjekkes det, at denne er planlagt
	//Dernæst tjekkes det, at denne ikke kan planlægges igen.
	
	@Before
	public void setPlannedWorks(){
		

	}
	
	public void setUp2(){
		//Cals
		GregorianCalendar gregorianCalendar1 = new GregorianCalendar(2015, 2, 1, 9, 0);
		GregorianCalendar gregorianCalendar2 = new GregorianCalendar(2015, 2, 1, 14, 0);
		GregorianCalendar gregorianCalendar3 = new GregorianCalendar(2015, 2, 1, 12, 0);
		//TSpans
		Timespan timespan1 = new Timespan(gregorianCalendar1, gregorianCalendar2);
		Timespan timespan2 = new Timespan(gregorianCalendar3, gregorianCalendar2);
		//PlannedWork smækkes ind
		planApp.getProjectByName("HalfLife 3").getActivityByName("Engine").planWork("DaSc", timespan1);
		planApp.getProjectByName("HalfLife 3").getActivityByName("Engine").planWork("SaMu", timespan2);
	}	
	
	@Test
	public void testMain(){
		
		setUp2();
		
		
		
		ArrayList freePeople = new ArrayList<User>();
		freePeople = planApp.getAvailableUsers(timespan1);
		//Tester for, at der returneres alle med undtagelse af to planlagte
		assertEquals(planApp.getUsers.size()-2,freePeople.size());
		//Planlægger en tredje person
		planApp.getProjectByName("HalfLife3").getActivityByName("Engine").planWork("IsSt", timespan2);
		//Tester, at der er tre personer optaget i timespan2
		ArrayList freePeople2 = new ArrayList<User>();
		freePeople2 = planApp.getAvailableUsers(timespan2);
		assertEquals(planApp.getUsers.size()-3,freePeople2.size());
		}
	
	@Test
	public void testPlanBusy(){//Tjekker, at der smides en exception, hvis det forsøges at planlægge en allerede planlagt user. Exceptionnavn ligger ikke fast.
		setUp2();
		try {
			planApp.getProjectByName("HalfLife3").getActivityByName("Engine").planWork("DaSc", timespan2);
			fail();
		} catch (UserAlreadyPlannedException e){
			
		}
	
	}
	
	
}