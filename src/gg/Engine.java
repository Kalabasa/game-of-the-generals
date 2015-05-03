package gg;

public class Engine {
	
	Board board;
	
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
	public Engine(boolean player){

	}
	
	public void start() {
		board = new Board();
	}
	
	public void update() {
		int x=0, y=0, newX=0, newY=0;
		boolean winner;
		boolean isChallengerFlag = false, isDefenderFlag = false;
		
		//get user input
		
		if (isMovePossible(x, y, newX, newY)){
			if (board.getPieceAt(x,y).getPieceRank() == 0){
				isChallengerFlag = true;
			} 
			if (board.getPieceAt(newX,newY).getPieceRank() == 0){
				isDefenderFlag = true;
			}
			
			if (isDefenderFlag){
				//Challenger wins
				
			} else if (!isDefenderFlag && isChallengerFlag){
				//Defender wins
			}
			board.move(x, y, newX, newY);
		} else {
			//insert prompt here
		}
	}
	
	public void stop(boolean result) {
		
	}
	
	private boolean isMovePossible(int x, int y, int newX, int newY){
		if ((board.getPieceAt(x, y).getTeam() == board.getPieceAt(newX, newY).getTeam())){
				return false;
		} else {
			return true;
		}
	}
}
