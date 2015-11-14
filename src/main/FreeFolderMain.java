package main;

import gui.GUI;
import model.PathTrackerManager;

public class FreeFolderMain {

	public static void main(String[] args) {
		PathTrackerManager ftm = new PathTrackerManager();
		GUI gui = new GUI(ftm);
	}
}
