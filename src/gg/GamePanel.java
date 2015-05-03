package gg;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public GamePanel(final MainFrame mainFrame) {
		super(new FlowLayout(FlowLayout.CENTER, 0, 0));

		ImageIcon gameBg = new ImageIcon("res/gamebg.png");
		JLabel gameLabel = new JLabel(gameBg);

		ImageIcon boardBg = new ImageIcon("res/board.png");
		JLabel boardLabel = new JLabel(boardBg);
		boardLabel.setBounds((800 - boardBg.getIconWidth()) / 2,
				(570 - boardBg.getIconHeight()) / 2, boardBg.getIconWidth(),
				boardBg.getIconHeight());
		gameLabel.add(boardLabel);

		add(gameLabel);
	}
}
