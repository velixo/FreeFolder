package model;

import java.io.File;


public class FileTracker {
	private File file;

	public FileTracker(File file) {
		this.file = file;
	}
	
	public String getFileName() {
		return file.getName();
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
