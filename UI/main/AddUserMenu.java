package main;

import exceptions.InputWrongFormatException;

public class AddUserMenu extends Menu {
	
	int menuState = 0;
	String name = "";
	
	String menuString = "";
	String errorString = "";

	public AddUserMenu(JFrameUserInterface UI) {
		super(UI);
		
		menuString = "Add User Menu:\n"
				+ "  B = Back\n";
	}

	@Override
	public Menu readInput(String input) throws InputWrongFormatException {
		
		errorString = "";

		if (input.equalsIgnoreCase("b")) {
			return new MainMenu(UI);
		}
		
		if (menuState == 0){
			name = input;
			if (name.isEmpty()){
				errorString = "Cant add a user without a name\n";
				return this;
			}
			UI.planApp.addUser(name);
			menuState = 1;
			return this;
		}
		
		if (menuState == 1){
			return new MainMenu(UI);
		}
		
		throw new InputWrongFormatException("Cant read input in add user menu");
	}

	@Override
	public String toString() {

		if (menuState == 0){
			return menuString
					+ "Enter the name of the new User:\n" + errorString;
		}
		
		if (menuState == 1){
			return "You have now add a user with the name:\n"
					+ "\"" + name + "\"\n";
		}
		
		return "";
	}

}
