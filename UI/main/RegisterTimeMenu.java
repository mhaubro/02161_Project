package main;

import exceptions.InputWrongFormatException;

public class RegisterTimeMenu extends Menu {

	String menuString;

	int listState = 0;

	public RegisterTimeMenu(JFrameUserInterface UI) {
		super(UI);

		menuString = "Register Time Menu:\n"
				+ "  B = Back\n"
				+ "  L = List Your Activities\n"
				+ " LA = List All Available Activities\n"
				+ "  C = clear List\n"
				+ "Write the initials of the activity,\nyou want to register time for\n";
	}

	@Override
	public Menu readInput(String input) throws InputWrongFormatException {
		if (input.equalsIgnoreCase("b")) {
			return new MainMenu(UI);
		}
		if (input.equalsIgnoreCase("l")) {
			listState = 1;
			return this;
		}
		if (input.equalsIgnoreCase("la")) {
			listState = 2;
			return this;
		}
		if (input.equalsIgnoreCase("c")) {
			listState = 0;
			return this;
		}
		throw new InputWrongFormatException("The input was not recognized");
	}

	@Override
	public String toString() {

		switch (listState) {
		case 0:
			return menuString;
		case 1:
			return menuString + getUsersActivities();
		case 2:
			return menuString + getAllActivities();

		default:
			listState = 0;
			return menuString;
		}
	}

	private String getAllActivities() {
		String returnString = "\n\nNum | Name\n";

		int i = 0;
		for (Activity A : UI.planApp.getAllActivities()) {
			returnString += " " + i + " | " + A.getName();
		}
		return returnString;
	}

	private String getUsersActivities() {
		String returnString = "\n\nNum | Name\n";

		int i = 0;
		for (Activity A : UI.planApp.getActiveUser().getActivities()) {
			returnString += " " + i + " | " + A.getName();
		}

		return returnString;
	}

}
