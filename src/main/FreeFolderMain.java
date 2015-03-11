package main;

import model.FileTrackerManager;
import gui.GUI;

public class FreeFolderMain {

	public static void main(String[] args) {
		FileTrackerManager ftm = new FileTrackerManager();
		GUI gui = new GUI(ftm);
	}
}
