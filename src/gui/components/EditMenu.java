package gui.components;

import gui.GUI;

import java.awt.Color;

public class EditMenu extends FlatMenu {
	private static final long serialVersionUID = -3419150312760197669L;
	private Color borderGray = GUI.BORDER_GRAY;
	
	public EditMenu() {
		super("EDIT");
		fixMenuStyling();
	}
}
