package gui;

import gui.menu.EditMenu;
import gui.menu.FileAddItem;
import gui.menu.FileMenu;
import gui.menu.OptionsMenu;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import model.Model;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 433106539000278200L;
	private Model model;
	private JMenuBar menubar;
	
	private FileMenu menuFile;
	private EditMenu menuEdit;
	private OptionsMenu menuOptions;
	
	private FileAddItem fileAdd;
	
	
	public MainWindow(Model model) {
		super("FileSync");
		//TODO look for config file in directory
		//TODO if config found,
		this.model = model;

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
		
		setSize(1000, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
