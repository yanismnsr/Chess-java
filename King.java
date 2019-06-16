import java.util.ArrayList;


public class King extends Piece{

	public static int[] listeMouvements = {-11, -10, -9, -1, 1, 9, 10, 11};
	public static int[] mouvementsRoque = {-2, 2};

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


		if (board.getGRoque1() || board.getGRoque2()){
			pos = Piece.getCase120(Piece.getCase64(index) + King.mouvementsRoque[0]);
			int pos1 = Piece.getCase120(Piece.getCase64(index) -3);
			int pos2 = Piece.getCase120(Piece.getCase64(index) - 1);
			if (pos != -1 && pos1 != -1 && pos2 != -1 && board.getCase(pos).isEmpty() && board.getCase(pos1).isEmpty() && board.getCase(pos2).isEmpty() && !mouvementsInterdits.contains(pos)){
				liste.add(pos);
			}
		}
		if (board.getPRoque1() || board.getPRoque2()){
			pos = Piece.getCase120(Piece.getCase64(index) + King.mouvementsRoque[1]);
			int pos1 = Piece.getCase120(Piece.getCase64(index) + 1);
			if (pos != -1 && pos1 != -1 && board.getCase(pos).isEmpty() && board.getCase(pos1).isEmpty() && !mouvementsInterdits.contains(pos)){
				liste.add(pos);
			}
		}

		return liste;

	}


	public String toString(){
		if (this.getColor() == 1){
			return "" + (char)9812;
		}else{
			return "" + (char)9818;
		}
	}


	public String pieceToFEN(){
		if (this.getColor() == 1){
			return "K";
		}else{
			return "k";
		}
	}


	public boolean mouvementValide(Chessboard board, int pos){
		ArrayList<Integer> mouvements = this.movements(board);
		return mouvements.contains(pos);
	}


	public static void main (String[] args){
		Chessboard b = new Chessboard();
		King k = (King)b.getCase(59);
		System.out.println(k.movements(b));
		System.out.println(k);
	}

}
