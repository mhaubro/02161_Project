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

import exceptions.NoSuchUserException;
import exceptions.OperationNotAllowedException;
import exceptions.TimeSpanIsNotValidException;
import exceptions.UserAlreadyPlannedException;
public class Planning extends SampleDataSetup{
	
	Timespan timespan1;
	Timespan timespan2;
	Timespan timespan3;
	
	//Først testes, at den rette liste af uplanlagte medarbejdere dukker op
	//Dernæst planlægges en medarbejder
	//Dernæst tjekkes det, at denne er planlagt
	//Dernæst tjekkes det, at denne ikke kan planlægges igen.
	
	@Before
	public void setPlannedWorks(){
		

	}
	
	public void setUp2() throws NoSuchUserException{
		planApp.login("Admin");
		//Cals
		GregorianCalendar gregorianCalendar1 = new GregorianCalendar(2015, 2, 1, 9, 0);
		GregorianCalendar gregorianCalendar2 = new GregorianCalendar(2015, 2, 1, 14, 0);
		GregorianCalendar gregorianCalendar3 = new GregorianCalendar(2015, 2, 1, 12, 0);
		GregorianCalendar gregorianCalendar4 = new GregorianCalendar(2015, 2, 2, 13, 0);
		//TSpans
		Timespan timespan1 = new Timespan(gregorianCalendar1, gregorianCalendar2);
		Timespan timespan2 = new Timespan(gregorianCalendar3, gregorianCalendar2);
		Timespan timespan3 = new Timespan(gregorianCalendar3, gregorianCalendar4);
		//PlannedWork smækkes ind
		planApp.getProjectByName("HalfLife 3").getActivityByName("Engine").planWork("DaSc", timespan1);
		planApp.getProjectByName("HalfLife 3").getActivityByName("Engine").planWork("SaMu", timespan2);
		//planApp.logout();
	}	
	
	@Test
	public void testMain() throws NoSuchUserException{
		setUp2();
		planApp.login("ZaBe");
		
		
		ArrayList freePeople = new ArrayList<User>();
		freePeople = planApp.getAvailableUsers(timespan1);
		//Tester for, at der returneres alle med undtagelse af to planlagte
		assertEquals(planApp.getNumberOfEmployes()-2,freePeople.size());
		//Planlægger en tredje person
		planApp.getProjectByName("HalfLife3").getActivityByName("Engine").planWork("IsSt", timespan2);
		//Tester, at der er tre personer optaget i timespan2
		ArrayList freePeople2 = new ArrayList<User>();
		freePeople2 = planApp.getAvailableUsers(timespan2);
		assertEquals(planApp.getNumberOfEmployes()-3,freePeople2.size());
		//planApp.logout();
		}
	
	@Test
	public void userIsAdmin() throws NoSuchUserException{
		
		//Logger ind som admin
		planApp.login("Admin");
		//Planlægger
		planApp.getProjectByName("HalfLife3").getActivityByName("Engine").planWork("GaWo", timespan2);
		//Undersøger hvor mange der er ledige. Der er planlagt to i setUp2, og herved en 3.
		ArrayList freePeople = new ArrayList<User>();
		freePeople = planApp.getAvailableUsers(timespan1);
		assertEquals(planApp.getNumberOfEmployes()-3,freePeople.size());
		//planApp.logout();
	}
	
	@Test //Tester at user er projektleder
	public void UserIsNotProjectLeaderOrAdmin() throws NoSuchUserException{
		planApp.login("JeSm");
		try{
			planApp.getProjectByName("HalfLife3").getActivityByName("Engine").planWork("GaWo", timespan2);
		} catch (OperationNotAllowedException e){
			
		}
	}
	
	@Test //Tester at brugeren ikke er optaget
	public void testPlanBusy() throws NoSuchUserException{//Tjekker, at der smides en exception, hvis det forsøges at planlægge en allerede planlagt user. Exceptionnavn ligger ikke fast.
		setUp2();
		planApp.login("ZaBe");
		try {
			planApp.getProjectByName("HalfLife3").getActivityByName("Engine").planWork("DaSc", timespan2);
			fail();
		} catch (UserAlreadyPlannedException e){
			
		}
		planApp.logout();
	}
	
	@Test //Tester at der findes en sådan bruger
	public void testNoSuchUser() throws NoSuchUserException{
		setUp2();
		planApp.login("ZaBe");
		try {
			planApp.getProjectByName("HalfLife3").getActivityByName("Engine").planWork("AbAb", timespan2);
			fail();
		} catch (NoSuchUserException e){
			
		}
		planApp.logout();
	}
	@Test //Tester at timeSpan er gyldigt
	public void testTimeSpanIsValid() throws NoSuchUserException{
		setUp2();
		planApp.login("ZaBe");
		Timespan timespan4 = null;
		//Tjekker, at timespan-objektet ikke er null
		try {
			planApp.getProjectByName("HalfLife3").getActivityByName("Engine").planWork("DaSc", timespan4);
		} catch (TimeSpanIsNotValidException e){
			
		}
		//Tjekker, at det ikke er muligt at planlægge over 24 timer
		try {
			planApp.getProjectByName("HalfLife3").getActivityByName("Engine").planWork("DaSc", timespan3);

		} catch (TimeSpanIsNotValidException e){
			
		}
		planApp.logout();
	}
}