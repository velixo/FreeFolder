package model;

import java.io.File;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileTrackerManager extends Observable{
	private Set<FileTracker> fileTrackers;
	private final ScheduledExecutorService scheduler;
	
	public FileTrackerManager() {
		fileTrackers = new HashSet<FileTracker>();
		scheduler = Executors.newScheduledThreadPool(1);
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
	
	/** Starts a thread that periodically checks every file to see if it has been updated recently*/
	public void setFilestalker(int period, TimeUnit timeUnit) {
		Runnable filestalkingTask = new Runnable() {
			public void run() {
				//for each filetracker
					//if file has been modified after their lastModified date
						//mark file as changed - FileTracker.setStatus
					//else if file missing
						//mark file as missing
			}
		};
		scheduler.scheduleAtFixedRate(filestalkingTask, 0, period, timeUnit);
	}
}
