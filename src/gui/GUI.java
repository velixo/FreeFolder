package gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;


public class GUI extends JFrame implements Observer {
	private static final long serialVersionUID = -4343086109680697375L;
	private final static String programTitle = "FreeFolder";
	private Observable observable;
	
	public GUI(Observable observable) {
		super(programTitle);
		observable.addObserver(this);
		this.observable = observable;
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		
		menuBar.add(fileMenu);
		add(menuBar);
		
		BoxLayout fileContainer = new BoxLayout(this, BoxLayout.Y_AXIS);
		
		setBackground(Color.WHITE);
		setSize(800, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
