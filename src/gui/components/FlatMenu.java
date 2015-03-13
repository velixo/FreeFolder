package gui.components;

import gui.GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public abstract class FlatMenu extends JMenu{
	private Color borderGray = GUI.BORDER_GRAY;
	private Color textGray = GUI.TEXT_GRAY;
	
	public FlatMenu(String title) {
		super(title);
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension size = super.getPreferredSize();
		size.height = 40;
		size.width = 160;
		return size;
	}
	
	protected void fixMenuStyling() {
		Border paddingBorder = BorderFactory.createEmptyBorder(0, 60, 0, 0);
		Border lineBorder = BorderFactory.createMatteBorder(0, 0, 0, 1, borderGray);
		CompoundBorder border = BorderFactory.createCompoundBorder(lineBorder, paddingBorder);
		setBorder(border);
		setForeground(textGray);
	}
}
