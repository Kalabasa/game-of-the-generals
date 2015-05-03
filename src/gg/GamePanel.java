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

		for (int i = 0; i < 15; i++) {
			gameLabel.add(PaintPieces(20, (i+1)*33+5, "black", i));
		}
		
		// Set up grid of buttons
		for(int x=0; x<9; x++){
			for(int y=0; y<8; y++){
				JButton tileButton = new JButton(y + "," + x);
				tileButton.setBounds(
					boardLabel.getBounds().x + 8 + x * 64,
					boardLabel.getBounds().y + 8 + y * 64
						+ (y >= 4 ? 4 : 0),
					64, 64);
				gameLabel.add(tileButton);
				
				final int row = y;
				final int col = x;
				tileButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(selectedPiece != null){
							engine.setAPiece(selectedPiece.getTeam(), selectedPiece.getPieceRank(), row, col);
						}
					}
				});
			}
		}

		gameLabel.setComponentZOrder(boardLabel, gameLabel.getComponentCount() - 1);
		add(gameLabel);
	}

	public JLabel PaintPieces(int x, int y, String color, final int rank) {
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
				selectedPiece = new Piece(false, rank); 
			}
		});
		
		pieceLabel.add(pieceButton);
		return pieceLabel;
	}
	
}
