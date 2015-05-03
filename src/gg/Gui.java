package gg;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Gui extends JFrame {

	MenuPanel menuPanel;

	public Gui() {
		setTitle("Game of the Generals");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout());

		menuPanel = new MenuPanel();
		add(menuPanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

