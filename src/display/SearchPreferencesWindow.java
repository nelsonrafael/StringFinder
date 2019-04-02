package display;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class SearchPreferencesWindow implements ActionListener, ItemListener {

	private boolean searchSubFolders = false;
	private boolean caseSensitive = true;

	private boolean oldSearchSubFolders = false;
	private boolean oldCaseSensitive = true;

	private JCheckBox searchSubFoldersCheckBox = new JCheckBox("Search Sub Folders");
	private JCheckBox caseSensitiveCheckBox = new JCheckBox("Case Sensitive");

	private JDialog dialog;

	private JFrame parentFrame;

	private JPanel mainPanel, checkBoxesPanel, buttonsPanel;

	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");

	public SearchPreferencesWindow(JFrame parentFrame) {
		this.parentFrame = parentFrame;

		dialog = new JDialog();
		mainPanel = new JPanel();
		checkBoxesPanel = new JPanel();
		buttonsPanel = new JPanel();

		checkBoxesPanel.setLayout(new GridLayout(2, 1));
		checkBoxesPanel.add(searchSubFoldersCheckBox, "North");
		checkBoxesPanel.add(caseSensitiveCheckBox, "South");

		buttonsPanel.setLayout(new GridLayout(1, 2));
		buttonsPanel.add(okButton, "West");
		buttonsPanel.add(cancelButton, "East");

		mainPanel.setLayout(new GridLayout(2, 1));
		mainPanel.add(checkBoxesPanel, "North");
		mainPanel.add(buttonsPanel, "South");

		dialog.add(mainPanel);

		searchSubFoldersCheckBox.setSelected(searchSubFolders);
		caseSensitiveCheckBox.setSelected(caseSensitive);

		searchSubFoldersCheckBox.addItemListener(this);
		caseSensitiveCheckBox.addItemListener(this);

		okButton.addActionListener(this);
		cancelButton.addActionListener(this);

		windowOptions();
	}

	public void startGUI() {
		dialog.setLocationRelativeTo(parentFrame);

		searchSubFoldersCheckBox.setSelected(searchSubFolders);
		caseSensitiveCheckBox.setSelected(caseSensitive);

		searchSubFolders = oldSearchSubFolders;
		caseSensitive = oldCaseSensitive;

		dialog.setVisible(true);
	}

	private void windowOptions() {
		dialog.setModal(true);
		dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		dialog.setTitle("Search Preferences");
		dialog.setPreferredSize(new Dimension(200, 200));
		dialog.pack();
		dialog.setLocationRelativeTo(parentFrame);
		dialog.setVisible(false);
	}

	public boolean getSearchSubFolders() {
		return this.searchSubFolders;
	}

	public boolean getCaseSensitive() {
		return this.caseSensitive;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();
		if (source == searchSubFoldersCheckBox) {
			searchSubFolders = !searchSubFolders;
		} else if (source == caseSensitiveCheckBox) {
			caseSensitive = !caseSensitive;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == okButton) {
			oldSearchSubFolders = searchSubFolders;
			oldCaseSensitive = caseSensitive;
			dialog.dispose();
		} else if (source == cancelButton) {
			searchSubFolders = oldSearchSubFolders;
			caseSensitive = oldCaseSensitive;
			dialog.dispose();
		}
	}

}
