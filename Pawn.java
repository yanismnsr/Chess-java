import java.util.ArrayList;


public class Pawn extends Piece{

	private static int[] listeMouvements = {-10, -9, -11};						// Liste de mouvements possibles selon le plateau120
	public static int[] listeFirstMoove = {-20, -21, -19};						// liste des mouvements complémentaires si on est dans le premier mouvement
	private boolean firstMove;									// si le pion n'a pas encore bougé

	public Pawn(int color) {
		super(color);
		this.firstMove = true;
	}

	public void setFirstMove(boolean b) {
		this.firstMove = b;
	}



	public ArrayList<Integer> movements(int position, Chessboard board) {
		ArrayList<Integer> liste = new ArrayList<Integer>(); 					// liste des déplacements possibles
		int coef;										// si c'est le joueur 1, on va d'un coté, sinon on va dans l'autre

		if (this.getColor() == 1) {								// initialiser le coefficient en fonction du joueur.
			coef = 1;
		}else{
			coef = -1;
		}

		int pos = Piece.getCase120(Piece.getCase64(position) + coef * Pawn.listeMouvements[0]); // obtention de la case qui correspond à la case 1 de la liste des mouvements possibles prévue

		if (pos != -1) {									// si on a pas débordé
			Piece p = board.getCase(pos);							// on récupere la pièce correspondant à la position
			if (p.isEmpty()){								// si la case correspondant à la position est vide
				liste.add(pos);								// on ajoute la case à la liste
			}
		}
		pos = Piece.getCase120(Piece.getCase64(position) + coef * Pawn.listeMouvements[1]);	// initialisation de la position avec le premier coup dans le tableau
		for (int i = 1; i < Pawn.listeMouvements.length && pos != -1; i++) {			// tant qu'on a pas débordé
			p = board.getCase(pos);								// on récupère la case correspondante à la position
			if (p.getColor() == board.getJoueurAdverse()) {					// si dans la case il y a une pièce adverse
				liste.add(pos);								// rajouter la case à la liste des mouvements (on peut la manger)
			}
			pos = Piece.getCase120(Piece.getCase64(position) + coef * Pawn.listeMouvements[1]); // position suivante
		}

		if (this.firstMove) {									// si le pion n'a jamais bougé, il peut avancer de 2 cases
			pos = Piece.getCase120(Piece.getCase64(position) + coef * Pawn.listeFirstMoove[0]); // on récupère la position correspondante à la position selon listeFirstMoove
			if (pos != -1) {								// si on a pas débordé
				liste.add(pos);								// on ajoute la position à la liste des déplacements
			}
		}
		return liste;										// on retourne la liste
	} // fin de la fonction movements


	// controle si le mouvement est dans la liste des déplcaments
	public boolean mouvementValide(int positionDepart, int positionArrive, Chessboard board) {
		ArrayList<Integer> a = this.movements(positionDepart, board);
		if (a.contains(positionArrive)) {
			return true;
		}
		return false;
	}


	public static void main (String[] args) {
		Chessboard b = new Chessboard();
		Pawn p = (Pawn)b.getCase(49);
		ArrayList<Integer> a = p.movements(49, b);
		System.out.println(a);
	}

}

