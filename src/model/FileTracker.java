package model;

import java.io.File;


public class FileTracker {
	public final static int UNCHANGED = 1;
	public final static int CHANGED = 2;
	public final static int MISSING = 3;
	
	private File file;
	private int status;

	public FileTracker(File file) {
		this.file = file;
		status = UNCHANGED;
	}
	
	public String getFileName() {
		return file.getName();
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int newStatus) {
		status = newStatus;
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
