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

public class FilePreferencesWindow extends JFrame implements ActionListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean searchXLSX = true;
	private boolean searchXLS = false;
	private boolean searchXLT = true;
	private boolean searchXML = true;
	private boolean searchCSV = true;
	private boolean searchTXT = true;
	private boolean searchLOG = true;
	private boolean searchDOCX = true;
	private boolean searchDOC = true;

	private boolean oldSearchXLSX = true;
	private boolean oldSearchXLS = false;
	private boolean oldSearchXLT = true;
	private boolean oldSearchXML = true;
	private boolean oldSearchCSV = true;
	private boolean oldSearchTXT = true;
	private boolean oldSearchLOG = true;
	private boolean oldSearchDOCX = true;
	private boolean oldSearchDOC = true;

	private JCheckBox searchXLSXCheckBox = new JCheckBox("XLSX");
	private JCheckBox searchXLSCheckBox = new JCheckBox("XLS");
	private JCheckBox searchXLTCheckBox = new JCheckBox("XLT");
	private JCheckBox searchXMLCheckBox = new JCheckBox("XML");
	private JCheckBox searchCSVCheckBox = new JCheckBox("CSV");
	private JCheckBox searchTXTCheckBox = new JCheckBox("TXT");
	private JCheckBox searchLOGCheckBox = new JCheckBox("LOG");
	private JCheckBox searchDOCXCheckBox = new JCheckBox("DOCX");
	private JCheckBox searchDOCCheckBox = new JCheckBox("DOC");

	private JDialog dialog;

	private JPanel mainPanel, checkBoxesPanel, buttonsPanel;

	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");

	public FilePreferencesWindow() {
		dialog = new JDialog();
		mainPanel = new JPanel();
		checkBoxesPanel = new JPanel();
		buttonsPanel = new JPanel();

		checkBoxesPanel.setLayout(new GridLayout(9, 1));
		checkBoxesPanel.add(searchXLSXCheckBox);
		checkBoxesPanel.add(searchXLSCheckBox);
		checkBoxesPanel.add(searchXLTCheckBox);
		checkBoxesPanel.add(searchXMLCheckBox);
		checkBoxesPanel.add(searchCSVCheckBox);
		checkBoxesPanel.add(searchTXTCheckBox);
		checkBoxesPanel.add(searchLOGCheckBox);
		checkBoxesPanel.add(searchDOCXCheckBox);
		checkBoxesPanel.add(searchDOCCheckBox);

		buttonsPanel.setLayout(new GridLayout(1, 2));
		buttonsPanel.add(okButton, "West");
		buttonsPanel.add(cancelButton, "East");

		mainPanel.setLayout(new GridLayout(2, 1));
		mainPanel.add(checkBoxesPanel, "North");
		mainPanel.add(buttonsPanel, "South");

		dialog.add(mainPanel);

		searchXLSXCheckBox.setSelected(searchXLSX);
		searchXLSCheckBox.setSelected(searchXLS);
		searchXLTCheckBox.setSelected(searchXLT);
		searchXMLCheckBox.setSelected(searchXML);
		searchCSVCheckBox.setSelected(searchCSV);
		searchTXTCheckBox.setSelected(searchTXT);
		searchLOGCheckBox.setSelected(searchLOG);
		searchDOCXCheckBox.setSelected(searchDOCX);
		searchDOCCheckBox.setSelected(searchDOC);

		searchXLSXCheckBox.addItemListener(this);
		searchXLSCheckBox.addItemListener(this);
		searchXLTCheckBox.addItemListener(this);
		searchXMLCheckBox.addItemListener(this);
		searchCSVCheckBox.addItemListener(this);
		searchTXTCheckBox.addItemListener(this);
		searchLOGCheckBox.addItemListener(this);
		searchDOCXCheckBox.addItemListener(this);
		searchDOCCheckBox.addItemListener(this);

		okButton.addActionListener(this);
		cancelButton.addActionListener(this);

		windowOptions();
	}

	public void startGUI() {
		searchXLSXCheckBox.setSelected(searchXLSX);
		searchXLSCheckBox.setSelected(searchXLS);
		searchXLTCheckBox.setSelected(searchXLT);
		searchXMLCheckBox.setSelected(searchXML);
		searchCSVCheckBox.setSelected(searchCSV);
		searchTXTCheckBox.setSelected(searchTXT);
		searchLOGCheckBox.setSelected(searchLOG);
		searchDOCXCheckBox.setSelected(searchDOCX);
		searchDOCCheckBox.setSelected(searchDOC);

		searchXLSX = oldSearchXLSX;
		searchXLS = oldSearchXLS;
		searchXLT = oldSearchXLT;
		searchXML = oldSearchXML;
		searchCSV = oldSearchCSV;
		searchTXT = oldSearchTXT;
		searchLOG = oldSearchLOG;
		searchDOCX = oldSearchDOCX;
		searchDOC = oldSearchDOC;

		dialog.setVisible(true);
	}

	private void windowOptions() {
		dialog.setModal(true);
		dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		dialog.setPreferredSize(new Dimension(200, 400));
		dialog.setLocationRelativeTo(null);
		dialog.pack();
		dialog.setLocation(0, 0);
		dialog.setVisible(false);
	}

	public boolean getSearchXLSX() {
		return this.searchXLSX;
	}

	public boolean getSearchXLS() {
		return this.searchXLS;
	}

	public boolean getSearchXLT() {
		return this.searchXLT;
	}

	public boolean getSearchXML() {
		return this.searchXML;
	}

	public boolean getSearchCSV() {
		return this.searchCSV;
	}

	public boolean getSearchTXT() {
		return this.searchTXT;
	}

	public boolean getSearchLOG() {
		return this.searchLOG;
	}

	public boolean getSearchDOCX() {
		return this.searchDOCX;
	}

	public boolean getSearchDOC() {
		return this.searchDOC;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();
		if (source == searchXLSXCheckBox) {
			searchXLSX = !searchXLSX;
		} else if (source == searchXLSCheckBox) {
			searchXLS = !searchXLS;
		} else if (source == searchXLTCheckBox) {
			searchXLT = !searchXLT;
		} else if (source == searchXMLCheckBox) {
			searchXML = !searchXML;
		} else if (source == searchCSVCheckBox) {
			searchCSV = !searchCSV;
		} else if (source == searchTXTCheckBox) {
			searchTXT = !searchTXT;
		} else if (source == searchLOGCheckBox) {
			searchLOG = !searchLOG;
		} else if (source == searchDOCXCheckBox) {
			searchDOCX = !searchDOCX;
		} else if (source == searchDOCCheckBox) {
			searchDOC = !searchDOC;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == okButton) {
			oldSearchXLSX = searchXLSX;
			oldSearchXLS = searchXLS;
			oldSearchXLT = searchXLT;
			oldSearchXML = searchXML;
			oldSearchCSV = searchCSV;
			oldSearchTXT = searchTXT;
			oldSearchLOG = searchLOG;
			oldSearchDOCX = searchDOCX;
			oldSearchDOC = searchDOC;
			dialog.dispose();
		} else if (source == cancelButton) {
			searchXLSX = oldSearchXLSX;
			searchXLS = oldSearchXLS;
			searchXLT = oldSearchXLT;
			searchXML = oldSearchXML;
			searchCSV = oldSearchCSV;
			searchDOCX = oldSearchDOCX;
			searchDOC = oldSearchDOC;
			dialog.dispose();
		}
	}

}
