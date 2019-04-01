package display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenu optionsMenu = new JMenu("Options");
	private JMenu helpMenu = new JMenu("Help");

	private JMenuItem filePreferencesItem = new JMenuItem("File Preferences");
	private JMenuItem searchPreferencesItem = new JMenuItem("Search Preferences");
	private JMenuItem helpItem = new JMenuItem("Help Contents");
	private JMenuItem aboutItem = new JMenuItem("About StringFinder");

	private SearchPreferencesWindow searchPreferences = new SearchPreferencesWindow();

	private FilePreferencesWindow filePreferences = new FilePreferencesWindow();

	public MenuBar() {
		optionsMenu.add(filePreferencesItem);
		optionsMenu.add(searchPreferencesItem);

		helpMenu.add(helpItem);
		helpMenu.add(aboutItem);

		this.add(optionsMenu);
		this.add(helpMenu);

		filePreferencesItem.addActionListener(this);
		searchPreferencesItem.addActionListener(this);
		helpItem.addActionListener(this);
		aboutItem.addActionListener(this);
	}

	public SearchPreferencesWindow getSearchPreferencesWindow() {
		return searchPreferences;
	}

	public FilePreferencesWindow getFilePreferencesWindow() {
		return filePreferences;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == filePreferencesItem) {
			filePreferences.startGUI();
		} else if (e.getSource() == searchPreferencesItem) {
			searchPreferences.startGUI();
		} else if (e.getSource() == helpItem) {
			// TODO
		} else if (e.getSource() == aboutItem) {
			// TODO
		}
	}

}
