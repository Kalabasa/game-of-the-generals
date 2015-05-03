package gg;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	JPanel mainPanel;
	
	JPanel currentPanel = null;
	MenuPanel menuPanel;
	GamePanel gamePanel;

	public MainFrame() {
		setTitle("Game of the Generals");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout());

		mainPanel = new JPanel();
		add(mainPanel);
		
		menuPanel = new MenuPanel(this);
		setPanel(menuPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void setPanel(JPanel panel){
		if(currentPanel != null)
			mainPanel.remove(currentPanel);
		currentPanel = panel;
		mainPanel.add(panel);

		revalidate();
		repaint();
	}
	
	public void newGame(){
		gamePanel = new GamePanel(this);
		setPanel(gamePanel);
	}
}
