package main;

import exceptions.InputWrongFormatException;
import exceptions.NoSuchUserException;
import exceptions.OperationNotAllowedException;

/**
 * 
 * @author Mathias Gammelmark
 *
 */
public class AddProjectMenu extends Menu {

	int menuState = 0;
	String name = "";
	String errorString = "";
	String menuString = "";

	public AddProjectMenu(JFrameUserInterface UI) {
		super(UI);
		menuString = "Add Project Menu:\n"
				+ "  B = Back\n";
	}

	@Override
	public Menu readInput(String input) throws InputWrongFormatException {

		errorString = "";

		if (input.equalsIgnoreCase("b")) {
			return new MainMenu(UI);
		}

		if (menuState == 0) {
			name = input;
			menuState = 1;
			return this;
		}
		if (menuState == 1) {
			if (input.equalsIgnoreCase("Y")) {
				menuState = 2;
				return this;
			} else if (input.equalsIgnoreCase("N")) {
				menuState = 0;
				return this;
			}

		}
		if (menuState == 2) {
			try {
				User u = UI.planApp.getUserByInitials(input);
				UI.planApp.addProject(name, u.getInitials());
				menuState = 3;
				return this;
			} catch (NoSuchUserException e) {
				errorString = e.getMessage() + "\n";
			} catch (OperationNotAllowedException e) {
				errorString = e.getMessage() + "\n";
			}
			return this;
		}
		if (menuState == 3) {
			return new MainMenu(UI);
		}

		throw new InputWrongFormatException("Cant read string in addProjectMenu");
	}

	@Override
	public String toString() {
		if (menuState == 0) {
			return menuString
					+ "Enter the name of the project you want to add:\n";
		}

		if (menuState == 1) {
			return menuString
					+ "Are you sure you want to add a project by the name:\n"
					+ "\"" + name + "\""
					+ "\nY/N:\n";
		}

		if (menuState == 2) {
			return menuString
					+ "Enter the initials of the user, you want as project-leader:\n" + errorString + getUserList();
		}

		if (menuState == 3) {
			return "You have succesfuly added the project:\n"
					+ "\"" + name + "\""
					+ "\n\nPress Enter to continue...\n";
		}

		return "";
	}

	private String getUserList() {
		String returnString = "\n\nInitials | Name\n";

		for (User U : UI.planApp.getUsers()) {
			returnString += "  " + U.getInitials() + " | " + U.getName() + "\n";
		}
		return returnString;
	}
}
