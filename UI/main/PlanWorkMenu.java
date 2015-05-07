package main;

import java.util.GregorianCalendar;

import exceptions.InputWrongFormatException;
import exceptions.NoSuchUserException;
import exceptions.OperationNotAllowedException;
import exceptions.TimeSpanIsNotValidException;
import exceptions.UserAlreadyPlannedException;

public class PlanWorkMenu extends Menu {

	String menuString = "";
	String errorString = "";

	int menuState = 0;

	Timespan timespan = null;
	User user = null;
	Activity activity = null;

	public PlanWorkMenu(JFrameUserInterface UI) {
		super(UI);

		menuString = "Plan Work Menu\n"
				+ "B = Back\n";

	}

	@Override
	public Menu readInput(String input) throws InputWrongFormatException {
		errorString = "";
		if (input.equalsIgnoreCase("b")) {
			return new MainMenu(UI);
		}

		if (menuState == 0) {
			try {
				timespan = getTimespan(input);
				menuState = 1;
				return this;
			} catch (OperationNotAllowedException e) {
				errorString = e.getMessage();
				return this;
			}
		} else if (menuState == 1) {
			try {
				user = UI.planApp.getUserByInitials(input);
				menuState = 2;
				return this;
			} catch (NoSuchUserException e) {
				errorString = e.getMessage();
				return this;
			}

		} else if (menuState == 2) {
			try {
				activity = UI.planApp.getActivityByName(input);
				activity.planWork(user.getInitials(), timespan);
				menuState = 3;
				return this;
			} catch (NoSuchUserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OperationNotAllowedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UserAlreadyPlannedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeSpanIsNotValidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				errorString = "could not regignize the activity";
			}
		} else {
			return new MainMenu(UI);
		}

		throw new InputWrongFormatException("Cant reconize input");
	}

	@Override
	public String toString() {

		switch (menuState) {
		case 0:
			return menuString + "\n"
					+ "Enter the timespan you want to plan work for:\n"
					+ "Format: Year Month Day Hour Minute Year Month Day Hour Minute\n" + errorString + getListString();

		case 1:
			return menuString + "\n"
					+ "Enter the initials of the user you wish to plan work for:\n" + getListString();
		case 2:
			return menuString + "\n"
					+ "Enter the name of the activity you wish to plan the user for:\n" + getActivities();
		case 3:
			return menuString + "\nYou have now planned work for the user: " + user.getName() + "\n"
					+ "In the activity: " + activity.getName() + "\n"
					+ "In the timespan: " + timespan + "\n";
		default:
			menuState = 0;
			return menuString;
		}
	}

	private String getListString() {
		if (timespan == null)
			return "";

		String returnString = "";
		for (User U : UI.planApp.getAvailableUsers(timespan)) {
			returnString += "  " + U.getInitials() + " | " + U.getName() + "\n";
		}
		return returnString;
	}
	
	private String getActivities(){
		String returnString = "\n\nNum | Name\n";

		int i = 0;
		for (Activity A : UI.planApp.getAllActivities()) {
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
