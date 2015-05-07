package main;

import java.util.GregorianCalendar;

import exceptions.InputWrongFormatException;
import exceptions.OperationNotAllowedException;
import exceptions.OverlapException;

public class RegisterTimeMenu extends Menu {

	String menuString;
	String errorString = "";

	int listState = 0;
	int menuState = 0;

	Activity activity = null;

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
		errorString = "";
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

		switch (menuState) {
		case 0:
			activity = UI.planApp.getActivityByName(input);
			if (activity == null) {
				throw new InputWrongFormatException("The input was not recognized");
			} else {
				menuState = 1;
			}
			return this;

		case 1:
			if (activity == null) {
				menuState = 0;
				return readInput(input);
			}
			try {
				Timespan t = getTimespan(input);
				activity.registreTime(t, "");
				errorString = "You have now registert time in the activity: " + activity.getName() + "\nIn the timespan: " + t;
				menuState = 2;
				return this;
			} catch (OperationNotAllowedException e) {
				errorString = e.getMessage();
			} catch (OverlapException e) {
				errorString = e.getMessage();
			}
			return this;

		default:
			return new MainMenu(UI);
		}
	}

	@Override
	public String toString() {

		if (menuState == 0) {
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
		}else{
			return menuString + "\nYou hava choson the activity:\n" + activity.getName() +"\nEnter a data with the format:\nYear Month Day Hour Minute Year Month Day Hour Minute\n\n" + errorString;
		}
	}

	private String getAllActivities() {
		String returnString = "\n\nNum | Name\n";

		int i = 0;
		for (Activity A : UI.planApp.getAllActivities()) {
			returnString += " " + i++ + " | " + A.getName() + "\n";
		}
		return returnString;
	}

	private String getUsersActivities() {
		String returnString = "\n\nNum | Name\n";

		int i = 0;
		for (Activity A : UI.planApp.getActiveUser().getActivities()) {
			returnString += " " + i++ + " | " + A.getName() + "\n";
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
