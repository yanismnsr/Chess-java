package objects.pieces;

import objects.Chessboard;

import java.util.ArrayList;


public class Tower extends Piece{

	public static int[] listeMouvements = {1, 10, -1, -10};

	public Tower(int color) {
		super(color);
	}

	private ArrayList<Integer> function(Chessboard board, int option){
		ArrayList<Integer> liste = new ArrayList<Integer>();
		int index = board.indexOf(this);
		int pos;
		for (int i = 0; i < Tower.listeMouvements.length; i++){
			pos = Piece.getCase120(Piece.getCase64(index) + Tower.listeMouvements[i]);
			for (int j = 2; pos != -1 && board.getCase(pos).isEmpty(); j++){
				liste.add(pos);
				pos = Piece.getCase120(Piece.getCase64(index) + j * Tower.listeMouvements[i]);
			}
			switch (option) {
				case 0: if (pos != -1 && board.getCase(index).getColor() == this.getColor()) {
					liste.add(pos);
				}
				case 1: if (pos != -1 && board.getCase(index).getColor() != this.getColor()) {
					liste.add(pos);
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
	public ArrayList<Integer> casesMangeables(objects.Chessboard board){

		ArrayList<Integer> liste = new ArrayList<Integer>();
		int index = board.indexOf(this);
		int pos;
		for (int i = 0; i < objects.pieces.Tower.listeMouvements.length; i++){
			pos = objects.pieces.Piece.getCase120(objects.pieces.Piece.getCase64(index) + objects.pieces.Tower.listeMouvements[i]);
			for (int j = 2; pos != -1 && board.getCase(pos).isEmpty(); j++){
				liste.add(pos);
				pos = objects.pieces.Piece.getCase120(objects.pieces.Piece.getCase64(index) + j * objects.pieces.Tower.listeMouvements[i]);
			}
			if (pos != -1 && board.getCase(index).getColor() == this.getColor()){
				liste.add(pos);
			}
		}
		return liste;

	}


	public ArrayList<Integer> movements(objects.Chessboard board){

		int pos;
		int index = board.indexOf(this);
		ArrayList<Integer> liste = new ArrayList<Integer>();
		for (int i = 0; i < objects.pieces.Tower.listeMouvements.length; i++){
			pos = objects.pieces.Piece.getCase120(objects.pieces.Pawn.getCase64(index) + objects.pieces.Tower.listeMouvements[i]);
			for (int j = 2; pos != -1 && board.getCase(pos).isEmpty(); j++){
				liste.add(pos);
				pos = objects.pieces.Piece.getCase120(objects.pieces.Pawn.getCase64(index) + objects.pieces.Tower.listeMouvements[i] * j);
			}
			if (pos != -1 && board.getCase(pos).getColor() != this.getColor()){
				liste.add(pos);
			}
		}
		return liste;

	}*/


	
	
	public String toString(){
		if (this.getColor() == 1){
			return "" + (char)9814;
		}else{
			return "" + (char)9820;
		}
	}


	public static void main (String[] args){
		Chessboard b = new Chessboard();
		Tower t = (Tower)b.getCase(56);
		System.out.println(t.movements(b));
		System.out.println(t.casesMangeables(b));
	}

}
