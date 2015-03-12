package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class FileTrackerManager extends Observable{
	
	private List<FileTracker> fileTrackers;
	
	public FileTrackerManager() {
		fileTrackers = new ArrayList<FileTracker>();
	}
	
	public void trackFile(File f) {
		fileTrackers.add(new FileTracker(f));
		setChanged();
		notifyObservers();
	}
	
	public void trackFiles(File[] files) {
		for(File f : files) {
			fileTrackers.add(new FileTracker(f));
		}
		setChanged();
		notifyObservers();
	}
	
	public List<FileTracker> getTrackedFiles() {
		return fileTrackers;
	}
}
