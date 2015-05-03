package gg;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui extends JFrame {

	JPanel mainPanel;
	MenuPanel menuPanel;

	public Gui() {
		setTitle("Game of the Generals");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout());

		mainPanel = new JPanel();
		menuPanel = new MenuPanel(mainPanel);
		mainPanel.add(menuPanel);
		add(mainPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
