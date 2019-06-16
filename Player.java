
public class Player {

	private String name;
	private int color;
	private Piece casesManges[] = new Piece[12];
	private int nbrPiecesManges = 0;

	public Player(String name, int color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return this.name;
	}

	public int getColor() {
		return this.color;
	}

	public void manger(Piece p){
		this.casesManges[nbrPiecesManges] = p;
		this.nbrPiecesManges += 1;
	}


	public String toString(){
		return this.name;
	}



}
