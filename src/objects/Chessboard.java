package objects;

import objects.pieces.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Chessboard {

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
	/*
	private Piece[] board = {
			new Tower(2), new Knight(2), new Bishop(2), new Queen(2), new King(2), new Bishop(2), new Knight(2), new Tower(2),
			new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2), new Pawn(2),
			new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(),
			new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(),
			new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(),
			new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(),
			new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1),
			new Tower(1), new Knight(1), new Bishop(1), new King(1), new Queen(1), new Bishop(1), new Knight(1), new Tower(1),
	};*/

	private Piece[] board;
	private boolean roque;
	private String[] history;

	public Chessboard() {
		this.tourJeu = 1;
		this.roque = true;
		this.board = Chessboard.fenToBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");

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


	public int indexOf(Piece p){
		/*
		for (int i = 0; i < this.getBoard().length; i++){
			if (this.getCase(i) == p){
				return i;
			}
		}
		return -1;*/
		return java.util.Arrays.asList(board).indexOf(p);
	}


	public ArrayList<Integer> casesMangeablesPar(int joueur){
		ArrayList<Integer> liste = new ArrayList<Integer>();

		Arrays.stream(board).filter(p -> p.getColor() == joueur).forEach(p -> {
			if (p instanceof Pawn){
				liste.addAll(((Pawn)p).casesMangeables(this));
			}else if(p instanceof Tower){
				liste.addAll(((Tower)p).casesMangeables(this));
			}else if(p instanceof Knight){
				liste.addAll(((Knight)p).casesMangeables(this));
			}else if(p instanceof Bishop){
				liste.addAll(((Bishop)p).casesMangeables(this));
			}else if (p instanceof Queen) {
				liste.addAll(((Queen) p).casesMangeables(this));
			}
		});

		return liste;



		/*
		ArrayList<Integer> liste = new ArrayList<Integer>();
		ArrayList<Integer> sousListe = new ArrayList<Integer>();
		objects.pieces.Piece p;

		for (int i = 0; i < this.board.length; i++){
			p = this.getCase(i);
			if (p.getColor() == joueur){
				if (p instanceof objects.pieces.Pawn){
					sousListe = ((objects.pieces.Pawn)p).casesMangeables(this);
					liste.addAll(sousListe);
				}else if(p instanceof objects.pieces.Tower){
					sousListe = ((objects.pieces.Tower)p).casesMangeables(this);
					liste.addAll(sousListe);
				}else if(p instanceof objects.pieces.Knight){
					sousListe = ((objects.pieces.Knight)p).casesMangeables(this);
					liste.addAll(sousListe);
				}else if(p instanceof objects.pieces.Bishop){
					sousListe = ((objects.pieces.Bishop)p).casesMangeables(this);
					liste.addAll(sousListe);
				}else if (p instanceof objects.pieces.Queen){
					sousListe = ((objects.pieces.Queen)p).casesMangeables(this);
					liste.addAll(sousListe);
				}
			}
		}
		return liste;*/

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

	private String buildString(char c, int n) {
		char[] arr = new char[n];
		Arrays.fill(arr, c);
		return new String(arr);
	}

	public String boardToFEN2() {
		StringBuilder allchaine = new StringBuilder();
		IntStream.range(8, 65).filter(n -> n % 8 == 0).forEachOrdered(n -> {
			StringBuilder string = new StringBuilder(8);
			Arrays.stream(Arrays.copyOfRange(this.board, n - 8, n)).forEach(p -> string.append(p.pieceToFEN()));
			String formatstring = string.toString();
			for (int i = 8;  i >= 0; i --){
				if (!formatstring.contains(" ")) break;
				formatstring = formatstring.replace(buildString(' ', i), String.valueOf(i));
			}
			allchaine.append(formatstring + "/");
		});
		return allchaine.deleteCharAt(allchaine.length() -1 ).toString();
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
			e.printStackTrace();
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
			e.printStackTrace();
		}
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
		System.out.println(c.casesMangeablesPar(1));
		System.out.println(c.boardToFEN());
		System.out.println(c.boardToFEN2());

	}

}
