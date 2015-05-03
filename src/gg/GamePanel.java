package gg;

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
	
	private JButton grid[][] = new JButton[9][8];

	private Piece selectedPiece = null;
	
	private static final ImageIcon images[][] = new ImageIcon[][]{
		{new ImageIcon("/res/blackpiece0.png"), new ImageIcon("/res/whitepiece0.png")},
		{new ImageIcon("/res/blackpiece1.png"), new ImageIcon("/res/whitepiece1.png")},
		{new ImageIcon("/res/blackpiece2.png"), new ImageIcon("/res/whitepiece2.png")},
		{new ImageIcon("/res/blackpiece3.png"), new ImageIcon("/res/whitepiece3.png")},
		{new ImageIcon("/res/blackpiece4.png"), new ImageIcon("/res/whitepiece4.png")},
		{new ImageIcon("/res/blackpiece5.png"), new ImageIcon("/res/whitepiece5.png")},
		{new ImageIcon("/res/blackpiece6.png"), new ImageIcon("/res/whitepiece6.png")},
		{new ImageIcon("/res/blackpiece7.png"), new ImageIcon("/res/whitepiece7.png")},
		{new ImageIcon("/res/blackpiece8.png"), new ImageIcon("/res/whitepiece8.png")},
		{new ImageIcon("/res/blackpiece9.png"), new ImageIcon("/res/whitepiece9.png")},
		{new ImageIcon("/res/blackpiece10.png"), new ImageIcon("/res/whitepiece10.png")},
		{new ImageIcon("/res/blackpiece11.png"), new ImageIcon("/res/whitepiece11.png")},
		{new ImageIcon("/res/blackpiece12.png"), new ImageIcon("/res/whitepiece12.png")},
		{new ImageIcon("/res/blackpiece13.png"), new ImageIcon("/res/whitepiece13.png")},
		{new ImageIcon("/res/blackpiece14.png"), new ImageIcon("/res/whitepiece14.png")}
	};

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
		for (int i = 0; i < 8; i++) {
			j = i + 1;
			gameLabel.add(PaintPieces(20, j * 50, "black", i));
		}
		for (int i = 8; i < 15; i++) {
			j = i - 7;
			gameLabel.add(PaintPieces(715, j * 50, "black", i));
		}

		// Set up grid of buttons
		for(int x=0; x<9; x++){
			for(int y=0; y<8; y++){
				JButton tileButton = grid[x][y] = new JButton(y + "," + x);
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

		gameLabel.setComponentZOrder(boardLabel,
				gameLabel.getComponentCount() - 1);
		add(gameLabel);
	}
	
	public JButton PaintPieces(int x, int y, String color, final int rank) {
		ImageIcon pieceBg = images[rank][color == "white" ? 1 : 0];
		JLabel pieceLabel = new JLabel(pieceBg);
		ImageIcon pieceIcon = new ImageIcon("res/" + color + "piece" + rank + ".png");
		JButton pieceButton = new JButton(pieceIcon);
		pieceButton.setContentAreaFilled(false);
		pieceButton.setBorderPainted(false);
		pieceButton.setBorder(null);
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

		return pieceButton;
	}

	public void updateGrid(){
		for(int x=0; x<9; x++){
			for(int y=0; y<8; y++){
				Piece piece = engine.board.getPieceAt(y, x);
				JButton button = grid[x][y];
				if(piece != null){
					ImageIcon image = images[piece.getPieceRank()][piece.getTeam() ? 1 : 0];
					button.setIcon(image);
				}else{
					button.setIcon(null);
				}
			}
		}
	}
}
