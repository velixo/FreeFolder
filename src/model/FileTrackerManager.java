package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class FileTrackerManager extends Observable{
	
	private List<FileTracker> fileTrackers;
	
	public FileTrackerManager() {
		fileTrackers = new ArrayList<FileTracker>();
	}
	
	public void trackFile(FileTracker ft) {
		fileTrackers.add(ft);
		setChanged();
		notifyObservers();
	}
	
	public List<FileTracker> getTrackedFiles() {
		return fileTrackers;
	}
}
