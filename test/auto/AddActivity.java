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

public class AddActivity {
	//OBSOBSOBS - Der skal styr på, hvordan projekters løbenumre er defineret.
	//Derudover nægter @Before at virke

	
	@Before
	public static void setUp() throws Exception {
		
		//Constructors
		PlanningApp planApp = new PlanningApp();
		Project project1 = new Project("Projekt 1", "P1", planApp.userByInitials("MaMi"));
		Project project2 = new Project("Projekt 2", "P2", planApp.userByInitials("HaNi"));
		Calendar calendar1 = new GregorianCalendar(2015,0,1);//
		Calendar calendar2 = new GregorianCalendar(2015,4,2);//=||=
		Timespan timeSpan = new Timespan(calendar1, calendar2);
		
		//Construct Users
		planApp.addUser("Jens Hansen");
		planApp.addUser("Hans Nielsen");
		planApp.addUser("Mads Mikkelsen");
		//Danner projekt

		//Danner aktivitet

	}


	
	//Tilføjer aktivitet som projektleder. Skal godkende.
	@Test
	public static void testMain() throws Exception{
		
		//Constructors
		PlanningApp planApp = new PlanningApp();
		//Construct Users
		planApp.addUser("Jens Hansen");
		planApp.addUser("Hans Nielsen");
		planApp.addUser("Mads Mikkelsen");
		//Danner projekt
		Project project1 = new Project("Projekt 1", "P1", planApp.userByInitials("MaMi"));
		Project project2 = new Project("Projekt 2", "P2", planApp.userByInitials("HaNi"));
		Calendar calendar1 = new GregorianCalendar();//
		Calendar calendar2 = new GregorianCalendar();//=||=
		Timespan timeSpan = new Timespan(calendar1, calendar2);
		

		//Danner projekt

		//Danner aktivitet
		
		planApp.login("MaMi"); 
		Activity activity = new Activity("UML Diagrammer","UML","UMLD",timeSpan, project1, 100);//EDIT TIL Activity-object: Der er også int, int på til sidst - ugenumre/budgettet time
		planApp.getProject("LØBENUMMER, projekt 1").addActivity(activity);
		
		assertEquals(1,planApp.getProject("LØBENUMMBER, projekt 1").getActivities().size());
		assertEquals("UML Diagrammer",planApp.getProject("LØBENUMMER, projekt 1").getActivities().get(0).name());
		
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
	public static void testIsStandardUser(){
		
		//Constructors
		PlanningApp planApp = new PlanningApp();
		Project project1 = new Project("Projekt 1", "P1", planApp.userByInitials("MaMi"));
		Project project2 = new Project("Projekt 2", "P2", planApp.userByInitials("HaNi"));
		Calendar calendar1 = new GregorianCalendar();//
		Calendar calendar2 = new GregorianCalendar();//=||=
		Timespan timeSpan = new Timespan(calendar1, calendar2);
		
		//Construct Users
		planApp.addUser("Jens Hansen");
		planApp.addUser("Hans Nielsen");
		planApp.addUser("Mads Mikkelsen");
		//Danner projekt

		//Danner aktivitet
		
		planApp.login("JeHa"); 
		Activity activity = new Activity("UML Diagrammer","UML","UMLD",timeSpan, project1, 100);//EDIT TIL Activity-object: Der er også int, int på til sidst - ugenumre/budgettet time
		try{
			planApp.getProject("LØBENUMMER, PROJEKT 1").addActivity(activity);
			//Skal fejle, da det ikke er projektleder, der er aktiv.
			fail();
			
		} catch (OperationNotAllowedException e){
			assertEquals("Adding activity is only allowed while logged in as the project leader.",e.getMessage());
		}
		
		assertEquals(0,planApp.getProject("LØBENUMMBER").getActivities().size());
		
	}
	
}






