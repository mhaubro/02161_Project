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

public class SuperUserTest extends SampleDataSetup{
	
	
	@Test
	public void testMain() throws NoSuchUserException  {
		planApp.login("Admin");
		assertTrue(planApp.isSuperByInitials("Admin"));
		assertTrue(planApp.isSuperByInitials("MaGa"));
		assertFalse(planApp.isSuperByInitials("DaSc"));
	}
	
	@Test
	public void testAdd() throws NoSuchUserException, OperationNotAllowedException{
		planApp.login("Admin");
		planApp.makeSuper("GaWo");
		assertTrue(planApp.isSuperByInitials("GaWo"));
	}
	
	@Test
	public void noSuchUser() throws NoSuchUserException, OperationNotAllowedException{
		planApp.login("Admin");
		try {
			planApp.makeSuper("HKH Dronning MARGRETHE");
			fail();
		} catch (NoSuchUserException e){
			
		}
	}
	
	@Test
	public void notSuperAdder() throws NoSuchUserException{
		planApp.login("ZaBe");
		try {
			planApp.makeSuper("HKH Dronning MARGRETHE");
			fail();
		} catch (OperationNotAllowedException e){
			
		}
	}
	
	@Test
	public void removeUser() throws NoSuchUserException, OperationNotAllowedException{
		planApp.login("Admin");
		planApp.removeUserByInitials("GaWo");
		try {
			User u = planApp.getUserByInitials("GaWo");
			fail();
		} catch (NoSuchUserException e){
			
		}
	}
	
	@Test
	public void removeUserNotSuper() throws NoSuchUserException{
		planApp.login("DaSc");
		try {
			planApp.removeUserByInitials("GaWo");
			fail();
		} catch (OperationNotAllowedException e){
			
		}
	}
	
	@Test
	public void userIsDeleted() throws NoSuchUserException, OperationNotAllowedException{
		planApp.login("Admin");
		planApp.removeUserByInitials("MaGa");
		assertFalse(planApp.isSuperByInitials("MaGa"));
	}
	
	@Test
	public void superRemoveByNotSuper() throws NoSuchUserException{
		planApp.login("GaWo");
		try {
			planApp.revokeSuper("MaGa");
			fail();
		} catch (OperationNotAllowedException e){
			
		}
	}
	
}
