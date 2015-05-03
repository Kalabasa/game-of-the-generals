package gg;

public class Board {
	
	private Piece[][] piece;
	private final int WIDTH = 9;
	private final int HEIGHT = 8;

	private final int[] STARTING_PIECES = {0, 1, 1, 1, 1, 1, 1, 2, 3, 4, 5, 6, 7,
										8, 9, 10, 11, 12, 13, 14, 14};
	
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
				piece[i][j] = null;
			}
		}
	}
	
	public Piece getPieceAt(int row, int col){
		return piece[row][col];
		
	}
	
	public void move(int row, int col, int newRow, int newCol){
		if(piece[newRow][newCol] == null){
			piece[newRow][newCol] = piece[row][col];
		} else{
			int result = didChallengerWin(row, col, newRow, newCol);
			if (result == 1) { 					// challenger won
				piece[newRow][newCol] = piece[row][col];
				piece[row][col] = null;
			} else if (result == -1) { 			// defender won
				piece[row][col] = null;
			} else { 							// draw
				piece[newRow][newCol] = null;
				piece[row][col] = null;
			}
		}
	}
	
	private int didChallengerWin(int row, int col, int newRow, int newCol) {
		int defender = piece[newRow][newCol].getPieceRank();
		int challenger = piece[row][col].getPieceRank();
		
		if(challenger == 14 && defender == 1) {
			return -1;
		}
		if(challenger == 1 && defender == 14) {
			return 1;
		}
			
		if (challenger == defender) {
			if(piece[row][col].getPieceRank() == 0){
				return 1;
			}
			return 0;
		} else if (challenger > defender) {
			return 1;
		} else {
			return -1;
		}
	}

	public int[] getSTARTING_PIECES() {
		return STARTING_PIECES;
	}
	
	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

}
