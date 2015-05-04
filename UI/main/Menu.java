package main;

import exceptions.InputWrongFormatException;

public abstract class Menu {
	
	protected JFrameUserInterface UI;
	
	public Menu(JFrameUserInterface UI){
		this.UI = UI;
	}
	
	public abstract Menu readInput(String input) throws InputWrongFormatException;
	
	public abstract String toString();

}
