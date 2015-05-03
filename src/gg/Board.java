package gg;

public class Board {
	
	private Piece[][] piece;
	private final int WIDTH = 9;
	private final int HEIGHT = 8;

	private final int[] STARTING_PIECES = {0, 1, 1, 1, 1, 1, 1, 2, 3, 4, 5, 6, 7,
										8, 9, 10, 11, 12, 13, 14, 14};
	
	public Board() {
		piece = new Piece[HEIGHT][WIDTH];
		initializePieces();
	}
	
	

	public void initializePiece(boolean team, int rank, int x, int y) {
		piece[x][y] = new Piece(team, rank);
	}
	
	private void initializePieces() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				piece[i][j] = null;
			}
		}
	}
	
	public Piece getPieceAt(int x, int y){
		return piece[x][y];
		
	}
	
	public void move(int x, int y, int newX, int newY){
		if(piece[newX][newY] == null){
			piece[newX][newY] = piece[x][y];
		} else{
			int result = didChallengerWin(x, y, newX, newY);
			if (result == 1) { 					// challenger won
				piece[newX][newY] = piece[x][y];
				piece[x][y] = null;
			} else if (result == -1) { 			// defender won
				piece[x][y] = null;
			} else { 							// draw
				piece[newX][newY] = null;
				piece[x][y] = null;
			}
		}
	}
	
	private int didChallengerWin(int x, int y, int newX, int newY) {
		int defender = piece[newX][newY].getPieceRank();
		int challenger = piece[x][y].getPieceRank();
		
		if(challenger == 14 && defender == 1) {
			return -1;
		}
		if(challenger == 1 && defender == 14) {
			return 1;
		}
			
		if (challenger == defender) {
			if(piece[x][y].getPieceRank() == 0){
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
