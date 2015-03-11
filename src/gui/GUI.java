package gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;


public class GUI implements Observer {
	private String programTitle = "FreeFolder";
	private Observable observable;
	
	public GUI(Observable observable) {
		this.observable = observable;
		JFrame frame = new JFrame(programTitle);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
