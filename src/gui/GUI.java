package gui;

import gui.components.EditMenu;
import gui.components.FileMenu;
import gui.components.FileTrackerView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import model.FileTracker;
import model.FileTrackerManager;


public class GUI extends JFrame implements Observer {
	private static final long serialVersionUID = -4343086109680697375L;
	private static final String programTitle = "FreeFolder";
	public static final Color TEXT_GRAY = Color.decode("#808080");
	public static final Color BORDER_GRAY = Color.decode("#d0d0d0");
	private FileTrackerManager ftm;
	private List<FileTrackerView> ftViews;
	
	private JPanel fileTrackerPanel;
	
	public GUI(FileTrackerManager ftm) {
		super(programTitle);
		ftm.addObserver(this);
		this.ftm = ftm;
		setMainFont();

		JMenuBar menuBar = new JMenuBar();
		FileMenu fileMenu = new FileMenu(this, ftm);
		EditMenu editMenu = new EditMenu();
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		fileTrackerPanel = new JPanel();
		ftViews = new ArrayList<FileTrackerView>();
		updateFileTrackerPanel();
		
		add(menuBar, BorderLayout.NORTH);
		add(fileTrackerPanel, BorderLayout.CENTER);
		setStyling(menuBar, fileTrackerPanel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setStyling(JMenuBar menuBar, JPanel ftPanel) {
		menuBar.setBackground(Color.white);
		menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_GRAY));
		ftPanel.setLayout(new BoxLayout(ftPanel, BoxLayout.PAGE_AXIS));
		ftPanel.setBackground(Color.white);
		ftPanel.setBorder(BorderFactory.createMatteBorder(0, 50, 0, 50, Color.white));
		setBackground(Color.white);
		setSize(700, 500);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		//reload filetracker views
		ftViews.clear();
		Set<FileTracker> fileTrackers = ftm.getTrackedFiles();
		for(FileTracker ft : fileTrackers) {
			ftViews.add(new FileTrackerView(ft));
		}
		updateFileTrackerPanel();
	}

	private void setMainFont() {
		Font f = new Font("Arial", Font.PLAIN, 16);
		UIManager.put("Menu.font", f);
		UIManager.put("MenuItem.font", f);
	}

	private void updateFileTrackerPanel() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				fileTrackerPanel.removeAll();
				if (ftViews != null && ftViews.size() >= 1) {
					for (int i = 0; i < ftViews.size() - 1; i++) {
						fileTrackerPanel.add(ftViews.get(i).setBottomBorder(true));
					}
					fileTrackerPanel.add(ftViews.get(ftViews.size()-1).setBottomBorder(false));
				}
				fileTrackerPanel.validate();
			}
		});
	}
}
