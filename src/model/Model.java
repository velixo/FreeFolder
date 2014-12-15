package model;

import java.io.File;
import java.util.List;

public interface Model {
	public void trackFiles(File[] file);
	public List<String> getFileChangeHistory();
}
