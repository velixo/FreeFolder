package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import shared.Globals.PTStatus;

public class FolderTracker extends Observable implements PathTracker {
	private PathTrackerManager ftm;
	private File root;
	private long lastModified = Long.MIN_VALUE;
	private int folderId;
	private volatile PTStatus status;
	private List<PathTracker> pathTrackers;

	public FolderTracker(File f, PathTrackerManager ftm)
			throws IllegalArgumentException {
		if(!f.exists() || !f.isDirectory()) {
			throw new IllegalArgumentException("f doesn't exist or isn't a directory");
		}
		this.ftm = ftm;
		root = f;
		folderId = f.hashCode();
		
		pathTrackers = new ArrayList<PathTracker>();
		File[] subfiles = f.listFiles();
		for (File subf : subfiles) {
			System.out.println(subf + " added");
			if(subf.exists()) {
				if(subf.isDirectory()) {
					pathTrackers.add(new FolderTracker(subf, ftm));
				} else if (subf.isFile()) {
					pathTrackers.add(new FileTracker(subf, ftm));
				}
			}
		}
	}
	
	@Override
	public PTStatus getStatus() {
		PTStatus firstStatus = pathTrackers.get(0).getStatus();
		for (int i = 1; i < pathTrackers.size(); i++) {
			PathTracker nextPT = pathTrackers.get(i);
			if (firstStatus != nextPT.getStatus())
				return PTStatus.MIXED;
		}
		return firstStatus;
	}

	@Override
	public String getName() {
		return root.getName();
	}

	@Override
	public long getLastModified() {
		return lastModified;
	}

	@Override
	public int getId() {
		return folderId;
	}

	@Override
	public void update() {
		for (PathTracker pt : pathTrackers) {
			pt.update();
		}
	}
	
	@Override
	public byte[] encode() {
		// TODO Auto-generated method stub
		return null;
	}

}
