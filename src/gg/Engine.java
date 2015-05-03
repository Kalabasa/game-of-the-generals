package gg;

public class Engine {
	
	Board board;
	boolean isFinished;
	boolean winner; // 1 = white, 0 - black
	//Piece pieceToMove = new Piece();
	/*
	
		while game is running
			accept user input
			if (isMovePossible)
				move
			else
				check for another input
			after user moves
	
				
	*/
	public Engine(){

	}
	
	public int[] start() {
		board = new Board();
		isFinished = false;
		return board.getSTARTING_PIECES();
		
	}
	
	public boolean setAPiece(boolean team, int rank, int x, int y) {
		if (validPlacement(team, x, y)) {
			board.initializePiece(team, rank, x, y);
			return true;
		} else {
			return false;
		}
	}
	
	private boolean validPlacement(boolean team, int x, int y) {
		int range = 3;
	
		if (team) { // white
			if (x < range && x >= 0) {
				return true;
			} else {
				return false;
			}
		} else {
			if (x < board.getHEIGHT() && x >= board.getHEIGHT() - range) {
				return true;
			} else {
				return false;
			}
		}
	}

	public void play(boolean team, int x, int y, int newX, int newY) {
		boolean isChallengerFlag = false, isDefenderFlag = false;
		
		// TODO get user input
		
		if (isMovePossible(team, x, y, newX, newY)){
			if (board.getPieceAt(x,y).getPieceRank() == 0){
				isChallengerFlag = true;
				if ((team && newX == board.getHEIGHT() - 1 || !team && newX == 0) && 
						(board.getPieceAt(newX, newY + 1) != null || board.getPieceAt(newX, newY - 1) != null)) {
					//TODO insert prompt here 
					return;
				}
			} 
			if (board.getPieceAt(newX,newY).getPieceRank() == 0){
				isDefenderFlag = true;
			}
			
			if (isDefenderFlag){
				isFinished = true;
				winner = team;
				
			} else if (!isDefenderFlag && isChallengerFlag){
				isFinished = true;
				winner = !team;
			}
			board.move(x, y, newX, newY);
		} else {
			//TODO insert prompt here
		}
	}
	
	public void gameOver(boolean result) {
		
	}
	
	private boolean isMovePossible(boolean team, int x, int y, int newX, int newY) {
		if ((board.getPieceAt(x, y).getTeam() != team) // check if you are moving your own piece
			|| (team == board.getPieceAt(newX, newY).getTeam())) { // check if you are eating your own piece
				return false;
		} else {
			return true;
		}
	}
	
	public void deleteBoard() {
		board = null;
	}
}
