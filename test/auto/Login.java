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

import exceptions.NoSuchUserException;

/**
 * 
 * @author Martin Haubro
 *
 */
public class Login extends SampleDataSetup {
	
	@Test

	public void main()throws Exception{//Gyldig bruger
		planApp.login("GaWo");
		assertTrue(planApp.getActiveUser().getInitials().equalsIgnoreCase("GaWo"));
		assertFalse(planApp.isSuperByInitials(planApp.getActiveUser().getInitials()));
		planApp.logout();
		assertNull(planApp.getActiveUser());
	}
	
	@Test
	public void admin() throws Exception{//Admin
		planApp.login("Admin");
		assertTrue(planApp.getActiveUser().getInitials().equalsIgnoreCase("Admin"));
		assertTrue(planApp.isSuperByInitials(planApp.getActiveUser().getInitials()));
	}
	
	@Test
	public void alternative(){//Ikke-eksisterende bruger
		try{
			planApp.login("ZaZa");
			fail("cant login as a user that does not exist");
		} catch (NoSuchUserException e){
			//assertEquals("No such user in program.",e.message());
		}
	}	
}
