package model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

public class ModelClass extends Observable implements Model {
	private List<File> trackedFiles;
	private List<FileWatcher> fileWatchers;
	private long updateTime = 1000;
	private List<String> fileChangeHistory;
	
	public ModelClass() {
		trackedFiles = new ArrayList<File>();
		//TODO load fileChangeHistory from file?
		fileChangeHistory = new ArrayList<String>();
		initiateTracking();
	}
	
	public ModelClass(List<File> trackedFiles) {
		this.trackedFiles = trackedFiles;
		//TODO load fileChangeHistory from file?
		fileChangeHistory = new ArrayList<String>();
		initiateTracking();
	}
	
	public void trackFiles(File[] files) {
		for (File f : files) {
			trackedFiles.add(f);
		}
	}
	
	public void untrackFiles(File[] files) {
		for (File f : files) {
			trackedFiles.remove(f);
			//TODO stop the thread tracking this file and remove it from fileWatchers
		}
	}
	
	public List<String> getFileChangeHistory() {
		return fileChangeHistory;
	}
	
	private void initiateTracking() {
		fileWatchers = new ArrayList<FileWatcher>();
		for (File f : trackedFiles) {
			FileWatcher fw = new FileWatcher(this, f, updateTime);
			fw.run();
			fileWatchers.add(fw);
		}
	}

	void fileChanged(File file) {
		String timeDate = new SimpleDateFormat("HH:mm:ss yyy/MM/dd").format(new Date());
		//TODO refactor historyMessage
		String historyMessage = file.getName() + " has been changed - " + timeDate;
		fileChangeHistory.add(historyMessage);
		System.out.println(historyMessage);
		
		setChanged();
		notifyObservers();
	}

}
