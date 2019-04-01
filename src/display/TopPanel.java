package display;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

public class TopPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel currentWorkDirLabel;

	private JFormattedTextField currentWorkDirField = new JFormattedTextField();

	private String currentWorkDirString = "Current Directory: ";

	private JButton browseButton = new JButton("Browse");

	private JFileChooser fileChooser;

	public TopPanel() {
		currentWorkDirLabel = new JLabel(currentWorkDirString);

		currentWorkDirField.setText("No Directory Selected");
		currentWorkDirField.setEditable(false);
		currentWorkDirField.setForeground(Color.red);

		this.setLayout(new GridLayout(1, 3));
		this.add(browseButton, "West");
		this.add(currentWorkDirLabel, "Center");
		this.add(currentWorkDirField, "East");

		browseButton.addActionListener(this);
	}

	public String getCurrentWorkDirField() {
		return this.currentWorkDirField.getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == browseButton) {
			fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(FileSystemView.getFileSystemView().getDefaultDirectory());
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.setAcceptAllFileFilterUsed(false);
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				currentWorkDirField.setText((fileChooser.getSelectedFile().getPath()));
				currentWorkDirField.setForeground(Color.black);
			}
		}
	}

}
