package gui.components;

import gui.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;

import model.FileTrackerManager;

public class TrackFileMenuItem extends JMenuItem {
	private static final long serialVersionUID = 5717623182741787772L;
	private Color textGray = GUI.TEXT_GRAY;
	private Color borderGray = GUI.BORDER_GRAY;
	private Frame parent;
	private FileTrackerManager fileTrackerManager;

	public TrackFileMenuItem(final Frame parent, Dimension size, FileTrackerManager ftManager) {
		super("Add file");
		this.parent = parent;
		this.fileTrackerManager = ftManager;
		setBackground(Color.white);
		setBorder(BorderFactory.createEmptyBorder());
		setForeground(textGray);
		setBorder(BorderFactory.createEmptyBorder(5, 26, 5, 26));
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileDialog dialog = new FileDialog(parent, "Add file", FileDialog.LOAD);
				dialog.setDirectory(System.getProperty("user.dir"));
				dialog.setVisible(true);
				fileTrackerManager.trackFiles(dialog.getFiles());
			}
		});
	}
}
