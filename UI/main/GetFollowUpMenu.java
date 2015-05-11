package main;

import exceptions.InputWrongFormatException;

/**
 * 
 * @author Mathias Gammelmark
 *
 */
public class GetFollowUpMenu extends Menu {
	
	String menuString = "";
	String errorString = "";
	
	String followUp = "";
	
	int menuState = 0;
	
	Project P = null;

	public GetFollowUpMenu(JFrameUserInterface UI) {
		super(UI);
		
		menuString = "FollowUp Menu:\n"
				+ "  B = Back\n";
	}

	@Override
	public Menu readInput(String input) throws InputWrongFormatException {
		
		errorString = "";
		
		if (input.equalsIgnoreCase("B")){
			return new MainMenu(UI);
		}

		if (menuState == 0){
			P = UI.planApp.getProjectByName(input);
			if (P == null){
				errorString = "Cant find a project by the name:n"
						+"\"" + input + "\"\n";
				return this;
			}
			menuState = 1;
			return this;
		}
		
		if (menuState == 1){
			if (input.equalsIgnoreCase("1")){
				followUp = P.getFollowup();
				menuState = 2;
				return this;
			}
			if (input.equalsIgnoreCase("2")){
				followUp = P.getBigFollowup();
				menuState = 2;
				return this;
			}
		}
		
		if (menuState == 2){
			return new MainMenu(UI);
		}
		
		menuState = 0;
		throw new InputWrongFormatException("Cant read input in FollowUp Menu");
	}

	@Override
	public String toString() {

		if (menuState == 0){
			return menuString
					+ "Enter the name of the project you want a followup from:\n" + errorString + getProjectList();
		}
		
		if (menuState == 1){
			return menuString
					+ "  1 = FollowUp\n"
					+ "  2 = Big FollowUp\n";
		}
		
		if (menuState == 2){
			return menuString
					+ followUp 
					+"\n\nPress Enter to continue...";
		}
		
		return "";
	}
	
	private String getProjectList() {
		String returnString = "\n\nID | Name\n";

		for (Project P : UI.planApp.getAllProjects()) {
			returnString += "  " + P.getID() + " | " + P.getName() + "\n";
		}

		return returnString;
	}

}
