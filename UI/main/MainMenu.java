package main;

import exceptions.InputWrongFormatException;

public class MainMenu extends Menu {

	String menuString = "";

	public MainMenu(JFrameUserInterface UI) {
		super(UI);
		
		menuString += "Main Menu:\n";
		
		if (UI.planApp.getActiveUser() != null) {
			menuString += "Active User: " + UI.planApp.getActiveUser().getName() + "\n";
		}

		menuString += ("" +
				"Write a number for matching function:\n"+
				"  1 = Login\n" +
				"  2 = Logout\n" +
				"  E = Exit\n");
	}

	@Override
	public Menu readInput(String input) throws InputWrongFormatException {
		if (input.equalsIgnoreCase("e")) {
			return null;
		}
		if (input.equalsIgnoreCase("1")) {
			return new LoginMenu(UI);
		}
		if (input.equalsIgnoreCase("2")) {
			return new LogoutMenu(UI);
		}
		throw new InputWrongFormatException("The input was not recognized");
	}

	@Override
	public String toString() {
		return menuString;
	}
}
