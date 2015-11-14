package gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.GUI;
import model.PathTracker;
import shared.Globals.PTStatus;

public class PathTrackerView extends JPanel implements Observer {
	private final static Color borderGray = GUI.BORDER_GRAY;
	private final static Color COL_UPTODATE= GUI.TEXT_GRAY;
	private final static Color COL_UPDATING = Color.decode("#7f4cfb");
	private final static Color COL_NEW = Color.decode("#7070c0");
	private final static Color COL_OLD = Color.decode("#7070c0");
	private final static Color COL_UPLOADING = Color.decode("#60b0e0");
//	private final static Color COL_RECENTLY_UPLOADED = Color.decode("#80b060");

	private String fileName;
	private JTextField fileNameField;
	private JTextField statusField;
	private PathTracker pt;

	public PathTrackerView(PathTracker pt) {
		super();
		this.pt = pt;
		//TODO find solution so pt always is Observable
		if (pt instanceof Observable)
			((Observable)pt).addObserver(this);
		fileName = pt.getName();
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
		update(null, null);
		
		add(fileNameField);
		add(statusField);
		setBackground(Color.white);
		setLayout(new GridLayout(1, 2));
	}

	public PathTrackerView setBottomBorder(boolean hasBottomBorder) {
		if (hasBottomBorder)
			setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderGray));
		return this;
	}

	@Override
	/** ptFlag should be a PTMissingFlag object that */
	public void update(Observable o, Object ptFlag) {
		PTStatus status = pt.getStatus();
		if(status == PTStatus.UPTODATE) {
			fileNameField.setForeground(COL_UPTODATE);
			statusField.setForeground(COL_UPTODATE);
		} else if(status == PTStatus.NEW) {
			fileNameField.setForeground(COL_NEW);
			statusField.setForeground(COL_NEW);
		} else if(status == PTStatus.OLD) {
			fileNameField.setForeground(COL_OLD);
			statusField.setForeground(COL_OLD);
		} else if(status == PTStatus.UPDATING) {
			fileNameField.setForeground(COL_UPDATING);
			statusField.setForeground(COL_UPDATING);
		} else if(status == PTStatus.UPLOADING) {
			fileNameField.setForeground(COL_UPLOADING);
			statusField.setForeground(COL_UPLOADING);
		} else if(status == PTStatus.MISSING) {
			//TODO implement?
		} else if (status == PTStatus.DELETEDONSERVER) {
			//TODO implement?
		}
	}
	
	public PTStatus getPTStatus() {
		return pt.getStatus();
	}
	
	public String getPTName() {
		return pt.getName();
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
}
