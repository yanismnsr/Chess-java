import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


public class Pawn extends Piece{

	private static int[] listeMouvements = {-10, -9, -11};						// Liste de mouvements possibles selon le plateau120
	public static int[] listeFirstMoove = {-20, -21, -19};						// liste des mouvements complémentaires si on est dans le premier mouvement
	private boolean firstMove;													// si le pion n'a pas encore bougé
	private int prisePassant = -1;

	public Pawn(int color) {
		super(color);
		this.firstMove = true;
	}

	public void setFirstMove(boolean b) {
		this.firstMove = b;
	}


	public int getPrisePassant(){
		return this.prisePassant;
	}


	public ArrayList<Integer> casesMangeables(Chessboard board){

		//ArrayList<Integer> liste = new ArrayList<Integer>();
		int[] depMangeables = {-11, -9};
		int index = board.indexOf(this);
		// int pos;
		int coef;
		if (this.getColor() == 1){
			coef = 1;
		}else {
			coef = -1;
		}


		return new ArrayList<Integer>(Arrays.stream(depMangeables)
				.map(x -> Piece.getCase120(Piece.getCase64(index) + coef * x))
				.filter(x -> x !=1).boxed()
				.collect(Collectors.toList()));
	}



	public ArrayList<Integer> movements(Chessboard board) {
		ArrayList<Integer> liste = new ArrayList<Integer>(); 					// liste des déplacements possibles
		int coef;																// si c'est le joueur 1, on va d'un coté, sinon on va dans l'autre
		int index = board.indexOf(this);
		Piece p;

		if (this.getColor() == 1) {												// initialiser le coefficient en fonction du joueur.
			coef = 1;
		}else{
			coef = -1;
		}



		int pos = Piece.getCase120(Piece.getCase64(index) + coef * Pawn.listeMouvements[0]); // obtention de la case qui correspond à la case 1 de la liste des mouvements possibles prévue

		if (pos != -1) {														// si on a pas débordé
			p = board.getCase(pos);										// on récupere la pièce correspondant à la position
			if (p.isEmpty()){													// si la case correspondant à la position est vide
				liste.add(pos);													// on ajoute la case à la liste
			}
		}

		Arrays.stream(Pawn.listeMouvements)
				.map(x -> Piece.getCase120(Piece.getCase64(index) + coef * x)).filter(x -> x != -1)
				.filter(x -> board.getCase(x).getColor() == board.getJoueurAdverse())
				.forEach(x -> liste.add(x));


		/*
		for (int i = 1; i < objects.pieces.Pawn.listeMouvements.length; i++) {					// on parcours la liste des mouvements
			pos = objects.pieces.Piece.getCase120(objects.pieces.Piece.getCase64(index) + coef * objects.pieces.Pawn.listeMouvements[i]);	// initialisation de la position avec le premier coup dans le tableau
			if (pos != -1){														// si on déborde pas
				p = board.getCase(pos);											// on récupère la case correspondante à la position
				if (p.getColor() == board.getJoueurAdverse()) {					// si dans la case il y a une pièce adverse
					liste.add(pos);												// rajouter la case à la liste des mouvements (on peut la manger)
				}
			}
		}*/

		if (this.firstMove) {													// si le pion n'a jamais bougé, il peut avancer de 2 cases
			pos = Piece.getCase120(Piece.getCase64(index) + coef * Pawn.listeFirstMoove[0]);	// on récupère la position correspondante à la position selon listeFirstMoove
			if (pos != -1) {													// si on a pas débordé
				liste.add(pos);													// on ajoute la position à la liste des déplacements
			}
		}
		return liste;															// on retourne la liste
	} // fin de la fonction movements


	// controle si le mouvement est dans la liste des déplcaments
	public boolean mouvementValide(Chessboard board, int pos){
		ArrayList<Integer> mouvements = this.movements(board);
		return mouvements.contains(pos);
	}



	public String toString(){
		if (this.getColor() == 1){
			return "" + (char)9817;
		}else{
			return "" + (char)9823;
		}
	}


	public String pieceToFEN(){
		if (this.getColor() == 1){
			return "P";
		}else{
			return "p";
		}
	}

	public void setPrisePassant(int pos){
		this.prisePassant = pos;
	}


	public boolean isFirstMove(){
		return this.firstMove;
	}


	public static void main (String[] args) {
		Chessboard b = new Chessboard();
		Pawn p = (Pawn)b.getCase(8);
		ArrayList<Integer> a = p.movements(b);
		System.out.println(a);
		System.out.println(p.casesMangeables(b));
		System.out.println(p);
	}

}
