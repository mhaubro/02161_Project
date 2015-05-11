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
				"Write a number for matching function:\n" +
				"  1 = Login\n" +
				"  2 = Logout\n" +
				"  3 = Register Time\n" +
				"  4 = Plan Work\n" +
				"  5 = Add Project\n" +
				"  6 = Add Activity\n" +
				"  7 = Add User\n" +
				"  8 = FollowUp\n" +
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
		if (input.equalsIgnoreCase("3")) {
			return new RegisterTimeMenu(UI);
		}
		if (input.equalsIgnoreCase("4")) {
			return new PlanWorkMenu(UI);
		}
		if (input.equalsIgnoreCase("5")) {
			return new AddProjectMenu(UI);
		}
		if (input.equalsIgnoreCase("6")) {
			return new AddActivityMenu(UI);
		}
		if (input.equalsIgnoreCase("7")) {
			return new AddUserMenu(UI);
		}
		if (input.equalsIgnoreCase("8")){
			return new GetFollowUpMenu(UI);
		}
		throw new InputWrongFormatException("The input was not recognized");
	}

	@Override
	public String toString() {
		return menuString;
	}
}
