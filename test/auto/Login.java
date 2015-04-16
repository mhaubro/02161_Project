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

public class Login extends SampleDataSetup {
	
	@Test
	public void main(){//Gyldig bruger
		planApp.logIn("GaWo");
		assertEquals(0, planApp.activeUser.compareTo(planApp.getUserByInitials("GaWo")));
		assertFalse(planApp.isSuperUserByInitials(planApp.activeUser.getInitials()));
	}
	
	@Test
	public void admin(){//Admin
		planApp.logIn("Admin");
		assertEquals(0, planApp.activeUser.compareTo(planApp.getUserByInitials("Admin")));
		assertTrue(planApp.isSuperUserByInitials(planApp.activeUser.getInitials()));
	}
	
	@Test
	public void alternative(){//Ikke-eksisterende bruger
		try{
			planApp.login("ZaZa");
			fail();
		} catch (NoSuchUserException e){
			//assertEquals("No such user in program.",e.message());
		}
	}
	
	
}
