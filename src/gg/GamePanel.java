package gg;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Engine engine;

	private JButton grid[][] = new JButton[9][8];
	private List<JButton> gilidPieces = new LinkedList<>();

	private Piece selectedPiece = null;

	private boolean placementPhase = true;


	public GamePanel(final MainFrame mainFrame) {
		super(new FlowLayout(FlowLayout.CENTER, 0, 0));

		engine = new Engine();

		ImageIcon gameBg = new ImageIcon("res/gamebg.png");
		final JLabel gameLabel = new JLabel(gameBg);

		ImageIcon boardBg = new ImageIcon("res/board.png");
		JLabel boardLabel = new JLabel(boardBg);
		boardLabel.setBounds((800 - boardBg.getIconWidth()) / 2,
				(600 - boardBg.getIconHeight()) / 2, boardBg.getIconWidth(),
				boardBg.getIconHeight());
		gameLabel.add(boardLabel);
		
		//Set up a return button
		ImageIcon returnButton = new ImageIcon("res/return2.png");
		
		JButton returnBut = new JButton(returnButton);
		returnBut.setContentAreaFilled(false);
		returnBut.setBorderPainted(false);
		returnBut.setBorder(null);
		returnBut.setBounds(25, 500, returnButton.getIconWidth(),
				returnButton.getIconHeight());
		returnBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.returnToMainMenu();
			}
		});
		
		gameLabel.add(returnBut);
		
		//Set up a sound button
		final ImageIcon onSoundButton = new ImageIcon("res/sounds.png");
		final ImageIcon offSoundButton = new ImageIcon("res/nosounds.png");

		final JButton sound = new JButton(onSoundButton);
		sound.setContentAreaFilled(false);
		sound.setBorderPainted(false);
		sound.setBorder(null);
		sound.setBounds(725, 500, onSoundButton.getIconWidth(),
				onSoundButton.getIconHeight());
		sound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Music.toggleMute();
				Sound.toggleMute();
				Sound.click.play();

				if (Sound.isMuted()) {
					sound.setIcon(offSoundButton);
				} else {
					sound.setIcon(onSoundButton);
				}
			}
		});
		
		gameLabel.add(sound);

		Music.play("Omens.mp3");

		// Set up grid of buttons
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 8; y++) {
				JButton tileButton = grid[x][y] = new JButton();
				tileButton.setOpaque(false);
				tileButton.setContentAreaFilled(false);
				tileButton.setBorderPainted(false);
				tileButton.setBounds(boardLabel.getBounds().x + 8 + x * 64,
						boardLabel.getBounds().y + 8 + y * 64
								+ (y >= 4 ? 4 : 0), 64, 64);
				gameLabel.add(tileButton);

				final int row = y;
				final int col = x;
				tileButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TESTCOED
						if (placementPhase && selectedPiece != null) {
							Sound.click.play();
							engine.setAPiece(selectedPiece.getTeam(),
									selectedPiece.getPieceRank(), row, col);
						}

						updateGrid();
						updateGilidPieces(gameLabel, true);
					}
				});
			}
		}

		engine.getMyPieces(true);
		updateGilidPieces(gameLabel, true);

		gameLabel.setComponentZOrder(boardLabel,
				gameLabel.getComponentCount() - 1);
		add(gameLabel);
	}

	private void updateGilidPieces(JLabel gameLabel, boolean team) {
		for(JButton pb : gilidPieces){
			gameLabel.remove(pb);
		}
		gilidPieces.clear();
		
		int j;
		for (int i = 0; i < 8; i++) {
			j = i + 1;
			if(engine.hasThisPiece(i)){
				JButton piece = PaintPieces(20, j * 50, team ? "white" : "black", i);
				gilidPieces.add(piece);
				gameLabel.add(piece);
			}
		}
		for (int i = 8; i < 15; i++) {
			j = i - 7;
			if(engine.hasThisPiece(i)){
				JButton piece = PaintPieces(715, j * 50, team ? "white" : "black", i);
				gilidPieces.add(piece);
				gameLabel.add(piece);
			}
		}
		
		gameLabel.invalidate();
		gameLabel.repaint();
	}

	public JButton PaintPieces(int x, int y, String color, final int rank) {
		ImageIcon pieceIcon = new ImageIcon(getImagePath(color, rank));
		JButton pieceButton = new JButton(pieceIcon);
		pieceButton.setContentAreaFilled(false);
		pieceButton.setBorderPainted(false);
		pieceButton.setBounds(x, y, pieceIcon.getIconWidth(),
				pieceIcon.getIconHeight());
		pieceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sound.click.play();
				selectedPiece = new Piece(true, rank);
			}
		});

		return pieceButton;
	}

	private String getImagePath(String color, int rank) {
		return "res/" + color + "piece" + rank + ".png";
	}

	public void updateGrid() {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 8; y++) {
				Piece piece = engine.board.getPieceAt(y, x);
				JButton button = grid[x][y];
				if (piece != null) {
					ImageIcon image = null;
					if(engine.getCurrentTurn() == piece.getTeam()){
						image = new ImageIcon(getImagePath(
							piece.getTeam() ? "white" : "black",
							piece.getPieceRank()));
					}else{
						image = new ImageIcon("res/blackpiece.png");
					}
					button.setIcon(image);
				} else {
					button.setIcon(null);
				}
			}
		}
	}
}
