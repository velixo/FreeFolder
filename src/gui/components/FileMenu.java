package gui.components;

import gui.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class FileMenu extends JMenu {
	private static final long serialVersionUID = -3641031798577935262L;
	private Color borderGray = GUI.BORDER_GRAY;
	
	public FileMenu(JFrame parent) {
//		super("<html><p style='margin: 5 40 5 40'>FILE</p></html>");
		super("FILE");
//		setMargin(new Insets(5, 40, 5, 40));
//		Dimension preferredSize = getPreferredSize();
//		preferredSize.width = 120;
//		setPreferredSize(preferredSize);
//		setHorizontalAlignment(CENTER);
//		setHorizontalTextPosition(SwingConstants.CENTER);
//		invalidate();
		
		Border paddingBorder = BorderFactory.createEmptyBorder(5, 40, 5, 40);
		Border lineBorder = BorderFactory.createMatteBorder(0, 0, 0, 1, borderGray);
		CompoundBorder border = BorderFactory.createCompoundBorder(lineBorder, paddingBorder);
		setBorder(border);
		
		add(new TrackFileMenuItem(parent, getSize()));
		JPopupMenu popupMenu = getPopupMenu();
		popupMenu.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, borderGray));
	}
}
