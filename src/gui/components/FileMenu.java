package gui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import gui.GUI;
import model.PathTrackerManager;

public class FileMenu extends FlatMenu {
	private static final long serialVersionUID = -3641031798577935262L;
	private Color borderGray = GUI.BORDER_GRAY;
	private Color textGray = GUI.TEXT_GRAY;
	
	public FileMenu(final JFrame parent, final PathTrackerManager ftm) {
		super("FILE");
		fixMenuStyling();
		add(initAddFileMenuItem(parent, ftm));
		
		JPopupMenu popupMenu = getPopupMenu();
		popupMenu.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, borderGray));
	}
	
	private JMenuItem initAddFileMenuItem(final JFrame parent, final PathTrackerManager ftm) {
		JMenuItem addFileMenuItem = new JMenuItem("Add file");
		addFileMenuItem.setBackground(Color.white);
		addFileMenuItem.setForeground(textGray);
		
		//fixing margins and centering text is haaaard, bruteforcing it in the meanwhile
		addFileMenuItem.setBorder(BorderFactory.createEmptyBorder(5, 26, 5, 0));
		Dimension preferredSize = getPreferredSize();
		preferredSize.height = 35;
		preferredSize.width--;
		addFileMenuItem.setPreferredSize(preferredSize);
		
		Component fm = this; //fm = filemenu
		addFileMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String currentPath = System.getProperty("user.dir");
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Track file or folder");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.setMultiSelectionEnabled(true);
				
				if(fileChooser.showDialog(fm, "Track") == JFileChooser.APPROVE_OPTION) {
					File[] files = fileChooser.getSelectedFiles();
					for(File f: files) {
						ftm.trackFile(f);
					}
				}
			}
		});
		return addFileMenuItem;
	}
}
