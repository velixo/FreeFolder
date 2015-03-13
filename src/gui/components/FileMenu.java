package gui.components;

import gui.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import model.FileTrackerManager;

public class FileMenu extends FlatMenu {
	private static final long serialVersionUID = -3641031798577935262L;
	private Color borderGray = GUI.BORDER_GRAY;
	private Color textGray = GUI.TEXT_GRAY;
	
	public FileMenu(final JFrame parent, final FileTrackerManager ftm) {
		super("FILE");
		fixMenuStyling();
		add(initAddFileMenuItem(parent, ftm));
		
		JPopupMenu popupMenu = getPopupMenu();
		popupMenu.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, borderGray));
	}
	
	private JMenuItem initAddFileMenuItem(final JFrame parent, final FileTrackerManager ftm) {
		JMenuItem addFileMenuItem = new JMenuItem("Add file");
		addFileMenuItem.setBackground(Color.white);
		addFileMenuItem.setForeground(textGray);
		
		//fixing margins and centering text is haaaard, bruteforcing it in the meanwhile
		addFileMenuItem.setBorder(BorderFactory.createEmptyBorder(5, 26, 5, 0));
		Dimension preferredSize = getPreferredSize();
		preferredSize.height = 35;
		preferredSize.width--;
		addFileMenuItem.setPreferredSize(preferredSize);
		
		addFileMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileDialog dialog = new FileDialog(parent, "Add file", FileDialog.LOAD);
				dialog.setDirectory(System.getProperty("user.dir"));
				dialog.setVisible(true);
				ftm.trackFiles(dialog.getFiles());
			}
		});
		return addFileMenuItem;
	}
}
