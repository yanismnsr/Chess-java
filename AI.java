import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AI {
    public static String getMove(Chessboard chessboard) throws IOException, InterruptedException {
        String cmd = "java -jar ChessAI.jar \"" + chessboard.boardToFEN() + "\"";
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = "";
        String move = "";
        while ((line=buf.readLine())!=null) {
            //System.out.println(move);
            move = line;
        }
        System.out.println(move);
        return move;
    }

    public static void main(String[] args) throws IOException, InterruptedException{
        System.out.println(AI.getMove(new Chessboard()));
    }
}
