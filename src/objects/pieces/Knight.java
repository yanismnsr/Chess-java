package objects.pieces;

import objects.Chessboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



public class Knight extends Piece{

	public static int[] listeMouvements = {-21, -19, -12, -8, 8, 12, 19, 21};

	public Knight(int color) {
		super(color);
	}

	private ArrayList<Integer> function(Chessboard board, int option){
		int index = board.indexOf(this);
		IntStream stream = Arrays.stream(Knight.listeMouvements).map(x -> Piece.getCase120(Piece.getCase64(index) + x));
		switch (option){
			case 0: stream = stream.filter(x -> x != -1 && board.getCase(x).getColor() == this.getColor());
			case 1: stream = stream.filter(x -> x != -1 && board.getCase(x).getColor() != this.getColor());
		}
		return  new ArrayList<>(stream.boxed().collect(Collectors.toList()));

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
		int pos;
		int index = board.indexOf(this);

		for (int i = 0; i < objects.pieces.Knight.listeMouvements.length; i++){
			pos = objects.pieces.Piece.getCase120(objects.pieces.Piece.getCase64(index) + objects.pieces.Knight.listeMouvements[i]);
			if (pos != -1 && board.getCase(pos).getColor() == this.getColor()){
				liste.add(pos);
			}
		}

		return liste;

	}


	public ArrayList<Integer> movements(objects.Chessboard board){

		ArrayList<Integer> liste = new ArrayList<Integer>();
		int pos;
		int index = board.indexOf(this);

		for (int i = 0; i < objects.pieces.Knight.listeMouvements.length; i++){
			pos = objects.pieces.Piece.getCase120(objects.pieces.Piece.getCase64(index) + objects.pieces.Knight.listeMouvements[i]);
			if (pos != -1 && board.getCase(pos).getColor() != this.getColor()){
				liste.add(pos);
			}
		}

		return liste;
	}*/
	
	
	public String toString(){
		if (this.getColor() == 1){
			return "" + (char)9816;
		}else{
			return "" + (char)9822;
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
		}
	}

}