package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileTracker {
	public final static int UNCHANGED = 1;
	public final static int CHANGED = 2;
	public final static int MISSING = 3;

	private File file;
	private long lastModified = Long.MIN_VALUE;
	private int status;
	private int fileId = Integer.MIN_VALUE;

	public FileTracker(String filepath) {
		file = new File(filepath);
		status = UNCHANGED;
		if (!file.exists() || !file.isFile()) {
			status = MISSING;
		} else {
			lastModified = file.lastModified();
		}
	}
	
	public void updateStatus() {
		if (!file.exists() || !file.isFile())
			status = MISSING;
		else if (file.lastModified() > lastModified) {
			status = CHANGED;
			lastModified = file.lastModified();
		} else status = UNCHANGED;
	}
	
	public void overwriteFile(byte[] fileData) {
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(file);
			stream.write(fileData);
		} catch (FileNotFoundException e1) {
			status = MISSING;
		} catch (IOException e) {
			//print stacktrace to error log file
			e.printStackTrace();
		} finally {
			try {
				stream.close();
				lastModified = file.lastModified();	//TODO check if rewriting file changes lastModified date 
				status = UNCHANGED;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getAbsoluteFileName() {
		return file.getAbsolutePath();
	}

	public int getStatus() {
		return status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FileTracker) {
			FileTracker ft = (FileTracker) obj;
			return file.equals(ft.file);
		} else {
			return false;
		}
	}

}
