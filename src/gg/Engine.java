package gg;

import java.util.LinkedList;
import java.util.ListIterator;

public class Engine {

	Board board;
	boolean isFinished;

	boolean winner; // 1 = white, 0 - black
	private boolean currentTurn;
	LinkedList<Piece> listOfAvailablePieces = new LinkedList<Piece>();
	private final int[] STARTING_PIECES = { 0, 1, 1, 1, 1, 1, 1, 2, 3, 4, 5, 6,
			7, 8, 9, 10, 11, 12, 13, 14, 14 };

	public Engine() {
		board = new Board();
		isFinished = false;
		currentTurn = true;
	}

	public boolean getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(boolean currentTurn) {
		this.currentTurn = currentTurn;
	}

	public boolean getWinner() {
		return winner;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public LinkedList<Piece> getListOfAvailablePieces() {
		return listOfAvailablePieces;
	}

	public void removePieceFromList(int rank) {
		ListIterator<Piece> i = listOfAvailablePieces.listIterator();
		while (i.hasNext()) {
			Piece p = i.next();
			if (p.getPieceRank() == rank) {
				i.remove();
				break;
			}
		}
	}

	public void addPieceToList(int rank) {
		Piece p = new Piece(true, rank);
		listOfAvailablePieces.add(p);
	}

	public void getMyPieces(boolean team) {
		for (int i = 0; i < STARTING_PIECES.length; i++) {
			Piece newPiece = new Piece(team, STARTING_PIECES[i]);
			listOfAvailablePieces.add(newPiece);
		}

	}

	public boolean isPieceListEmpty() {
		return listOfAvailablePieces.isEmpty();
	}

	public boolean hasThisPiece(int rank) {
		for (Piece p : listOfAvailablePieces) {
			if (p.getPieceRank() == rank) {
				return true;
			}
		}
		return false;
	}

	public boolean setAPiece(boolean team, int rank, int row, int col) {
		if (!hasThisPiece(rank)) {
			return false;
		}
		if (validPlacement(team, row, col)) {
			board.initializePiece(team, rank, row, col);
			removePieceFromList(rank);
			return true;
		} else {
			return false;
		}
	}

	public boolean unSetAPiece(int row, int col) {
		if (board.getPieceAt(row, col) == null) {
			return false;
		}

		int rank = board.getPieceAt(row, col).getPieceRank();
		addPieceToList(rank);
		board.removePieceFromBoard(row, col);
		return true;
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

	public void play(boolean team, int row, int col, int newRow, int newCol)
			throws InvalidMoveException {
		if (team != currentTurn) {
			throw new InvalidMoveException(
					"You cannot use your opponent's piece.");
		}
		if (board.getPieceAt(row, col) == null) {
			throw new InvalidMoveException(
					"You cannot move a piece that is already gone.");
		}

		System.out.println(row + " " + col + " to " + newRow + " " + newCol);
		boolean isChallengerFlag = false, isDefenderFlag = false;

		if (isMovePossible(team, row, col, newRow, newCol)) {


			if (board.getPieceAt(row, col).getPieceRank() == 0) {
				isChallengerFlag = true;
				if ((team && newRow == board.getHEIGHT() - 1)
						|| (!team && newRow == 0)) { // check kung dulo na
					if (
							(
									(newCol + 1 < board.getHEIGHT()) 
									&& (board.getPieceAt(newRow, newCol + 1) == null
									|| board.getPieceAt(newRow, newCol + 1).getTeam() == team)
							)
							|| 
							(
									(newCol - 1 >= 0)
									&& (board.getPieceAt(newRow, newCol - 1) == null
									|| board.getPieceAt(newRow, newCol - 1).getTeam() == team)
							)
						) { // check kung may katabing kalaban

						isFinished = true;
						winner = team;
					} else {
						throw new InvalidMoveException(
								"You can't move to that position since there are enemies waiting.");
					}
				}
			}
			if (board.getPieceAt(newRow, newCol) != null
					&& board.getPieceAt(newRow, newCol).getPieceRank() == 0) {
				isDefenderFlag = true;
			}

			if (isDefenderFlag) { //
				System.out.println("challenger");
				isFinished = true;
				winner = team;

			} else if (!isDefenderFlag && isChallengerFlag
					&& board.getPieceAt(newRow, newCol) != null) {
				System.out.println("defender");
				isFinished = true;
				winner = !team;
			}
			board.move(row, col, newRow, newCol);
		} else {
			throw new InvalidMoveException(
					"You can only move your piece one tile at a time within the board.");
		}
		currentTurn = !currentTurn;
	}

	public void gameOver(boolean result) {
		// TODO gameover shiz
	}

	private boolean isMovePossible(boolean team, int row, int col, int newRow,
			int newCol) {

		if (Math.abs(row - newRow) + Math.abs(col - newCol) != 1) { // check if
																	// move too
																	// far
			return false;
		}
		if (board.getPieceAt(newRow, newCol) == null) {
			return true;
		}
		if ((board.getPieceAt(row, col).getTeam() != team) // check if you are
															// moving your own
															// piece
				|| (team == board.getPieceAt(newRow, newCol).getTeam())) { // check
																			// if
																			// you
																			// are
																			// eating
																			// your
																			// own
																			// piece
			return false;
		} else {
			return true;
		}
	}

	public void deleteBoard() {
		board = null;
	}

	class InvalidMoveException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4763533106425767465L;

		public InvalidMoveException(String message) {
			super(message);
		}
	}
}
