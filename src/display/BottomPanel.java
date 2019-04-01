package display;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BottomPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel searchStringLabel;

	private JFormattedTextField searchStringField = new JFormattedTextField();

	private String searchStringString = "String To Search: ";

	private JPanel buttonPanel = new JPanel();

	private JButton startButton = new JButton("Start");
	private JButton openButton = new JButton("Open");

	public BottomPanel() {
		searchStringLabel = new JLabel(searchStringString);

		buttonPanel.setLayout(new GridLayout(1, 2));

		buttonPanel.add(startButton, "West");
		buttonPanel.add(openButton, "West");

		this.setLayout(new GridLayout(1, 3));
		this.add(buttonPanel, "West");
		this.add(searchStringLabel, "Center");
		this.add(searchStringField, "East");
	}

	public String getSearchStringField() {
		return this.searchStringField.getText();
	}

	public JButton getStartButton() {
		return this.startButton;
	}
	
	public JButton getOpenButton() {
		return this.openButton;
	}

}
