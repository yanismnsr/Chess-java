package Objects.Pieces;

import Objects.Chessboard;

import java.util.ArrayList;


public class King extends Piece{

	public static int[] listeMouvements = {-11, -10, -9, -1, 1, 9, 10, 11};

	public King (int color) {
		super(color);
	}

	public ArrayList<Integer> movements(Chessboard board){

		int pos;
		int index = board.indexOf(this);
		ArrayList<Integer> liste = new ArrayList<Integer>();
		int joueurAdverse =  board.getJoueurAdverse();
		ArrayList<Integer> mouvementsInterdits = board.casesMangeablesPar(joueurAdverse);

		for (int i = 0; i < King.listeMouvements.length; i++){
			pos = Piece.getCase120(Piece.getCase64(index) + King.listeMouvements[i]);
			if (pos != -1 && !mouvementsInterdits.contains(pos) && board.getCase(pos).getColor() != this.getColor()){
				liste.add(pos);
			}
		}

		return liste;

	}

	public static void main (String[] args){
		Chessboard b = new Chessboard();
		King k = (King)b.getCase(59);
		System.out.println(k.movements(b));
	}

}
