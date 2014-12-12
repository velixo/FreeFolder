package model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModelClass implements Model {
	private List<File> trackedFiles;
	private List<FileWatcher> fileWatchers;
	private long updateTime = 1000;
	
	public ModelClass() {
		trackedFiles = new ArrayList<File>();
	}
	
	public ModelClass(List<File> trackedFiles) {
		this.trackedFiles = trackedFiles;
	}
	
	public void trackFile(File file) {
		trackedFiles.add(file);
	}
	
	public void trackFile(File[] files) {
		for (File f : files) {
			trackedFiles.add(f);
		}
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
		// TODO Auto-generated method stub
		String timeDate = new SimpleDateFormat("HH:mm:ss yyy/MM/dd").format(new Date());
		System.out.println(file.getName() + " has been changed - " + timeDate);
	}

}
