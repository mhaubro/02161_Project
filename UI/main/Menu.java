package main;

import exceptions.InputWrongFormatException;

/**
 * 
 * @author Mathias Gammelmark
 *
 */
public abstract class Menu {
	
	protected JFrameUserInterface UI;
	
	public Menu(JFrameUserInterface UI){
		this.UI = UI;
	}
	
	public abstract Menu readInput(String input) throws InputWrongFormatException;
	
	public abstract String toString();

}
