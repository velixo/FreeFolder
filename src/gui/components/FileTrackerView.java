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
	public final static int UNCHANGED = 1;
	public final static int UPDATING = 2;
	public final static int CHANGED = 3;
	public final static int UPLOADING = 4;
//	public final static int RECENTLY_UPLOADED = 5;
	
	private final static Color borderGray = GUI.BORDER_GRAY;
	private final static Color COL_UNCHANGED= GUI.TEXT_GRAY;
	private final static Color COL_UPDATING = Color.decode("#7f4cfb");
	private final static Color COL_CHANGED = Color.decode("#7070c0");
	private final static Color COL_UPLOADING = Color.decode("#60b0e0");
//	private final static Color COL_RECENTLY_UPLOADED = Color.decode("#80b060");
	
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
		statusField.setEditable(false);
		statusField.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		statusField.setBackground(Color.white);
		statusField.setFont(font);
		statusField.setHorizontalAlignment(JTextField.RIGHT);
		setStatus(UNCHANGED);
		
		add(fileNameField);
		add(statusField);
		setBackground(Color.white);
		setLayout(new GridLayout(1, 2));
	}
	
	@Override
	public Dimension getPreferredSize() {
		Dimension size = super.getMaximumSize();
		size.height = 70;
		return size;
	}
	
	@Override
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}
	
	@Override
	public Dimension getMinimumSize() {
		Dimension size = new Dimension(50, 250);
		return size;
	}
	
	public void setStatus(int status) {
		this.status = status;
		updateTextStatus();
	}
	
	public FileTrackerView setBottomBorder(boolean hasBottomBorder) {
		if (hasBottomBorder)
			setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderGray));
		return this;
	}
	
	private void updateTextStatus() {
		switch (status) {
		case UNCHANGED:
			fileNameField.setForeground(COL_UNCHANGED);
			statusField.setForeground(COL_UNCHANGED);
			break;
			
		case UPDATING:
			fileNameField.setForeground(COL_UPDATING);
			statusField.setForeground(COL_UPDATING);
			break;
			
		case CHANGED:
			fileNameField.setForeground(COL_CHANGED);
			statusField.setForeground(COL_CHANGED);
			break;
			
		case UPLOADING:
			fileNameField.setForeground(COL_UPLOADING);
			statusField.setForeground(COL_UPLOADING);
			break;
			
//		case RECENTLY_UPLOADED:
//			fileNameField.setForeground(COL_RECENTLY_UPLOADED);
//			statusField.setForeground(COL_RECENTLY_UPLOADED);
//			break;

		default:
			fileNameField.setForeground(COL_UNCHANGED);
			statusField.setForeground(COL_UNCHANGED);
			break;
		}
	}
}
