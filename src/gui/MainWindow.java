package gui;

import gui.menu.EditMenu;
import gui.menu.FileAddItem;
import gui.menu.FileMenu;
import gui.menu.OptionsMenu;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.ModelClass;

public class MainWindow extends JFrame implements Observer {
	private static final long serialVersionUID = 433106539000278200L;
	private ModelClass model;
	
	private JMenuBar menubar;
	private FileMenu menuFile;
	private EditMenu menuEdit;
	private OptionsMenu menuOptions;
	private FileAddItem fileAdd;

	private JTextArea recentChangesArea;
	
	
	
	public MainWindow(ModelClass model) {
		super("FileSync");
		//TODO look for config file in directory
		//TODO if config found,
		this.model = model;
		//TODO check if ((Observable) model).addObserver(this); is better implementation
		model.addObserver(this);

		//initialize menuBar
		menubar = new JMenuBar();
		menuFile = new FileMenu();
		menuEdit = new EditMenu();
		menuOptions = new OptionsMenu();
		fileAdd = new FileAddItem(this, model);
		menuFile.add(fileAdd);
		menubar.add(menuFile);
		menubar.add(menuEdit);
		menubar.add(menuOptions);
		setJMenuBar(menubar);
		
		//TODO add "recent changes/syncs"
		recentChangesArea = new JTextArea();
		recentChangesArea.setEditable(false);
		add(new JScrollPane(recentChangesArea), BorderLayout.CENTER);
		
		setSize(1000, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void update(Observable obs, Object obj) {
		//go through fileChangeHistory from latest entry to the earliest and append to recentChangesArea
		recentChangesArea.setText("");
		List<String> history = model.getFileChangeHistory();
		for (int i = history.size()-1; i > 0; i--) {
			recentChangesArea.append(history.get(i) + "\n");
		}
		
	}

}
