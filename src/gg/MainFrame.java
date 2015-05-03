package gg;

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

		menuPanel = new MenuPanel(this);
		setPanel(menuPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void setPanel(JPanel panel){
		if(currentPanel != null)
			remove(currentPanel);
		currentPanel = panel;
		add(panel);

		revalidate();
		repaint();
	}
	
	public void newGame(){
		gamePanel = new GamePanel(this);
		setPanel(gamePanel);
	}
}
