package auto;

import static org.junit.Assert.*;
import main.PlanningApp;
import main.Project;
import main.User;

import org.junit.Before;
import org.junit.Test;

import exceptions.NoSuchUserException;
import exceptions.OperationNotAllowedException;

public class Followup extends SampleDataSetupFollowUp{

	@Test
	public void testMainScenario() throws NoSuchUserException {
		planApp.login("ZaBe");
		String followup = planApp.getProjectByName("HalfLife 3").getFollowup();
		//Building expected string with lines:
		String str1 = "FollowUp on project HalfLife 3\n";
		String str2 = "Project Leader: Zachariah Beckstead\n";
		String str3 = "\n";
		String str4 = "Activities and their states:\n";
		String str5 = "Activity\tBudgettet Time\tPlanned Time\tRegistered Time\tRegistered/Planned\n";
		//Vides det hvilken string der kommer først?
		String str6 = "Engine\n\t\t20\t\t20\t\t16\t\t0.8\n";
		String str7 = "Design\n\t\t25\t\t20\t\t20\t\t0.8\n";
		String str8 = "\n";
		String str9 = "Amount of work done relative to budgettet time: 80 %\n";
		String expectedSmallFollowup = str1 + str2 + str3 + str4 + str5 + str6 + str7 + str8 + str9;
		//Bør måske lave det til equalsignorecase for fejltjek. En anden metode kunne være at dele op i substrings og tjekke disse.
		assertTrue(followup.equals(expectedSmallFollowup));
		String bigFollowup = planApp.getProjectByName("HalfLife 3").getBigFollowup();
		String str10 = "\n";
		String str11 = "Employees on the project\n";
		//Skal der vises noget om planlagt tid før/efter nuværende dato? - Det skal vurderes. Derudover skal man inkludere projektleder-tab?
		//Hvilke rækkefølge kommer string 13, 14 og 15 i?!
		String str12 = "User\tPlanned time\tRegistered time\n";
		String str13 = "ZaBe\t8\t\t4\n";
		String str14 = "DaSc\t24\t\t24\n";
		String str15 = "GaWo\t8\t\t8\n";
		String exceptedBigFollowup = expectedSmallFollowup + str10 + str11 + str12 + str13 + str14 + str15;
		//Igen, equalsIgnoreCase?
		assertTrue(bigFollowup.equals(exceptedBigFollowup));
		//System.out.println(exceptedBigFollowup);
	}

//	@Test //Skal det være muligt som projektleder? - Valgt ja!
//	public void testNotProjectLeader() throws NoSuchUserException{
//		planApp.login("DaSc");
//		try {
//			String followUp = planApp.getProjectByName("HalfLife 3").getFollowup();
//			fail();
//		} catch (OperationNotAllowedException e){
//			
//		}
//	}
	
	@Test
	public void testNoSuchProject() throws NoSuchUserException{
		planApp.login("ZaBe");
		try {
			String followUp = planApp.getProjectByName("HalfLIFEEEEE 3").getFollowup();
			fail();
		} catch (NoSuchProjecException e){
			
		}
	}
	
}
