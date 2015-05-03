package gg;

public class Engine {
	
	Board board;
	boolean isFinished;
	boolean winner; // 1 = white, 0 - black
	boolean currentTurn;
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
		board = new Board();
		isFinished = false;
		currentTurn = true;
	}
	
	public boolean setAPiece(boolean team, int rank, int row, int col) {
		if (validPlacement(team, row, col)) {
			board.initializePiece(team, rank, row, col);
			return true;
		} else {
			return false;
		}
	}
	
	private boolean validPlacement(boolean team, int row, int col) {
		int range = 3;
		if (board.getPieceAt(row, col) != null) {
			return false;
		}
		if (team) { // white
			if (row < range && row >= 0) {
				return true;
			} else {
				return false;
			}
		} else {
			if (row < board.getHEIGHT() && row >= board.getHEIGHT() - range) {
				return true;
			} else {
				return false;
			}
		}
	}

	public void play(boolean team, int row, int col, int newRow, int newCol) throws InvalidMoveException {
		if (team != currentTurn) {
			throw new InvalidMoveException("You cannot use your opponent's piece.");
		}
		if (board.getPieceAt(row, col) == null) {
			throw new InvalidMoveException("You cannot move a piece that is already gone.");
		}
		
		System.out.println(row + " " + col + " to " + newRow + " " + newCol);
		boolean isChallengerFlag = false, isDefenderFlag = false;
		
		// TODO get user input
		
		if (isMovePossible(team, row, col, newRow, newCol)){
			
			if (board.getPieceAt(row,col).getPieceRank() == 0){ // check kung dulo na
				isChallengerFlag = true;
				if ((team && newRow == board.getHEIGHT() - 1 || !team && newRow == 0) && 
						(board.getPieceAt(newRow, newCol + 1) != null || board.getPieceAt(newRow, newCol - 1) != null)) {
					//TODO insert prompt here 
					return;
				}
			} 
			if (board.getPieceAt(newRow, newCol) != null && board.getPieceAt(newRow,newCol).getPieceRank() == 0 ){ 
				isDefenderFlag = true;
			}
			
			if (isDefenderFlag){ // 
				System.out.println("challenger");
				isFinished = true;
				winner = team;
				
			} else if (!isDefenderFlag && isChallengerFlag && board.getPieceAt(newRow,newCol) != null){
				System.out.println("defender");
				isFinished = true;
				winner = !team;
			}
			board.move(row, col, newRow, newCol);
		} else {
			throw new InvalidMoveException("You can only move your piece one tile at a time within the board.");
		}
		currentTurn = !currentTurn;
	}
	
	public void gameOver(boolean result) {
		
	}
	
	private boolean isMovePossible(boolean team, int row, int col, int newRow, int newCol) {

		if (Math.abs(row - newRow) + Math.abs(col - newCol) != 1) { // check if move too far
			return false;
		}
		if (board.getPieceAt(newRow, newCol) == null) {
			return true;
		}
		if ((board.getPieceAt(row, col).getTeam() != team) // check if you are moving your own piece
			|| (team == board.getPieceAt(newRow, newCol).getTeam())) { // check if you are eating your own piece
				return false;
		} else {
			return true;
		}
	}
	
	public void deleteBoard() {
		board = null;
	}
	
	class InvalidMoveException extends Exception
	{
	  /**
		 * 
		 */
		private static final long serialVersionUID = -4763533106425767465L;

	public InvalidMoveException(String message) {
	    super(message);
	  }
	}
}
