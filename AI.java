import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AI {
    public static void getMove(Chessboard chessboard) throws IOException, InterruptedException {
        String cmd = "java -jar ChessAI.jar \"" + chessboard.boardToFEN() + "\"";
        System.out.println(cmd);
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = "";
        while ((line=buf.readLine())!=null) {
            System.out.println(line);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException{
        AI.getMove(new Chessboard());


    }
}
