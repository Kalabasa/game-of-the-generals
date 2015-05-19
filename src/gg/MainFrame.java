package gg;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	JPanel mainPanel;
	
	JPanel currentPanel = null;
	MenuPanel menuPanel;
	GamePanel gamePanel;
	InstructionsPanel instructionsPanel;
	InstructionsPanel2 instructionsPanel2;

	public MainFrame() {
		setTitle("Game of the Generals");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);

		returnToMainMenu();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void setPanel(JPanel panel){
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
	
	public void instructions(){
		instructionsPanel = new InstructionsPanel(this);
		setPanel(instructionsPanel);
	}
	
	public void instructions2(){
		instructionsPanel2 = new InstructionsPanel2(this);
		setPanel(instructionsPanel2);
	}
	
	public void returnToMainMenu() {
		menuPanel = new MenuPanel(this);
		setPanel(menuPanel);
	}
}
