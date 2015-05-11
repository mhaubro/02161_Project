package main;

import exceptions.InputWrongFormatException;
import exceptions.NoSuchUserException;

/**
 * 
 * @author Mathias Gammelmark
 *
 */
public class LoginMenu extends Menu {

	String menuString = "";
	String printString = "";

	private boolean printUsers = false;

	int state = 0;

	public LoginMenu(JFrameUserInterface UI) {
		super(UI);

		menuString += "Login Menu:\n" +
				"Write your initials for login\n" +
				"L = toogle list\n" +
				"B = back\n";

		printString = menuString;
	}

	@Override
	public Menu readInput(String input) throws InputWrongFormatException {

		if (state == 0) {
			if (input.equalsIgnoreCase("b"))
				return new MainMenu(UI);
			if (input.equalsIgnoreCase("l")) {
				if (printUsers) {
					printUsers = false;
				} else {
					printUsers = true;
				}
				return this;
			}
			try {
				UI.planApp.login(input);
				printString = menuString + "  You are now logged in as: " + UI.planApp.getActiveUser().getName()
						+ "\nPress enter to continue\n";
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
		if (printUsers) {
			return printString + getUsers();
		} else {
			return printString;
		}

	}

	private String getUsers() {
		String users = "\n\nInitials | Name\n" +
				"   Admin | Admin Administrator\n";

		for (User u : UI.planApp.getUsers()) {
			users += "    " + u.getInitials() + " | " + u.getName() + "\n";
		}

		return users;
	}

}
