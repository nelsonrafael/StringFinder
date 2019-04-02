package display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.record.RecordInputStream.LeftoverDataException;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;

import miscellaneous.*;
import readers.ReadDOC;
import readers.ReadDOCX;
import readers.ReadTXT;
import readers.ReadXLS;
import readers.ReadXLSM;
import readers.ReadXLSX;
import readers.ReadXLT;

public class MainWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MenuBar menuBar = new MenuBar(this);

	private TopPanel topPanel = new TopPanel();

	private CenterPanel centerPanel = new CenterPanel();

	private BottomPanel bottomPanel = new BottomPanel();

	private JTextArea consoleArea = new JTextArea();

	private TextAreaOutputStream consoleOutputStream = new TextAreaOutputStream(consoleArea, 10000);

	private JSplitPane midSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, centerPanel,
			new JScrollPane(consoleArea));

	public MainWindow() {
		midSplitPane.setResizeWeight(.305d);

		PrintStream printStream = new PrintStream(consoleOutputStream);
		System.setOut(printStream);
		System.setErr(printStream);

		this.setJMenuBar(menuBar);
		this.add(topPanel, "North");
		this.add(midSplitPane, "Center");
		this.add(bottomPanel, "South");

		bottomPanel.getStartButton().addActionListener(this);
		bottomPanel.getOpenButton().addActionListener(this);
	}

	private boolean findFilesAndDirectories(String path, DefaultMutableTreeNode node) {
		boolean fileExists = false;
		Object obj = null;
		try {
			ListDirectory ld = new ListDirectory(path);
			for (int i = 0; i < ld.getFiles().length; i++) {
				if (ld.getFiles()[i].isFile()) {
					int index = ld.getFiles()[i].getName().lastIndexOf(".");
					if (index > 0) {
						String fileFormat = ld.getFiles()[i].getName()
								.substring(index, ld.getFiles()[i].getName().length()).toLowerCase();
						try {
							switch (fileFormat) {
							case ".xlsx":
								if (menuBar.getFilePreferencesWindow().getSearchXLSX()) {
									obj = new ReadXLSX(path + "\\" + ld.getFiles()[i].getName());
									if (((ReadXLSX) obj).findString(bottomPanel.getSearchStringField(),
											menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
										centerPanel.addObject(node, ld.getFiles()[i].getName());
										fileExists = true;
									}
								}
								break;
							case ".xlsm":
								if (menuBar.getFilePreferencesWindow().getSearchXLSM()) {
									obj = new ReadXLSM(path + "\\" + ld.getFiles()[i].getName());
									if (((ReadXLSM) obj).findString(bottomPanel.getSearchStringField(),
											menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
										centerPanel.addObject(node, ld.getFiles()[i].getName());
										fileExists = true;
									}
								}
								break;
							case ".xls":
								if (menuBar.getFilePreferencesWindow().getSearchXLS()) {
									obj = new ReadXLS(path + "\\" + ld.getFiles()[i].getName());
									if (((ReadXLS) obj).findString(bottomPanel.getSearchStringField(),
											menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
										centerPanel.addObject(node, ld.getFiles()[i].getName());
										fileExists = true;
									}
								}
								break;
							case ".xlt":
								if (menuBar.getFilePreferencesWindow().getSearchXLT()) {
									obj = new ReadXLT(path + "\\" + ld.getFiles()[i].getName());
									if (((ReadXLT) obj).findString(bottomPanel.getSearchStringField(),
											menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
										centerPanel.addObject(node, ld.getFiles()[i].getName());
										fileExists = true;
									}
								}
								break;
							case ".xml":
								if (menuBar.getFilePreferencesWindow().getSearchXML()) {
									obj = new ReadTXT(path + "\\" + ld.getFiles()[i].getName());
									if (((ReadTXT) obj).findString(bottomPanel.getSearchStringField(),
											menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
										centerPanel.addObject(node, ld.getFiles()[i].getName());
										fileExists = true;
									}
								}
								break;
							case ".csv":
								if (menuBar.getFilePreferencesWindow().getSearchCSV()) {
									obj = new ReadTXT(path + "\\" + ld.getFiles()[i].getName());
									if (((ReadTXT) obj).findString(bottomPanel.getSearchStringField(),
											menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
										centerPanel.addObject(node, ld.getFiles()[i].getName());
										fileExists = true;
									}
								}
								break;
							case ".log":
								if (menuBar.getFilePreferencesWindow().getSearchLOG()) {
									obj = new ReadTXT(path + "\\" + ld.getFiles()[i].getName());
									if (((ReadTXT) obj).findString(bottomPanel.getSearchStringField(),
											menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
										centerPanel.addObject(node, ld.getFiles()[i].getName());
										fileExists = true;
									}
								}
								break;
							case ".txt":
								if (menuBar.getFilePreferencesWindow().getSearchTXT()) {
									obj = new ReadTXT(path + "\\" + ld.getFiles()[i].getName());
									if (((ReadTXT) obj).findString(bottomPanel.getSearchStringField(),
											menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
										centerPanel.addObject(node, ld.getFiles()[i].getName());
										fileExists = true;
									}
								}
								break;
							case ".docx":
								if (menuBar.getFilePreferencesWindow().getSearchDOCX()) {
									obj = new ReadDOCX(path + "\\" + ld.getFiles()[i].getName());
									if (((ReadDOCX) obj).findString(bottomPanel.getSearchStringField(),
											menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
										centerPanel.addObject(node, ld.getFiles()[i].getName());
										fileExists = true;
									}
								}
								break;
							case ".doc":
								if (menuBar.getFilePreferencesWindow().getSearchDOC()) {
									obj = new ReadDOC(path + "\\" + ld.getFiles()[i].getName());
									if (((ReadDOC) obj).findString(bottomPanel.getSearchStringField(),
											menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
										centerPanel.addObject(node, ld.getFiles()[i].getName());
										fileExists = true;
									}
								}
								break;
							default:
								break;
							}
							obj = null;
						} catch (NotOfficeXmlFileException e) {
							System.out.println("NotOfficeXmlFileException (No valid entries or contents "
									+ "found, this is not a valid OOXML (Office Open XML) file) - " + path + "\\"
									+ ld.getFiles()[i].getName());
							continue;
						} catch (FileNotFoundException e) {
							System.out.println("FileNotFoundException (Process can not open file) - " + path + "\\"
									+ ld.getFiles()[i].getName());
							continue;
						} catch (LeftoverDataException e) {
							System.out.println("LeftoverDataException (Left 1/X bytes remaining still to be read) - "
									+ path + "\\" + ld.getFiles()[i].getName());
							continue;
						} catch (EncryptedDocumentException e) {
							System.out.println(
									"EncryptedDocumentException (Encryption not supported for Old Excel files) - "
											+ path + "\\" + ld.getFiles()[i].getName());
							continue;
						} catch (IllegalArgumentException e) {
							/*
							 * System.out.println(
							 * "IllegalArgumentException (The document is really a UNKNOWN/RTF/... file) -"
							 * + path + "\\" + ld.getFiles()[i].getName());
							 */
							obj = new ReadTXT(path + "\\" + ld.getFiles()[i].getName());
							try {
								if (((ReadTXT) obj).findString(bottomPanel.getSearchStringField(),
										menuBar.getSearchPreferencesWindow().getCaseSensitive())) {
									centerPanel.addObject(node, ld.getFiles()[i].getName());
									fileExists = true;
								}
							} catch (Exception g) {
								System.out.println("IllegalArgumentException generated another exception " + path + "\\"
										+ ld.getFiles()[i].getName());
								g.printStackTrace();
							}
							continue;
						} catch (POIXMLException e) {
							System.out.println(
									"POIXMLException (Strict OOXML isn't currently supported, please see bug #57699) - "
											+ path + "\\" + ld.getFiles()[i].getName());
							continue;
						} catch (Exception e) {
							System.out.println("UNDEFINED ERROR AT " + path + "\\" + ld.getFiles()[i].getName());
							e.printStackTrace();
							continue;
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
			} catch (NullPointerException g) {
				System.out.println("NullPointerException (removing node)");
			} catch (Exception g) {
				System.out.println("SOMETHING HAS GONE TERRIBLY WRONG!");
				e.printStackTrace();
				g.printStackTrace();
			}
			// System.out.println("NullPointerException (can be ignored)");
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
				textTitle = "Error";
			}
			if (bottomPanel.getSearchStringField().equals("")) {
				error = true;
				textMessage += "No Search String!\n";
				textTitle = "Error";
			}
			if (error) {
				JOptionPane.showMessageDialog(this, textMessage, textTitle, JOptionPane.ERROR_MESSAGE);
				return;
			} else if (warning) {
				// TODO
			} else if (question) {
				// TODO
			}
			centerPanel.clear();
			DefaultMutableTreeNode node = centerPanel.addObject(null, topPanel.getCurrentWorkDirField());
			boolean result = findFilesAndDirectories(topPanel.getCurrentWorkDirField(), node);
			if (!result) {
				JOptionPane.showMessageDialog(this, "No results found!", "", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		} else if (source == bottomPanel.getOpenButton()) {
			String filePath = null;
			int index = 0;
			try {
				filePath = centerPanel.getCurrentNode();
				index = filePath.lastIndexOf(".");
			} catch (NullPointerException g) {
				JOptionPane.showMessageDialog(this, "No file selected!", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			} catch (Exception g) {
				System.out.println("UNDENTIFIED ERROR");
				g.printStackTrace();
			}
			if (index > 0) {
				String fileFormat = filePath.substring(index, filePath.length()).toLowerCase();
				if (fileFormat.equals(".xlsx") || fileFormat.equals(".xlsm") || fileFormat.equals(".xls")
						|| fileFormat.equals(".xlt") || fileFormat.equals(".csv") || fileFormat.equals(".xml")
						|| fileFormat.equals(".log") || fileFormat.equals(".txt") || fileFormat.equals(".docx")
						|| fileFormat.equals(".doc")) {
					openFile(filePath);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Please select a valid file!", "Warning",
						JOptionPane.WARNING_MESSAGE);
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
