package model;

import java.io.File;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public class FileTrackerManager extends Observable{
	
	private Set<FileTracker> fileTrackers;
	
	public FileTrackerManager() {
		fileTrackers = new HashSet<FileTracker>();
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
	
	public Set<FileTracker> getTrackedFiles() {
		return fileTrackers;
	}
}
