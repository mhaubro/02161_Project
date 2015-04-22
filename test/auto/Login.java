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

public class Login extends SampleDataSetup {
	
	@Test
<<<<<<< HEAD
	public void main() throws Exception{//Gyldig bruger
		planApp.login("GaWo");
		assertEquals(0, planApp.getActiveUser().compareTo(planApp.getUserByInitials("GaWo")));
		assertFalse(planApp.isSuperByInitials(planApp.getActiveUser().getInitials()));
=======
	public void main(){//Gyldig bruger
		planApp.logIn("GaWo");
		assertEquals(0, planApp.activeUser.compareTo(planApp.getUserByInitials("GaWo")));
		assertFalse(planApp.isSuperUserByInitials(planApp.activeUser.getInitials()));
		planApp.logout();
		assertNull(planApp.activeUser);
>>>>>>> branch 'master' of https://github.com/mhaubro/02161_Project.git
	}
	
	@Test
	public void admin() throws Exception{//Admin
		planApp.login("Admin");
		assertEquals(0, planApp.getActiveUser().compareTo(planApp.getUserByInitials("Admin")));
		assertTrue(planApp.isSuperByInitials(planApp.getActiveUser().getInitials()));
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
