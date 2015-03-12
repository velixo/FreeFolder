package test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.FileTracker;
import model.FileTrackerManager;

import org.junit.Before;
import org.junit.Test;

public class TestFileTrackerManager {
	private FileTrackerManager ftManager;
	
	@Before
	public void setUp() {
		ftManager = new FileTrackerManager();
	}
	
	@Test
	public void testTrackFiles() {
		File f1 = new File("f1");
		File f2 = new File("f2");
		ftManager.trackFile(f1);
		ftManager.trackFile(f2);
		List<FileTracker> actual = ftManager.getTrackedFiles();
		
		List<FileTracker> expected = new ArrayList<FileTracker>();
		expected.add(new FileTracker(f1));
		expected.add(new FileTracker(f2));

		assertEquals(expected, actual);
	}

}
