package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;

import shared.Globals;
import shared.Globals.PTStatus;


public class FileTracker extends Observable implements PathTracker {
	private PathTrackerManager ftm;
	private File file;
	private long lastModified = Long.MIN_VALUE;
	private int fileId;
	private volatile PTStatus status;

	public FileTracker(File f, PathTrackerManager ftm)
			throws IllegalArgumentException {
		if(!f.exists() || !f.isFile()) {
			throw new IllegalArgumentException("f doesn't exist or isn't a file");
		}
		this.ftm = ftm;
		file = f;
		fileId = f.hashCode();
		update();
	}

	public PTStatus getStatus() {
		return status;
	}
	
	public String getName() {
		return file.getName();
	}

	@Override
	public long getLastModified() {
		return lastModified;
	}

	@Override
	public int getId() {
		return fileId;
	}
	
	@Override
	public void update() {
		PTStatus oldStatus = status;

		//check file missing
		if (!file.exists() || !file.isFile()) {
			status = PTStatus.MISSING;
		} else {
			lastModified = file.lastModified();
			
			//check file status
			if(status != PTStatus.UPDATING && status != PTStatus.UPLOADING) {			
				long lastModifiedOnServer = ftm.getLastModifiedOnServer(fileId);
				if (lastModifiedOnServer == Globals.PATH_DELETED_ON_SERVER)
					status = PTStatus.DELETEDONSERVER;
				else if(lastModified == lastModifiedOnServer)
					status = PTStatus.UPTODATE;
				else if (lastModified < lastModifiedOnServer)
					status = PTStatus.OLD;
				else if (lastModified > lastModifiedOnServer)
					status = PTStatus.NEW;
			}
		}
		//check if status changed
		if (status != oldStatus) {
			setChanged();
			notifyObservers();
		}
	}
	
	@Override
	public synchronized byte[] encode() {
		return null;
	}
	
	/** Only used by FileTracker to set status to updating or */
	void setStatus(PTStatus status) {
		this.status = status;
	}

	/** Overwrites the file. Returns true if overwrite
	 * was a success, false if not. */
	public boolean overwriteFile(byte[] fileData) {
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(file);
			stream.write(fileData);
		} catch (FileNotFoundException e1) {
			status = PTStatus.MISSING;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				stream.close();
				lastModified = file.lastModified();	//TODO check if rewriting file changes lastModified date
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
