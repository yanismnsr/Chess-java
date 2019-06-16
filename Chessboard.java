import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Scanner;
import java.util.Hashtable;

public class Chessboard {


	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";


	private static String[] coord = {
			"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8",
			"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
			"a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
			"a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
			"a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
			"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
			"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
			"a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1",
	};
	private Hashtable<Integer, Pawn> dicoPrisePassant = new Hashtable<Integer, Pawn>();
	private int tourJeu;
	private Piece[] board = {
			new Tower(2), new Knight(2), new Bishop(2), new Queen(2), new King(2), new Bishop(2), new Knight(2), new Tower(2),
			new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2),
			new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(),
			new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(),
			new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(),
			new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(),
			new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1),
			new Tower(1), new Knight(1), new Bishop(1), new Queen(1), new King(1), new Bishop(1), new Knight(1), new Tower(1),
	};
	private boolean gRoque1;
	private boolean gRoque2;
	private boolean pRoque1;
	private boolean pRoque2;

	private String[] history;
	private Player joueurs[] = new Player[2];

	public Chessboard() {
		this.tourJeu = 1;
		this.gRoque1 = true;
		this.gRoque2 = true;
		this.pRoque1 = true;
		this.pRoque2 = true;
	}


	public boolean caseValide(int pos){
		if (pos < 0 || pos >= this.board.length){
			return false;
		}
		return true;
	}

	public Chessboard(String player1, String player2) {
		this.tourJeu = 1;
		this.gRoque1 = true;
		this.gRoque2 = true;
		this.joueurs[0] = new Player(player1, 1);
		this.joueurs[1] = new Player(player2, 2);
	}

	public Chessboard(Piece[] board, int tourJeu, boolean gRoque1, boolean gRoque2){
		this.board = board;
		this.tourJeu = tourJeu;
		this.gRoque1 = gRoque1;
		this.gRoque2 = gRoque2;
	}


	public Hashtable<Integer, Pawn> getDicoPrisePassant(){
		return this.dicoPrisePassant;
	}


	public static void effacerTerminal(){
		System.out.print("\033\143");
	}

	public int getTourJeu() {
		return this.tourJeu;
	}

	public int getJoueurAdverse() {
		return 2 - (this.getTourJeu() +1) % 2;
	}

	public void joueurAdverse() {
		this.tourJeu = 2 - (this.getTourJeu() + 1)%2;
	}

	public Piece[] getBoard() {
		return this.board;
	}

	public Piece getCase(int pos) {
		return this.board[pos];
	}

	public void setCase(int pos, Piece p){
		this.board[pos] = p;
	}

	public int indexOf(Piece p){
		for (int i = 0; i < this.getBoard().length; i++){
			if (this.getCase(i) == p){
				return i;
			}
		}
		return -1;
	}


	public ArrayList<Integer> casesMangeablesPar(int joueur){

		ArrayList<Integer> liste = new ArrayList<Integer>();
		ArrayList<Integer> sousListe = new ArrayList<Integer>();
		Piece p;
		for (int i = 0; i < this.board.length; i++){
			p = this.getCase(i);
			if (p.getColor() == joueur){
				if (p instanceof Pawn){
					sousListe = ((Pawn)p).casesMangeables(this);
					liste.addAll(sousListe);
				}else if(p instanceof Tower){
					sousListe = ((Tower)p).casesMangeables(this);
					liste.addAll(sousListe);
				}else if(p instanceof Knight){
					sousListe = ((Knight)p).casesMangeables(this);
					liste.addAll(sousListe);
				}else if(p instanceof Bishop){
					sousListe = ((Bishop)p).casesMangeables(this);
					liste.addAll(sousListe);
				}else if (p instanceof Queen){
					sousListe = ((Queen)p).casesMangeables(this);
					liste.addAll(sousListe);
				}
			}
		}
		return liste;

	}


	public ArrayList<Integer> mouvementsDuJoueur(int numJoueur){
		ArrayList<Integer> liste = new ArrayList<Integer>();
		ArrayList<Integer> sousListe = new ArrayList<Integer>();
		Piece p;
		for (int i = 0; i < this.board.length; i++){
			p = this.getCase(i);
			if (p.getColor() == numJoueur){
				if (p instanceof Pawn){
					sousListe = ((Pawn)p).movements(this);
					liste.addAll(sousListe);
				}else if(p instanceof Tower){
					sousListe = ((Tower)p).movements(this);
					liste.addAll(sousListe);
				}else if(p instanceof Knight){
					sousListe = ((Knight)p).movements(this);
					liste.addAll(sousListe);
				}else if(p instanceof Bishop){
					sousListe = ((Bishop)p).movements(this);
					liste.addAll(sousListe);
				}else if (p instanceof Queen){
					sousListe = ((Queen)p).movements(this);
					liste.addAll(sousListe);
				}else if (p instanceof King){
					sousListe = ((King)p).movements(this);
					liste.addAll(sousListe);
				}
			}
		}
		return liste;
	}


	public String tableauUniquementToFen(){
		String chaine = "";
		Piece p;
		int vide;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j <  8; j++){

				p = this.getCase(8*i + j);
				if (p.isEmpty()){
					vide = 1;
					j ++;
					while (j < 8){
						p = this.getCase(8*i + j);
						if (p.isEmpty()){
							vide++;
							j++;
						}else{
							j--;
							break;
						}
					}
					chaine += vide;
				}else{
					chaine += p.pieceToFEN();
				}

			}
			if (i != 7){
				chaine += "/";
			}
		}
		return chaine;
	}


	public String boardToFEN(){
		String chaine = this.tableauUniquementToFen();
		chaine += " ";
		if (this.pRoque1){
			chaine += 'K';
		}
		if (this.gRoque1){
			chaine += 'Q';
		}
		if (this.pRoque2){
			chaine +='k';
		}
		if (this.gRoque2){
			chaine += 'q';
		}
		if (!(this.pRoque1 || this.gRoque1 || this.pRoque2 || this.gRoque2)){
			chaine += "-";
		}
		chaine += " ";

		return chaine;
	}


	public static Piece fenToPiece(char caracter){
		Piece p = new Piece();
		switch (caracter){
			case 'P' :
				p = new Pawn(1);
				break;
			case 'K':
				p = new King(1);
				break;
			case 'Q':
				p = new Queen(1);
				break;
			case 'R':
				p = new Tower(1);
				break;
			case 'N':
				p = new Knight(1);
				break;
			case 'B':
				p = new Bishop(1);
				break;
			case 'p' :
				p = new Pawn(2);
				break;
			case 'k':
				p = new King(2);
				break;
			case 'q':
				p = new Queen(2);
				break;
			case 'r':
				p = new Tower(2);
				break;
			case 'n':
				p = new Knight(2);
				break;
			case 'b':
				p = new Bishop(2);
				break;
		}
		return p;
	}


	public static Piece[] fenToBoard(String chaine){
		String[] lignes = chaine.split("/");
		Piece[] board = new Piece[64];
		int cptBoard = 0;
		for (String ligne : lignes){
			for (int i = 0; i < ligne.length(); i++){
				char c = ligne.charAt(i);
				if ((int)c <= (int)'8' && (int)c >= (int)'1'){
					for (int j = 0; j < Integer.parseInt("" + c); j++){
						board[cptBoard] = new Piece();
						cptBoard++;
					}
				}else{
					board[cptBoard] = Chessboard.fenToPiece(c);
					cptBoard++;
				}
			}
		}
		return board;
	}

	public void sauvegarder(){
		try{
			File fichier = new File("sauvegarde_partie.txt");
			FileWriter fw = new FileWriter(fichier);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(this.joueurs[0] + "\n");
			bw.write(this.joueurs[1] + "\n");
			bw.write(this.boardToFEN());
			bw.close();
			fw.close();
		}catch (IOException e){
			System.out.println(e);
		}
	}


	public void charger(){
		try{
			FileReader fr = new FileReader(new File("sauvegarde_partie.txt"));
			BufferedReader br = new BufferedReader(fr);
			int tourJeu = Integer.parseInt(br.readLine());
			String line = br.readLine();
			boolean roque = line.equals("true");
			line = br.readLine();
			Piece[] board = Chessboard.fenToBoard(line);
			this.tourJeu = tourJeu;
			this.gRoque1 = roque;
			this.board = board;
			br.close();
			fr.close();
		}catch (IOException e){
			System.out.println(e);
		}
	}


	private String getLine(int i){
		String line = "   ";
		for (int j = 0; j < 8; j++){
			if ((i-j)%2 == 0){
				line = line + Chessboard.ANSI_WHITE_BACKGROUND + "       " + Chessboard.ANSI_RESET;
			}
			else{
				line = line + Chessboard.ANSI_BLUE_BACKGROUND + "       " + Chessboard.ANSI_RESET;
			}
		}
		line += "\n " + (8-i) + " ";
		for (int j = 0; j < 8; j++){
			if ((i-j)%2 == 0){
				line = line + Chessboard.ANSI_BLACK + Chessboard.ANSI_WHITE_BACKGROUND + "   " + this.getCase(i*8 + j) + "   " + Chessboard.ANSI_RESET;
			}
			else{
				line = line + Chessboard.ANSI_BLACK + Chessboard.ANSI_BLUE_BACKGROUND + "   " + this.getCase(i*8 + j) + "   " + Chessboard.ANSI_RESET;
			}
		}
		line += "\n   ";
		for (int j = 0; j < 8; j++){
			if ((i-j)%2 == 0){
				line = line + Chessboard.ANSI_WHITE_BACKGROUND + "       " + Chessboard.ANSI_RESET;
			}
			else{
				line = line + Chessboard.ANSI_BLUE_BACKGROUND + "       " + Chessboard.ANSI_RESET;
			}
		}
		line += "\n";
		return line;
	}


	private String getLastLine(){
		String line = "   ";
		char c = 'A';
		for (int i = 0; i < 8; i++){
			line += "   " + (char)((int)c + i) + "   ";
		}
		line += "\n";
		return line;
	}


	public void show(){
		String board = "";
		for (int i = 0; i < 8; i++){
			board += this.getLine(i);
		}
		board += this.getLastLine();
		System.out.println(board);
	}


	public boolean deplacer(int[] tabDep){
		if (tabDep.length != 2){
			return false;
		}
		if (this.mouvementsDuJoueur(this.getTourJeu()).size() == 0){
			this.joueurAdverse();
			return true;
		}
		int posDepart = tabDep[0];
		int posArrive = tabDep[1];
		Piece pd = this.getCase(posDepart);
		if (this.getCase(posDepart).getColor() != this.getTourJeu()){
			return false;
		}
		if (pd.isEmpty()){
			return false;
		}
		if (pd instanceof Pawn){
			Pawn piece = (Pawn)pd;
			if (!piece.mouvementValide(this, posArrive)){
				return false;
			}
		}else if(pd instanceof Bishop){
			Bishop piece = (Bishop)pd;
			if (!piece.mouvementValide(this, posArrive)){
				return false;
			}
		}else if (pd instanceof King){
			King piece = (King)pd;
			if (!piece.mouvementValide(this, posArrive)){
				return false;
			}
		}else if (pd instanceof Knight){
			Knight piece = (Knight)pd;
			if (!piece.mouvementValide(this, posArrive)){
				return false;
			}
		}else if (pd instanceof Queen){
			Queen piece = (Queen)pd;
			if (!piece.mouvementValide(this, posArrive)){
				return false;
			}
		}else{
			Tower piece = (Tower)pd;
			if (!piece.mouvementValide(this, posArrive)){
				return false;
			}
		}


		Piece pa = this.getCase(posArrive);
		if (pa.isEmpty()){
			this.setCase(posArrive, pd);
			this.setCase(posDepart, pa);
			System.out.println("izurzieurhgzoirghzoirh");
			if (pd instanceof Pawn){
				Pawn pawn = (Pawn)pd;
				if (pawn.isFirstMove() && Math.abs(posArrive - posDepart) == 16){
					pawn.setPrisePassant(Math.max(posArrive, posDepart) - 8);
					this.dicoPrisePassant.put(pawn.getPrisePassant(), (Pawn)pd);
					pawn.setFirstMove(false);
				}else if(!pawn.isFirstMove() && pawn.getPrisePassant() != -1){
					this.dicoPrisePassant.remove(pawn.getPrisePassant());
					pawn.setPrisePassant(-1);
				}
			}
			if (this.dicoPrisePassant.containsKey(posArrive)){
				Pawn pawn = (Pawn)(this.getCase(this.indexOf(this.dicoPrisePassant.get(posArrive))));
				Player player = this.joueurs[this.getTourJeu() - 1];
				player.manger(pawn);
				this.setCase(this.indexOf(pawn), new Piece());
				this.setCase(posArrive, pd);
				this.setCase(posDepart, pa);
				this.dicoPrisePassant.remove(pawn.getPrisePassant());
			}
		}else{
			this.setCase(posArrive, pd);
			this.setCase(posDepart, new Piece());
			Player player = this.joueurs[this.getTourJeu() - 1];
			player.manger(pa);
		}
		this.joueurAdverse();
		return true;

	}


	public int[] chaineToMouv(String chaine){
		int[] mouv = new int[2];
		chaine = chaine.toUpperCase();
		if (chaine.length() != 4){
			return new int[0];
		}else{
			int j = (int)chaine.charAt(0) - (int)'A';
			int i = (int)chaine.charAt(1) - (int)'1';
			mouv[0] = (7-i)*8 + j;
			j = (int)chaine.charAt(2) - (int)'A';
			i = (int)chaine.charAt(3) - (int)'1';
			mouv[1] = (7-i)*8 + j;
			if (!(this.caseValide(mouv[0]) && this.caseValide(mouv[1]))){
				return new int[0];
			}
		}
		return mouv;
	}


	public int[] saisirMouvement(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Joueur " + this.getTourJeu());
		System.out.println("Saisir un mouvement");
		String mouvement = sc.nextLine();
		int[] tab_mouvement = this.chaineToMouv(mouvement);
		while (tab_mouvement.length != 2){
			System.out.println("mouvement invalide \nsaisir un mouvement");
			mouvement = sc.nextLine();
			tab_mouvement = this.chaineToMouv(mouvement);
		}
		return tab_mouvement;
	}


	public static void afficherMenu(){
		boolean sauvegarde = false;
		File fichier = new File("sauvegarde_partie.txt");
		if (fichier.exists() && !fichier.isDirectory()){
			sauvegarde = true;
		}
		String chaine = "MENU\n- 0 pour terminer le jeu\n- 1 pour commencer une nouvelle partie";
		if (sauvegarde){
			chaine += "\n- 2 pour charger la partie";
		}
		System.out.println(chaine);
	}


	public static int choixMenu(){
		Chessboard.effacerTerminal();
		Chessboard.afficherMenu();
		Scanner sc = new Scanner(System.in);
		String choix = sc.nextLine();
		while (choix.length() != 1 || ((int)choix.charAt(0)-(int)'0') > 2 || ((int)choix.charAt(0)-(int)'0') < 0){
			Chessboard.effacerTerminal();
			System.out.println("Choix invalide, saisissez votre choix :");
			Chessboard.afficherMenu();
			choix = sc.nextLine();
		}
		return ((int)choix.charAt(0)-(int)'0');
	}


	public void menu(){
		int choix = choixMenu();
		if (choix == 0){
			System.exit(0);
		}else if (choix == 1) {
			this.nouvellePartie();
		}else{
			this.charger();
		}
	}


	public void nouvellePartie(){
		Scanner sc = new Scanner(System.in);
		String players[] = new String[2];
		do{
			System.out.println("saisir le nom du joueur 1 : ");
			players[0] = sc.nextLine();
		}while (players[0].length() == 0);
		do{
			System.out.println("saisir le nom du joueur 2 : ");
			players[1] = sc.nextLine();
		}while (players[1].length() == 0);
		this.joueurs[0] = new Player(players[0], 1);
		this.joueurs[1] = new Player(players[1], 2);
		this.tourJeu = 1;
		this.gRoque1 = true;
	}


	public boolean echec(int numJoueur){
		int posRoi = 0;
		Piece p;
		for (int i = 0; i < this.board.length; i++) {
			p = this.getCase(i);
			if (p instanceof King && p.getColor() == numJoueur){
				posRoi = i;
				break;
			}
		}
		int adverse;
		if (numJoueur == 1){
			adverse = 2;
		}else{
			adverse = 1;
		}
		ArrayList <Integer> a = this.mouvementsDuJoueur(adverse);
		if (a.contains(posRoi)){
			return true;
		}else{
			return false;
		}
	}


	public boolean echecEtMat(int numJoueur){

		int posRoi;
		Piece king = new Piece();
		for (int i = 0; i < this.board.length; i++){
			king = this.getCase(i);
			if (king instanceof King && king.getColor() == numJoueur){
				posRoi = i;
				break;
			}
		}
		if (this.echec(numJoueur) && ((King)king).movements(this).size() == 0){
			return true;
		}
		return false;

	}


	public boolean pat(){
		if (!(this.echecEtMat(1) && this.echecEtMat(2)) && this.mouvementsDuJoueur(1).size() == 0 && this.mouvementsDuJoueur(2).size() == 0){
			return true;
		}
		return false;
	}

	public boolean partieFinie(){
		return this.pat() || (this.echecEtMat(1) && this.echecEtMat(2));
	}


	public void jouer(){
		//this.effacerTerminal();
		this.show();
		System.out.println("C'est au joueur " + this.joueurs[this.getTourJeu()-1] + " de jouer");
		System.out.println(this.dicoPrisePassant);
		this.deplacer(this.saisirMouvement());
		while (! this.partieFinie()){
			this.jouer();
		}
	}

	public static void partie(){
		Chessboard board = new Chessboard();
		board.menu();
		board.sauvegarder();
		board.jouer();
	}



	public static void main(String[] args) {
		Chessboard.partie();

	}

}
