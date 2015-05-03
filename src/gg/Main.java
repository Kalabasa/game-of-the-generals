package gg;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// Piece piece = new Piece();
		// System.out.println(piece.getPieceRank());
		System.out.println("Hello world!");
//		Gui gui = new Gui();
		
		Engine engine = new Engine();
		engine.start();
		engine.setAPiece(true, 0, 1, 0); // flag of white
		engine.setAPiece(true, 1, 0, 1);
		engine.setAPiece(false, 4, 5, 0); // flag of black
		engine.play(false, 5, 0, 4, 0);
		System.out.println(engine.board.getPieceAt(4, 0).getPieceRank());
		engine.play(false, 4, 0, 3, 15);
		
		engine.play(false, 3, 0, 2, 0);
		
		engine.play(false, 2, 0, 1, 0);
		
		
		
	}

}
