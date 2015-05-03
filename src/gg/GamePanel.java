package gg;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	public GamePanel() {
		ImageIcon boardBg = new ImageIcon("res/board.png");
		JLabel boardLabel = new JLabel(boardBg);
		add(boardLabel);
	}

}
