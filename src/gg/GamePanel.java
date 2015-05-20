package gg;

import gg.Engine.InvalidMoveException;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	public class TurnPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		private JLabel text;
		private JButton button;

		public TurnPanel(ActionListener action) {
			super(new FlowLayout(FlowLayout.CENTER, 0, 0));
			text = new JLabel();

			button = new JButton();
			button.setBorder(null);
			button.addActionListener(action);

			add(text);
		}

		public void setTurn(boolean turn) {
			ImageIcon turnBg = new ImageIcon(getClass().getResource(
					(turn ? "/turnwhite.png" : "/turnblack.png")));
			text.setIcon(turnBg);
			ImageIcon okButton = new ImageIcon(getClass().getResource(
					(turn ? "/okwhite.png" : "/ok.png")));
			button.setIcon(okButton);
			button.setBounds(0, 350, okButton.getIconWidth(),
					okButton.getIconHeight());
			text.add(button);
		}

	}

	public class WinPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		private JLabel text;
		private JButton button;

		public WinPanel(ActionListener action) {
			super(new FlowLayout(FlowLayout.CENTER, 0, 0));
			text = new JLabel();

			button = new JButton();
			button.setBorder(null);
			button.addActionListener(action);

			add(text);
		}

		public void setWinner(boolean winner) {
			ImageIcon winBg = new ImageIcon(getClass().getResource(
					(winner ? "/winwhite.png" : "/winblacck.png")));
			text.setIcon(winBg);
			ImageIcon playAgainButton = new ImageIcon(getClass().getResource(
					(winner ? "/playagainwhite.png" : "/playagainblack.png")));
			button.setIcon(playAgainButton);
			button.setBounds(0, 350, playAgainButton.getIconWidth(),
					playAgainButton.getIconHeight());
			text.add(button);
		}

	}

	private static final long serialVersionUID = 1L;

	private Engine engine;

	private TurnPanel turnPanel;
	private WinPanel winPanel;
	private MainFrame mainFrame;

	private JButton grid[][] = new JButton[9][8];
	private List<JButton> gilidPieces = new LinkedList<>();

	private Piece selectedPiece = null;
	private Point selectedCell = null;

	private boolean placementPhase = true;

	JButton done;

	public GamePanel(final MainFrame mainFrame) {
		super(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.mainFrame = mainFrame;

		engine = new Engine();

		turnPanel = new TurnPanel(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setPanel(GamePanel.this);
			}
		});

		winPanel = new WinPanel(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.returnToMainMenu();
			}
		});

		ImageIcon gameBg = new ImageIcon(getClass().getResource("/gamebg.png"));
		final JLabel gameLabel = new JLabel(gameBg);

		ImageIcon boardBg = new ImageIcon(getClass().getResource("/board.png"));
		JLabel boardLabel = new JLabel(boardBg);
		boardLabel.setBounds((800 - boardBg.getIconWidth()) / 2,
				(600 - boardBg.getIconHeight()) / 2, boardBg.getIconWidth(),
				boardBg.getIconHeight());
		gameLabel.add(boardLabel);

		// Set up a return button
		ImageIcon returnButton = new ImageIcon(getClass().getResource(
				"/return2.png"));

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

		// Set up a sound button
		final ImageIcon onSoundButton = new ImageIcon(getClass().getResource(
				"/sounds.png"));
		final ImageIcon offSoundButton = new ImageIcon(getClass().getResource(
				"/nosounds.png"));

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

		// Set up a done button
		done = new JButton("DONE");
		done.setBackground(Color.WHITE);
		done.setBounds(710, 455, 70, 25);
		done.setEnabled(false);
		done.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (engine.getCurrentTurn()) {
					engine.setCurrentTurn(false);
					engine.getMyPieces(false);
					updateGilidPieces(gameLabel, false);
					updateGrid();

					showTurnPanel();
				} else {
					engine.setCurrentTurn(true);
					updateGrid();
					placementPhase = false;

					// ready to play
					showTurnPanel();
				}
			}
		});

		gameLabel.add(done);
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
						Piece piece = engine.board.getPieceAt(row, col);
						if (placementPhase) {
							Sound.click.play();
							if (selectedPiece != null) {
								engine.setAPiece(selectedPiece.getTeam(),
										selectedPiece.getPieceRank(), row, col);
								selectedPiece = null;
							} else {
								if (piece != null
										&& piece.getTeam() == engine
												.getCurrentTurn()) {
									engine.unSetAPiece(row, col);
								}
							}

							updateGilidPieces(gameLabel,
									engine.getCurrentTurn());
						} else {
							if (selectedPiece == null) {
								if (piece != null) {
									Sound.click.play();
									selectedPiece = piece;
									selectedCell = new Point(col, row);
								}
							} else {
								try {
									engine.play(selectedPiece.getTeam(),
											selectedCell.y, selectedCell.x,
											row, col);
									Sound.click.play();
									if (engine.isFinished()) {
										showWinPanel();
									} else {
										showTurnPanel();
									}
								} catch (InvalidMoveException e1) {
									e1.printStackTrace();
								} finally {
									selectedPiece = null;
								}
							}
						}

						updateGrid();
					}
				});
			}
		}

		engine.getMyPieces(true);
		updateGilidPieces(gameLabel, true);

		updateGrid();

		gameLabel.setComponentZOrder(boardLabel,
				gameLabel.getComponentCount() - 1);
		add(gameLabel);
	}

	private void showTurnPanel() {
		mainFrame.setPanel(turnPanel);
		turnPanel.setTurn(engine.getCurrentTurn());
	}

	private void showWinPanel() {
		mainFrame.setPanel(winPanel);
		winPanel.setWinner(engine.getWinner());
	}

	private void updateGilidPieces(JLabel gameLabel, boolean team) {
		for (JButton pb : gilidPieces) {
			gameLabel.remove(pb);
		}
		gilidPieces.clear();

		int j;
		for (int i = 0; i < 8; i++) {
			j = i + 1;
			if (engine.hasThisPiece(i)) {
				JButton piece = PaintPieces(20, j * 50, team ? "white"
						: "black", i);
				gilidPieces.add(piece);
				gameLabel.add(piece);
			}
		}
		for (int i = 8; i < 15; i++) {
			j = i - 7;
			if (engine.hasThisPiece(i)) {
				JButton piece = PaintPieces(715, j * 50, team ? "white"
						: "black", i);
				gilidPieces.add(piece);
				gameLabel.add(piece);
			}
		}

		gameLabel.invalidate();
		gameLabel.repaint();
	}

	public JButton PaintPieces(int x, int y, final String color, final int rank) {
		ImageIcon pieceIcon = new ImageIcon(getClass().getResource(
				getImagePath(color, rank)));
		JButton pieceButton = new JButton(pieceIcon);
		pieceButton.setContentAreaFilled(false);
		pieceButton.setBorderPainted(false);
		pieceButton.setBounds(x, y, pieceIcon.getIconWidth(),
				pieceIcon.getIconHeight());
		pieceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sound.click.play();
				selectedPiece = new Piece(color.equals("white") ? true : false,
						rank);
			}
		});

		return pieceButton;
	}

	private String getImagePath(String color, int rank) {
		return "/" + color + "piece" + rank + ".png";
	}

	public void updateGrid() {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 8; y++) {
				Piece piece = engine.board.getPieceAt(y, x);
				JButton button = grid[x][y];
				if (piece != null) {
					ImageIcon image = null;
					if (engine.getCurrentTurn() == piece.getTeam()) {
						image = new ImageIcon(getClass().getResource(
								getImagePath(piece.getTeam() ? "white"
										: "black", piece.getPieceRank())));
					} else {
						image = new ImageIcon(getClass().getResource(
								"/" + (piece.getTeam() ? "white" : "black")
										+ "piece.png"));
					}
					button.setIcon(image);
				} else {
					button.setIcon(null);
				}
			}
		}

		if (engine.isPieceListEmpty()) {
			done.setEnabled(true);
		} else {
			done.setEnabled(false);
		}
	}
}
