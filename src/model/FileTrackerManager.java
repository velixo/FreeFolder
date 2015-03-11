package model;

import java.util.ArrayList;
import java.util.List;

public class FileTrackerManager {
	
	private List<FileTracker> fileTrackers;
	
	public FileTrackerManager() {
		fileTrackers = new ArrayList<FileTracker>();
	}
	
	public void trackFile(FileTracker ft) {
		fileTrackers.add(ft);
	}
	
	public List<FileTracker> getTrackedFiles() {
		return fileTrackers;
	}
}
