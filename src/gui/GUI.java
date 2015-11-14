package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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

import gui.components.EditMenu;
import gui.components.FileMenu;
import gui.components.PathTrackerView;
import model.PathTracker;
import model.PathTrackerManager;
import shared.Globals.PTStatus;


public class GUI extends JFrame implements Observer {
	private static final long serialVersionUID = -4343086109680697375L;
	private static final String programTitle = "FreeFolder";
	public static final Color TEXT_GRAY = Color.decode("#808080");
	public static final Color BORDER_GRAY = Color.decode("#d0d0d0");
	private PathTrackerManager ftm;
	private List<PathTrackerView> ptViews;
	private JPanel pathTrackerPanel;
	
	public GUI(PathTrackerManager ftm) {
		super(programTitle);
		ftm.addObserver(this);
		this.ftm = ftm;
		setMainFont();

		JMenuBar menuBar = new JMenuBar();
		FileMenu fileMenu = new FileMenu(this, ftm);
		EditMenu editMenu = new EditMenu();
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		pathTrackerPanel = new JPanel();
		ptViews = new ArrayList<PathTrackerView>();
		
		add(menuBar, BorderLayout.NORTH);
		add(pathTrackerPanel, BorderLayout.CENTER);
		setStyling(menuBar, pathTrackerPanel);
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
		Set<PathTracker> newPTs = ftm.getNewlyTrackedFiles();
		for(PathTracker pt : newPTs) {
			PathTrackerView ptv = new PathTrackerView(pt);
			ptViews.add(ptv);
			pathTrackerPanel.add(ptv);
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for(PathTrackerView ptView : ptViews) {
					ptView.update(null, null);
					if (ptView.getPTStatus() == PTStatus.MISSING) {
						//TODO display popup
						
						System.out.println(ptView.getPTName()+" has gone missing");
					}
				}
				pathTrackerPanel.validate();
			}
		});
	}

	private void setMainFont() {
		Font f = new Font("Arial", Font.PLAIN, 16);
		UIManager.put("Menu.font", f);
		UIManager.put("MenuItem.font", f);
	}
}
