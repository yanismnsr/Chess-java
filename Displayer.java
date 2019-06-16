import java.io.IOException;

public class Displayer {
    public static void update(String FEN) throws IOException {
        Runtime.getRuntime().exec("java -jar chessDisplayer.jar \"" + FEN + "\"");


    }

    public static void main (String[] args) throws IOException {
        Displayer.update("2q5/3kr3/7p/2pN1r2/3R4/1P1BB3/2P2PP1/4b1K1");
    }
}
