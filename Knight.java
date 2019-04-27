import java.util.ArrayList;


public class Knight extends Piece{

	public static int[] listeMouvements = {-21, -19, -12, -8, 8, 12, 19, 21};

	public Knight(int color) {
		super(color);
	}


	public ArrayList<Integer> movements(int position, Chessboard board){

		ArrayList<Integer> liste = new ArrayList<Integer>();
		int pos;

		for (int i = 0; i < Knight.listeMouvements.length; i++){
			System.out.println(Knight.listeMouvements[i]);
			pos = Piece.getCase120(Piece.getCase64(position) + Knight.listeMouvements[i]);
			if (pos != -1 && board.getCase(pos).getColor() != this.getColor()){
				liste.add(pos);
			}
		}

		return liste;
	}

	public static void main(String[] args){
		Chessboard b = new Chessboard();
		Piece p = b.getCase(1);
		if (p instanceof Knight){
			Knight k = (Knight)p;
			ArrayList<Integer> a = k.movements(1, b);
			System.out.println(a);
		}
	}

}
