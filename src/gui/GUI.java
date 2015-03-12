package gui;

import gui.components.FileMenu;
import gui.components.FileTrackerInfoContainer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIManager;

import model.FileTracker;
import model.FileTrackerManager;


public class GUI extends JFrame implements Observer {
	private static final long serialVersionUID = -4343086109680697375L;
	private static final String programTitle = "FreeFolder";
	public static final Color TEXT_GRAY = Color.decode("#808080");
	public static final Color BORDER_GRAY = Color.decode("#d0d0d0");
	private Observable observable;
	private FileTrackerManager ftm;
	
	public GUI(Observable observable) {
		super(programTitle);
		observable.addObserver(this);
		this.observable = observable;
		ftm = (FileTrackerManager) observable;
		initLookAndFeel();

		JMenuBar menuBar = initMenuBar();
		JPanel fileTrackerPanel = initFileTrackerPanel(ftm.getTrackedFiles());
		add(menuBar, BorderLayout.NORTH);
		add(fileTrackerPanel, BorderLayout.CENTER);
		
		setBackground(Color.white);
		setSize(700, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private void initLookAndFeel() {
		Font f = new Font("Arial", Font.PLAIN, 16);
		UIManager.put("Menu.font", f);
		UIManager.put("MenuItem.font", f);
		UIManager.put("Menu.foreground", TEXT_GRAY);
	}

	private JMenuBar initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		FileMenu fileMenu = new FileMenu(this);
//		JMenu fileMenu = new JMenu("<html><p style='margin: 5 40 5 40'>FILE</p></html>");
		JMenu editMenu = new JMenu("<html><p style='margin: 5 40 5 40'>EDIT</p></html>");
//		fileMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, BORDER_GRAY));
		editMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, BORDER_GRAY));
//		fileMenu.add(new TrackFileMenuItem(this));
//		fileMenu.getPopupMenu().setBackground(Color.white);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.setBackground(Color.white);
		menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_GRAY));
		return menuBar;
	}
	
	private JPanel initFileTrackerPanel(List<FileTracker> fileTrackers) {
		JPanel ftPanel = new JPanel();

		for (FileTracker ft : fileTrackers) {
			FileTrackerInfoContainer fileContainer = new FileTrackerInfoContainer(ft);
			ftPanel.add(fileContainer);
		}
		ftPanel.setLayout(new BoxLayout(ftPanel, BoxLayout.PAGE_AXIS));
		ftPanel.setBackground(Color.white);
		ftPanel.setBorder(BorderFactory.createEmptyBorder());
		return ftPanel;
	}
}
