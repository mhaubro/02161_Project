package main;

import exceptions.InputWrongFormatException;
import exceptions.NoSuchUserException;

public class LoginMenu extends Menu {

	String menuString = "";
	String printString = "";

	int state = 0;

	public LoginMenu(JFrameUserInterface UI) {
		super(UI);

		menuString += "Login Menu:\n" +
				"Write your initials for login\n" +
				"L = list\n" +
				"B = back\n";
		
		printString = menuString;
	}

	@Override
	public Menu readInput(String input) throws InputWrongFormatException {

		if (state == 0) {
			if (input.equalsIgnoreCase("b"))
				return new MainMenu(UI);
			try {
				UI.planApp.login(input);
				printString = menuString + "  You are now logged in as: " + UI.planApp.getActiveUser().getName()
						+ "\nPress enter to continue";
				state = 1;
			} catch (NoSuchUserException e) {
				printString = menuString +
						"  Cant find a user to match the initials: " + input + "\n";
			}
			return this;
		}
		if (state == 1) {
			return new MainMenu(UI);
		}
		return null;
	}

	@Override
	public String toString() {
		return printString;
	}

}
