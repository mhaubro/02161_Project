package main;

import java.util.GregorianCalendar;

import exceptions.InputWrongFormatException;
import exceptions.OperationNotAllowedException;

public class AddActivityMenu extends Menu {

	int menuState = 0;
	String name = "";
	String description = "";

	String errorString = "";
	String menuString = "";

	Project P = null;
	Timespan T = null;

	public AddActivityMenu(JFrameUserInterface UI) {
		super(UI);
		menuString = "Add Activity Menu:\n"
				+ "  B = Back\n";
	}

	@Override
	public Menu readInput(String input) throws InputWrongFormatException {
		errorString = "";

		if (input.equalsIgnoreCase("b")) {
			return new MainMenu(UI);
		}

		if (menuState == 0) {
			P = UI.planApp.getProjectByName(input);
			if (P == null) {
				errorString = "Could not find project by the name:\n" + input + "\n";
			} else {
				menuState = 1;
			}
			return this;
		}

		if (menuState == 1) {
			name = input;
			menuState = 2;
			return this;
		}

		if (menuState == 2) {
			if (input.equalsIgnoreCase("Y")) {
				menuState = 3;
				return this;
			} else if (input.equalsIgnoreCase("N")) {
				menuState = 1;
				return this;
			}

		}

		if (menuState == 3) {
			description = input;
			menuState = 4;
			return this;
		}

		if (menuState == 4) {
			try {
				T = getTimespan(input);
				menuState = 5;
				return this;
			} catch (OperationNotAllowedException e) {
				errorString = e.getLocalizedMessage() + "\n";
				return this;
			}
		}

		if (menuState == 5) {
			int B = Integer.parseInt(input);
			if (B == 0) {
				errorString = "cant assign zero budgetted hours...\n";
				return this;
			}
			try {
				UI.planApp.addActivity(name, description, T, B);
				menuState = 6;
				return this;
			} catch (OperationNotAllowedException e) {
				errorString = e.getMessage() + "\n";
				menuState = 0;
				return this;
			}
		}

		if (menuState == 6) {
			return new MainMenu(UI);
		}

		throw new InputWrongFormatException("Cant read input in add activity menu");
	}

	@Override
	public String toString() {
		if (menuState == 0) {
			return menuString
					+ "Enter the name of the project you want to add an activity to:\n"
					+ getProjectList();
		}

		if (menuState == 1) {
			return menuString
					+ "Enter the name of the new Activity:\n";
		}

		if (menuState == 2) {
			return menuString
					+ "Are you sure you want to add the activity by the name:\n"
					+ "\"" + name + "\"\n"
					+ "Y/N:\n";
		}

		if (menuState == 3) {
			return menuString
					+ "Enter a description of the activity:\n"
					+ "Or dont, if you dosnt want to...";
		}

		if (menuState == 4) {
			return menuString
					+ "Enter a data with the format:\nYear Month Day Hour Minute Year Month Day Hour Minute";
		}

		if (menuState == 5) {
			return menuString
					+ "Enter an integer, \nThat indicates the expected budgetted time in hours:";
		}

		if (menuState == 6) {
			return "CONGRADS!!!\n\n"
					+ "YOU have now added the activity:\n"
					+ name + "\n"
					+ "In the project:\n"
					+ P.getName() + "\n\n"
					+ "The fancy description you have given this glorius activity is:\n\""
					+ description + "\"\n\n"
					+ "Press Enter to continue...";

		}

		menuState = 0;
		return "";
	}

	private String getProjectList() {
		String returnString = "\n\nID | Name\n";

		for (Project P : UI.planApp.getAllProjects()) {
			returnString += "  " + P.getID() + " | " + P.getName() + "\n";
		}

		return returnString;
	}

	private Timespan getTimespan(String input) throws InputWrongFormatException, OperationNotAllowedException {

		String[] dateString = input.split(" ");

		try {
			Timespan t;
			t = new Timespan(
					new GregorianCalendar(
							Integer.parseInt(dateString[0]),
							Integer.parseInt(dateString[1]),
							Integer.parseInt(dateString[2]),
							Integer.parseInt(dateString[3]),
							Integer.parseInt(dateString[4])),
					new GregorianCalendar(
							Integer.parseInt(dateString[5]),
							Integer.parseInt(dateString[6]),
							Integer.parseInt(dateString[7]),
							Integer.parseInt(dateString[8]),
							Integer.parseInt(dateString[9])));
			return t;
		} catch (NumberFormatException e) {
			throw new InputWrongFormatException("wrong input format");
		}
	}

}
