public class Piece implements ConstantesDeplacement{

	private int color = 0;
	public Piece() {}

	public Piece(int color) {
		this.color = color;
	}

	public int getColor() {
		return this.color;
	}

	public boolean isEmpty() {
		return (this.color == 0);
	}

	public static int getCase120(int position) {
		return ConstantesDeplacement.tab120[position];
	}

	public static int getCase64(int position) {
		return ConstantesDeplacement.tab64[position];
	}

	public static void main(String[] args) {
		Piece p = new Piece();
		System.out.println(p.isEmpty());
	}

}
