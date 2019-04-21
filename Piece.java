
public class Piece implements ConstantesDeplacement{
	
	private int color = 0;
	
	public Piece() {
		
	}
	
	public Piece(int color) {
		this.color = color;
	}
	
	public boolean isEmpty() {
		return (this.color == 0);
	}
	
	public static void main(String[] args) {
		Piece p = new Piece();
		System.out.println(p.isEmpty());
	}

}
