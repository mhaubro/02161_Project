package main;

import exceptions.InputWrongFormatException;

public class LogoutMenu extends Menu {
	
	String menuString = "";

	public LogoutMenu(JFrameUserInterface UI) {
		super(UI);
		
		menuString += "Logout Menu:\n"
				+ "Are you sure you want to logout\n"
				+ "Y = Yes\n"
				+ "N = No\n";
	}

	@Override
	public Menu readInput(String input) throws InputWrongFormatException {
		if (input.equalsIgnoreCase("y")){
			UI.planApp.logout();
			return new MainMenu(UI);
		}
		if (input.equalsIgnoreCase("n")){
			return new MainMenu(UI);
		}
		throw new InputWrongFormatException("wrong format in logoutMenu");
	}

	@Override
	public String toString() {
		return menuString;
	}

}
