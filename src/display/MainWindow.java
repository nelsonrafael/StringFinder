package display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import miscellaneous.*;
import readers.ReadDOC;
import readers.ReadDOCX;
import readers.ReadTXT;
import readers.ReadXLS;
import readers.ReadXLSX;
import readers.ReadXLT;

public class MainWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MenuBar menuBar = new MenuBar();

	private TopPanel topPanel = new TopPanel();

	private CenterPanel centerPanel = new CenterPanel();

	private BottomPanel bottomPanel = new BottomPanel();

	public MainWindow() {
		this.setJMenuBar(menuBar);
		this.add(topPanel, "North");
		this.add(centerPanel, "Center");
		this.add(bottomPanel, "South");

		bottomPanel.getStartButton().addActionListener(this);
		bottomPanel.getOpenButton().addActionListener(this);
	}

	private boolean findFilesAndDirectories(String path, DefaultMutableTreeNode node) {
		boolean fileExists = false;

		try {
			ListDirectory ld = new ListDirectory(path);

			for (int i = 0; i < ld.getFiles().length; i++) {

				if (ld.getFiles()[i].isFile()) {
					int index = ld.getFiles()[i].getName().lastIndexOf(".");

					if (index > 0) {
						String fileFormat = ld.getFiles()[i].getName()
								.substring(index, ld.getFiles()[i].getName().length()).toLowerCase();

						switch (fileFormat) {
						case ".xlsx":
							if (menuBar.getFilePreferencesWindow().getSearchXLSX()) {
								ReadXLSX objXLSX = new ReadXLSX(path + "\\" + ld.getFiles()[i].getName());
								if (objXLSX.findString(bottomPanel.getSearchStringField(),
										menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
									centerPanel.addObject(node, ld.getFiles()[i].getName());
									fileExists = true;
								}
							}
							break;

						case ".xls":
							if (menuBar.getFilePreferencesWindow().getSearchXLS()) {
								ReadXLS objXLS = new ReadXLS(path + "\\" + ld.getFiles()[i].getName());
								if (objXLS.findString(bottomPanel.getSearchStringField(),
										menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
									centerPanel.addObject(node, ld.getFiles()[i].getName());
									fileExists = true;
								}
							}
							break;

						case ".xlt":
							if (menuBar.getFilePreferencesWindow().getSearchXLT()) {
								ReadXLT objXLT = new ReadXLT(path + "\\" + ld.getFiles()[i].getName());
								if (objXLT.findString(bottomPanel.getSearchStringField(),
										menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
									centerPanel.addObject(node, ld.getFiles()[i].getName());
									fileExists = true;
								}
							}
							break;

						case ".xml":
							if (menuBar.getFilePreferencesWindow().getSearchXML()) {
								ReadTXT objXML = new ReadTXT(path + "\\" + ld.getFiles()[i].getName());
								if (objXML.findString(bottomPanel.getSearchStringField(),
										menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
									centerPanel.addObject(node, ld.getFiles()[i].getName());
									fileExists = true;
								}
							}
							break;

						case ".csv":
							if (menuBar.getFilePreferencesWindow().getSearchCSV()) {
								ReadTXT objCSV = new ReadTXT(path + "\\" + ld.getFiles()[i].getName());
								if (objCSV.findString(bottomPanel.getSearchStringField(),
										menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
									centerPanel.addObject(node, ld.getFiles()[i].getName());
									fileExists = true;
								}
							}
							break;

						case ".log":
							if (menuBar.getFilePreferencesWindow().getSearchLOG()) {
								ReadTXT objLOG = new ReadTXT(path + "\\" + ld.getFiles()[i].getName());
								if (objLOG.findString(bottomPanel.getSearchStringField(),
										menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
									centerPanel.addObject(node, ld.getFiles()[i].getName());
									fileExists = true;
								}
							}
							break;

						case ".txt":
							if (menuBar.getFilePreferencesWindow().getSearchTXT()) {
								ReadTXT objTXT = new ReadTXT(path + "\\" + ld.getFiles()[i].getName());
								if (objTXT.findString(bottomPanel.getSearchStringField(),
										menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
									centerPanel.addObject(node, ld.getFiles()[i].getName());
									fileExists = true;
								}
							}
							break;

						case ".docx":
							if (menuBar.getFilePreferencesWindow().getSearchDOCX()) {
								ReadDOCX objDOCX = new ReadDOCX(path + "\\" + ld.getFiles()[i].getName());
								if (objDOCX.findString(bottomPanel.getSearchStringField(),
										menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
									centerPanel.addObject(node, ld.getFiles()[i].getName());
									fileExists = true;
								}
							}
							break;

						case ".doc":
							if (menuBar.getFilePreferencesWindow().getSearchDOC()) {
								ReadDOC objDOC = new ReadDOC(path + "\\" + ld.getFiles()[i].getName());
								if (objDOC.findString(bottomPanel.getSearchStringField(),
										menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
									centerPanel.addObject(node, ld.getFiles()[i].getName());
									fileExists = true;
								}
							}
							break;

						default:
							break;
						}

					}

				} else if (ld.getFiles()[i].isDirectory()
						&& menuBar.getSearchPreferencesWindow().getSearchSubFolders()) {
					DefaultMutableTreeNode newNode = centerPanel.addObject(node, ld.getFiles()[i].getName());
					if (!fileExists)
						fileExists = findFilesAndDirectories(path + "\\" + ld.getFiles()[i].getName(), newNode);
					else
						findFilesAndDirectories(path + "\\" + ld.getFiles()[i].getName(), newNode);
				}
			}

			if (!fileExists) {
				centerPanel.removeNode(node);
			}

		} catch (NullPointerException e) {
			try {
				centerPanel.removeNode(node);
			} catch (NullPointerException f) {
				System.out.println("ERROR MainWindow - NullPointerException (removing node)");
			}
			System.out.println("ERROR MainWindow - NullPointerException (can be ignored)");
		}

		return fileExists;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == bottomPanel.getStartButton()) {
			// TODO
			String textMessage = "", textTitle = "";

			boolean error = false, question = false, warning = false;

			if (topPanel.getCurrentWorkDirField().equals("No Directory Selected")) {
				error = true;
				textMessage += "No Directory Selected!\n";
				textTitle = "ERROR";
			}

			if (bottomPanel.getSearchStringField().equals("")) {
				error = true;
				textMessage += "No Search String!\n";
				textTitle = "ERROR";
			}

			if (error) {
				JOptionPane.showMessageDialog(this, textMessage, textTitle, JOptionPane.ERROR_MESSAGE);
				return;
			} else if (warning) {

			} else if (question) {

			}

			centerPanel.clear();
			DefaultMutableTreeNode node = centerPanel.addObject(null, topPanel.getCurrentWorkDirField());
			findFilesAndDirectories(topPanel.getCurrentWorkDirField(), node);
		} else if (source == bottomPanel.getOpenButton()) {
			String filePath = centerPanel.getCurrentNode();
			int index = filePath.lastIndexOf(".");

			if (index > 0) {
				String fileFormat = filePath.substring(index, filePath.length()).toLowerCase();

				if (fileFormat.equals(".xlsx") || fileFormat.equals(".xls") || fileFormat.equals(".xlt")
						|| fileFormat.equals(".csv") || fileFormat.equals(".xml") || fileFormat.equals(".log")
						|| fileFormat.equals(".txt") || fileFormat.equals(".docx") || fileFormat.equals(".doc")) {
					openFile(filePath);
				}
			}
		}
	}

	public void openFile(String filePath) {
		ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", filePath);
		try {
			pb.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
