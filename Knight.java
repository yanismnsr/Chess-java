import java.util.ArrayList;


public class Knight extends Piece{

	public static int[] listeMouvements = {-21, -19, -12, -8, 8, 12, 19, 21};

	public Knight(int color) {
		super(color);
	}


	public ArrayList<Integer> casesMangeables(Chessboard board){

		ArrayList<Integer> liste = new ArrayList<Integer>();
		int pos;
		int index = board.indexOf(this);

		for (int i = 0; i < listeMouvements.length; i++){
			pos = Piece.getCase120(Piece.getCase64(index) + listeMouvements[i]);
			if (pos != -1 && board.getCase(pos).getColor() == this.getColor()){
				liste.add(pos);
			}
		}

		return liste;

	}


	public ArrayList<Integer> movements(Chessboard board){

		ArrayList<Integer> liste = new ArrayList<Integer>();
		int pos;
		int index = board.indexOf(this);

		for (int i = 0; i < Knight.listeMouvements.length; i++){
			pos = Piece.getCase120(Piece.getCase64(index) + Knight.listeMouvements[i]);
			if (pos != -1 && board.getCase(pos).getColor() != this.getColor()){
				liste.add(pos);
			}
		}

		return liste;
	}


	public boolean mouvementValide(Chessboard board, int pos){
		ArrayList<Integer> mouvements = this.movements(board);
		return mouvements.contains(pos);
	}


	public String toString(){
		if (this.getColor() == 1){
			return "" + (char)9816;
		}else{
			return "" + (char)9822;
		}
	}


	public String pieceToFEN(){
		if (this.getColor() == 1){
			return "N";
		}else{
			return "n";
		}
	}


	public static void main(String[] args){
		Chessboard b = new Chessboard();
		Piece p = b.getCase(1);
		if (p instanceof Knight){
			Knight k = (Knight)p;
			ArrayList<Integer> a = k.movements(b);
			System.out.println(a);
			System.out.println(k.casesMangeables(b));
			System.out.println(k);
		}
	}

}
