package main;

import gui.GUI;
import model.FileTrackerManager;

public class FreeFolderMain {

	public static void main(String[] args) {
		FileTrackerManager ftm = new FileTrackerManager();
		GUI gui = new GUI(ftm);
	}
}
