import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class InputOutput {
    public static ByteArrayOutputStream getOutputStream() {
        var baos = new ByteArrayOutputStream();
        var ps = new PrintStream(baos);
        System.setOut(ps);
        return baos;
    }

    public static void sendInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
}
