
public class Chessboard {
	
	private static String[] coord = {
			"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8",
			"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
			"a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
			"a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
			"a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
			"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
			"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
			"a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1",
	};
	private int tourJeu;
	private Piece[] board = {
			new Tower(2), new Knight(2), new Bishop(2), new Queen(2), new King(2), new Bishop(2), new Knight(2), new Tower(2),
			new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2),
			new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), 
			new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), 
			new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), 
			new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), 
			new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1),
			new Tower(1), new Knight(1), new Bishop(1), new King(1), new Queen(1), new Bishop(1), new Knight(1), new Tower(1),
	};
	private boolean roque;
	private String[] history;
	
	public Chessboard() {
		this.tourJeu = 1;
		this.roque = true;
		
	}
	
	public int getTourJeu() {
		return this.tourJeu;
	}
	
	public void joueurAdverse() {
		this.tourJeu = 2 - (this.getTourJeu() + 1)%2;
	}
	
	public Piece[] getBoard() {
		return this.board;
	}
	
	public static void main(String[] args) {
		Chessboard c = new Chessboard();
		System.out.println(c.getTourJeu());
		c.joueurAdverse();
		System.out.println(c.getTourJeu());
		c.joueurAdverse();
		System.out.println(c.getTourJeu());
		Piece[] board = c.getBoard();
		System.out.println(c.getBoard()[12].isEmpty());
		
	}
	
}
