package gui.components;

import gui.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.FileTracker;

public class FileTrackerView extends JPanel {
	public final static int PASSIVE = 1;
	public final static int UPDATING = 2;
	public final static int PENDING_UPDATE = 3;
	public final static int UPLOADING = 4;
	public final static int RECENTLY_UPLOADED = 5;
	
	private final static Color borderGray = GUI.BORDER_GRAY;
	private final static Color COL_PASSIVE= GUI.TEXT_GRAY;
	private final static Color COL_UPDATING = Color.decode("#60b0e0");
	private final static Color COL_PENDING_UPDATE = Color.decode("#7070c0");
	private final static Color COL_UPLOADING = Color.decode("#f060e0");
	private final static Color COL_RECENTLY_UPLOADED = Color.decode("#80b060");
	
	private String fileName;
	private int status;
	private JTextField fileNameField;
	private JTextField statusField;
	
	public FileTrackerView(FileTracker ft) {
		super();
		fileName = ft.getFileName();
		Font font = new Font("Arial", Font.PLAIN, 16);
		
		fileNameField = new JTextField(fileName);
		fileNameField.setEditable(false);
		fileNameField.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		fileNameField.setBackground(Color.white);
		fileNameField.setFont(font);
		fileNameField.setHorizontalAlignment(JTextField.LEFT);
		
		statusField = new JTextField("Last modified: 2013-13-37");
		statusField.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		statusField.setBackground(Color.white);
		statusField.setFont(font);
		statusField.setHorizontalAlignment(JTextField.RIGHT);
		setStatus(PASSIVE);
		
		add(fileNameField);
		add(statusField);
		setBackground(Color.white);
		setLayout(new GridLayout(1, 2));
	}
	
	@Override
	public Dimension getPreferredSize() {
		Dimension preferredSize = super.getPreferredSize();
		preferredSize.height = 50;
		return preferredSize;
	}
	
	@Override
	public Dimension getMaximumSize() {
		Dimension maxSize = super.getMaximumSize();
		maxSize.height = 50;
		return maxSize;
	}
	
	@Override
	public Dimension getMinimumSize() {
		Dimension minSize = super.getMaximumSize();
		minSize.height = 50;
		return minSize;
	}
	
	public void setStatus(int status) {
		this.status = status;
		updateTextStatus();
	}
	
	private void updateTextStatus() {
		switch (status) {
		case PASSIVE:
			fileNameField.setForeground(COL_PASSIVE);
			statusField.setForeground(COL_PASSIVE);
			break;
			
		case UPDATING:
			fileNameField.setForeground(COL_UPDATING);
			statusField.setForeground(COL_UPDATING);
			break;
			
		case PENDING_UPDATE:
			fileNameField.setForeground(COL_PENDING_UPDATE);
			statusField.setForeground(COL_PENDING_UPDATE);
			break;
			
		case UPLOADING:
			fileNameField.setForeground(COL_UPLOADING);
			statusField.setForeground(COL_UPLOADING);
			break;
			
		case RECENTLY_UPLOADED:
			fileNameField.setForeground(COL_RECENTLY_UPLOADED);
			statusField.setForeground(COL_RECENTLY_UPLOADED);
			break;

		default:
			fileNameField.setForeground(COL_PASSIVE);
			statusField.setForeground(COL_PASSIVE);
			break;
		}
	}
}
