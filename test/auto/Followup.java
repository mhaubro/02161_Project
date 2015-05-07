package auto;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import main.PlanningApp;
import main.Project;
import main.Timespan;
import main.User;
import exceptions.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.NoSuchUserException;
import exceptions.OperationNotAllowedException;
import exceptions.OverlapException;
import exceptions.TimeSpanIsNotValidException;
import exceptions.UserAlreadyPlannedException;

public class Followup extends SampleDataSetupFollowUp{

	@Test
	public void testMainScenario() throws NoSuchUserException {
		planApp.login("ZaBe");
		String followup = planApp.getProjectByName("HalfLife 3").getFollowup();
		//Building expected string with lines:
		String str1 = "FollowUp on project: HalfLife 3\n";
		String str2 = "Project Leader: Zachariah Beckstead\n";
		String str3 = "\n";
		String str4 = "Activities and their states:\n";
		String str5 = "Activity\tBudgettet Time\tPlanned Time\tRegistered Time\tRegistered/Planned\tBudgettet - Registered\n";
		//Vides det hvilken string der kommer først?
		String str6 = "Engine\n\t\t20\t\t20.0\t\t16.0\t\t0.8\t\t4.0\n";
		String str7 = "Design\n\t\t25\t\t20.0\t\t20.0\t\t1.0\t\t5.0\n";
		String str8 = "\n";
		String str9 = "Amount of work done relative to budgettet time: 80 %\n";
		String str10 = "Expected workhours left on project: 9.0\n";
		String expectedSmallFollowup = str1 + str2 + str3 + str4 + str5 + str6 + str7 + str8 + str9 + str10;
		assertEquals(expectedSmallFollowup, followup);
	}
	
	@Test
	public void testBigFollowup() throws NoSuchUserException{
		planApp.login("ZaBe");
		String followup = planApp.getProjectByName("HalfLife 3").getFollowup();
		String bigFollowup = planApp.getProjectByName("HalfLife 3").getBigFollowup();
		String str11 = "\n";
		String str12 = "Employees on the project\n";
		//Skal der vises noget om planlagt tid før/efter nuværende dato? - Det skal vurderes. Derudover skal man inkludere projektleder-tab?
		//Hvilke rækkefølge kommer string 13, 14 og 15 i?!
		String str13 = "User\tPlanned time\tRegistered time\n";
		String str14 = "DaSc\t24.0\t\t24.0\n";
		String str15 = "GaWo\t8.0\t\t8.0\n";
		String str16 = "ZaBe\t8.0\t\t4.0\n";
		String exceptedBigFollowup = followup + str11 + str12 + str13 + str14 + str15 + str16;
		//Igen, equalsIgnoreCase?
		assertEquals(exceptedBigFollowup, bigFollowup);
		//System.out.println(exceptedBigFollowup);
	}
	
	@Test
	public void activityBreaksBudgettetTime() throws NoSuchUserException, OperationNotAllowedException, OverlapException, UserAlreadyPlannedException, TimeSpanIsNotValidException{
		planApp.login("ZaBe");
		//Registrerer ZaBe
		planApp.getProjectByName("HalfLife 3").getActivityByName("Engine")
		.registreTime(new Timespan(
				new GregorianCalendar(2015, 6, 1, 8, 0), 
				new GregorianCalendar(2015, 6, 1, 18, 0)), "This is some serius Engine work...");
		
		planApp.getProjectByName("HalfLife 3").getActivityByName("Engine").planWork("ZaBe", new Timespan(
				new GregorianCalendar(2015, 6, 1, 8, 0), 
				new GregorianCalendar(2015, 6, 1, 18, 0)));
		
		String followup = planApp.getProjectByName("HalfLife 3").getFollowup();
		//Building expected string with lines:
		String str1 = "FollowUp on project: HalfLife 3\n";
		String str2 = "Project Leader: Zachariah Beckstead\n";
		String str3 = "\n";
		String str4 = "Activities and their states:\n";
		String str5 = "Activity\tBudgettet Time\tPlanned Time\tRegistered Time\tRegistered/Planned\tBudgettet - Registered\n";
		//Vides det hvilken string der kommer først?
		String str6 = "Engine\n\t\t20\t\t30.0\t\t26.0\t\t0.87\t\t-6.0\n";
		String str7 = "Design\n\t\t25\t\t20.0\t\t20.0\t\t1.0\t\t5.0\n";
		String str8 = "\n";
		String str9 = "Amount of work done relative to budgettet time: 102 %\n";
		String str10 = "Expected workhours left on project: 5.0\n";
		String expectedSmallFollowup = str1 + str2 + str3 + str4 + str5 + str6 + str7 + str8 + str9 + str10;
		assertEquals(expectedSmallFollowup, followup);
	}

}
