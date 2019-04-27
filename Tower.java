import java.util.ArrayList;


public class Tower extends Piece{

	public static int[] listeMouvements = {1, 10, -1, -10};

	public Tower(int color) {
		super(color);
	}


	public ArrayList<Integer> movements(int position, Chessboard board){

		int pos;
		ArrayList<Integer> liste = new ArrayList<Integer>();
		for (int i = 0; i < Tower.listeMouvements.length; i++){
			pos = Piece.getCase120(Pawn.getCase64(position) + Tower.listeMouvements[i]);
			for (int j = 2; pos != -1 && board.getCase(pos).isEmpty(); j++){
				liste.add(pos);
				pos = Piece.getCase120(Pawn.getCase64(position) + Tower.listeMouvements[i] * j);
			}
		}
		return liste;

	}


	public static void main (String[] args){
		Chessboard b = new Chessboard();
		Tower t = (Tower)b.getCase(56);
		System.out.println(t.movements(56, b));
	}

}
