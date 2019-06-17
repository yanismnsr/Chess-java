import java.util.ArrayList;


public class Bishop extends Piece{

	public static int[] listeMouvements = {-11, -9, 9, 11};


	public Bishop(int color) {
		super(color);
	}


	private ArrayList<Integer> function(Chessboard board, int option) {
		ArrayList<Integer> liste = new ArrayList();
		int index = board.indexOf(this);
		int i = 0;

		while(i < listeMouvements.length) {
			int pos = Piece.getCase120(Piece.getCase64(index) + listeMouvements[i]);

			for(int j = 0; pos != -1 && board.getCase(pos).isEmpty(); ++j) {
				liste.add(pos);
				pos = Piece.getCase120(Piece.getCase64(index) + j * listeMouvements[i]);
			}

			switch(option) {
				case 0:
					if (pos != -1 && board.getCase(pos).getColor() == this.getColor()) {
						liste.add(pos);
					}
				case 1:
					if (pos != -1 && board.getCase(pos).getColor() != this.getColor()) {
						liste.add(pos);
					}
				default:
					++i;
			}
		}

		return liste;
	}

	public ArrayList<Integer> casesMangeables(Chessboard board) {
		return this.function(board, 0);
	}

	public ArrayList<Integer> movements(Chessboard board) {
		return this.function(board, 1);
	}


	public String toString(){
		if (this.getColor() == 1){
			return "" + (char)9815;
		}else{
			return "" + (char)9821;
		}
	}


	public String pieceToFEN(){
		if (this.getColor() == 1){
			return "B";
		}else{
			return "b";
		}
	}


	public boolean mouvementValide(Chessboard board, int pos){
		ArrayList<Integer> mouvements = this.movements(board);
		return mouvements.contains(pos);
	}


	public static void main(String[] args){
		Chessboard b = new Chessboard();
		Bishop bis = (Bishop)b.getCase(58);
		System.out.println(bis.movements(b));
		System.out.println(b.casesMangeablesPar(2));
		System.out.println(bis);
	}

}
