package gui.menu;

import gui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import model.Model;

public class FileAddItem extends JMenuItem {
	private static final long serialVersionUID = -8713598439481016630L;
	private MainWindow parent;
	private Model model;

	public FileAddItem(final MainWindow parent, final Model model) {
		super("Add File...");
		this.parent = parent;
		this.model = model;
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO implement
				System.out.println("FileAddItem.actionPerformed");
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showDialog(parent, "Track file");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					model.trackFile(chooser.getSelectedFiles());
				}
			}
		});
	}
}
