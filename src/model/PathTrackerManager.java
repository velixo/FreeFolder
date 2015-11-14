package model;

import java.io.File;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PathTrackerManager extends Observable {
	private Set<PathTracker> pathTrackers;
	private Set<PathTracker> newlyTrackedPTs;
	private final ScheduledExecutorService scheduler;

	public PathTrackerManager() {
		pathTrackers = new HashSet<PathTracker>();
		scheduler = Executors.newScheduledThreadPool(1);
		newlyTrackedPTs = new HashSet<PathTracker>();
	}

	/** Creates a PathTracker based off of the File f */
	public void trackFile(File f) {
		PathTracker pt = null;
		if(f.exists() && f.isDirectory())
			pt = new FolderTracker(f, this);
		else if (f.exists() && f.isFile())
			pt = new FileTracker(f, this);
		pathTrackers.add(pt);
		newlyTrackedPTs.add(pt);
		setChanged();
		notifyObservers();
	}

	public Set<PathTracker> getTrackedFiles() {
		for(PathTracker pt : pathTrackers)
			pt.update();
		return pathTrackers;
	}
	
	public Set<PathTracker> getNewlyTrackedFiles() {
		Set<PathTracker> tempNewPTs = new HashSet<PathTracker>();
		for(PathTracker pt : newlyTrackedPTs)
			tempNewPTs.add(pt);
		newlyTrackedPTs.clear();
		return tempNewPTs;
	}
	
	/** Return the timestamp of when the file was modified on
	 * the server. Returns Globals.PATH_MISSING_ON_SERVER if
	 * the file is missing on the server. */
	public long getLastModifiedOnServer(int pathID) {
		//TODO implement
		return 0;
	}

	/** Starts a thread that periodically checks every file to see if it has been updated recently*/
	public void setFilestalker(int period, TimeUnit timeUnit) {
		Runnable filestalkingTask = new Runnable() {
			public void run() {
				for (PathTracker pt : pathTrackers) {
					pt.update();
				}
			}
		};
		scheduler.scheduleAtFixedRate(filestalkingTask, 0, period, timeUnit);
	}

//	private synchronized Map<Integer, Integer> getServerReport() {
//		return null;
//		//Download a map from server with all fileids and their timestamp
//		//compare the map with pathTrackers
//		//set status of all outdated files to needs update
//		//set status of all newer files to changed
//		//randomly
//	}
}
