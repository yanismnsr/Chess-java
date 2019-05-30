import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;

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
	private int tourJeu;
	private Piece[] board = {
			new Tower(2), new Knight(2), new Bishop(2), new Queen(2), new King(2), new Bishop(2), new Knight(2), new Tower(2),
			new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2),
			new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(),
			new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(),
			new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(),
			new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(),
			new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1),
			new Tower(1), new Knight(1), new Bishop(1), new King(1), new Queen(1), new Bishop(1), new Knight(1), new Tower(1),
	};
	private boolean roque;
	private String[] history;
	private Player joueurs[] = new Player[2];

	public Chessboard() {
		this.tourJeu = 1;
		this.roque = true;
	}

	public Chessboard(String player1, String player2) {
		this.tourJeu = 1;
		this.roque = true;
		this.joueurs[0] = new Player(player1, 1);
		this.joueurs[1] = new Player(player2, 2);
	}

	public Chessboard(Piece[] board, int tourJeu, boolean roque){
		this.board = board;
		this.tourJeu = tourJeu;
		this.roque = roque;
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


	public String boardToFEN(){
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
			bw.write(this.tourJeu + "\n");
			bw.write(this.roque + "\n");
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
			this.roque = roque;
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


	public boolean deplacer(int posDepart, int posArrive){
		Piece pd = this.getCase(posDepart);
		if (pd.isEmpty()){
			return false;
		}
		if (pd instanceof Pawn){
			Pawn piece = (Pawn)pd;
			if (!piece.mouvementValide(this, posArrive)){
				return false;
			}
			piece.setFirstMove(false);
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
		}else{
			this.setCase(posArrive, pd);
			this.setCase(posDepart, new Piece());
			Player player = this.joueurs[this.getTourJeu() - 1];
			player.manger(pa);
		}
		return true;

	}


	public static void main(String[] args) {
		Chessboard c = new Chessboard();
		System.out.println(c.getTourJeu());
		c.joueurAdverse();
		System.out.println(c.getTourJeu());
		c.joueurAdverse();
		System.out.println(c.getTourJeu());
		Piece[] board = c.getBoard();
		System.out.println(c.getBoard()[12].isEmpty());
		System.out.println(c.boardToFEN());
		Piece[] b = Chessboard.fenToBoard(c.boardToFEN());
		for (int i = 0; i< b.length; i++){
			System.out.println(b[i]);
		}
		c.sauvegarder();
		c.charger();
		b = c.getBoard();
		for (int i = 0; i< b.length; i++){
			System.out.println(b[i]);
		}
		System.out.print(c.getLine(2));
		System.out.println(c.getLastLine());
		c.show();
		c.deplacer(49, 33);
		c.deplacer(33, 17);
		c.show();
	}

}
