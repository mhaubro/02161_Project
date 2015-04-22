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


public class SwitchProjectLeader extends SampleDataSetup{
	
	@Test
	public void testMain(){
		assertEquals("MiNe", planApp.getProjectByName("Solstorm").getProjectLeader().getInitials());
		planApp.login("MiNe");
		planApp.getProjectByName("Solstorm").switchProjectLeader("JeSm");
		assertEquals("JeSm", planApp.getProjectByName("Solstorm").getProjectLeader().getInitials());
	}
	
	@Test
	public void testAdmin(){
		planApp.login("Admin");
		planApp.getProjectByName("Solstorm").switchProjectLeader("JeSm");
		assertEquals("JeSm", planApp.getProjectByName("Solstorm").getProjectLeader().getInitials());
	}
	
	@Test
	public void testAlternative(){
		planApp.login("SyRo");
		try {		
			planApp.getProjectByName("Solstorm").switchProjectLeader("JeSm");
			fail();
		} catch (OperationNotAllowedException e) {
			//assertEquals("Active user is not Project leader",e.message);
			
		}
	}
	
	@Test
	public void testNoUser(){
		planApp.login("MiNe");
		try{
			planApp.getProjectByName("Solstorm").switchProjectLeader("HaGr");
			fail();
		} catch (NoSuchUserException e){
			
		}
		
	}
	
}
