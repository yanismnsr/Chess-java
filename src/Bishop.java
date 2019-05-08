import java.util.ArrayList;
import java.util.Arrays;


public class Bishop extends Piece {

	public static int[] listeMouvements = {-11, -9, 9, 11};


	public Bishop(int color) {
		super(color);
	}

	private ArrayList<Integer> function(Chessboard board, int option){
		ArrayList<Integer> liste = new ArrayList<Integer>();
		int index = board.indexOf(this);
		int pos;
		for (int i = 0; i < Bishop.listeMouvements.length; i++){
			pos = Piece.getCase120(Piece.getCase64(index) + Bishop.listeMouvements[i]);
			for (int j = 0; pos != -1 && board.getCase(pos).isEmpty(); j++){
				liste.add(pos);
				pos = Piece.getCase120(Piece.getCase64(index) + j * Bishop.listeMouvements[i]);
			}
			switch (option) {
				case 0: {if (pos != -1 && board.getCase(pos).getColor() == this.getColor()) {
					liste.add(pos);
				}
				}
				case 1: {if (pos != -1 && board.getCase(pos).getColor() != this.getColor()) {
						liste.add(pos);
					}
				}
			}
		}

		return liste;
	}







	public ArrayList<Integer> casesMangeables(Chessboard board){
		return function(board, 0);
	}

	public ArrayList<Integer> movements(Chessboard board){
		return function(board, 1);
	}



/*
	public ArrayList<Integer> casesMangeables(Chessboard board){

		ArrayList<Integer> liste = new ArrayList<Integer>();
		int index = board.indexOf(this);
		int pos;
		for (int i = 0; i < Bishop.listeMouvements.length; i++){
			pos = Piece.getCase120(Piece.getCase64(index) + Bishop.listeMouvements[i]);
			for (int j = 0; pos != -1 && board.getCase(pos).isEmpty(); j++){
				liste.add(pos);
				pos = Piece.getCase120(Piece.getCase64(index) + j * Bishop.listeMouvements[i]);
			}
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

		for (int i = 0; i < Bishop.listeMouvements.length; i++){
			pos = Piece.getCase120(Piece.getCase64(index) + Bishop.listeMouvements[i]);
			for (int j = 2; pos != -1 && board.getCase(pos).isEmpty(); j++){
				liste.add(pos);
				pos = Piece.getCase120(Piece.getCase64(index) + j * Bishop.listeMouvements[i]);
			}
			if (pos != -1 && board.getCase(pos).getColor() != this.getColor()){
				liste.add(pos);
			}
		}
		return liste;
	}*/

	
	public String toString(){
		if (this.getColor() == 1){
			return "" + (char)9815;
		}else{
			return "" + (char)9821;
		}
	}



	public static void main(String[] args){
		Chessboard b = new Chessboard();
		Bishop bis = (Bishop)b.getCase(58);
		System.out.println(bis.movements(b));
		System.out.println("" + (char)9812);
	}

}
