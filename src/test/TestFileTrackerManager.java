package test;

import static org.junit.Assert.assertEquals;

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
		FileTracker ft1 = new FileTracker("ft1");
		FileTracker ft2 = new FileTracker("ft2");
		ftManager.trackFile(ft1);
		ftManager.trackFile(ft2);
		List<FileTracker> actual = ftManager.getTrackedFiles();
		
		List<FileTracker> expected = new ArrayList<FileTracker>();
		expected.add(ft1);
		expected.add(ft2);
		
		assertEquals(expected, actual);
	}

}
