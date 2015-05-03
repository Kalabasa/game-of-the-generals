package gg;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Engine engine;
	
	private Piece selectedPiece = null;

	public GamePanel(final MainFrame mainFrame) {
		super(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		engine = new Engine();

		ImageIcon gameBg = new ImageIcon("res/gamebg.png");
		JLabel gameLabel = new JLabel(gameBg);

		ImageIcon boardBg = new ImageIcon("res/board.png");
		JLabel boardLabel = new JLabel(boardBg);
		boardLabel.setBounds((800 - boardBg.getIconWidth()) / 2,
				(570 - boardBg.getIconHeight()) / 2, boardBg.getIconWidth(),
				boardBg.getIconHeight());
		gameLabel.add(boardLabel);

		int j;
		for (int i = 0; i < 15; i++) {
			j = i+1;
			gameLabel.add(PaintPieces(20, j*33+5, "black", i));
		}
		
		add(gameLabel);
	}

	public JLabel PaintPieces(int x, int y, String color, int rank) {
		ImageIcon pieceBg = new ImageIcon("res/" + color + "piece" + ".png");
		JLabel pieceLabel = new JLabel(pieceBg);
		ImageIcon pieceIcon = new ImageIcon("res/" + color + "piece" + rank + ".png");
		JButton pieceButton = new JButton(pieceIcon);
		pieceButton.setBackground(Color.WHITE);
		pieceButton.setBounds(x, y, pieceIcon.getIconWidth(),
				pieceIcon.getIconHeight());
		pieceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("YAAY");
				Sound.click.play();
				//selectedPiece = 
			}
		});
		
		pieceLabel.add(pieceButton);
		return pieceLabel;
	}
	
}
