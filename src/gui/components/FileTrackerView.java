package gui.components;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.FileTracker;

public class FileTrackerView extends JPanel {
	public final static int PASSIVE = 1;
	public final static int UPDATING = 2;
	public final static int PENDING_UPDATE = 3;
	public final static int UPLOADING = 4;
	public final static int RECENTLY_UPLOADED = 5;
	
	private String fileName;
	private int status;
	private JTextField fileNameField;
	private JTextField statusField;
	
	public FileTrackerView(FileTracker ft) {
		super();
		fileName = ft.getFileName();
		status = PASSIVE;
		
		fileNameField = new JTextField(fileName);
		statusField = new JTextField("Last modified: 2013-13-37");
		add(fileNameField);
		add(statusField);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	}
	
	public void setStatus() {
		
	}
}
