package main;

import java.util.Observable;

import exceptions.InputWrongFormatException;

/**
 * 
 * @author Mathias Gammelmark
 *
 */
public class PlanAppUserInterfaceClean extends JFrameUserInterface {

	Menu activeMenu;

	String lastInput = "";

	public PlanAppUserInterfaceClean(PlanningApp planApp) {
		super(planApp);
		activeMenu = new MainMenu(this);
		printMenu();
	}

	@Override
	public void readInput(String inputString) {
		try {
			activeMenu = activeMenu.readInput(inputString);
			if (activeMenu == null)
				System.exit(0);

			printMenu();

		} catch (InputWrongFormatException e) {
			output.setText(activeMenu + "Could not recognize the input: " + inputString + "\n");
		}
	}

	private void printMenu() {
		output.setText(activeMenu.toString());
	}

	public static void main(String[] args) {
		PlanningApp planApp = new PlanningApp();
		JFrameUserInterface UI = new PlanAppUserInterface(planApp);
	}

	@Override
	public void update(Observable o, Object arg) {
		super.update(o, arg);

		printMenu();
	}

}
