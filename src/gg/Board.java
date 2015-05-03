package gg;

public class Board {
	
	private Piece[][] piece;
	
	int team = {1,1,2,3,4,5,6,7 	};
	
	public Board() {
		piece = new Piece[8][9];
		initializePieces();
	}

	
	
	
	
	private void initializePiece(int rank, int x, int y) {
		piece[x][y] = new Piece();
	}
	
	public void move(int x, int y, int newX, int newY) {
		if (piece[newX][newY] != null) {
			int result = didChallengerWin(x, y, newX, newY);
			if (result == 1) {
				
			} else if (result == -1) {
				
			} else { // draw
				
			}
		} else {
			// move to new position
			piece[newX][newY] = piece[x][y];
			piece[x][y] = null;
			
		}
	}





	private int didChallengerWin(int x, int y, int newX, int newY) {
		int rank1 = piece[newX][newY].getPieceRank();
		int
		piece[x][y]
		int result = 0;
		
		
		
		return result;
	}
	

}
