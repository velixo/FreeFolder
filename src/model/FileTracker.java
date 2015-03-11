package model;


public class FileTracker {
	private String filePath;

	public FileTracker(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof FileTracker) {
			FileTracker ft = (FileTracker) o;
			return filePath.equals(ft.filePath);
		} else {
			return false;
		}
	}
}
