import java.io.IOException;
import java.io.InputStream;

public class Displayer {
    public static void update(String FEN) throws IOException, InterruptedException {
        Process proc = Runtime.getRuntime().exec("java -jar chessDisplayer.jar \"" + FEN + "\"");
        proc.waitFor();

        InputStream in = proc.getInputStream();
        InputStream err = proc.getErrorStream();

        byte b[]=new byte[in.available()];
        in.read(b,0,b.length);
        System.out.println(new String(b));

        byte c[]=new byte[err.available()];
        err.read(c,0,c.length);
        System.out.println(new String(c));

    }

    public static void main (String[] args) throws IOException, InterruptedException {
        Displayer.update("2q5/3kr3/7p/2pN1r2/3R4/1P1BB3/2P2PP1/4b1K1");
    }
}
