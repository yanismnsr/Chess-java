import java.util.ArrayList;

public class Queen extends Piece{

	public static int[] listeMouvements = {-11, -10, -9, -1, 1, 9, 10, 11};

	public Queen(int color) {
		super(color);
	}


	public ArrayList<Integer> casesMangeables(Chessboard board){

		int index = board.indexOf(this);
		ArrayList<Integer> liste = new ArrayList<Integer>();
		int pos;
		for (int i = 0; i < Queen.listeMouvements.length; i++){
			pos = Piece.getCase120(Piece.getCase64(index) + Queen.listeMouvements[i]);
			for (int j = 2; pos != -1 && board.getCase(pos).isEmpty(); j++){
				liste.add(pos);
				pos = Piece.getCase120(Piece.getCase64(index) + j * Queen.listeMouvements[i]);
			}
			if (pos != -1 && board.getCase(pos).getColor() == this.getColor()){
				liste.add(pos);
			}
		}
		return liste;

	}


	public ArrayList<Integer> movements(Chessboard board){

		int index = board.indexOf(this);
		ArrayList<Integer> liste = new ArrayList<Integer>();
		int pos;
		for (int i = 0; i < Queen.listeMouvements.length; i++){
			pos = Piece.getCase120(Piece.getCase64(index) + Queen.listeMouvements[i]);
			for (int j = 2; pos != -1 && board.getCase(pos).isEmpty(); j++){
				liste.add(pos);
				pos = Piece.getCase120(Piece.getCase64(index) + j * Queen.listeMouvements[i]);
			}
			if (pos != -1 && board.getCase(pos).getColor() != this.getColor()){
				liste.add(pos);
			}
		}
		return liste;

	}


	public static void main(String[] args){
		Chessboard b = new Chessboard();
		Queen q = (Queen)b.getCase(3);
		System.out.println(q.movements(b));
		System.out.println(q.casesMangeables(b));
	}

}
