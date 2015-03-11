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
	public boolean equals(Object o) {
		if (o instanceof FileTracker) {
			FileTracker ft = (FileTracker) o;
			return file.equals(ft.file);
		} else {
			return false;
		}
	}
}
