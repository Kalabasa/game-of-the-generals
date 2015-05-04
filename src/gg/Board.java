package gg;

public class Board {

	private Piece[][] piece;
	private final int WIDTH = 9;
	private final int HEIGHT = 8;

	public Board() {
		piece = new Piece[HEIGHT][WIDTH];
		clearBoard();
	}

	public void initializePiece(boolean team, int rank, int row, int col) {
		piece[row][col] = new Piece(team, rank);
	}

	private void clearBoard() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				removePieceFromBoard(i, j);
			}
		}
	}

	public Piece getPieceAt(int row, int col) {
		return piece[row][col];

	}

	public void move(int row, int col, int newRow, int newCol) {
		if (piece[newRow][newCol] == null) {
			piece[newRow][newCol] = piece[row][col];
		} else {
			int result = didChallengerWin(row, col, newRow, newCol);
			if (result == 1) { // challenger won
				piece[newRow][newCol] = piece[row][col];
				removePieceFromBoard(row, col);
			} else if (result == -1) { // defender won
				removePieceFromBoard(row, col);
			} else { // draw
				removePieceFromBoard(newRow, newCol);
				removePieceFromBoard(row, col);
			}
		}
	}

	private int didChallengerWin(int row, int col, int newRow, int newCol) {
		int defender = piece[newRow][newCol].getPieceRank();
		int challenger = piece[row][col].getPieceRank();

		if (challenger == 14 && defender == 1) {
			return -1;
		}
		if (challenger == 1 && defender == 14) {
			return 1;
		}

		if (challenger == defender) {
			if (piece[row][col].getPieceRank() == 0) {
				return 1;
			}
			return 0;
		} else if (challenger > defender) {
			return 1;
		} else {
			return -1;
		}
	}

	public void removePieceFromBoard(int row, int col) {
		piece[row][col] = null;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

}
